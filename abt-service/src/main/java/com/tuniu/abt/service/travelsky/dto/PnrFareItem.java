package com.tuniu.abt.service.travelsky.dto;

import java.io.Serializable;

/**
 * Created by lanlugang on 2016/4/14.
 */
public class PnrFareItem implements Serializable {

    private static final long serialVersionUID = 7735021140583723205L;

    // 序号
    private int rph;
    // 票价
    private double fare;
    // 机建
    private double airPortTax;
    // 燃油
    private double fuelSurcharge;
    // 总价
    private double total;
    // 运价基础code
    private String fareBasisCode;

    public int getRph() {
        return rph;
    }

    public void setRph(int rph) {
        this.rph = rph;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public double getAirPortTax() {
        return airPortTax;
    }

    public void setAirPortTax(double airPortTax) {
        this.airPortTax = airPortTax;
    }

    public double getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(double fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFareBasisCode() {
        return fareBasisCode;
    }

    public void setFareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
    }
}
