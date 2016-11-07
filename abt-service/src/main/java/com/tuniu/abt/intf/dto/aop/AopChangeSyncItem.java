package com.tuniu.abt.intf.dto.aop;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退票通知单项目（ABT->AOP）
 * Created by chengyao on 2016/4/25.
 */
public class AopChangeSyncItem implements Serializable {

    private static final long serialVersionUID = -8655411196108344375L;

    /**
     * 更改费
     */
    private BigDecimal changeFee;

    /**
     * 升舱费
     */
    private BigDecimal upgradeFee;

    /**
     * 手续费
     */
    private BigDecimal commissionFee;

    /**
     * 航段信息（出发城市三字码/到达城市三字码）
     */
    private String leg;

    /**
     * FAB ID
     */
    private Long personId;

    /**
     * 新的PNR编号
     */
    private String pnrCode;

    /**
     * 新的人员类型：1=成人，2=儿童，3=婴儿
     */
    private Integer personType;

    /**
     * 修改项目备注，改了什么，原记录，新纪录各为什么
     */
    private String remark;

    /**
     * 新的乘客信息ID，FAB ID
     */
    private Long newPersonId;

    /**
     * 新的航班信息
     */
    private AopTicketSyncSegment res;

    public BigDecimal getChangeFee() {
        return changeFee;
    }

    public void setChangeFee(BigDecimal changeFee) {
        this.changeFee = changeFee;
    }

    public BigDecimal getUpgradeFee() {
        return upgradeFee;
    }

    public void setUpgradeFee(BigDecimal upgradeFee) {
        this.upgradeFee = upgradeFee;
    }

    public BigDecimal getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(BigDecimal commissionFee) {
        this.commissionFee = commissionFee;
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

    public String getPnrCode() {
        return pnrCode;
    }

    public void setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getNewPersonId() {
        return newPersonId;
    }

    public void setNewPersonId(Long newPersonId) {
        this.newPersonId = newPersonId;
    }

    public AopTicketSyncSegment getRes() {
        return res;
    }

    public void setRes(AopTicketSyncSegment res) {
        this.res = res;
    }
}
