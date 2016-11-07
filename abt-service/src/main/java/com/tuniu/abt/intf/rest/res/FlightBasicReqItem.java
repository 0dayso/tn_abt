package com.tuniu.abt.intf.rest.res;

/**
 * 请求参数，资源团期项目
 * Created by chengyao on 2015/11/28.
 */
public class FlightBasicReqItem {

    /**
     * 资源ID
     */
    private Integer resId;

    /**
     * 出发时间
     */
    private String departureDate;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
