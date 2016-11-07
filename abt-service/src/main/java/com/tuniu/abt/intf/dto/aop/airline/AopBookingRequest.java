package com.tuniu.abt.intf.dto.aop.airline;

/**
 * Created by huanggao on 2016/4/20.
 */
public class AopBookingRequest{

    public static final int POLICY_TYPE_AIRLINE = 3;

    /**
     * 途牛订单号
     */
    private String orderId;
    /**
     * 占位的供应商渠道
     */
    private String channel;

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * 请求来源
     */
    private String source;

    /**
     * 下单数据
     */
    private AopBookingDetail bookingDetail;

    /**
     * 来源渠道系统ID
     */
    private int systemId;

    /**
     * 政策id
     * @return
     */
    private long policyID;

    /**
     * 政策类型,伪直连默认为3
     */
    private int policyType;

    public long getPolicyID() {
        return policyID;
    }

    public void setPolicyID(long policyID) {
        this.policyID = policyID;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public AopBookingDetail getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(AopBookingDetail bookingDetail) {
        this.bookingDetail = bookingDetail;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getPolicyType() {
        return policyType;
    }

    public void setPolicyType(int policyType) {
        this.policyType = policyType;
    }
}
