package com.tuniu.abt.intf.dto.book.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderInfo implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 7944539325414200373L;

    /**
     * 供应商子订单号
     */
    private String orderId;
    
    /**
     * pnr或外部供应商子订单号
     */
    private String pnr;
    
    /**
     * 供应商子订单号关联的订单号
     */
    private String parentOrderId;
    
    /**
     * 供应商id
     */
    private int vendorId;
    
    /**
     * 清位时间
     */
    private String clearTime;
    
    /**
     * 乘机人信息
     */
    private List<PassengerInfo> passengers;
    
    /**
     * 航班舱位信息
     */
    private List<FlightCabin> flightCabinList;
    
    /**
     * 价格详情
     */
    private PriceDetail priceDetail;
    
    /**
     * 退改签规则(Json串)
     */
    private String flightRCSPolicyData;

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

    public List<PassengerInfo> getPassengers() {
        if (null == passengers) {
            passengers = new ArrayList<PassengerInfo>();
        }
        return passengers;
    }

    public void setPassengers(List<PassengerInfo> passengers) {
        this.passengers = passengers;
    }

    public List<FlightCabin> getFlightCabinList() {
        if (null == flightCabinList) {
            flightCabinList = new ArrayList<FlightCabin>();
        }
        return flightCabinList;
    }

    public void setFlightCabinList(List<FlightCabin> flightCabinList) {
        this.flightCabinList = flightCabinList;
    }

    public PriceDetail getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(PriceDetail priceDetail) {
        this.priceDetail = priceDetail;
    }

    public String getFlightRCSPolicyData() {
        return flightRCSPolicyData;
    }

    public void setFlightRCSPolicyData(String flightRCSPolicyData) {
        this.flightRCSPolicyData = flightRCSPolicyData;
    }
}
