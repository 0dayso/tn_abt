package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

public class ResSegment implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 5064060628698829462L;

    /**
     * 航班号
     */
    private String flightNo;
    
    /**
     * 舱位
     */
    private String seatCode;
    
    /**
     * 出发城市三字码
     */
    private String orgCityIataCode;
    
    /**
     * 到达城市三字码
     */
    private String dstCityIataCode;
    
    /**
     * 出发日期
     */
    private String departureDate;

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

    public String getOrgCityIataCode() {
        return orgCityIataCode;
    }

    public void setOrgCityIataCode(String orgCityIataCode) {
        this.orgCityIataCode = orgCityIataCode;
    }

    public String getDstCityIataCode() {
        return dstCityIataCode;
    }

    public void setDstCityIataCode(String dstCityIataCode) {
        this.dstCityIataCode = dstCityIataCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

}
