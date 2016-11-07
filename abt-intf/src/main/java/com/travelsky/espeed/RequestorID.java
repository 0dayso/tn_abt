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
 *         &lt;element ref="{http://espeed.travelsky.com}CompanyName" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ID_Context" type="{http://espeed.travelsky.com}IDContextType" />
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Instance" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="URL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "companyName"
})
@XmlRootElement(name = "RequestorID")
public class RequestorID {

    @XmlElement(name = "CompanyName")
    protected CompanyName companyName;
    @XmlAttribute(name = "Type")
    protected String type;
    @XmlAttribute(name = "ID_Context")
    protected IDContextType idContext;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "Instance")
    protected String instance;
    @XmlAttribute(name = "URL")
    protected String url;

    /**
     * 获取companyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CompanyName }
     *     
     */
    public CompanyName getCompanyName() {
        return companyName;
    }

    /**
     * 设置companyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CompanyName }
     *     
     */
    public void setCompanyName(CompanyName value) {
        this.companyName = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * 获取idContext属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IDContextType }
     *     
     */
    public IDContextType getIDContext() {
        return idContext;
    }

    /**
     * 设置idContext属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IDContextType }
     *     
     */
    public void setIDContext(IDContextType value) {
        this.idContext = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * 获取instance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstance() {
        return instance;
    }

    /**
     * 设置instance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstance(String value) {
        this.instance = value;
    }

    /**
     * 获取url属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURL() {
        return url;
    }

    /**
     * 设置url属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURL(String value) {
        this.url = value;
    }

}
