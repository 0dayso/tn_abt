package com.tuniu.abt.intf.dto.intf.airline;

public class AirDirectConnectPayResponse extends AirlineResponse {

    // 航司直连支付流水号
    private String payId;

    // 支付成功时间
    private String payTime;


    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }


}
