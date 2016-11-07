package com.tuniu.abt.intf.tsp.dto.res;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuniu.abt.intf.constants.BizzConstants;

/**
 * Created by chengyao on 2016/4/5.
 */
public class ResCtripSearchRelationReq {

    private int vendorId = BizzConstants.V_CTRIP;

    /**
     * 航班
     */
    private String flightNo;

    /**
     * 舱位
     */
    private String cabin;

    /**
     * 出发城市
     */
    @JSONField(name = "orgCityIataCode")
    @JsonProperty("orgCityIataCode")
    private String orgCityCode;

    /**
     * 到达城市
     */
    @JSONField(name = "dstCityIataCode")
    @JsonProperty("dstCityIataCode")
    private String dstCityCode;

    /**
     * 团期
     */
    private String departureDate;

    /**
     * 单程、往返程
     */
    private Integer isBack = 0; // 单程

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
