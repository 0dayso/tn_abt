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
 *         &lt;element ref="{http://espeed.travelsky.com}OperatingAirline" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Equipment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}MarketingAirline" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DepartureDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "marketingAirline"
})
@XmlRootElement(name = "FlightSegmentInfo")
public class FlightSegmentInfo {

    @XmlElement(name = "DepartureAirport")
    protected DepartureAirport departureAirport;
    @XmlElement(name = "ArrivalAirport")
    protected ArrivalAirport arrivalAirport;
    @XmlElement(name = "OperatingAirline")
    protected OperatingAirline operatingAirline;
    @XmlElement(name = "Equipment")
    protected List<Equipment> equipment;
    @XmlElement(name = "MarketingAirline")
    protected MarketingAirline marketingAirline;
    @XmlAttribute(name = "RPH")
    protected String rph;
    @XmlAttribute(name = "DepartureDateTime")
    @XmlSchemaType(name = "dateTime")
    protected String departureDateTime;
    @XmlAttribute(name = "FlightNumber")
    protected String flightNumber;

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
     * Gets the value of the equipment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equipment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEquipment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Equipment }
     * 
     * 
     */
    public List<Equipment> getEquipment() {
        if (equipment == null) {
            equipment = new ArrayList<Equipment>();
        }
        return this.equipment;
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

}
