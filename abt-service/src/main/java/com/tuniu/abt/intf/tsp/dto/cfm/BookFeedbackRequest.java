package com.tuniu.abt.intf.tsp.dto.cfm;

import java.io.Serializable;

import com.tuniu.abt.intf.dto.book.response.BookingResponse;

/**
 * 占位回调请求对象
 * Created by chengyao on 2015/12/5.
 */
public class BookFeedbackRequest implements Serializable {

    private static final long serialVersionUID = -3658905018005413423L;

    private boolean success;

    private int errorCode;

    private String msg;

    private BookingResponse data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BookingResponse getData() {
        return data;
    }

    public void setData(BookingResponse bookingResponse) {
        this.data = bookingResponse;
    }
}
