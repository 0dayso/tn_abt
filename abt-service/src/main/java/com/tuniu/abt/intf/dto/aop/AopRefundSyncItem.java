package com.tuniu.abt.intf.dto.aop;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退票单具体项目
 *
 * Created by chengyao on 2016/4/18.
 */
public class AopRefundSyncItem implements Serializable {

    private static final long serialVersionUID = 8233729625352378797L;

    /**
     * 退单类型：1=全退
     */
    private Integer refundItemType = 1;

    /**
     * 航段信息（出发城市三字码/到达城市三字码）
     */
    private String leg;

    /**
     * 乘客ID
     */
    private Long personId;

    /**
     * 退票费（供应商退票手续费）
     */
    private BigDecimal subAmount;

    /**
     * 手续费（服务费，目前为0，前端UI不展示）
     */
    private BigDecimal commissionFee = new BigDecimal(0);

    /**
     * 退单项目备注，退哪个航段
     */
    private String remark;

    public Integer getRefundItemType() {
        return refundItemType;
    }

    public void setRefundItemType(Integer refundItemType) {
        this.refundItemType = refundItemType;
    }

    public String getLeg() {
        return leg;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public BigDecimal getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(BigDecimal subAmount) {
        this.subAmount = subAmount;
    }

    public BigDecimal getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(BigDecimal commissionFee) {
        this.commissionFee = commissionFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
