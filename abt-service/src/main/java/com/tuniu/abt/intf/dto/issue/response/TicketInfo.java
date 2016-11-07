package com.tuniu.abt.intf.dto.issue.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangsizhou on 16/3/21.
 */
public class TicketInfo {

    private String ticketNo;//票号

    private List<FlightInfo> flights = new ArrayList<FlightInfo>();

    private List<PassengerInfo> passengers = new ArrayList<PassengerInfo>();

    public TicketInfo(String ticketNo, List<FlightInfo> flights, List<PassengerInfo> passengers) {
        this.ticketNo = ticketNo;
        this.flights = flights;
        this.passengers = passengers;
    }

    public TicketInfo() {
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public List<FlightInfo> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightInfo> flights) {
        this.flights = flights;
    }

    public List<PassengerInfo> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerInfo> passengerInfos) {
        this.passengers = passengerInfos;
    }

    public void addFlight(FlightInfo flightInfo) {
        this.flights.add(flightInfo);
    }

    public void addPassenger(PassengerInfo passengerInfo) {
        this.passengers.add(passengerInfo);
    }
}
