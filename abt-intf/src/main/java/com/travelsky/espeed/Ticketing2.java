//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 04:03:21 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://espeed.travelsky.com}TicketItemInfo" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerRefNumber" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TicketTimeLimit" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="IsIssued" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="OfficeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IssuedType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IssueDevice" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IssueLocation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OfficeCity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Limitation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ticketItemInfo",
    "travelerRefNumber"
})
@XmlRootElement(name = "Ticketing")
public class Ticketing2 {

    @XmlElement(name = "TicketItemInfo")
    protected TicketItemInfo2 ticketItemInfo;
    @XmlElement(name = "TravelerRefNumber")
    protected TravelerRefNumber travelerRefNumber;
    @XmlAttribute(name = "TicketTimeLimit")
    @XmlSchemaType(name = "dateTime")
    protected String ticketTimeLimit;
    @XmlAttribute(name = "RPH")
    protected Integer rph;
    @XmlAttribute(name = "IsIssued")
    protected Boolean isIssued;
    @XmlAttribute(name = "OfficeCode")
    protected String officeCode;
    @XmlAttribute(name = "IssuedType")
    protected String issuedType;
    @XmlAttribute(name = "Remark")
    protected String remark;
    @XmlAttribute(name = "IssueDevice")
    protected String issueDevice;
    @XmlAttribute(name = "IssueLocation")
    protected String issueLocation;
    @XmlAttribute(name = "OfficeCity")
    protected String officeCity;
    @XmlAttribute(name = "Limitation")
    protected String limitation;

    /**
     * 获取ticketItemInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TicketItemInfo2 }
     *     
     */
    public TicketItemInfo2 getTicketItemInfo() {
        return ticketItemInfo;
    }

    /**
     * 设置ticketItemInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TicketItemInfo2 }
     *     
     */
    public void setTicketItemInfo(TicketItemInfo2 value) {
        this.ticketItemInfo = value;
    }

    /**
     * 获取travelerRefNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TravelerRefNumber }
     *     
     */
    public TravelerRefNumber getTravelerRefNumber() {
        return travelerRefNumber;
    }

    /**
     * 设置travelerRefNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TravelerRefNumber }
     *     
     */
    public void setTravelerRefNumber(TravelerRefNumber value) {
        this.travelerRefNumber = value;
    }

    /**
     * 获取ticketTimeLimit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketTimeLimit() {
        return ticketTimeLimit;
    }

    /**
     * 设置ticketTimeLimit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketTimeLimit(String value) {
        this.ticketTimeLimit = value;
    }

    /**
     * 获取rph属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRPH() {
        return rph;
    }

    /**
     * 设置rph属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRPH(Integer value) {
        this.rph = value;
    }

    /**
     * 获取isIssued属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsIssued() {
        return isIssued;
    }

    /**
     * 设置isIssued属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsIssued(Boolean value) {
        this.isIssued = value;
    }

    /**
     * 获取officeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeCode() {
        return officeCode;
    }

    /**
     * 设置officeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeCode(String value) {
        this.officeCode = value;
    }

    /**
     * 获取issuedType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuedType() {
        return issuedType;
    }

    /**
     * 设置issuedType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuedType(String value) {
        this.issuedType = value;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * 获取issueDevice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueDevice() {
        return issueDevice;
    }

    /**
     * 设置issueDevice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueDevice(String value) {
        this.issueDevice = value;
    }

    /**
     * 获取issueLocation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueLocation() {
        return issueLocation;
    }

    /**
     * 设置issueLocation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueLocation(String value) {
        this.issueLocation = value;
    }

    /**
     * 获取officeCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeCity() {
        return officeCity;
    }

    /**
     * 设置officeCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeCity(String value) {
        this.officeCity = value;
    }

    /**
     * 获取limitation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimitation() {
        return limitation;
    }

    /**
     * 设置limitation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimitation(String value) {
        this.limitation = value;
    }

}
