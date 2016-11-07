package com.tuniu.abt.service.book;

import com.tuniu.abt.dao.*;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.book.main.*;
import com.tuniu.abt.intf.dto.book.request.BookingContactInfo;
import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.intf.entity.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.book.work.BookWorkSupport;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.adapter.flightTicket.vo.policy.aop.PolicyDetail;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * Created by lanlugang on 2016/6/8.
 */
@Service
public class BookDBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDBService.class);

    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;

    @Resource
    private AbtPnrFlightDao abtPnrFlightDao;

    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    @Resource
    private AbtAopPolicyDao abtAopPolicyDao;

    @Resource
    private AbtBookContactDao abtBookContactDao;

    @Transactional
    public void saveAbtBookingData(Long requestId, BookingRequest request, BookingReply bookingReply) {
        try {
            List<Long> policyIds = new ArrayList<Long>();
            for (PnrInfo pnrInfo : bookingReply.getPnrInfos()) {
                // abt_pnr_main
                Long pnrId = saveAbtPnrMain(request, requestId, pnrInfo);
                // abt_pnr_passenger
                saveAbtPnrPassengers(pnrId, pnrInfo.getPassengers());
                // abt_pnr_flight
                saveAbtPnrFlights(pnrId, pnrInfo.getSegments());
                // abt_pnr_price
                saveAbtPnrPrices(pnrId, pnrInfo.getPrices());
                if (null != pnrInfo.getPolicyId() && pnrInfo.getPolicyId() != 0) {
                    policyIds.add(pnrInfo.getPolicyId());
                }
            }
            // abt_aop_policy
            if (CollectionUtils.isNotEmpty(policyIds)
                    && CollectionUtils.isNotEmpty(BookWorkSupport.getAopPolicyDetailList())) {
                for (Long policyId : policyIds) {
                    for (PolicyDetail policyDetail : BookWorkSupport.getAopPolicyDetailList()) {
                        if (policyId.equals(new Long(policyDetail.getPolicyId()))) {
                            // 先查库中有没有该政策，如果没有再入库
                            AbtAopPolicy policyInDb = abtAopPolicyDao.find(policyId);
                            if (null == policyInDb) {
                                saveAbtAopPolicy(policyDetail);
                            }
                            break;
                        }
                    }
                }
            }
            // abt_book_contact
            BookingContactInfo contactInfo = request.getBookingDetail().getContactInfo();
            if (null != contactInfo) {
                AbtBookContact abtBookContact = new AbtBookContact();
                abtBookContact.setRequestId(requestId);
                abtBookContact.setName(contactInfo.getContactPersonName());
                abtBookContact.setTel(contactInfo.getContactPersonTel());
                abtBookContact.setEmail(contactInfo.getContactPersonEmail());
                abtBookContactDao.save(abtBookContact);
            }
        } catch (Exception e) {
            LOGGER.error("占位数据入库异常", e);
            throw new BizzException(BookEx.POST_DATA_CRUD_ERROR, "占位数据入库异常", e);
        }
    }

    private void saveAbtAopPolicy(PolicyDetail policyDetail) {
        AbtAopPolicy abtAopPolicy = new AbtAopPolicy();
        abtAopPolicy.setId((long)policyDetail.getPolicyId());
        abtAopPolicy.setVendorId(BizzConstants.V_AOP);
        if (policyDetail.getEtdzType() == 2) {
            abtAopPolicy.setChangePnrFlag(1);
        }
        abtAopPolicy.setSubVendorId(policyDetail.getSubVendorId());
        abtAopPolicy.setSubVendorName(policyDetail.getVendorName());
        abtAopPolicy.setTicketingOfficeNo(policyDetail.getEtdzOffice());
        abtAopPolicy.setSupplierOfficeNo(policyDetail.getVendorOffice());
        abtAopPolicy.setTicketingAccountNo(policyDetail.getEtdzAccountNo());
        abtAopPolicy.setTicketingAccountPwd(policyDetail.getEtdzPassword());
        abtAopPolicy.setActualTicketingOfficeNo(policyDetail.getEtdzOffice());
        abtAopPolicyDao.save(abtAopPolicy);
    }

    private Long saveAbtPnrMain(BookingRequest request, Long requestId, PnrInfo pnrInfo) throws ParseException {
        AbtPnrMain abtPnrMain = new AbtPnrMain();
        abtPnrMain.setRequestId(requestId);
        abtPnrMain.setOrderId(request.getOrderId());
        abtPnrMain.setPnr(pnrInfo.getPnrNo());
        abtPnrMain.setOutOrderId(pnrInfo.getPnrNo());
        abtPnrMain.setOutMainOrderId(pnrInfo.getAssociatedPnrNo());
        abtPnrMain.setExternalNo(pnrInfo.getExternalNo());
        Date clearTime = DateTimeUtils.parse(pnrInfo.getClearTime(), DateTimeUtils.DATETIME_PATTERN);
        abtPnrMain.setTimeLimit(clearTime);
        abtPnrMain.setActTimeLimit(clearTime);
        abtPnrMain.setOrderStatusDisplay(pnrInfo.getOrderStatusDisplay());
        abtPnrMain.setStatus(AbtPnrMain.STATUS_INIT);
        abtPnrMain.setPayStatus(AbtPnrMain.STATUS_INIT);
        abtPnrMain.setVendorId(request.getVendorId());
        if (null != pnrInfo.getDfpActionCode() && 0 != pnrInfo.getDfpActionCode()) {
            abtPnrMain.setDfpActionCode(String.valueOf(pnrInfo.getDfpActionCode()));
        }
        if (null != pnrInfo.getPolicyId() && 0 != pnrInfo.getPolicyId()) {
            abtPnrMain.setPolicyId(String.valueOf(pnrInfo.getPolicyId()));
        }
        abtPnrMain.setPolicyType(pnrInfo.getPolicyType());
        abtPnrMain.setTicketingCarrier(pnrInfo.getTicketingCarrier());
        abtPnrMain.setAirCompanyCode(pnrInfo.getAirCompanyCode());
        return abtPnrMainDao.save(abtPnrMain);
    }

    private void saveAbtPnrPassengers(Long pnrId, List<Passenger> passengers) {
        Map<Long, Long> passengerIdMap = new HashMap<Long, Long>();
        List<Passenger> infPassengers = null;
        AbtPnrPassenger abtPnrPassenger = null;
        for (Passenger passenger : passengers) {
            if (BizzConstants.PSG_TYPE_CODE_INF.equals(passenger.getPassengerTypeCode())) {
                if (null == infPassengers) {
                    infPassengers = new ArrayList<Passenger>();
                }
                infPassengers.add(passenger);
                continue;
            }
            abtPnrPassenger = convert2AbtPnrPassenger(pnrId, passenger);
            Long psgId = abtPnrPassengerDao.save(abtPnrPassenger);
            passengerIdMap.put(abtPnrPassenger.getPersonId(), psgId);
        }
        // 2016/4/11 婴儿需要写入跟随成人的id
        if (CollectionUtils.isNotEmpty(infPassengers)) {
            for (Passenger infPassenger : infPassengers) {
                AbtPnrPassenger inf = convert2AbtPnrPassenger(pnrId, infPassenger);
                Long refPsgId = passengerIdMap.get(infPassenger.getRefPersonId());
                inf.setRefPsgId(refPsgId);
                abtPnrPassengerDao.save(inf);
            }
        }
    }

    private void saveAbtPnrPrices(Long pnrId, List<PnrPrice> prices) {
        List<AbtPnrPrice> priceList = CommonUtils.transformList(prices, AbtPnrPrice.class);
        for (AbtPnrPrice abtPnrPrice : priceList) {
            abtPnrPrice.setPnrId(pnrId);
            abtPnrPriceDao.save(abtPnrPrice);
        }
    }

    private void saveAbtPnrFlights(Long pnrId, List<Segment> segments) throws ParseException {
        AbtPnrFlight abtPnrFlight = null;
        for (Segment segment : segments) {
            abtPnrFlight = new AbtPnrFlight();
            abtPnrFlight.setPnrId(pnrId);
            abtPnrFlight.setFlightNo(segment.getFlightNo());
            abtPnrFlight.setRph(segment.getSegmentNumber());
            abtPnrFlight.setSeatCode(segment.getSeatCode());
            abtPnrFlight.setPlaneType(segment.getPlaneType());
            abtPnrFlight.setOrgAirportCode(segment.getOrgAirportIataCode());
            abtPnrFlight.setOrgAirportName(segment.getOrgAirportName());
            abtPnrFlight.setOrgCityCode(segment.getOrgCityIataCode());
            abtPnrFlight.setOrgCityName(segment.getOrgCityName());
            abtPnrFlight.setOrgAirportTerminal(segment.getDepartureAirportTerminal());
            abtPnrFlight.setDstAirportCode(segment.getDstAirportIataCode());
            abtPnrFlight.setDstAirportName(segment.getDstAirportName());
            abtPnrFlight.setDstCityCode(segment.getDstCityIataCode());
            abtPnrFlight.setDstCityName(segment.getDstCityName());
            abtPnrFlight.setDstAirportTerminal(segment.getArrivalAirportTerminal());
            String orgTimeStr = segment.getDepartureDate() + " " + segment.getDepartureTime() + ":00";
            String dstTimeStr = segment.getArriveDate() + " " + segment.getArrivalTime() + ":00";
            abtPnrFlight.setOrgTime(DateTimeUtils.parse(orgTimeStr.substring(0, 19), DateTimeUtils.DATETIME_PATTERN));
            abtPnrFlight.setDstTime(DateTimeUtils.parse(dstTimeStr.substring(0, 19), DateTimeUtils.DATETIME_PATTERN));
            abtPnrFlightDao.save(abtPnrFlight);
        }
    }

    private AbtPnrPassenger convert2AbtPnrPassenger(Long pnrId, Passenger passenger) {
        AbtPnrPassenger abtPnrPassenger = new AbtPnrPassenger();
        abtPnrPassenger.setPnrId(pnrId);
        abtPnrPassenger.setStatus(AbtPnrPassenger.STATUS_INIT);
        abtPnrPassenger.setBookName(passenger.getPassengerName());
        abtPnrPassenger.setFullName(passenger.getOrgPassengerName());
        abtPnrPassenger.setFirstName(passenger.getFirstName());
        abtPnrPassenger.setLastName(passenger.getLastName());
        abtPnrPassenger.setPassengerType(BookPassengerType.valueOf(passenger.getPassengerTypeCode()).intValue());
        abtPnrPassenger.setBirthDate(passenger.getBirthday());
        abtPnrPassenger.setGender(passenger.getGender());
        abtPnrPassenger.setIdentityType(passenger.getIdentityType());
        abtPnrPassenger.setPersonId(passenger.getPersonId());
        abtPnrPassenger.setRefPsgId(0L);
        return abtPnrPassenger;
    }

}
