package com.tuniu.abt.service.issue.ctrip.module;

import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.issue.other.InnerIssueResultDto;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.ctrip.module.CtripCommonModule;
import com.tuniu.abt.service.ctrip.module.CtripPayModule;
import com.tuniu.abt.validator.group.CtripIssueScene;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderInfoEntity;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.RelationForExtNoEntity;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.CtripPayReq;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.FlightAlipayResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.OrderList;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.OrderType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by lanlugang on 2016/5/23.
 */
@Service
@Validated(CtripIssueScene.class)
public class CtripIssuePayService {

    @Resource
    private CtripCommonModule ctripCommonModule;
    @Resource
    private AbtPnrMainDao abtPnrMainDao;
    @Resource
    private CtripPayModule ctripPayModule;

    @Valid
    public IssueResult issuePayByCtrip(IssueRequest issueRequest) {
        IssueResult issueResult = new IssueResult();
        List<CtripPayReq> ctripPayReqs = this.preparePayReq(issueRequest);
        issueResult.setInnerData(ctripPayReqs);
        ctripPayReqs.forEach(ctripPayReq -> innerPay(issueResult, ctripPayReq));
        return issueResult;
    }

    private void innerPay(IssueResult issueResult, CtripPayReq ctripPayReq) {
        InnerIssueResultDto resultDto = new InnerIssueResultDto();
        try {
            FlightAlipayResponse response = ctripPayModule.flightAlipay(ctripPayReq);
            if (!response.isResult() && !response.getMessage().contains("扣款成功")) {
                resultDto.setSuccess(false);
                resultDto.setMsg("给携程支付失败，主订单号:" + ctripPayReq.getMainOrderID()
                        + ",失败原因:" + response.getMessage());
            }
        } catch (Exception e) {
            resultDto.setSuccess(false);
            resultDto.setMsg("给携程支付失败，主订单号:" + ctripPayReq.getMainOrderID() + ",失败原因:" + e.getMessage());
        }
        if (issueResult.getIssueStatus() == AbtTicketRequest.PAY_SUCCESS
                || (issueResult.getIssueStatus() == AbtTicketRequest.PAY_SUCCESS && !resultDto.isSuccess()
                || (issueResult.getIssueStatus() == AbtTicketRequest.PAY_FAIL && resultDto.isSuccess()))) {
            issueResult.setIssueStatus(AbtTicketRequest.PAY_PART_SUCCESS);
            issueResult.setSuccess(false);
        } else if (resultDto.isSuccess()) {
            issueResult.setIssueStatus(AbtTicketRequest.PAY_SUCCESS);
            issueResult.setSuccess(true);
        } else {
            issueResult.setIssueStatus(AbtTicketRequest.PAY_FAIL);
            issueResult.setSuccess(false);
        }
        ctripPayReq.getOrderList().getOrder().forEach(orderType -> issueResult.addResult(orderType.getOrderID(), resultDto));
    }

    private List<CtripPayReq> preparePayReq(IssueRequest issueRequest) {
        List<CtripPayReq> ctripPayReqs = new LinkedList<CtripPayReq>();
        //将请求报文中的pnr列表信息取出 再从占位表查出对应信息(对应携程子订单号)
        List<AbtPnrMain> pnrMains = abtPnrMainDao.findByFlightItemIdAndOrderId(issueRequest.getIssueDetail().getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
        if (CollectionUtils.isEmpty(pnrMains)) {
            throw new BizzException(TicketEx.UNEXISTS_ISSUE, "找不到对应的占位数据");
        }
        //将子订单号 按主订单号进行分组
        MultiValueMap<String, AbtPnrMain> groupedMainPnrs = new LinkedMultiValueMap<String, AbtPnrMain>();
        for (AbtPnrMain pnrMain : pnrMains) {
            groupedMainPnrs.add(pnrMain.getOutMainOrderId(), pnrMain);
        }
        //按分组后的子订单 组装支付请求参数
        for (Map.Entry<String, List<AbtPnrMain>> entry : groupedMainPnrs.entrySet()) {
            List<AbtPnrMain> subOrders = entry.getValue();
            /** 查询携程子订单信息 */
            List<String> subOrderIdList = new ArrayList<>();
            subOrders.forEach(subOrder -> subOrderIdList.add(subOrder.getPnr()));
            List<OrderInfoEntity> orderInfoEntities = ctripCommonModule.queryCtripOrderDetail(subOrderIdList);
            /** 校验携程订单状态 */
            checkCanPay(orderInfoEntities);
            /** 转换成支付请求 */
            CtripPayReq ctripPayReq = convertToPayRequest(Long.valueOf(issueRequest.getOrderId()), orderInfoEntities);
            ctripPayReqs.add(ctripPayReq);
        }
        return ctripPayReqs;
    }

    private static CtripPayReq convertToPayRequest(Long orderId, List<OrderInfoEntity> orderInfoEntities) {
        CtripPayReq ctripPayReq = new CtripPayReq();
        ctripPayReq.setOrderID(orderId);
        RelationForExtNoEntity relationForExtNoEntity = orderInfoEntities.get(0)
                .getRelationForExtNoInfo().getRelationForExtNoEntity().get(0);
        ctripPayReq.setMainOrderID(String.valueOf(relationForExtNoEntity.getOrderID()));
        ctripPayReq.setExternalNo(relationForExtNoEntity.getExternalNo());
        ctripPayReq.setOrderList(new OrderList());
        for (OrderInfoEntity orderInfoEntity : orderInfoEntities) {
            OrderType orderType = new OrderType();
            orderType.setOrderID(String.valueOf(orderInfoEntity.getBasicOrderInformation().getOrderID()));
            orderType.setOrderAmount(orderInfoEntity.getBasicOrderInformation().getAmount());
            ctripPayReq.getOrderList().getOrder().add(orderType);
        }
        return ctripPayReq;
    }

    private static void checkCanPay(List<OrderInfoEntity> orderInfoEntityList) {
        for (OrderInfoEntity orderInfoEntity : orderInfoEntityList) {
            String orderStatusDisplay = orderInfoEntity.getBasicOrderInformation().getOrderStatusDisplay();
            if ("Cancel".equals(orderStatusDisplay)) {
                throw new BizzException(TicketEx.NOT_ALLOW_PAYMENT, "携程订单状态为已取消["+orderStatusDisplay+"]，不可发起支付");
            }
            if (isOrderStatusPayed(orderStatusDisplay)) {
                throw new BizzException(TicketEx.NOT_ALLOW_PAYMENT, "携程订单状态为["+orderStatusDisplay+"], 不可发起支付");
            }
        }
    }

    private static boolean isOrderStatusPayed(String orderStatusDisplay) {
        boolean payedFlag = false;
        Set<String> payedOrderStatusSet = new HashSet<String>();
        payedOrderStatusSet.add("PrintedTicket");// 已出票
        payedOrderStatusSet.add("RefundApplying");// 退票申请中
        payedOrderStatusSet.add("FullRetire");// 已退票
        payedOrderStatusSet.add("PartRetire");// 部分退票
        payedOrderStatusSet.add("SendingTicket");// 已送票
        if (payedOrderStatusSet.contains(orderStatusDisplay)) {
            payedFlag = true;
        }
        return payedFlag;
    }

}
