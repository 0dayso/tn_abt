package com.tuniu.abt.service.cancel.impl;

import com.google.common.collect.Maps;
import com.tuniu.abt.dbservice.AbtPnrMainService;
import com.tuniu.abt.intf.dto.book.response.PassengerInfo;
import com.tuniu.abt.intf.dto.cancel.CancelOrderGroup;
import com.tuniu.abt.intf.dto.cancel.CancelResult;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.service.airline.AirlineFacadeService;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 航司直连取消占位
 * Created by chengyao on 2016/3/21.
 */
@Service
public class AirlineCancelProcessor extends AbstractCancelProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineCancelProcessor.class);

    @Resource
    private AirlineFacadeService airlineFacadeService;
    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;
    @Resource
    private AbtPnrMainService abtPnrMainService;

    @Override
    public List<CancelResult> cancelPnrs(List<CancelOrderGroup> groups) {
        List<CancelResult> cancelResults = new ArrayList<CancelResult>();
        for (CancelOrderGroup group : groups) {
            List<String> pnrs = group.getOrderIds();
            for (String pnr : pnrs) {
                CancelResult cancelResult = new CancelResult();
                cancelResult.setCancelPnr(pnr);
                try {
                    airlineFacadeService.cancelOrder(group.getTicketingCarrier(), pnr);
                } catch (Exception ex) {
                    LOGGER.error("航司直连取消订单失败，outOrderId=" + pnr, ex);
                    cancelResult.setThrowable(ex);
                    cancelResult.setErrorCode(exceptionMessageUtils.findCodeString(ex));
                }
                dealRemark(cancelResult);
                cancelResults.add(cancelResult);
            }
        }
        return cancelResults;
    }

    @Override
    public List<CancelOrderGroup> checkCancelRequest(List<AbtPnrMain> availablePnrMains, List<String> pnrs,
            Map<String, List<PassengerInfo>> ignores) {
        // 航信每个group标识为一个pnr
        Map<String, CancelOrderGroup> result = Maps.newHashMap();
        for (AbtPnrMain availablePnrMain : availablePnrMains) {
            String orderId = availablePnrMain.getOutOrderId();
            // result数据处理。
            if (!pnrs.contains(orderId)) { //过滤不需要取消的订单
                continue;
            }
            if (!result.containsKey(orderId)) { // 按pnr保存cancelORderGroup
                CancelOrderGroup cancelOrderGroup = new CancelOrderGroup();
                cancelOrderGroup.setMainOrderId(orderId);
                cancelOrderGroup.setTicketingCarrier(availablePnrMain.getTicketingCarrier());
                result.put(orderId, cancelOrderGroup);
            }
        }
        return new ArrayList<CancelOrderGroup>(result.values());
    }
}
