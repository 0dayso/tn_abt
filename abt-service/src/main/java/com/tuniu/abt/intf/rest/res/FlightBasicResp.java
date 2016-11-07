package com.tuniu.abt.intf.rest.res;

import java.util.List;

/**
 * 资源详情接口返回对象
 * Created by chengyao on 2015/11/28.
 */
public class FlightBasicResp {

    /**
     * 资源ID
     */
    private String resId;

    /**
     * 是否国内国际 0 国内 1 国际
     */
    private Integer international;

    /**
     * 航班组合
     */
    private String flightKey;

    /**
     * 舱位组合
     */
    private String cabinKey;

    /**
     * 航程类型
     */
    private Integer journeyType;

    /**
     * 时间间隔
     */
    private String timeSpan;

    /**
     * 价格信息
     */
    private String prices;

    /**
     * 有效标识
     */
    private Boolean valid;

    /**
     * 航程LIST
     */
    private List<FlightBasicRespFlight> flights;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public Integer getInternational() {
        return international;
    }

    public void setInternational(Integer international) {
        this.international = international;
    }

    public String getFlightKey() {
        return flightKey;
    }

    public void setFlightKey(String flightKey) {
        this.flightKey = flightKey;
    }

    public String getCabinKey() {
        return cabinKey;
    }

    public void setCabinKey(String cabinKey) {
        this.cabinKey = cabinKey;
    }

    public Integer getJourneyType() {
        return journeyType;
    }

    public void setJourneyType(Integer journeyType) {
        this.journeyType = journeyType;
    }

    public String getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public List<FlightBasicRespFlight> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightBasicRespFlight> flights) {
        this.flights = flights;
    }
}
