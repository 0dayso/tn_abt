package com.tuniu.abt.intf.dto.aop;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * aop 出票项目
 * Created by chengyao on 2016/3/24.
 */
public class AopTicketSyncItem implements Serializable {

    private static final long serialVersionUID = -7623639155878767369L;

    /**
     * 航段信息（出发城市三字码/到达城市三字码）
     */
    private String leg;

    /**
     * FAB ID
     */
    private Long personId;

    /**
     * 乘客类型
     */
    private Integer personType;

    /**
     * PNR编号
     */
    private String pnrCode;

    /**
     * 航司大编码
     */
    private String airCompanyCode;

    /**
     * 票面价
     */
    private BigDecimal ticketPrice;

    /**
     * 税费
     */
    private BigDecimal taxFee;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getLeg() {
        return leg;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public String getPnrCode() {
        return pnrCode;
    }

    public void setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
    }

    public String getAirCompanyCode() {
        return airCompanyCode;
    }

    public void setAirCompanyCode(String airCompanyCode) {
        this.airCompanyCode = airCompanyCode;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public BigDecimal getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(BigDecimal taxFee) {
        this.taxFee = taxFee;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
}
