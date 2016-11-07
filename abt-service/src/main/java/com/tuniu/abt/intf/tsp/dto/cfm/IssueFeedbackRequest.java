package com.tuniu.abt.intf.tsp.dto.cfm;

import java.io.Serializable;

/**
 * Created by huangsizhou on 16/3/11.
 */
public class IssueFeedbackRequest implements Serializable{

    private boolean success;

    private int errorCode = 0;

    private String msg;

    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
