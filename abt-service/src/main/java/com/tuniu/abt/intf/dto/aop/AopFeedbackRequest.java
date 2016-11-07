package com.tuniu.abt.intf.dto.aop;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 开放平台操作结果通知请求
 *
 * Created by chengyao on 2015/12/5.
 */
public class AopFeedbackRequest implements Serializable {

    private static final long serialVersionUID = -1315717523007086424L;

    /**
     * 出票成功
     */
    public static final int OP_TYPE_TICKET_SUCCESS = 11;

    /**
     * 出票拒绝
     */
    public static final int OP_TYPE_TICKET_FAIL = 12;

    /**
     * 退票成功
     */
    public static final int OP_TYPE_REFUND_SUCCESS = 21;

    /**
     * 退票失败
     */
    public static final int OP_TYPE_REFUND_FAIL = 22;

    /**
     * 改期成功
     */
    public static final int OP_TYPE_CHANGE_SUCCESS = 31;

    /**
     * 改期失败
     */
    public static final int OP_TYPE_CHANGE_FAIL = 32;

    /**
     * 请求类型：11=出票成功，12=出票拒绝，21=退票成功， 31=改期成功，32=改期失败
     */
    private int opType;

    /**
     * 会话ID
     */
    @NotBlank(message = "{AopFeedbackRequest.sessionId.notEmpty}")
    private String sessionId;

    /**
     * 供应商ID
     */
    @NotNull(message = "{AopFeedbackRequest.vendorId.notEmpty}")
    private Integer vendorId;

    /**
     * 供应商名
     */
    @NotBlank(message = "{AopFeedbackRequest.vendorName.notEmpty}")
    private String vendorName;

    /**
     * 开放平台出票单号
     */
    private Long ticketOrderId;

    /**
     * 开放平台退票单号
     */
    private Long refundOrderId;

    /**
     * 开放平台改期单号
     */
    private Long changeOrderId;

    /**
     * 失败类型：1=超时，2=拒绝
     */
    private Integer failureType;

    /**
     * 拒绝原因：1=非合理拒绝，2=拉回，3=催单，4=取消，9=其他
     */
    private Integer refuseReason;

    /**
     * 备注说明
     */
    private String refuseRemark;

    /**
     * 子项目信息
     */
    @NotEmpty(message = "{AopFeedbackRequest.items.notEmpty}")
    @Valid
    private List<AopFeedbackItem> items;

    public Long getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(Long refundOrderId) {
        this.refundOrderId = refundOrderId;
    }

    public Long getChangeOrderId() {
        return changeOrderId;
    }

    public void setChangeOrderId(Long changeOrderId) {
        this.changeOrderId = changeOrderId;
    }

    public Long getTicketOrderId() {
        return ticketOrderId;
    }

    public void setTicketOrderId(Long ticketOrderId) {
        this.ticketOrderId = ticketOrderId;
    }

    public Integer getFailureType() {
        return failureType;
    }

    public void setFailureType(Integer failureType) {
        this.failureType = failureType;
    }

    public List<AopFeedbackItem> getItems() {
        return items;
    }

    public void setItems(List<AopFeedbackItem> items) {
        this.items = items;
    }

    public int getOpType() {
        return opType;
    }

    public void setOpType(int opType) {
        this.opType = opType;
    }

    public Integer getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(Integer refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getRefuseRemark() {
        return refuseRemark;
    }

    public void setRefuseRemark(String refuseRemark) {
        this.refuseRemark = refuseRemark;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
