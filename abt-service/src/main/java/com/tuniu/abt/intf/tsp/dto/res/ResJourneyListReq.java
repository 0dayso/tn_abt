package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/11.
 */
public class ResJourneyListReq implements Serializable {

    private static final long serialVersionUID = -5890000997335503525L;

    private String flightKey;
    private String cabinKey;
    private String cityKey;
    private String timeSpan;
    private Integer seatType;
    private String segSpan;
    private String cabinSeatType;

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

    public String getCityKey() {
        return cityKey;
    }

    public void setCityKey(String cityKey) {
        this.cityKey = cityKey;
    }

    public String getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    public String getSegSpan() {
        return segSpan;
    }

    public void setSegSpan(String segSpan) {
        this.segSpan = segSpan;
    }

    public String getCabinSeatType() {
        return cabinSeatType;
    }

    public void setCabinSeatType(String cabinSeatType) {
        this.cabinSeatType = cabinSeatType;
    }
}
