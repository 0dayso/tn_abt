package com.tuniu.abt.intf.dto.book.response;

import java.io.Serializable;

public class PriceItem implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = -8221269185320290251L;

    private String currency = "CNY";

    private float orgPrice;
    
    private float salePrice;
    
    private float floorPrice;
    
    private float airportTax;
    
    private float fuelSurcharge;

    private Long saleId;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(float orgPrice) {
        this.orgPrice = orgPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public float getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(float floorPrice) {
        this.floorPrice = floorPrice;
    }

    public float getAirportTax() {
        return airportTax;
    }

    public void setAirportTax(float airportTax) {
        this.airportTax = airportTax;
    }

    public float getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(float fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
