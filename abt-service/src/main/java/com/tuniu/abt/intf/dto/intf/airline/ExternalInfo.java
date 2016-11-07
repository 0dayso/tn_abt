package com.tuniu.abt.intf.dto.intf.airline;

public class ExternalInfo {

    private String ticketingCarrier;

    private String extraInfo;

    private int adultTax;

    private int adultFuel;

    private int childTax;

    private int childFuel;
    
    //标示 机票类型 0：散客票，1：团票
    private int ticketType;
    
    /**航司要求显示的成人价格*/
    private int airCmpAdultPrice;
    
    /**航司要求显示的孩童价格*/
    private int airCmpChildPrice;
    
    /**航司要求显示的婴儿价格*/
    private int airCmpBabyPrice;
    
    /**代理商全称*/
    private String agentFullName;
    
    /**代理商简称*/
    private String agentShortName;
    
    /**航协代码*/
    private String cata;


    public String getTicketingCarrier() {
        return ticketingCarrier;
    }

    public void setTicketingCarrier(String ticketingCarrier) {
        this.ticketingCarrier = ticketingCarrier;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public int getAdultTax() {
        return adultTax;
    }

    public void setAdultTax(int adultTax) {
        this.adultTax = adultTax;
    }

    public int getAdultFuel() {
        return adultFuel;
    }

    public void setAdultFuel(int adultFuel) {
        this.adultFuel = adultFuel;
    }

    public int getChildTax() {
        return childTax;
    }

    public void setChildTax(int childTax) {
        this.childTax = childTax;
    }

    public int getChildFuel() {
        return childFuel;
    }

    public void setChildFuel(int childFuel) {
        this.childFuel = childFuel;
    }

    public int getTicketType() {
        return ticketType;
    }

    public void setTicketType(int ticketType) {
        this.ticketType = ticketType;
    }

    public int getAirCmpAdultPrice() {
        return airCmpAdultPrice;
    }

    public void setAirCmpAdultPrice(int airCmpAdultPrice) {
        this.airCmpAdultPrice = airCmpAdultPrice;
    }

    public int getAirCmpChildPrice() {
        return airCmpChildPrice;
    }

    public void setAirCmpChildPrice(int airCmpChildPrice) {
        this.airCmpChildPrice = airCmpChildPrice;
    }

    public int getAirCmpBabyPrice() {
        return airCmpBabyPrice;
    }

    public void setAirCmpBabyPrice(int airCmpBabyPrice) {
        this.airCmpBabyPrice = airCmpBabyPrice;
    }

    public String getAgentFullName() {
        return agentFullName;
    }

    public void setAgentFullName(String agentFullName) {
        this.agentFullName = agentFullName;
    }

    public String getAgentShortName() {
        return agentShortName;
    }

    public void setAgentShortName(String agentShortName) {
        this.agentShortName = agentShortName;
    }

    public String getCata() {
        return cata;
    }

    public void setCata(String cata) {
        this.cata = cata;
    }
    
    

}
