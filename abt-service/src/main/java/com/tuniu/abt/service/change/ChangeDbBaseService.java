package com.tuniu.abt.service.change;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.dao.AbtPnrPassengerDao;
import com.tuniu.abt.dao.AbtRefundDao;
import com.tuniu.abt.dao.AbtTicketMainDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.change.ProcChangeData;
import com.tuniu.abt.intf.dto.change.ReqChange;
import com.tuniu.abt.intf.dto.change.ReqChangeItem;
import com.tuniu.abt.intf.entity.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.mapper.AbtChangeItemMapper;
import com.tuniu.abt.mapper.AbtChangeOrderMapper;
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
public class ChangeDbBaseService {

    @Resource
    private AbtChangeOrderMapper abtChangeOrderMapper;
    @Resource
    private AbtChangeItemMapper abtChangeItemMapper;
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

    public void updateChangeStatus(ProcChangeData procChangeData, boolean success) {
        for (AbtTicketMain abtTicketMain : procChangeData.getAbtTicketMains()) {
            if (success) {
                abtTicketMainDao.updateStatus(abtTicketMain.getId(), AbtTicketMain.STATUS_CHANGED);
            }
        }
        abtRefundDao.updateRefundOrderStatus(procChangeData.getAbtChangeOrder().getId(),
                success ? AbtChangeOrder.STATUS_OK : AbtChangeOrder.STATUS_FAIL);
    }

    @Transactional
    public void recordChangeOrder(ProcChangeData procChangeData) {
        AbtTicketRequest abtTicketRequest = procChangeData.getAbtTicketRequest();
        ReqChange reqChange = procChangeData.getReqChange();

        AbtChangeOrder abtChangeOrder = new AbtChangeOrder();
        abtChangeOrder.setOrderId(abtTicketRequest.getOrderId());
        abtChangeOrder.setFlightItemId(abtTicketRequest.getFlightItemId());
        abtChangeOrder.setSessionId(reqChange.getTransId());
        abtChangeOrder.setChangeType(reqChange.getChangeType());
        abtChangeOrder.setCtripOrderId(reqChange.getCtripOrderId());
        abtChangeOrder.setFailureTime(reqChange.getFailureTime());
        abtChangeOrder.setTotalAmount(reqChange.getTotalAmount() == null ? 0 : reqChange.getTotalAmount().floatValue());
        abtChangeOrder.setRemark(reqChange.getRemark());
        abtChangeOrder.setStatus(AbtChangeOrder.STATUS_INIT);
        Long aopTicketOrderId = abtTicketRequest.getTicketOrderId();
        if (aopTicketOrderId != null && aopTicketOrderId > 0) {
            abtChangeOrder.setAopOrder(aopTicketOrderId.toString());
        }
        abtChangeOrderMapper.insertSelective(abtChangeOrder);
        procChangeData.setAbtChangeOrder(abtChangeOrder);

        if (procChangeData.getAbtTicketRequest().getVendorId() == BizzConstants.V_CTRIP) {
            return;
        }

        procChangeData.setAbtChangeItems(new ArrayList<AbtChangeItem>());

        List<ReqChangeItem> changeItems = procChangeData.getReqChange().getItems();
        for (ReqChangeItem changeItem : changeItems) {

            AbtTicketMain abtTicketMain = findTicketMain(procChangeData.getAbtTicketMains(), changeItem.getTicketNo());
            if (abtTicketMain == null) {
                throw new BizzException(BizzEx.COMM_EX_WRAP, "not find abt_ticket_main record, ticketNo=" + changeItem.getTicketNo());
            }
            AbtPnrPassenger passenger = findPassenger(procChangeData.getReqChange().getOrderId(),
                    abtTicketMain.getPnr(), abtTicketMain.getPassengerName());

            AbtChangeItem abtChangeItem = new AbtChangeItem();

            abtChangeItem.setOrderId(abtChangeOrder.getOrderId());
            abtChangeItem.setChangeId(abtChangeOrder.getId());
            abtChangeItem.setChangeFee(changeItem.getChangeFee().floatValue());
            abtChangeItem.setCommissionfee(changeItem.getCommissionFee().floatValue());
            abtChangeItem.setUpgradefee(changeItem.getUpgradeFee().floatValue());
            abtChangeItem.setPnr(abtTicketMain.getNewPnr());
            abtChangeItem.setNewPnr(changeItem.getPnr());
            abtChangeItem.setTicketNo(changeItem.getTicketNo());
            abtChangeItem.setPersonId(passenger.getPersonId());
            abtChangeItem.setPnrPassengerId(passenger.getId());
            if (changeItem.getNewPassengerInfo() != null) {
                abtChangeItem.setNewPersonId(changeItem.getNewPassengerInfo().getPersonId());
            }
            if (changeItem.getNewSegments() != null) {
                abtChangeItem.setNewSegments(JSON.toJSONString(changeItem.getNewSegments()));
            }

            abtChangeItem.setTicketMainId(abtTicketMain.getId());

            String orgCity = resourceBaseCacheService.getAirport(abtTicketMain.getOrgAirportCode()).getIataCityCode();
            String dstCity = resourceBaseCacheService.getAirport(abtTicketMain.getDstAirportCode()).getIataCityCode();
            abtChangeItem.setLeg(orgCity + "/" + dstCity);
            abtChangeItem.setRemark(changeItem.getRemark());

            abtChangeItemMapper.insertSelective(abtChangeItem);

            procChangeData.getAbtChangeItems().add(abtChangeItem);
        }

        procChangeData.getAbtChangeOrder().setStatus(AbtRefundOrder.STATUS_FAIL);
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
            throw new BizzException(BizzEx.COMM_EX_WRAP, "(change) not find pnr_main data.");
        }
        AbtPnrPassenger passenger = abtPnrPassengerDao.findByMainPnrIdAndName(abtPnrMain.getId(), passengerName);
        if (passenger == null) {
            throw new BizzException(BizzEx.COMM_EX_WRAP, "(change) not find pnr_passenger data.");
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
