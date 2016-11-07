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
 *         &lt;element name="SectorNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DateA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DateB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sectorNumber",
    "dateA",
    "dateB"
})
@XmlRootElement(name = "SectorInfo")
public class SectorInfo {

    @XmlElement(name = "SectorNumber", required = true)
    protected String sectorNumber;
    @XmlElement(name = "DateA")
    protected String dateA;
    @XmlElement(name = "DateB")
    protected String dateB;

    /**
     * 获取sectorNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSectorNumber() {
        return sectorNumber;
    }

    /**
     * 设置sectorNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSectorNumber(String value) {
        this.sectorNumber = value;
    }

    /**
     * 获取dateA属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateA() {
        return dateA;
    }

    /**
     * 设置dateA属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateA(String value) {
        this.dateA = value;
    }

    /**
     * 获取dateB属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateB() {
        return dateB;
    }

    /**
     * 设置dateB属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateB(String value) {
        this.dateB = value;
    }

}
