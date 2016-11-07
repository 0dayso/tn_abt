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
 *         &lt;element ref="{http://espeed.travelsky.com}SectorInfo" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FareBinding"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Amount" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CurrencyCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sectorInfo",
    "fareBinding"
})
@XmlRootElement(name = "FareComponent")
public class FareComponent {

    @XmlElement(name = "SectorInfo", required = true)
    protected List<SectorInfo> sectorInfo;
    @XmlElement(name = "FareBinding", required = true)
    protected FareBinding fareBinding;
    @XmlAttribute(name = "Amount", required = true)
    protected String amount;
    @XmlAttribute(name = "CurrencyCode", required = true)
    protected String currencyCode;

    /**
     * Gets the value of the sectorInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sectorInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSectorInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SectorInfo }
     * 
     * 
     */
    public List<SectorInfo> getSectorInfo() {
        if (sectorInfo == null) {
            sectorInfo = new ArrayList<SectorInfo>();
        }
        return this.sectorInfo;
    }

    /**
     * 获取fareBinding属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareBinding }
     *     
     */
    public FareBinding getFareBinding() {
        return fareBinding;
    }

    /**
     * 设置fareBinding属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareBinding }
     *     
     */
    public void setFareBinding(FareBinding value) {
        this.fareBinding = value;
    }

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

}
