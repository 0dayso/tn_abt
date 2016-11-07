package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * 航段信息
 * Created by chengyao on 2016/2/3.
 */
public class ResAirRoute implements Serializable {

    private static final long serialVersionUID = 1765478591576004512L;

    /**
     * id
     */
    private Integer id;

    /**
     * 航空公司三字码
     */
    private String airlineCompanyCode;

    /**
     * 航空公司信息
     */
    private ResAirlineCompany airlineCompany;

    /**
     * 全价
     */
    private Integer fullPrice;

    /**
     * 到达机场三字码
     */
    private String arrivalAirportCode;

    /**
     * 出发机场三字码
     */
    private String departureAirportCode;

    /**
     * 燃油附加费
     */
    private Integer fuelSurcharge;

    /**
     * 里程数
     */
    private Integer kilometers;

    /**
     * 机场建设费
     */
    private Integer airportCharge;

    /**
     * 燃油费
     */
    private Integer fuelCharge;

    /**
     * 出发机场信息
     */
    private ResAirport departureAirport;

    /**
     * 到达机场信息
     */
    private ResAirport arrivalAirport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAirlineCompanyCode() {
        return airlineCompanyCode;
    }

    public void setAirlineCompanyCode(String airlineCompanyCode) {
        this.airlineCompanyCode = airlineCompanyCode;
    }

    public ResAirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(ResAirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public Integer getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Integer fullPrice) {
        this.fullPrice = fullPrice;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public Integer getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(Integer fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public Integer getAirportCharge() {
        return airportCharge;
    }

    public void setAirportCharge(Integer airportCharge) {
        this.airportCharge = airportCharge;
    }

    public Integer getFuelCharge() {
        return fuelCharge;
    }

    public void setFuelCharge(Integer fuelCharge) {
        this.fuelCharge = fuelCharge;
    }

    public ResAirport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(ResAirport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public ResAirport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(ResAirport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}
