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
 *         &lt;element ref="{http://espeed.travelsky.com}FlightTypePref" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FareRestrictPref" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}CabinPref" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ETicketDesired" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "flightTypePref",
    "fareRestrictPref",
    "cabinPref"
})
@XmlRootElement(name = "TravelPreferences")
public class TravelPreferences {

    @XmlElement(name = "FlightTypePref")
    protected FlightTypePref flightTypePref;
    @XmlElement(name = "FareRestrictPref")
    protected FareRestrictPref fareRestrictPref;
    @XmlElement(name = "CabinPref")
    protected CabinPref cabinPref;
    @XmlAttribute(name = "ETicketDesired")
    protected Boolean eTicketDesired;

    /**
     * 获取flightTypePref属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FlightTypePref }
     *     
     */
    public FlightTypePref getFlightTypePref() {
        return flightTypePref;
    }

    /**
     * 设置flightTypePref属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FlightTypePref }
     *     
     */
    public void setFlightTypePref(FlightTypePref value) {
        this.flightTypePref = value;
    }

    /**
     * 获取fareRestrictPref属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareRestrictPref }
     *     
     */
    public FareRestrictPref getFareRestrictPref() {
        return fareRestrictPref;
    }

    /**
     * 设置fareRestrictPref属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareRestrictPref }
     *     
     */
    public void setFareRestrictPref(FareRestrictPref value) {
        this.fareRestrictPref = value;
    }

    /**
     * 获取cabinPref属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CabinPref }
     *     
     */
    public CabinPref getCabinPref() {
        return cabinPref;
    }

    /**
     * 设置cabinPref属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CabinPref }
     *     
     */
    public void setCabinPref(CabinPref value) {
        this.cabinPref = value;
    }

    /**
     * 获取eTicketDesired属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isETicketDesired() {
        return eTicketDesired;
    }

    /**
     * 设置eTicketDesired属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setETicketDesired(Boolean value) {
        this.eTicketDesired = value;
    }

}
