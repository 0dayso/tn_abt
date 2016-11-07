package com.tuniu.abt.service.res;

import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.TspResInterface;
import com.tuniu.abt.intf.tsp.dto.res.*;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.adapter.flightTicket.domain.FlightIndivCabinFare;
import com.tuniu.adapter.flightTicket.domain.FlightIndivFlightPrice;
import com.tuniu.adapter.flightTicket.domain.FlightIndivSeat;
import com.tuniu.operation.platform.base.text.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chengyao on 2016/2/3.
 */
@Component
public class ResourceService {

    @Resource
    private ResourceBaseCacheService resourceBaseCacheService;
    @Resource
    private TspResInterface tspResInterface;

    public ResAirport findAirPort(String airPortCode) {
        ResAirport resAirport = resourceBaseCacheService.getAirport(airPortCode);
        if (resAirport == null || StringUtils.isEmpty(resAirport.getAirportCode())) {
            return null;
        }
        return resAirport;
    }

    public String findDistrictName(String iataCode) {
        ResDistrict resDistrict = findDistrict(iataCode);
        return resDistrict == null ? null : resDistrict.getName();
    }

    public ResDistrict findDistrict(String iataCode) {
        Map<String, ResDistrict> map = resourceBaseCacheService.getDistrictMap();
        return map.get(iataCode);
    }

    public FlightIndivSeat getIndivSeatByIdDemestic(int seatId) {
        return tspResInterface.getIndivSeatById(new ResIdReq(seatId));
    }

    public List<FlightIndivFlightPrice> getIndivPriceInfo(ResPriceInfoReq request) {
        return tspResInterface.getIndivPriceInfo(request);
    }

    public List<FlightIndivVendorPrice> findFlightVendorPrices(ResFlightVendorPriceReq request) {
        List<FlightIndivVendorPrice> datas = tspResInterface.findFlightVendorPrices(request);
        if (CollectionUtils.isEmpty(datas)) {
            throw new BizzException(BizzEx.TSP_RESOURCE_INTF_ERROR, "从资源系统获取航段价格信息为空");
        }
        return datas;
    }

    public List<FlightIndivCabinFare> findFlightCabinFares(ResFlightCabinfareReq request) {
        return tspResInterface.findFlightCabinFares(request);
    }

    public List<FlightIndivFlightPrice> getIndivPriceInfo(Segment segment, String passengerCode, Integer vendorId) {
        List<Segment> segmentList = new ArrayList<Segment>();
        segmentList.add(segment);
        return getIndivPriceInfo(segmentList, passengerCode, vendorId);
    }

    public List<FlightIndivFlightPrice> getIndivPriceInfo(List<Segment> segments, String passengerCode, Integer vendorId) {
        try {
            ResPriceInfoReq resPriceInfoReq = new ResPriceInfoReq();
            resPriceInfoReq.setVendorId(vendorId);
            resPriceInfoReq.setPassengerCode(passengerCode);
            resPriceInfoReq.setDepartureDate(segments.get(0).getDepartureDate());
            String flightKey = null;
            String cityKey = null;
            String timeSpan = null;
            for (int i = 0; i < segments.size(); i++) {
                if (i > 0) {
                    Date predate = DateTimeUtils.parseDate(segments.get(i-1).getDepartureDate());
                    Date curdate = DateTimeUtils.parseDate(segments.get(i).getDepartureDate());
                    int curTimeSpan = DateTimeUtils.getDayBetween(predate, curdate);
                    timeSpan = (timeSpan == null) ? String.valueOf(curTimeSpan)
                            : timeSpan + "#" + String.valueOf(curTimeSpan);
                }
                flightKey = (flightKey == null) ? segments.get(i).getFlightNo()
                        : flightKey + "#" + segments.get(i).getFlightNo();
                cityKey = (cityKey ==null) ? segments.get(i).getOrgCityIataCode() + "-" + segments.get(i).getDstCityIataCode()
                        : cityKey + "#" + segments.get(i).getOrgCityIataCode() + "-" + segments.get(i).getDstCityIataCode();
            }
            resPriceInfoReq.setFlightKey(flightKey);
            resPriceInfoReq.setCityKey(cityKey);
            if (null != timeSpan) {
                resPriceInfoReq.setTimeSpan(timeSpan);
            }
            return getIndivPriceInfo(resPriceInfoReq);
        } catch (Exception e) {
//            throw new BizzException(BizzEx.TSP_RESOURCE_INTF_EX, "从资源系统获取航段价格信息异常", e);
            return null;
        }
    }

}
