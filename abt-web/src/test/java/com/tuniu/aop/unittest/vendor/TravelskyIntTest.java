package com.tuniu.aop.unittest.vendor;

import com.alibaba.fastjson.JSON;
import com.travelsky.espeed.OTAAirResRetRS;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.Passenger;
import com.tuniu.abt.intf.dto.book.main.PnrInfo;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.service.travelsky.dto.AirAvailResult;
import com.tuniu.abt.service.travelsky.dto.PatByPnrReq;
import com.tuniu.abt.service.travelsky.dto.PatByPnrRes;
import com.tuniu.abt.service.travelsky.dto.PataBySegRes;
import com.tuniu.abt.service.travelsky.ibe.module.IbeCleanPnrWrapModule;
import com.tuniu.abt.service.travelsky.ibe.module.IbePataBySegWrapModule;
import com.tuniu.abt.service.travelsky.ibeplus.module.AirTicketRefundWrapModule;
import com.tuniu.adapter.flightTicket.vo.connector.AirAvailRequest;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanlugang on 2016/4/7.
 */
public class TravelskyIntTest extends BaseTest{

    @Resource
    private TravelSkyInterface travelSkyInterface;
    @Resource
    private AirTicketRefundWrapModule airTicketRefundWrapModuleV2;
    @Resource
    private IbeCleanPnrWrapModule ibeCleanPnrWrapModule;

    @Test
    public void test1() {
        Object o = airTicketRefundWrapModuleV2.queryTicketRefundPrice("324-1770605773");
        System.out.println(JSON.toJSONString(o));
    }

    @Test
    public void testAirAvail() {
        AirAvailRequest request = new AirAvailRequest();
        request.setAirline("MU");
        request.setOrgCityIataCode("PEK");
        request.setDstCityIataCode("SHA");
        request.setDepartureDate("2016-06-30");
        request.setDirect(true);
        try {
            AirAvailResult result = travelSkyInterface.airAvail(request, 0);
            System.out.println(JSON.toJSONString(result));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCreatPnr() {

        BookingData bookingData = new BookingData();
        bookingData.setOrderId(101010101010L);
        bookingData.setSystemId(10);
        bookingData.setVendorId(1);
        bookingData.setContactTel("13813027581");
        bookingData.setOccupyOfficeNo("SHA622");// SHA622  BJS914 CAN527
//        bookingData.setVendorOfficeNo("CAN527");
//        bookingData.setEtdzOfficeNo("SHA622");
        //
        {
            Passenger passenger = new Passenger();
            passenger.setPersonId(1L);
            passenger.setPassengerName("测试");
            passenger.setPassengerTypeCode("ADT");
            passenger.setGender(1);
            passenger.setIdentityNo("NKG111222");
            passenger.setBirthday("1988-09-01");
            bookingData.getPassengers().add(passenger);
        }
//        {
//            Passenger passenger = new Passenger();
//            passenger.setPersonId(2L);
//            passenger.setPassengerName("成滔");
//            passenger.setPassengerTypeCode("ADT");
//            passenger.setGender(1);
//            passenger.setIdentityNo("NKG111223");
//            passenger.setBirthday("1988-09-01");
//            bookingData.getPassengers().add(passenger);
//        }
//        {
//            Passenger passenger = new Passenger();
//            passenger.setPersonId(3L);
//            passenger.setPassengerName("测试儿童");
//            passenger.setPassengerTypeCode("CHD");
//            passenger.setGender(1);
//            passenger.setIdentityNo("NKG111224");
//            passenger.setBirthday("2010-09-01");
//            bookingData.getPassengers().add(passenger);
//        }
//        {
//            Passenger passenger = new Passenger();
//            passenger.setPersonId(4L);
//            passenger.setRefPersonId(1L);
//            passenger.setFirstName("ceshi");
//            passenger.setLastName("yinger");
//            passenger.setPassengerName("测试婴儿");
//            passenger.setPassengerTypeCode("INF");
//            passenger.setGender(1);
//            passenger.setIdentityNo("20150901");
//            passenger.setBirthday("2015-09-01");
//            bookingData.getPassengers().add(passenger);
//        }

        //
//        Segment segment = new Segment();
//        segment.setFlightNo("CA1113");
//        segment.setSeatCode("Y");
//        segment.setOrgAirportIataCode("PEK");
//        segment.setDstAirportIataCode("WUA");
//        segment.setOrgCityIataCode("BJS");
//        segment.setDstCityIataCode("WUA");
//        segment.setDepartureDate("2016-10-25");
//        segment.setDepartureTime("07:20");
//        segment.setAirlineCompanyIataCode("CA");
//        bookingData.getSegments().add(segment);
        Segment segment2 = new Segment();
        segment2.setFlightNo("CZ3767");
        segment2.setSeatCode("L");// E Y
        segment2.setOrgAirportIataCode("ZUH");
        segment2.setDstAirportIataCode("KWE");
        segment2.setOrgCityIataCode("ZUH");
        segment2.setDstCityIataCode("KWE");
        segment2.setDepartureDate("2016-07-20");
        segment2.setDepartureTime("08:05");
        segment2.setAirlineCompanyIataCode("CZ");
        segment2.setPlaneType("319");
        bookingData.getSegments().add(segment2);

        try {
            PnrInfo pnrInfo = travelSkyInterface.airBookPnr(bookingData);
            System.out.println(JSON.toJSONString(pnrInfo));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testRTPnr() {
        try {
            OTAAirResRetRS.AirResRet rtPnrReply = travelSkyInterface.rt("KVCHQ3", 54);
            System.out.println(JSON.toJSONString(rtPnrReply));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCancelPnr() {
        try {
            travelSkyInterface.cancelPnr("KEYE5J", 54, "NKG166");
        } catch (Exception e) {

        }
    }

    @Test
    public void testCancelPnrSpecialOfficeNo() {
        try {
            ibeCleanPnrWrapModule.cleanPnr("JYE4Y1", "CAN527");
        } catch (Exception e) {

        }
    }

    @Test
    public void testPatByPnr() {
        try {
            PatByPnrReq request = new PatByPnrReq();
            request.setPnrNo("JYE4Y1");
            request.setPsgType(PatByPnrReq.PSG_TYPE_ADT);
//            request.setFareType(PatByPnrReq.FARE_TYPE_FD);
//            request.setHasBaby(true);
            PatByPnrRes res = travelSkyInterface.doPatPriceByPnr(request, 0);
            System.out.println(JSON.toJSONString(res));
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @Test
    public void authorizePnr() {
        try {
            List<PnrInfo> pnrInfos = new ArrayList<PnrInfo>();
            PnrInfo pnrInfo = new PnrInfo();
            pnrInfo.setPnrNo("JZ9E5C");
            pnrInfos.add(pnrInfo);
            travelSkyInterface.authorizePnr(pnrInfos, "CAN199");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testPataBySeg() {
        List<Segment> segments = new ArrayList<Segment>();
        {
//        Segment segment = new Segment();
//        segment.setFlightNo("HU7810");//MU5182 CZ3105 CA1302 HU7804 KN5820 HU7810
//        segment.setSeatCode("Q");// E Y
//        segment.setOrgAirportIataCode("CAN");
//        segment.setDstAirportIataCode("PEK");
//        segment.setOrgCityIataCode("CAN");
//        segment.setDstCityIataCode("BJS");
//        segment.setDepartureDate("2016-07-20");
//        segment.setDepartureTime("14:20");
//        segment.setArriveDate("2016-07-20");
//        segment.setArrivalTime("16:20");
//        segment.setAirlineCompanyIataCode("MU");
//        segment.setPlaneType("763");
//        segments.add(segment);
        }
        {
            Segment segment2 = new Segment();
            segment2.setFlightNo("ZH9123");
            segment2.setSeatCode("Q");// E Y
            segment2.setOrgAirportIataCode("CAN");
            segment2.setDstAirportIataCode("TAO");
            segment2.setOrgCityIataCode("CAN");
            segment2.setDstCityIataCode("TAO");
            segment2.setDepartureDate("2016-07-20");
            segment2.setDepartureTime("14:20");
            segment2.setArriveDate("2016-07-20");
            segment2.setArrivalTime("16:20");
            segment2.setAirlineCompanyIataCode("ZH");
            segment2.setPlaneType("763");
            segments.add(segment2);
        }

        try {
            PataBySegRes pataBySegRes = travelSkyInterface
                    .airPriceBySegD(segments, PataBySegRes.PATA_PSG_TYPE_ADT);
            System.out.println(JSON.toJSONString(pataBySegRes));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Test
    public void testBOP() {
        try {

            travelSkyInterface.issueTicketByBOP("KN1188");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testRefund() {

        try {
            Object object = travelSkyInterface.queryRefundPrice("123-1254125122");
            System.out.println(JSON.toJSONString(object));
        } catch (Exception e) {
            System.out.println(e);

        }

    }

}
