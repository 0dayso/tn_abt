package com.tuniu.abt.intf.dto.aop;

import java.io.Serializable;

/**
 * 出票对应航段信息
 *
 * Created by chengyao on 2016/3/23.
 */
public class AopTicketSyncSegment implements Serializable {

    private static final long serialVersionUID = -4597067982650116763L;

    /**
     * 团期，yyyy-MM-dd
     */
    private String departureDate;

    /**
     * 到达日期，yyyy-MM-dd
     */
    private String arrivalDate;

    /**
     * 航班舱位
     */
    private String seatCode;

    /**
     * 航班号
     */
    private String flightNo;

    /**
     * 出发机场三字码
     */
    private String departAirportCode;

    /**
     * 到达机场三字码
     */
    private String arriveAirportCode;

    /**
     * 出发机场名
     */
    private String departAirportName;

    /**
     * 到达机场名
     */
    private String arriveAirportName;

    /**
     * 出发时间,HH:mm
     */
    private String departureTime;

    /**
     * 到达时间,HH:mm
     */
    private String arrivalTime;

    /**
     * 出发航站楼
     */
    private String departureAirportTerminal;

    /**
     * 到达航站楼
     */
    private String arrivalAirportTerminal;

    /**
     * 机型
     */
    private String airplaneType;

    /**
     * 出发城市三字码
     */
    private String departureCityIataCode;

    /**
     * 到达城市三字码
     */
    private String arrivalCityIataCode;

    /**
     * 出发城市名
     */
    private String departureCityName;

    /**
     * 到达城市名
     */
    private String arrivalCityName;

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDepartAirportCode() {
        return departAirportCode;
    }

    public void setDepartAirportCode(String departAirportCode) {
        this.departAirportCode = departAirportCode;
    }

    public String getArriveAirportCode() {
        return arriveAirportCode;
    }

    public void setArriveAirportCode(String arriveAirportCode) {
        this.arriveAirportCode = arriveAirportCode;
    }

    public String getDepartAirportName() {
        return departAirportName;
    }

    public void setDepartAirportName(String departAirportName) {
        this.departAirportName = departAirportName;
    }

    public String getArriveAirportName() {
        return arriveAirportName;
    }

    public void setArriveAirportName(String arriveAirportName) {
        this.arriveAirportName = arriveAirportName;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureAirportTerminal() {
        return departureAirportTerminal;
    }

    public void setDepartureAirportTerminal(String departureAirportTerminal) {
        this.departureAirportTerminal = departureAirportTerminal;
    }

    public String getArrivalAirportTerminal() {
        return arrivalAirportTerminal;
    }

    public void setArrivalAirportTerminal(String arrivalAirportTerminal) {
        this.arrivalAirportTerminal = arrivalAirportTerminal;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public String getDepartureCityIataCode() {
        return departureCityIataCode;
    }

    public void setDepartureCityIataCode(String departureCityIataCode) {
        this.departureCityIataCode = departureCityIataCode;
    }

    public String getArrivalCityIataCode() {
        return arrivalCityIataCode;
    }

    public void setArrivalCityIataCode(String arrivalCityIataCode) {
        this.arrivalCityIataCode = arrivalCityIataCode;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }
}
