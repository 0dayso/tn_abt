//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:13:27 PM CST 
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
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element ref="{http://espeed.travelsky.com}Warnings" minOccurs="0"/>
 *           &lt;element ref="{http://espeed.travelsky.com}AirItineraryPricingInfo" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *         &lt;element ref="{http://espeed.travelsky.com}Errors"/>
 *       &lt;/choice>
 *       &lt;attribute name="EchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SequenceNmbr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TPM" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "warnings",
    "airItineraryPricingInfo",
    "errors"
})
@XmlRootElement(name = "OTA_AirFareDisplayRS")
public class OTAAirFareDisplayRS {

    @XmlElement(name = "Warnings")
    protected Warnings warnings;
    @XmlElement(name = "AirItineraryPricingInfo")
    protected List<AirItineraryPricingInfo> airItineraryPricingInfo;
    @XmlElement(name = "Errors")
    protected Errors errors;
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
    @XmlAttribute(name = "TPM")
    protected String tpm;

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
     * Gets the value of the airItineraryPricingInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the airItineraryPricingInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAirItineraryPricingInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AirItineraryPricingInfo }
     * 
     * 
     */
    public List<AirItineraryPricingInfo> getAirItineraryPricingInfo() {
        if (airItineraryPricingInfo == null) {
            airItineraryPricingInfo = new ArrayList<AirItineraryPricingInfo>();
        }
        return this.airItineraryPricingInfo;
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
     * 获取tpm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTPM() {
        return tpm;
    }

    /**
     * 设置tpm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTPM(String value) {
        this.tpm = value;
    }

}
