package com.tuniu.abt.intf.builder;

import com.tuniu.abt.intf.entity.AbtTicketMain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by huangsizhou on 16/3/23.
 */
public class AbtTicketMainBuilder {

    private Long requestId;

    private String pnr;

    private String newPnr;

    private String tId;

    private String ticketNo;

    private String passengerName;

    private Integer passengerType;

    private String flightNo;

    private Integer rph;

    private String seatCode;

    private String orgAirportCode;

    private String dstAirportCode;

    private Date orgTime;

    private BigDecimal orgPrice;

    private BigDecimal floorPrice;

    private Integer status;

    private Date addTime = new Date();

    private Date updateTime = new Date();

    private String solutionId;

    private String solutionName;

    public AbtTicketMainBuilder requestId(Long val) {
        requestId = val;
        return this;
    }

    public AbtTicketMainBuilder pnr(String val) {
        pnr = val;
        return this;
    }

    public AbtTicketMainBuilder newPnr(String val) {
        newPnr = val;
        return this;
    }

    public AbtTicketMainBuilder tId(String val) {
        tId = val;
        return this;
    }

    public AbtTicketMainBuilder ticketNo(String val) {
        ticketNo = val;
        return this;
    }

    public AbtTicketMainBuilder passengerName(String val) {
        passengerName = val;
        return this;
    }

    public AbtTicketMainBuilder passengerType(Integer val) {
        passengerType = val;
        return this;
    }

    public AbtTicketMainBuilder flightNo(String val) {
        flightNo = val;
        return this;
    }

    public AbtTicketMainBuilder rph(Integer val) {
        rph = val;
        return this;
    }

    public AbtTicketMainBuilder seatCode(String val) {
        seatCode = val;
        return this;
    }


    public AbtTicketMainBuilder orgAirportCode(String val) {
        orgAirportCode = val;
        return this;
    }

    public AbtTicketMainBuilder dstAirportCode(String val) {
        dstAirportCode = val;
        return this;
    }

    public AbtTicketMainBuilder orgTime(Date val) {
        orgTime = val;
        return this;
    }

    public AbtTicketMainBuilder orgPrice(BigDecimal val) {
        orgPrice = val;
        return this;
    }

    public AbtTicketMainBuilder floorPrice(BigDecimal val) {
        floorPrice = val;
        return this;
    }

    public AbtTicketMainBuilder status(Integer val) {
        status = val;
        return this;
    }

    public AbtTicketMainBuilder solutionId(String val) {
        solutionId = val;
        return this;
    }

    public AbtTicketMainBuilder solutionName(String val) {
        solutionName = val;
        return this;
    }

    public AbtTicketMain build() {
        return new AbtTicketMain(this);
    }

    public Long getRequestId() {
        return requestId;
    }

    public String getPnr() {
        return pnr;
    }

    public String getNewPnr() {
        return newPnr;
    }

    public String gettId() {
        return tId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Integer getPassengerType() {
        return passengerType;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public Integer getRph() {
        return rph;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public String getOrgAirportCode() {
        return orgAirportCode;
    }

    public String getDstAirportCode() {
        return dstAirportCode;
    }

    public Date getOrgTime() {
        return orgTime;
    }

    public BigDecimal getOrgPrice() {
        return orgPrice;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    public Date getAddTime() {
        return addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId == null ? null : solutionId.trim();
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName == null ? null : solutionName.trim();
    }
}
