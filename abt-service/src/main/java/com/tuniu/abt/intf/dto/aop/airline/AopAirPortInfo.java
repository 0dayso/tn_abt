package com.tuniu.abt.intf.dto.aop.airline;

/**
 * Created by huanggao on 2016/4/22.
 */
public class AopAirPortInfo {

    /**
     * 城市三字码
     */
    private String  cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 机场三字码
     */
    private String airportCode;

    /**
     * 机场名称
     */
    private String airportName;

    /**
     * 出发日期
     */
    private String date;

    /**
     * 出发时间
     */
    private String time;

    /**
     * 航站楼
     */
    private String terminal;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
