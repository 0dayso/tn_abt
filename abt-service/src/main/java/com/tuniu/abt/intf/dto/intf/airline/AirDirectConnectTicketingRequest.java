package com.tuniu.abt.intf.dto.intf.airline;

public class AirDirectConnectTicketingRequest {

    // * 渠道 如：MU
    private String c;

    private AirDirectConnectPayParam d;

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

}
