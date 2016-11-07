package com.tuniu.abt.intf.dto.book.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FlightCabin implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 5223730224059172703L;

    private String flightNo;
    
    @JsonProperty("cabinClass")
    @JSONField(name = "cabinClass")
    private String cabinCode;


    /**
     * 行李额信息
     */
    @JsonProperty("baggageInfo")
    @JSONField(name = "baggageInfo")
    private String luggageInfo;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getCabinCode() {
        return cabinCode;
    }

    public void setCabinCode(String cabinCode) {
        this.cabinCode = cabinCode;
    }

    public String getLuggageInfo() {
        return luggageInfo;
    }

    public void setLuggageInfo(String luggageInfo) {
        this.luggageInfo = luggageInfo;
    }
}
