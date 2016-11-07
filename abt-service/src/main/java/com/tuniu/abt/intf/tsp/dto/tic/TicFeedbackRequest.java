package com.tuniu.abt.intf.tsp.dto.tic;

import com.tuniu.abt.intf.constants.BizzConstants;

import java.io.Serializable;

/**
 * 通用异步回调请求对象
 * Created by chengyao on 2016/3/18.
 */
public class TicFeedbackRequest implements Serializable {

    private static final long serialVersionUID = 1288136140100378767L;

    private boolean success = true;

    private int errorCode = 0;

    private String msg;

    private String transId;

    private int systemId = BizzConstants.SYSTEM_ME;

    private String orderIdTuniu;

    // 国内国际
    private int intl = 0;

    private Object data;

    public String getOrderIdTuniu() {
        return orderIdTuniu;
    }

    public void setOrderIdTuniu(String orderIdTuniu) {
        this.orderIdTuniu = orderIdTuniu;
    }

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

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getIntl() {
        return intl;
    }

    public void setIntl(int intl) {
        this.intl = intl;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
