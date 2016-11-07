package com.tuniu.abt.service.issue.airline;

/**
 * Created by huangsizhou on 16/3/24.
 */
public class AirLinePayReq {

    private String c;
    private String orderId;
    private int totalPrice;
    private String currency;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
