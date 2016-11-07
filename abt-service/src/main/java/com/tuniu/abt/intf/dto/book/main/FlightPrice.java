package com.tuniu.abt.intf.dto.book.main;

import java.io.Serializable;

public class FlightPrice implements Serializable{

	/**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = -1276556373121202873L;
    // 航班号
	private String flightNo;
	// 舱位
	private String seatCode;
	// 成人价格
	private float adultCost;
	// 儿童价格
	private float childCost;
	// 婴儿价格
	private float babyCost;
	// 成人机场建设费
    private float adultAirportTax;
    // 儿童机场建设费
    private float childAirportTax;
    // 婴儿机场建设费
    private float babyAirportTax;
    // 成人燃油附加费
    private float adultFuelSurcharge;
    // 儿童燃油附加费
    private float childFuelSurcharge;
    // 婴儿燃油附加费
    private float babyFuelSurcharge;
    // 出发城市Iata code
    private String orgCityIataCode;
    // 抵达城市Iata code
    private String dstCityIataCode;
    // 出发机场Iata code;
    private String orgAirportIataCode;
    // 抵达机场Iata code;
    private String dstAirportIataCode;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public float getAdultCost() {
        return adultCost;
    }

    public void setAdultCost(float adultCost) {
        this.adultCost = adultCost;
    }

    public float getChildCost() {
        return childCost;
    }

    public void setChildCost(float childCost) {
        this.childCost = childCost;
    }

    public float getBabyCost() {
        return babyCost;
    }

    public void setBabyCost(float babyCost) {
        this.babyCost = babyCost;
    }

    public float getAdultAirportTax() {
        return adultAirportTax;
    }

    public void setAdultAirportTax(float adultAirportTax) {
        this.adultAirportTax = adultAirportTax;
    }

    public float getChildAirportTax() {
        return childAirportTax;
    }

    public void setChildAirportTax(float childAirportTax) {
        this.childAirportTax = childAirportTax;
    }

    public float getBabyAirportTax() {
        return babyAirportTax;
    }

    public void setBabyAirportTax(float babyAirportTax) {
        this.babyAirportTax = babyAirportTax;
    }

    public float getAdultFuelSurcharge() {
        return adultFuelSurcharge;
    }

    public void setAdultFuelSurcharge(float adultFuelSurcharge) {
        this.adultFuelSurcharge = adultFuelSurcharge;
    }

    public float getChildFuelSurcharge() {
        return childFuelSurcharge;
    }

    public void setChildFuelSurcharge(float childFuelSurcharge) {
        this.childFuelSurcharge = childFuelSurcharge;
    }

    public float getBabyFuelSurcharge() {
        return babyFuelSurcharge;
    }

    public void setBabyFuelSurcharge(float babyFuelSurcharge) {
        this.babyFuelSurcharge = babyFuelSurcharge;
    }

    public String getOrgCityIataCode() {
        return orgCityIataCode;
    }

    public void setOrgCityIataCode(String orgCityIataCode) {
        this.orgCityIataCode = orgCityIataCode;
    }

    public String getDstCityIataCode() {
        return dstCityIataCode;
    }

    public void setDstCityIataCode(String dstCityIataCode) {
        this.dstCityIataCode = dstCityIataCode;
    }

    public String getOrgAirportIataCode() {
        return orgAirportIataCode;
    }

    public void setOrgAirportIataCode(String orgAirportIataCode) {
        this.orgAirportIataCode = orgAirportIataCode;
    }

    public String getDstAirportIataCode() {
        return dstAirportIataCode;
    }

    public void setDstAirportIataCode(String dstAirportIataCode) {
        this.dstAirportIataCode = dstAirportIataCode;
    }

}
