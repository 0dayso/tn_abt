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
 *         &lt;element ref="{http://espeed.travelsky.com}CombineRuleDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FareCombinationFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CombineRoundTripFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CombineOpenJawFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "combineRuleDetail"
})
@XmlRootElement(name = "CombineRule")
public class CombineRule {

    @XmlElement(name = "CombineRuleDetail")
    protected List<CombineRuleDetail> combineRuleDetail;
    @XmlAttribute(name = "FareCombinationFlag")
    protected String fareCombinationFlag;
    @XmlAttribute(name = "CombineRoundTripFlag")
    protected String combineRoundTripFlag;
    @XmlAttribute(name = "CombineOpenJawFlag")
    protected String combineOpenJawFlag;

    /**
     * Gets the value of the combineRuleDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the combineRuleDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCombineRuleDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CombineRuleDetail }
     * 
     * 
     */
    public List<CombineRuleDetail> getCombineRuleDetail() {
        if (combineRuleDetail == null) {
            combineRuleDetail = new ArrayList<CombineRuleDetail>();
        }
        return this.combineRuleDetail;
    }

    /**
     * 获取fareCombinationFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareCombinationFlag() {
        return fareCombinationFlag;
    }

    /**
     * 设置fareCombinationFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareCombinationFlag(String value) {
        this.fareCombinationFlag = value;
    }

    /**
     * 获取combineRoundTripFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombineRoundTripFlag() {
        return combineRoundTripFlag;
    }

    /**
     * 设置combineRoundTripFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombineRoundTripFlag(String value) {
        this.combineRoundTripFlag = value;
    }

    /**
     * 获取combineOpenJawFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombineOpenJawFlag() {
        return combineOpenJawFlag;
    }

    /**
     * 设置combineOpenJawFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombineOpenJawFlag(String value) {
        this.combineOpenJawFlag = value;
    }

}
