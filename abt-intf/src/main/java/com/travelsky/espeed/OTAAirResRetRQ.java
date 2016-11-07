//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 04:03:21 PM CST 
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
 *         &lt;element ref="{http://espeed.travelsky.com}POS" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}BookingReferenceID"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RetCreateTimeInd" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="SearchCodeShareInd" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="SynTicketNumberInd" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pos",
    "bookingReferenceID"
})
@XmlRootElement(name = "OTA_AirResRetRQ")
public class OTAAirResRetRQ {

    @XmlElement(name = "POS")
    protected POS pos;
    @XmlElement(name = "BookingReferenceID", required = true)
    protected BookingReferenceID2 bookingReferenceID;
    @XmlAttribute(name = "EchoToken")
    protected String echoToken;
    @XmlAttribute(name = "TimeStamp")
    protected String timeStamp;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "Target")
    protected String target;
    @XmlAttribute(name = "RetCreateTimeInd")
    protected Boolean retCreateTimeInd;
    @XmlAttribute(name = "SearchCodeShareInd")
    protected Boolean searchCodeShareInd;
    @XmlAttribute(name = "SynTicketNumberInd")
    protected Boolean synTicketNumberInd;

    /**
     * 获取pos属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POS }
     *     
     */
    public POS getPOS() {
        return pos;
    }

    /**
     * 设置pos属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POS }
     *     
     */
    public void setPOS(POS value) {
        this.pos = value;
    }

    /**
     * 获取bookingReferenceID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BookingReferenceID2 }
     *     
     */
    public BookingReferenceID2 getBookingReferenceID() {
        return bookingReferenceID;
    }

    /**
     * 设置bookingReferenceID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BookingReferenceID2 }
     *     
     */
    public void setBookingReferenceID(BookingReferenceID2 value) {
        this.bookingReferenceID = value;
    }

    /**
     * 获取echoToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEchoToken() {
        return echoToken;
    }

    /**
     * 设置echoToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEchoToken(String value) {
        this.echoToken = value;
    }

    /**
     * 获取timeStamp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * 设置timeStamp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStamp(String value) {
        this.timeStamp = value;
    }

    /**
     * 获取version属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置version属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * 获取target属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置target属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * 获取retCreateTimeInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isRetCreateTimeInd() {
        if (retCreateTimeInd == null) {
            return false;
        } else {
            return retCreateTimeInd;
        }
    }

    /**
     * 设置retCreateTimeInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRetCreateTimeInd(Boolean value) {
        this.retCreateTimeInd = value;
    }

    /**
     * 获取searchCodeShareInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSearchCodeShareInd() {
        if (searchCodeShareInd == null) {
            return false;
        } else {
            return searchCodeShareInd;
        }
    }

    /**
     * 设置searchCodeShareInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSearchCodeShareInd(Boolean value) {
        this.searchCodeShareInd = value;
    }

    /**
     * 获取synTicketNumberInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSynTicketNumberInd() {
        if (synTicketNumberInd == null) {
            return false;
        } else {
            return synTicketNumberInd;
        }
    }

    /**
     * 设置synTicketNumberInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSynTicketNumberInd(Boolean value) {
        this.synTicketNumberInd = value;
    }

}
