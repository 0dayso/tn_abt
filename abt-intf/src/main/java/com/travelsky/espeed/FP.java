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
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerRefNumber" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PayType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsInfant" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "travelerRefNumber"
})
@XmlRootElement(name = "FP")
public class FP {

    @XmlElement(name = "TravelerRefNumber")
    protected TravelerRefNumber travelerRefNumber;
    @XmlAttribute(name = "RPH")
    protected Integer rph;
    @XmlAttribute(name = "Remark")
    protected String remark;
    @XmlAttribute(name = "PayType")
    protected String payType;
    @XmlAttribute(name = "Currency")
    protected String currency;
    @XmlAttribute(name = "IsInfant")
    protected Boolean isInfant;

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

    /**
     * 获取rph属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRPH() {
        return rph;
    }

    /**
     * 设置rph属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRPH(Integer value) {
        this.rph = value;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * 获取payType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置payType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayType(String value) {
        this.payType = value;
    }

    /**
     * 获取currency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置currency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * 获取isInfant属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsInfant() {
        return isInfant;
    }

    /**
     * 设置isInfant属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInfant(Boolean value) {
        this.isInfant = value;
    }

}
