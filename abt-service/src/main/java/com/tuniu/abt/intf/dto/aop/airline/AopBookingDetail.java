package com.tuniu.abt.intf.dto.aop.airline;

import java.util.List;

/**
 * Created by tangaizhong on 2016/5/3.
 */
public class AopBookingDetail {

    private List<AopFlightSegment> flightSegments;

    private List<AopPassenger> passengers;

    private AopContactInfo contactInfo;

    private AopPriceDetail priceInfo;

    public AopPriceDetail getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(AopPriceDetail priceInfo) {
        this.priceInfo = priceInfo;
    }

    public List<AopFlightSegment> getFlightSegments() {
        return flightSegments;
    }

    public void setFlightSegments(List<AopFlightSegment> flightSegments) {
        this.flightSegments = flightSegments;
    }

    public List<AopPassenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<AopPassenger> passengers) {
        this.passengers = passengers;
    }

    public AopContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(AopContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
