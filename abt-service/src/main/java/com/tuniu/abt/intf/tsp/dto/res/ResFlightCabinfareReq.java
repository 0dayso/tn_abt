package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * Created by lanlugang on 2016/4/11.
 */
public class ResFlightCabinfareReq implements Serializable {

    private static final long serialVersionUID = 7850235876241600880L;

    private String[] flightNos;

    private String cabinClass;

    private String orgCityIataCode;

    private String dstCityIataCode;

    private int[] solutionIds;

    private String seatCode;

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String[] getFlightNos() {
        return flightNos;
    }

    public void setFlightNos(String[] flightNos) {
        this.flightNos = flightNos;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
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

    public int[] getSolutionIds() {
        return solutionIds;
    }

    public void setSolutionIds(int[] solutionIds) {
        this.solutionIds = solutionIds;
    }
}
