package com.tuniu.abt.intf.dto.book.request;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 航段信息
 */
public class TravelSegment implements Serializable {

    private static final long serialVersionUID = -4456345693580715627L;

    /**
     * 航班号
     */
    @NotBlank(message = "{TravelSegment.flightNO.notEmpty}")
    @JsonProperty("flightNO")
    @JSONField(name = "flightNO")
    private String flightNo;

    /**
     * 共享航班号
     */
    private String codeShare;
    
    /**
     * 机型
     */
    private String craftType;

    /**
     * 始发站
     */
    @NotNull(message = "{TravelSegment.departure.notEmpty}")
    @Valid
    private TravelSegmentPoint departure;

    /**
     * 到达站
     */
    @NotNull(message = "{TravelSegment.arrival.notEmpty}")
    @Valid
    private TravelSegmentPoint arrival;

    /**
     * 舱位信息
     */
    @NotNull(message = "{TravelSegment.cabin.notEmpty}")
    @Valid
    private TravelSegmentCabin cabin;

    private String extraInfo;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getCodeShare() {
        return codeShare;
    }

    public void setCodeShare(String codeShare) {
        this.codeShare = codeShare;
    }

    public String getCraftType() {
        return craftType;
    }

    public void setCraftType(String craftType) {
        this.craftType = craftType;
    }

    public TravelSegmentPoint getDeparture() {
        return departure;
    }

    public void setDeparture(TravelSegmentPoint departure) {
        this.departure = departure;
    }

    public TravelSegmentPoint getArrival() {
        return arrival;
    }

    public void setArrival(TravelSegmentPoint arrival) {
        this.arrival = arrival;
    }

    public TravelSegmentCabin getCabin() {
        return cabin;
    }

    public void setCabin(TravelSegmentCabin cabin) {
        this.cabin = cabin;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}

    