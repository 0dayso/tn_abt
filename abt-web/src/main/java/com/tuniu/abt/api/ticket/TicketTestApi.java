package com.tuniu.abt.api.ticket;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.converter.IssueRequestToTicketRequestConverter;
import com.tuniu.abt.dao.AbtPnrFlightDao;
import com.tuniu.abt.dao.AbtPnrPassengerDao;
import com.tuniu.abt.dao.AbtPnrPriceDao;
import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.dbservice.AbtTicketMainService;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.issue.request.IssueDetail;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.request.OrderInfo;
import com.tuniu.abt.intf.dto.issue.response.*;
import com.tuniu.abt.intf.entity.AbtPnrFlight;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import com.tuniu.abt.intf.entity.AbtPnrPrice;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.service.issue.IssueResultReporter;
import com.tuniu.abt.service.issue.travelsky.TravelSkyIssueService;
import com.tuniu.mauritius.price.constants.CurrencyType;
import com.tuniu.operation.platform.base.rest.RestException;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import com.tuniu.zkconfig.client.utils.ConfigApi;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/ticket")
@Profile({"local", "sit"})
public class TicketTestApi {

    @Resource
    private IssueRequestToTicketRequestConverter issueRequestToTicketRequestConverter;

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Resource
    private AbtTicketMainService abtTicketMainService;

    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;

    @Resource
    private AbtPnrFlightDao abtPnrFlightDao;
    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    @Resource
    private IssueResultReporter issueResultReporter;

    @Resource
    private ConfigApi configApi;

    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    @TSPServiceInfo(name = "ATS.ABT.TicketApi.issue", description = "出票")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.TICKET)
    public IssueResponse issue(@Json final IssueRequest issueRequest) throws RestException {

        AbtTicketRequest abtTicketRequest = issueRequestToTicketRequestConverter.convert(issueRequest);
        abtTicketRequestDao.save(abtTicketRequest);


        IssueResponse issueResponse = new IssueResponse();
        issueResponse.setSystemId(issueRequest.getSystemId());
        issueResponse.setOrderId(issueRequest.getOrderId());
        issueResponse.setIntl(0);
        issueResponse.setSessionId(issueRequest.getSessionId());
        issueResponse.setSuccess(true);


        IssueDetail issueDetail = issueRequest.getIssueDetail();

        issueResponse.getData().setFlightItemId(issueDetail.getFlightItemId());
        List<OrderInfo> orderIds = issueDetail.getOrderIds();

        int issue = configApi.get("issue-test", Integer.class);
        if (issue == -1) {
            issueResponse.setSuccess(false);
            issueResponse.setMsg("入参问题");
            issueResponse.setErrorCode(BizzEx.IBE_INTF_REQ_PARAM_EX);
            return issueResponse;
        }

        if (issue == 2) {
            //出票超时
            return null;
        }

        if (issue == 0) {
            issueResponse.setSuccess(false);
            issueResponse.setMsg("出票错误");

            issueResponse.setErrorCode(TicketEx.OBTAIN_TICKET_ERROR);

            for (OrderInfo orderId : orderIds) {

                PnrInfo pnrInfo = new PnrInfo();
                pnrInfo.setIssueFlag(false);
                pnrInfo.setOrderId(orderId.getPnr());
                pnrInfo.setNewOrderId(orderId.getPnr());
                pnrInfo.setVendorId(issueRequest.getVendorId());
                issueResponse.addPnrInfo(pnrInfo);
            }

            issueResultReporter.processCallback(issueRequest.getCallback(), issueResponse);

            return null;
        }


        if (issue == 4) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //start

                    IssueResponse issueResponse = new IssueResponse();
                    issueResponse.setSystemId(issueRequest.getSystemId());
                    issueResponse.setOrderId(issueRequest.getOrderId());
                    issueResponse.setIntl(0);
                    issueResponse.setSessionId(issueRequest.getSessionId());
                    issueResponse.setSuccess(true);


                    IssueDetail issueDetail = issueRequest.getIssueDetail();

                    issueResponse.getData().setFlightItemId(issueDetail.getFlightItemId());
                    List<OrderInfo> orderIds = issueDetail.getOrderIds();

                    for (int j = 0; j < orderIds.size(); j++) {
                        OrderInfo orderId = orderIds.get(j);

                        PnrInfo pnrInfo = new PnrInfo();


                        pnrInfo.setIssueFlag(true);


                        pnrInfo.setOrderId(orderId.getPnr());
                        pnrInfo.setNewOrderId(orderId.getPnr());
                        pnrInfo.setVendorId(issueRequest.getVendorId());
                        pnrInfo.setSolutionId("2");

                        PriceDetail priceDetail = new PriceDetail();
                        pnrInfo.setPriceDetail(priceDetail);

                        List<AbtPnrPassenger> passengers = abtPnrPassengerDao.findByPnrAndFlightItemIdAndOrderId(orderId.getPnr(), issueDetail.getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
                        List<AbtPnrFlight> flights = abtPnrFlightDao.findByPnrAndFlightItemIdAndOrderId(orderId.getPnr(), issueDetail.getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
                        Set<Integer> priceType = new HashSet<Integer>();

                        for (int i = 0; i < passengers.size(); i++) {
                            AbtPnrPassenger passenger = passengers.get(i);
                            TicketInfo ticketInfo = new TicketInfo();
                            ticketInfo.setPassengers(Collections.singletonList(new PassengerInfo(passenger.getFullName(), passenger.getPassengerType())));

                            for (AbtPnrFlight flight : flights) {
                                ticketInfo.addFlight(new FlightInfo(flight.getFlightNo(), flight.getSeatCode()));
                            }

                            if (pnrInfo.isIssueFlag()) {
                                ticketInfo.setTicketNo("784-177042566" + (9 - i));
                            }

                            pnrInfo.addTicketInfo(ticketInfo);

                            if (!priceType.contains(passenger.getPassengerType())) {
                                priceType.add(passenger.getPassengerType());
                                AbtPnrPrice abtPnrPrice = abtPnrPriceDao.findByPnrAndPriceTypeAndFlightItemIdAndOrderId(orderId.getPnr(), passenger.getPassengerType(), issueDetail.getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
                                if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_ADULT) {
                                    priceDetail.setAdultPrice(new PriceInfo(CurrencyType.CNY.getCode(), abtPnrPrice.getOrgPrice(), abtPnrPrice.getSalePrice(), abtPnrPrice.getFloorPrice(), BigDecimal.valueOf(abtPnrPrice.getAirportTax()), BigDecimal.valueOf(abtPnrPrice.getFuelSurcharge())));
                                } else if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_CHILD) {
                                    priceDetail.setChildPrice(new PriceInfo(CurrencyType.CNY.getCode(), abtPnrPrice.getOrgPrice(), abtPnrPrice.getSalePrice(), abtPnrPrice.getFloorPrice(), BigDecimal.valueOf(abtPnrPrice.getAirportTax()), BigDecimal.valueOf(abtPnrPrice.getFuelSurcharge())));
                                } else if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_BABY) {
                                    priceDetail.setBabyPrice(new PriceInfo(CurrencyType.CNY.getCode(), abtPnrPrice.getOrgPrice(), abtPnrPrice.getSalePrice(), abtPnrPrice.getFloorPrice(), BigDecimal.valueOf(abtPnrPrice.getAirportTax()), BigDecimal.valueOf(abtPnrPrice.getFuelSurcharge())));
                                }
                            }

                            if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_ADULT) {
                                priceDetail.setAdultNum(priceDetail.getAdultNum() + 1);
                            } else if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_CHILD) {
                                priceDetail.setChildNum(priceDetail.getChildNum() + 1);
                            } else if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_BABY) {
                                priceDetail.setBabyNum(priceDetail.getBabyNum() + 1);
                            }

                        }

                        issueResponse.addPnrInfo(pnrInfo);

                    }


                    issueResultReporter.processCallback(issueRequest.getCallback(), issueResponse);


                    //end
                }
            }, DateUtils.addMinutes(new Date(), 40));
        }


        if (issue == 1 || issue == 3) {
            for (int j = 0; j < orderIds.size(); j++) {
                OrderInfo orderId = orderIds.get(j);

                PnrInfo pnrInfo = new PnrInfo();

                if (issue == 3) {
                    pnrInfo.setIssueFlag(j % 2 == 0);
                    issueResponse.setSuccess(false);
                } else {
                    pnrInfo.setIssueFlag(true);
                }

                pnrInfo.setOrderId(orderId.getPnr());
                pnrInfo.setNewOrderId(orderId.getPnr());
                pnrInfo.setVendorId(issueRequest.getVendorId());
                pnrInfo.setSolutionId("2");

                PriceDetail priceDetail = new PriceDetail();
                pnrInfo.setPriceDetail(priceDetail);

                List<AbtPnrPassenger> passengers = abtPnrPassengerDao.findByPnrAndFlightItemIdAndOrderId(orderId.getPnr(), issueDetail.getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
                List<AbtPnrFlight> flights = abtPnrFlightDao.findByPnrAndFlightItemIdAndOrderId(orderId.getPnr(), issueDetail.getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
                Set<Integer> priceType = new HashSet<Integer>();

                for (int i = 0; i < passengers.size(); i++) {
                    AbtPnrPassenger passenger = passengers.get(i);
                    TicketInfo ticketInfo = new TicketInfo();
                    ticketInfo.setPassengers(Collections.singletonList(new PassengerInfo(passenger.getFullName(), passenger.getPassengerType())));

                    for (AbtPnrFlight flight : flights) {
                        ticketInfo.addFlight(new FlightInfo(flight.getFlightNo(), flight.getSeatCode()));
                    }

                    if (pnrInfo.isIssueFlag()) {
                        ticketInfo.setTicketNo("784-177042566" + (9 - i));
                    }

                    pnrInfo.addTicketInfo(ticketInfo);

                    if (!priceType.contains(passenger.getPassengerType())) {
                        priceType.add(passenger.getPassengerType());
                        AbtPnrPrice abtPnrPrice = abtPnrPriceDao.findByPnrAndPriceTypeAndFlightItemIdAndOrderId(orderId.getPnr(), passenger.getPassengerType(), issueDetail.getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
                        if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_ADULT) {
                            priceDetail.setAdultPrice(new PriceInfo(CurrencyType.CNY.getCode(), abtPnrPrice.getOrgPrice(), abtPnrPrice.getSalePrice(), abtPnrPrice.getFloorPrice(), BigDecimal.valueOf(abtPnrPrice.getAirportTax()), BigDecimal.valueOf(abtPnrPrice.getFuelSurcharge())));
                        } else if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_CHILD) {
                            priceDetail.setChildPrice(new PriceInfo(CurrencyType.CNY.getCode(), abtPnrPrice.getOrgPrice(), abtPnrPrice.getSalePrice(), abtPnrPrice.getFloorPrice(), BigDecimal.valueOf(abtPnrPrice.getAirportTax()), BigDecimal.valueOf(abtPnrPrice.getFuelSurcharge())));
                        } else if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_BABY) {
                            priceDetail.setBabyPrice(new PriceInfo(CurrencyType.CNY.getCode(), abtPnrPrice.getOrgPrice(), abtPnrPrice.getSalePrice(), abtPnrPrice.getFloorPrice(), BigDecimal.valueOf(abtPnrPrice.getAirportTax()), BigDecimal.valueOf(abtPnrPrice.getFuelSurcharge())));
                        }
                    }

                    if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_ADULT) {
                        priceDetail.setAdultNum(priceDetail.getAdultNum() + 1);
                    } else if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_CHILD) {
                        priceDetail.setChildNum(priceDetail.getChildNum() + 1);
                    } else if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_BABY) {
                        priceDetail.setBabyNum(priceDetail.getBabyNum() + 1);
                    }

                }

                issueResponse.addPnrInfo(pnrInfo);

                abtTicketMainService.saveTicketInfo(abtTicketRequest.getId(), pnrInfo);

            }

            issueResultReporter.processCallback(issueRequest.getCallback(), issueResponse);
        }


        return null;
    }
}

