package com.tuniu.aop.unittest.vendor;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.service.cachework.FdPriceCacheWorker;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.abt.service.travelsky.ibe.module.IbeFdPriceWrapModule;
import com.tuniu.abt.service.travelsky.ibeplus.module.AirFareDisplayWrapModule;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightAirCompanyFDPrice;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightCabinFDPrice;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightFDPriceRes;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chengyao on 2016/4/16.
 */
public class TravelSkyFdTest extends BaseTest {

    @Resource
    private IbeFdPriceWrapModule ibeFdPriceWrapModule;
    @Resource
    private AirFareDisplayWrapModule airFareDisplayWrapModule2;
    @Resource
    private FdPriceCacheWorker fdPriceCacheWorker;

    @Test
    public void test1() throws IOException {
        FdPriceParam fdPriceParam = new FdPriceParam();
        fdPriceParam.setOrgCityIataCode("SHA");
        fdPriceParam.setDstCityIataCode("PEK");
//        fdPriceParam.setAirlineCompany("XO");
        fdPriceParam.setDepartureDate("2016-06-04");

        FlightFDPriceRes flightFDPriceRes2 = airFareDisplayWrapModule2.queryAirFareDisplay(fdPriceParam);
        Collections.sort(flightFDPriceRes2.getFdPriceList(), new XCompare());
        for (FlightAirCompanyFDPrice flightAirCompanyFDPrice : flightFDPriceRes2.getFdPriceList()) {
            Collections.sort(flightAirCompanyFDPrice.getCabinFDPricelist(), new YCompare());
        }
        System.out.println(JSON.toJSONString(flightFDPriceRes2));

        FlightFDPriceRes flightFDPriceRes1 = ibeFdPriceWrapModule.queryFDPriceByIBE(fdPriceParam);
        Collections.sort(flightFDPriceRes1.getFdPriceList(), new XCompare());
        for (FlightAirCompanyFDPrice flightAirCompanyFDPrice : flightFDPriceRes1.getFdPriceList()) {
            Collections.sort(flightAirCompanyFDPrice.getCabinFDPricelist(), new YCompare());
        }
        System.out.println(JSON.toJSONString(flightFDPriceRes1));

        System.out.println();
        System.in.read();

    }

    @Test
    public void t2() {
        FdPriceParam fdPriceParam = new FdPriceParam();
        fdPriceParam.setSystemId(1);
        fdPriceParam.setCabin("Z");
        fdPriceParam.setAirlineCompany("TA");
        fdPriceParam.setOrgCityIataCode("SHA");
        fdPriceParam.setDstCityIataCode("PEK");
        fdPriceParam.setDepartureDate("2016-04-26");


        Object z = fdPriceCacheWorker.find(fdPriceParam);
        System.out.println(JSON.toJSONString(z));
    }

    class XCompare implements Comparator<FlightAirCompanyFDPrice> {
        @Override
        public int compare(FlightAirCompanyFDPrice o1, FlightAirCompanyFDPrice o2) {
            return o1.getAirCompanyIataCode().compareTo(o2.getAirCompanyIataCode());
        }
    }

    class YCompare implements Comparator<FlightCabinFDPrice> {
        @Override
        public int compare(FlightCabinFDPrice o1, FlightCabinFDPrice o2) {
            return o1.getFdPrice().compareTo(o2.getFdPrice());
        }
    }

}
