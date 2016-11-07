package com.tuniu.aop.unittest.rest;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.travelsky.ibe.client.AvResult;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import com.tuniu.abt.service.travelsky.ibe.module.IbeFdPriceWrapModule;
import com.tuniu.adapter.flightTicket.vo.connector.AirAvailRequest;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightFDPriceRes;
import com.tuniu.aop.unittest.BaseTest;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;

/**
 * Created by chengyao on 2016/3/11.
 */
public class IbeTest extends BaseTest {

    @Resource
    private IbeInterfaceService ibeInterfaceService;
    @Resource
    private IbeFdPriceWrapModule ibeFdPriceWrapModule;

    @Test
    public void testFd() throws IOException {
        FdPriceParam fdPriceParam = new FdPriceParam();
        fdPriceParam.setDstCityIataCode("SHA");
        fdPriceParam.setOrgCityIataCode("CAN");
        fdPriceParam.setDepartureDate("2016-04-01");
        try {
            FlightFDPriceRes s = ibeFdPriceWrapModule.queryFDPriceByIBE(fdPriceParam);
            System.out.println(JSON.toJSONString(s));
        } catch (Exception e) {
            e.printStackTrace();;
        }
        System.in.read();
    }


    @Test
    public void testFdWrapModule() {
        FdPriceParam fdPriceParam = new FdPriceParam();
        fdPriceParam.setDstCityIataCode("SHA");
        fdPriceParam.setOrgCityIataCode("CAN");
        fdPriceParam.setDepartureDate("2016-04-01");
        FlightFDPriceRes s = ibeFdPriceWrapModule.queryFDPriceByIBE(fdPriceParam);
        System.out.println(JSON.toJSONString(s));
    }
    
    @Test
    public void testAirAvail() {
        
        AirAvailRequest request = new AirAvailRequest();
        request.setOrgCityIataCode("BJS");
        request.setDstCityIataCode("SHA");
        request.setDepartureDate("2016-06-12");
        try {
            AvResult result = ibeInterfaceService.getAvail(request);
            System.out.println(JsonUtil.toString(result));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
