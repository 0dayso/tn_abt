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
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{http://espeed.travelsky.com}CountryName" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}StateProv" minOccurs="0"/>
 *         &lt;element name="County" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AddressLine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrafficInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" type="{http://espeed.travelsky.com}AddressType" />
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "countryName",
    "stateProv",
    "county",
    "cityName",
    "addressLine",
    "postalCode",
    "trafficInfo"
})
@XmlRootElement(name = "Address")
public class Address {

    @XmlElement(name = "CountryName")
    protected CountryName countryName;
    @XmlElement(name = "StateProv")
    protected StateProv stateProv;
    @XmlElement(name = "County")
    protected String county;
    @XmlElement(name = "CityName")
    protected String cityName;
    @XmlElement(name = "AddressLine")
    protected String addressLine;
    @XmlElement(name = "PostalCode")
    protected String postalCode;
    @XmlElement(name = "TrafficInfo")
    protected String trafficInfo;
    @XmlAttribute(name = "Type")
    protected AddressType type;
    @XmlAttribute(name = "RPH")
    protected String rph;

    /**
     * 获取countryName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CountryName }
     *     
     */
    public CountryName getCountryName() {
        return countryName;
    }

    /**
     * 设置countryName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CountryName }
     *     
     */
    public void setCountryName(CountryName value) {
        this.countryName = value;
    }

    /**
     * 获取stateProv属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StateProv }
     *     
     */
    public StateProv getStateProv() {
        return stateProv;
    }

    /**
     * 设置stateProv属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StateProv }
     *     
     */
    public void setStateProv(StateProv value) {
        this.stateProv = value;
    }

    /**
     * 获取county属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounty() {
        return county;
    }

    /**
     * 设置county属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounty(String value) {
        this.county = value;
    }

    /**
     * 获取cityName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置cityName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * 获取addressLine属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     * 设置addressLine属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine(String value) {
        this.addressLine = value;
    }

    /**
     * 获取postalCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 设置postalCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * 获取trafficInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrafficInfo() {
        return trafficInfo;
    }

    /**
     * 设置trafficInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrafficInfo(String value) {
        this.trafficInfo = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setType(AddressType value) {
        this.type = value;
    }

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

}
