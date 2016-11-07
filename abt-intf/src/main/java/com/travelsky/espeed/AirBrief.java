//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DepartureDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
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
    "flightNumber",
    "departureDate"
})
@XmlRootElement(name = "AirBrief")
public class AirBrief {

    @XmlElement(name = "Airline", required = true)
    protected Airline airline;
    @XmlElement(name = "FlightNumber", required = true)
    protected String flightNumber;
    @XmlElement(name = "DepartureDate", required = true)
    @XmlSchemaType(name = "date")
    protected String departureDate;

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

}
