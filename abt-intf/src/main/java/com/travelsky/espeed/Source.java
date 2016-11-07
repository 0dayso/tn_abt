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
 *         &lt;element ref="{http://espeed.travelsky.com}RequestorID" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}BookingChannel" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ISOCurrency" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ISOCountry" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CityCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PseudoCityCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TerminalID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ERSP_UserID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OtherID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestorID",
    "bookingChannel"
})
@XmlRootElement(name = "Source")
public class Source {

    @XmlElement(name = "RequestorID")
    protected RequestorID requestorID;
    @XmlElement(name = "BookingChannel")
    protected BookingChannel bookingChannel;
    @XmlAttribute(name = "ISOCurrency")
    protected String isoCurrency;
    @XmlAttribute(name = "ISOCountry")
    protected String isoCountry;
    @XmlAttribute(name = "CityCode")
    protected String cityCode;
    @XmlAttribute(name = "PseudoCityCode")
    protected String pseudoCityCode;
    @XmlAttribute(name = "TerminalID")
    protected String terminalID;
    @XmlAttribute(name = "ERSP_UserID")
    protected String erspUserID;
    @XmlAttribute(name = "OtherID")
    protected String otherID;

    /**
     * 获取requestorID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RequestorID }
     *     
     */
    public RequestorID getRequestorID() {
        return requestorID;
    }

    /**
     * 设置requestorID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RequestorID }
     *     
     */
    public void setRequestorID(RequestorID value) {
        this.requestorID = value;
    }

    /**
     * 获取bookingChannel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BookingChannel }
     *     
     */
    public BookingChannel getBookingChannel() {
        return bookingChannel;
    }

    /**
     * 设置bookingChannel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BookingChannel }
     *     
     */
    public void setBookingChannel(BookingChannel value) {
        this.bookingChannel = value;
    }

    /**
     * 获取isoCurrency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISOCurrency() {
        return isoCurrency;
    }

    /**
     * 设置isoCurrency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISOCurrency(String value) {
        this.isoCurrency = value;
    }

    /**
     * 获取isoCountry属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISOCountry() {
        return isoCountry;
    }

    /**
     * 设置isoCountry属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISOCountry(String value) {
        this.isoCountry = value;
    }

    /**
     * 获取cityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置cityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityCode(String value) {
        this.cityCode = value;
    }

    /**
     * 获取pseudoCityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPseudoCityCode() {
        return pseudoCityCode;
    }

    /**
     * 设置pseudoCityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPseudoCityCode(String value) {
        this.pseudoCityCode = value;
    }

    /**
     * 获取terminalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalID() {
        return terminalID;
    }

    /**
     * 设置terminalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalID(String value) {
        this.terminalID = value;
    }

    /**
     * 获取erspUserID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERSPUserID() {
        return erspUserID;
    }

    /**
     * 设置erspUserID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERSPUserID(String value) {
        this.erspUserID = value;
    }

    /**
     * 获取otherID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherID() {
        return otherID;
    }

    /**
     * 设置otherID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherID(String value) {
        this.otherID = value;
    }

}
