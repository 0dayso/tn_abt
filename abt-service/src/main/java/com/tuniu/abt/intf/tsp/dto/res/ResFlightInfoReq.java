package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/9.
 */
public class ResFlightInfoReq implements Serializable {

    private static final long serialVersionUID = -3704555778559111308L;

    private String flightNo;
    private String orgCityIataCode;
    private String dstCityIataCode;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
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
}
