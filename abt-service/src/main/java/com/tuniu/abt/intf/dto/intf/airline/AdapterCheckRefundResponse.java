package com.tuniu.abt.intf.dto.intf.airline;

import java.util.List;

public class AdapterCheckRefundResponse {

    private boolean success=true;

    private int errorCode;

    private String message;

    private List<AdapterPassenger> passengerTravels;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AdapterPassenger> getPassengerTravels() {
        return passengerTravels;
    }

    public void setPassengerTravels(List<AdapterPassenger> passengerTravels) {
        this.passengerTravels = passengerTravels;
    }

}
