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
 *         &lt;element name="CombineFareBasisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CombineClassOfTravel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CombineCarrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CheckAllSectorsInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CombineRuleID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "combineFareBasisCode",
    "combineClassOfTravel",
    "combineCarrier",
    "checkAllSectorsInd"
})
@XmlRootElement(name = "CombineRuleDetail")
public class CombineRuleDetail {

    @XmlElement(name = "CombineFareBasisCode")
    protected String combineFareBasisCode;
    @XmlElement(name = "CombineClassOfTravel")
    protected String combineClassOfTravel;
    @XmlElement(name = "CombineCarrier")
    protected String combineCarrier;
    @XmlElement(name = "CheckAllSectorsInd")
    protected String checkAllSectorsInd;
    @XmlAttribute(name = "CombineRuleID")
    protected String combineRuleID;

    /**
     * 获取combineFareBasisCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombineFareBasisCode() {
        return combineFareBasisCode;
    }

    /**
     * 设置combineFareBasisCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombineFareBasisCode(String value) {
        this.combineFareBasisCode = value;
    }

    /**
     * 获取combineClassOfTravel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombineClassOfTravel() {
        return combineClassOfTravel;
    }

    /**
     * 设置combineClassOfTravel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombineClassOfTravel(String value) {
        this.combineClassOfTravel = value;
    }

    /**
     * 获取combineCarrier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombineCarrier() {
        return combineCarrier;
    }

    /**
     * 设置combineCarrier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombineCarrier(String value) {
        this.combineCarrier = value;
    }

    /**
     * 获取checkAllSectorsInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckAllSectorsInd() {
        return checkAllSectorsInd;
    }

    /**
     * 设置checkAllSectorsInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckAllSectorsInd(String value) {
        this.checkAllSectorsInd = value;
    }

    /**
     * 获取combineRuleID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombineRuleID() {
        return combineRuleID;
    }

    /**
     * 设置combineRuleID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombineRuleID(String value) {
        this.combineRuleID = value;
    }

}
