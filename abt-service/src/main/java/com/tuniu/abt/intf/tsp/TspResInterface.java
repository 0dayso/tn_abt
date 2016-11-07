package com.tuniu.abt.intf.tsp;

import com.tuniu.abt.intf.tsp.dto.res.*;
import com.tuniu.adapter.flightTicket.domain.*;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.vla.base.tsp.intf.TspInterface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 资源TSP接口
 *
 * Created by chengyao on 2016/1/15.
 */
@Component
public interface TspResInterface extends TspInterface {

    @TspMethod(serviceName = "RES.NM.CitySearchController.queryCityInfoBatch", method = "GET")
    List<ResDistrict> findDistrict(ResDistrictReq request);

    @TspMethod(serviceName = "RES.NM.FlightController.queryAirPorts", method = "GET")
    ResRowsResp<ResAirport> findAirport(ResAirPortReq request);

    @TspMethod(serviceName = "RES.NM.FilghtAirlineCompanyController.selAirlineCompanyBatch", method = "GET")
    ResRowsResp<ResAirlineCompany> findAirlineCompany(ResAirlineCompanyReq request);

    @TspMethod(serviceName = "RES.NM.FlightAirRouteController.selAirRouteBatch", method = "GET")
    ResRowsResp<ResAirRoute> findAirRoute(ResAirRouteReq request);

    @TspMethod(serviceName = "RES.NM.findCtripTerminals", method = "GET")
    List<ResCtripTerminal> findCtripTerminals();
    
    @TspMethod(serviceName = "RES.NM.FlightIndividualTravelerController.queryIndivVendorPriceByAirportFlightNo", method = "GET")
    List<FlightIndivVendorPrice> findFlightVendorPrices(ResFlightVendorPriceReq request);

    @TspMethod(serviceName = "RES.NM.FlightAdapterController.getCtripAllInfo")
    ResCtripSearchRelationResp getCabinPriceIndivPriceCtripRuleCtripSeatPrice(ResCtripSearchRelationReq request);

    @TspMethod(serviceName = "RES.NM.FlightAdapterController.getIndivCabinGroupByResIdDemestic")
    List<FlightIndivCabinGroupRes> getIndivCabinGroupByResIdDomestic(ResIdDateReq resIdReq);

    @TspMethod(serviceName = "RES.NM.FlightAdapterController.getSeatPriceByCondition")
    List<FlightIndivSeatPriceDomestic> getSeatPriceByCondition(FlightIndivSeatPriceDomestic condition);

    @TspMethod(serviceName = "RES.NM.FlightAdapterController.getIndivPriceInfo")
    List<FlightIndivFlightPrice> getIndivPriceInfo(ResPriceInfoReq request);

    @TspMethod(serviceName = "RES.NM.FlightAdapterController.getFirstIndivFlightByCondition")
    List<FlightIndivFlight> getFirstIndivFlightByCondition(ResFlightQueryReq request);

    @TspMethod(serviceName = "RES.NM.FlightAdapterController.getFirstIndivSeatByCondition")
    FlightIndivSeat getFirstIndivSeatByCondition(ResFlightQueryReq request);

    @TspMethod(serviceName = "RES.NM.FlightAdapterController.getIndivSeatById")
    FlightIndivSeat getIndivSeatById(ResIdReq request);

    @TspMethod(serviceName = "RES.NM.FlightAdapterController.getIndivCabinFareByFlightNos", method = "GET")
    List<FlightIndivCabinFare> findFlightCabinFares(ResFlightCabinfareReq request);



}
