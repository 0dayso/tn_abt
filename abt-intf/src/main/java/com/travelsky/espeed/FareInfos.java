//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{http://espeed.travelsky.com}FareInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}RuleInfo" minOccurs="0"/>
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
    "fareInfo",
    "ruleInfo"
})
@XmlRootElement(name = "FareInfos")
public class FareInfos {

    @XmlElement(name = "FareInfo")
    protected List<FareInfo> fareInfo;
    @XmlElement(name = "RuleInfo")
    protected RuleInfo ruleInfo;

    /**
     * Gets the value of the fareInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fareInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFareInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FareInfo }
     * 
     * 
     */
    public List<FareInfo> getFareInfo() {
        if (fareInfo == null) {
            fareInfo = new ArrayList<FareInfo>();
        }
        return this.fareInfo;
    }

    /**
     * 获取ruleInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RuleInfo }
     *     
     */
    public RuleInfo getRuleInfo() {
        return ruleInfo;
    }

    /**
     * 设置ruleInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RuleInfo }
     *     
     */
    public void setRuleInfo(RuleInfo value) {
        this.ruleInfo = value;
    }

}
