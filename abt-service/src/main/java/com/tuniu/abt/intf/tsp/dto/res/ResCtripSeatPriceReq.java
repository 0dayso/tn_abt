package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/9.
 */
public class ResCtripSeatPriceReq implements Serializable {

    private static final long serialVersionUID = -5034211847471278529L;

    private int flightId;
    private int seatId;
    private int journeyRph;
    private int personType;
    private String departureDate;

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getJourneyRph() {
        return journeyRph;
    }

    public void setJourneyRph(int journeyRph) {
        this.journeyRph = journeyRph;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
