package com.tuniu.abt.intf.dto.issue.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by huangsizhou on 16/3/21.
 */
public class PassengerInfo {

    private String passengerName;

    private String idNumber;

    @JsonIgnore
    @JSONField(serialize = false)
    private Integer passengerType;

    public PassengerInfo(String passengerName, String idNumber) {
        this.passengerName = passengerName;
        this.idNumber = idNumber;
    }

    public PassengerInfo(String passengerName) {
        this.passengerName = passengerName;
    }

    public PassengerInfo(String passengerName, Integer passengerType) {
        this.passengerName = passengerName;
        this.passengerType = passengerType;
    }

    public PassengerInfo() {
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }
}
