package com.tuniu.abt.service.travelsky.dto;

import com.travelsky.espeed.PassengerType;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/4/16.
 */
public class PassengerItem implements Serializable {

    private static final long serialVersionUID = 8862517449214541096L;

    private String rph;

    private String name;

    private PassengerType passengerType;

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }
}
