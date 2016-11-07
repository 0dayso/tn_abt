package com.tuniu.aop.unittest;


/**
 * Created by chengyao on 2016/2/18.
 */
public class PrintTest {

//    public static void main(String[] args) {
//        BookingRequest bookingRequest = new BookingRequest();
//        bookingRequest.setSessionId("19837504");
//        bookingRequest.setOrderId(410319148);
//        bookingRequest.setServiceName("CFM.individual.flight.occupy.OccupyController.feedback");
//        bookingRequest.setSystemId(71);
//        bookingRequest.setProductId(null);
//
//        BookingContactInfo bookingContactInfo = new BookingContactInfo();
//        bookingContactInfo.setContactPersonName("zhangsan");
//        bookingContactInfo.setContactPersonEmail("zhangsan@163.com");
//        bookingContactInfo.setContactPersonTel("13286987456");
//
//        bookingContactInfo.setFirstName("san");
//        bookingContactInfo.setLastName("zhang");
//        bookingContactInfo.setContactAddress("SHANGHAI");
//        bookingContactInfo.setContactCity("SHANGHAI");
//        bookingContactInfo.setContactCountry("CN");
//        bookingContactInfo.setContactPostal("200120");
//        bookingContactInfo.setContactProvinceState("SH");
//
//        bookingRequest.setContactInfo(bookingContactInfo);
//
//
//        BookingDetail bookingDetail = new BookingDetail();
//
//        List<TravelSegment> travelSegmentList = new ArrayList<TravelSegment>();
//        TravelSegment travelSegment = new TravelSegment();
//        travelSegment.setArrival(new TravelSegmentPoint());
//        travelSegment.setCabin(new TravelSegmentCabin());
//        travelSegment.setDeparture(new TravelSegmentPoint());
//        travelSegmentList.add(travelSegment);
//
//        bookingDetail.setTravelSegments(travelSegmentList);
//
//        bookingDetail.setPassengers(Lists.newArrayList(new BookingPassengers()));
//
//        PriceInfo priceInfo = new PriceInfo();
//        priceInfo.setAdultPrice(new PriceInfoItem());
//        priceInfo.setChildPrice(new PriceInfoItem());
//        priceInfo.setInfantPrice(new PriceInfoItem());
//
//        bookingDetail.setPriceInfo(priceInfo);
//        bookingRequest.setBookingDetail(bookingDetail);
//
//        String t = JSON.toJSONString(bookingRequest, SerializerFeature.PrettyFormat,
//                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
//                SerializerFeature.WriteNullListAsEmpty
//        , SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero);
//        System.out.println(t);
//
//        ResponseVo responseVo = new ResponseVo();
//        String t2 = JSON.toJSONString(responseVo, SerializerFeature.PrettyFormat,
//                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
//                SerializerFeature.WriteNullListAsEmpty
//                , SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero);
//        System.out.println(t2);
//
//        BookFeedbackRequest bookFeedbackRequest = new BookFeedbackRequest();
//        OccupyResponseInl occupyResponseInl = new OccupyResponseInl();
//        occupyResData occupyResData = new occupyResData();
//        OccupyResDataDetail occupyResDataDetail = new OccupyResDataDetail();
//        BlendSaleInfo blendSaleInfo = new BlendSaleInfo();
//        blendSaleInfo.setBindingCouponInfoList(Lists.newArrayList(new CouponInfo()));
//
//        occupyResDataDetail.setBlendSaleInfo(blendSaleInfo);
//        occupyResData.setDetail(occupyResDataDetail);
//        DepartDateResVo departDateResVo = new DepartDateResVo();
//        departDateResVo.setDetail(new OccupyDatePriceDetail());
//        occupyResData.setDepartDates(Lists.newArrayList(departDateResVo));
//        occupyResponseInl.setResource(Lists.newArrayList(occupyResData));
//        //bookFeedbackRequest.setData(occupyResponseInl);
//
//        String t3 = JSON.toJSONString(bookFeedbackRequest, SerializerFeature.PrettyFormat,
//                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
//                SerializerFeature.WriteNullListAsEmpty
//                , SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero);
//        System.out.println(t3);
//
//    }
}
