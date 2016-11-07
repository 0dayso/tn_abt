package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/9.
 */
public class ResPriceListReq implements Serializable {

    private static final long serialVersionUID = 7930063229475502237L;

    private int resId;
    private String departureDate;
    private int vendorId;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
