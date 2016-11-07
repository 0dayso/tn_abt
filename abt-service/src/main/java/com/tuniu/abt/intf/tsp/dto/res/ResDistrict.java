package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * 地区信息结果
 * Created by chengyao on 2016/2/3.
 */
public class ResDistrict implements Serializable {

    private static final long serialVersionUID = 8500630061320489355L;

    /**
     * id
     */
    private Integer id;

    /**
     * 城市三字码
     */
    private String iataCode;

    /**
     * 城市名
     */
    private String name;

    /**
     * 层级编码
     */
    private String poiCode;

    /**
     * 英文名
     */
    private String englishName;

    /**
     * 国内国际标识
     */
    private Integer abroad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoiCode() {
        return poiCode;
    }

    public void setPoiCode(String poiCode) {
        this.poiCode = poiCode;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Integer getAbroad() {
        return abroad;
    }

    public void setAbroad(Integer abroad) {
        this.abroad = abroad;
    }

}
