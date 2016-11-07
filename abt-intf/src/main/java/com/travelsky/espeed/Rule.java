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
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="Cancellation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Rebook" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Extension" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Other" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Application" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Eligibility" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ticketing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Together" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Payment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}AdvTicketing" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}CombineRule" minOccurs="0"/>
 *         &lt;element name="FareCompute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RuleID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cancellation",
    "rebook",
    "extension",
    "other",
    "application",
    "eligibility",
    "ticketing",
    "together",
    "payment",
    "advTicketing",
    "combineRule",
    "fareCompute"
})
@XmlRootElement(name = "Rule")
public class Rule {

    @XmlElement(name = "Cancellation")
    protected String cancellation;
    @XmlElement(name = "Rebook")
    protected String rebook;
    @XmlElement(name = "Extension")
    protected String extension;
    @XmlElement(name = "Other")
    protected String other;
    @XmlElement(name = "Application")
    protected String application;
    @XmlElement(name = "Eligibility")
    protected String eligibility;
    @XmlElement(name = "Ticketing")
    protected String ticketing;
    @XmlElement(name = "Together")
    protected String together;
    @XmlElement(name = "Payment")
    protected String payment;
    @XmlElement(name = "AdvTicketing")
    protected AdvTicketing advTicketing;
    @XmlElement(name = "CombineRule")
    protected CombineRule combineRule;
    @XmlElement(name = "FareCompute")
    protected String fareCompute;
    @XmlAttribute(name = "RPH")
    protected String rph;
    @XmlAttribute(name = "RuleID")
    protected String ruleID;

    /**
     * 获取cancellation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancellation() {
        return cancellation;
    }

    /**
     * 设置cancellation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancellation(String value) {
        this.cancellation = value;
    }

    /**
     * 获取rebook属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRebook() {
        return rebook;
    }

    /**
     * 设置rebook属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRebook(String value) {
        this.rebook = value;
    }

    /**
     * 获取extension属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtension() {
        return extension;
    }

    /**
     * 设置extension属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtension(String value) {
        this.extension = value;
    }

    /**
     * 获取other属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置other属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOther(String value) {
        this.other = value;
    }

    /**
     * 获取application属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplication() {
        return application;
    }

    /**
     * 设置application属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplication(String value) {
        this.application = value;
    }

    /**
     * 获取eligibility属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEligibility() {
        return eligibility;
    }

    /**
     * 设置eligibility属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibility(String value) {
        this.eligibility = value;
    }

    /**
     * 获取ticketing属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketing() {
        return ticketing;
    }

    /**
     * 设置ticketing属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketing(String value) {
        this.ticketing = value;
    }

    /**
     * 获取together属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTogether() {
        return together;
    }

    /**
     * 设置together属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTogether(String value) {
        this.together = value;
    }

    /**
     * 获取payment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayment() {
        return payment;
    }

    /**
     * 设置payment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayment(String value) {
        this.payment = value;
    }

    /**
     * 获取advTicketing属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AdvTicketing }
     *     
     */
    public AdvTicketing getAdvTicketing() {
        return advTicketing;
    }

    /**
     * 设置advTicketing属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AdvTicketing }
     *     
     */
    public void setAdvTicketing(AdvTicketing value) {
        this.advTicketing = value;
    }

    /**
     * 获取combineRule属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CombineRule }
     *     
     */
    public CombineRule getCombineRule() {
        return combineRule;
    }

    /**
     * 设置combineRule属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CombineRule }
     *     
     */
    public void setCombineRule(CombineRule value) {
        this.combineRule = value;
    }

    /**
     * 获取fareCompute属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareCompute() {
        return fareCompute;
    }

    /**
     * 设置fareCompute属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareCompute(String value) {
        this.fareCompute = value;
    }

    /**
     * 获取rph属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRPH() {
        return rph;
    }

    /**
     * 设置rph属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRPH(String value) {
        this.rph = value;
    }

    /**
     * 获取ruleID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleID() {
        return ruleID;
    }

    /**
     * 设置ruleID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleID(String value) {
        this.ruleID = value;
    }

}
