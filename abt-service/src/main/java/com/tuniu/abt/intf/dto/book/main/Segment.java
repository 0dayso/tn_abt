package com.tuniu.abt.intf.dto.book.main;

import java.io.Serializable;

public class Segment implements Serializable {
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 1215020394071619574L;

    // 航段序号
    private int segmentNumber;

    // 出发城市三字码,如PEK
    private String orgCityIataCode;

    // 抵达城市三字码，如SYX
    private String dstCityIataCode;

    // 出发机场三字码
    private String orgAirportIataCode;

    // 抵达机场三字码
    private String dstAirportIataCode;

    // 航班抵达时间
    private String arrivalTime;

    // 出发城市名称
    private String orgCityName;

    // 抵达城市名称
    private String dstCityName;

    // 出发机场名称
    private String orgAirportName;

    // 抵达机场名称
    private String dstAirportName;

    // 航班号，如CA1354
    private String flightNo;
    
    // 机型
    private String planeType;

    // 舱等code
    private String seatClass;

    // 舱位code
    private String seatCode;

    // 出发日期，格式yyyy-MM-dd
    private String departureDate;

    // 出发时间 HH:mm
    private String departureTime;

    // 抵达日期，格式yyyy-MM-dd
    private String arriveDate;

    // 出发机场航站楼
    private String departureAirportTerminal;

    // 抵达机场航站楼
    private String arrivalAirportTerminal;

    // 航空公司名称
    private String airlineCompany;

    // 航司二字码
    private String airlineCompanyIataCode;
    
    /**
     * PID携程下单专用字段
     */
    private String pidCtrip;
    
    /**
     * 销售类型携程下单专用字段
     */
    private String saleTypeCtrip;
    
    /**
     * 产品类型携程下单专用字段
     */
    private String productTypeCtrip;

    public int getSegmentNumber() {
        return segmentNumber;
    }

    public void setSegmentNumber(int segmentNumber) {
        this.segmentNumber = segmentNumber;
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

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getOrgCityName() {
        return orgCityName;
    }

    public void setOrgCityName(String orgCityName) {
        this.orgCityName = orgCityName;
    }

    public String getDstCityName() {
        return dstCityName;
    }

    public void setDstCityName(String dstCityName) {
        this.dstCityName = dstCityName;
    }

    public String getOrgAirportName() {
        return orgAirportName;
    }

    public void setOrgAirportName(String orgAirportName) {
        this.orgAirportName = orgAirportName;
    }

    public String getDstAirportName() {
        return dstAirportName;
    }

    public void setDstAirportName(String dstAirportName) {
        this.dstAirportName = dstAirportName;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getDepartureAirportTerminal() {
        return departureAirportTerminal;
    }

    public void setDepartureAirportTerminal(String departureAirportTerminal) {
        this.departureAirportTerminal = departureAirportTerminal;
    }

    public String getArrivalAirportTerminal() {
        return arrivalAirportTerminal;
    }

    public void setArrivalAirportTerminal(String arrivalAirportTerminal) {
        this.arrivalAirportTerminal = arrivalAirportTerminal;
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public String getAirlineCompanyIataCode() {
        return airlineCompanyIataCode;
    }

    public void setAirlineCompanyIataCode(String airlineCompanyIataCode) {
        this.airlineCompanyIataCode = airlineCompanyIataCode;
    }

    public String getPidCtrip() {
        return pidCtrip;
    }

    public void setPidCtrip(String pidCtrip) {
        this.pidCtrip = pidCtrip;
    }

    public String getSaleTypeCtrip() {
        return saleTypeCtrip;
    }

    public void setSaleTypeCtrip(String saleTypeCtrip) {
        this.saleTypeCtrip = saleTypeCtrip;
    }

    public String getProductTypeCtrip() {
        return productTypeCtrip;
    }

    public void setProductTypeCtrip(String productTypeCtrip) {
        this.productTypeCtrip = productTypeCtrip;
    }
    
}
