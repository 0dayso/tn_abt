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
 *         &lt;element ref="{http://espeed.travelsky.com}NetFares" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PublicFares" minOccurs="0"/>
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
    "netFares",
    "publicFares"
})
@XmlRootElement(name = "FareOption")
public class FareOption {

    @XmlElement(name = "NetFares")
    protected NetFares netFares;
    @XmlElement(name = "PublicFares")
    protected PublicFares publicFares;

    /**
     * 获取netFares属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NetFares }
     *     
     */
    public NetFares getNetFares() {
        return netFares;
    }

    /**
     * 设置netFares属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NetFares }
     *     
     */
    public void setNetFares(NetFares value) {
        this.netFares = value;
    }

    /**
     * 获取publicFares属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PublicFares }
     *     
     */
    public PublicFares getPublicFares() {
        return publicFares;
    }

    /**
     * 设置publicFares属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PublicFares }
     *     
     */
    public void setPublicFares(PublicFares value) {
        this.publicFares = value;
    }

}
