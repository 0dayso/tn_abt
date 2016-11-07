package com.tuniu.abt.intf.dto.intf.airline;

public class AirDirectConnectPayParam {

    // *下单成功的订单号，该订单号是供应商提供的订单号
    private String orderId;

    // * 支付总价
    private int totalPrice;

    private String currency;

    // 出票等待超时时长（单位：秒）
    private int timeout;

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

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
