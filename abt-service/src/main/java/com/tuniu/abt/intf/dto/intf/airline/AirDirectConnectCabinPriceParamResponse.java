package com.tuniu.abt.intf.dto.intf.airline;

public class AirDirectConnectCabinPriceParamResponse extends AirlineResponse {

    private AirFlightItem flightItem;

    public AirFlightItem getFlightItem() {
        return flightItem;
    }

    public void setFlightItem(AirFlightItem flightItem) {
        this.flightItem = flightItem;
    }

}
