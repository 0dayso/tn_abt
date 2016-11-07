package com.tuniu.abt.intf.dto.intf.airline;

import java.util.List;

public class AirDirectConnectOccupyParam {

    private AirFlightItem flightItem;

    private ContactInfo contactInfo;

    private List<Passenger> Passengers;

    public AirFlightItem getFlightItem() {
        return flightItem;
    }

    public void setFlightItem(AirFlightItem flightItem) {
        this.flightItem = flightItem;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Passenger> getPassengers() {
        return Passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        Passengers = passengers;
    }

}
