package com.tuniu.abt.service.book.work.handlers;

import com.tuniu.abt.intf.dto.book.main.BookVendorId;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.BookingReply;
import com.tuniu.abt.service.book.travelsky.BookTravelSkyService;
import com.tuniu.abt.service.book.work.BookWorkSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/1/18.
 */
@Component
public class TravelSkyBookWorkHandler extends BookWorkSupport {

	private static final Logger LOG = LoggerFactory.getLogger(TravelSkyBookWorkHandler.class);
	
	@Resource
	private BookTravelSkyService bookTravelSkyService;

    @Override
    public int getOrder() {
        return 19;
    }

    @Override
    public boolean apply() {
        return (BookVendorId.TRAVELSKY.intValue() == getBookingData().getVendorId());
    }

    @Override
    public boolean processing() throws Exception {
    	BookingData bookingData = getBookingData();
        BookingReply bookingReply = bookTravelSkyService.occupy4Hangxin(bookingData);
        setBookingReply(bookingReply);
        return true;
    }

}