package com.tuniu.abt.intf.rest.res;

import java.util.List;

/**
 * rest接口返回的根节点对象（用于data为list）
 *
 * Created by chengyao on 2015/11/28.
 */
public class RootListResp<T> {

    private boolean success;
    private String msg;
    private int errorCode;
    private List<T> data;

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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
