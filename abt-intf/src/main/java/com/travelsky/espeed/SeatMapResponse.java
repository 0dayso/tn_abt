//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
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
 *         &lt;element ref="{http://espeed.travelsky.com}FlightSegmentInfo"/>
 *         &lt;element ref="{http://espeed.travelsky.com}SeatMapDetails"/>
 *         &lt;element ref="{http://espeed.travelsky.com}BookingReferenceID" minOccurs="0"/>
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
    "flightSegmentInfo",
    "seatMapDetails",
    "bookingReferenceID"
})
@XmlRootElement(name = "SeatMapResponse")
public class SeatMapResponse {

    @XmlElement(name = "FlightSegmentInfo", required = true)
    protected FlightSegmentInfo flightSegmentInfo;
    @XmlElement(name = "SeatMapDetails", required = true)
    protected SeatMapDetails seatMapDetails;
    @XmlElement(name = "BookingReferenceID")
    protected BookingReferenceID bookingReferenceID;

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
     * 获取seatMapDetails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SeatMapDetails }
     *     
     */
    public SeatMapDetails getSeatMapDetails() {
        return seatMapDetails;
    }

    /**
     * 设置seatMapDetails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SeatMapDetails }
     *     
     */
    public void setSeatMapDetails(SeatMapDetails value) {
        this.seatMapDetails = value;
    }

    /**
     * 获取bookingReferenceID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BookingReferenceID }
     *     
     */
    public BookingReferenceID getBookingReferenceID() {
        return bookingReferenceID;
    }

    /**
     * 设置bookingReferenceID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BookingReferenceID }
     *     
     */
    public void setBookingReferenceID(BookingReferenceID value) {
        this.bookingReferenceID = value;
    }

}
