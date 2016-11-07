package com.tuniu.abt.intf.dto.intf.airline;

public class AirDirectConnectCabinPriceParamRequest {

    // * 要查询的渠道
    private String c;

    private CabinCheckParam d;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public CabinCheckParam getD() {
        return d;
    }

    public void setD(CabinCheckParam d) {
        this.d = d;
    }

}
