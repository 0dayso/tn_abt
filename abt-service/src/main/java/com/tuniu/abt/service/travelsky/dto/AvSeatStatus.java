package com.tuniu.abt.service.travelsky.dto;

public class AvSeatStatus {
    
    /**
     * 舱位
     */
    private String seatCode;
    
    /**
     * 座位状态
     */
    private String status;

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
