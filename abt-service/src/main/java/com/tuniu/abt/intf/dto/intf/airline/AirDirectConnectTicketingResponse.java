package com.tuniu.abt.intf.dto.intf.airline;

import java.util.List;

public class AirDirectConnectTicketingResponse extends AirlineResponse {

    private String orderId;

    private int totalPrice;

    private ContactInfo contactInfo;

    private List<PassengerTravel> passengerTravels;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<PassengerTravel> getPassengerTravels() {
        return passengerTravels;
    }

    public void setPassengerTravels(List<PassengerTravel> passengerTravels) {
        this.passengerTravels = passengerTravels;
    }

}
