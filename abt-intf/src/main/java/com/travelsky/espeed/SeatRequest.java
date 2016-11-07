//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;
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
 *         &lt;element ref="{http://espeed.travelsky.com}Airline" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}DepartureAirport" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}ArrivalAirport" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FlightRefNumber" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerRefNumber" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FlightLeg" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SeatInRow" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SmokingAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DepartureDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="PartialSeatingInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="RowNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SeatNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SeatPreference" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DeckNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airline",
    "departureAirport",
    "arrivalAirport",
    "flightRefNumber",
    "travelerRefNumber",
    "flightLeg"
})
@XmlRootElement(name = "SeatRequest")
public class SeatRequest {

    @XmlElement(name = "Airline")
    protected Airline airline;
    @XmlElement(name = "DepartureAirport")
    protected DepartureAirport departureAirport;
    @XmlElement(name = "ArrivalAirport")
    protected ArrivalAirport arrivalAirport;
    @XmlElement(name = "FlightRefNumber")
    protected List<FlightRefNumber> flightRefNumber;
    @XmlElement(name = "TravelerRefNumber")
    protected List<TravelerRefNumber> travelerRefNumber;
    @XmlElement(name = "FlightLeg")
    protected List<FlightLeg> flightLeg;
    @XmlAttribute(name = "FlightNumber")
    protected String flightNumber;
    @XmlAttribute(name = "SeatInRow")
    protected String seatInRow;
    @XmlAttribute(name = "SmokingAllowed")
    protected Boolean smokingAllowed;
    @XmlAttribute(name = "Status")
    protected String status;
    @XmlAttribute(name = "DepartureDate")
    @XmlSchemaType(name = "date")
    protected String departureDate;
    @XmlAttribute(name = "PartialSeatingInd")
    protected Boolean partialSeatingInd;
    @XmlAttribute(name = "RowNumber")
    protected String rowNumber;
    @XmlAttribute(name = "SeatNumber")
    protected String seatNumber;
    @XmlAttribute(name = "SeatPreference")
    protected String seatPreference;
    @XmlAttribute(name = "DeckNumber")
    protected String deckNumber;

    /**
     * 获取airline属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Airline }
     *     
     */
    public Airline getAirline() {
        return airline;
    }

    /**
     * 设置airline属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Airline }
     *     
     */
    public void setAirline(Airline value) {
        this.airline = value;
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
     * Gets the value of the flightRefNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightRefNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightRefNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightRefNumber }
     * 
     * 
     */
    public List<FlightRefNumber> getFlightRefNumber() {
        if (flightRefNumber == null) {
            flightRefNumber = new ArrayList<FlightRefNumber>();
        }
        return this.flightRefNumber;
    }

    /**
     * Gets the value of the travelerRefNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the travelerRefNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTravelerRefNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TravelerRefNumber }
     * 
     * 
     */
    public List<TravelerRefNumber> getTravelerRefNumber() {
        if (travelerRefNumber == null) {
            travelerRefNumber = new ArrayList<TravelerRefNumber>();
        }
        return this.travelerRefNumber;
    }

    /**
     * Gets the value of the flightLeg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightLeg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightLeg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightLeg }
     * 
     * 
     */
    public List<FlightLeg> getFlightLeg() {
        if (flightLeg == null) {
            flightLeg = new ArrayList<FlightLeg>();
        }
        return this.flightLeg;
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
     * 获取seatInRow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatInRow() {
        return seatInRow;
    }

    /**
     * 设置seatInRow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatInRow(String value) {
        this.seatInRow = value;
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
     * 获取departureDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * 设置departureDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureDate(String value) {
        this.departureDate = value;
    }

    /**
     * 获取partialSeatingInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPartialSeatingInd() {
        return partialSeatingInd;
    }

    /**
     * 设置partialSeatingInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPartialSeatingInd(Boolean value) {
        this.partialSeatingInd = value;
    }

    /**
     * 获取rowNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowNumber() {
        return rowNumber;
    }

    /**
     * 设置rowNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowNumber(String value) {
        this.rowNumber = value;
    }

    /**
     * 获取seatNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatNumber() {
        return seatNumber;
    }

    /**
     * 设置seatNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatNumber(String value) {
        this.seatNumber = value;
    }

    /**
     * 获取seatPreference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatPreference() {
        return seatPreference;
    }

    /**
     * 设置seatPreference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatPreference(String value) {
        this.seatPreference = value;
    }

    /**
     * 获取deckNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeckNumber() {
        return deckNumber;
    }

    /**
     * 设置deckNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeckNumber(String value) {
        this.deckNumber = value;
    }

}
