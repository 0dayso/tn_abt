package com.tuniu.abt.service.book.work.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tuniu.abt.intf.dto.aop.airline.AopBookingRequest;
import com.tuniu.abt.intf.dto.aop.airline.AopBookingResponse;
import com.tuniu.abt.intf.dto.book.main.BookVendorId;
import com.tuniu.abt.intf.dto.book.main.BookingReply;
import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.service.book.aop.airline.BookAopAirlineService;
import com.tuniu.abt.service.book.work.BookWorkSupport;

@Component
public class AopAirlineBookWorkHandler extends BookWorkSupport{

	@Resource
    private BookAopAirlineService bookAopAirlineService;

    @Override
    public int getOrder() {
        return 17;
    }

    @Override
    public boolean apply() {
    	BookingRequest bookingRequest = getRequest();
    	long policyId = bookingRequest.getBookingDetail().getPolicyId();
        if (BookVendorId.AOP.intValue() == getBookingData().getVendorId() 
        		&& bookAopAirlineService.isAirlineLinkOfAop(policyId)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean processing() throws Exception {

    	BookingRequest bookingRequest = getRequest();
    	/*参数转换*/
    	AopBookingRequest aopBookingRequest = bookAopAirlineService.convertAbtBookRequestToAop(bookingRequest);
    	/*占位*/
    	AopBookingResponse aopBookingResponse = bookAopAirlineService.book(aopBookingRequest);
    	/*参数转换*/
		BookingReply bookingReply = bookAopAirlineService.convertAopBookResponseToAbt(aopBookingResponse.getData(),aopBookingRequest);
		/*保存返参*/
        setBookingReply(bookingReply);
        return true;
    }
}
