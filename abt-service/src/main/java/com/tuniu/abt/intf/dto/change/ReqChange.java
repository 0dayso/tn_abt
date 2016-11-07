package com.tuniu.abt.intf.dto.change;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuniu.abt.intf.dto.common.AopAttachment;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by chengyao on 2016/5/3.
 */
public class ReqChange implements Serializable {
    private static final long serialVersionUID = -9206996257575862196L;

    /**
     * 会话ID
     */
    @NotEmpty(message = "{change.transId.notEmpty}")
    private String transId;

    /**
     * 途牛订单号
     */
    @JsonProperty("orderIdTuniu")
    @NotNull(message = "{change.orderId.notEmpty}")
    @Range(message = "{change.orderId.range}")
    private Long orderId;

    /**
     * 航程标识
     */
    @NotNull(message = "{change.flightItemId.notEmpty}")
    @Range(message = "{change.flightItemId.range}")
    private Long flightItemId;

    /**
     * 回调地址
     */
    private String callback;

    /**
     * 改期类型
     */
    private String changeType;

    /**
     * 改期单失效时间
     */
    private Date failureTime;

    /**
     * 改期单合计金额
     */
    @NotNull(message = "{change.totalAmount.notNull}")
    private BigDecimal totalAmount;

    /**
     * 携程改升对应订单号
     */
    private String ctripOrderId;

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
    @NotEmpty(message = "{change.items.notEmpty}")
    @Valid
    private List<ReqChangeItem> items;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
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

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public Date getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCtripOrderId() {
        return ctripOrderId;
    }

    public void setCtripOrderId(String ctripOrderId) {
        this.ctripOrderId = ctripOrderId;
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

    public List<ReqChangeItem> getItems() {
        return items;
    }

    public void setItems(List<ReqChangeItem> items) {
        this.items = items;
    }
}
