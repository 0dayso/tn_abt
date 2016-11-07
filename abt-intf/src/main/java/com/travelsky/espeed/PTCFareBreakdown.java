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
 *         &lt;element ref="{http://espeed.travelsky.com}PassengerTypeQuantity"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FareBasisCodes" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}EndorsementRef" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PaymentInfoRef" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PassengerFare" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerRefNumber"/>
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
    "passengerTypeQuantity",
    "fareBasisCodes",
    "endorsementRef",
    "paymentInfoRef",
    "passengerFare",
    "travelerRefNumber"
})
@XmlRootElement(name = "PTC_FareBreakdown")
public class PTCFareBreakdown {

    @XmlElement(name = "PassengerTypeQuantity", required = true)
    protected PassengerTypeQuantity passengerTypeQuantity;
    @XmlElement(name = "FareBasisCodes")
    protected FareBasisCodes fareBasisCodes;
    @XmlElement(name = "EndorsementRef")
    protected EndorsementRef endorsementRef;
    @XmlElement(name = "PaymentInfoRef")
    protected PaymentInfoRef paymentInfoRef;
    @XmlElement(name = "PassengerFare", required = true)
    protected List<PassengerFare> passengerFare;
    @XmlElement(name = "TravelerRefNumber", required = true)
    protected TravelerRefNumber travelerRefNumber;

    /**
     * 获取passengerTypeQuantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PassengerTypeQuantity }
     *     
     */
    public PassengerTypeQuantity getPassengerTypeQuantity() {
        return passengerTypeQuantity;
    }

    /**
     * 设置passengerTypeQuantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PassengerTypeQuantity }
     *     
     */
    public void setPassengerTypeQuantity(PassengerTypeQuantity value) {
        this.passengerTypeQuantity = value;
    }

    /**
     * 获取fareBasisCodes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareBasisCodes }
     *     
     */
    public FareBasisCodes getFareBasisCodes() {
        return fareBasisCodes;
    }

    /**
     * 设置fareBasisCodes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareBasisCodes }
     *     
     */
    public void setFareBasisCodes(FareBasisCodes value) {
        this.fareBasisCodes = value;
    }

    /**
     * 获取endorsementRef属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EndorsementRef }
     *     
     */
    public EndorsementRef getEndorsementRef() {
        return endorsementRef;
    }

    /**
     * 设置endorsementRef属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EndorsementRef }
     *     
     */
    public void setEndorsementRef(EndorsementRef value) {
        this.endorsementRef = value;
    }

    /**
     * 获取paymentInfoRef属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaymentInfoRef }
     *     
     */
    public PaymentInfoRef getPaymentInfoRef() {
        return paymentInfoRef;
    }

    /**
     * 设置paymentInfoRef属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentInfoRef }
     *     
     */
    public void setPaymentInfoRef(PaymentInfoRef value) {
        this.paymentInfoRef = value;
    }

    /**
     * Gets the value of the passengerFare property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the passengerFare property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPassengerFare().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PassengerFare }
     * 
     * 
     */
    public List<PassengerFare> getPassengerFare() {
        if (passengerFare == null) {
            passengerFare = new ArrayList<PassengerFare>();
        }
        return this.passengerFare;
    }

    /**
     * 获取travelerRefNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TravelerRefNumber }
     *     
     */
    public TravelerRefNumber getTravelerRefNumber() {
        return travelerRefNumber;
    }

    /**
     * 设置travelerRefNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TravelerRefNumber }
     *     
     */
    public void setTravelerRefNumber(TravelerRefNumber value) {
        this.travelerRefNumber = value;
    }

}
