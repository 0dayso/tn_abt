package com.tuniu.abt.intf.dto.aop.airline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 取消占位（取消订单）请求对象
 */
public class AopReqCancel{

    /**
     * 渠道
     */
    private String channel;

    /**
     * 途牛订单号
     */
    private Long orderId;

    /**
     * 供应商id
     */
    private Integer vendorId;

    /**
     * 来源
     */
    private String source;

    /**
     * 系统ID
     */
    private Integer systemId;

    /**
     * 回调地址
     */
    private String callback;

    /**
     * 会话ID，建议使用当前时间戳
     */
    private String transId;

    /**
     * 数据
     */
    private AopReqCancelDetail cancelDetail;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public AopReqCancelDetail getCancelDetail() {
        return cancelDetail;
    }

    public void setCancelDetail(AopReqCancelDetail cancelDetail) {
        this.cancelDetail = cancelDetail;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

}
