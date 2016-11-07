package com.tuniu.vla.base.tsp;

import java.io.Serializable;

/**
 * Created by chengyao on 2015/11/20.
 */
public class ErrorResp implements Serializable {

    private static final long serialVersionUID = -7924078996896256731L;

    // 成功标记
    private boolean success = false;

    // 提示信息
    private String msg = "";

    // 错误码
    private String errorCode ;

    public ErrorResp() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
