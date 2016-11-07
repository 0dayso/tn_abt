package com.tuniu.abt.service.issue.aop;

import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.dao.AbtTicketMainDao;
import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.aop.AopFeedbackItem;
import com.tuniu.abt.intf.dto.aop.AopFeedbackRequest;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.*;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.issue.IssueService;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huangsizhou on 16/4/11.
 */
@Service
public class AopIssueService implements IssueService {

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Resource
    private AbtTicketMainDao abtTicketMainDao;

    @Resource
    private AopIssueProcessor aopIssueProcessor;

    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    public IssueResult issueTicket(IssueRequest issueRequest) {
        return aopIssueProcessor.issueTicket(issueRequest);
    }

    public IssueResultDetail handleIssueResult(AopFeedbackRequest aopFeedbackRequest) {

        // 回调的通用参数设置
        DataSharedSupport.putTransId(aopFeedbackRequest.getSessionId());
        AbtTicketRequest abtTicketRequest = abtTicketRequestDao.findBySessionId(aopFeedbackRequest.getSessionId());
        if (null == abtTicketRequest) {
            throw new BizzException(TicketEx.AOP_TICKET_REQUEST_NOT_FOUND, "根据sessionId查询出票请求为空");
        }
        DataSharedSupport.putOrderId(abtTicketRequest.getOrderId().toString());
        DataSharedSupport.putCallback(abtTicketRequest.getCallback());
        // 出票拒绝
        if (aopFeedbackRequest.getOpType() == AopFeedbackRequest.OP_TYPE_TICKET_FAIL) {
            abtTicketRequestDao.updateStatusById(AbtTicketRequest.ISSUE_FAIL, abtTicketRequest.getId());
            throw new BizzException(TicketEx.AOP_TICKET_FAIL, "开放平台出票拒绝,备注:"+aopFeedbackRequest.getRefuseRemark());
        }

        List<PnrInfo> pnrInfos = buildPnrInfos(abtTicketRequest, aopFeedbackRequest);

        IssueResultDetail issueResultDetail = new IssueResultDetail(abtTicketRequest.getFlightItemId(),
                null, aopFeedbackRequest.getTicketOrderId().toString(), pnrInfos);
        abtTicketRequestDao.updateStatusAndTicketOrderIdById(AbtTicketRequest.ISSUE_SUCCESS,
                aopFeedbackRequest.getTicketOrderId(), aopFeedbackRequest.getVendorId().toString(), abtTicketRequest.getId());
        return issueResultDetail;

    }

    private List<PnrInfo> buildPnrInfos(AbtTicketRequest abtTicketRequest, AopFeedbackRequest aopFeedbackRequest) {

        List<AbtTicketMain> abtTikcetMains = abtPnrMainDao.buildAbtTicketMainByBookData(abtTicketRequest.getOrderId(), abtTicketRequest.getFlightItemId());
        if (CollectionUtils.isEmpty(abtTikcetMains)) {
            throw new BizzException(TicketEx.AOP_TICKET_BOOK_DATA_NOT_FOUND, "从库中获取占位数据为空");
        }
        // 按pnr进行分组
        MultiValueMap<String, AbtTicketMain> groupedMainPnrs = new LinkedMultiValueMap<>();
        for (AbtTicketMain abtTikcetMain : abtTikcetMains) {
            abtTikcetMain.setRequestId(abtTicketRequest.getId());
            groupedMainPnrs.add(abtTikcetMain.getPnr(), abtTikcetMain);
        }

        // 组装pnrInfo 添加票号信息
        String solutionId = aopFeedbackRequest.getVendorId().toString();
        String solutionName = aopFeedbackRequest.getVendorName();
        List<AopFeedbackItem> items = aopFeedbackRequest.getItems();
        List<PnrInfo> pnrInfos = new ArrayList<>();
        for (Map.Entry<String, List<AbtTicketMain>> entry : groupedMainPnrs.entrySet()) {
            PnrInfo pnrInfo = new PnrInfo();
            pnrInfos.add(pnrInfo);
            pnrInfo.setIssueFlag(true);
            pnrInfo.setOrderId(entry.getKey());
            pnrInfo.setVendorId(BizzConstants.V_AOP);
            pnrInfo.setSolutionId(solutionId);
            pnrInfo.setSolutionName(solutionName);
            for (AbtTicketMain abtTicketMain : entry.getValue()) {
                fillTicketInfo(pnrInfo, items, abtTicketMain);
                fillPriceInfo(pnrInfo.getPriceDetail(), abtTicketMain);
            }
        }
        // 票号入库
        abtTicketMainDao.batchInsert(abtTikcetMains);

        return pnrInfos;
    }

    private void fillTicketInfo(PnrInfo pnrInfo, List<AopFeedbackItem> items, AbtTicketMain abtTicketMain) {
        TicketInfo ticketInfo = new TicketInfo();
        pnrInfo.getTickets().add(ticketInfo);
        ticketInfo.getFlights().add(new FlightInfo(abtTicketMain.getFlightNo(), abtTicketMain.getSeatCode()));
        ticketInfo.getPassengers().add(new PassengerInfo(abtTicketMain.getPassengerName()));
        for (AopFeedbackItem item : items) {
            if (abtTicketMain.getPersonId().equals(item.getPersonId())) {
                ticketInfo.setTicketNo(item.getTicketNo());
                abtTicketMain.setNewPnr(item.getPnrCode());
                abtTicketMain.setTicketNo(item.getTicketNo());
                if (!pnrInfo.getOrderId().equals(item.getPnrCode())) {
                    pnrInfo.setNewOrderId(item.getPnrCode());
                }
                break;
            }
        }
        if (StringUtils.isBlank(ticketInfo.getTicketNo())) {
            pnrInfo.setIssueFlag(false);
            String msg = "pnr:" + pnrInfo.getOrderId() + ",乘客:" + abtTicketMain.getPassengerName() + "aop反馈票号为空;";
            pnrInfo.setMsg(pnrInfo.getMsg() == null ? msg : pnrInfo.getMsg() + msg);
            pnrInfo.setErrorCode(TicketEx.OBTAIN_TICKET_ERROR);
        }
    }

    private void fillPriceInfo(PriceDetail priceDetail, AbtTicketMain abtTicketMain) {
        PriceInfo priceInfo = new PriceInfo();
        priceInfo.setOrgPrice(BigDecimal.valueOf(abtTicketMain.getOrgPrice()));
        priceInfo.setSalePrice(BigDecimal.valueOf(abtTicketMain.getSalePrice()));
        priceInfo.setFloorPrice(BigDecimal.valueOf(abtTicketMain.getFloorPrice()));
        priceInfo.setAirportTax(BigDecimal.valueOf(abtTicketMain.getAirportTax()));
        priceInfo.setFuelSurcharge(BigDecimal.valueOf(abtTicketMain.getFuelSurcharge()));
        if (BizzConstants.PERSON_TYPE_ADULT == abtTicketMain.getPassengerType()) {
            int adultNum = priceDetail.getAdultNum();
            priceDetail.setAdultNum(++adultNum);
            priceDetail.setAdultPrice(priceInfo);
        } else if (BizzConstants.PERSON_TYPE_CHILD == abtTicketMain.getPassengerType()) {
            int childNum = priceDetail.getChildNum();
            priceDetail.setChildNum(++childNum);
            priceDetail.setChildPrice(priceInfo);
        } else if (BizzConstants.PERSON_TYPE_BABY == abtTicketMain.getPassengerType()) {
            int babyNum = priceDetail.getBabyNum();
            priceDetail.setBabyNum(++babyNum);
            priceDetail.setBabyPrice(priceInfo);
        }
    }


}
