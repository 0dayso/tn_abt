package com.tuniu.abt.service.change.ctrip;

import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.constants.ChangeEx;
import com.tuniu.abt.intf.dto.change.*;
import com.tuniu.abt.intf.entity.AbtChangeOrder;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.mapper.AbtChangeOrderMapper;
import com.tuniu.abt.service.ctrip.module.CtripPayModule;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.CtripPayReq;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.FlightAlipayResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.OrderList;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.OrderType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 携程改升支付服务
 * Created by chengyao on 2016/5/3.
 */
@Service
public class CtripChangeApplyService {

    @Resource
    private AbtChangeOrderMapper abtChangeOrderMapper;

    @Resource
    private CtripPayModule ctripPayModule;

    /**
     * 携程改升支付
     *
     * @param request
     * @return
     * @author lining
     */
    public RespChange applyChange(ReqChange request) {
        checkInput(request);
        long orderId = request.getOrderId();
        String ctripOrderId = request.getCtripOrderId();
        BigDecimal orderAmount = request.getTotalAmount();

        AbtChangeOrder record = new AbtChangeOrder();
        record.setOrderId(orderId);
        record.setCtripOrderId(ctripOrderId);
        int count = abtChangeOrderMapper.selectCount(record);
        if (count == 0) {
            throw new BizzException(ChangeEx.CTRIP_NOT_FOUND_PAY_LIST, "库中没有查到该订单的改升记录");
        }

        String changeOrderId = ctripOrderId + "g" + count;
        //组成携程支付入参
        CtripPayReq req = new CtripPayReq();
        req.setOrderID(orderId);
        req.setMainOrderID(ctripOrderId);
        req.setChangeOrderId(changeOrderId);
        req.setExternalNo(String.valueOf(orderId));
        OrderType order = new OrderType();
        order.setOrderID(ctripOrderId);
        order.setOrderAmount(orderAmount);
        OrderList orderList = new OrderList();
        orderList.getOrder().add(order);
        req.setOrderList(orderList);
        //调用携程支付接口
        try {
            FlightAlipayResponse res = ctripPayModule.flightAlipayChange(req);
            if (!res.isResult() && !res.getMessage().contains("扣款成功")) {
                throw new BizzException(ChangeEx.CTRIP_CHANGE_PAY_FAIL, "给携程支付失败:" + res.getMessage());
            }
            RespChange respChange = new RespChange();
            respChange.setCtripData(new RespChangeCtrip());
            respChange.getCtripData().setPayNo(changeOrderId);

            // 一些默认参数，统一返回信息
            respChange.setFailureType(1); // 默认给出失败的结果，票务好做人工单
            respChange.setItems(new ArrayList<>());
            List<ReqChangeItem> reqItems = request.getItems();
            if (CollectionUtils.isNotEmpty(reqItems)) {
                for (ReqChangeItem reqItem : reqItems) {
                    RespChangeItem respChangeItem = new RespChangeItem();
                    respChangeItem.setPnr(reqItem.getPnr());
                    respChangeItem.setTicketNo(reqItem.getTicketNo());
                    respChange.getItems().add(respChangeItem);
                }
            }
            return respChange;
        } catch (Exception e) {
            throw new BizzException(BizzEx.COMM_EX_WRAP, "携程改升支付接口异常," + e.getMessage(), e);
        }
    }

    private void checkInput(ReqChange request) {
        if (StringUtils.isEmpty(request.getCtripOrderId())) {
            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "携程改升支付：入参请求中ctripOrderId不能为空");
        }
        if (request.getTotalAmount() == null || request.getTotalAmount().intValue() == 0) {
            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "携程改升支付：未传入升舱支付金额或金额为0");
        }
    }

}
