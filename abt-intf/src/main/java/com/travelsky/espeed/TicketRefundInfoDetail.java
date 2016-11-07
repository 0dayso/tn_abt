//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 04:21:17 PM CST 
//


package com.travelsky.espeed;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{http://espeed.travelsky.com}TicketItemInfo"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PrinterInfo"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ReturnRefundFormInfoInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ConfirmRefundInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CancelSeatInd" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
    "ticketItemInfo",
    "printerInfo"
})
@XmlRootElement(name = "TicketRefundInfoDetail")
public class TicketRefundInfoDetail {

    @XmlElement(name = "BookingReferenceID")
    protected List<BookingReferenceID> bookingReferenceID;
    @XmlElement(name = "TicketItemInfo", required = true)
    protected TicketItemInfo ticketItemInfo;
    @XmlElement(name = "PrinterInfo", required = true)
    protected PrinterInfo printerInfo;
    @XmlAttribute(name = "ReturnRefundFormInfoInd")
    protected Boolean returnRefundFormInfoInd;
    @XmlAttribute(name = "ConfirmRefundInd")
    protected Boolean confirmRefundInd;
    @XmlAttribute(name = "CancelSeatInd")
    @XmlSchemaType(name = "anySimpleType")
    protected String cancelSeatInd;

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
     * 获取ticketItemInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TicketItemInfo }
     *     
     */
    public TicketItemInfo getTicketItemInfo() {
        return ticketItemInfo;
    }

    /**
     * 设置ticketItemInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TicketItemInfo }
     *     
     */
    public void setTicketItemInfo(TicketItemInfo value) {
        this.ticketItemInfo = value;
    }

    /**
     * 获取printerInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PrinterInfo }
     *     
     */
    public PrinterInfo getPrinterInfo() {
        return printerInfo;
    }

    /**
     * 设置printerInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PrinterInfo }
     *     
     */
    public void setPrinterInfo(PrinterInfo value) {
        this.printerInfo = value;
    }

    /**
     * 获取returnRefundFormInfoInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnRefundFormInfoInd() {
        return returnRefundFormInfoInd;
    }

    /**
     * 设置returnRefundFormInfoInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnRefundFormInfoInd(Boolean value) {
        this.returnRefundFormInfoInd = value;
    }

    /**
     * 获取confirmRefundInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConfirmRefundInd() {
        return confirmRefundInd;
    }

    /**
     * 设置confirmRefundInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConfirmRefundInd(Boolean value) {
        this.confirmRefundInd = value;
    }

    /**
     * 获取cancelSeatInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelSeatInd() {
        return cancelSeatInd;
    }

    /**
     * 设置cancelSeatInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelSeatInd(String value) {
        this.cancelSeatInd = value;
    }

}
