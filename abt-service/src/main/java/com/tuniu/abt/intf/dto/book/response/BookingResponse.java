package com.tuniu.abt.intf.dto.book.response;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/2/15.
 */
public class BookingResponse implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 3548052541625284363L;

    /**
     * 成功标志位
     */
    private boolean success;
    
    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 会话ID
     */
    private String transId;

    /**
     * 系统id(国内底层7)
     */
    private String systemId;

    /**
     * 响应数据
     */
    private BookingResData data;

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

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public BookingResData getData() {
        return data;
    }

    public void setData(BookingResData data) {
        this.data = data;
    }
    
}
