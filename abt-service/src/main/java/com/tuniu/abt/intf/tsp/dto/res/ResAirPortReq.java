package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * 机场查询请求
 * Created by chengyao on 2016/2/3.
 */
public class ResAirPortReq implements Serializable {

    private static final long serialVersionUID = 6401769154168644150L;

    /**
     * 机场三字码。全匹配。为null或空忽略此条件
     */
    private String airportCode;

    private int start = 0;

    private int limit = 10;

    public String getAirportCode() {
        return airportCode;
    }

    public ResAirPortReq setAirportCode(String airportCode) {
        this.airportCode = airportCode;
        return this;
    }

    public int getStart() {
        return start;
    }

    public ResAirPortReq setStart(int start) {
        this.start = start;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public ResAirPortReq setLimit(int limit) {
        this.limit = limit;
        return this;
    }
}
