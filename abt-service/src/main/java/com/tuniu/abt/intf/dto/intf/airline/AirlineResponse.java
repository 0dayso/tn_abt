package com.tuniu.abt.intf.dto.intf.airline;

/**
 * Created by chengyao on 2016/3/22.
 */
public class AirlineResponse {

    public boolean success;

    private int errorCode;

    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
