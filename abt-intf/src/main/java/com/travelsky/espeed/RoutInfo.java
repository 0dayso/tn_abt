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
 *         &lt;element ref="{http://espeed.travelsky.com}Airline"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FlightNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BookingClass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OIInd" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airline"
})
@XmlRootElement(name = "RoutInfo")
public class RoutInfo {

    @XmlElement(name = "Airline", required = true)
    protected Airline airline;
    @XmlAttribute(name = "FlightNumber", required = true)
    protected String flightNumber;
    @XmlAttribute(name = "BookingClass", required = true)
    protected String bookingClass;
    @XmlAttribute(name = "OIInd", required = true)
    protected String oiInd;

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
     * 获取bookingClass属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingClass() {
        return bookingClass;
    }

    /**
     * 设置bookingClass属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingClass(String value) {
        this.bookingClass = value;
    }

    /**
     * 获取oiInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOIInd() {
        return oiInd;
    }

    /**
     * 设置oiInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOIInd(String value) {
        this.oiInd = value;
    }

}
