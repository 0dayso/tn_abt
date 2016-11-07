package com.tuniu.abt.intf.dto.book.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 占位（下单）请求对象
 * Created by chengyao on 2016/2/1.
 */
public class BookingRequest implements Serializable {

    private static final long serialVersionUID = -9054970636556506110L;
    
    /**
     * 占位的供应商渠道
     */
    @JsonProperty("c")
    @JSONField(name = "c")
    private String channel;
    
    /**
     * 供应商id:1航信,6携程,7联拓51book,8AOP开放平台,9航司直连
     */
    @NotNull(message = "{BookingRequest.vendorId.notEmpty}")
    private Integer vendorId;
    
    /**
     * 会话ID
     */
    @JsonProperty("transId")
    @JSONField(name = "transId")
    @NotBlank(message = "{BookingRequest.transId.notEmpty}")
    private String sessionId;

    /**
     * 订单号
     */
    @JsonProperty("orderIdTuniu")
    @JSONField(name = "orderIdTuniu")
    @NotNull(message = "{BookingRequest.orderIdTuniu.notEmpty}")
    private Long orderId;

    /**
     * 回调地址
     */
    private String callback;
    
    /**
     * 来源渠道
     */
    private String source;

    /**
     * 来源渠道的系统ID
     */
    private int systemId;

    /**
     * 下单数据
     */
    @NotNull(message = "{BookingRequest.d.notEmpty}")
    @Valid
    @JsonProperty("d")
    @JSONField(name = "d")
    private BookingDetail bookingDetail;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public BookingDetail getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(BookingDetail bookingDetail) {
        this.bookingDetail = bookingDetail;
    }

}
