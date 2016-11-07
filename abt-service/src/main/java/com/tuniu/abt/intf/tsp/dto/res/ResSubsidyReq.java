package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/11.
 */
public class ResSubsidyReq implements Serializable {

    private static final long serialVersionUID = -1715340053147908561L;

    private Integer resId;

    private Integer vendorId;

    private String departureDate;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
