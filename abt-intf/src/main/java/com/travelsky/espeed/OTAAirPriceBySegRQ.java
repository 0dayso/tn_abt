//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:30:57 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


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
 *         &lt;element ref="{http://espeed.travelsky.com}POS" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}AirItinerary2" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerInfoSummary2" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}BookingReferenceID2" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TransactionIdentifier" type="{http://espeed.travelsky.com}TransactionType" />
 *       &lt;attribute name="FareRefRPH" type="{http://www.w3.org/2001/XMLSchema}decimal" />
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
    "airItinerary",
    "travelerInfoSummary",
    "bookingReferenceID"
})
@XmlRootElement(name = "OTA_AirPriceRQ")
public class OTAAirPriceBySegRQ {

    @XmlElement(name = "POS")
    protected POS pos;
    @XmlElement(name = "AirItinerary")
    protected AirItinerary2 airItinerary;
    @XmlElement(name = "TravelerInfoSummary")
    protected TravelerInfoSummary2 travelerInfoSummary;
    @XmlElement(name = "BookingReferenceID")
    protected BookingReferenceID2 bookingReferenceID;
    @XmlAttribute(name = "EchoToken")
    protected String echoToken;
    @XmlAttribute(name = "TimeStamp")
    protected String timeStamp;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "Target")
    protected String target;
    @XmlAttribute(name = "TransactionIdentifier")
    protected TransactionType transactionIdentifier;
    @XmlAttribute(name = "FareRefRPH")
    protected BigDecimal fareRefRPH;

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
     * 获取travelerInfoSummary属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TravelerInfoSummary2 }
     *     
     */
    public TravelerInfoSummary2 getTravelerInfoSummary() {
        return travelerInfoSummary;
    }

    /**
     * 设置travelerInfoSummary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TravelerInfoSummary2 }
     *     
     */
    public void setTravelerInfoSummary(TravelerInfoSummary2 value) {
        this.travelerInfoSummary = value;
    }

    /**
     * 获取bookingReferenceID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BookingReferenceID2 }
     *     
     */
    public BookingReferenceID2 getBookingReferenceID() {
        return bookingReferenceID;
    }

    /**
     * 设置bookingReferenceID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BookingReferenceID2 }
     *     
     */
    public void setBookingReferenceID(BookingReferenceID2 value) {
        this.bookingReferenceID = value;
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

    /**
     * 获取transactionIdentifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TransactionType }
     *     
     */
    public TransactionType getTransactionIdentifier() {
        return transactionIdentifier;
    }

    /**
     * 设置transactionIdentifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionType }
     *     
     */
    public void setTransactionIdentifier(TransactionType value) {
        this.transactionIdentifier = value;
    }

    /**
     * 获取fareRefRPH属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFareRefRPH() {
        return fareRefRPH;
    }

    /**
     * 设置fareRefRPH属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFareRefRPH(BigDecimal value) {
        this.fareRefRPH = value;
    }

}