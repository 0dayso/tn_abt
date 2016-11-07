package com.tuniu.abt.intf.dto.intf.airline;

import com.tuniu.abt.utils.DateTimeUtils;

import java.text.ParseException;
import java.util.Date;

public class StopInfo {

    /**
     * 机场三字码
     */
    private String airportCode;

    /**
     * 机场名称
     */
    private String airportName;

    /**
     * 城市三字码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 抵达时间 yyyy-MM-dd HH:mm
     */
    private String arriveTime;

    /**
     * 起飞时间 yyyy-MM-dd HH:mm
     */
    private String departTime;

    /**
     * 经停时长
     */
    private String duration;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStopDateTime(Date arriveTime, Date departTime) throws ParseException {
        setArriveTime(DateTimeUtils.formatDate(arriveTime, DateTimeUtils.DATETIME_M));
        setDepartTime(DateTimeUtils.formatDate(departTime, DateTimeUtils.DATETIME_M));
        long durationMinutes = (departTime.getTime() - arriveTime.getTime()) / (60 * 1000);
        setDuration(String.valueOf(durationMinutes));
    }
}
