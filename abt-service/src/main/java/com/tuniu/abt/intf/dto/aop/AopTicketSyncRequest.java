package com.tuniu.abt.intf.dto.aop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 出票通知单（ABT->AOP）
 * Created by chengyao on 2016/3/23.
 */
public class AopTicketSyncRequest implements Serializable {

    private static final long serialVersionUID = -2067293641726347871L;

    /**
     * 请求类型：2=供应商出票
     */
    private Integer ticketType = 2;

    /**
     * 会话号
     */
    private String sessionId;

    /**
     * 单据状态：3=待出票
     */
    private Integer status = 3;

    /**
     * 政策ID
     */
    private Integer policyId;

    /**
     * 供应商ID（开放平台供应商）
     */
    private String vendorId;

    /**
     * 途牛订单号
     */
    private Long orderId;

    /**
     * 清位时间
     */
    private String clearPositionTime;

    /**
     * 订单合计金额
     */
    private BigDecimal totalAmount;

    /**
     * 航段信息
     */
    private AopTicketSyncSegment segment;

    /**
     * 出票项目
     */
    private List<AopTicketSyncItem> items;

    public Integer getTicketType() {
        return ticketType;
    }

    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public AopTicketSyncSegment getSegment() {
        return segment;
    }

    public void setSegment(AopTicketSyncSegment segment) {
        this.segment = segment;
    }

    public String getClearPositionTime() {
        return clearPositionTime;
    }

    public void setClearPositionTime(String clearPositionTime) {
        this.clearPositionTime = clearPositionTime;
    }

    public List<AopTicketSyncItem> getItems() {
        return items;
    }

    public void setItems(List<AopTicketSyncItem> items) {
        this.items = items;
    }
}
