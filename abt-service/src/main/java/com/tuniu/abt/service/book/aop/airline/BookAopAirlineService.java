package com.tuniu.abt.service.book.aop.airline;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.tuniu.abt.intf.dto.book.main.*;
import org.apache.commons.collections.CollectionUtils;


import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.aop.airline.AopAirPortInfo;
import com.tuniu.abt.intf.dto.aop.airline.AopBookingDetail;
import com.tuniu.abt.intf.dto.aop.airline.AopBookingOrderInfo;
import com.tuniu.abt.intf.dto.aop.airline.AopBookingRequest;
import com.tuniu.abt.intf.dto.aop.airline.AopBookingResponse;
import com.tuniu.abt.intf.dto.aop.airline.AopBookingResult;
import com.tuniu.abt.intf.dto.aop.airline.AopCabinInfo;
import com.tuniu.abt.intf.dto.aop.airline.AopContactInfo;
import com.tuniu.abt.intf.dto.aop.airline.AopFlightSegment;
import com.tuniu.abt.intf.dto.aop.airline.AopPriceDetail;
import com.tuniu.abt.intf.dto.aop.airline.AopPriceInfo;
import com.tuniu.abt.intf.dto.aop.airline.GetVendorRequest;
import com.tuniu.abt.intf.dto.aop.airline.GetVendorResponse;
import com.tuniu.abt.intf.dto.aop.airline.AopPassenger;
import com.tuniu.abt.intf.dto.book.request.BookingContactInfo;
import com.tuniu.abt.intf.dto.book.request.BookingDetail;
import com.tuniu.abt.intf.dto.book.request.BookingPassenger;
import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.intf.dto.book.request.FlightOption;
import com.tuniu.abt.intf.dto.book.request.TravelSegment;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.TspAopAirlineInterface;
import com.tuniu.abt.intf.tsp.TspResInterface;
import com.tuniu.abt.intf.tsp.dto.res.FlightIndivVendorPrice;
import com.tuniu.abt.intf.tsp.dto.res.ResFlightVendorPriceReq;
import com.tuniu.abt.intf.tsp.dto.res.ResSegment;
import org.springframework.stereotype.Service;

@Service
public class BookAopAirlineService {

	 @Resource
	 private TspAopAirlineInterface aopAirlineInterface;
	 
	 @Resource 
	 private TspResInterface tspResInterface;
	 
	 /**
	  * 根据政策ID判断是否为伪直连资源
	  * @param policyId 开放平台政策ID
	  * @return
	  */
	 public boolean isAirlineLinkOfAop(long policyId) {
		 GetVendorRequest getVendorRequest = new GetVendorRequest();
		 getVendorRequest.setPolicyId(policyId);
		 GetVendorResponse getVendorResponse = aopAirlineInterface.getVendor(getVendorRequest);
		 if (null == getVendorResponse) {
			 throw new BizzException(BookEx.AOP_QUERY_ERROR, "开放平台-根据政策ID判断是否为伪直连资源失败");
		 }
		 return  getVendorResponse.isDirectToAirLine();
	 }
	 
	 /**
	  * ABT占位入参转换华为伪直连占位入参
	  * @param bookingRequest
	  * @return
	  */
	 public AopBookingRequest convertAbtBookRequestToAop (BookingRequest bookingRequest) {
		 
		 AopBookingRequest aopBookingRequest = new AopBookingRequest();
		 
		 /*占位基本信息*/
		 aopBookingRequest.setChannel(bookingRequest.getChannel());//渠道
		 long policyId = bookingRequest.getBookingDetail().getPolicyId();
		 aopBookingRequest.setPolicyID(policyId);//政策ID
		 aopBookingRequest.setPolicyType(AopBookingRequest.POLICY_TYPE_AIRLINE);
		 aopBookingRequest.setSessionId(bookingRequest.getSessionId());//SessionId
		 aopBookingRequest.setSource(bookingRequest.getSource());//APP/M站
		 aopBookingRequest.setSystemId(bookingRequest.getSystemId());//渠道ID
		 aopBookingRequest.setOrderId(bookingRequest.getOrderId()+"");//订单号
		 
		 /*占位详细信息*/
		 BookingDetail bookingDetail = bookingRequest.getBookingDetail();
		 AopBookingDetail aopBookingDetail = new AopBookingDetail();
		 aopBookingRequest.setBookingDetail(aopBookingDetail);
		 /*价格信息*/
		 AopPriceDetail price = new AopPriceDetail();
		 aopBookingDetail.setPriceInfo(price);
		 /*查询价格信息*/
		 ResFlightVendorPriceReq resRequest = new ResFlightVendorPriceReq();
		 resRequest.setVendorId(bookingRequest.getVendorId());
		 List<ResSegment> resSegments = new ArrayList<>();
		 resRequest.setSegments(resSegments);
		 /*航段信息*/
		 List<AopFlightSegment> flightSegments = new ArrayList<>();
		 aopBookingDetail.setFlightSegments(flightSegments);
		 for (FlightOption flightOption : bookingDetail.getFlightItem().getFlightOptions()) {
			 for (TravelSegment travelSegment : flightOption.getTravelSegments()) {
				 AopFlightSegment segment = new AopFlightSegment();
				 /*查询价格-航段*/
				 ResSegment resSegment = new ResSegment();
				 resSegments.add(resSegment);
				 flightSegments.add(segment);
				 resSegment.setDepartureDate(travelSegment.getDeparture().getDate());
				 resSegment.setOrgCityIataCode(travelSegment.getDeparture().getCityIataCode());
				 resSegment.setDstCityIataCode(travelSegment.getArrival().getCityIataCode());
				 resSegment.setFlightNo(travelSegment.getFlightNo());
				 resSegment.setSeatCode(travelSegment.getCabin().getSeatCode());
				 /*航班*/
				 segment.setFlightNO(travelSegment.getFlightNo());
				 /*出发信息*/
				 AopAirPortInfo departure = new AopAirPortInfo();
				 departure.setAirportCode(travelSegment.getDeparture().getAirportCode());
				 departure.setAirportName(travelSegment.getDeparture().getAirportName());
				 departure.setCityCode(travelSegment.getDeparture().getCityIataCode());
				 departure.setCityName(travelSegment.getDeparture().getCityName());
				 departure.setDate(travelSegment.getDeparture().getDate());
				 departure.setTime(travelSegment.getDeparture().getTime());
				 segment.setDeparture(departure);
				 /*到达信息*/
				 AopAirPortInfo arrival = new AopAirPortInfo();
				 arrival.setAirportCode(travelSegment.getArrival().getAirportCode());
				 arrival.setAirportName(travelSegment.getArrival().getAirportName());
				 arrival.setCityCode(travelSegment.getArrival().getCityIataCode());
				 arrival.setCityName(travelSegment.getArrival().getCityName());
				 arrival.setDate(travelSegment.getArrival().getDate());
				 arrival.setTime(travelSegment.getArrival().getTime());
				 segment.setArrival(arrival);
				 /*舱等、舱位、人员*/
				 AopCabinInfo cabin = new AopCabinInfo();
				 cabin.setCabinClass(travelSegment.getCabin().getSeatCode());//舱位
				 cabin.setSeatType(travelSegment.getCabin().getSeatClass());//舱等
				 segment.setCabin(cabin);
			 }
		 }
		 /*人员信息*/
		 int adultNum =0;
		 int childNum = 0;
		 int babyNum = 0;
		 List<AopPassenger> passengerList = new ArrayList<>();
		 aopBookingDetail.setPassengers(passengerList);
		 for (BookingPassenger bookingPassenger : bookingDetail.getPassengers()) {
			 AopPassenger passenger = new AopPassenger();
			 passengerList.add(passenger);
			 passenger.setName(bookingPassenger.getName());
			 passenger.setFirstName(bookingPassenger.getFirstName());
			 passenger.setLastName(bookingPassenger.getLastName());
			 passenger.setBirthday(bookingPassenger.getBirthday());
			 passenger.setIdType(Integer.parseInt(bookingPassenger.getIdType()));
			 passenger.setIdNumber(bookingPassenger.getIdNumber());
			 passenger.setPassangerTypeCode(bookingPassenger.getPassengerTypeCode());
			 if (bookingPassenger.getPassengerTypeCode().equals("ADT")) {
				 adultNum++;
			 }
			 else if (bookingPassenger.getPassengerTypeCode().equals("CHD")) {
				 childNum++;
			 }
			 else if (bookingPassenger.getPassengerTypeCode().equals("INF")) {
				 babyNum++;
			 }
			 passenger.setSex(bookingPassenger.getSex());
		 }
		 /*价格信息*/
		 price.setAdultNum(adultNum);
		 price.setChildNum(childNum);
		 price.setBabyNum(babyNum);
		 getFlightIndivPriceFromRes(resRequest,price);
		 /*联系人信息*/
		 BookingContactInfo bookingContactInfo = new BookingContactInfo();
		 AopContactInfo contactInfo = new AopContactInfo();
		 contactInfo.setContactPersonTel(bookingContactInfo.getContactPersonTel());
		 aopBookingDetail.setContactInfo(contactInfo);
		 
		 return aopBookingRequest;
	 }

	/**
	 *
	 * @param aopBookingResult
	 * @param aopBookingRequest
	 * @return
	 */
	 public BookingReply convertAopBookResponseToAbt (AopBookingResult aopBookingResult,
													  AopBookingRequest aopBookingRequest) {
		 BookingReply bookingReply = new BookingReply();
		 List<PnrInfo> pnrInfos = new ArrayList<>();
		 bookingReply.setPnrInfos(pnrInfos);
		 for (AopBookingOrderInfo orderInfo : aopBookingResult.getOrderInfoList()){
			 PnrInfo pnrInfo = new PnrInfo();
			 pnrInfos.add(pnrInfo);
			 pnrInfo.setPnrNo(orderInfo.getPnr());
			 pnrInfo.setAssociatedPnrNo(orderInfo.getParentOrderId());
			 pnrInfo.setClearTime(orderInfo.getClearTime());
	         /*航段信息*/
	         List<Segment> segments = new ArrayList<>();
	         pnrInfo.setSegments(segments);
	         for (AopFlightSegment flightSegment : aopBookingRequest.getBookingDetail().getFlightSegments()){
	        	 Segment segment = new Segment();
	        	 segments.add(segment);
	        	 /*航班、航司信息*/
	        	 segment.setAirlineCompanyIataCode(flightSegment.getFlightNO().substring(0, 2));//航司二字码
	        	 segment.setFlightNo(flightSegment.getFlightNO());//航司
	        	 /*出发信息*/
	        	 AopAirPortInfo departure = flightSegment.getDeparture();
	        	 segment.setOrgAirportIataCode(departure.getAirportCode());
	        	 segment.setOrgAirportName(departure.getAirportName());
	        	 segment.setOrgCityIataCode(departure.getCityCode());
	        	 segment.setOrgCityName(departure.getCityName());
	        	 segment.setDepartureDate(departure.getDate());
	        	 segment.setDepartureTime(departure.getTime());
	        	 /*到达信息*/
	        	 AopAirPortInfo arrival = flightSegment.getArrival();
	        	 segment.setDstAirportIataCode(arrival.getAirportCode());
	        	 segment.setDstAirportName(arrival.getAirportName());
	        	 segment.setDstCityIataCode(arrival.getCityCode());
	        	 segment.setDstCityName(arrival.getCityName());
	        	 segment.setArriveDate(arrival.getDate());
	        	 segment.setArrivalTime(arrival.getTime());
	        	 /*舱等、舱位*/
	        	 segment.setSeatClass(flightSegment.getCabin().getSeatType());
	        	 segment.setSeatCode(flightSegment.getCabin().getCabinClass());
	         }
	         /*人员信息*/
	         List<com.tuniu.abt.intf.dto.book.main.Passenger> passengers = new ArrayList<>();
	         pnrInfo.setPassengers(passengers);
	         for (AopPassenger passengerInfo : orderInfo.getPassengers()) {
	        	 com.tuniu.abt.intf.dto.book.main.Passenger passenger = new com.tuniu.abt.intf.dto.book.main.Passenger();
				 /*姓名*/
				 passenger.setPassengerName(passengerInfo.getName());
				 passenger.setOrgPassengerName(passengerInfo.getName());
				 passenger.setFirstName(passengerInfo.getFirstName());
				 passenger.setLastName(passengerInfo.getLastName());
				 /*类型及证件*/
				 passenger.setPassengerTypeCode(passengerInfo.getPassangerTypeCode());
				 passenger.setIdentityType(passengerInfo.getIdType());
				 passenger.setIdentityNo(passengerInfo.getIdNumber());
				 /*性别及出生日期*/
				 passenger.setGender(passengerInfo.getSex());
				 passenger.setBirthday(passengerInfo.getBirthday());
				 passengers.add(passenger);
	         }
	        /*价格信息*/
	         AopPriceDetail priceDetail = orderInfo.getPriceDetail();
	         List<PnrPrice> prices = new ArrayList<>();
	         pnrInfo.setPrices(prices);
	         /*成人*/
	         PnrPrice adultPnrPrice = new PnrPrice();
	         AopPriceInfo adultPriceInfo = priceDetail.getAdultPrice();
	         prices.add(adultPnrPrice);
	         adultPnrPrice.setPriceType(1);
	         adultPnrPrice.setOrgPrice((float)adultPriceInfo.getPrintPrice());
	         adultPnrPrice.setFloorPrice((float)adultPriceInfo.getSalePrice());
	         adultPnrPrice.setSalePrice((float)adultPriceInfo.getSalePrice());
	         /*儿童*/
			 if (aopBookingRequest.getBookingDetail().getPriceInfo().getChildNum() != 0) {
				 PnrPrice childPnrPrice = new PnrPrice();
				 AopPriceInfo childPriceInfo = priceDetail.getChildPrice();
				 prices.add(childPnrPrice);
				 childPnrPrice.setPriceType(2);
				 childPnrPrice.setOrgPrice((float)childPriceInfo.getPrintPrice());
				 childPnrPrice.setFloorPrice((float)childPriceInfo.getSalePrice());
				 childPnrPrice.setSalePrice((float)childPriceInfo.getSalePrice());
			 }
	         /*婴儿*/
			 if (aopBookingRequest.getBookingDetail().getPriceInfo().getBabyNum() != 0) {
				 PnrPrice babyPnrPrice = new PnrPrice();
				 AopPriceInfo babyPriceInfo = priceDetail.getChildPrice();
				 prices.add(babyPnrPrice);
				 babyPnrPrice.setPriceType(3);
				 babyPnrPrice.setOrgPrice((float)babyPriceInfo.getPrintPrice());
				 babyPnrPrice.setFloorPrice((float)babyPriceInfo.getSalePrice());
				 babyPnrPrice.setSalePrice((float)babyPriceInfo.getSalePrice());
			 }
		 }
		 return bookingReply;
	 }
	 public AopBookingResponse book(AopBookingRequest aopBookingRequest) {

		    AopBookingResponse aopBookingResponse = aopAirlineInterface.book(aopBookingRequest);

			if (null == aopBookingResponse) {
				throw new BizzException(BookEx.AOP_QUERY_ERROR, "开放平台-伪直连占位响应为空");
			}
			if (!aopBookingResponse.isSuccess() || null == aopBookingResponse.getData()) {
				throw new BizzException(BookEx.AOP_QUERY_ERROR, "开放平台-伪直连占位失败："+aopBookingResponse.getMessage());
			}
			return aopBookingResponse;
	 }
	 
	 public void getFlightIndivPriceFromRes (ResFlightVendorPriceReq resRequest, AopPriceDetail price) {

		 List<FlightIndivVendorPrice> flightIndivPriceList =  tspResInterface.findFlightVendorPrices(resRequest);

		 if (CollectionUtils.isEmpty(flightIndivPriceList)) {
			 throw new BizzException(BookEx.AOP_QUERY_ERROR, "开放平台-伪直连占位失败：查询资源价格信息失败");
		 }
         /*成人价格信息*/
         AopPriceInfo adultPrice = new AopPriceInfo();
         price.setAdultPrice(adultPrice);
         /*儿童价格信息*/
         AopPriceInfo childPrice = new AopPriceInfo();
         price.setChildPrice(childPrice);
         /*婴儿价格信息*/
         AopPriceInfo babyPrice = new AopPriceInfo();
         price.setInfantPrice(babyPrice);
         for (FlightIndivVendorPrice flightIndivPrice : flightIndivPriceList) {
         	if (flightIndivPrice.getPassengerCode().equals("ADT")) {
         		adultPrice.setPrintPrice(flightIndivPrice.getPrintPrice());
         		adultPrice.setSalePrice(flightIndivPrice.getCost());
         		adultPrice.setAirportTax(flightIndivPrice.getTaxes());
         		adultPrice.setFuelSurcharge(flightIndivPrice.getQcharge());
         	}
         	else if (flightIndivPrice.getPassengerCode().equals("CHD")) {
         		childPrice.setPrintPrice(flightIndivPrice.getPrintPrice());
         		childPrice.setSalePrice(flightIndivPrice.getCost());
         		childPrice.setAirportTax(flightIndivPrice.getTaxes());
         		childPrice.setFuelSurcharge(flightIndivPrice.getQcharge());
         	}
         	else if (flightIndivPrice.getPassengerCode().equals("INF")) {
         		babyPrice.setPrintPrice(flightIndivPrice.getPrintPrice());
         		babyPrice.setSalePrice(flightIndivPrice.getCost());
         		babyPrice.setAirportTax(flightIndivPrice.getTaxes());
         		babyPrice.setFuelSurcharge(flightIndivPrice.getQcharge());
         	}
         }
	 }
	 
	 
}
