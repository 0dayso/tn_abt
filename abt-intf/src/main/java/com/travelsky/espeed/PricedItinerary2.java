//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:30:57 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://espeed.travelsky.com}AirItinerary2" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}AirItineraryPricingInfo2" minOccurs="0"/>
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
    "airItinerary",
    "airItineraryPricingInfo"
})
@XmlRootElement(name = "PricedItinerary")
public class PricedItinerary2 {

    @XmlElement(name = "AirItinerary")
    protected AirItinerary2 airItinerary;
    @XmlElement(name = "AirItineraryPricingInfo")
    protected AirItineraryPricingInfo2 airItineraryPricingInfo;

    /**
     * 获取airItinerary属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AirItinerary2 }
     *     
     */
    public AirItinerary2 getAirItinerary() {
        return airItinerary;
    }

    /**
     * 设置airItinerary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AirItinerary2 }
     *     
     */
    public void setAirItinerary(AirItinerary2 value) {
        this.airItinerary = value;
    }

    /**
     * 获取airItineraryPricingInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AirItineraryPricingInfo2 }
     *     
     */
    public AirItineraryPricingInfo2 getAirItineraryPricingInfo() {
        return airItineraryPricingInfo;
    }

    /**
     * 设置airItineraryPricingInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AirItineraryPricingInfo2 }
     *     
     */
    public void setAirItineraryPricingInfo(AirItineraryPricingInfo2 value) {
        this.airItineraryPricingInfo = value;
    }

}
