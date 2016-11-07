package com.tuniu.abt.intf.tsp.dto.flt;

import java.io.Serializable;

/**
 * 航班查询是否屏蔽请求
 * Created by chengyao on 2016/3/30.
 */
public class BanQueryRequest implements Serializable {

    private static final long serialVersionUID = -8076121990530142379L;

    /**
     * 供应商（必填）
     */
    private String solutionId;

    /**
     * 系统ID（必填）
     */
    private String systemId;

    /**
     * 航司（必填）
     */
    private String airlineCompany;

    /**
     * 航班号
     */
    private String flightNo;

    //团期
    private String departureDate;

    //出发城市三字码
    private String orgAirportCode;

    //到达城市三字码
    private String dstAirportCode;

    //舱位码
    private String cabin;

    public String getSolutionId() {
        return solutionId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getOrgAirportCode() {
        return orgAirportCode;
    }

    public void setOrgAirportCode(String orgAirportCode) {
        this.orgAirportCode = orgAirportCode;
    }

    public String getDstAirportCode() {
        return dstAirportCode;
    }

    public void setDstAirportCode(String dstAirportCode) {
        this.dstAirportCode = dstAirportCode;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }
}
