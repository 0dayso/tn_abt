package com.tuniu.abt.service.issue.travelsky.module;

import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.issue.response.FlightInfo;
import com.tuniu.abt.intf.dto.issue.response.*;
import com.tuniu.abt.intf.dto.issue.response.PriceInfo;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.utils.DateTimeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lanlugang on 2016/5/22.
 */
@Component
public class TravelSkyTicketInfoHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelSkyTicketInfoHandler.class);

    @Resource
    private TravelSkyInterface travelSkyInterface;

    public void rtTicketInfo(PnrInfo pnrInfo) {
        String pnr = pnrInfo.getOrderId();
        try {
            OTAAirResRetRS.AirResRet airResRet = travelSkyInterface.rt(pnr);
            boolean isIssued = checkTicketingResult(pnrInfo, airResRet);
            if (!isIssued) {
                return;
            }
            Map<String, PassengerInfo> psgInfoMap = parsePassengerInfo(airResRet);
            Map<String, FlightInfo> flightInfoMap = parseFlightInfo(airResRet.getFlightSegments());
            /**填充票号信息 */
            fillTicketNos(pnrInfo, psgInfoMap, flightInfoMap, airResRet.getSpecialServiceRequest());
            /**填充票价信息 */
            List<PassengerInfo> passengerInfos = new ArrayList<>();
            psgInfoMap.values().forEach(psgInfo -> passengerInfos.add(psgInfo));
            fillPriceDetail(pnrInfo, passengerInfos, airResRet.getFN());
        } catch (Exception e) {
            LOGGER.error("pnr["+pnr+"]获取票号信息失败,"+e.getMessage(), e);
            setPnrInfoFalse(pnrInfo, TicketEx.TS_ISSUED_GET_TICKET_FAILED, "pnr["+pnr+"]获取票号信息失败,"+e.getMessage());
        }
    }

    private void fillTicketNos(PnrInfo pnrInfo, Map<String, PassengerInfo> psgInfoMap,
                               Map<String, FlightInfo> flightInfoMap, List<SpecialServiceRequest> ssrList) {
        Map<String, TicketInfo> ticketInfoMap = new HashMap<>();
        for (SpecialServiceRequest ssr : ssrList) {
            if ("TKNE".equals(ssr.getSSRCode())) {
                String[] array = ssr.getText().trim().replaceAll("\\s{1,}", " ").split(" ");
                String flightNo = ssr.getAirline().getCode() + array[4];
                String[] subArray = array[6].split("/");
                String ticketNo = subArray[0];
                String psgInfoKey = subArray[2];
                if (subArray[0].startsWith("INF")) {
                    ticketNo = subArray[0].substring(3);
                    psgInfoKey += "INF";
                }
                TicketInfo ticketInfo = null;
                if (ticketInfoMap.containsKey(ticketNo)) {
                    ticketInfo = ticketInfoMap.get(ticketNo);
                } else {
                    ticketInfo = new TicketInfo();
                    ticketInfo.setTicketNo(ticketNo);
                    ticketInfoMap.put(ticketNo, ticketInfo);
                    pnrInfo.getTickets().add(ticketInfo);
                }
                PassengerInfo psgInfo = psgInfoMap.get(psgInfoKey);
                if (null != psgInfo) {
                    ticketInfo.getPassengers().add(psgInfo);
                } else {
                    setPnrInfoFalse(pnrInfo, TicketEx.TS_ISSUED_PSG_INFO_MISSED,
                            "票号["+ticketNo+"]关联的人员信息缺失");
                }
                FlightInfo flightInfo = flightInfoMap.get(flightNo);
                if (null != flightInfo) {
                    ticketInfo.getFlights().add(flightInfo);
                } else {
                    setPnrInfoFalse(pnrInfo, TicketEx.TS_ISSUED_FLIGHT_INFO_MISSED,
                            "票号["+ticketNo+"]关联的航班信息缺失");
                }
            }
        }
    }

    private void fillPriceDetail(PnrInfo pnrInfo, List<PassengerInfo> passengerInfos, List<FN> fnList) {
        Map<String, PriceInfo> priceInfoMap = parsePriceInfo(fnList);
        int[] passengerCount = getPassengerCount(passengerInfos);
        PriceDetail priceDetail = pnrInfo.getPriceDetail();
        priceDetail.setAdultNum(passengerCount[0]);
        priceDetail.setChildNum(passengerCount[1]);
        priceDetail.setBabyNum(passengerCount[2]);
        PriceInfo priceInfo = priceInfoMap.get("DEFAULT");
        if (null == priceInfo) {
            setPnrInfoFalse(pnrInfo, TicketEx.TS_ISSUED_PRICE_INFO_MISSED,
                    "pnr["+pnrInfo.getOrderId()+"]关联的运价信息缺失");
            return;
        }
        if (priceDetail.getAdultNum() > 0) {
            priceDetail.setAdultPrice(priceInfo);
        }
        if (priceDetail.getChildNum() > 0) {
            priceDetail.setChildPrice(priceInfo);
        }
        if (priceDetail.getBabyNum() > 0) {
            PriceInfo babyPrice = priceInfoMap.get(BizzConstants.PSG_TYPE_CODE_INF);
            if (null == babyPrice) {
                setPnrInfoFalse(pnrInfo, TicketEx.TS_ISSUED_PRICE_INFO_MISSED,
                        "pnr["+pnrInfo.getOrderId()+"]关联的婴儿运价信息缺失");
                return;
            }
            priceDetail.setBabyPrice(babyPrice);
        }
    }

    private int[] getPassengerCount(List<PassengerInfo> passengerInfos) {
        int [] count = new int[3];
        int adultNum = 0;
        int childNum = 0;
        int babyNum = 0;
        for (PassengerInfo passengerInfo : passengerInfos) {
            if (passengerInfo.getPassengerType() == BizzConstants.PERSON_TYPE_ADULT) {
                adultNum++;
            } else if (passengerInfo.getPassengerType() == BizzConstants.PERSON_TYPE_CHILD) {
                childNum++;
            } else if (passengerInfo.getPassengerType() == BizzConstants.PERSON_TYPE_BABY) {
                babyNum++;
            }
        }
        count[0] = adultNum;
        count[1] = childNum;
        count[2] = babyNum;
        return count;
    }

    private static Map<String, PriceInfo> parsePriceInfo(List<FN> fnList) {
        Map<String, PriceInfo> priceInfoMap = new HashMap<>();
        for (FN fn : fnList) {
            PriceInfo priceInfo = parseFnToPriceInfo(fn.getText());
            if (fn.getText().contains("/IN/")) {
                priceInfoMap.put(BizzConstants.PSG_TYPE_CODE_INF, priceInfo);
            } else {
                priceInfoMap.put("DEFAULT", priceInfo);
            }
        }
        return priceInfoMap;
    }

    private static com.tuniu.abt.intf.dto.issue.response.PriceInfo parseFnToPriceInfo(String text) {
        PriceInfo priceInfo = new PriceInfo();
        String[] items = text.trim().replaceAll("\\s{1,}", "").split("/");
        for (String item : items) {
            if (item.startsWith("FCNY")) {
                priceInfo.setFloorPrice(findBigDecimalValue(item));
            } else if (item.startsWith("SCNY") && priceInfo.getFloorPrice().intValue() == 0) {
                priceInfo.setFloorPrice(findBigDecimalValue(item));
            } else if (item.endsWith("CN")) {
                priceInfo.setAirportTax(findBigDecimalValue(item));
            } else if (item.endsWith("YQ")) {
                priceInfo.setFuelSurcharge(findBigDecimalValue(item));
            }
        }
        priceInfo.setOrgPrice(priceInfo.getFloorPrice());
        priceInfo.setSalePrice(priceInfo.getFloorPrice());
        return priceInfo;
    }

    private static BigDecimal findBigDecimalValue(String str) {
        Pattern p = Pattern.compile("(\\d+\\.?\\d*)");
        Matcher m = p.matcher(str);
        if (m.find()) {
            double value = Double.valueOf(m.group(1));
            return BigDecimal.valueOf(value);
        }
        return new BigDecimal(0);
    }

    private static Map<String, FlightInfo> parseFlightInfo(List<FlightSegments2> flightSegments) throws ParseException {
        Map<String, FlightInfo> flightMap = new HashMap<>();
        int rph = 0;
        for (FlightSegments2 segments : flightSegments) {
            for (FlightSegment2 segment : segments.getFlightSegment()) {
                if (SegmentType.NORMAL.equals(segment.getSegmentType())) {
                    FlightInfo flightInfo = new FlightInfo();
                    flightInfo.setRph(++rph);
                    String flightNo = segment.getMarketingAirline().getCode() + segment.getFlightNumber();
                    flightInfo.setFlightNo(flightNo);
                    BookingClassAvail bookingClassAvail = segment.getBookingClassAvail().get(0);
                    String seatCode = bookingClassAvail.getResBookDesigCode();
                    if (CollectionUtils.isNotEmpty(bookingClassAvail.getSubClass())) {
                        seatCode = bookingClassAvail.getSubClass().get(0);
                    }
                    flightInfo.setSeatCode(seatCode);
                    flightInfo.setOrgAirportCode(segment.getDepartureAirport().getLocationCode());
                    flightInfo.setDstAirportCode(segment.getArrivalAirport().getLocationCode());
                    Date departureTime = DateTimeUtils.parse(segment.getDepartureDateTime().replace("T", " "), DateTimeUtils.DATETIME_PATTERN);
                    flightInfo.setDepartureTime(departureTime);
                    flightMap.put(flightNo, flightInfo);
                }
            }
        }
        return flightMap;
    }

    private static Map<String, PassengerInfo> parsePassengerInfo(OTAAirResRetRS.AirResRet airResRet) {
        Map<String, PassengerInfo> psgMap = new HashMap<>();
        for (AirTraveler2 airTraveler : airResRet.getAirTraveler()) {
            String key = "P";
            PassengerInfo passengerInfo = new PassengerInfo();
            passengerInfo.setPassengerName(airTraveler.getPersonName().get(0).getSurname());
            if (PassengerType.ADT.equals(airTraveler.getPassengerTypeQuantity().getCode())) {
                key += airTraveler.getRPH();
                passengerInfo.setPassengerType(BizzConstants.PERSON_TYPE_ADULT);
            } else if (PassengerType.CHD.equals(airTraveler.getPassengerTypeQuantity().getCode())) {
                key += airTraveler.getRPH();
                passengerInfo.setPassengerType(BizzConstants.PERSON_TYPE_CHILD);
            } else if (PassengerType.INF.equals(airTraveler.getPassengerTypeQuantity().getCode())) {
                key = airTraveler.getTravelerRefNumber().getInfantTravelerRPH() + PassengerType.INF.value();
                passengerInfo.setPassengerType(BizzConstants.PERSON_TYPE_BABY);
                passengerInfo.setIdNumber(airTraveler.getBirthDate().replace("-", ""));
            }
            psgMap.put(key, passengerInfo);
        }
        for (SpecialServiceRequest ssr : airResRet.getSpecialServiceRequest()) {
            if ("FOID".equals(ssr.getSSRCode())) {
                String idNumber = parseIdNumber(ssr.getText());
                psgMap.get(ssr.getTravelerRefNumber().get(0).getRPH()).setIdNumber(idNumber);
            }
        }
        return psgMap;
    }

    private static String parseIdNumber(String text) {
        Pattern p = Pattern.compile("NI(.*?)/P");
        Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    private boolean checkTicketingResult(PnrInfo pnrInfo, OTAAirResRetRS.AirResRet airResRet) {
        if (airResRet.getTicketing().isEmpty() || !airResRet.getTicketing().get(0).isIsIssued()) {
            setPnrInfoFalse(pnrInfo, TicketEx.TS_NOT_ISSUED, pnrInfo.getOrderId()+"未出票");
            return false;
        }
        if (airResRet.getTicketItemInfo().isEmpty() || tkneIsEmpty(airResRet.getSpecialServiceRequest())) {
            setPnrInfoFalse(pnrInfo, TicketEx.TS_ISSUED_NO_TICKET, pnrInfo.getOrderId()+"未获取到票号信息");
            return false;
        }
        if (airResRet.getAirTraveler().size() > airResRet.getTicketItemInfo().size()) {
            setPnrInfoFalse(pnrInfo, TicketEx.TS_ISSUED_TICKET_MISSED, pnrInfo.getOrderId()+"票号信息缺失");
            return false;
        }
        return true;
    }

    private boolean tkneIsEmpty(List<SpecialServiceRequest> specialServiceRequest) {
        for (SpecialServiceRequest serviceRequest : specialServiceRequest) {
            if ("TKNE".equals(serviceRequest.getSSRCode())) {
                return false;
            }
        }
        return true;
    }

    private void setPnrInfoFalse(PnrInfo pnrInfo, int errCode, String errMsg) {
        pnrInfo.setIssueFlag(false);
        pnrInfo.setErrorCode(errCode);
        pnrInfo.setMsg(errMsg);
    }

    public void addAdtTicketNoForChdPnr(List<PnrInfo> pnrInfoList) {
        String adtTicketNo = null;
        String airlineCode = null;
        boolean hasChd = false;
        Map<String, Integer> pnrChdNumMap = new HashMap<>();
        Map<String, PnrInfo> pnrInfoMap = new HashedMap();
        for (PnrInfo pnrInfo : pnrInfoList) {
            pnrInfoMap.put(pnrInfo.getOrderId(), pnrInfo);
            for (TicketInfo ticketInfo : pnrInfo.getTickets()) {
                if (null == airlineCode) {
                    airlineCode = ticketInfo.getFlights().get(0).getFlightNo().substring(0, 2);
                }
                for (PassengerInfo passengerInfo : ticketInfo.getPassengers()) {
                    if (passengerInfo.getPassengerType() == BizzConstants.PERSON_TYPE_ADULT) {
                        if (null == adtTicketNo) {
                            adtTicketNo = ticketInfo.getTicketNo();
                        }
                    } else if (passengerInfo.getPassengerType() == BizzConstants.PERSON_TYPE_CHILD) {
                        if (pnrChdNumMap.containsKey(pnrInfo.getOrderId())) {
                            int chdNum = pnrChdNumMap.get(pnrInfo.getOrderId()) + 1;
                            pnrChdNumMap.put(pnrInfo.getOrderId(), chdNum);
                        } else {
                            pnrChdNumMap.put(pnrInfo.getOrderId(), 1);
                        }
                        hasChd = true;
                    }
                }
            }
        }
        if (hasChd && null != adtTicketNo && null != airlineCode) {
            adtTicketNo = adtTicketNo.replace("-", "");
            for (Map.Entry<String, Integer> entry : pnrChdNumMap.entrySet()) {
                String pnr = entry.getKey();
                for (int i = 1; i <= entry.getValue(); i++) {
                    String osiTxt = "ADULT" + adtTicketNo + "/P" + i;
                    boolean success = addOsi(pnr, airlineCode, osiTxt);
                    if (!success) {
                        setPnrInfoFalse(pnrInfoMap.get(pnr), TicketEx.TS_ISSUED_ADD_OSI_FAILED, "pnr["+pnr+"]备注成人票号信息失败");
                    }
                }
            }
        }
    }

    private boolean addOsi(String pnr, String airlineCode, String osiTxt) {
        try {
            travelSkyInterface.addOsiInfo(pnr, airlineCode, osiTxt);
            return true;
        } catch (Exception e) {
            LOGGER.error(pnr+"备注成人票号["+osiTxt+"]失败"+e.getMessage(), e);
            return false;
        }
    }

}
