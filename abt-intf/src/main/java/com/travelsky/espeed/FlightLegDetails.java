//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element ref="{http://espeed.travelsky.com}OperatingAirline" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Equipment" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}MarketingCabinAvailability" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}MarketingAirline" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="JourneyString" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="GroundString" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="OnTimeRate" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="Ticket" type="{http://espeed.travelsky.com}TicketType" />
 *       &lt;attribute name="DepartureDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ArrivalDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="DepartureDayOfWeek" type="{http://espeed.travelsky.com}DayOfWeekType" />
 *       &lt;attribute name="LegDistance" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="DateChangeNbr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SmokingAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
    "operatingAirline",
    "equipment",
    "marketingCabinAvailability",
    "marketingAirline"
})
@XmlRootElement(name = "FlightLegDetails")
public class FlightLegDetails {

    @XmlElement(name = "DepartureAirport")
    protected DepartureAirport departureAirport;
    @XmlElement(name = "ArrivalAirport")
    protected ArrivalAirport arrivalAirport;
    @XmlElement(name = "OperatingAirline")
    protected OperatingAirline operatingAirline;
    @XmlElement(name = "Equipment")
    protected Equipment equipment;
    @XmlElement(name = "MarketingCabinAvailability")
    protected List<MarketingCabinAvailability> marketingCabinAvailability;
    @XmlElement(name = "MarketingAirline")
    protected MarketingAirline marketingAirline;
    @XmlAttribute(name = "SequenceNumber")
    protected BigDecimal sequenceNumber;
    @XmlAttribute(name = "FlightNumber")
    protected String flightNumber;
    @XmlAttribute(name = "JourneyString")
    protected String journeyString;
    @XmlAttribute(name = "GroundString")
    protected String groundString;
    @XmlAttribute(name = "OnTimeRate")
    protected BigDecimal onTimeRate;
    @XmlAttribute(name = "Ticket")
    protected TicketType ticket;
    @XmlAttribute(name = "DepartureDateTime")
    @XmlSchemaType(name = "dateTime")
    protected String departureDateTime;
    @XmlAttribute(name = "ArrivalDateTime")
    @XmlSchemaType(name = "dateTime")
    protected String arrivalDateTime;
    @XmlAttribute(name = "DepartureDayOfWeek")
    protected DayOfWeekType departureDayOfWeek;
    @XmlAttribute(name = "LegDistance")
    protected BigDecimal legDistance;
    @XmlAttribute(name = "DateChangeNbr")
    protected String dateChangeNbr;
    @XmlAttribute(name = "SmokingAllowed")
    protected Boolean smokingAllowed;

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
     * Gets the value of the marketingCabinAvailability property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketingCabinAvailability property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketingCabinAvailability().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketingCabinAvailability }
     * 
     * 
     */
    public List<MarketingCabinAvailability> getMarketingCabinAvailability() {
        if (marketingCabinAvailability == null) {
            marketingCabinAvailability = new ArrayList<MarketingCabinAvailability>();
        }
        return this.marketingCabinAvailability;
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
     * 获取sequenceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * 设置sequenceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSequenceNumber(BigDecimal value) {
        this.sequenceNumber = value;
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

    /**
     * 获取ticket属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TicketType }
     *     
     */
    public TicketType getTicket() {
        return ticket;
    }

    /**
     * 设置ticket属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TicketType }
     *     
     */
    public void setTicket(TicketType value) {
        this.ticket = value;
    }

    /**
     * 获取departureDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureDateTime() {
        return departureDateTime;
    }

    /**
     * 设置departureDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureDateTime(String value) {
        this.departureDateTime = value;
    }

    /**
     * 获取arrivalDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    /**
     * 设置arrivalDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalDateTime(String value) {
        this.arrivalDateTime = value;
    }

    /**
     * 获取departureDayOfWeek属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DayOfWeekType }
     *     
     */
    public DayOfWeekType getDepartureDayOfWeek() {
        return departureDayOfWeek;
    }

    /**
     * 设置departureDayOfWeek属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DayOfWeekType }
     *     
     */
    public void setDepartureDayOfWeek(DayOfWeekType value) {
        this.departureDayOfWeek = value;
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
     * 获取dateChangeNbr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateChangeNbr() {
        return dateChangeNbr;
    }

    /**
     * 设置dateChangeNbr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateChangeNbr(String value) {
        this.dateChangeNbr = value;
    }

    /**
     * 获取smokingAllowed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    /**
     * 设置smokingAllowed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSmokingAllowed(Boolean value) {
        this.smokingAllowed = value;
    }

}
