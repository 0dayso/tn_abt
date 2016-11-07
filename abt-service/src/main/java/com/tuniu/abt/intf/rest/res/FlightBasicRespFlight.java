package com.tuniu.abt.intf.rest.res;

import java.util.List;

/**
 * 航程信息
 * Created by chengyao on 2015/11/28.
 */
public class FlightBasicRespFlight {

    /**
     * 航程序号
     */
    private Integer journeyRph;

    /**
     * 出发城市三字码
     */
    private String departureCityIataCode;

    /**
     * 出发城市
     */
    private String departureCityName;

    /**
     * 出发城市Code
     */
    private Integer departureCityCode;

    /**
     * 到达城市三字码
     */
    private String arrivalCityIataCode;

    /**
     * 到达城市
     */
    private String arrivalCityName;

    /**
     * 到达城市Code
     */
    private Integer arrivalCityCode;

    /**
     * 出发日期
     */
    private String departureDate;

    /**
     * 中转标识
     */
    private Integer transferFlag;

    /**
     * 中转时间
     */
    private String journeyDuration;

    /**
     * 航段LIST
     */
    private List<FlightBasicRespIndiv> flightIndivResFlightList;

    public Integer getJourneyRph() {
        return journeyRph;
    }

    public void setJourneyRph(Integer journeyRph) {
        this.journeyRph = journeyRph;
    }

    public String getDepartureCityIataCode() {
        return departureCityIataCode;
    }

    public void setDepartureCityIataCode(String departureCityIataCode) {
        this.departureCityIataCode = departureCityIataCode;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public Integer getDepartureCityCode() {
        return departureCityCode;
    }

    public void setDepartureCityCode(Integer departureCityCode) {
        this.departureCityCode = departureCityCode;
    }

    public String getArrivalCityIataCode() {
        return arrivalCityIataCode;
    }

    public void setArrivalCityIataCode(String arrivalCityIataCode) {
        this.arrivalCityIataCode = arrivalCityIataCode;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }

    public Integer getArrivalCityCode() {
        return arrivalCityCode;
    }

    public void setArrivalCityCode(Integer arrivalCityCode) {
        this.arrivalCityCode = arrivalCityCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(Integer transferFlag) {
        this.transferFlag = transferFlag;
    }

    public String getJourneyDuration() {
        return journeyDuration;
    }

    public void setJourneyDuration(String journeyDuration) {
        this.journeyDuration = journeyDuration;
    }

    public List<FlightBasicRespIndiv> getFlightIndivResFlightList() {
        return flightIndivResFlightList;
    }

    public void setFlightIndivResFlightList(List<FlightBasicRespIndiv> flightIndivResFlightList) {
        this.flightIndivResFlightList = flightIndivResFlightList;
    }
}
