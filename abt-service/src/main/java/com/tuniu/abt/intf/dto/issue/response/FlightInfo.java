package com.tuniu.abt.intf.dto.issue.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Created by huangsizhou on 16/3/21.
 */
public class FlightInfo {

    private String flightNo;

    private String seatCode;

    @JsonIgnore
    @JSONField(serialize = false)
    private String orgAirportCode;

    @JsonIgnore
    @JSONField(serialize = false)
    private String dstAirportCode;

    @JsonIgnore
    @JSONField(serialize = false)
    private Date departureTime;

    @JsonIgnore
    @JSONField(serialize = false)
    private int rph;

    public FlightInfo(String flightNo, String seatCode) {
        this.flightNo = flightNo;
        this.seatCode = seatCode;
    }

    public FlightInfo() {
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getOrgAirportCode() {
        return orgAirportCode;
    }

    public void setOrgAirportCode(String orgAirportCode) {
        this.orgAirportCode = orgAirportCode;
    }

    public String getDstAirportCode() {
        return dstAirportCode;
    }

    public void setDstAirportCode(String dstAirportCode) {
        this.dstAirportCode = dstAirportCode;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public int getRph() {
        return rph;
    }

    public void setRph(int rph) {
        this.rph = rph;
    }
}
