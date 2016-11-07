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
 *       &lt;attribute name="FlightType" type="{http://espeed.travelsky.com}FlightType" />
 *       &lt;attribute name="JourneyType" type="{http://espeed.travelsky.com}JourneyType" />
 *       &lt;attribute name="DirectOnlyInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="NonScheduledFltInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DirectAndNonStopOnlyInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="NonStopsOnlyInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="OnlineConnectionsOnlyInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "FlightTypePref")
public class FlightTypePref {

    @XmlAttribute(name = "FlightType")
    protected FlightType flightType;
    @XmlAttribute(name = "JourneyType")
    protected JourneyType journeyType;
    @XmlAttribute(name = "DirectOnlyInd")
    protected Boolean directOnlyInd;
    @XmlAttribute(name = "NonScheduledFltInfo")
    protected Boolean nonScheduledFltInfo;
    @XmlAttribute(name = "DirectAndNonStopOnlyInd")
    protected Boolean directAndNonStopOnlyInd;
    @XmlAttribute(name = "NonStopsOnlyInd")
    protected Boolean nonStopsOnlyInd;
    @XmlAttribute(name = "OnlineConnectionsOnlyInd")
    protected Boolean onlineConnectionsOnlyInd;

    /**
     * 获取flightType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FlightType }
     *     
     */
    public FlightType getFlightType() {
        return flightType;
    }

    /**
     * 设置flightType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FlightType }
     *     
     */
    public void setFlightType(FlightType value) {
        this.flightType = value;
    }

    /**
     * 获取journeyType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JourneyType }
     *     
     */
    public JourneyType getJourneyType() {
        return journeyType;
    }

    /**
     * 设置journeyType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JourneyType }
     *     
     */
    public void setJourneyType(JourneyType value) {
        this.journeyType = value;
    }

    /**
     * 获取directOnlyInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDirectOnlyInd() {
        return directOnlyInd;
    }

    /**
     * 设置directOnlyInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDirectOnlyInd(Boolean value) {
        this.directOnlyInd = value;
    }

    /**
     * 获取nonScheduledFltInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonScheduledFltInfo() {
        return nonScheduledFltInfo;
    }

    /**
     * 设置nonScheduledFltInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonScheduledFltInfo(Boolean value) {
        this.nonScheduledFltInfo = value;
    }

    /**
     * 获取directAndNonStopOnlyInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDirectAndNonStopOnlyInd() {
        return directAndNonStopOnlyInd;
    }

    /**
     * 设置directAndNonStopOnlyInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDirectAndNonStopOnlyInd(Boolean value) {
        this.directAndNonStopOnlyInd = value;
    }

    /**
     * 获取nonStopsOnlyInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonStopsOnlyInd() {
        return nonStopsOnlyInd;
    }

    /**
     * 设置nonStopsOnlyInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonStopsOnlyInd(Boolean value) {
        this.nonStopsOnlyInd = value;
    }

    /**
     * 获取onlineConnectionsOnlyInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnlineConnectionsOnlyInd() {
        return onlineConnectionsOnlyInd;
    }

    /**
     * 设置onlineConnectionsOnlyInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnlineConnectionsOnlyInd(Boolean value) {
        this.onlineConnectionsOnlyInd = value;
    }

}
