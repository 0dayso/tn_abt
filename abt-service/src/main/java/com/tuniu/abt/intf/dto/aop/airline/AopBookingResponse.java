package com.tuniu.abt.intf.dto.aop.airline;

/**
 * Created by huanggao on 2016/4/21.
 */
public class AopBookingResponse {

    private boolean success;

    public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	private int errorCode;

    private String message;

    private AopBookingResult data;

    public AopBookingResult getData() {
        return data;
    }

    public void setData(AopBookingResult data) {
        this.data = data;
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
