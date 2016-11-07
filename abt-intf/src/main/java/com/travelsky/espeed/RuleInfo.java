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
 *         &lt;element ref="{http://espeed.travelsky.com}Rules" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FareByRuleDetails" minOccurs="0"/>
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
    "rules",
    "fareByRuleDetails"
})
@XmlRootElement(name = "RuleInfo")
public class RuleInfo {

    @XmlElement(name = "Rules")
    protected Rules rules;
    @XmlElement(name = "FareByRuleDetails")
    protected FareByRuleDetails fareByRuleDetails;

    /**
     * 获取rules属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Rules }
     *     
     */
    public Rules getRules() {
        return rules;
    }

    /**
     * 设置rules属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Rules }
     *     
     */
    public void setRules(Rules value) {
        this.rules = value;
    }

    /**
     * 获取fareByRuleDetails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareByRuleDetails }
     *     
     */
    public FareByRuleDetails getFareByRuleDetails() {
        return fareByRuleDetails;
    }

    /**
     * 设置fareByRuleDetails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareByRuleDetails }
     *     
     */
    public void setFareByRuleDetails(FareByRuleDetails value) {
        this.fareByRuleDetails = value;
    }

}
