package com.tuniu.abt.intf.dto.book.request;

import java.io.Serializable;

/**
 * 价格信息
 */
public class PriceInfoItem implements Serializable {

    private static final long serialVersionUID = -1878469016932506518L;

    /**
     * 货币类型三字码，默认为：CNY（人民币）
     */
    private String currency;

    /**
     * 票面价
     */
    private float price;

    /**
     * 税费：燃油+机建费
     */
    private float tax;

    /**
     * 基建税
     */
    private float airportTax;

    /**
     * 燃油附加费
     */
    private float fuelSurcharge;

    /**
     * 附加信息
     */
    private String extraInfo;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
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

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
