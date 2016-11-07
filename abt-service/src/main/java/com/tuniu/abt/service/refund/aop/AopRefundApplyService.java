package com.tuniu.abt.service.refund.aop;

import com.tuniu.abt.dao.AbtRefundItemDao;
import com.tuniu.abt.dao.AbtRefundOrderDao;
import com.tuniu.abt.intf.constants.RefundEx;
import com.tuniu.abt.intf.dto.aop.AopFeedbackRequest;
import com.tuniu.abt.intf.dto.aop.AopRefundSyncItem;
import com.tuniu.abt.intf.dto.aop.AopRefundSyncRequest;
import com.tuniu.abt.intf.dto.refund.ProcRefundData;
import com.tuniu.abt.intf.dto.refund.RespRefund;
import com.tuniu.abt.intf.dto.refund.RespRefundItem;
import com.tuniu.abt.intf.entity.AbtRefundItem;
import com.tuniu.abt.intf.entity.AbtRefundOrder;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.aop.AopTicketSyncService;
import com.tuniu.abt.service.refund.RefundCommonUtils;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/4/13.
 */
@Service
public class AopRefundApplyService {

    @Resource
    private AopTicketSyncService aopTicketSyncService;
    @Resource
    private AbtRefundOrderDao abtRefundOrderDao;
    @Resource
    private SystemConfig systemConfig;
    @Resource
    private AbtRefundItemDao atAbtRefundItemDao;


    /**
     * 开放平台的退票处理，发送退票单至开放平台
     * @param procRefundData procRefundData
     * @return
     */
    public void refund(ProcRefundData procRefundData) {
        List<AbtRefundItem> refundItems = procRefundData.getAbtRefundItems();
        AbtTicketRequest abtTicketRequest = procRefundData.getAbtTicketRequest();
//        boolean auto = AbtRefundOrder.REFUND_SOURCE_A.equals(procRefundData.getAbtRefundOrder().getRefundSource());
//
//        // 检验阀值
//        if (auto) {
//            RefundCommonUtils.checkDifferenceThreshold(refundItems, systemConfig.getRefundDifferenceThreshold());
//        }


        AopRefundSyncRequest aopRefundSyncRequest = new AopRefundSyncRequest();
        aopRefundSyncRequest.setTicketOrderId(abtTicketRequest.getTicketOrderId().toString());
        aopRefundSyncRequest.setRefundType(Integer.parseInt(procRefundData.getAbtRefundOrder().getRefundType()));
        aopRefundSyncRequest.setVendorId(procRefundData.getAbtTicketRequest().getAopVendorId());
        aopRefundSyncRequest.setSessionId(procRefundData.getAbtRefundOrder().getSessionId());
        aopRefundSyncRequest.setRemark(procRefundData.getAbtRefundOrder().getRemark());
        aopRefundSyncRequest.setItems(new ArrayList<>());
        aopRefundSyncRequest.setTotalAmount(RefundCommonUtils.countTotal(refundItems, false));
        aopRefundSyncRequest.setAttachments(procRefundData.getReqRefund().getAttachments());

        for (AbtRefundItem abtRefundItem : refundItems) {

            String subAmount = abtRefundItem.getRefundAmount().toString();

            AopRefundSyncItem item = new AopRefundSyncItem();
            item.setLeg(abtRefundItem.getLeg());
            item.setPersonId(abtRefundItem.getPersonId());
            item.setCommissionFee(new BigDecimal(abtRefundItem.getCommissionFee().toString()));
            item.setSubAmount(new BigDecimal(subAmount));
            item.setRefundItemType(abtRefundItem.getRefundItemType());
            item.setRemark(abtRefundItem.getRemark());

            aopRefundSyncRequest.getItems().add(item);
        }

        aopTicketSyncService.sendRefund(aopRefundSyncRequest);
    }


    public RespRefund workWithFeedback(AopFeedbackRequest aopFeedbackRequest) {

        AbtRefundOrder abtRefundOrder = abtRefundOrderDao.findBySessionId(aopFeedbackRequest.getSessionId());
        if (abtRefundOrder == null) {
            throw new BizzException(RefundEx.REFUND_SESSION_NOT_FOUND);
        }
        // 回调的通用参数设置
        DataSharedSupport.putOrderId(abtRefundOrder.getOrderId().toString());
        DataSharedSupport.putTransId(abtRefundOrder.getSessionId());

        // 更新aop数据
        abtRefundOrderDao.updateAopInfo(abtRefundOrder.getId(),
                aopFeedbackRequest.getTicketOrderId().toString(), aopFeedbackRequest.getRefundOrderId().toString());

        RespRefund respRefund = new RespRefund();
        respRefund.setItems(new ArrayList<>());
        respRefund.setOrderId(abtRefundOrder.getOrderId());
        respRefund.setFlightItemId(abtRefundOrder.getFlightItemId());
        respRefund.setAopTicketId(aopFeedbackRequest.getTicketOrderId());
        respRefund.setAopRefundId(aopFeedbackRequest.getRefundOrderId());
        respRefund.setSolutionId(aopFeedbackRequest.getVendorId().toString());
        respRefund.setSolutionName(aopFeedbackRequest.getVendorName());

        List<AbtRefundItem> items = atAbtRefundItemDao.findItems(abtRefundOrder.getId());
        for (AbtRefundItem item : items) {
            RespRefundItem respRefundItem = new RespRefundItem();
            respRefundItem.setRefundAmount(new BigDecimal(item.getRealRefundAmount().toString()));
            respRefundItem.setTicketNo(item.getTicketNo());
            respRefund.getItems().add(respRefundItem);
        }


        return respRefund;
    }
}
