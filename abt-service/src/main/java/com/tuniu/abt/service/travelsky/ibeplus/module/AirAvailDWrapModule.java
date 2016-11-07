package com.tuniu.abt.service.travelsky.ibeplus.module;

import com.travelsky.espeed.*;
import com.tuniu.abt.service.travelsky.dto.AirAvailResult;
import com.tuniu.abt.service.travelsky.dto.AvFlightSegment;
import com.tuniu.abt.service.travelsky.dto.AvOrgDstOption;
import com.tuniu.abt.service.travelsky.dto.AvSeatStatus;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusInterfaceService;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.adapter.flightTicket.vo.connector.AirAvailRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chengyao on 2016/2/22.
 */
@Service
public class AirAvailDWrapModule {
    
    @Resource
    private SystemConfig systemConfig;

    @Resource
    private IbePlusInterfaceService ibePlusInterfaceService;

    /**
     * 
     * Description: 余位查询<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param availRequest
     * @return
     * @throws Exception <br>
     */
    public AirAvailResult airAvailD(AirAvailRequest availRequest) {
        OTAAirAvailRQ airAvailRQ = this.convertAirAvailRQ(availRequest);
        OTAAirAvailRS reply = ibePlusInterfaceService.airAvailD(airAvailRQ);
        return convertToAirAvailResult(availRequest, reply);
    }

    private OTAAirAvailRQ convertAirAvailRQ(AirAvailRequest availRequest) {
        OTAAirAvailRQ airAvailRQ = new OTAAirAvailRQ();
        POS pos = new POS();
        Source source = new Source();
        source.setPseudoCityCode(systemConfig.getIbeplusOfficeNo());
        pos.getSource().add(source);
        airAvailRQ.setPOS(pos);

        TravelPreferences travelPreferences = new TravelPreferences();
        travelPreferences.setETicketDesired(availRequest.isEticket());// 电子票
        if (!availRequest.isDirect()) { // 联程
            FlightTypePref flightTypePref = new FlightTypePref();
            flightTypePref.setFlightType(FlightType.CONNECTION);
            travelPreferences.setFlightTypePref(flightTypePref);
        }
        airAvailRQ.setTravelPreferences(travelPreferences);

        if (StringUtils.isNotBlank(availRequest.getAirline())) {
            SpecificFlightInfo specificFlightInfo = new SpecificFlightInfo();
            Airline airLine = new Airline();
            airLine.setCode(availRequest.getAirline());
            specificFlightInfo.getAirline().add(airLine);
            airAvailRQ.setSpecificFlightInfo(specificFlightInfo);
        }
        OriginDestinationInformation info = new OriginDestinationInformation();
        info.setDepartureDateTime(availRequest.getDepartureDate());
        OriginLocation originLocation = new OriginLocation();
        originLocation.setLocationCode(availRequest.getOrgCityIataCode());
        info.setOriginLocation(originLocation);
        DestinationLocation destinationLocation = new DestinationLocation();
        destinationLocation.setLocationCode(availRequest.getDstCityIataCode());
        info.setDestinationLocation(destinationLocation);
        airAvailRQ.setOriginDestinationInformation(info);

        return airAvailRQ;
    }
    
    /**
     * 
     * Description: 组装airAvail结果<br> 
     *  
     * @author lanlugang<br>
     * @taskId AIR-227<br>
     * @param availRequest
     * @param airAvailRS
     * @return <br>
     */
    private AirAvailResult convertToAirAvailResult(AirAvailRequest availRequest, OTAAirAvailRS airAvailRS) {
        
        String orgCityIataCode = availRequest.getOrgCityIataCode();
        String dstCityIataCode = availRequest.getDstCityIataCode();
        //
        OriginDestinationInformation oriDestInfo = airAvailRS.getOriginDestinationInformation();
        AirAvailResult airAvailResult = new AirAvailResult();
        airAvailResult.setOrgCityIataCode(orgCityIataCode);
        airAvailResult.setDstCityIataCode(dstCityIataCode);
        airAvailResult.setDepartureDate(oriDestInfo.getDepartureDateTime());
        //
        List<OriginDestinationOption> orgDstOptionList = oriDestInfo
                .getOriginDestinationOptions().getOriginDestinationOption();
        AvOrgDstOption avOrgDstOption = null;
        for (OriginDestinationOption orgDstOption : orgDstOptionList) {
            avOrgDstOption = new AvOrgDstOption();
            airAvailResult.getOrgDstOptions().add(avOrgDstOption);
            AvFlightSegment avSegment = null;
            for (FlightSegment segmentShop : orgDstOption.getFlightSegment()) {
                avSegment = new AvFlightSegment();
                avOrgDstOption.getSegments().add(avSegment);
                avSegment.setAirline(segmentShop.getMarketingAirline().getCode());
                avSegment.setFlightNo(segmentShop.getMarketingAirline().getCode()
                                      + segmentShop.getFlightNumber());
                avSegment.setOrgAirportIataCode(segmentShop.getDepartureAirport().getLocationCode());
                avSegment.setDstAirportIataCode(segmentShop.getArrivalAirport().getLocationCode());
                avSegment.setDepartureDateTime(segmentShop.getDepartureDateTime().replaceAll("T", " "));
                AvSeatStatus avSeatStatus = null;
                for (BookingClassAvail bookingClassAvail : segmentShop.getBookingClassAvail()) {
                    String seatStatus = bookingClassAvail.getResBookDesigQuantity();
                    // 过滤掉舱位状态不是A和数字的舱位
                    if (seatStatus.matches("[1-9A]")) {
                        avSeatStatus = new AvSeatStatus();
                        avSegment.getSeatStatus().add(avSeatStatus);
                        avSeatStatus.setSeatCode(bookingClassAvail.getResBookDesigCode());
                        if (seatStatus.equals("A")) {
                            avSeatStatus.setStatus(">9");
                        } else {
                            avSeatStatus.setStatus(seatStatus);
                        }
                    }
                }
            }
            
        }
        return airAvailResult;
    }

}
