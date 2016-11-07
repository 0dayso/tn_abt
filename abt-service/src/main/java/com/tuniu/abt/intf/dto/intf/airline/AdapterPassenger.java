package com.tuniu.abt.intf.dto.intf.airline;

import java.math.BigDecimal;
import java.util.Date;

public class AdapterPassenger {

    // 供应商订单号
    private String subOrderId;

    // 旅客姓名
    private String passengerName;

    // 旅客ID
    private int personId;

    // 人员类型
    private int personType;

    // 退票手续费
    private String refundFee;

    // 退票费率
    private String refundRate;

    // 退票类型
    private String refundType;

    // 是否收到行程单
    private int recieveSement;

    // 行程单位置
    private String segmentPosition;

    // 是否自动退票
    private boolean refundFeeAvailable;

    // 退票原因备注类型
    private String refundReasonRemark;

    // 飞享金
    private BigDecimal refundFlyFee;

    // 退票申请时间
    private Date applyRefundTime;

    // 出发城市名称
    private String orgCityName;

    // 出发城市三字码
    private String orgCityIataCode;

    // 抵达城市名称
    private String dstCityName;

    // 抵达城市三字码
    private String dstCityIataCode;

    // 出发机场三字码
    private String orgAirportIataCode;

    // 到达机场三字码
    private String dstAirportIataCode;

    // 航班号
    private String flightNo;

    // 出发日期时间
    private String departureTime;

    // 成本价
    private BigDecimal costPrice;

    // 票号
    private String ticketNo;

    // PNR
    private String pnr;

    // 舱位
    private String seatCode;

    // 对供应商退款
    private BigDecimal vendorRefundFee;

    // 供应商ID
    private int vendorId;

    // 航司直连查询是否可退票返回的参数
    private String extraInfo;

    // 出发机场名称
    private String orgAirportName;

    // 抵达机场名称
    private String dstAirportName;

    // 售卖票价
    private String salePrice;

    // 税费
    private String taxPrice;

    // 供应商名字
    private String vendorName;

    // 退改签规则
    private String ruleInfos;

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundRate() {
        return refundRate;
    }

    public void setRefundRate(String refundRate) {
        this.refundRate = refundRate;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public int getRecieveSement() {
        return recieveSement;
    }

    public void setRecieveSement(int recieveSement) {
        this.recieveSement = recieveSement;
    }

    public String getSegmentPosition() {
        return segmentPosition;
    }

    public void setSegmentPosition(String segmentPosition) {
        this.segmentPosition = segmentPosition;
    }

    public boolean isRefundFeeAvailable() {
        return refundFeeAvailable;
    }

    public void setRefundFeeAvailable(boolean refundFeeAvailable) {
        this.refundFeeAvailable = refundFeeAvailable;
    }

    public String getRefundReasonRemark() {
        return refundReasonRemark;
    }

    public void setRefundReasonRemark(String refundReasonRemark) {
        this.refundReasonRemark = refundReasonRemark;
    }

    public BigDecimal getRefundFlyFee() {
        return refundFlyFee;
    }

    public void setRefundFlyFee(BigDecimal refundFlyFee) {
        this.refundFlyFee = refundFlyFee;
    }

    public Date getApplyRefundTime() {
        return applyRefundTime;
    }

    public void setApplyRefundTime(Date applyRefundTime) {
        this.applyRefundTime = applyRefundTime;
    }

    public String getOrgCityName() {
        return orgCityName;
    }

    public void setOrgCityName(String orgCityName) {
        this.orgCityName = orgCityName;
    }

    public String getOrgCityIataCode() {
        return orgCityIataCode;
    }

    public void setOrgCityIataCode(String orgCityIataCode) {
        this.orgCityIataCode = orgCityIataCode;
    }

    public String getDstCityName() {
        return dstCityName;
    }

    public void setDstCityName(String dstCityName) {
        this.dstCityName = dstCityName;
    }

    public String getDstCityIataCode() {
        return dstCityIataCode;
    }

    public void setDstCityIataCode(String dstCityIataCode) {
        this.dstCityIataCode = dstCityIataCode;
    }

    public String getOrgAirportIataCode() {
        return orgAirportIataCode;
    }

    public void setOrgAirportIataCode(String orgAirportIataCode) {
        this.orgAirportIataCode = orgAirportIataCode;
    }

    public String getDstAirportIataCode() {
        return dstAirportIataCode;
    }

    public void setDstAirportIataCode(String dstAirportIataCode) {
        this.dstAirportIataCode = dstAirportIataCode;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public BigDecimal getVendorRefundFee() {
        return vendorRefundFee;
    }

    public void setVendorRefundFee(BigDecimal vendorRefundFee) {
        this.vendorRefundFee = vendorRefundFee;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getOrgAirportName() {
        return orgAirportName;
    }

    public void setOrgAirportName(String orgAirportName) {
        this.orgAirportName = orgAirportName;
    }

    public String getDstAirportName() {
        return dstAirportName;
    }

    public void setDstAirportName(String dstAirportName) {
        this.dstAirportName = dstAirportName;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getRuleInfos() {
        return ruleInfos;
    }

    public void setRuleInfos(String ruleInfos) {
        this.ruleInfos = ruleInfos;
    }

}
