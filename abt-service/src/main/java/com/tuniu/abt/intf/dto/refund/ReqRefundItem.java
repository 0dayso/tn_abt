package com.tuniu.abt.intf.dto.refund;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退票项目
 *
 * Created by chengyao on 2016/3/25.
 */
public class ReqRefundItem implements Serializable {

    private static final long serialVersionUID = -3393872370961239096L;

    /**
     * 退单类型：1=全退
     */
    private Integer refundItemType;

    /**
     * 退票费
     */
    @NotNull(message = "{refund.items.refundAmount.notEmpty}")
    @Range(message = "{refund.items.refundAmount.range}")
    private BigDecimal refundAmount;

    /**
     * 服务费
     */
    private BigDecimal commissionFee;

    /**
     * 票号
     */
    @NotEmpty(message = "{refund.items.ticketNo.notEmpty}")
    private String ticketNo;

    /**
     * 备注
     */
    private String remark;

    public Integer getRefundItemType() {
        return refundItemType;
    }

    public void setRefundItemType(Integer refundItemType) {
        this.refundItemType = refundItemType;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(BigDecimal commissionFee) {
        this.commissionFee = commissionFee;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
