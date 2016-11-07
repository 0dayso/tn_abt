package com.tuniu.abt.service.book;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.tuniu.abt.dao.AbtBookingRequestDao;
import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.entity.AbtBookingRequest;
import com.tuniu.abt.intf.exception.BizzException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.service.book.async.BookAsyncTask;
import com.tuniu.abt.service.book.work.BookWorkSupport;

/**
 * Created by chengyao on 2016/1/12.
 */
@Component
@Validated
public class BookFacadeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookFacadeService.class);

    @Resource
    private BookAsyncTask bookAsyncTask;
    
    @Resource
    private AbtBookingRequestDao abtBookingRequestDao;

    public void occupy(@Valid BookingRequest bookingRequest) {
        // 检查库中是否已有相同orderId,flightItemId的请求
        AbtBookingRequest requestInDb = abtBookingRequestDao.findByOrder(bookingRequest.getOrderId(),
                bookingRequest.getBookingDetail().getFlightItemId());
        if (requestInDb != null) {
            throw new BizzException(BookEx.DUPLICATED_REQUEST, "重复的占位请求，请检查入参是否有误");
        }
        // 检查入参
        BookRequestValidator.checkRequest(bookingRequest);
        BookWorkSupport.setRequest(bookingRequest);
        // 初始化，插入request表
        BookWorkSupport.setRequestId(saveAbtBookingRequest(bookingRequest));

        // 后台执行占位
        bookAsyncTask.execute(bookingRequest.getCallback());
    }

    private Long saveAbtBookingRequest(BookingRequest bookingRequest) {
        AbtBookingRequest request = new AbtBookingRequest();
        request.setOrderId(bookingRequest.getOrderId());
        request.setSessionId(bookingRequest.getSessionId());
        request.setSystemId(bookingRequest.getSystemId());
        request.setVendorId(bookingRequest.getVendorId());
        request.setFlightItemId(bookingRequest.getBookingDetail().getFlightItemId());
        try {
            abtBookingRequestDao.save(request);
        } catch (Exception e) {
            LOGGER.error("写入占位请求数据异常", e);
            throw new BizzException(BookEx.POST_DATA_CRUD_ERROR, "写入占位请求数据异常", e);
        }
        return request.getId();
    }

}
