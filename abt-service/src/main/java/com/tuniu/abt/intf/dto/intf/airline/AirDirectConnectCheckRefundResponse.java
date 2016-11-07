package com.tuniu.abt.intf.dto.intf.airline;

import java.util.List;

public class AirDirectConnectCheckRefundResponse extends AirlineResponse {


    private List<PassengerTravel> passengerTravels;


    public List<PassengerTravel> getPassengerTravels() {
        return passengerTravels;
    }

    public void setPassengerTravels(List<PassengerTravel> passengerTravels) {
        this.passengerTravels = passengerTravels;
    }

}
