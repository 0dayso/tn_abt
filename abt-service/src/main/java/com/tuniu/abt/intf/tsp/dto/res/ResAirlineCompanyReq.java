package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/10.
 */
public class ResAirlineCompanyReq implements Serializable {

    private static final long serialVersionUID = 2328479054724961030L;

    /**
     * 机场三字码。全匹配。为null或空忽略此条件
     */
    private String airlineCompanyIataCode;

    private int start = 0;

    private int limit = 10;

    public String getAirlineCompanyIataCode() {
        return airlineCompanyIataCode;
    }

    public ResAirlineCompanyReq setAirlineCompanyIataCode(String airlineCompanyIataCode) {
        this.airlineCompanyIataCode = airlineCompanyIataCode;
        return this;
    }

    public int getStart() {
        return start;
    }

    public ResAirlineCompanyReq setStart(int start) {
        this.start = start;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public ResAirlineCompanyReq setLimit(int limit) {
        this.limit = limit;
        return this;
    }
}
