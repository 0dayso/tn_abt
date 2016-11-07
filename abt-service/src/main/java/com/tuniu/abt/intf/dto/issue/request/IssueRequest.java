package com.tuniu.abt.intf.dto.issue.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuniu.abt.validator.annotation.ValidIssue;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by huangsizhou on 16/3/17.
 */
@ValidIssue
public class IssueRequest implements Serializable {

    /**
     * 会话ID
     */
    @NotEmpty(message = "{IssueRequest.transId.notEmpty}")
    @JsonProperty("transId")
    private String sessionId;

    /**
     * 订单号
     */
    @NotEmpty(message = "{IssueRequest.orderIdTuniu.notEmpty}")
    @JsonProperty("orderIdTuniu")
    private String orderId;

    /**
     * 供应商ID
     */
    private int vendorId;

    /**
     * 系统ID
     */
    private int systemId;

    /**
     * 来源渠道
     */
    private String source;

    /**
     * 回调服务名
     */
    private String callback;

    @NotNull(message = "{IssueDetail.orderIds.notEmpty}")
    @Valid
    @JsonProperty("d")
    private IssueDetail issueDetail;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public IssueDetail getIssueDetail() {
        return issueDetail;
    }

    public void setIssueDetail(IssueDetail issueDetail) {
        this.issueDetail = issueDetail;
    }
}

