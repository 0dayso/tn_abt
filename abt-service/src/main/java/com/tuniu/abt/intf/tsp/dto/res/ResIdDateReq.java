package com.tuniu.abt.intf.tsp.dto.res;

import java.util.Date;

/**
 * Created by chengyao on 2016/1/16.
 */
public class ResIdDateReq {

    private int resId;

    private Date departureDate;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public ResIdDateReq() {
    }

    public ResIdDateReq(int resId) {
        this.resId = resId;
    }

    public ResIdDateReq(int resId, Date departureDate) {
        this.resId = resId;
        this.departureDate = departureDate;
    }

    public static ResIdDateReq build(int resId) {
        return new ResIdDateReq(resId);
    }

    public static ResIdDateReq build(int resId, Date departureDate) {
        return new ResIdDateReq(resId, departureDate);
    }
}
