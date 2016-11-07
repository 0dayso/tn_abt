package com.tuniu.abt.service.travelsky.dto;

import java.util.ArrayList;
import java.util.List;

public class AvFlightSegment {
    
    /**
     * 航司二字码
     */
    private String airline;
    
    /**
     * 航班号
     */
    private String flightNo;
    
    /**
     * 出发机场三字码
     */
    private String orgAirportIataCode;
    
    /**
     * 到达机场三字码
     */
    private String dstAirportIataCode;
    
    /**
     * 出发时间,格式:yyyy-MM-dd hh:mm:ss
     */
    private String departureDateTime;
    
    private List<AvSeatStatus> seatStatus;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getOrgAirportIataCode() {
        return orgAirportIataCode;
    }

    public void setOrgAirportIataCode(String orgAirportIataCode) {
        this.orgAirportIataCode = orgAirportIataCode;
    }

    public String getDstAirportIataCode() {
        return dstAirportIataCode;
    }

    public void setDstAirportIataCode(String dstAirportIataCode) {
        this.dstAirportIataCode = dstAirportIataCode;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public List<AvSeatStatus> getSeatStatus() {
        if (seatStatus == null) {
            seatStatus = new ArrayList<AvSeatStatus>();
        }
        return seatStatus;
    }

    public void setSeatStatus(List<AvSeatStatus> seatStatus) {
        this.seatStatus = seatStatus;
    }

}
