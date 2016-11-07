package com.tuniu.abt.service.change.aop;

import com.tuniu.abt.dao.AbtChangeOrderDao;
import com.tuniu.abt.intf.constants.ChangeEx;
import com.tuniu.abt.intf.dto.aop.*;
import com.tuniu.abt.intf.dto.book.main.BookPassengerType;
import com.tuniu.abt.intf.dto.book.request.BookingPassenger;
import com.tuniu.abt.intf.dto.book.request.TravelSegment;
import com.tuniu.abt.intf.dto.change.ProcChangeData;
import com.tuniu.abt.intf.dto.change.ReqChangeItem;
import com.tuniu.abt.intf.dto.change.RespChange;
import com.tuniu.abt.intf.dto.change.RespChangeItem;
import com.tuniu.abt.intf.entity.AbtChangeItem;
import com.tuniu.abt.intf.entity.AbtChangeOrder;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.aop.AopTicketSyncService;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/4/13.
 */
@Service
public class AopChangeApplyService {

    @Resource
    private AopTicketSyncService aopTicketSyncService;
    @Resource
    private AbtChangeOrderDao abtChangeOrderDao;

    /**
     * 开放平台的改期处理，发送改期单至开放平台
     * @param procChangeData procChangeData
     * @return
     */
    public void applyChange(ProcChangeData procChangeData) {
        List<AbtChangeItem> changeItems = procChangeData.getAbtChangeItems();
        AbtTicketRequest abtTicketRequest = procChangeData.getAbtTicketRequest();

        AopChangeSyncRequest aopChangeSyncRequest = new AopChangeSyncRequest();
        aopChangeSyncRequest.setTicketOrderId(abtTicketRequest.getTicketOrderId().toString());
        aopChangeSyncRequest.setVendorId(procChangeData.getAbtTicketRequest().getAopVendorId());
        aopChangeSyncRequest.setSessionId(procChangeData.getAbtChangeOrder().getSessionId());
        aopChangeSyncRequest.setRemark(procChangeData.getAbtChangeOrder().getRemark());
        aopChangeSyncRequest.setTotalAmount(new BigDecimal(procChangeData.getAbtChangeOrder().getTotalAmount().toString()));
        aopChangeSyncRequest.setFailureTime(DateTimeUtils.formatDatetime(procChangeData.getAbtChangeOrder().getFailureTime()));
        aopChangeSyncRequest.setChangeType(Integer.parseInt(procChangeData.getAbtChangeOrder().getChangeType()));
        aopChangeSyncRequest.setAttachments(procChangeData.getReqChange().getAttachments());

        aopChangeSyncRequest.setItems(new ArrayList<AopChangeSyncItem>());

        int idx = 0;
        for (AbtChangeItem abtChangeItem : changeItems) {
            ReqChangeItem reqChangeItem = procChangeData.getReqChange().getItems().get(idx);
            
            AopChangeSyncItem item = new AopChangeSyncItem();
            item.setLeg(abtChangeItem.getLeg());
            item.setPersonId(abtChangeItem.getPersonId());
            item.setUpgradeFee(new BigDecimal(abtChangeItem.getUpgradefee().toString()));
            item.setChangeFee(new BigDecimal(abtChangeItem.getChangeFee().toString()));
            item.setCommissionFee(new BigDecimal(abtChangeItem.getCommissionfee().toString()));
            item.setNewPersonId(abtChangeItem.getNewPersonId());
            item.setPnrCode(abtChangeItem.getNewPnr());
            item.setPersonType(reqChangeItem.getNewPassengerInfo() == null ? null :
                    BookPassengerType.valueOf(reqChangeItem.getNewPassengerInfo().getPassengerTypeCode()).intValue());
            item.setRemark(abtChangeItem.getRemark());

            if (CollectionUtils.isNotEmpty(reqChangeItem.getNewSegments())) {
                TravelSegment travelSegment = reqChangeItem.getNewSegments().get(0);
                AopTicketSyncSegment aopTicketSyncSegment = new AopTicketSyncSegment();
                aopTicketSyncSegment.setFlightNo(travelSegment.getFlightNo());
                aopTicketSyncSegment.setAirplaneType(travelSegment.getCraftType());//机型

                aopTicketSyncSegment.setArrivalAirportTerminal(travelSegment.getArrival().getTerminal());//到达航站楼名称
                aopTicketSyncSegment.setArrivalCityIataCode(travelSegment.getArrival().getCityIataCode());//到达城市三字码
                aopTicketSyncSegment.setArrivalCityName(travelSegment.getArrival().getCityName());//到达城市名称
                aopTicketSyncSegment.setArrivalDate(travelSegment.getArrival().getDate());//到达日期
                aopTicketSyncSegment.setArrivalTime(travelSegment.getArrival().getTime());//到达时间
                aopTicketSyncSegment.setArriveAirportCode(travelSegment.getArrival().getAirportCode());//到达机场三字码
                aopTicketSyncSegment.setArriveAirportName(travelSegment.getArrival().getAirportName());//到达机场名称

                aopTicketSyncSegment.setDepartAirportCode(travelSegment.getDeparture().getAirportCode());//出发机场三字码
                aopTicketSyncSegment.setDepartAirportName(travelSegment.getDeparture().getAirportName());//出发机场名称
                aopTicketSyncSegment.setDepartureAirportTerminal(travelSegment.getDeparture().getTerminal());
                aopTicketSyncSegment.setDepartureCityIataCode(travelSegment.getDeparture().getCityIataCode());
                aopTicketSyncSegment.setDepartureCityName(travelSegment.getDeparture().getCityName());
                aopTicketSyncSegment.setDepartureDate(travelSegment.getDeparture().getDate());
                aopTicketSyncSegment.setDepartureTime(travelSegment.getDeparture().getTime());
                item.setRes(aopTicketSyncSegment);
            }

            aopChangeSyncRequest.getItems().add(item);
        }

        aopTicketSyncService.sendChange(aopChangeSyncRequest);
    }


    public RespChange workWithFeedback(AopFeedbackRequest aopFeedbackRequest) {

        AbtChangeOrder abtChangeOrder = abtChangeOrderDao.findBySessionId(aopFeedbackRequest.getSessionId());
        if (abtChangeOrder == null) {
            throw new BizzException(ChangeEx.CHANGE_SESSION_NOT_FOUND);
        }
        // 回调的通用参数设置
        DataSharedSupport.putOrderId(abtChangeOrder.getOrderId().toString());
        DataSharedSupport.putTransId(abtChangeOrder.getSessionId());

        // 更新aop数据
        abtChangeOrderDao.updateAopInfo(abtChangeOrder.getId(),
                aopFeedbackRequest.getTicketOrderId().toString(), aopFeedbackRequest.getChangeOrderId().toString());

        RespChange respChange = new RespChange();
        respChange.setItems(new ArrayList<RespChangeItem>());
        respChange.setOrderId(abtChangeOrder.getOrderId());
        respChange.setFlightItemId(abtChangeOrder.getFlightItemId());
        respChange.setAopTicketId(aopFeedbackRequest.getTicketOrderId());
        respChange.setAopChangeId(aopFeedbackRequest.getChangeOrderId());
        respChange.setFailureType(aopFeedbackRequest.getFailureType());
        respChange.setRefuseReason(aopFeedbackRequest.getRefuseReason());
        respChange.setRefuseRemark(aopFeedbackRequest.getRefuseRemark());
        respChange.setResultType(aopFeedbackRequest.getOpType());
        respChange.setItems(new ArrayList<RespChangeItem>());
        respChange.setSolutionId(aopFeedbackRequest.getVendorId().toString());
        respChange.setSolutionName(aopFeedbackRequest.getVendorName());

        List<AopFeedbackItem> feedbackItems = aopFeedbackRequest.getItems();
        for (AopFeedbackItem feedbackItem : feedbackItems) {
            RespChangeItem respChangeItem = new RespChangeItem();
            respChangeItem.setPnr(feedbackItem.getTicketNo());
            respChangeItem.setTicketNo(feedbackItem.getTicketNo());
            respChange.getItems().add(respChangeItem);
        }

        return respChange;
    }
}
