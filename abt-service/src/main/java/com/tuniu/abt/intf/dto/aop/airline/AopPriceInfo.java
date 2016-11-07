package com.tuniu.abt.intf.dto.aop.airline;

import java.util.Map;

/**
 * Created by huanggao on 2016/4/22.
 */
public class AopPriceInfo {

    /**
     * 币种
     */
    private String currency;

    /**
     * 票面价
     */
    private int printPrice;

    /**
     * 供应商售卖价，即途牛成本价
     */
    private int salePrice;

    /**
     * 供应商新售卖价，即途牛新成本价
     */
    private int salePriceNew;

    /**
     * 机建费
     */
    private float airportTax;

    /**
     * 燃油费
     */
    private float fuelSurcharge;

    /**
     * 扩展信息【预留】
     */
    private Map<String,Object> extraInfo;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPrintPrice() {
        return printPrice;
    }

    public void setPrintPrice(int printPrice) {
        this.printPrice = printPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getSalePriceNew() {
        return salePriceNew;
    }

    public void setSalePriceNew(int salePriceNew) {
        this.salePriceNew = salePriceNew;
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

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }
}
