package com.tuniu.abt.intf.dto.intf.airline;

public class AirDirectConnectOccupyResponse extends AirlineResponse {

    private String orderId;
    private String orderCreateTime;
    private PriceInfo priceInfo;
    private int totalPrice;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public PriceInfo getPriceInfo() {
        return priceInfo;
    }
    public void setPriceInfo(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getOrderCreateTime() {
        return orderCreateTime;
    }
    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }
    
}
