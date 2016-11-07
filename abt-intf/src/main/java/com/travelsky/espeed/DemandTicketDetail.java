//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://espeed.travelsky.com}BookingReferenceID" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PaymentInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Endorsement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PassengerName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FlightReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PTC_FareBreakdowns" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ReconfirmSegmentInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ReturnTicketInfoInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="LimitSegmentStatusInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="RollbackInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="EtryInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bookingReferenceID",
    "paymentInfo",
    "endorsement",
    "passengerName",
    "flightReference",
    "ptcFareBreakdowns"
})
@XmlRootElement(name = "DemandTicketDetail")
public class DemandTicketDetail {

    @XmlElement(name = "BookingReferenceID", required = true)
    protected List<BookingReferenceID> bookingReferenceID;
    @XmlElement(name = "PaymentInfo")
    protected List<PaymentInfo> paymentInfo;
    @XmlElement(name = "Endorsement")
    protected List<Endorsement> endorsement;
    @XmlElement(name = "PassengerName")
    protected List<PassengerName> passengerName;
    @XmlElement(name = "FlightReference")
    protected List<FlightReference> flightReference;
    @XmlElement(name = "PTC_FareBreakdowns")
    protected PTCFareBreakdowns ptcFareBreakdowns;
    @XmlAttribute(name = "ReconfirmSegmentInd")
    protected Boolean reconfirmSegmentInd;
    @XmlAttribute(name = "ReturnTicketInfoInd")
    protected Boolean returnTicketInfoInd;
    @XmlAttribute(name = "LimitSegmentStatusInd")
    protected Boolean limitSegmentStatusInd;
    @XmlAttribute(name = "RollbackInd")
    protected Boolean rollbackInd;
    @XmlAttribute(name = "EtryInd")
    protected Boolean etryInd;

    /**
     * Gets the value of the bookingReferenceID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingReferenceID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingReferenceID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BookingReferenceID }
     * 
     * 
     */
    public List<BookingReferenceID> getBookingReferenceID() {
        if (bookingReferenceID == null) {
            bookingReferenceID = new ArrayList<BookingReferenceID>();
        }
        return this.bookingReferenceID;
    }

    /**
     * Gets the value of the paymentInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentInfo }
     * 
     * 
     */
    public List<PaymentInfo> getPaymentInfo() {
        if (paymentInfo == null) {
            paymentInfo = new ArrayList<PaymentInfo>();
        }
        return this.paymentInfo;
    }

    /**
     * Gets the value of the endorsement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endorsement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndorsement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Endorsement }
     * 
     * 
     */
    public List<Endorsement> getEndorsement() {
        if (endorsement == null) {
            endorsement = new ArrayList<Endorsement>();
        }
        return this.endorsement;
    }

    /**
     * Gets the value of the passengerName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the passengerName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPassengerName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PassengerName }
     * 
     * 
     */
    public List<PassengerName> getPassengerName() {
        if (passengerName == null) {
            passengerName = new ArrayList<PassengerName>();
        }
        return this.passengerName;
    }

    /**
     * Gets the value of the flightReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightReference }
     * 
     * 
     */
    public List<FlightReference> getFlightReference() {
        if (flightReference == null) {
            flightReference = new ArrayList<FlightReference>();
        }
        return this.flightReference;
    }

    /**
     * 获取ptcFareBreakdowns属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PTCFareBreakdowns }
     *     
     */
    public PTCFareBreakdowns getPTCFareBreakdowns() {
        return ptcFareBreakdowns;
    }

    /**
     * 设置ptcFareBreakdowns属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PTCFareBreakdowns }
     *     
     */
    public void setPTCFareBreakdowns(PTCFareBreakdowns value) {
        this.ptcFareBreakdowns = value;
    }

    /**
     * 获取reconfirmSegmentInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReconfirmSegmentInd() {
        return reconfirmSegmentInd;
    }

    /**
     * 设置reconfirmSegmentInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReconfirmSegmentInd(Boolean value) {
        this.reconfirmSegmentInd = value;
    }

    /**
     * 获取returnTicketInfoInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnTicketInfoInd() {
        return returnTicketInfoInd;
    }

    /**
     * 设置returnTicketInfoInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnTicketInfoInd(Boolean value) {
        this.returnTicketInfoInd = value;
    }

    /**
     * 获取limitSegmentStatusInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLimitSegmentStatusInd() {
        return limitSegmentStatusInd;
    }

    /**
     * 设置limitSegmentStatusInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLimitSegmentStatusInd(Boolean value) {
        this.limitSegmentStatusInd = value;
    }

    /**
     * 获取rollbackInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRollbackInd() {
        return rollbackInd;
    }

    /**
     * 设置rollbackInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRollbackInd(Boolean value) {
        this.rollbackInd = value;
    }

    /**
     * 获取etryInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEtryInd() {
        return etryInd;
    }

    /**
     * 设置etryInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEtryInd(Boolean value) {
        this.etryInd = value;
    }

}
