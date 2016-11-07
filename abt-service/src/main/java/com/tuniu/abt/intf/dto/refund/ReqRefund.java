package com.tuniu.abt.intf.dto.refund;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuniu.abt.intf.dto.common.AopAttachment;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * 退票请求
 *
 * Created by chengyao on 2016/3/25.
 */
public class ReqRefund implements Serializable {

    private static final long serialVersionUID = -940650465867126173L;

    public static final String DEFAULT_SEGMENT_POSITION = "未打印";
    public static final boolean DEFAULT_RECEIVED_SEGMENT = false;

    /**
     * 会话ID
     */
    @NotEmpty(message = "{refund.transId.notEmpty}")
    private String transId;

    /**
     * 途牛订单号
     */
    @JsonProperty("orderIdTuniu")
    @NotNull(message = "{refund.orderId.notEmpty}")
    @Range(message = "{refund.orderId.range}")
    private Long orderId;

    /**
     * 航程标识
     */
    @NotNull(message = "{refund.flightItemId.notEmpty}")
    @Range(message = "{refund.flightItemId.range}")
    private Long flightItemId;

    /**
     * 回调地址
     */
    private String callback;

    /**
     * 退票类型
     */
    @NotEmpty(message = "{refund.refundType.notEmpty}")
    @Pattern(regexp = "^[0-9]*$", message = "{refund.refundType.invalid}")
    private String refundType;

    /**
     * A自动退票， M客服退票
     */
    @NotEmpty(message = "{refund.refundSource.notEmpty}")
    private String refundSource;

    // 是否收到行程单 0：收到 1：未收到
    private Boolean receivedSegment = Boolean.FALSE;

    // 行程单位置 客人、票台、机场 . 未知 . 未打印. 配送中. 办理挂失. 其他
    private String segmentPosition;

    /**
     * 备注
     */
    private String remark;

    /**
     * 附件
     */
    private List<AopAttachment> attachments;

    /**
     * 退票项目
     */
    @NotEmpty(message = "{refund.items.notEmpty}")
    @Valid
    private List<ReqRefundItem> items;

    public String getRefundSource() {
        return refundSource;
    }

    public void setRefundSource(String refundSource) {
        this.refundSource = refundSource;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFlightItemId() {
        return flightItemId;
    }

    public void setFlightItemId(Long flightItemId) {
        this.flightItemId = flightItemId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<AopAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AopAttachment> attachments) {
        this.attachments = attachments;
    }

    public List<ReqRefundItem> getItems() {
        return items;
    }

    public void setItems(List<ReqRefundItem> items) {
        this.items = items;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public Boolean getReceivedSegment() {
        return receivedSegment;
    }

    public void setReceivedSegment(Boolean receivedSegment) {
        this.receivedSegment = receivedSegment;
    }

    public String getSegmentPosition() {
        return segmentPosition;
    }

    public void setSegmentPosition(String segmentPosition) {
        this.segmentPosition = segmentPosition;
    }
}
