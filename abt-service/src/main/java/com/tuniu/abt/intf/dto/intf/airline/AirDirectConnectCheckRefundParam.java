package com.tuniu.abt.intf.dto.intf.airline;

public class AirDirectConnectCheckRefundParam {

    // 航司直连生成的订单号
    private String orderId;

    // * 退票类型 100=自愿， 200=非自愿
    private int refundType;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getRefundType() {
        return refundType;
    }

    public void setRefundType(int refundType) {
        this.refundType = refundType;
    }

}
