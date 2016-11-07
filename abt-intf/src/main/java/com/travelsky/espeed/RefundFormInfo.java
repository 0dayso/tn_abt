//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 04:21:17 PM CST 
//


package com.travelsky.espeed;

import java.math.BigDecimal;
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
 *         &lt;element ref="{http://espeed.travelsky.com}Remark" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CouponNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Taxes" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PrinterInfo"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PaymentInfo"/>
 *         &lt;element ref="{http://espeed.travelsky.com}CommissionInfo" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PassengerName" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TicketItemInfo"/>
 *       &lt;/sequence>
 *       &lt;attribute name="GrossRefund" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="NetRefund" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="Deduction" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="Check" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="GroupInd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "remark",
    "couponNumber",
    "taxes",
    "printerInfo",
    "paymentInfo",
    "commissionInfo",
    "passengerName",
    "ticketItemInfo"
})
@XmlRootElement(name = "RefundFormInfo")
public class RefundFormInfo {

    @XmlElement(name = "Remark")
    protected List<Remark> remark;
    @XmlElement(name = "CouponNumber")
    protected List<String> couponNumber;
    @XmlElement(name = "Taxes")
    protected Taxes taxes;
    @XmlElement(name = "PrinterInfo", required = true)
    protected PrinterInfo printerInfo;
    @XmlElement(name = "PaymentInfo", required = true)
    protected PaymentInfo paymentInfo;
    @XmlElement(name = "CommissionInfo")
    protected CommissionInfo commissionInfo;
    @XmlElement(name = "PassengerName")
    protected PassengerName passengerName;
    @XmlElement(name = "TicketItemInfo", required = true)
    protected TicketItemInfo ticketItemInfo;
    @XmlAttribute(name = "GrossRefund", required = true)
    protected BigDecimal grossRefund;
    @XmlAttribute(name = "NetRefund", required = true)
    protected BigDecimal netRefund;
    @XmlAttribute(name = "Deduction", required = true)
    protected BigDecimal deduction;
    @XmlAttribute(name = "Check")
    protected String check;
    @XmlAttribute(name = "GroupInd")
    protected String groupInd;

    /**
     * Gets the value of the remark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Remark }
     * 
     * 
     */
    public List<Remark> getRemark() {
        if (remark == null) {
            remark = new ArrayList<Remark>();
        }
        return this.remark;
    }

    /**
     * Gets the value of the couponNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the couponNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCouponNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCouponNumber() {
        if (couponNumber == null) {
            couponNumber = new ArrayList<String>();
        }
        return this.couponNumber;
    }

    /**
     * 获取taxes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Taxes }
     *     
     */
    public Taxes getTaxes() {
        return taxes;
    }

    /**
     * 设置taxes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Taxes }
     *     
     */
    public void setTaxes(Taxes value) {
        this.taxes = value;
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
     * 获取paymentInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaymentInfo }
     *     
     */
    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * 设置paymentInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentInfo }
     *     
     */
    public void setPaymentInfo(PaymentInfo value) {
        this.paymentInfo = value;
    }

    /**
     * 获取commissionInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CommissionInfo }
     *     
     */
    public CommissionInfo getCommissionInfo() {
        return commissionInfo;
    }

    /**
     * 设置commissionInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CommissionInfo }
     *     
     */
    public void setCommissionInfo(CommissionInfo value) {
        this.commissionInfo = value;
    }

    /**
     * 获取passengerName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PassengerName }
     *     
     */
    public PassengerName getPassengerName() {
        return passengerName;
    }

    /**
     * 设置passengerName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PassengerName }
     *     
     */
    public void setPassengerName(PassengerName value) {
        this.passengerName = value;
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
     * 获取grossRefund属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGrossRefund() {
        return grossRefund;
    }

    /**
     * 设置grossRefund属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGrossRefund(BigDecimal value) {
        this.grossRefund = value;
    }

    /**
     * 获取netRefund属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetRefund() {
        return netRefund;
    }

    /**
     * 设置netRefund属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetRefund(BigDecimal value) {
        this.netRefund = value;
    }

    /**
     * 获取deduction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeduction() {
        return deduction;
    }

    /**
     * 设置deduction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeduction(BigDecimal value) {
        this.deduction = value;
    }

    /**
     * 获取check属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheck() {
        return check;
    }

    /**
     * 设置check属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheck(String value) {
        this.check = value;
    }

    /**
     * 获取groupInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupInd() {
        return groupInd;
    }

    /**
     * 设置groupInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupInd(String value) {
        this.groupInd = value;
    }

}
