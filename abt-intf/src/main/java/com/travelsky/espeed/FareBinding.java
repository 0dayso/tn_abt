//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

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
 *         &lt;element name="FareSysType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FareRPH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FBRRuleRPH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FBRDetailRPH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "fareSysType",
    "fareRPH",
    "fbrRuleRPH",
    "fbrDetailRPH"
})
@XmlRootElement(name = "FareBinding")
public class FareBinding {

    @XmlElement(name = "FareSysType", required = true)
    protected String fareSysType;
    @XmlElement(name = "FareRPH", required = true)
    protected String fareRPH;
    @XmlElement(name = "FBRRuleRPH")
    protected String fbrRuleRPH;
    @XmlElement(name = "FBRDetailRPH")
    protected String fbrDetailRPH;

    /**
     * 获取fareSysType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareSysType() {
        return fareSysType;
    }

    /**
     * 设置fareSysType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareSysType(String value) {
        this.fareSysType = value;
    }

    /**
     * 获取fareRPH属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareRPH() {
        return fareRPH;
    }

    /**
     * 设置fareRPH属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareRPH(String value) {
        this.fareRPH = value;
    }

    /**
     * 获取fbrRuleRPH属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFBRRuleRPH() {
        return fbrRuleRPH;
    }

    /**
     * 设置fbrRuleRPH属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFBRRuleRPH(String value) {
        this.fbrRuleRPH = value;
    }

    /**
     * 获取fbrDetailRPH属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFBRDetailRPH() {
        return fbrDetailRPH;
    }

    /**
     * 设置fbrDetailRPH属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFBRDetailRPH(String value) {
        this.fbrDetailRPH = value;
    }

}
