package com.tuniu.abt.intf.dto.intf.airline;

public class AirDirectConnectPayRequest {

    // * 渠道 如：MU
    private String c;

    private AirDirectConnectPayParam d;

    // 回调地址，如果 callback 为空则为同步返回，否则为采用回调的方式异步返回
    private String callback;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public AirDirectConnectPayParam getD() {
        return d;
    }

    public void setD(AirDirectConnectPayParam d) {
        this.d = d;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

}
