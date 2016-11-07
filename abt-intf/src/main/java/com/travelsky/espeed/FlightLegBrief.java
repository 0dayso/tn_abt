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
 *         &lt;element ref="{http://espeed.travelsky.com}DepartureAirport" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}ArrivalAirport" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Equipment" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}MarketingCabinAvailability" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DepartureDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ArrivalDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
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
    "equipment",
    "marketingCabinAvailability"
})
@XmlRootElement(name = "FlightLegBrief")
public class FlightLegBrief {

    @XmlElement(name = "DepartureAirport")
    protected DepartureAirport departureAirport;
    @XmlElement(name = "ArrivalAirport")
    protected ArrivalAirport arrivalAirport;
    @XmlElement(name = "Equipment")
    protected Equipment equipment;
    @XmlElement(name = "MarketingCabinAvailability")
    protected List<MarketingCabinAvailability> marketingCabinAvailability;
    @XmlAttribute(name = "FlightNumber")
    protected String flightNumber;
    @XmlAttribute(name = "DepartureDateTime")
    @XmlSchemaType(name = "dateTime")
    protected String departureDateTime;
    @XmlAttribute(name = "ArrivalDateTime")
    @XmlSchemaType(name = "dateTime")
    protected String arrivalDateTime;

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

}
