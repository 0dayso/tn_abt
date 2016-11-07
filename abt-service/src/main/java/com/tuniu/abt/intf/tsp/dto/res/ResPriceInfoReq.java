package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/9.
 */
public class ResPriceInfoReq implements Serializable {

    private static final long serialVersionUID = -6486334181344869498L;

    /**
     * 航班号，多段用#连接，例如：CA1503#CA1820
     */
    private String flightKey;

    /**
     * 出发到达城市三字码，多段用#号连接，例如：PEK-NKG#NKG-PEK
     */
    private String cityKey;

    /**
     * 出发日期
     */
    private String departureDate;

    /**
     * 人员类型：ADT,CHD,BAB (可空)
     */
    private String passengerCode;

    /**
     * 供应商id
     */
    private Integer vendorId;

    /**
     * 多段时间间隔，多个区间用#连接
     */
    private String timeSpan;

    public String getFlightKey() {
        return flightKey;
    }

    public void setFlightKey(String flightKey) {
        this.flightKey = flightKey;
    }

    public String getCityKey() {
        return cityKey;
    }

    public void setCityKey(String cityKey) {
        this.cityKey = cityKey;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getPassengerCode() {
        return passengerCode;
    }

    public void setPassengerCode(String passengerCode) {
        this.passengerCode = passengerCode;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }
}
