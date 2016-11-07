package com.tuniu.abt.service.cancel.impl;

import com.google.common.collect.Maps;
import com.tuniu.abt.intf.constants.CancelEx;
import com.tuniu.abt.intf.dto.book.response.PassengerInfo;
import com.tuniu.abt.intf.dto.cancel.CancelOrderGroup;
import com.tuniu.abt.intf.dto.cancel.CancelResult;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.cancel.CancelWorkUtils;
import com.tuniu.abt.service.ctrip.module.CtripCommonModule;
import com.tuniu.abt.service.ctrip.module.CtripOrderModule;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.cancelOrder.FltOpenCancelOrderResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderBasicInfoEntity;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderInfoEntity;
import com.tuniu.adapter.flightTicket.vendor.ctrip.vo.FltOpenCancelOrderRequestVo;
import com.tuniu.adapter.flightTicket.vendor.ctrip.vo.OpenCancelOrderRequestItemVo;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 携程取消操作逻辑
 *
 * Created by chengyao on 2016/3/1.
 */
@Service
public class CtripCancelProcessor extends AbstractCancelProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripCancelProcessor.class);

    @Resource
    private CtripCommonModule ctripCommonModule;
    @Resource
    private CtripOrderModule ctripOrderModule;
    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;

    /**
     * 取消多个主订单
     * @param cancelOrders 主订单信息
     * @return 结果列表
     */
    @Override
    public List<CancelResult> cancelPnrs(List<CancelOrderGroup> cancelOrders) {
        List<CancelResult> result = new ArrayList<CancelResult>();
        //循环处理每一个mainOrderId
        for (CancelOrderGroup cancelOrder : cancelOrders) {
            CancelResult ctripCancelResult = new CancelResult(cancelOrder, null);
            try {
                cancelSingleMainOrder(cancelOrder);
            } catch (Exception ex) {
                LOGGER.error("携程取消下单失败，mainOrderId=" + cancelOrder.getMainOrderId(), ex);
                ctripCancelResult.setThrowable(ex);
                ctripCancelResult.setErrorCode(exceptionMessageUtils.findCodeString(ex));
                ctripCancelResult.setSuccess(false);
            }
            dealRemark(ctripCancelResult);
            result.add(ctripCancelResult);
        }
        splitCancelResultBySubOrderId(result);
        return result;
    }

    /**
     * 按携程的子订单数返回取消结果数据
     * @param results
     */
    private void splitCancelResultBySubOrderId(List<CancelResult> results) {
        List<CancelResult> subResults = new ArrayList<>();
        for (CancelResult cancelResult : results) {
            for (int i = 0; i < cancelResult.getCancelOrderGroup().getOrderIds().size(); i++) {
                String orderId = cancelResult.getCancelOrderGroup().getOrderIds().get(i);
                if (i == 0) {
                    cancelResult.setCancelPnr(orderId);
                } else {
                    CancelResult subResult = CommonUtils.deepCloneObject(cancelResult);
                    subResult.setCancelPnr(orderId);
                    subResults.add(subResult);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(subResults)) {
            results.addAll(subResults);
        }
    }

    /**
     * 取消一组携程订单
     * @param cancelOrderGroup 订单信息
     * @return 操作结果报文
     */
    private FltOpenCancelOrderResponse cancelSingleMainOrder(CancelOrderGroup cancelOrderGroup) {
        List<String> groupOrderList = cancelOrderGroup.getOrderIds();
        List<OrderInfoEntity> orderInfoEntityList = ctripCommonModule.queryCtripOrderDetail(groupOrderList);

        StringBuilder cannotCancelOrderStateInfo = new StringBuilder();
        //遍历每个子订单的状态
        for (OrderInfoEntity orderInfo : orderInfoEntityList) {
            OrderBasicInfoEntity basicOrderInformation = orderInfo.getBasicOrderInformation();
            //如果携程订单处于支付状态/出票完成状态，那么不能调用携程取消订单接口
            String orderStatus = basicOrderInformation.getOrderStatus();
            boolean havePay = basicOrderInformation.isHavePay();
            if (havePay || "S".equals(orderStatus.toUpperCase())) {
                cannotCancelOrderStateInfo.append("【携程订单状态】订单号：").append(basicOrderInformation.getOrderID());
                cannotCancelOrderStateInfo.append(",主订单号：").append(orderInfo.getRelationForExtNoInfo().getRelationForExtNoEntity().get(0).getOrderID());
                cannotCancelOrderStateInfo.append(",是否支付：").append(havePay);
                cannotCancelOrderStateInfo.append(",订单状态：").append(orderStatus);
            }
        }
        //只有订单状态处于非支付成功/出票完成状态再调用携程取消订单接口 jira[AIR-713]
        if (cannotCancelOrderStateInfo.length() > 0) {
            throw new BizzException(CancelEx.CANCEL_CTRIP_FAIL, "携程取消订单，订单存在非支付成功/出票完成状态的订单。订单号="
                    + cancelOrderGroup.getMainOrderId() + ", info=" + cannotCancelOrderStateInfo.toString());
        }
        ///////////////////调用携程取消订单接口\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        FltOpenCancelOrderRequestVo requestVo = new FltOpenCancelOrderRequestVo();
        List<OpenCancelOrderRequestItemVo> items = new ArrayList<OpenCancelOrderRequestItemVo>();
        requestVo.setOpenCancelOrderRequestItems(items);
        OpenCancelOrderRequestItemVo item = new OpenCancelOrderRequestItemVo();
        item.setOrderId(String.valueOf(cancelOrderGroup.getMainOrderId()));
        item.setRelationCancelOrders(cancelOrderGroup.getRelationOrderIds());
        items.add(item);
        //取消一个主订单(有子订单的话，带上子订单)
        return ctripOrderModule.ctripCancelOrder(requestVo);
    }



    // 校验携程取消下单的pnr是否符合按分组一次性取消
    @Override
    public List<CancelOrderGroup> checkCancelRequest(List<AbtPnrMain> availablePnrMains, List<String> pnrs,
            Map<String, List<PassengerInfo>> ignores) {
        // 数据库中的主子订单map。
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        // 要取消的主子订单关系
        Map<String, CancelOrderGroup> result = Maps.newHashMap();
        for (AbtPnrMain availablePnrMain : availablePnrMains) {
            String orderId = availablePnrMain.getOutOrderId();
            String mainOrderId = availablePnrMain.getOutMainOrderId();
            // 数据转化
            if (!map.containsKey(mainOrderId)) {
                map.put(mainOrderId, new ArrayList<String>());
            }
            map.get(mainOrderId).add(orderId);

            // result数据处理。
            if (!pnrs.contains(orderId)) { //过滤不需要取消的订单
                continue;
            }

            if (!result.containsKey(mainOrderId)) { // 判断主订单key是否存在，不存在，新建一个主订单项目
                CancelOrderGroup cancelOrderGroup = new CancelOrderGroup();
                cancelOrderGroup.setMainOrderId(mainOrderId);
                result.put(mainOrderId, cancelOrderGroup);
                result.get(mainOrderId).getOrderIds().add(mainOrderId);
            }

            if (!orderId.equals(mainOrderId)) { // 子订单项目，放入关联list
                CancelOrderGroup cancelOrder = result.get(mainOrderId);
                cancelOrder.getRelationOrderIds().add(orderId);
                cancelOrder.getOrderIds().add(orderId);
            }
        }
        for (List<String> orderIds : map.values()) {
            if (!Collections.disjoint(orderIds, pnrs)) {
                if (!pnrs.containsAll(orderIds)) {
                    throw new BizzException(CancelEx.CANCEL_FAIL, "有关联关系的单据需一次取消。"
                            + CancelWorkUtils.buildPnrMessage(availablePnrMains, pnrs));
                }
            }
        }

        return new ArrayList<CancelOrderGroup>(result.values());
    }


}
