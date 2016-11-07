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
 *         &lt;element ref="{http://espeed.travelsky.com}AirTravelerAvail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PaymentDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="GroupInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airTravelerAvail",
    "paymentDetail"
})
@XmlRootElement(name = "TravelerInfoSummary")
public class TravelerInfoSummary {

    @XmlElement(name = "AirTravelerAvail")
    protected List<AirTravelerAvail> airTravelerAvail;
    @XmlElement(name = "PaymentDetail")
    protected PaymentDetail paymentDetail;
    @XmlAttribute(name = "GroupInd")
    protected Boolean groupInd;

    /**
     * Gets the value of the airTravelerAvail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the airTravelerAvail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAirTravelerAvail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AirTravelerAvail }
     * 
     * 
     */
    public List<AirTravelerAvail> getAirTravelerAvail() {
        if (airTravelerAvail == null) {
            airTravelerAvail = new ArrayList<AirTravelerAvail>();
        }
        return this.airTravelerAvail;
    }

    /**
     * 获取paymentDetail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaymentDetail }
     *     
     */
    public PaymentDetail getPaymentDetail() {
        return paymentDetail;
    }

    /**
     * 设置paymentDetail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentDetail }
     *     
     */
    public void setPaymentDetail(PaymentDetail value) {
        this.paymentDetail = value;
    }

    /**
     * 获取groupInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGroupInd() {
        return groupInd;
    }

    /**
     * 设置groupInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGroupInd(Boolean value) {
        this.groupInd = value;
    }

}
