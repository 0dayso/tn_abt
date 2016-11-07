package com.tuniu.abt.intf.dto.book.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 航段的舱位信息
 */
public class TravelSegmentCabin implements Serializable {

    private static final long serialVersionUID = 7104308335557406827L;

    /**
     * 行李信息
     */
    private String baggageInfo;
    
    /**
     * 舱等，譬如：F头等舱 J公务舱 Y经济舱
     */
    @NotBlank(message = "{TravelSegmentCabin.seatType.notEmpty}")
    @JsonProperty("seatType")
    @JSONField(name = "seatType")
    private String seatClass;

    /**
     * 舱位，譬如：R，Y
     */
    @NotBlank(message = "{TravelSegmentCabin.cabinClass.notEmpty}")
    @JsonProperty("cabinClass")
    @JSONField(name = "cabinClass")
    private String seatCode;

    /**
     * 舱位余位：-1 表示未知
     */
    private Integer seatStatus;

    /**
     * 舱等代码：1经济舱， 2公务，3 头等，4 超经  5超公 6 超头
     */
    private Integer seatTypeCode;

    public String getBaggageInfo() {
        return baggageInfo;
    }

    public void setBaggageInfo(String baggageInfo) {
        this.baggageInfo = baggageInfo;
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

    public Integer getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Integer seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Integer getSeatTypeCode() {
        return seatTypeCode;
    }

    public void setSeatTypeCode(Integer seatTypeCode) {
        this.seatTypeCode = seatTypeCode;
    }
}
