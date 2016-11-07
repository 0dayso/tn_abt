package com.tuniu.aop.unittest.rest;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.intf.tsp.TspResInterface;
import com.tuniu.abt.intf.tsp.dto.res.*;
import com.tuniu.abt.rest.CommonRestClientService;
import com.tuniu.abt.service.res.ResourceBaseCacheService;
import com.tuniu.abt.service.res.ResourceService;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by chengyao on 2016/3/10.
 */
public class ResourceBaseTest extends BaseTest {

    @Resource
    private TspResInterface tspResInterface;

    @Resource
    private ResourceService resourceService;

    @Resource
    private ResourceBaseCacheService resourceBaseCacheService;

    @Resource
    private CommonRestClientService commonRestClientService;


    @Test
    public void s9() {
        ResCtripSearchRelationReq resCtripSearchRelationReq = new ResCtripSearchRelationReq();
        resCtripSearchRelationReq.setFlightNo("MU2544");
        resCtripSearchRelationReq.setCabin("Y");
        resCtripSearchRelationReq.setOrgCityCode("SHA");
        resCtripSearchRelationReq.setDstCityCode("WUH");
        resCtripSearchRelationReq.setDepartureDate("2016-04-27");
        Object o = tspResInterface.getCabinPriceIndivPriceCtripRuleCtripSeatPrice(resCtripSearchRelationReq);
        System.out.println(JSON.toJSONString(o));
    }

    @Test
    public void s0() throws IOException {
        Object o = resourceBaseCacheService.getAirport("TEB");
        System.out.println(JSON.toJSONString(o));
        try {
            Thread.sleep(10000000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.in.read();
    }

    @Test
    public void s14() {
        commonRestClientService.saveOrUpdateFlights("11");
    }

    @Test
    public void s1() {
        List<ResDistrict> list = tspResInterface.findDistrict(new ResDistrictReq());
        System.out.println(JSON.toJSONString(list));
    }


    @Test
    public void s2() {
        ResAirlineCompany company = resourceBaseCacheService.getAirlineCompany("OQ");
    }

    @Test
    public void s3() {
        ResAirport resAirport = resourceBaseCacheService.getAirport("PEK");
        ResAirport resAirport1 = resourceBaseCacheService.getAirport("BBS");
        ResAirport resAirport2 = resourceBaseCacheService.getAirport("BBS");
        ResAirport resAirport3 = resourceBaseCacheService.getAirport("ABE");
        ResAirport resAirport4 = resourceBaseCacheService.getAirport("ABE");
        ResAirport resAirport5 = resourceBaseCacheService.getAirport("PEK");
    }

    @Test
    public void s4() {
        ResRowsResp<ResAirRoute> resAirport = tspResInterface.findAirRoute(
                new ResAirRouteReq().setArrivalAirportCode("PEK").setDepartureAirportCode("SHA"));

        System.out.println(JSON.toJSONString(resAirport));
    }

    @Test
    public void s5() {
        Object o = resourceService.getIndivSeatByIdDemestic(1);
        System.out.println(JSON.toJSONString(o));
    }

}
