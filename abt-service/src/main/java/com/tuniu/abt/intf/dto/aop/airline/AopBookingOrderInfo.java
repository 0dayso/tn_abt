package com.tuniu.abt.intf.dto.aop.airline;

import java.util.List;

/**
 * Created by huanggao on 2016/4/25.
 */
public class AopBookingOrderInfo {

    /**
     * String 供应商子订单号
     */
    private String orderId;

    /**
     * pnr或外部供应商订单号（春秋航空只有pnr）
     */
    private String pnr;

    /**
     * 如果一个儿童的PNR在一个成人的PNR下，则成人PNR的该字段为空，儿童PNR的该字段填成人的供应商子订单号
     */
    private String parentOrderId;

    /**
     * 供应商ID
     */
    private int vendorId;

    /**
     * 清位时间（占位时间+半小时）
     */
    private String clearTime;

    /**
     * 乘客信息
     */
    private List<AopPassenger> passengers;

    /**
     * 航班舱位信息
     */
    private List<AopFlightCabin> flightCabinList;

    /**
     * 价格信息
     */
    private AopPriceDetail priceDetail;

    /**
     * 退改签规则信息
     */
    private FlightRCSPolicyData flightRCSPolicyData;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getClearTime() {
        return clearTime;
    }

    public void setClearTime(String clearTime) {
        this.clearTime = clearTime;
    }

    public List<AopPassenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<AopPassenger> passengers) {
        this.passengers = passengers;
    }

    public List<AopFlightCabin> getFlightCabinList() {
        return flightCabinList;
    }

    public void setFlightCabinList(List<AopFlightCabin> flightCabinList) {
        this.flightCabinList = flightCabinList;
    }

    public AopPriceDetail getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(AopPriceDetail priceDetail) {
        this.priceDetail = priceDetail;
    }

    public FlightRCSPolicyData getFlightRCSPolicyData() {
        return flightRCSPolicyData;
    }

    public void setFlightRCSPolicyData(FlightRCSPolicyData flightRCSPolicyData) {
        this.flightRCSPolicyData = flightRCSPolicyData;
    }
}
