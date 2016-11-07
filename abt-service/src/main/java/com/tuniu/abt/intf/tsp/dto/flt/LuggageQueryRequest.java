package com.tuniu.abt.intf.tsp.dto.flt;

import java.io.Serializable;

/**
 * 行李额查询请求
 * Created by lanlugang on 2016/5/28.
 */
public class LuggageQueryRequest implements Serializable {

    private static final long serialVersionUID = -6512133449772153548L;

    /**
     * 航司二字码
     */
    private String airlineCompanyIataCode;

    /**
     * 舱等code
     */
    private String seatClass;

    /**
     * 舱位code
     */
    private String seatCode;

    //状态(0待维护，1已保存，2已生效，3已失效)
    private int state = 2;

    //分页查询条件
    private int start = 0;
    private int limit = 1000;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getAirlineCompanyIataCode() {
        return airlineCompanyIataCode;
    }

    public void setAirlineCompanyIataCode(String airlineCompanyIataCode) {
        this.airlineCompanyIataCode = airlineCompanyIataCode;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }
}
