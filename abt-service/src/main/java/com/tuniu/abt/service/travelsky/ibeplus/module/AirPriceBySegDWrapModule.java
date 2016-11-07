package com.tuniu.abt.service.travelsky.ibeplus.module;

import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.PataBySegRes;
import com.tuniu.abt.service.travelsky.dto.PnrFareItem;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusInterfaceService;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lanlugang on 2016/4/23.
 */
@Service
public class AirPriceBySegDWrapModule {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private IbePlusInterfaceService ibePlusInterfaceService;

    /**
     * Description: IBEPLUS底层AirPriceBySegD接口<br>
     *
     * @param segmentList
     * @return <br>
     * @author lanlugang<br>
     * @taskId AIR-227<br>
     */
    public PataBySegRes<PricedItineraries2> airPriceBySegD(List<Segment> segmentList, String psgType) {
        OTAAirPriceBySegRQ request = convertToAirPriceRQ(segmentList, psgType);
        OTAAirPriceBySegRS airPriceRS = ibePlusInterfaceService.airPriceBySegD(request);
        PataBySegRes pataBySegRes = new PataBySegRes();
        pataBySegRes.setPsgType(psgType);
        pataBySegRes.setSegments(segmentList);
        pataBySegRes.setPataResult(airPriceRS.getPricedItineraries());
        pataBySegRes.setFareItem(fareItemConvertor(airPriceRS.getPricedItineraries()));
        pataBySegRes.setFareItems(fareItemsConvertor(airPriceRS.getPricedItineraries()));
        return pataBySegRes;
    }

    /**
     * Description: 组装OTAAirPriceRQ<br>
     *
     * @param segmentList
     * @param psgType
     * @return <br>
     * @author lanlugang<br>
     * @taskId AIR-227<br>
     */
    private OTAAirPriceBySegRQ convertToAirPriceRQ(List<Segment> segmentList, String psgType) {
        OTAAirPriceBySegRQ airPriceRQ = new OTAAirPriceBySegRQ();
        // office号
        POS pos = new POS();
        Source source = new Source();
        source.setPseudoCityCode(systemConfig.getIbeplusOfficeNo());
        pos.getSource().add(source);
        airPriceRQ.setPOS(pos);
        // 人员类型
        TravelerInfoSummary2 travelerInfoSummary = new TravelerInfoSummary2();
        AirTravelerAvail2 airTravelerAvail = new AirTravelerAvail2();
        PassengerTypeQuantity2 passengerTypeQuantity = new PassengerTypeQuantity2();
        passengerTypeQuantity.setCode(psgType);
        airTravelerAvail.getPassengerTypeQuantity().add(passengerTypeQuantity);
        travelerInfoSummary.getAirTravelerAvail().add(airTravelerAvail);
        airPriceRQ.setTravelerInfoSummary(travelerInfoSummary);
        // 航段信息
        AirItinerary2 airItinerary = new AirItinerary2();
        airPriceRQ.setAirItinerary(airItinerary);
        OriginDestinationOptions2 originDestinationOptions = new OriginDestinationOptions2();
        airItinerary.getOriginDestinationOptions().add(originDestinationOptions);
        OriginDestinationOption2 originDestinationOption = new OriginDestinationOption2();
        originDestinationOptions.getOriginDestinationOption().add(originDestinationOption);
        FlightSegment2 segmentVo = null;
        for (Segment flightSegment : segmentList) {
            segmentVo = new FlightSegment2();
            segmentVo.setFlightNumber(flightSegment.getFlightNo());
            segmentVo.setStatus("HK");
            segmentVo.setDepartureDateTime(
                    flightSegment.getDepartureDate() + "T" + flightSegment.getDepartureTime() + ":00");
            if (null != flightSegment.getArriveDate() && null != flightSegment.getArrivalTime() && !flightSegment
                    .getArriveDate().trim().isEmpty() && !flightSegment.getArrivalTime().trim().isEmpty()) {
                segmentVo.setArrivalDateTime(
                        flightSegment.getArriveDate() + "T" + flightSegment.getArrivalTime() + ":00");
            }
            // 出发机场
            DepartureAirport dAirport = new DepartureAirport();
            dAirport.setLocationCode(flightSegment.getOrgAirportIataCode());
            segmentVo.setDepartureAirport(dAirport);
            // 到达机场
            ArrivalAirport aAirport = new ArrivalAirport();
            aAirport.setLocationCode(flightSegment.getDstAirportIataCode());
            segmentVo.setArrivalAirport(aAirport);
            // 机型
            Equipment equipment = new Equipment();
            equipment.setAirEquipType(flightSegment.getPlaneType());
            segmentVo.setEquipment(equipment);
            // 舱位
            BookingClassAvail bookingClassAvail = new BookingClassAvail();
            // 舱位
            bookingClassAvail.setResBookDesigCode(flightSegment.getSeatCode().substring(0, 1));
            // 子舱位
            if (flightSegment.getSeatCode().matches("[A-Z][1-9]")) {
                bookingClassAvail.getSubClass().add(flightSegment.getSeatCode());
            }
            segmentVo.getBookingClassAvail().add(bookingClassAvail);
            originDestinationOption.getFlightSegment().add(segmentVo);
        }
        return airPriceRQ;
    }

    private PnrFareItem fareItemConvertor(PricedItineraries2 pricedItineraries) {
        List<PnrFareItem> fareItems = fareItemsConvertor(pricedItineraries);
        if (CollectionUtils.isNotEmpty(fareItems)) {
            Collections.sort(fareItems, (o1,o2)->Double.compare(o1.getFare(), o2.getFare()));
            return fareItems.get(0);
        } else {
            throw new BizzException(BizzEx.IBE_INTF_RESULT_NONE, "Pata查询航段价格为空，未获取到可用价格");
        }
    }
    private List<PnrFareItem> fareItemsConvertor(PricedItineraries2 pricedItineraries) {
        PricedItinerary2 pricedItinerary = pricedItineraries.getPricedItinerary().get(0);
        AirItineraryPricingInfo2 pricingInfo = pricedItinerary.getAirItineraryPricingInfo();
        FareInfos fareInfos = pricingInfo.getFareInfos();
        Map<String, String> fareBaseCodeMap = new HashMap<String, String>();
        for (FareInfo fareInfo : fareInfos.getFareInfo()) {
            List<String> fareBaseCode = fareInfo.getFareBasisCodes().getFareBasisCode();
            fareBaseCodeMap.put(fareInfo.getRPH(), fareBaseCode.get(0));
        }
        List<ItinTotalFare2> itinTotalFareList = pricingInfo.getItinTotalFare();
        List<PnrFareItem> fareItems = new ArrayList<PnrFareItem>();
        PnrFareItem fareItem = null;
        for (ItinTotalFare2 itinTotalFare : itinTotalFareList) {
            BaseFare baseFare = itinTotalFare.getBaseFare();
            if (null == baseFare || baseFare.getAmount() == null
                    || baseFare.getAmount().intValue() == 0) {
                continue;
            }
            fareItem = new PnrFareItem();
            double total = 0f;
            fareItem.setFare(baseFare.getAmount().doubleValue());
            total += baseFare.getAmount().doubleValue();
            for (Tax tax : itinTotalFare.getTaxes().getTax()) {
                if (("CN").equals(tax.getTaxCode())) {
                    fareItem.setAirPortTax(tax.getAmount().doubleValue());
                } else if (("YQ").equals(tax.getTaxCode())) {
                    fareItem.setFuelSurcharge(tax.getAmount().doubleValue());
                }
                total += tax.getAmount().doubleValue();
            }
            fareItem.setTotal(total);
            String fareBaseCode = null;
            for (FareInfoRef fareInfoRef : itinTotalFare.getFareInfoRef()) {
                String code = fareBaseCodeMap.get(fareInfoRef.getRPH());
                if (StringUtils.isNotBlank(code)) {
                    fareBaseCode = StringUtils.isBlank(fareBaseCode)
                                 ? code : fareBaseCode + "/" + code;
                }
            }
            fareItem.setFareBasisCode(fareBaseCode);
            fareItems.add(fareItem);
        }
        return fareItems;
    }

}
