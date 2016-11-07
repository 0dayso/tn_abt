package com.tuniu.abt.service.travelsky.ibeplus.converter;

import com.travelsky.espeed.*;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightAirCompanyFDPrice;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightCabinFDPrice;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightFDPriceRes;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by chengyao on 2016/4/16.
 */
public class IbePlusPriceConverter {

    public static FlightFDPriceRes toResponse(FdPriceParam fdPriceParam, OTAAirFareDisplayRS otaAirFareDisplayRS) {
        FlightFDPriceRes flightFDPriceRes = new FlightFDPriceRes();
        flightFDPriceRes.setOrgCityIataCode(fdPriceParam.getOrgCityIataCode());
        flightFDPriceRes.setDstCityIataCode(fdPriceParam.getDstCityIataCode());
        flightFDPriceRes.setDepartureDate(fdPriceParam.getDepartureDate());
        flightFDPriceRes.getFdPriceList().addAll(convertToFdPrices(otaAirFareDisplayRS));
        return flightFDPriceRes;
    }


    public static OTAAirFareDisplayRQ toRequest(FdPriceParam fdPriceParam, String officeNo) {
        OTAAirFareDisplayRQ request = new OTAAirFareDisplayRQ();

        request.setPOS(new POS());
        request.getPOS().getSource().add(new Source());
        request.getPOS().getSource().get(0).setPseudoCityCode(officeNo);

        OriginDestinationInformation originDestinationInformation = new OriginDestinationInformation();
        OriginDestinationOptions originDestinationOptions = new OriginDestinationOptions();
        originDestinationInformation.setOriginDestinationOptions(originDestinationOptions);
        List<OriginDestinationOption> originDestinationOptionList = originDestinationOptions
                .getOriginDestinationOption();
        //目前只有单程
        OriginDestinationOption originDestinationOption = new OriginDestinationOption();
        originDestinationOption.setRPH("1");
        List<FlightSegment> flightSegmentList = originDestinationOption.getFlightSegment();
        FlightSegment flightSegment = new FlightSegment();
        flightSegment.setDepartureDateTime(fdPriceParam.getDepartureDate() + "T00:00:00");
        DepartureAirport departureAirport = new DepartureAirport();
        departureAirport.setLocationCode(fdPriceParam.getOrgCityIataCode());
        flightSegment.setDepartureAirport(departureAirport);
        ArrivalAirport arrivalAirport = new ArrivalAirport();
        arrivalAirport.setLocationCode(fdPriceParam.getDstCityIataCode());
        flightSegment.setArrivalAirport(arrivalAirport);
        MarketingAirline marketingAirline = new MarketingAirline();
        if (StringUtils.isNotBlank(fdPriceParam.getAirlineCompany())) {
            marketingAirline.setCode(fdPriceParam.getAirlineCompany());
        } else {
            marketingAirline.setCode("");
        }
        flightSegment.setMarketingAirline(marketingAirline);
        flightSegmentList.add(flightSegment);
        originDestinationOptionList.add(originDestinationOption);
        request.setOriginDestinationInformation(originDestinationInformation);
        TravelerInfoSummary travelerInfoSummary = new TravelerInfoSummary();
        travelerInfoSummary.setGroupInd(false);
        List<AirTravelerAvail> airTravelerAvailList = travelerInfoSummary.getAirTravelerAvail();
        AirTravelerAvail airTravelerAvail = new AirTravelerAvail();
        List<PassengerTypeQuantity> passengerTypeQuantityList = airTravelerAvail.getPassengerTypeQuantity();
        PassengerTypeQuantity passengerTypeQuantity = new PassengerTypeQuantity();
        passengerTypeQuantity.setCode(PassengerType.ADT);
        passengerTypeQuantityList.add(passengerTypeQuantity);
        airTravelerAvailList.add(airTravelerAvail);
        request.setTravelerInfoSummary(travelerInfoSummary);
        return request;
    }

    private static List<FlightAirCompanyFDPrice> convertToFdPrices(OTAAirFareDisplayRS airFareDisplayDRS) {
        List<FlightAirCompanyFDPrice> fdPrices = new ArrayList<FlightAirCompanyFDPrice>();
        Map<String, FlightAirCompanyFDPrice> airComFdPriceMap = new HashMap<String, FlightAirCompanyFDPrice>();
        FlightAirCompanyFDPrice fdPrice = null;
        for (AirItineraryPricingInfo pricingInfo : airFareDisplayDRS.getAirItineraryPricingInfo()) {
            ItinTotalFare itinTotalFare = findItinTotalFare(pricingInfo);
            if (itinTotalFare == null ||
                    itinTotalFare.getPricingInfos() == null ||
                    itinTotalFare.getPricingInfos().getSinglePrice() == null
                    || itinTotalFare.getPricingInfos().getSinglePrice().intValue() == 0) {
                continue;
            }
            Double singlePrice = itinTotalFare.getPricingInfos().getSinglePrice().doubleValue();
            FareInfo fareInfo = pricingInfo.getFareInfos().getFareInfo().get(0);
            String airlineCode = fareInfo.getMarketingAirline().get(0).getCode();
            if (airComFdPriceMap.containsKey(airlineCode)) {
                fdPrice = airComFdPriceMap.get(airlineCode);
            } else {
                fdPrice = new FlightAirCompanyFDPrice();
                fdPrice.setAirCompanyIataCode(airlineCode);
                airComFdPriceMap.put(airlineCode, fdPrice);
                fdPrices.add(fdPrice);
            }

            FlightCabinFDPrice price = new FlightCabinFDPrice();
            price.setFdPrice(singlePrice);
            price.setSeatClass(fareInfo.getCabin());
            price.setSeatCode(fareInfo.getResBookDesigCode());
            if ("XO".equals(airlineCode)) {// 新疆航空fareBaseCode中没有子舱位
                price.setSubSeatCode(fareInfo.getResBookDesigCode());
            } else {
                String fareBaseCode = fareInfo.getFareBasisCodes().getFareBasisCode().get(0);
                if (fareBaseCode.matches("^[A-Z]{2}.*")) {
                    if (fareBaseCode.startsWith(fareInfo.getCabin())
                            || fareBaseCode.startsWith(fareInfo.getResBookDesigCode())) {
                        fareBaseCode = fareBaseCode.substring(1);
                    } else {
                        continue;
                    }
                }
                if (fareBaseCode.matches("^[A-Z][1-9].*")) {
                    fareBaseCode = fareBaseCode.substring(0, 2);
                } else {
                    fareBaseCode = fareBaseCode.substring(0, 1);
                }
                price.setSubSeatCode(fareBaseCode);
            }
            fdPrice.getCabinFDPricelist().add(price);
        }
        // 同航司同舱位如果有多个价格，只取最低价
        filterInvalidPrice(fdPrices);
        return fdPrices;
    }

    private static void filterInvalidPrice(List<FlightAirCompanyFDPrice> fdPrices) {
        for (FlightAirCompanyFDPrice fdPrice : fdPrices) {
            Map<String, FlightCabinFDPrice> cabinFDPriceMap = new HashMap<>();
            for (FlightCabinFDPrice flightCabinFDPrice : fdPrice.getCabinFDPricelist()) {
                String subSeatCode = flightCabinFDPrice.getSubSeatCode();
                if (cabinFDPriceMap.containsKey(subSeatCode)) {
                    FlightCabinFDPrice preCabinFDPrice = cabinFDPriceMap.get(subSeatCode);
                    if (flightCabinFDPrice.getFdPrice() < preCabinFDPrice.getFdPrice()) {
                        cabinFDPriceMap.put(subSeatCode, flightCabinFDPrice);
                    }
                } else {
                    cabinFDPriceMap.put(subSeatCode, flightCabinFDPrice);
                }
            }
            fdPrice.getCabinFDPricelist().clear();
            cabinFDPriceMap.values().forEach(p -> fdPrice.getCabinFDPricelist().add(p));
            Collections.sort(fdPrice.getCabinFDPricelist(), (o1, o2) -> -Double.compare(o1.getFdPrice(), o2.getFdPrice()));
        }
    }

    private static ItinTotalFare findItinTotalFare(AirItineraryPricingInfo pricingInfo) {
        List<ItinTotalFare> itinTotalFareList = pricingInfo.getItinTotalFare();
        if (CollectionUtils.isEmpty(itinTotalFareList)) {
            return null;
        }
        if (itinTotalFareList.size() == 1) {
            return itinTotalFareList.get(0);
        }
        Collections.sort(itinTotalFareList,
                (o1, o2) -> o1.getPricingInfos().getSinglePrice().compareTo(o2.getPricingInfos().getSinglePrice()));
        return itinTotalFareList.get(0);
    }

}
