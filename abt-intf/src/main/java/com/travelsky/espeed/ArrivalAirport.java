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
 *       &lt;attribute name="LocationCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CodeContext" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Terminal" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Gate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BaggageClaim" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Diversion" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ArrivalAirport")
public class ArrivalAirport {

    @XmlAttribute(name = "LocationCode")
    protected String locationCode;
    @XmlAttribute(name = "CodeContext")
    protected String codeContext;
    @XmlAttribute(name = "Terminal")
    protected String terminal;
    @XmlAttribute(name = "Gate")
    protected String gate;
    @XmlAttribute(name = "BaggageClaim")
    protected String baggageClaim;
    @XmlAttribute(name = "Diversion")
    protected Boolean diversion;

    /**
     * 获取locationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * 设置locationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationCode(String value) {
        this.locationCode = value;
    }

    /**
     * 获取codeContext属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeContext() {
        return codeContext;
    }

    /**
     * 设置codeContext属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeContext(String value) {
        this.codeContext = value;
    }

    /**
     * 获取terminal属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * 设置terminal属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminal(String value) {
        this.terminal = value;
    }

    /**
     * 获取gate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGate() {
        return gate;
    }

    /**
     * 设置gate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGate(String value) {
        this.gate = value;
    }

    /**
     * 获取baggageClaim属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaggageClaim() {
        return baggageClaim;
    }

    /**
     * 设置baggageClaim属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaggageClaim(String value) {
        this.baggageClaim = value;
    }

    /**
     * 获取diversion属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDiversion() {
        return diversion;
    }

    /**
     * 设置diversion属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDiversion(Boolean value) {
        this.diversion = value;
    }

}
