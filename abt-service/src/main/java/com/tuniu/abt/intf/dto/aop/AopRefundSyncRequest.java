package com.tuniu.abt.intf.dto.aop;

import com.tuniu.abt.intf.dto.common.AopAttachment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 退票通知单（ABT->AOP）
 * Created by chengyao on 2016/4/18.
 */
public class AopRefundSyncRequest implements Serializable {

    private static final long serialVersionUID = 2388086621778244826L;

    /**
     * 退票类型
     */
    private Integer refundType;

    /**
     * 单据状态：3=待确认
     */
    private Integer status = 3;

    /**
     * 开放平台出票单号
     */
    private String ticketOrderId;

    /**
     * 供应商ID
     */
    private String vendorId;

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * 退票备注
     */
    private String remark;

    /**
     * 订单合计金额
     */
    private BigDecimal totalAmount;

    /**
     * 附件
     */
    private List<AopAttachment> attachments;

    /**
     * 订单项目
     */
    private List<AopRefundSyncItem> items;

    public String getTicketOrderId() {
        return ticketOrderId;
    }

    public void setTicketOrderId(String ticketOrderId) {
        this.ticketOrderId = ticketOrderId;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<AopAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AopAttachment> attachments) {
        this.attachments = attachments;
    }

    public List<AopRefundSyncItem> getItems() {
        return items;
    }

    public void setItems(List<AopRefundSyncItem> items) {
        this.items = items;
    }
}
