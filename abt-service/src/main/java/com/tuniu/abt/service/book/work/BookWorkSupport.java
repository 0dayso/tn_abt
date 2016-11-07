package com.tuniu.abt.service.book.work;

import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.BookingReply;
import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.intf.dto.book.response.BookingResData;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.adapter.flightTicket.vo.policy.aop.PolicyDetail;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 静态类， 扩展工作项目接口，提供一些默认值，及公共方法
 * Created by chengyao on 2016/1/12.
 */
public abstract class BookWorkSupport implements BookWorkHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String KEY_REQUEST = "_REQUEST";
    private static final String KEY_RESPONSE_DATA = "_RESPONSE_DATA";
    private static final String KEY_BOOK_DATA = "_BOOK_DATA";
    private static final String KEY_BOOK_REPLY = "_BOOK_REPLY";
    private static final String KEY_AOP_POLICY = "_AOP_POLICY";
    private static final String KEY_REQUEST_ID = "_REQUEST_ID";

    public static Map<String, Object> getModel() {
        return DataSharedSupport.getModel();
    }

    public static void setRequest(BookingRequest request) {
        getModel().put(KEY_REQUEST, request);
    }

    public static BookingRequest getRequest() {
        return (BookingRequest) getModel().get(KEY_REQUEST);
    }

    public static void setResponseData(BookingResData resData) {
        getModel().put(KEY_RESPONSE_DATA, resData);
    }

    public static BookingResData getResponseData() {
        return (BookingResData) getModel().get(KEY_RESPONSE_DATA);
    }

    public static BookingData getBookingData() {
        return (BookingData) getModel().get(KEY_BOOK_DATA);
    }

    public static void setBookingData(BookingData bookingData) {
        getModel().put(KEY_BOOK_DATA, bookingData);
    }

    public static BookingReply getBookingReply() {
        return (BookingReply) getModel().get(KEY_BOOK_REPLY);
    }

    public static void setBookingReply(BookingReply bookingReply) {
        getModel().put(KEY_BOOK_REPLY, bookingReply);
    }
    
    public static List<PolicyDetail> getAopPolicyDetailList() {
        List<PolicyDetail> policyDetails = (List<PolicyDetail>) getModel().get(KEY_AOP_POLICY);
        if (null == policyDetails) {
            policyDetails = new ArrayList<>();
            getModel().put(KEY_AOP_POLICY, policyDetails);
        }
        return policyDetails;
    }

    public static Long getRequestId() {
        return (Long)getModel().get(KEY_REQUEST_ID);
    }
    
    public static void setRequestId(Long id) {
        getModel().put(KEY_REQUEST_ID, id);
    }
    
    @Override
    public boolean onBusinessException(BizzException ex) {
        return false;
    }

    @Override
    public boolean apply() {
        return false;
    }

    @Override
    public boolean processing() throws Exception {
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
