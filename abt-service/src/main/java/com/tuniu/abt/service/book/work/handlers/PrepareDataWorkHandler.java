package com.tuniu.abt.service.book.work.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.service.book.BookPrepareDataService;
import com.tuniu.abt.service.book.work.BookWorkSupport;

/**
 * Created by chengyao on 2016/1/15.
 */
@Component
public class PrepareDataWorkHandler extends BookWorkSupport {

    @Resource
    private BookPrepareDataService bookPrepareDataService;

    @Override
    public int getOrder() {
        return 15;
    }

    @Override
    public boolean apply() {
        return true;
    }

    @Override
    public boolean processing() throws Exception {
        BookingData bookingData = bookPrepareDataService.convert2InnerObject(getRequest());
        setBookingData(bookingData);
        bookPrepareDataService.checkBookingData(bookingData);
        return true;
    }



}
