//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *       &lt;/sequence>
 *       &lt;attribute name="Date" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ResBookDesigCode" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "arrivalAirport"
})
@XmlRootElement(name = "FlightLeg")
public class FlightLeg {

    @XmlElement(name = "DepartureAirport")
    protected DepartureAirport departureAirport;
    @XmlElement(name = "ArrivalAirport")
    protected ArrivalAirport arrivalAirport;
    @XmlAttribute(name = "Date")
    protected String date;
    @XmlAttribute(name = "FlightNumber")
    protected String flightNumber;
    @XmlAttribute(name = "ResBookDesigCode")
    protected String resBookDesigCode;

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
     * 获取date属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置date属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
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

}
