//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:13:27 PM CST 
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
 *         &lt;element ref="{http://espeed.travelsky.com}POS"/>
 *         &lt;element ref="{http://espeed.travelsky.com}OriginDestinationInformation"/>
 *         &lt;element ref="{http://espeed.travelsky.com}SpecificFlightInfo" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelPreferences" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerInfoSummary" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pos",
    "originDestinationInformation",
    "specificFlightInfo",
    "travelPreferences",
    "travelerInfoSummary"
})
@XmlRootElement(name = "OTA_AirFareDisplayRQ")
public class OTAAirFareDisplayRQ {

    @XmlElement(name = "POS", required = true)
    protected POS pos;
    @XmlElement(name = "OriginDestinationInformation", required = true)
    protected OriginDestinationInformation originDestinationInformation;
    @XmlElement(name = "SpecificFlightInfo")
    protected SpecificFlightInfo specificFlightInfo;
    @XmlElement(name = "TravelPreferences")
    protected TravelPreferences travelPreferences;
    @XmlElement(name = "TravelerInfoSummary")
    protected TravelerInfoSummary travelerInfoSummary;
    @XmlAttribute(name = "EchoToken")
    protected String echoToken;
    @XmlAttribute(name = "TimeStamp")
    protected String timeStamp;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "Target")
    protected String target;

    /**
     * 获取pos属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POS }
     *     
     */
    public POS getPOS() {
        return pos;
    }

    /**
     * 设置pos属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POS }
     *     
     */
    public void setPOS(POS value) {
        this.pos = value;
    }

    /**
     * 获取originDestinationInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OriginDestinationInformation }
     *     
     */
    public OriginDestinationInformation getOriginDestinationInformation() {
        return originDestinationInformation;
    }

    /**
     * 设置originDestinationInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OriginDestinationInformation }
     *     
     */
    public void setOriginDestinationInformation(OriginDestinationInformation value) {
        this.originDestinationInformation = value;
    }

    /**
     * 获取specificFlightInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SpecificFlightInfo }
     *     
     */
    public SpecificFlightInfo getSpecificFlightInfo() {
        return specificFlightInfo;
    }

    /**
     * 设置specificFlightInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SpecificFlightInfo }
     *     
     */
    public void setSpecificFlightInfo(SpecificFlightInfo value) {
        this.specificFlightInfo = value;
    }

    /**
     * 获取travelPreferences属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TravelPreferences }
     *     
     */
    public TravelPreferences getTravelPreferences() {
        return travelPreferences;
    }

    /**
     * 设置travelPreferences属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TravelPreferences }
     *     
     */
    public void setTravelPreferences(TravelPreferences value) {
        this.travelPreferences = value;
    }

    /**
     * 获取travelerInfoSummary属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TravelerInfoSummary }
     *     
     */
    public TravelerInfoSummary getTravelerInfoSummary() {
        return travelerInfoSummary;
    }

    /**
     * 设置travelerInfoSummary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TravelerInfoSummary }
     *     
     */
    public void setTravelerInfoSummary(TravelerInfoSummary value) {
        this.travelerInfoSummary = value;
    }

    /**
     * 获取echoToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEchoToken() {
        return echoToken;
    }

    /**
     * 设置echoToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEchoToken(String value) {
        this.echoToken = value;
    }

    /**
     * 获取timeStamp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * 设置timeStamp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStamp(String value) {
        this.timeStamp = value;
    }

    /**
     * 获取version属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置version属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * 获取target属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置target属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

}
