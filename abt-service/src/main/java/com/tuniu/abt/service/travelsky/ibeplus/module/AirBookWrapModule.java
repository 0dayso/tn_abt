package com.tuniu.abt.service.travelsky.ibeplus.module;

import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.Passenger;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.AirBookRsWrapper;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusInterfaceService;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusIntf;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * IBE PLUS AIR_BOOK 接口再次封装
 * Created by chengyao on 2016/2/20.
 */
@Service
public class AirBookWrapModule {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private IbePlusInterfaceService ibePlusInterfaceService;

    /**
     * IBEPLUS生成PNR接口，底层实现方法
     *
     * @param bookingData bookingData
     */
    public AirBookRsWrapper airBook(BookingData bookingData) {
        AirBookRsWrapper result = new AirBookRsWrapper();

        OTAAirBookRQ request = toAirBookRequest(bookingData);
        OTAAirBookRS response = ibePlusInterfaceService.airBook(request);

        String pnrNo = response.getAirReservation().get(0).getBookingReferenceID().get(0).getID();

        String ticketTimeLimit = request.getTicketing().getTicketTimeLimit();
        String clearTime = ticketTimeLimit.substring(0, 10) + " " + ticketTimeLimit.substring(11);

        List<FlightSegment> flightSegmentList = response
                .getAirReservation().get(0).getAirItinerary().getFlightSegments().getFlightSegment();
        List<String> actionCodes = new ArrayList<String>();
        for (FlightSegment flightSegment : flightSegmentList) {
            actionCodes.add(flightSegment.getStatus());
        }
        List<String> comments = response.getAirReservation().get(0).getComment();

        result.setPnrNo(pnrNo);
        result.setActionCodes(actionCodes);
        result.setClearTime(clearTime);
        result.setComments(comments);
        return result;
    }

    /**
     * 组装OTAAirBookRQ
     *
     * @param bookingData bookingData
     * @return
     */
    private OTAAirBookRQ toAirBookRequest(BookingData bookingData) {
        String departureDate = bookingData.getSegments().get(0).getDepartureDate();
        String departureTime = bookingData.getSegments().get(0).getDepartureTime();

        try {
            // 1. 扩展信息：联系方式，封口方式，封口延时设置
            TPAExtensions tpaExtensions = new TPAExtensions();
            tpaExtensions.setEnvelopType("KI");
            tpaExtensions.setEnvelopDelay(false);
            // 2. 设置出票时限：航班起飞前2小时
            Ticketing ticketing = new Ticketing();
            String ticketTimeLimit = calculateTicketTimeLimit(departureDate + " " + departureTime);
            ticketing.setTicketTimeLimit(ticketTimeLimit);

            // 3.行程信息
            AirItinerary airItinerary = new AirItinerary();
            OriginDestinationOptions originDestinationOptions = new OriginDestinationOptions();
            OriginDestinationOption originDestinationOption = new OriginDestinationOption();
            List<Segment> flightSegments = bookingData.getSegments();
            List<StringBuffer> chdSsrOthsTextList = new ArrayList<StringBuffer>();//eg: CZ 6729 06JUL Y
            List<String> flightSegmentRphList = new ArrayList<String>();//存放航段序号，乘客需要绑定航段
            StringBuffer chdSsrOthsText = null;
            for (Segment flightSegment : flightSegments) {
                // 拼接文本用于儿童SSR_OSI项：备注成人PNR 航班舱位信息--- start
                chdSsrOthsText = new StringBuffer();
                String month = DateTimeUtils.convertMonth(flightSegment.getDepartureDate().substring(5, 7));
                String day = flightSegment.getDepartureDate().substring(8, 10);
                chdSsrOthsText.append(flightSegment.getFlightNo().substring(2)).append(" ").append(day).append(month)
                        .append(" ");
                chdSsrOthsTextList.add(chdSsrOthsText);
                // 拼接文本用于儿童SSR_OSI项：备注成人PNR 航班舱位信息--- end
                FlightSegment segmentShop = new FlightSegment();
                // 航班信息
                segmentShop.setFlightNumber(flightSegment.getFlightNo().substring(2));
                String flightSegmentRph = String.valueOf(flightSegment.getSegmentNumber());
                flightSegmentRphList.add(flightSegmentRph);
                segmentShop.setRPH(flightSegmentRph);
                segmentShop.setDepartureDateTime(
                        flightSegment.getDepartureDate() + "T" + flightSegment.getDepartureTime() + ":00");
                if (null != flightSegment.getArriveDate() && null != flightSegment.getArrivalTime() && !flightSegment
                        .getArriveDate().trim().isEmpty() && !flightSegment.getArrivalTime().trim().isEmpty()) {
                    segmentShop.setArrivalDateTime(
                            flightSegment.getArriveDate() + "T" + flightSegment.getArrivalTime() + ":00");
                }
                segmentShop.setCodeshareInd(false);
                segmentShop.setStatus("NN");
                segmentShop.setSegmentType(SegmentType.NORMAL);
                // 出发机场
                DepartureAirport dAirport = new DepartureAirport();
                dAirport.setLocationCode(flightSegment.getOrgAirportIataCode());
                segmentShop.setDepartureAirport(dAirport);
                // 到达机场
                ArrivalAirport aAirport = new ArrivalAirport();
                aAirport.setLocationCode(flightSegment.getDstAirportIataCode());
                segmentShop.setArrivalAirport(aAirport);
                // 机型
                Equipment equipment = new Equipment();
                equipment.setAirEquipType(flightSegment.getPlaneType());
                segmentShop.setEquipment(equipment);
                // 航司
                MarketingAirline marketingAirline = new MarketingAirline();
                String airlineCode = flightSegment.getAirlineCompanyIataCode();
                if (null == airlineCode || airlineCode.trim().isEmpty()) {
                    airlineCode = flightSegment.getFlightNo().substring(0, 2);
                }
                marketingAirline.setCode(airlineCode);
                segmentShop.setMarketingAirline(marketingAirline);
                // 舱位
                BookingClassAvail bookingClassAvail = new BookingClassAvail();
                bookingClassAvail.setResBookDesigCode(flightSegment.getSeatCode().substring(0, 1));
                segmentShop.getBookingClassAvail().add(bookingClassAvail);
                originDestinationOption.getFlightSegment().add(segmentShop);
            }
            originDestinationOptions.getOriginDestinationOption().add(originDestinationOption);
            airItinerary.getOriginDestinationOptions().add(originDestinationOptions);
            // 4.旅客信息
            TravelerInfo travelerInfo = new TravelerInfo();
            Map<String, String> rphBirthdayMap = new HashMap<String, String>();
            List<AirTraveler> airTravelers = new ArrayList<AirTraveler>();
            Map<Long, Passenger> passengerIdMap = new HashMap<Long, Passenger>();
            Map<String, Passenger> passengerNameMap = new HashMap<String, Passenger>();
            Map<String, AirTraveler> airTravelerMap = new HashMap<String, AirTraveler>();
            for (int i = 0; i < bookingData.getPassengers().size(); i++) {
                Passenger passenger = bookingData.getPassengers().get(i);
                passengerIdMap.put(passenger.getPersonId(), passenger);
                passengerNameMap.put(passenger.getPassengerName(), passenger);
                AirTraveler airTraveler = new AirTraveler();
                // 性别
                String sex = "MALE";
                if (passenger.getGender() == 1) {
                    sex = "MALE";
                } else {
                    sex = "FEMALE";
                }
                airTraveler.setGender(sex);
                // 姓名，婴儿需要添加英文名(姓名拼音)
                PersonName personName = new PersonName();
                personName.setLanguageType(LangType.ZH);
                personName.setSurname(passenger.getPassengerName());
                airTraveler.getPersonName().add(personName);
                // 乘客类型，婴儿需要添加生日
                if (BizzConstants.PSG_TYPE_CODE_ADT.equals(passenger.getPassengerTypeCode())) {
                    airTraveler.setPassengerTypeCode(PassengerType.ADT);
                } else if (BizzConstants.PSG_TYPE_CODE_INF.equals(passenger.getPassengerTypeCode())) {
                    airTraveler.setPassengerTypeCode(PassengerType.INF);
                    airTraveler.setBirthDate(passenger.getBirthday());
                    PersonName infEnName = new PersonName();
                    infEnName.setLanguageType(LangType.EN);
                    if (!StringUtils.isEmpty(passenger.getFirstName()) && !StringUtils
                            .isEmpty(passenger.getLastName())) {
                        infEnName.setSurname(passenger.getFirstName() + "/" + passenger.getLastName());
                    } else {
                        infEnName.setSurname(passenger.getPassengerName());
                    }
                    airTraveler.getPersonName().add(infEnName);
                } else if (BizzConstants.PSG_TYPE_CODE_CHD.equals(passenger.getPassengerTypeCode())) {
                    airTraveler.setPassengerTypeCode(PassengerType.CHD);
                }
                // 证件号
                Document document = new Document();
                // PNR中默认证件类型NI
                document.setDocType(DocumentType.NI);
                document.setDocID(passenger.getIdentityNo());
                airTraveler.getDocument().add(document);
                // 乘客编号rph
                TravelerRefNumber travelerRefNumberP = new TravelerRefNumber();
                travelerRefNumberP.setRPH(String.valueOf(i + 1));
                airTraveler.setTravelerRefNumber(travelerRefNumberP);
                // 备注信息
                airTraveler.setComment("HK");
                // 航段绑定
                FlightSegmentRPHs flightSegmentRPHs = new FlightSegmentRPHs();
                flightSegmentRPHs.getFlightSegmentRPH().addAll(flightSegmentRphList);
                airTraveler.setFlightSegmentRPHs(flightSegmentRPHs);
                // 证件绑定
                /*List<DocumentFlightBinding> bindingList = new ArrayList<DocumentFlightBinding>();
	            for (String flightSegmentRPH : flightSegmentRphList) {
	                DocumentFlightBinding documentFlightBinding = new DocumentFlightBinding();
	                documentFlightBinding.setDocumentRPH((i + 1) + "");
	                documentFlightBinding.setFlightSegmentRPH(flightSegmentRPH);
	                bindingList.add(documentFlightBinding);
	            }
	            airTraveler.setDocumentFlightBindings(bindingList);*/
                airTravelers.add(airTraveler);
                airTravelerMap.put(airTraveler.getPersonName().get(0).getSurname(), airTraveler);
                rphBirthdayMap.put(String.valueOf((i + 1)), passenger.getBirthday());
            }
            // 婴儿特殊处理：关联跟随的成人
            for (AirTraveler babyAirTraveler : airTravelers) {
                if (babyAirTraveler.getPassengerTypeCode().equals(PassengerType.INF)) {
                    Passenger infPassenger = passengerNameMap.get(babyAirTraveler.getPersonName().get(0).getSurname());
                    Passenger refPassenger = passengerIdMap.get(infPassenger.getRefPersonId());
                    AirTraveler refAirTraveler = airTravelerMap.get(refPassenger.getPassengerName());
                    refAirTraveler.setAccompaniedByInfant(true);
                    refAirTraveler.getTravelerRefNumber()
                            .setInfantTravelerRPH(babyAirTraveler.getTravelerRefNumber().getRPH());
                }
            }
            // 5.特殊输入项 SSR OSI
            Airline airline = new Airline();
            Segment flightSegment = bookingData.getSegments().get(0);
            String airlineCode = flightSegment.getAirlineCompanyIataCode();
            if (null == airlineCode || airlineCode.trim().isEmpty()) {
                airlineCode = flightSegment.getFlightNo().substring(0, 2);
            }
            airline.setCode(airlineCode);
            SpecialReqDetails specialReqDetails = new SpecialReqDetails();
            SpecialReqDetails.OtherServiceInformations otherServiceInformations = new SpecialReqDetails.OtherServiceInformations();
            String ctct = "CTCT" + bookingData.getContactTel();
            String ctcm = "CTCM" + bookingData.getContactTel();
            // CTCT项不需要关联对应旅客，属于非PNR中旅客联系方式
            OtherServiceInformation ctctInformation = new OtherServiceInformation();
            ctctInformation.setCode("OTHS");
            ctctInformation.setText(ctct);
            ctctInformation.setAirline(airline);
            otherServiceInformations.getOtherServiceInformation().add(ctctInformation);
            // CTCM必须关联旅客,默认关联第一位乘机人(如果有CTCT项,CTCM项可不填)
	        OtherServiceInformation ctcmInformation = new OtherServiceInformation();
	        ctcmInformation.setAirline(airline);
	        ctcmInformation.setCode("OTHS");
	        ctcmInformation.setText(ctcm);
	        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
	        travelerRefNumber.setRPH("1");
            ctcmInformation.getTravelerRefNumber().add(travelerRefNumber);
	        otherServiceInformations.getOtherServiceInformation().add(ctcmInformation);
            specialReqDetails.setOtherServiceInformations(otherServiceInformations);
            // SSR:儿童备注生日信息
            SpecialReqDetails.SpecialServiceRequests specialServiceRequests = new SpecialReqDetails.SpecialServiceRequests();
            for (AirTraveler airTraveler : airTravelers) {
                if (airTraveler.getPassengerTypeCode().equals(PassengerType.CHD)) {
                    // 备注生日信息
                    SpecialServiceRequest specialServiceRequest = new SpecialServiceRequest();
                    specialServiceRequest.setSSRCode("CHLD");
                    specialServiceRequest.setServiceQuantity(new BigDecimal(1));
                    specialServiceRequest.setStatus("HK");
                    specialServiceRequest.setAirline(airline);
                    specialServiceRequest.setText(
                            DateTimeUtils.str2strdate(rphBirthdayMap.get(airTraveler.getTravelerRefNumber().getRPH())));
                    specialServiceRequest.getTravelerRefNumber().add(airTraveler.getTravelerRefNumber());
                    specialServiceRequests.getSpecialServiceRequest().add(specialServiceRequest);
                    // 备注其他项OTHS：成人PNR
                    String adtPNR = null;
                    String[] adtSeatCodes = null;
                    if (null != bookingData.getPnrExternalInfo()) {
                        adtPNR = bookingData.getPnrExternalInfo().getAdultPnr();
                        adtSeatCodes = bookingData.getPnrExternalInfo().getAdultSeatCode().split("#");
                    }
                    SpecialServiceRequest ssrOthsAdtPNR = null;
                    for (int i = 0; i < chdSsrOthsTextList.size(); i++) {
                        StringBuffer ssrOthsAdtText = chdSsrOthsTextList.get(i);
                        String adtSeatCodeString = "";
                        if (null != adtSeatCodes && i < adtSeatCodes.length) {
                            adtSeatCodeString = adtSeatCodes[i];
                        }
                        ssrOthsAdtPNR = new SpecialServiceRequest();
                        ssrOthsAdtPNR.setSSRCode("OTHS");
                        ssrOthsAdtPNR.setAirline(airline);
                        ssrOthsAdtPNR.setText("ADT" + " " + adtPNR + " " + ssrOthsAdtText + adtSeatCodeString);
                        specialServiceRequests.getSpecialServiceRequest().add(ssrOthsAdtPNR);
                    }
                }
            }
            specialReqDetails.setSpecialServiceRequests(specialServiceRequests);
            SpecialReqDetails.SpecialRemarks specialRemarks = new SpecialReqDetails.SpecialRemarks();
            specialRemarks.getSpecialRemark().addAll(creatAuthorizeRemarks(bookingData));
            specialReqDetails.setSpecialRemarks(specialRemarks);
            travelerInfo.setSpecialReqDetails(specialReqDetails);
            travelerInfo.getAirTraveler().addAll(airTravelers);
            // 6.判断缺口程，是否自动添加地面段
            boolean autoARNKInd = false;
            if (bookingData.getSegments().size() > 1) {
                for (int i = 0; i < bookingData.getSegments().size() - 1; i++) {
                    Segment currentFlight = bookingData.getSegments().get(i);
                    Segment nextFlight = bookingData.getSegments().get(i + 1);
                    String currentDstCode = currentFlight.getDstAirportIataCode();
                    String nextOrgCode = nextFlight.getOrgAirportIataCode();
                    if (!currentDstCode.equals(nextOrgCode)) {
                        autoARNKInd = true;
                        break;
                    }
                }
            }
            // 7.组装airbook请求对象
            OTAAirBookRQ airBookRQ = new OTAAirBookRQ();
            POS pos = new POS();
            Source source = new Source();
            source.setPseudoCityCode(bookingData.getOccupyOfficeNo());
            pos.getSource().add(source);
            airBookRQ.setAirItinerary(airItinerary);
            airBookRQ.setTicketing(ticketing);
            airBookRQ.setTPAExtensions(tpaExtensions);
            airBookRQ.setTravelerInfo(travelerInfo);
            airBookRQ.setPOS(pos);
            airBookRQ.setAutoARNKInd(autoARNKInd);
            return airBookRQ;
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_PLUS_TO_REQ, new Object[] { IbePlusIntf.AIR_BOOK }, e);
        }
    }

    /**
     * 给office号授权
     * @param bookingData
     * @return
     */
    private List<SpecialRemark> creatAuthorizeRemarks(BookingData bookingData) {
        List<SpecialRemark> remarks = new ArrayList<SpecialRemark>();
        // 给供应商office号授权
        if (StringUtils.isNotBlank(bookingData.getVendorOfficeNo())
                && !bookingData.getVendorOfficeNo().equals(bookingData.getOccupyOfficeNo())) {
            remarks.add(getAuthorizeRemark(bookingData.getVendorOfficeNo()));
        }
        // 给出票office号授权
        if (StringUtils.isNotBlank(bookingData.getEtdzOfficeNo())
                && !bookingData.getEtdzOfficeNo().equals(bookingData.getOccupyOfficeNo())) {
            remarks.add(getAuthorizeRemark(bookingData.getEtdzOfficeNo()));
        }
        // 给途牛office号授权
        if (!bookingData.getOccupyOfficeNo().equals(systemConfig.getIbeplusOfficeNo())) {
            remarks.add(getAuthorizeRemark(systemConfig.getIbeplusOfficeNo()));
        }
        return remarks;
    }

    private SpecialRemark getAuthorizeRemark(String officeNo) {
        SpecialRemark specialRemark = new SpecialRemark();
        String rmkTxt = "TJ AUTH " + officeNo + "/T";
        specialRemark.setText(rmkTxt);
        return specialRemark;
    }

    /**
     * 计算出票时限
     *
     * @param departureDateTimeString yyyy-MM-dd HH:mm
     * @return yyyy-MM-ddTHH:mm:ss
     * @throws ParseException
     */
    private String calculateTicketTimeLimit(String departureDateTimeString) throws ParseException {
        Date departureDateTime = DateTimeUtils.parse(departureDateTimeString, DateTimeUtils.DATETIME_M);
        Date onedate = DateTimeUtils.addDays(new Date(), 1);
        Date ticketTimeLimit = null;
        if (departureDateTime.compareTo(onedate) > 0) {
            ticketTimeLimit = onedate;
        } else {
            ticketTimeLimit = departureDateTime;
        }
        return DateTimeUtils.formatDate(ticketTimeLimit, DateTimeUtils.DATETIME_PATTERN).replace(" ", "T");
    }
}
