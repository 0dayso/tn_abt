package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * 航空公司信息
 * Created by chengyao on 2016/2/3.
 */
public class ResAirlineCompany implements Serializable {

    private static final long serialVersionUID = -5029862243795810952L;

    private Integer id;

    /**
     * 公司二字码
     */
    private String airlineCompanyIataCode;

    /**
     * 公司名
     */
    private String airlineCompanyName;

    /**
     * 短名
     */
    private String shortName;

    private String imgPath;

    private String imgPathCdn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAirlineCompanyIataCode() {
        return airlineCompanyIataCode;
    }

    public void setAirlineCompanyIataCode(String airlineCompanyIataCode) {
        this.airlineCompanyIataCode = airlineCompanyIataCode;
    }

    public String getAirlineCompanyName() {
        return airlineCompanyName;
    }

    public void setAirlineCompanyName(String airlineCompanyName) {
        this.airlineCompanyName = airlineCompanyName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPathCdn() {
        return imgPathCdn;
    }

    public void setImgPathCdn(String imgPathCdn) {
        this.imgPathCdn = imgPathCdn;
    }
}
