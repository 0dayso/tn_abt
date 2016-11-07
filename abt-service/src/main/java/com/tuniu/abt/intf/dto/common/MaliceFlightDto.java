package com.tuniu.abt.intf.dto.common;

/**
 * Created by chengyao on 2016/3/30.
 */
public class MaliceFlightDto {

    private String flightNo;

    private String orgCityCode;

    private String dstCityCode;

    private String orgAirportCode;

    private String dstAirportCode;

    // 恶意占位的数量
    private int maliceOccupyNum;

    // 团期
    private String departureDate;

    private String orderId;

    private String orgTime;

    private String dstTime;

    private int vendorId;

    //1：一天时间内，有30个客人；2： 15分钟内，有15个客人;3:起飞前满80人
    private int type;

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

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrgTime() {
        return orgTime;
    }

    public void setOrgTime(String orgTime) {
        this.orgTime = orgTime;
    }

    public String getDstTime() {
        return dstTime;
    }

    public void setDstTime(String dstTime) {
        this.dstTime = dstTime;
    }

    public String getOrgCityCode() {
        return orgCityCode;
    }

    public void setOrgCityCode(String orgCityCode) {
        this.orgCityCode = orgCityCode;
    }

    public String getDstCityCode() {
        return dstCityCode;
    }

    public void setDstCityCode(String dstCityCode) {
        this.dstCityCode = dstCityCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getMaliceOccupyNum() {
        return maliceOccupyNum;
    }

    public void setMaliceOccupyNum(int maliceOccupyNum) {
        this.maliceOccupyNum = maliceOccupyNum;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }
}
