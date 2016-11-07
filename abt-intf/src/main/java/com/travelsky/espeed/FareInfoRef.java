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
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FareByRuleRPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FareByRuleDetailRPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "FareInfoRef")
public class FareInfoRef {

    @XmlAttribute(name = "RPH")
    protected String rph;
    @XmlAttribute(name = "FareByRuleRPH")
    protected String fareByRuleRPH;
    @XmlAttribute(name = "FareByRuleDetailRPH")
    protected String fareByRuleDetailRPH;

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
     * 获取fareByRuleRPH属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareByRuleRPH() {
        return fareByRuleRPH;
    }

    /**
     * 设置fareByRuleRPH属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareByRuleRPH(String value) {
        this.fareByRuleRPH = value;
    }

    /**
     * 获取fareByRuleDetailRPH属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareByRuleDetailRPH() {
        return fareByRuleDetailRPH;
    }

    /**
     * 设置fareByRuleDetailRPH属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareByRuleDetailRPH(String value) {
        this.fareByRuleDetailRPH = value;
    }

}
