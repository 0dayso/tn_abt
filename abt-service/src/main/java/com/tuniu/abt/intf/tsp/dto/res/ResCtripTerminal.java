package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * 携程航站楼信息
 * Created by chengyao on 2016/2/3.
 */
public class ResCtripTerminal implements Serializable {

    private static final long serialVersionUID = -465643710487214635L;

    /**
     * 携程航站楼id
     */
    private int id;

    /**
     * 全名
     */
    private String name;

    /**
     * 全名，英文
     */
    private String ename;

    /**
     * 短名
     */
    private String shortName;

    /**
     * 短名，英文
     */
    private String shortEname;

    /**
     * 机场三字码
     */
    private String airportCode;

    /**
     * 城市三字码
     */
    private String districtIataCode;

    /**
     * 途牛地区id
     */
    private Integer districtId;

    /**
     * 携程城市id
     */
    private Integer ctripCity;

    /**
     * 航站楼缩写
     */
    private String smsBuildingName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortEname() {
        return shortEname;
    }

    public void setShortEname(String shortEname) {
        this.shortEname = shortEname;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getDistrictIataCode() {
        return districtIataCode;
    }

    public void setDistrictIataCode(String districtIataCode) {
        this.districtIataCode = districtIataCode;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getCtripCity() {
        return ctripCity;
    }

    public void setCtripCity(Integer ctripCity) {
        this.ctripCity = ctripCity;
    }

    public String getSmsBuildingName() {
        return smsBuildingName;
    }

    public void setSmsBuildingName(String smsBuildingName) {
        this.smsBuildingName = smsBuildingName;
    }
}
