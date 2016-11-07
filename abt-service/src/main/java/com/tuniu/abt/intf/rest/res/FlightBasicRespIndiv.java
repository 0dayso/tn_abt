package com.tuniu.abt.intf.rest.res;

/**
 * 航段信息
 * Created by chengyao on 2015/11/28.
 */
public class FlightBasicRespIndiv {

    /**
     * 航班号
     */
    private String flightNo;

    /**
     * 出发城市Code
     */
    private String departureCityCode;

    /**
     * 到达城市Code
     */
    private String arrivalCityCode;

    /**
     * 出发城市三字码
     */
    private String departureCityIataCode;

    /**
     * 到达城市三字码
     */
    private String arrivalCityIataCode;

    /**
     * 出发机场三字码
     */
    private String departureAirportIataCode;

    /**
     * 到达机场三字码
     */
    private String arrivalAirportIataCode;

    /**
     * 出发机场名称
     */
    private String departureAirportName;

    /**
     * 到达机场名称
     */
    private String arrivalAirportName;

    /**
     * 出发机场航站楼
     */
    private String departureAirportTerminal;

    /**
     * 到达机场航站楼
     */
    private String arrivalAirportTerminal;

    /**
     * 出发日期
     */
    private String departureDate;

    /**
     * 出发时间
     */
    private String departureTime;

    /**
     * 到达时间
     */
    private String arrivalTime;

    /**
     * 餐食
     */
    private String meal;

    /**
     * 经停次数
     */
    private Integer stopNum;

    /**
     * 经停信息
     */
    private String stopInformation;

    /**
     * 舱位
     */
    private String seatClass;

    /**
     * 舱位等级
     */
    private String seatType;

    /**
     * 舱位等级名称
     */
    private String seatTypeName;

    /**
     * 航程序号
     */
    private int journeyNumber;

    /**
     * 航段序号
     */
    private int segmentNumber;

    /**
     * 航段间隔
     */
    private int segSpan;

    /**
     * 机型
     */
    private String airplaneType;

    /**
     * 共享航班
     */
    private String shareNum;

    /**
     * 出发城市名称
     */
    private String departureCityName;

    /**
     * 到达城市名称
     */
    private String arrivalCityName;

    /**
     * 到达日期
     */
    private String arrivalDate;

    /**
     * 航空公司名称
     */
    private String airlineCompany;

    /**
     * 飞行时长
     */
    private String duration;

    /**
     * 出发机场简称
     */
    private String departureAirportShortName;

    /**
     * 到达机场简称
     */
    private String arriveAirportShortName;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDepartureCityCode() {
        return departureCityCode;
    }

    public void setDepartureCityCode(String departureCityCode) {
        this.departureCityCode = departureCityCode;
    }

    public String getArrivalCityCode() {
        return arrivalCityCode;
    }

    public void setArrivalCityCode(String arrivalCityCode) {
        this.arrivalCityCode = arrivalCityCode;
    }

    public String getDepartureCityIataCode() {
        return departureCityIataCode;
    }

    public void setDepartureCityIataCode(String departureCityIataCode) {
        this.departureCityIataCode = departureCityIataCode;
    }

    public String getArrivalCityIataCode() {
        return arrivalCityIataCode;
    }

    public void setArrivalCityIataCode(String arrivalCityIataCode) {
        this.arrivalCityIataCode = arrivalCityIataCode;
    }

    public String getDepartureAirportIataCode() {
        return departureAirportIataCode;
    }

    public void setDepartureAirportIataCode(String departureAirportIataCode) {
        this.departureAirportIataCode = departureAirportIataCode;
    }

    public String getArrivalAirportIataCode() {
        return arrivalAirportIataCode;
    }

    public void setArrivalAirportIataCode(String arrivalAirportIataCode) {
        this.arrivalAirportIataCode = arrivalAirportIataCode;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
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

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public Integer getStopNum() {
        return stopNum;
    }

    public void setStopNum(Integer stopNum) {
        this.stopNum = stopNum;
    }

    public String getStopInformation() {
        return stopInformation;
    }

    public void setStopInformation(String stopInformation) {
        this.stopInformation = stopInformation;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getSeatTypeName() {
        return seatTypeName;
    }

    public void setSeatTypeName(String seatTypeName) {
        this.seatTypeName = seatTypeName;
    }

    public int getJourneyNumber() {
        return journeyNumber;
    }

    public void setJourneyNumber(int journeyNumber) {
        this.journeyNumber = journeyNumber;
    }

    public int getSegmentNumber() {
        return segmentNumber;
    }

    public void setSegmentNumber(int segmentNumber) {
        this.segmentNumber = segmentNumber;
    }

    public int getSegSpan() {
        return segSpan;
    }

    public void setSegSpan(int segSpan) {
        this.segSpan = segSpan;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public String getShareNum() {
        return shareNum;
    }

    public void setShareNum(String shareNum) {
        this.shareNum = shareNum;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDepartureAirportShortName() {
        return departureAirportShortName;
    }

    public void setDepartureAirportShortName(String departureAirportShortName) {
        this.departureAirportShortName = departureAirportShortName;
    }

    public String getArriveAirportShortName() {
        return arriveAirportShortName;
    }

    public void setArriveAirportShortName(String arriveAirportShortName) {
        this.arriveAirportShortName = arriveAirportShortName;
    }
}
