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
 *         &lt;element name="DepartureDateTime" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DepartureEarlyTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *         &lt;element name="DepartureLateTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}OriginLocation" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}DestinationLocation" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}OriginDestinationOptions" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "departureDateTime",
    "departureEarlyTime",
    "departureLateTime",
    "originLocation",
    "destinationLocation",
    "originDestinationOptions"
})
@XmlRootElement(name = "OriginDestinationInformation")
public class OriginDestinationInformation {

    @XmlElement(name = "DepartureDateTime")
    @XmlSchemaType(name = "date")
    protected String departureDateTime;
    @XmlElement(name = "DepartureEarlyTime")
    @XmlSchemaType(name = "time")
    protected String departureEarlyTime;
    @XmlElement(name = "DepartureLateTime")
    @XmlSchemaType(name = "time")
    protected String departureLateTime;
    @XmlElement(name = "OriginLocation")
    protected OriginLocation originLocation;
    @XmlElement(name = "DestinationLocation")
    protected DestinationLocation destinationLocation;
    @XmlElement(name = "OriginDestinationOptions")
    protected OriginDestinationOptions originDestinationOptions;
    @XmlAttribute(name = "RPH")
    protected String rph;

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
     * 获取departureEarlyTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureEarlyTime() {
        return departureEarlyTime;
    }

    /**
     * 设置departureEarlyTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureEarlyTime(String value) {
        this.departureEarlyTime = value;
    }

    /**
     * 获取departureLateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureLateTime() {
        return departureLateTime;
    }

    /**
     * 设置departureLateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureLateTime(String value) {
        this.departureLateTime = value;
    }

    /**
     * 获取originLocation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OriginLocation }
     *     
     */
    public OriginLocation getOriginLocation() {
        return originLocation;
    }

    /**
     * 设置originLocation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OriginLocation }
     *     
     */
    public void setOriginLocation(OriginLocation value) {
        this.originLocation = value;
    }

    /**
     * 获取destinationLocation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DestinationLocation }
     *     
     */
    public DestinationLocation getDestinationLocation() {
        return destinationLocation;
    }

    /**
     * 设置destinationLocation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DestinationLocation }
     *     
     */
    public void setDestinationLocation(DestinationLocation value) {
        this.destinationLocation = value;
    }

    /**
     * 获取originDestinationOptions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OriginDestinationOptions }
     *     
     */
    public OriginDestinationOptions getOriginDestinationOptions() {
        return originDestinationOptions;
    }

    /**
     * 设置originDestinationOptions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OriginDestinationOptions }
     *     
     */
    public void setOriginDestinationOptions(OriginDestinationOptions value) {
        this.originDestinationOptions = value;
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

}
