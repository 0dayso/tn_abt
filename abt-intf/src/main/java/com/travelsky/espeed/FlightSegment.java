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
 *         &lt;element ref="{http://espeed.travelsky.com}OperatingAirline" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}DepartureAirport"/>
 *         &lt;element ref="{http://espeed.travelsky.com}ArrivalAirport"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Equipment" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}MarketingAirline" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}BookingClassAvail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}DaysOfOperation" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Baggage" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DepartureDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="DepDateChangeNbr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ArrivalDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ArrDateChangeNbr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="StopQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="ASRInd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ParticipationLevelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CodeshareInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Ticket" type="{http://espeed.travelsky.com}TicketType" />
 *       &lt;attribute name="TicketStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoutNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SegmentID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PageInd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DOW" type="{http://espeed.travelsky.com}DayOfWeekType" />
 *       &lt;attribute name="MealCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EntertainmentCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ClassDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AdHocInd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CoHostInd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Addition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ResBookDesigCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NumberInParty" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="Status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FlightTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="SegmentType" type="{http://espeed.travelsky.com}SegmentType" />
 *       &lt;attribute name="ScheduleValidStartDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="ScheduleValidEndDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="DepartureTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="ArrivalTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="IsChanged" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="BoardingNo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CrsPnrNo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CrsType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="McoNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FPC" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="StopType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SegmentStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PnrNo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="InterSegment" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "operatingAirline",
    "departureAirport",
    "arrivalAirport",
    "equipment",
    "marketingAirline",
    "bookingClassAvail",
    "daysOfOperation",
    "baggage"
})
@XmlRootElement(name = "FlightSegment")
public class FlightSegment {

    @XmlElement(name = "OperatingAirline")
    protected OperatingAirline operatingAirline;
    @XmlElement(name = "DepartureAirport", required = true)
    protected DepartureAirport departureAirport;
    @XmlElement(name = "ArrivalAirport", required = true)
    protected ArrivalAirport arrivalAirport;
    @XmlElement(name = "Equipment")
    protected Equipment equipment;
    @XmlElement(name = "MarketingAirline")
    protected MarketingAirline marketingAirline;
    @XmlElement(name = "BookingClassAvail")
    protected List<BookingClassAvail> bookingClassAvail;
    @XmlElement(name = "DaysOfOperation")
    protected DaysOfOperation daysOfOperation;
    @XmlElement(name = "Baggage")
    protected Baggage baggage;
    @XmlAttribute(name = "RPH")
    protected String rph;
    @XmlAttribute(name = "DepartureDateTime")
    @XmlSchemaType(name = "dateTime")
    protected String departureDateTime;
    @XmlAttribute(name = "DepDateChangeNbr")
    protected String depDateChangeNbr;
    @XmlAttribute(name = "ArrivalDateTime")
    @XmlSchemaType(name = "dateTime")
    protected String arrivalDateTime;
    @XmlAttribute(name = "ArrDateChangeNbr")
    protected String arrDateChangeNbr;
    @XmlAttribute(name = "StopQuantity")
    protected BigDecimal stopQuantity;
    @XmlAttribute(name = "ASRInd")
    protected String asrInd;
    @XmlAttribute(name = "ParticipationLevelCode")
    protected String participationLevelCode;
    @XmlAttribute(name = "CodeshareInd")
    protected Boolean codeshareInd;
    @XmlAttribute(name = "FlightNumber")
    protected String flightNumber;
    @XmlAttribute(name = "Ticket")
    protected TicketType ticket;
    @XmlAttribute(name = "TicketStatus")
    protected String ticketStatus;
    @XmlAttribute(name = "RoutNumber")
    protected String routNumber;
    @XmlAttribute(name = "SegmentID")
    protected String segmentID;
    @XmlAttribute(name = "PageInd")
    protected String pageInd;
    @XmlAttribute(name = "DOW")
    protected DayOfWeekType dow;
    @XmlAttribute(name = "MealCode")
    protected String mealCode;
    @XmlAttribute(name = "EntertainmentCode")
    protected String entertainmentCode;
    @XmlAttribute(name = "ClassDescription")
    protected String classDescription;
    @XmlAttribute(name = "AdHocInd")
    protected String adHocInd;
    @XmlAttribute(name = "CoHostInd")
    protected String coHostInd;
    @XmlAttribute(name = "Addition")
    protected String addition;
    @XmlAttribute(name = "ResBookDesigCode")
    protected String resBookDesigCode;
    @XmlAttribute(name = "NumberInParty")
    protected BigDecimal numberInParty;
    @XmlAttribute(name = "Status")
    protected String status;
    @XmlAttribute(name = "FlightTime")
    protected String flightTime;
    @XmlAttribute(name = "SegmentType")
    protected SegmentType segmentType;
    @XmlAttribute(name = "ScheduleValidStartDate")
    @XmlSchemaType(name = "date")
    protected String scheduleValidStartDate;
    @XmlAttribute(name = "ScheduleValidEndDate")
    @XmlSchemaType(name = "date")
    protected String scheduleValidEndDate;
    @XmlAttribute(name = "DepartureTime")
    @XmlSchemaType(name = "time")
    protected String departureTime;
    @XmlAttribute(name = "ArrivalTime")
    @XmlSchemaType(name = "time")
    protected String arrivalTime;
    @XmlAttribute(name = "IsChanged")
    protected Boolean isChanged;
    @XmlAttribute(name = "BoardingNo")
    protected String boardingNo;
    @XmlAttribute(name = "CrsPnrNo")
    protected String crsPnrNo;
    @XmlAttribute(name = "CrsType")
    protected String crsType;
    @XmlAttribute(name = "McoNumber")
    protected String mcoNumber;
    @XmlAttribute(name = "FPC")
    protected Boolean fpc;
    @XmlAttribute(name = "StopType")
    protected String stopType;
    @XmlAttribute(name = "SegmentStatus")
    protected String segmentStatus;
    @XmlAttribute(name = "PnrNo")
    protected String pnrNo;
    @XmlAttribute(name = "InterSegment")
    protected Boolean interSegment;

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
     * Gets the value of the bookingClassAvail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingClassAvail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingClassAvail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BookingClassAvail }
     * 
     * 
     */
    public List<BookingClassAvail> getBookingClassAvail() {
        if (bookingClassAvail == null) {
            bookingClassAvail = new ArrayList<BookingClassAvail>();
        }
        return this.bookingClassAvail;
    }

    /**
     * 获取daysOfOperation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DaysOfOperation }
     *     
     */
    public DaysOfOperation getDaysOfOperation() {
        return daysOfOperation;
    }

    /**
     * 设置daysOfOperation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DaysOfOperation }
     *     
     */
    public void setDaysOfOperation(DaysOfOperation value) {
        this.daysOfOperation = value;
    }

    /**
     * 获取baggage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Baggage }
     *     
     */
    public Baggage getBaggage() {
        return baggage;
    }

    /**
     * 设置baggage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Baggage }
     *     
     */
    public void setBaggage(Baggage value) {
        this.baggage = value;
    }

    /**
     * 获取rph属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRPH() {
        return rph;
    }

    /**
     * 设置rph属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRPH(String value) {
        this.rph = value;
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
     * 获取depDateChangeNbr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepDateChangeNbr() {
        return depDateChangeNbr;
    }

    /**
     * 设置depDateChangeNbr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepDateChangeNbr(String value) {
        this.depDateChangeNbr = value;
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
     * 获取arrDateChangeNbr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrDateChangeNbr() {
        return arrDateChangeNbr;
    }

    /**
     * 设置arrDateChangeNbr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrDateChangeNbr(String value) {
        this.arrDateChangeNbr = value;
    }

    /**
     * 获取stopQuantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStopQuantity() {
        return stopQuantity;
    }

    /**
     * 设置stopQuantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStopQuantity(BigDecimal value) {
        this.stopQuantity = value;
    }

    /**
     * 获取asrInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getASRInd() {
        return asrInd;
    }

    /**
     * 设置asrInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setASRInd(String value) {
        this.asrInd = value;
    }

    /**
     * 获取participationLevelCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipationLevelCode() {
        return participationLevelCode;
    }

    /**
     * 设置participationLevelCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipationLevelCode(String value) {
        this.participationLevelCode = value;
    }

    /**
     * 获取codeshareInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCodeshareInd() {
        return codeshareInd;
    }

    /**
     * 设置codeshareInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCodeshareInd(Boolean value) {
        this.codeshareInd = value;
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
     * 获取ticketStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketStatus() {
        return ticketStatus;
    }

    /**
     * 设置ticketStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketStatus(String value) {
        this.ticketStatus = value;
    }

    /**
     * 获取routNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutNumber() {
        return routNumber;
    }

    /**
     * 设置routNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutNumber(String value) {
        this.routNumber = value;
    }

    /**
     * 获取segmentID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentID() {
        return segmentID;
    }

    /**
     * 设置segmentID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentID(String value) {
        this.segmentID = value;
    }

    /**
     * 获取pageInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageInd() {
        return pageInd;
    }

    /**
     * 设置pageInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageInd(String value) {
        this.pageInd = value;
    }

    /**
     * 获取dow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DayOfWeekType }
     *     
     */
    public DayOfWeekType getDOW() {
        return dow;
    }

    /**
     * 设置dow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DayOfWeekType }
     *     
     */
    public void setDOW(DayOfWeekType value) {
        this.dow = value;
    }

    /**
     * 获取mealCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMealCode() {
        return mealCode;
    }

    /**
     * 设置mealCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMealCode(String value) {
        this.mealCode = value;
    }

    /**
     * 获取entertainmentCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntertainmentCode() {
        return entertainmentCode;
    }

    /**
     * 设置entertainmentCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntertainmentCode(String value) {
        this.entertainmentCode = value;
    }

    /**
     * 获取classDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassDescription() {
        return classDescription;
    }

    /**
     * 设置classDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassDescription(String value) {
        this.classDescription = value;
    }

    /**
     * 获取adHocInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdHocInd() {
        return adHocInd;
    }

    /**
     * 设置adHocInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdHocInd(String value) {
        this.adHocInd = value;
    }

    /**
     * 获取coHostInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoHostInd() {
        return coHostInd;
    }

    /**
     * 设置coHostInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoHostInd(String value) {
        this.coHostInd = value;
    }

    /**
     * 获取addition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddition() {
        return addition;
    }

    /**
     * 设置addition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddition(String value) {
        this.addition = value;
    }

    /**
     * 获取resBookDesigCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResBookDesigCode() {
        return resBookDesigCode;
    }

    /**
     * 设置resBookDesigCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResBookDesigCode(String value) {
        this.resBookDesigCode = value;
    }

    /**
     * 获取numberInParty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberInParty() {
        return numberInParty;
    }

    /**
     * 设置numberInParty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberInParty(BigDecimal value) {
        this.numberInParty = value;
    }

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * 获取flightTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightTime() {
        return flightTime;
    }

    /**
     * 设置flightTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightTime(String value) {
        this.flightTime = value;
    }

    /**
     * 获取segmentType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SegmentType }
     *     
     */
    public SegmentType getSegmentType() {
        return segmentType;
    }

    /**
     * 设置segmentType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SegmentType }
     *     
     */
    public void setSegmentType(SegmentType value) {
        this.segmentType = value;
    }

    /**
     * 获取scheduleValidStartDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduleValidStartDate() {
        return scheduleValidStartDate;
    }

    /**
     * 设置scheduleValidStartDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduleValidStartDate(String value) {
        this.scheduleValidStartDate = value;
    }

    /**
     * 获取scheduleValidEndDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduleValidEndDate() {
        return scheduleValidEndDate;
    }

    /**
     * 设置scheduleValidEndDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduleValidEndDate(String value) {
        this.scheduleValidEndDate = value;
    }

    /**
     * 获取departureTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * 设置departureTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureTime(String value) {
        this.departureTime = value;
    }

    /**
     * 获取arrivalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 设置arrivalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalTime(String value) {
        this.arrivalTime = value;
    }

    /**
     * 获取isChanged属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsChanged() {
        return isChanged;
    }

    /**
     * 设置isChanged属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsChanged(Boolean value) {
        this.isChanged = value;
    }

    /**
     * 获取boardingNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardingNo() {
        return boardingNo;
    }

    /**
     * 设置boardingNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardingNo(String value) {
        this.boardingNo = value;
    }

    /**
     * 获取crsPnrNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrsPnrNo() {
        return crsPnrNo;
    }

    /**
     * 设置crsPnrNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrsPnrNo(String value) {
        this.crsPnrNo = value;
    }

    /**
     * 获取crsType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrsType() {
        return crsType;
    }

    /**
     * 设置crsType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrsType(String value) {
        this.crsType = value;
    }

    /**
     * 获取mcoNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMcoNumber() {
        return mcoNumber;
    }

    /**
     * 设置mcoNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMcoNumber(String value) {
        this.mcoNumber = value;
    }

    /**
     * 获取fpc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFPC() {
        return fpc;
    }

    /**
     * 设置fpc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFPC(Boolean value) {
        this.fpc = value;
    }

    /**
     * 获取stopType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStopType() {
        return stopType;
    }

    /**
     * 设置stopType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStopType(String value) {
        this.stopType = value;
    }

    /**
     * 获取segmentStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentStatus() {
        return segmentStatus;
    }

    /**
     * 设置segmentStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentStatus(String value) {
        this.segmentStatus = value;
    }

    /**
     * 获取pnrNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPnrNo() {
        return pnrNo;
    }

    /**
     * 设置pnrNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPnrNo(String value) {
        this.pnrNo = value;
    }

    /**
     * 获取interSegment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInterSegment() {
        return interSegment;
    }

    /**
     * 设置interSegment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInterSegment(Boolean value) {
        this.interSegment = value;
    }

}
