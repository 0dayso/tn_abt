package com.tuniu.abt.intf.dto.backmeal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退改返回价格对象
 * Created by chengyao on 2016/3/26.
 */
public class RespBackMealPrice implements Serializable {

    private static final long serialVersionUID = 1298074317027760834L;

    /**
     * 退票费
     */
    private BigDecimal refundAmount;

    /**
     * 是否允许退票
     */
    private boolean refundAllow;

    /**
     * 退票费有效期
     */
    private Date refundExpires;

    /**
     * 改期费用
     */
    private BigDecimal changeAmount;

    /**
     * 是否允许改期
     */
    private boolean changeAllow;

    /**
     * 改期费有效期
     */
    private Date changeExpires;

    /**
     * 备注
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isRefundAllow() {
        return refundAllow;
    }

    public void setRefundAllow(boolean refundAllow) {
        this.refundAllow = refundAllow;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public boolean isChangeAllow() {
        return changeAllow;
    }

    public void setChangeAllow(boolean changeAllow) {
        this.changeAllow = changeAllow;
    }

    public Date getRefundExpires() {
        return refundExpires;
    }

    public void setRefundExpires(Date refundExpires) {
        this.refundExpires = refundExpires;
    }

    public Date getChangeExpires() {
        return changeExpires;
    }

    public void setChangeExpires(Date changeExpires) {
        this.changeExpires = changeExpires;
    }
}
