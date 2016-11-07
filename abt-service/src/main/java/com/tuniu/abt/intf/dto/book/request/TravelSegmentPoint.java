package com.tuniu.abt.intf.dto.book.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 航段的出发到达站信息
 */
public class TravelSegmentPoint implements Serializable {

    private static final long serialVersionUID = -2101826221800051955L;

    /**
     * 城市三字码
     */
    @NotBlank(message = "{TravelSegmentPoint.cityIataCode.notEmpty}")
    @JsonProperty("cityCode")
    @JSONField(name = "cityCode")
    private String cityIataCode;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 机场三字码
     */
    @NotBlank(message = "{TravelSegmentPoint.airportCode.notEmpty}")
    private String airportCode;

    /**
     * 机场名
     */
    private String airportName;

    /**
     * 航站楼
     */
    private String terminal;

    /**
     * 日期，格式 yyyy-MM-dd
     */
    @NotBlank(message = "{TravelSegmentPoint.date.notEmpty}")
    private String date;

    /**
     * 时间，格式 HH:mm
     */
    private String time;

    public String getCityIataCode() {
        return cityIataCode;
    }

    public void setCityIataCode(String cityIataCode) {
        this.cityIataCode = cityIataCode;
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

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
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
}
