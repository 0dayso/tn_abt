package com.tuniu.abt.service.travelsky.dto;

import java.util.ArrayList;
import java.util.List;

public class AirAvailResult {
    
    /**
     * 出发城市三字码
     */
    private String orgCityIataCode;
    
    /**
     * 到达城市三字码
     */
    private String dstCityIataCode;
    
    /**
     * 出发日期
     */
    private String departureDate;
    
    /**
     * 航程信息
     */
    private List<AvOrgDstOption> orgDstOptions;

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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public List<AvOrgDstOption> getOrgDstOptions() {
        if (null == orgDstOptions) {
            orgDstOptions = new ArrayList<AvOrgDstOption>();
        }
        return orgDstOptions;
    }

    public void setOrgDstOptions(List<AvOrgDstOption> orgDstOptions) {
        this.orgDstOptions = orgDstOptions;
    }
    
}
