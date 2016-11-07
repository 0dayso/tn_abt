//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 04:21:17 PM CST 
//


package com.travelsky.espeed;

import java.math.BigInteger;
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
 *         &lt;element ref="{http://espeed.travelsky.com}BookingReferenceID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RefundForm" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://espeed.travelsky.com}RefundFormInfo" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Number" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "bookingReferenceID",
    "refundForm"
})
@XmlRootElement(name = "RefundFormDetail")
public class RefundFormDetail {

    @XmlElement(name = "BookingReferenceID")
    protected List<BookingReferenceID> bookingReferenceID;
    @XmlElement(name = "RefundForm")
    protected RefundFormDetail.RefundForm refundForm;

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
     * 获取refundForm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RefundFormDetail.RefundForm }
     *     
     */
    public RefundFormDetail.RefundForm getRefundForm() {
        return refundForm;
    }

    /**
     * 设置refundForm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RefundFormDetail.RefundForm }
     *     
     */
    public void setRefundForm(RefundFormDetail.RefundForm value) {
        this.refundForm = value;
    }


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
     *         &lt;element ref="{http://espeed.travelsky.com}RefundFormInfo" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Number" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "refundFormInfo"
    })
    public static class RefundForm {

        @XmlElement(name = "RefundFormInfo")
        protected RefundFormInfo refundFormInfo;
        @XmlAttribute(name = "Number")
        protected BigInteger number;

        /**
         * 获取refundFormInfo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link RefundFormInfo }
         *     
         */
        public RefundFormInfo getRefundFormInfo() {
            return refundFormInfo;
        }

        /**
         * 设置refundFormInfo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link RefundFormInfo }
         *     
         */
        public void setRefundFormInfo(RefundFormInfo value) {
            this.refundFormInfo = value;
        }

        /**
         * 获取number属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getNumber() {
            return number;
        }

        /**
         * 设置number属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setNumber(BigInteger value) {
            this.number = value;
        }

    }

}
