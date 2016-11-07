package com.tuniu.abt.converter;

import com.tuniu.abt.dao.*;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.aop.AopFeedbackItem;
import com.tuniu.abt.intf.dto.aop.AopFeedbackRequest;
import com.tuniu.abt.intf.dto.issue.response.*;
import com.tuniu.abt.intf.entity.*;
import com.tuniu.mauritius.price.constants.CurrencyType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangsizhou on 16/4/11.
 */
@Service
public class AopFeedbackRequestToIssueResponseConverter implements Converter<AopFeedbackRequest, IssueResponse> {

    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;

    @Resource
    private AbtPnrFlightDao abtPnrFlightDao;

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    @Resource
    private AbtAopPolicyDao abtAopPolicyDao;

    @Override
    public IssueResponse convert(AopFeedbackRequest source) {

        IssueResponse issueResponse = new IssueResponse();
        issueResponse.setSessionId(String.valueOf(source.getSessionId()));
        if (source.getOpType() == BizzConstants.AOP_ISSUE_FAIL) {
            issueResponse.setSuccess(false);
            issueResponse.setMsg(source.getRefuseRemark());
            issueResponse.setErrorCode(source.getRefuseReason());
        }

        IssueResultDetail issueDetail = issueResponse.getData();
        issueDetail.setAopTicketId(String.valueOf(source.getTicketOrderId()));
        issueResponse.setData(issueDetail);

        AbtTicketRequest abtTicketRequest = abtTicketRequestDao.findBySessionId(source.getSessionId());

        issueResponse.setOrderId(String.valueOf(abtTicketRequest.getOrderId()));
        Map<String, PnrInfo> pnrInfoMap = new HashMap<String, PnrInfo>();
        for (AopFeedbackItem aopFeedbackItem : source.getItems()) {

            PnrInfo pnrInfo;
            if (pnrInfoMap.containsKey(aopFeedbackItem.getPnrCode())) {
                pnrInfo = pnrInfoMap.get(aopFeedbackItem.getPnrCode());
            } else {
                pnrInfo = new PnrInfo();
                pnrInfo.setOrderId(aopFeedbackItem.getPnrCode());
                issueDetail.getPnrInfoList().add(pnrInfo);
                pnrInfoMap.put(aopFeedbackItem.getPnrCode(), pnrInfo);
            }

            PriceDetail priceDetail = pnrInfo.getPriceDetail();

            TicketInfo ticketInfo = new TicketInfo();

            AbtPnrPassenger abtPnrPassenger = abtPnrPassengerDao.findByOrderIdAndPnrAndPersonId(abtTicketRequest.getOrderId(), aopFeedbackItem.getPnrCode(), aopFeedbackItem.getPersonId());
            AbtPnrPrice abtPnrPrice = abtPnrPriceDao.findByPnrAndPriceTypeAndFlightItemIdAndOrderId(aopFeedbackItem.getPnrCode(), abtPnrPassenger.getPassengerType(), abtTicketRequest.getFlightItemId(), abtTicketRequest.getOrderId());
            PriceInfo priceInfo = new PriceInfo(CurrencyType.CNY.getCode(), abtPnrPrice.getOrgPrice(), abtPnrPrice.getSalePrice(), abtPnrPrice.getFloorPrice(), BigDecimal.valueOf(abtPnrPrice.getAirportTax()), BigDecimal.valueOf(abtPnrPrice.getFuelSurcharge()));

            if (abtPnrPassenger.getPassengerType() == BizzConstants.PERSON_TYPE_ADULT) {
                if (priceDetail.getAdultNum() == 0) {
                    priceDetail.setAdultPrice(priceInfo);
                }
                priceDetail.setAdultNum(priceDetail.getAdultNum() + 1);

            } else if (abtPnrPassenger.getPassengerType() == BizzConstants.PERSON_TYPE_CHILD) {
                if (priceDetail.getChildNum() == 0) {
                    priceDetail.setChildPrice(priceInfo);
                }
                priceDetail.setChildNum(priceDetail.getChildNum() + 1);
            } else if (abtPnrPassenger.getPassengerType() == BizzConstants.PERSON_TYPE_BABY) {
                if (priceDetail.getBabyNum() == 0) {
                    priceDetail.setBabyPrice(priceInfo);
                }
                priceDetail.setBabyNum(priceDetail.getBabyNum() + 1);
            }

            ticketInfo.addPassenger(new PassengerInfo(abtPnrPassenger.getFullName(), abtPnrPassenger.getPassengerType()));
            ticketInfo.setTicketNo(aopFeedbackItem.getTicketNo());
            pnrInfo.getTickets().add(ticketInfo);
        }

        for (PnrInfo pnrInfo : issueDetail.getPnrInfoList()) {
            AbtAopPolicy aopPolicy = abtAopPolicyDao.findByPnrAndOrderId(pnrInfo.getOrderId(), abtTicketRequest.getOrderId());
            pnrInfo.setVendorId(BizzConstants.V_AOP);
            pnrInfo.setSolutionId(String.valueOf(aopPolicy.getSubVendorId()));
            pnrInfo.setSolutionName(aopPolicy.getSubVendorName());

            if (source.getOpType() == BizzConstants.AOP_ISSUE_FAIL) {
                pnrInfo.setIssueFlag(false);
            } else {
                pnrInfo.setIssueFlag(true);
            }

            List<AbtPnrFlight> abtPnrFlights = abtPnrFlightDao.findByPnrAndFlightItemIdAndOrderId(pnrInfo.getOrderId(), abtTicketRequest.getFlightItemId(), abtTicketRequest.getOrderId());
            List<FlightInfo> flightInfos = new ArrayList<FlightInfo>();
            for (AbtPnrFlight abtPnrFlight : abtPnrFlights) {
                flightInfos.add(new FlightInfo(abtPnrFlight.getFlightNo(), abtPnrFlight.getSeatCode()));
            }

            for (TicketInfo ticketInfo : pnrInfo.getTickets()) {
                ticketInfo.getFlights().addAll(flightInfos);
            }
        }
        
        issueDetail.setFlightItemId(abtTicketRequest.getFlightItemId());

        return issueResponse;
    }

}
