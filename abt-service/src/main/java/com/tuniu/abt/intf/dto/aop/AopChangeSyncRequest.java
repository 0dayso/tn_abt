package com.tuniu.abt.intf.dto.aop;

import com.tuniu.abt.intf.dto.common.AopAttachment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 改期通知单（ABT->AOP）
 * Created by chengyao on 2016/4/25.
 */
public class AopChangeSyncRequest implements Serializable {

    private static final long serialVersionUID = 1539210245472645325L;

    /**
     * 改期类型：1=改名, 2=改证件, 3=同舱改期, 4=升舱换开, 5=航变改升, 9=其他
     */
    private Integer changeType;

    /**
     * 单据状态：3=待改期
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
     * 改期单失效时间
     */
    private String failureTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 改期单金额
     */
    private BigDecimal totalAmount;

    /**
     * 附件
     */
    private List<AopAttachment> attachments;

    /**
     * 改期项目
     */
    private List<AopChangeSyncItem> items;

    public String getTicketOrderId() {
        return ticketOrderId;
    }

    public void setTicketOrderId(String ticketOrderId) {
        this.ticketOrderId = ticketOrderId;
    }

    public List<AopAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AopAttachment> attachments) {
        this.attachments = attachments;
    }

    public List<AopChangeSyncItem> getItems() {
        return items;
    }

    public void setItems(List<AopChangeSyncItem> items) {
        this.items = items;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
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

    public String getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(String failureTime) {
        this.failureTime = failureTime;
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
}
