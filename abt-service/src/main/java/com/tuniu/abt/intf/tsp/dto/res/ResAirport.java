package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * 机场信息
 * Created by chengyao on 2016/2/3.
 */
public class ResAirport implements Serializable {

    private static final long serialVersionUID = 2599647273316066526L;

    /**
     * id
     */
    private Integer id;

    /**
     * 机场三字码
     */
    private String airportCode;

    /**
     * 机场名
     */
    private String name;

    /**
     * 机场短名
     */
    private String shortName;

    /**
     * 城市三字码
     */
    private String iataCityCode;
    
    /**
     * 城市名
     */
    private String cityName;

    /**
     * 航站楼信息
     */
    private String airportTerminals;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getIataCityCode() {
        return iataCityCode;
    }

    public void setIataCityCode(String iataCityCode) {
        this.iataCityCode = iataCityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAirportTerminals() {
        return airportTerminals;
    }

    public void setAirportTerminals(String airportTerminals) {
        this.airportTerminals = airportTerminals;
    }

}
