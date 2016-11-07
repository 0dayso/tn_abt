package com.tuniu.aop.unittest.tsp;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.base.mail.SendEmail;
import com.tuniu.abt.intf.tsp.*;
import com.tuniu.abt.intf.tsp.dto.adt.FreightCalculateRequestVo;
import com.tuniu.abt.intf.tsp.dto.adt.InquiryPriceDetailVo;
import com.tuniu.abt.intf.tsp.dto.flt.BanAddRequest;
import com.tuniu.abt.intf.tsp.dto.res.*;
import com.tuniu.adapter.flightTicket.vo.dfpPolicy.DetailFligthSegVo;
import com.tuniu.aop.unittest.BaseTest;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/2/14.
 */
public class TspClientTest extends BaseTest {

    @Resource
    TspAopInterface tspAopInterface;
    @Resource
    TspResInterface tspResInterface;
    @Resource
    private TspAdtInterface tspAdtInf;
    @Resource
    private TspFltInterface tspFltInterface;
    @Resource
    private TspPlaInterface tspPlaInterface;
    @Resource
    private SendEmail sendEmail;


    @Test
    public void t() {
        List<BanAddRequest> banAddRequests = new ArrayList<>();
        {
            BanAddRequest banAddRequest = new BanAddRequest();
            banAddRequest.setAirlineCompany("MU");
            banAddRequest.setFlightNo("MU1234");
            banAddRequest.setSystemId("1/2/3");
            banAddRequest.setSolutionId("0");
            banAddRequest.setAddUserId(0);
            banAddRequest.setAddUserName("系统");
            banAddRequests.add(banAddRequest);
        }

        {
            BanAddRequest banAddRequest = new BanAddRequest();
            banAddRequest.setAirlineCompany("CA");
            banAddRequest.setFlightNo("CA4321");
            banAddRequest.setSystemId("1/2/3");
            banAddRequest.setSolutionId("0");
            banAddRequest.setAddUserId(0);
            banAddRequest.setAddUserName("系统");
            banAddRequests.add(banAddRequest);
        }

        List<Long> result = tspFltInterface.addBanFlight(banAddRequests);
        System.out.println(result);
    }

    @Test
    public void sendmail() {

        sendEmail.sendEmail("邮件标题", "消息文本", "chengyao@tuniu.com", null);
    }

    @Test
    public void s() {

        for (int i = 0; i < 10; i++) {
            try {
                tspResInterface.getIndivCabinGroupByResIdDomestic(ResIdDateReq.build(1));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }

    @Test
    public void testResAirport() {
        ResAirPortReq request = new ResAirPortReq();
        request.setAirportCode("PEK");
        ResRowsResp<ResAirport> list = tspResInterface.findAirport(request);
        System.out.println(JsonUtil.toString(list));
    }

    @Test
    public void testFindFlightVendorPrices() {
        ResFlightVendorPriceReq request = new ResFlightVendorPriceReq();
        ResSegment resSegment = new ResSegment();
//        resSegment.setFlightNo("HU7603");
//        resSegment.setOrgCityIataCode("PEK");
//        resSegment.setDstCityIataCode("SHA");
//        resSegment.setSeatCode("C");
//        resSegment.setDepartureDate("2016-06-30");
        resSegment.setFlightNo("KN5827");
        resSegment.setOrgCityIataCode("PEK");
        resSegment.setDstCityIataCode("SYX");
        resSegment.setSeatCode("Y");
        resSegment.setDepartureDate("2016-04-30");
        request.getSegments().add(resSegment);
        request.setVendorId(6);
        try {
            List<FlightIndivVendorPrice> datas = tspResInterface.findFlightVendorPrices(request);
            System.out.println(JsonUtil.toString(datas));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    @Test
    public void testInquryFreightPrice() {
        FreightCalculateRequestVo requestVo = new FreightCalculateRequestVo();
        requestVo.setSystemId(59);
        requestVo.setSolutionId(6);
        requestVo.setPrice(2509);
        DetailFligthSegVo fligthSegVo = new DetailFligthSegVo();
        fligthSegVo.setFlightNo("KN5827");
        fligthSegVo.setSeatClass("Y");
        fligthSegVo.setSeatCode("Y");
        fligthSegVo.setOrgAirportIataCode("PEK");
        fligthSegVo.setDstAirportIataCode("SYX");
        fligthSegVo.setOrgCityIataCode("PEK");
        fligthSegVo.setDstCityIataCode("SYX");
        fligthSegVo.setDepartureDate("2016-04-30");
        requestVo.getFlightSegList().add(fligthSegVo);
        try {
            InquiryPriceDetailVo data = tspAdtInf.qryDetailPrice(requestVo);
            System.out.println(JSON.toJSONString(data));
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
