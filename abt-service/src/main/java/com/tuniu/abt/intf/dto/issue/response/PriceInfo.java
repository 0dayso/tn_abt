package com.tuniu.abt.intf.dto.issue.response;

import com.tuniu.mauritius.price.constants.CurrencyType;

import java.math.BigDecimal;

/**
 * Created by huangsizhou on 16/3/21.
 */
public class PriceInfo {

    private String currency = CurrencyType.CNY.getCode();

    private BigDecimal orgPrice = BigDecimal.ZERO;//票面价

    private BigDecimal salePrice = BigDecimal.ZERO;//售卖价

    private BigDecimal floorPrice = BigDecimal.ZERO;//结算价

    private BigDecimal airportTax = BigDecimal.ZERO;//税费：机建费（人民币）

    private BigDecimal fuelSurcharge = BigDecimal.ZERO;//税费：燃油附加税（人民币）

    public PriceInfo(String currency, BigDecimal orgPrice, BigDecimal salePrice, BigDecimal floorPrice, BigDecimal airportTax, BigDecimal fuelSurcharge) {
        this.currency = currency;
        this.orgPrice = orgPrice;
        this.salePrice = salePrice;
        this.floorPrice = floorPrice;
        this.airportTax = airportTax;
        this.fuelSurcharge = fuelSurcharge;
    }

    public PriceInfo() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(BigDecimal orgPrice) {
        this.orgPrice = orgPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    public BigDecimal getAirportTax() {
        return airportTax;
    }

    public void setAirportTax(BigDecimal airportTax) {
        this.airportTax = airportTax;
    }

    public BigDecimal getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(BigDecimal fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }
}
