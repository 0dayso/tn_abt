package com.tuniu.abt.intf.dto.backmeal;

import java.io.Serializable;

/**
 * 退改签规则、费用查询请求对象
 *
 * Created by chengyao on 2016/3/26.
 */
public class ReqBackMeal implements Serializable {

    private static final long serialVersionUID = 6984176301884135405L;

    /**
     * 供应商
     */
    private Integer vendorId;

    /**
     * 航司码
     */
    private String flightNo;

    /**
     * 舱位
     */
    private String cabin;

    /**
     * 乘客类型
     */
    private Integer passengerType;

    /**
     * 出发城市三字码
     */
    private String orgCityCode;

    /**
     * 到达城市三字码
     */
    private String dstCityCode;

    /**
     * 出票日期（规则查询用查询当前时间）
     */
    private String ticketDate;

    /**
     * 出发日期
     */
    private String departureDate;

    /**
     * 航班出发时间（用于计算退改价格）
     */
    private String departureTime;

    /**
     * 票面价
     */
    private Float costPrice;

    /**
     * 舱等全价
     */
    private Float fullPrice;

    /**
     * 来源渠道ID
     */
    private Integer systemId;

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Float getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Float fullPrice) {
        this.fullPrice = fullPrice;
    }

    public Float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Float costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public Integer getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
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

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }
}
