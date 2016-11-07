package com.tuniu.abt.intf.tsp.dto.res;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 散客票价格实体类
 *
 * @author mujunfeng
 * @since 2014-11-10
 */
public class FlightIndivPrice {
    // 主键ID
    private long id;

    // 基本运价
    private Integer baseFare;

    /**
     * 成本价
     */
    private Integer cost;

    // 税
    private Integer taxes;

    // 费
    private Integer qcharge;

    // 货币类型
    private String currencyCode;

    // 舱位折扣
    private double discount;

    // 系统运价数据来源
    private String pricingSource;

    // 旅客类型 ADT 和CHD 等
    private String passengerCode;

    // 旅客数量
    private Integer passengerQuantity;

    // 运价基础代码
    private String fareBasicCode;

    // 代理费类型，GROSS, NET, BOTH
    private String commissionType;

    // 代理费百分比
    private Integer commissionPercent;

    // 行李数量重量
    private String baggage;

    // 退改签ID
    private Integer ruleId;

    // 供应商ID
    private Integer vendorId;

    // 资源ID
    private Integer resId;

    // 团期
    @JSONField(format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    // 后续查询退改签规则用
    private String ref1;

    // 后续查询退改签规则用
    private String ref2;

    // 有效标识
    private Integer delFlag;

    // 舱位余位  add by tangchd at 2015-04-20
    private String seatStatus;

    // 携程票面价 add by tangchd at 2015-05-06
    private Integer printPrice;

    // 携程使用 add by tangchd at 2015-05-06
    private Integer fdPrice;

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Integer baseFare) {
        this.baseFare = baseFare;
    }

    public Integer getTaxes() {
        return taxes;
    }

    public void setTaxes(Integer taxes) {
        this.taxes = taxes;
    }

    public Integer getQcharge() {
        return qcharge;
    }

    public void setQcharge(Integer qcharge) {
        this.qcharge = qcharge;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPricingSource() {
        return pricingSource;
    }

    public void setPricingSource(String pricingSource) {
        this.pricingSource = pricingSource;
    }

    public String getPassengerCode() {
        return passengerCode;
    }

    public void setPassengerCode(String passengerCode) {
        this.passengerCode = passengerCode;
    }

    public Integer getPassengerQuantity() {
        return passengerQuantity;
    }

    public void setPassengerQuantity(Integer passengerQuantity) {
        this.passengerQuantity = passengerQuantity;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    public Integer getCommissionPercent() {
        return commissionPercent;
    }

    public void setCommissionPercent(Integer commissionPercent) {
        this.commissionPercent = commissionPercent;
    }

    public String getFareBasicCode() {
        return fareBasicCode;
    }

    public void setFareBasicCode(String fareBasicCode) {
        this.fareBasicCode = fareBasicCode;
    }

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(String seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Integer getPrintPrice() {
        return printPrice;
    }

    public void setPrintPrice(Integer printPrice) {
        this.printPrice = printPrice;
    }

    public Integer getFdPrice() {
        return fdPrice;
    }

    public void setFdPrice(Integer fdPrice) {
        this.fdPrice = fdPrice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FlightIndivPrice [id=").append(id).append(", baseFare=").append(baseFare).append(", cost=")
                .append(cost).append(", taxes=").append(taxes).append(", qcharge=").append(qcharge)
                .append(", currencyCode=").append(currencyCode).append(", discount=").append(discount)
                .append(", pricingSource=").append(pricingSource).append(", passengerCode=").append(passengerCode)
                .append(", passengerQuantity=").append(passengerQuantity).append(", fareBasicCode=")
                .append(fareBasicCode).append(", commissionType=").append(commissionType).append(", commissionPercent=")
                .append(commissionPercent).append(", baggage=").append(baggage).append(", ruleId=").append(ruleId)
                .append(", vendorId=").append(vendorId).append(", resId=").append(resId).append(", departureDate=")
                .append(departureDate).append(", ref1=").append(ref1).append(", ref2=").append(ref2)
                .append(", delFlag=")
                .append(delFlag).append(", seatStatus=").append(seatStatus).append(", printPrice=").append(printPrice)
                .append(", fdPrice=").append(fdPrice).append("]");
        return builder.toString();
    }

}
