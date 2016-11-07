package com.tuniu.abt.service.book.work.handlers;

import javax.annotation.Resource;

import com.tuniu.abt.intf.dto.book.main.BookingReply;
import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.service.book.BookDBService;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.tuniu.abt.intf.dto.book.response.BookingResData;
import com.tuniu.abt.service.book.BookBaseService;
import com.tuniu.abt.service.book.work.BookWorkSupport;

/**
 * Created by chengyao on 2016/1/15.
 */
@Component
public class PackAndRespWorkHandler extends BookWorkSupport {

    @Resource
    private BookBaseService bookBaseService;
    @Resource
    private BookDBService bookDBService;

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public boolean apply() {
        return true;
    }

    @Override
    public boolean processing() {
        BookingRequest request = getRequest();
        BookingReply bookingReply = getBookingReply();
        // 0.调销控进行调价
        bookBaseService.updatePnrPriceBySaleControl(request.getVendorId(),
                request.getSystemId(), bookingReply.getPnrInfos());
        // 1.保存占位数据
        bookDBService.saveAbtBookingData(getRequestId(), request, bookingReply);
        // 2.组装返回数据
        BookingResData resData = bookBaseService.convertToBookingResData(request, bookingReply);
        setResponseData(resData);
        return true;
    }

}
