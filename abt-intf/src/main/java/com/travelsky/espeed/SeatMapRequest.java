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
 *         &lt;element ref="{http://espeed.travelsky.com}FlightSegmentInfo"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TravelerRefNumberRPHs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "flightSegmentInfo"
})
@XmlRootElement(name = "SeatMapRequest")
public class SeatMapRequest {

    @XmlElement(name = "FlightSegmentInfo", required = true)
    protected FlightSegmentInfo flightSegmentInfo;
    @XmlAttribute(name = "TravelerRefNumberRPHs")
    protected String travelerRefNumberRPHs;

    /**
     * 获取flightSegmentInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FlightSegmentInfo }
     *     
     */
    public FlightSegmentInfo getFlightSegmentInfo() {
        return flightSegmentInfo;
    }

    /**
     * 设置flightSegmentInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FlightSegmentInfo }
     *     
     */
    public void setFlightSegmentInfo(FlightSegmentInfo value) {
        this.flightSegmentInfo = value;
    }

    /**
     * 获取travelerRefNumberRPHs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelerRefNumberRPHs() {
        return travelerRefNumberRPHs;
    }

    /**
     * 设置travelerRefNumberRPHs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelerRefNumberRPHs(String value) {
        this.travelerRefNumberRPHs = value;
    }

}
