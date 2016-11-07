package com.tuniu.aop.unittest.rest;

import com.tuniu.abt.service.ctrip.CtripInterfaceService;
import com.tuniu.abt.utils.JaxbXmlMapper;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.FlightSaveOrderResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.Response;
import com.tuniu.aop.unittest.BaseTest;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * Created by chengyao on 2016/2/19.
 */
public class CtripTest extends BaseTest {

    @Resource
    private CtripInterfaceService ctripInterfaceService;

    @Test
    public void s() throws IOException {
        try {
            Object o  = ctripInterfaceService.reviseCondition(new RefundRequest());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.in.read();
    }

    @Test
    public void testParseSaveOrderResult() {
        try {
            String xml = FileUtils.readFileToString(new File("D:/Users/lanlugang/Desktop/saveOrder.xml"));
            Response response = JaxbXmlMapper.fromXml(xml, Response.class);
            FlightSaveOrderResponse o = response.getFlightSaveOrderResponse();
            System.out.println(o.getSaveOrderResult().getFinalResultMsg());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
