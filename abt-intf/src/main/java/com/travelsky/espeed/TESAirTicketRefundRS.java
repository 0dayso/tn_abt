//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 04:21:17 PM CST 
//


package com.travelsky.espeed;

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
 *         &lt;choice>
 *           &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element ref="{http://espeed.travelsky.com}Warnings"/>
 *           &lt;element ref="{http://espeed.travelsky.com}Errors"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://espeed.travelsky.com}RefundFormDetails"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SequenceNmbr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OperationNo" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "success",
    "warnings",
    "errors",
    "refundFormDetails"
})
@XmlRootElement(name = "TES_AirTicketRefundRS")
public class TESAirTicketRefundRS {

    @XmlElement(name = "Success")
    protected String success;
    @XmlElement(name = "Warnings")
    protected Warnings warnings;
    @XmlElement(name = "Errors")
    protected Errors errors;
    @XmlElement(name = "RefundFormDetails", required = true)
    protected RefundFormDetails refundFormDetails;
    @XmlAttribute(name = "EchoToken")
    protected String echoToken;
    @XmlAttribute(name = "TimeStamp")
    protected String timeStamp;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "Target")
    protected String target;
    @XmlAttribute(name = "SequenceNmbr")
    protected String sequenceNmbr;
    @XmlAttribute(name = "OperationNo")
    @XmlSchemaType(name = "anySimpleType")
    protected String operationNo;

    /**
     * 获取success属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccess() {
        return success;
    }

    /**
     * 设置success属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccess(String value) {
        this.success = value;
    }

    /**
     * 获取warnings属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Warnings }
     *     
     */
    public Warnings getWarnings() {
        return warnings;
    }

    /**
     * 设置warnings属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Warnings }
     *     
     */
    public void setWarnings(Warnings value) {
        this.warnings = value;
    }

    /**
     * 获取errors属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Errors }
     *     
     */
    public Errors getErrors() {
        return errors;
    }

    /**
     * 设置errors属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Errors }
     *     
     */
    public void setErrors(Errors value) {
        this.errors = value;
    }

    /**
     * 获取refundFormDetails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RefundFormDetails }
     *     
     */
    public RefundFormDetails getRefundFormDetails() {
        return refundFormDetails;
    }

    /**
     * 设置refundFormDetails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RefundFormDetails }
     *     
     */
    public void setRefundFormDetails(RefundFormDetails value) {
        this.refundFormDetails = value;
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
     * 获取sequenceNmbr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequenceNmbr() {
        return sequenceNmbr;
    }

    /**
     * 设置sequenceNmbr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequenceNmbr(String value) {
        this.sequenceNmbr = value;
    }

    /**
     * 获取operationNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationNo() {
        return operationNo;
    }

    /**
     * 设置operationNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationNo(String value) {
        this.operationNo = value;
    }

}
