package com.tuniu.abt.service.refund;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.refund.ProcRefundData;
import com.tuniu.abt.intf.dto.refund.RespRefund;
import com.tuniu.abt.intf.entity.AbtRefundItem;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.refund.aop.AopRefundApplyService;
import com.tuniu.abt.service.refund.ctrip.CtripRefundApplyService;
import com.tuniu.abt.service.refund.ts.TravelSkyRefundService;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.service.travelsky.dto.RefundPriceResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chengyao on 2016/3/25.
 */
@Service
public class RefundService {

    @Resource
    private CtripRefundApplyService ctripRefundApplyService;
    @Resource
    private TravelSkyRefundService travelSkyRefundService;
    @Resource
    private AopRefundApplyService aopRefundApplyService;
    @Resource
    private RefundDbBaseService refundDbBaseService;
    @Resource
    private TravelSkyInterface travelSkyInterface;


    public RespRefund applyRefund(ProcRefundData refundData) {
        int vendorId = refundData.getAbtTicketRequest().getVendorId();

        refundDbBaseService.recordRefundOrder(refundData);

        try {
            RespRefund respRefund = null;

            // 开放平台、航信 通过接口查询退票费
            if (vendorId == BizzConstants.V_TS) {
                updateRealRefundAmount(refundData.getAbtRefundItems());
            }

            if (vendorId == BizzConstants.V_CTRIP) {
                respRefund = ctripRefundApplyService.refund(refundData);
            } else if (vendorId == BizzConstants.V_TS) {
                respRefund = travelSkyRefundService.refund(refundData);
            } else if (vendorId == BizzConstants.V_AOP) {
                aopRefundApplyService.refund(refundData);
            } else {
                throw new BizzException(BizzEx.COMM_EX_WRAP, "渠道 " + vendorId + " 不支持退票.");
            }

            if (respRefund != null) {
                respRefund.setOrderId(refundData.getReqRefund().getOrderId());
                respRefund.setFlightItemId(refundData.getReqRefund().getFlightItemId());
                return respRefund;
            }

        } finally {
            if (vendorId != BizzConstants.V_AOP)
                refundDbBaseService.updateRefundStatus(refundData);
        }

        return null;
    }




    /**
     * 从接口获取退票费，并记入数据库
     * @param refundItemList 退票item列表
     */
    private void updateRealRefundAmount(List<AbtRefundItem> refundItemList) {
        for (AbtRefundItem abtRefundItem : refundItemList) {
            String ticketNo = abtRefundItem.getTicketNo();
            RefundPriceResponse refundPriceResponse = travelSkyInterface.queryRefundPrice(ticketNo);
            abtRefundItem.setRealRefundAmount(refundPriceResponse.getDeduction().floatValue());
        }
        refundDbBaseService.updateRealAmount(refundItemList);
    }

}
