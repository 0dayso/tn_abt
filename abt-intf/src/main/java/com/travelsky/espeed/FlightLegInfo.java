//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;

import java.math.BigDecimal;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://espeed.travelsky.com}DepartureAirport" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}ArrivalAirport" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}MarketingAirline"/>
 *         &lt;element ref="{http://espeed.travelsky.com}OperatingAirline" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Equipment" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}DepartureDateTime" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}ArrivalDateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FlightStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LegDistance" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="JourneyString" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="GroundString" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="AccumulatedString" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="OnTimeRate" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "departureAirport",
    "arrivalAirport",
    "marketingAirline",
    "operatingAirline",
    "equipment",
    "departureDateTime",
    "arrivalDateTime"
})
@XmlRootElement(name = "FlightLegInfo")
public class FlightLegInfo {

    @XmlElement(name = "DepartureAirport")
    protected DepartureAirport departureAirport;
    @XmlElement(name = "ArrivalAirport")
    protected ArrivalAirport arrivalAirport;
    @XmlElement(name = "MarketingAirline", required = true)
    protected MarketingAirline marketingAirline;
    @XmlElement(name = "OperatingAirline")
    protected OperatingAirline operatingAirline;
    @XmlElement(name = "Equipment")
    protected Equipment equipment;
    @XmlElement(name = "DepartureDateTime")
    protected DepartureDateTime departureDateTime;
    @XmlElement(name = "ArrivalDateTime")
    protected ArrivalDateTime arrivalDateTime;
    @XmlAttribute(name = "FlightNumber")
    protected String flightNumber;
    @XmlAttribute(name = "FlightStatus")
    protected String flightStatus;
    @XmlAttribute(name = "LegDistance")
    protected BigDecimal legDistance;
    @XmlAttribute(name = "JourneyString")
    protected String journeyString;
    @XmlAttribute(name = "GroundString")
    protected String groundString;
    @XmlAttribute(name = "AccumulatedString")
    protected String accumulatedString;
    @XmlAttribute(name = "OnTimeRate")
    protected BigDecimal onTimeRate;

    /**
     * 获取departureAirport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DepartureAirport }
     *     
     */
    public DepartureAirport getDepartureAirport() {
        return departureAirport;
    }

    /**
     * 设置departureAirport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DepartureAirport }
     *     
     */
    public void setDepartureAirport(DepartureAirport value) {
        this.departureAirport = value;
    }

    /**
     * 获取arrivalAirport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrivalAirport }
     *     
     */
    public ArrivalAirport getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * 设置arrivalAirport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrivalAirport }
     *     
     */
    public void setArrivalAirport(ArrivalAirport value) {
        this.arrivalAirport = value;
    }

    /**
     * 获取marketingAirline属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MarketingAirline }
     *     
     */
    public MarketingAirline getMarketingAirline() {
        return marketingAirline;
    }

    /**
     * 设置marketingAirline属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MarketingAirline }
     *     
     */
    public void setMarketingAirline(MarketingAirline value) {
        this.marketingAirline = value;
    }

    /**
     * 获取operatingAirline属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OperatingAirline }
     *     
     */
    public OperatingAirline getOperatingAirline() {
        return operatingAirline;
    }

    /**
     * 设置operatingAirline属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OperatingAirline }
     *     
     */
    public void setOperatingAirline(OperatingAirline value) {
        this.operatingAirline = value;
    }

    /**
     * 获取equipment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Equipment }
     *     
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * 设置equipment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Equipment }
     *     
     */
    public void setEquipment(Equipment value) {
        this.equipment = value;
    }

    /**
     * 获取departureDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DepartureDateTime }
     *     
     */
    public DepartureDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    /**
     * 设置departureDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DepartureDateTime }
     *     
     */
    public void setDepartureDateTime(DepartureDateTime value) {
        this.departureDateTime = value;
    }

    /**
     * 获取arrivalDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrivalDateTime }
     *     
     */
    public ArrivalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    /**
     * 设置arrivalDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrivalDateTime }
     *     
     */
    public void setArrivalDateTime(ArrivalDateTime value) {
        this.arrivalDateTime = value;
    }

    /**
     * 获取flightNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * 设置flightNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightNumber(String value) {
        this.flightNumber = value;
    }

    /**
     * 获取flightStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightStatus() {
        return flightStatus;
    }

    /**
     * 设置flightStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightStatus(String value) {
        this.flightStatus = value;
    }

    /**
     * 获取legDistance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLegDistance() {
        return legDistance;
    }

    /**
     * 设置legDistance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLegDistance(BigDecimal value) {
        this.legDistance = value;
    }

    /**
     * 获取journeyString属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJourneyString() {
        return journeyString;
    }

    /**
     * 设置journeyString属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJourneyString(String value) {
        this.journeyString = value;
    }

    /**
     * 获取groundString属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroundString() {
        return groundString;
    }

    /**
     * 设置groundString属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroundString(String value) {
        this.groundString = value;
    }

    /**
     * 获取accumulatedString属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccumulatedString() {
        return accumulatedString;
    }

    /**
     * 设置accumulatedString属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccumulatedString(String value) {
        this.accumulatedString = value;
    }

    /**
     * 获取onTimeRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOnTimeRate() {
        return onTimeRate;
    }

    /**
     * 设置onTimeRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOnTimeRate(BigDecimal value) {
        this.onTimeRate = value;
    }

}
