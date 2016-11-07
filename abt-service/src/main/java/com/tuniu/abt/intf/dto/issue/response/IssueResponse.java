package com.tuniu.abt.intf.dto.issue.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by huangsizhou on 16/3/25.
 */
public class IssueResponse {

    private boolean success = true;
    private int errorCode;
    private String msg = "";
    private int systemId;
    private int intl;//国内0 1国际

    @JSONField(name = "transId")
    private String sessionId;

  /*  @JsonIgnore
    @JSONField(serialize = false)
    private String callback;*/

    @JsonProperty("orderIdTuniu")
    @JSONField(name = "orderIdTuniu")
    private String orderId;

    private IssueResultDetail data = new IssueResultDetail();

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

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
/*
    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }*/

    public int getIntl() {
        return intl;
    }

    public void setIntl(int intl) {
        this.intl = intl;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public IssueResultDetail getData() {
        return data;
    }

    public void setData(IssueResultDetail data) {
        this.data = data;
    }

    public void addPnrInfo(PnrInfo pnrInfo) {
        this.data.getPnrInfoList().add(pnrInfo);
    }

}
