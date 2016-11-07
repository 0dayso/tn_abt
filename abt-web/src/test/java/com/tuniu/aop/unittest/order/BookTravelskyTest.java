package com.tuniu.aop.unittest.order;

import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.Passenger;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.service.book.travelsky.BookTravelSkySupport;
import com.tuniu.abt.service.issue.travelsky.module.TravelSkyIssueSupport;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanlugang on 2016/5/6.
 */
public class BookTravelskyTest extends BaseTest {

    @Resource
    private BookTravelSkySupport bookTravelSkySupport;

    @Resource
    private TravelSkyIssueSupport travelSkyIssueSupport;

    @Test
    public void testSegmentNO() {

        try {
            travelSkyIssueSupport.checkPNRIssueTicketIsOK("HPV4X3"); // JR4HNX
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void testReBuildBookingData() {
        try {
            Object obj = bookTravelSkySupport.rebuildBookingData(buildBookingData());
            System.out.println(obj);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private BookingData buildBookingData() {
        BookingData bookingData = new BookingData();
        bookingData.setOrderId(101010101010L);
        bookingData.setSystemId(71);
        bookingData.setVendorId(1);
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
        {
            Passenger passenger = new Passenger();
            passenger.setPersonId(3L);
            passenger.setPassengerName("测试儿童");
            passenger.setPassengerTypeCode("CHD");
            passenger.setGender(1);
            passenger.setIdentityNo("NKG111224");
            passenger.setBirthday("2010-09-01");
            bookingData.getPassengers().add(passenger);
        }
        {
            Passenger passenger = new Passenger();
            passenger.setPersonId(4L);
            passenger.setRefPersonId(1L);
            passenger.setFirstName("ceshi");
            passenger.setLastName("yinger");
            passenger.setPassengerName("测试婴儿");
            passenger.setPassengerTypeCode("INF");
            passenger.setGender(1);
            passenger.setIdentityNo("20150901");
            passenger.setBirthday("2015-09-01");
            bookingData.getPassengers().add(passenger);
        }
        //
        bookingData.getSegments().addAll(buildSegments());
        return bookingData;
    }

    private List<Segment> buildSegments() {
        List<Segment> segments = new ArrayList<Segment>();
        {
//            Segment segment = new Segment();
//            segment.setFlightNo("CA1503");//MU5182 CZ3105 CA1302 HU7804 KN5820 HU7810
//            segment.setSeatCode("W");// E Y
//            segment.setSeatClass("Y");
//            segment.setOrgAirportIataCode("PEK");
//            segment.setDstAirportIataCode("NKG");
//            segment.setOrgCityIataCode("PEK");
//            segment.setDstCityIataCode("NKG");
//            segment.setDepartureDate("2016-05-10");
//            segment.setDepartureTime("14:20");
//            segment.setArriveDate("2016-05-10");
//            segment.setArrivalTime("16:20");
//            segment.setAirlineCompanyIataCode("CA");
//            segment.setPlaneType("763");
//            segments.add(segment);
        }
        {
            Segment segment1 = new Segment();
            segment1.setFlightNo("MU5181"); // MU5181 CZ3116
            segment1.setSeatCode("R"); // R L
            segment1.setSeatClass("Y");
            segment1.setOrgAirportIataCode("PEK");
            segment1.setDstAirportIataCode("CAN");
            segment1.setOrgCityIataCode("BJS");
            segment1.setDstCityIataCode("CAN");
            segment1.setDepartureDate("2016-07-22");
            segment1.setDepartureTime("08:20");
            segment1.setArriveDate("2016-07-22");
            segment1.setArrivalTime("10:20");
            segment1.setAirlineCompanyIataCode("MU");
            segment1.setPlaneType("333");
            segments.add(segment1);
        }
        {
//            Segment segment2 = new Segment();
//            segment2.setFlightNo("ZH9123");
//            segment2.setSeatCode("Q");// E Y
//            segment2.setSeatClass("Y");
//            segment2.setOrgAirportIataCode("CAN");
//            segment2.setDstAirportIataCode("TAO");
//            segment2.setOrgCityIataCode("CAN");
//            segment2.setDstCityIataCode("TAO");
//            segment2.setDepartureDate("2016-07-20");
//            segment2.setDepartureTime("14:20");
//            segment2.setArriveDate("2016-07-20");
//            segment2.setArrivalTime("16:20");
//            segment2.setAirlineCompanyIataCode("ZH");
//            segment2.setPlaneType("763");
//            segments.add(segment2);
        }
        return segments;
    }
}


