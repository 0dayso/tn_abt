package com.tuniu.abt.service.travelsky.ibe.converter;

import com.travelsky.espeed.*;
import com.travelsky.ibe.client.pnr.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * RT Converter
 * Created by chengyao on 2016/4/14.
 */
public class RtConverter {

    /**
     * ibe rt结果对象 转化为 ibe+ rt结果对象
     * @param rtResult ibe result
     * @return ibe+ result
     */
    public static OTAAirResRetRS toPlus(RTResult rtResult) {
        OTAAirResRetRS otaAirResRetRS = new OTAAirResRetRS();
        otaAirResRetRS.setAirResRet(new OTAAirResRetRS.AirResRet());
        OTAAirResRetRS.AirResRet airResRet = otaAirResRetRS.getAirResRet();
        airResRet.setCreateTime(null); // NO IN IBE
        // 0. PNR/CONTACT/REMARK/RESP/TICKET
        fillPnr(airResRet, rtResult);
        fillContact(airResRet, rtResult);
        fillRemark(airResRet, rtResult);
        fillResponsibility(airResRet, rtResult);
        fillTicketing(airResRet, rtResult);

        // 1. 航段信息   FlightSegments
        Collection<FlightSegment2> flightSegmentList = findFlightSegmentList(rtResult);
        airResRet.getFlightSegments().add(new FlightSegments2());
        airResRet.getFlightSegments().get(0).getFlightSegment().addAll(flightSegmentList);
        // 2. 人员信息   AirTravelers
        Collection<AirTraveler2> airTravelerList = findAirTravelerList(rtResult);
        airResRet.getAirTraveler().addAll(airTravelerList);
        // 3. SSR
        List<SpecialServiceRequest> specialServiceRequests = findSsr(rtResult);
        airResRet.getSpecialServiceRequest().addAll(specialServiceRequests);
        // 4. OSI
        List<OtherServiceInformation> osis = findOsi(rtResult);
        airResRet.getOtherServiceInformation().addAll(osis);
        // 5. PRICE
        fillPrice(airResRet, rtResult);

        // others
        Collection<Others> others = findOthers(rtResult);
        if (others.size() > 0) airResRet.getOthers().addAll(others);

        // ticketItemInfo
        Collection<TicketItemInfo2> ticketItemInfos = findTicketItemInfos(rtResult);
        if (ticketItemInfos.size() > 0) airResRet.getTicketItemInfo().addAll(ticketItemInfos);

        // airResRet.setTcList();

        // airResRet.setOpenAccountAddressList();

        // TODO bas, ois, tcs,
        return otaAirResRetRS;
    }


    private static Collection<Others> findOthers(RTResult rtResult) {
        Collection<Others> list = new ArrayList<Others>();
        int count = rtResult.getOthersCount();
        for (int i = 0; i < count; i++) {
            PNROther pnrOther = rtResult.getOtherAt(i);
            Others others = new Others();
            others.setText(pnrOther.getOther());
            others.setRPH(pnrOther.getIndex());
            list.add(others);
        }
        return list;
    }

    private static List<TicketItemInfo2> findTicketItemInfos(RTResult rtResult) {
        List<TicketItemInfo2> list = new ArrayList<TicketItemInfo2>();
        int count = rtResult.getTktnosCount();
        for (int i = 0; i < count; i++) {
            PNRTktNo pnrTktNo = rtResult.getTktnoAt(i);
            TicketItemInfo2 ticketItemInfo = new TicketItemInfo2();
            ticketItemInfo.setRPH(pnrTktNo.getIndex());
            ticketItemInfo.setTicketNumber(pnrTktNo.getTktNo());

            if (StringUtils.isNotEmpty(pnrTktNo.getPsgrID())) {
                ticketItemInfo.setTravelerRefNumber(new TravelerRefNumber());
                ticketItemInfo.getTravelerRefNumber().setRPH(pnrTktNo.getPsgrID());
            }

            if (StringUtils.isNotEmpty(pnrTktNo.getRemark())) {
                ticketItemInfo.setRemark(pnrTktNo.getRemark());
            }
            list.add(ticketItemInfo);
        }
        return list;
    }

    private static void fillPrice(OTAAirResRetRS.AirResRet airResRet, RTResult rtResult) {

        List<FC> fcs = new ArrayList<FC>();
        int count = rtResult.getFCsCount();
        for (int i = 0; i < count; i++) {
            PNRFC pnrfc = rtResult.getFCAt(i);
            fcs.add(new FC());
            fcs.get(i).setRPH(pnrfc.getIndex());
            fcs.get(i).setText(pnrfc.getFc());
            if (StringUtils.isNotEmpty(pnrfc.getPsgrid())) {
                fcs.get(i).setTravelerRefNumber(new TravelerRefNumber());
                fcs.get(i).getTravelerRefNumber().setRPH(pnrfc.getPsgrid());
            }
        }

        List<FN> fns = new ArrayList<FN>();
        count = rtResult.getFNsCount();
        for (int i = 0; i < count; i++) {
            PNRFN pnrfn = rtResult.getFNAt(i);
            fns.add(new FN());
            fns.get(i).setRPH(pnrfn.getIndex());
            fns.get(i).setText(pnrfn.getFn());
            if (StringUtils.isNotEmpty(pnrfn.getPsgrid())) {
                fns.get(i).setTravelerRefNumber(new TravelerRefNumber());
                fns.get(i).getTravelerRefNumber().setRPH(pnrfn.getPsgrid());
            }
        }

        List<FP> fps = new ArrayList<FP>();
        count = rtResult.getFpsCount();
        for (int i = 0; i < count; i++) {
            PNRFP pnrfp = rtResult.getFpAt(i);
            fps.add(new FP());
            fps.get(i).setRPH(pnrfp.getIndex());
            fps.get(i).setRemark(pnrfp.getRemark());
            fps.get(i).setPayType(pnrfp.getPaytype());
            fps.get(i).setCurrency(pnrfp.getCurrency());
            fps.get(i).setIsInfant(pnrfp.isInfant());
            if (StringUtils.isNotEmpty(pnrfp.getPsgrid())) {
                fps.get(i).setTravelerRefNumber(new TravelerRefNumber());
                fps.get(i).getTravelerRefNumber().setRPH(pnrfp.getPsgrid());
            }
        }

        List<EI> eis = new ArrayList<EI>();
        count = rtResult.getEisCount();
        for (int i = 0; i < count; i++) {
            PNREI pnrei = rtResult.getEiAt(i);
            eis.add(new EI());
            eis.get(i).setRPH(pnrei.getIndex());
            eis.get(i).setText(pnrei.getEi());
            eis.get(i).setIsInfant(pnrei.isInfant());
            if (StringUtils.isNotEmpty(pnrei.getPsgrid())) {
                eis.get(i).setTravelerRefNumber(new TravelerRefNumber());
                eis.get(i).getTravelerRefNumber().setRPH(pnrei.getPsgrid());
            }
        }

        if (fcs.size() > 0) airResRet.getFC().addAll(fcs);
        if (fns.size() > 0) airResRet.getFN().addAll(fns);
        if (fps.size() > 0) airResRet.getFP().addAll(fps);
        if (eis.size() > 0) airResRet.getEI().addAll(eis);
    }

    /**
     * find airTraveler
     */
    private static List<AirTraveler2> findAirTravelerList(RTResult rtResult) {
        List<AirTraveler2> airTravelers = new ArrayList<AirTraveler2>();
        int count = rtResult.getPassengersCount();
        for (int i = 0; i < count; i++) {
            PNRPassenger passenger = rtResult.getPassengerAt(i);
            AirTraveler2 airTraveler = new AirTraveler2();

            airTraveler.setRPH(String.valueOf(passenger.getIndex()));

            int type = passenger.getType();
            airTraveler.setPassengerTypeQuantity(new PassengerTypeQuantity());
            PassengerType passengerTypeCode = PassengerType.CHD;
            if (type == PNRPassenger.PASSENGER_ADULT) passengerTypeCode = PassengerType.ADT;

            airTraveler.setPassengerTypeQuantity(new PassengerTypeQuantity());
            airTraveler.getPassengerTypeQuantity().setCode(passengerTypeCode);

            airTraveler.getPersonName().add(new PersonName());
            airTraveler.getPersonName().get(0).setSurname(passenger.getName());
            airTraveler.getPersonName().get(0).setNamePNR(passenger.getNameInPnr());

            airTravelers.add(airTraveler);
        }

        count = rtResult.getInfantsCount();
        for (int i = 0; i < count; i++) {
            PNRInfant passenger = rtResult.getInfantAt(i);
            AirTraveler2 airTraveler = new AirTraveler2();

            airTraveler.setRPH(String.valueOf(passenger.getIndex()));

            airTraveler.setPassengerTypeQuantity(new PassengerTypeQuantity());
            airTraveler.getPassengerTypeQuantity().setCode(PassengerType.INF);

            airTraveler.getPersonName().add(new PersonName());
            airTraveler.getPersonName().get(0).setSurname(passenger.getName());
            airTraveler.getPersonName().get(0).setNamePNR(passenger.getNameInPnr());
            airTraveler.setBirthDate(FastDateFormat.getInstance("yyyy-MM-dd").format(passenger.getBirth()));

            airTraveler.setTravelerRefNumber(new TravelerRefNumber());
            airTraveler.getTravelerRefNumber().setInfantTravelerRPH(passenger.getCarrier());

            airTravelers.add(airTraveler);
        }
        return airTravelers;
    }

    /**
     * find flightSegmentShop
     */
    private static Collection<FlightSegment2> findFlightSegmentList(RTResult rtResult) {
        Collection<FlightSegment2> segmentList = new ArrayList<FlightSegment2>();
        int count = rtResult.getAirSegsCount();
        for (int i = 0; i < count; i++) {
            PNRAirSeg pnrAirSeg = rtResult.getAirSegAt(i);
            FlightSegment2 flightSegment2 = new FlightSegment2();

            String airlineCode = pnrAirSeg.getAirNo().substring(0, 2);
            String airlineNumber = pnrAirSeg.getAirNo().substring(2);

            flightSegment2.setRPH(String.valueOf(pnrAirSeg.getIndex()));
            flightSegment2.setFlightNumber(airlineNumber);
            if (StringUtils.isNotBlank(pnrAirSeg.getActionCode()))
                flightSegment2.setStatus(pnrAirSeg.getActionCode());
            flightSegment2.setNumberInParty(new BigDecimal(pnrAirSeg.getTktNum()));
            // flightSegmentShop.setCodeshareInd(Boolean.toString(pnrAirSeg.isCodeShare()));
            flightSegment2.setIsChanged(pnrAirSeg.isSkChanged());
            flightSegment2.setTicket(pnrAirSeg.isEtktairseg() ? TicketType.E_TICKET : null);

            if (pnrAirSeg.getDepartureTime() != null)
                flightSegment2.setDepartureDateTime(
                    FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").format(pnrAirSeg.getDepartureTime()));
            if (pnrAirSeg.getArrivalTime() != null)
                flightSegment2.setArrivalDateTime(
                    FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").format(pnrAirSeg.getArrivalTime()));
            // type
            SegmentType segmentType = SegmentType.ARRIVAL_UNKNOWN;
            int type = pnrAirSeg.getType();
            if (type == PNRAirSeg.AIRSEG_NORMAL)
                segmentType = SegmentType.NORMAL;
            if (type == PNRAirSeg.AIRSEG_OPEN)
                segmentType = SegmentType.OPEN;
            flightSegment2.setSegmentType(segmentType);

            //承运方
            String operateCarrier = pnrAirSeg.getOperateCarrier();
            if (StringUtils.isNotEmpty(operateCarrier)) {
                flightSegment2.setOperatingAirline(new OperatingAirline());
                flightSegment2.getOperatingAirline().setCode(operateCarrier.substring(0, 2));
                flightSegment2.getOperatingAirline().setFlightNumber(operateCarrier.substring(2));
            }
            // 市场方
            MarketingAirline marketingAirline = new MarketingAirline();
            marketingAirline.setCode(airlineCode);
            flightSegment2.setMarketingAirline(new MarketingAirline());
            flightSegment2.getMarketingAirline().setCode(airlineCode);
            // 出发
            flightSegment2.setDepartureAirport(new DepartureAirport());
            flightSegment2.getDepartureAirport().setLocationCode(pnrAirSeg.getOrgCity());
            if (StringUtils.isNotBlank(pnrAirSeg.getDepartureTerm()))
                flightSegment2.getDepartureAirport().setTerminal(pnrAirSeg.getDepartureTerm());
            // 目的
            flightSegment2.setArrivalAirport(new ArrivalAirport());
            flightSegment2.getArrivalAirport().setLocationCode(pnrAirSeg.getDesCity());
            if (StringUtils.isNotBlank(pnrAirSeg.getArrivalTerm()))
                flightSegment2.getArrivalAirport().setTerminal(pnrAirSeg.getArrivalTerm());
            // 舱位
            if (!Character.isWhitespace(pnrAirSeg.getFltClass())) {
                flightSegment2.getBookingClassAvail().add(new BookingClassAvail());
                flightSegment2.getBookingClassAvail().get(0).setResBookDesigCode(String.valueOf(pnrAirSeg.getFltClass()));
                if (StringUtils.isNotEmpty(pnrAirSeg.getSubClass()))
                    flightSegment2.getBookingClassAvail().get(0).getSubClass().add(pnrAirSeg.getSubClass());
            }

            segmentList.add(flightSegment2);
        }
        return segmentList;
    }

    private static List<SpecialServiceRequest> findSsr(RTResult rtResult) {
        List<SpecialServiceRequest> specialServiceRequests = new ArrayList<SpecialServiceRequest>();
        int count = rtResult.getSSRsCount();
        for (int i = 0; i < count; i++) {
            PNRSSR pnrssr = rtResult.getSSRAt(i);
            SpecialServiceRequest specialServiceRequest = new SpecialServiceRequest();

            specialServiceRequest.setRPH(String.valueOf(pnrssr.getIndex()));
            specialServiceRequest.setAirline(new Airline());
            specialServiceRequest.getAirline().setCode(pnrssr.getAirline());
            specialServiceRequest.setSSRCode(pnrssr.getSSRType());
            specialServiceRequest.setText(pnrssr.getServeInfo());
            if (StringUtils.isNotEmpty(pnrssr.getActionCode()))
                specialServiceRequest.setStatus(pnrssr.getActionCode());

            if (StringUtils.isNotEmpty(pnrssr.getPsgrID())) {

                specialServiceRequest.getTravelerRefNumber().add(new TravelerRefNumber());
                specialServiceRequest.getTravelerRefNumber().get(0).setRPH(pnrssr.getPsgrID());
            }
            // TODO citypair;
            // specialServiceRequest.setFlightLegList();

            // specialServiceRequest.setServiceQuantity();

            // specialServiceRequest.setDocumentRefNumberList();

            // specialServiceRequest.setBirthDate();

            // specialServiceRequest.setFlightRefNumberList();
            specialServiceRequests.add(specialServiceRequest);
        }
        return specialServiceRequests;
    }

    private static List<OtherServiceInformation> findOsi(RTResult rtResult) {
        List<OtherServiceInformation> osis = new ArrayList<OtherServiceInformation>();
        int count = rtResult.getOSIsCount();
        for (int i = 0; i < count; i++) {
            PNROSI pnrosi = rtResult.getOSIAt(i);
            OtherServiceInformation osi = new OtherServiceInformation();
            osi.setRPH(String.valueOf(pnrosi.getIndex()));
            osi.setText(pnrosi.getOsi());
            if (StringUtils.isNotEmpty(pnrosi.getPNum())) {
                osi.getTravelerRefNumber().add(new TravelerRefNumber());
                osi.getTravelerRefNumber().get(0).setRPH(pnrosi.getPNum());
            }
            osis.add(osi);
        }
        return osis;
    }

    private static void fillPnr(OTAAirResRetRS.AirResRet airResRet, RTResult rtResult) {
        airResRet.setBookingReferenceID(new BookingReferenceID2());
        airResRet.getBookingReferenceID().setID(rtResult.getPnrcode());
    }

    private static void fillContact(OTAAirResRetRS.AirResRet airResRet, RTResult rtResult) {
        int count = rtResult.getContactsCount();
        for (int i = 0; i < count; i++) {
            PNRContact pnrContact = rtResult.getContactAt(i);
            airResRet.getContactInfo().add(new ContactInfo());
            airResRet.getContactInfo().get(i).setContactCity(pnrContact.getCity());
            airResRet.getContactInfo().get(i).setContactInfo(pnrContact.getContact());
            airResRet.getContactInfo().get(i).setRPH(pnrContact.getIndex());
            if (StringUtils.isNotEmpty(pnrContact.getPsgrID())) {
                airResRet.getContactInfo().get(i).setTravelerRefNumber(new TravelerRefNumber());
                airResRet.getContactInfo().get(i).getTravelerRefNumber().setRPH(pnrContact.getPsgrID());
            }
        }
    }

    private static void fillRemark(OTAAirResRetRS.AirResRet airResRet, RTResult rtResult) {
        int count = rtResult.getRMKsCount();
        for (int i = 0; i < count; i++) {
            PNRRMK pnrrmk = rtResult.getRMKAt(i);
            airResRet.getSpecialRemark().add(new SpecialRemark());
            airResRet.getSpecialRemark().get(i).setRPH(String.valueOf(pnrrmk.getIndex()));
            airResRet.getSpecialRemark().get(i).setText(pnrrmk.getRmkinfo());
            if (StringUtils.isNotEmpty(pnrrmk.getRmktype())) {
                airResRet.getSpecialRemark().get(i).setRemarkType(pnrrmk.getRmktype());
            }
        }
    }

    private static void fillResponsibility(OTAAirResRetRS.AirResRet airResRet, RTResult rtResult) {
        PNRResp pnrResp = rtResult.getResp();
        airResRet.setResponsibility(new Responsibility());
        airResRet.getResponsibility().setRPH(pnrResp.getIndex());
        airResRet.getResponsibility().setOfficeCode(pnrResp.getOfficecode());
        if (StringUtils.isNotEmpty(pnrResp.getRemark()))
            airResRet.getResponsibility().setRemark(pnrResp.getRemark());
        if (StringUtils.isNotEmpty(pnrResp.getCrssign()))
            airResRet.getResponsibility().setCRS(pnrResp.getCrssign());
        if (StringUtils.isNotEmpty(pnrResp.getPnrno()))
            airResRet.getResponsibility().setPNRno(pnrResp.getPnrno());
    }

    // PNR
    private static void fillTicketing(OTAAirResRetRS.AirResRet airResRet, RTResult rtResult) {
        int count = rtResult.getTktsCount();
        for (int i = 0; i < count; i++) {
            PNRTkt pnrTkt = rtResult.getTktAt(i);

            airResRet.getTicketing().add(new Ticketing2());
            airResRet.getTicketing().get(i).setRPH(pnrTkt.getIndex());
            airResRet.getTicketing().get(i).setIsIssued(pnrTkt.isTkted());
            airResRet.getTicketing().get(i).setIssuedType(pnrTkt.getType());
            if (StringUtils.isNotEmpty(pnrTkt.getOffice()))
                airResRet.getTicketing().get(i).setOfficeCode(pnrTkt.getOffice());
            if (StringUtils.isNotEmpty(pnrTkt.getRmk()))
                airResRet.getTicketing().get(i).setRemark(pnrTkt.getRmk());
            if (pnrTkt.getDateLimit() != null) {
                airResRet.getTicketing().get(i).setTicketTimeLimit(
                        FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").format(pnrTkt.getDateLimit())
                );
            }
            if (StringUtils.isNotEmpty(pnrTkt.getPsgrID())) {
                airResRet.getTicketing().get(i).setTravelerRefNumber(new TravelerRefNumber());
                airResRet.getTicketing().get(i).getTravelerRefNumber().setRPH(pnrTkt.getPsgrID());
            }
        }
    }
}
