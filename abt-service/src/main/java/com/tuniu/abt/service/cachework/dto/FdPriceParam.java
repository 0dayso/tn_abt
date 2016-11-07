package com.tuniu.abt.service.cachework.dto;

import java.io.Serializable;

/**
 * Fd price入参
 * Created by chengyao on 2016/3/11.
 */
public class FdPriceParam implements Serializable {

    private static final long serialVersionUID = 6748407164859285906L;

    //出发城市三字码
    private String orgCityIataCode;
    //目的城市三字码
    private String dstCityIataCode;
    //出发时间
    private String departureDate;
    //航司二字码
    private String airlineCompany;
    //舱位
    private String cabin;
    //渠道id
    private int systemId;

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        FdPriceParam that = (FdPriceParam) o;

        if (orgCityIataCode != null ? !orgCityIataCode.equals(that.orgCityIataCode) : that.orgCityIataCode != null)
            return false;
        if (dstCityIataCode != null ? !dstCityIataCode.equals(that.dstCityIataCode) : that.dstCityIataCode != null)
            return false;
        return !(departureDate != null ? !departureDate.equals(that.departureDate) : that.departureDate != null);

    }

    @Override
    public int hashCode() {
        int result = orgCityIataCode != null ? orgCityIataCode.hashCode() : 0;
        result = 31 * result + (dstCityIataCode != null ? dstCityIataCode.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return orgCityIataCode + '_' + dstCityIataCode + '_' + departureDate;
    }
}
