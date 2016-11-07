package com.tuniu.abt.service.travelsky.ibeplus.converter;

import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.PassengerItem;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by chengyao on 2016/4/11.
 */
@Service
public class BookModifyConverter {

    @Resource
    private SystemConfig systemConfig;

    public OTAAirBookModifyRQ makeRQ(String pnrNo, AirbookModifyType airbookModifyType, Object... params) {
        OTAAirBookModifyRQ otaAirBookModifyRQ = new OTAAirBookModifyRQ();

        OTAAirBookModifyRQ.AirBookModifyRQ airBookModifyRQ = new OTAAirBookModifyRQ.AirBookModifyRQ();
        airBookModifyRQ.setModificationType(airbookModifyType);
        otaAirBookModifyRQ.setAirBookModifyRQ(airBookModifyRQ);

        // fill pos
        fillPos(otaAirBookModifyRQ);

        // fill pnr
        fillPnr(otaAirBookModifyRQ, pnrNo);

        // convert to RQ
        convert(otaAirBookModifyRQ, airbookModifyType, params);

        return otaAirBookModifyRQ;
    }

    private void fillPos(OTAAirBookModifyRQ otaAirBookModifyRQ) {
        if (otaAirBookModifyRQ == null) {
            return;
        }
        otaAirBookModifyRQ.setPOS(new POS());
        otaAirBookModifyRQ.getPOS().getSource().add(new Source());
        otaAirBookModifyRQ.getPOS().getSource().get(0).setPseudoCityCode(systemConfig.getIbeplusOfficeNo());
    }

    private void fillPnr(OTAAirBookModifyRQ otaAirBookModifyRQ, String pnrNo) {
        if (StringUtils.isEmpty(pnrNo)) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX, "PNR修改接口的PNR号参数为空");
        }

        otaAirBookModifyRQ.setAirReservation(new AirReservation());
        otaAirBookModifyRQ.getAirReservation().getBookingReferenceID().add(new BookingReferenceID());
        BookingReferenceID bookingReferenceID = otaAirBookModifyRQ.getAirReservation().getBookingReferenceID().get(0);
        bookingReferenceID.setID(pnrNo);
    }

    private void convert(OTAAirBookModifyRQ otaAirBookModifyRQ, AirbookModifyType airbookModifyType, Object... object) {
        switch (airbookModifyType) {
        case ITEM_CANCEL:
            toItemCancel(otaAirBookModifyRQ, (Collection<String>) object[0]);break;
        case SEGMENT_CONFIRM:
            toSegmentConfirm(otaAirBookModifyRQ, (String) object[0]);break;
        case SEGMENT_NO:
            toSegmentNo(otaAirBookModifyRQ, (List<FlightSegment2>) object[0]);break;
        case EI_ADD:
            toEiAdd(otaAirBookModifyRQ, (String) object[0]);break;
        case OSI_ADD:
            toOsiAdd(otaAirBookModifyRQ, (String) object[0], (String) object[1]);break;
        case PASSENGER_FOID_MODIFY:
            toPassengerFoidModify(otaAirBookModifyRQ, (List<AirTraveler>) object[0]);break;
        case REMARK_ADD:
            toRemarkAdd(otaAirBookModifyRQ, (String) object[0]);break;
        case PNR_CANCEL:
            toPnrCancel(otaAirBookModifyRQ);break;
        case PASSENGER_DELETE:
            toPassengerDelete(otaAirBookModifyRQ, (List<PassengerItem>) object[0]);break;
        case SSR_ADD:
            toSsrAdd(otaAirBookModifyRQ, (SpecialServiceRequest) object[0], (List<AirTraveler>) object[1]);break;
        case PNR_SPLIT:
            toPnrSplit(otaAirBookModifyRQ, (List<AirTraveler>) object[0]);break;
        default:
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX, "PNR修改接口airbookModifyType类型错误，请检查入参！");
        }
    }

    private void toPnrSplit(OTAAirBookModifyRQ otaAirBookModifyRQ, List<AirTraveler> travelers) {
        AirReservation airReservation = new AirReservation();
        TravelerInfo traveler = new TravelerInfo();
        traveler.getAirTraveler().addAll(travelers);
        airReservation.setTravelerInfo(traveler);
        otaAirBookModifyRQ.getAirBookModifyRQ().setAirReservation(airReservation);
    }

    private void toPassengerDelete(OTAAirBookModifyRQ otaAirBookModifyRQ, List<PassengerItem> passengers) {
        AirReservation travelerAirReservation = new AirReservation();
        TravelerInfo travelerInfo = new TravelerInfo();
        List<AirTraveler> reqTravelers = new ArrayList<AirTraveler>();
        for (PassengerItem passenger : passengers) {
            String rph = passenger.getRph();
            String passengerName = passenger.getName();
            AirTraveler airTraveler = new AirTraveler();
            airTraveler.setPassengerTypeCode(passenger.getPassengerType());
            airTraveler.setAccompaniedByInfant(false);
            TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
            travelerRefNumber.setRPH(rph);
            PersonName personName = new PersonName();
            personName.setLanguageType(LangType.ZH);
            personName.setSurname(passengerName);
            airTraveler.getPersonName().add(personName);
            airTraveler.setTravelerRefNumber(travelerRefNumber);
            reqTravelers.add(airTraveler);
        }
        travelerInfo.getAirTraveler().addAll(reqTravelers);
        travelerAirReservation.setTravelerInfo(travelerInfo);
        otaAirBookModifyRQ.getAirBookModifyRQ().setAirReservation(travelerAirReservation);
    }

    private void toPnrCancel(OTAAirBookModifyRQ otaAirBookModifyRQ) {
        // nothing done
    }

    private void toSegmentConfirm(OTAAirBookModifyRQ otaAirBookModifyRQ, String modificationInfo) {
        otaAirBookModifyRQ.getAirBookModifyRQ().setModificationInfo(modificationInfo);
    }

    private void toSegmentNo(OTAAirBookModifyRQ otaAirBookModifyRQ, List<FlightSegment2> flightSegmentShops) {
        OTAAirBookModifyRQ.AirBookModifyRQ airBookModifyRQ = otaAirBookModifyRQ.getAirBookModifyRQ();

        // 添加航段信息
        airBookModifyRQ.setModificationInfo("K");
        AirReservation airRes4Seg = new AirReservation();
        AirItinerary airItinerary = new AirItinerary();
        if (CollectionUtils.isEmpty(flightSegmentShops)) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX, "PNR修改接口SegmentNo，没有传入flightSegmentShops，请检查入参！");
        }
        List<FlightSegment> segmentNOList = new ArrayList<FlightSegment>();
        FlightSegment segment = null;
        for (FlightSegment2 segmentShop : flightSegmentShops) {
            segment = new FlightSegment();
            String flightNO = segmentShop.getFlightNumber();
            String airlineCode = segmentShop.getMarketingAirline().getCode();
            segment.setStatus("NN");
            String segmentRPH = segmentShop.getRPH();
            if (null != segmentRPH && !segmentRPH.trim().isEmpty()) {
                segment.setRPH(segmentRPH);
            }
            segment.setDepartureDateTime(segmentShop.getDepartureDateTime());
            segment.setFlightNumber(flightNO);
            segment.setNumberInParty(segmentShop.getNumberInParty());
            segment.setSegmentType(segmentShop.getSegmentType());
            // 市场方航司二字码
            MarketingAirline marketingAirline = new MarketingAirline();
            marketingAirline.setCode(airlineCode);
            segment.setMarketingAirline(marketingAirline);
            // 承运方航司二字码+航班号
            OperatingAirline operatingAirline = new OperatingAirline();
            operatingAirline.setCode(airlineCode);
            operatingAirline.setFlightNumber(flightNO);
            segment.setOperatingAirline(operatingAirline);
            // 出发到达机场+舱位
            DepartureAirport departureAirport = new DepartureAirport();
            departureAirport.setLocationCode(segmentShop.getDepartureAirport().getLocationCode());
            segment.setDepartureAirport(departureAirport);
            ArrivalAirport arrivalAirport = new ArrivalAirport();
            arrivalAirport.setLocationCode(segmentShop.getArrivalAirport().getLocationCode());
            segment.setArrivalAirport(arrivalAirport);

            BookingClassAvail bookingClassAvail = new BookingClassAvail();
            String seatCode = segmentShop.getBookingClassAvail().get(0).getResBookDesigCode();
            bookingClassAvail.setResBookDesigCode(seatCode);
            segment.getBookingClassAvail().add(bookingClassAvail);
            segmentNOList.add(segment);
        }
        airItinerary.getOriginDestinationOptions().add(new OriginDestinationOptions());
        airItinerary.getOriginDestinationOptions().get(0).getOriginDestinationOption().add(new OriginDestinationOption());
        airItinerary.getOriginDestinationOptions().get(0)
                .getOriginDestinationOption().get(0).getFlightSegment().addAll(segmentNOList);
        airRes4Seg.setAirItinerary(airItinerary);
        airBookModifyRQ.setAirReservation(airRes4Seg);
    }

    private void toPassengerFoidModify(OTAAirBookModifyRQ otaAirBookModifyRQ, List<AirTraveler> airTravelers) {
        if (null == airTravelers || airTravelers.size() == 0) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX, "PNR修改接口PassengerFoidModify，没有传入airTravelers，请检查入参！");
        }

        OTAAirBookModifyRQ.AirBookModifyRQ airBookModifyRQ = otaAirBookModifyRQ.getAirBookModifyRQ();

        // 处理人员信息
        AirReservation travelerAirReservation = new AirReservation();
        TravelerInfo travelerInfo = new TravelerInfo();

        travelerInfo.getAirTraveler().addAll(airTravelers);
        travelerAirReservation.setTravelerInfo(travelerInfo);
        airBookModifyRQ.setAirReservation(travelerAirReservation);

    }

    private void toItemCancel(OTAAirBookModifyRQ otaAirBookModifyRQ, Collection<String> rphList) {
        String idContext = StringUtils.join(rphList, " ");
        if (StringUtils.isEmpty(idContext)) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX, "PNR修改接口按行删除PNR项，没有传入删除项的序号，请检查入参！");
        }
        OTAAirBookModifyRQ.AirBookModifyRQ airBookModifyRQ = otaAirBookModifyRQ.getAirBookModifyRQ();
        AirReservation airReservation = otaAirBookModifyRQ.getAirReservation();
        airReservation.getBookingReferenceID().get(0).setIDContext(idContext);
        airBookModifyRQ.setAirReservation(airReservation);
    }


    private void toEiAdd(OTAAirBookModifyRQ otaAirBookModifyRQ, String endorsement) {
        OTAAirBookModifyRQ.AirBookModifyRQ airBookModifyRQ = otaAirBookModifyRQ.getAirBookModifyRQ();

        TicketItemInfo ticketItemInfo = new TicketItemInfo();
        ticketItemInfo.setEndorsement(endorsement);
        Ticketing ticketing = new Ticketing();
        ticketing.setTicketItemInfo(ticketItemInfo);
        AirReservation airReservation = otaAirBookModifyRQ.getAirReservation();
        airReservation.getTicketing().add(ticketing);

        airBookModifyRQ.setAirReservation(airReservation);
    }

    private void toOsiAdd(OTAAirBookModifyRQ otaAirBookModifyRQ, String airlineCode, String osiInfo) {
        // ROOT
        TravelerInfo pnrTravelerInfo = new TravelerInfo();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setPassengerTypeCode(PassengerType.OTHER);
        airTraveler.setAccompaniedByInfant(false);
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRPH("1");
        airTraveler.setTravelerRefNumber(travelerRefNumber);
        pnrTravelerInfo.getAirTraveler().add(airTraveler);
        otaAirBookModifyRQ.getAirReservation().setTravelerInfo(pnrTravelerInfo);

        // otherServiceInformations
        AirReservation airReservation = new AirReservation();
        SpecialReqDetails specialReqDetails = new SpecialReqDetails();
        specialReqDetails.setOtherServiceInformations(new SpecialReqDetails.OtherServiceInformations());
        OtherServiceInformation osi = new OtherServiceInformation();
        osi.getTravelerRefNumber().add(travelerRefNumber);
        osi.setText(osiInfo);
        Airline airline = new Airline();
        airline.setCode(airlineCode);
        osi.setAirline(airline);
        specialReqDetails.getOtherServiceInformations().getOtherServiceInformation().add(osi);
        TravelerInfo traveler = new TravelerInfo();
        traveler.setSpecialReqDetails(specialReqDetails);
        airReservation.setTravelerInfo(traveler);
        otaAirBookModifyRQ.getAirBookModifyRQ().setAirReservation(airReservation);
    }

    private void toRemarkAdd(OTAAirBookModifyRQ otaAirBookModifyRQ, String rmkText) {
        // ROOT
        TravelerInfo pnrTravelerInfo = new TravelerInfo();
        AirTraveler airTraveler = new AirTraveler();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRPH("1");
        airTraveler.setTravelerRefNumber(travelerRefNumber);
        airTraveler.setPassengerTypeCode(PassengerType.OTHER);
        pnrTravelerInfo.getAirTraveler().add(airTraveler);
        otaAirBookModifyRQ.getAirReservation().setTravelerInfo(pnrTravelerInfo);

        // rmkText
        AirReservation airReservation = new AirReservation();
        TravelerInfo travelerInfo = new TravelerInfo();
        SpecialReqDetails specialReqDetails = new SpecialReqDetails();
        SpecialRemark specialRemark = new SpecialRemark();
        specialRemark.setText(rmkText);
        specialReqDetails.setSpecialRemarks(new SpecialReqDetails.SpecialRemarks());
        specialReqDetails.getSpecialRemarks().getSpecialRemark().add(specialRemark);
        pnrTravelerInfo.setSpecialReqDetails(specialReqDetails);
        airReservation.setTravelerInfo(travelerInfo);
        otaAirBookModifyRQ.getAirBookModifyRQ().setAirReservation(airReservation);
    }

    private void toSsrAdd(OTAAirBookModifyRQ otaAirBookModifyRQ, SpecialServiceRequest specialServiceRequest, List<AirTraveler> travelers) {
        // if (null == travelers || travelers.size() == 0) {
        //     throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX, "PNR修改接口添加SSR，没有传入旅客信息，请检查入参！");
        // }

        // ROOT
        if (CollectionUtils.isNotEmpty(travelers)) {
            TravelerInfo pnrTravelerInfo = new TravelerInfo();
            pnrTravelerInfo.getAirTraveler().addAll(travelers);
            otaAirBookModifyRQ.getAirReservation().setTravelerInfo(pnrTravelerInfo);
        }

        // specialServiceRequest
        AirReservation airReservation = new AirReservation();
        SpecialReqDetails specialReqDetails = new SpecialReqDetails();
        specialReqDetails.setSpecialServiceRequests(new SpecialReqDetails.SpecialServiceRequests());
        specialReqDetails.getSpecialServiceRequests().getSpecialServiceRequest().add(specialServiceRequest);
        TravelerInfo traveler = new TravelerInfo();
        traveler.setSpecialReqDetails(specialReqDetails);
        airReservation.setTravelerInfo(traveler);
        otaAirBookModifyRQ.getAirBookModifyRQ().setAirReservation(airReservation);
    }



}
