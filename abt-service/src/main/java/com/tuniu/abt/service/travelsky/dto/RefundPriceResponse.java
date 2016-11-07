package com.tuniu.abt.service.travelsky.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退款返回信息封装
 * Created by chengyao on 2016/4/13.
 */
public class RefundPriceResponse implements Serializable {

    private static final long serialVersionUID = -6697218316550793964L;

    /** 毛退款额 **/
    private BigDecimal grossRefund;
    /** 净退款额 **/
    private BigDecimal netRefund;
    /** 扣款 **/
    private BigDecimal deduction;
    /** 税总 */
    private BigDecimal taxAll;
    /** 基础代理费 */
    private BigDecimal baseCommissionAmount;

    public BigDecimal getGrossRefund() {
        return grossRefund;
    }

    public void setGrossRefund(BigDecimal grossRefund) {
        this.grossRefund = grossRefund;
    }

    public BigDecimal getNetRefund() {
        return netRefund;
    }

    public void setNetRefund(BigDecimal netRefund) {
        this.netRefund = netRefund;
    }

    public BigDecimal getDeduction() {
        return deduction;
    }

    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
    }

    public BigDecimal getTaxAll() {
        return taxAll;
    }

    public void setTaxAll(BigDecimal taxAll) {
        this.taxAll = taxAll;
    }

    public BigDecimal getBaseCommissionAmount() {
        return baseCommissionAmount;
    }

    public void setBaseCommissionAmount(BigDecimal baseCommissionAmount) {
        this.baseCommissionAmount = baseCommissionAmount;
    }
}
