package com.tuniu.abt.service.refund;

import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.dao.AbtPnrPassengerDao;
import com.tuniu.abt.dao.AbtRefundDao;
import com.tuniu.abt.dao.AbtTicketMainDao;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.refund.ProcRefundData;
import com.tuniu.abt.intf.dto.refund.ReqRefund;
import com.tuniu.abt.intf.dto.refund.ReqRefundItem;
import com.tuniu.abt.intf.entity.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.mapper.AbtRefundItemMapper;
import com.tuniu.abt.mapper.AbtRefundOrderMapper;
import com.tuniu.abt.service.res.ResourceBaseCacheService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/4/18.
 */
@Service
public class RefundDbBaseService {

    @Resource
    private AbtRefundOrderMapper abtRefundOrderMapper;
    @Resource
    private AbtRefundItemMapper abtRefundItemMapper;
    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;
    @Resource
    private ResourceBaseCacheService resourceBaseCacheService;
    @Resource
    private AbtPnrMainDao abtPnrMainDao;
    @Resource
    private AbtTicketMainDao abtTicketMainDao;
    @Resource
    private AbtRefundDao abtRefundDao;

    public void updateRefundStatus(ProcRefundData procRefundData) {
        for (AbtTicketMain abtTicketMain : procRefundData.getRefundTicketMains()) {
            if (abtTicketMain.getStatus() == AbtTicketMain.STATUS_REFUNDED) {
                abtTicketMainDao.updateStatus(abtTicketMain.getId(), AbtTicketMain.STATUS_REFUNDED);
            }
        }

        boolean hasFail = false;
        for (AbtRefundItem abtRefundItem : procRefundData.getAbtRefundItems()) {
            if (abtRefundItem.getStatus() == AbtRefundItem.STATUS_OK) {
                abtRefundDao.updateRefundItemStatus(abtRefundItem.getId(), AbtRefundItem.STATUS_OK);
            } else if (abtRefundItem.getStatus() == AbtRefundItem.STATUS_FAIL) {
                abtRefundDao.updateRefundItemStatus(abtRefundItem.getId(), AbtRefundItem.STATUS_FAIL);
                hasFail = true;
            }
        }

        abtRefundDao.updateRefundOrderStatus(procRefundData.getAbtRefundOrder().getId(),
                hasFail ? AbtRefundOrder.STATUS_FAIL : AbtRefundOrder.STATUS_OK);
    }

    @Transactional
    public void recordRefundOrder(ProcRefundData procRefundData) {
        AbtTicketRequest abtTicketRequest = procRefundData.getAbtTicketRequest();
        ReqRefund reqRefund = procRefundData.getReqRefund();

        AbtRefundOrder abtRefundOrder = new AbtRefundOrder();
        abtRefundOrder.setOrderId(abtTicketRequest.getOrderId());
        abtRefundOrder.setFlightItemId(abtTicketRequest.getFlightItemId());
        abtRefundOrder.setSessionId(reqRefund.getTransId());
        abtRefundOrder.setRefundSource(reqRefund.getRefundSource());
        abtRefundOrder.setRefundType(reqRefund.getRefundType());
        abtRefundOrder.setRemark(reqRefund.getRemark());
        abtRefundOrder.setReceivedSegment(reqRefund.getReceivedSegment() ? 1 : 0);
        abtRefundOrder.setSegmentPosition(reqRefund.getSegmentPosition());
        abtRefundOrder.setStatus(AbtRefundOrder.STATUS_INIT);
        Long aopTicketOrderId = abtTicketRequest.getTicketOrderId();
        if (aopTicketOrderId != null && aopTicketOrderId > 0) {
            abtRefundOrder.setAopOrder(aopTicketOrderId.toString());
        }

        abtRefundOrderMapper.insertSelective(abtRefundOrder);
        procRefundData.setAbtRefundOrder(abtRefundOrder);

        procRefundData.setAbtRefundItems(new ArrayList<AbtRefundItem>());

        List<ReqRefundItem> refundItems = procRefundData.getReqRefund().getItems();
        for (ReqRefundItem refundItem : refundItems) {

            AbtTicketMain abtTicketMain = findTicketMain(procRefundData.getRefundTicketMains(), refundItem.getTicketNo());
            if (abtTicketMain == null) {
                throw new BizzException(BizzEx.COMM_EX_WRAP, "not find abt_ticket_main record, ticketNo=" + refundItem.getTicketNo());
            }
            AbtPnrPassenger passenger = findPassenger(procRefundData.getReqRefund().getOrderId(),
                    abtTicketMain.getPnr(), abtTicketMain.getPassengerName());

            AbtRefundItem abtRefundItem = new AbtRefundItem();
            abtRefundItem.setOrderId(abtRefundOrder.getOrderId());
            abtRefundItem.setRefundId(abtRefundOrder.getId());
            abtRefundItem.setRefundItemType(abtRefundItem.getRefundItemType());
            abtRefundItem.setPnr(abtTicketMain.getNewPnr());
            abtRefundItem.setStatus(AbtRefundItem.STATUS_INIT);
            abtRefundItem.setTicketMainId(abtTicketMain.getId());
            abtRefundItem.setTicketNo(refundItem.getTicketNo());
            abtRefundItem.setPersonId(passenger.getPersonId());
            abtRefundItem.setPnrPassengerId(passenger.getId());
            abtRefundItem.setRefundAmount(refundItem.getRefundAmount().floatValue());
            abtRefundItem.setCommissionFee(refundItem.getCommissionFee().floatValue());

            String orgCity = resourceBaseCacheService.getAirport(abtTicketMain.getOrgAirportCode()).getIataCityCode();
            String dstCity = resourceBaseCacheService.getAirport(abtTicketMain.getDstAirportCode()).getIataCityCode();
            abtRefundItem.setLeg(orgCity + "/" + dstCity);

            abtRefundItem.setRemark(refundItem.getRemark());

            abtRefundItemMapper.insertSelective(abtRefundItem);

            // 设置执行状态为失败
            abtRefundItem.setStatus(AbtRefundItem.STATUS_FAIL);
            procRefundData.getAbtRefundItems().add(abtRefundItem);
        }

        procRefundData.getAbtRefundOrder().setStatus(AbtRefundOrder.STATUS_FAIL);
    }

    @Transactional
    public void updateRealAmount(List<AbtRefundItem> abtRefundItems) {
        for (AbtRefundItem abtRefundItem : abtRefundItems) {
            abtRefundDao.updateRefundItemRealAmount(abtRefundItem.getId(), abtRefundItem.getRealRefundAmount());
        }
    }

    private AbtPnrPassenger findPassenger(Long orderId, String pnr, String passengerName) {
        AbtPnrMain abtPnrMain = abtPnrMainDao.findByOrderAndPnr(orderId, pnr);
        if (abtPnrMain == null) {
            throw new BizzException(BizzEx.COMM_EX_WRAP, "not find pnr_main data.");
        }
        AbtPnrPassenger passenger = abtPnrPassengerDao.findByMainPnrIdAndName(abtPnrMain.getId(), passengerName);
        if (passenger == null) {
            throw new BizzException(BizzEx.COMM_EX_WRAP, "not find pnr_passenger data.");
        }
        return passenger;
    }

    private AbtTicketMain findTicketMain(List<AbtTicketMain> ticketMainList, String ticketNo) {
        for (AbtTicketMain abtTicketMain : ticketMainList) {
            if (ticketNo.equals(abtTicketMain.getTicketNo())) {
                return abtTicketMain;
            }
        }
        return null;
    }


}
