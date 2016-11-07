//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element ref="{http://espeed.travelsky.com}AirItinerary" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerInfo" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Ticketing" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}BookingReferenceID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PriceInfo"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LastModified" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="CreateDateTime" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="PseudoCityCode" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airItinerary",
    "travelerInfo",
    "ticketing",
    "bookingReferenceID",
    "comment",
    "priceInfo"
})
@XmlRootElement(name = "AirReservation")
public class AirReservation {

    @XmlElement(name = "AirItinerary")
    protected AirItinerary airItinerary;
    @XmlElement(name = "TravelerInfo")
    protected TravelerInfo travelerInfo;
    @XmlElement(name = "Ticketing")
    protected List<Ticketing> ticketing;
    @XmlElement(name = "BookingReferenceID")
    protected List<BookingReferenceID> bookingReferenceID;
    @XmlElement(name = "Comment")
    protected List<String> comment;
    @XmlElement(name = "PriceInfo", required = true)
    protected PriceInfo priceInfo;
    @XmlAttribute(name = "Status")
    protected String status;
    @XmlAttribute(name = "LastModified")
    @XmlSchemaType(name = "dateTime")
    protected String lastModified;
    @XmlAttribute(name = "CreateDateTime")
    @XmlSchemaType(name = "anySimpleType")
    protected String createDateTime;
    @XmlAttribute(name = "PseudoCityCode")
    @XmlSchemaType(name = "anySimpleType")
    protected String pseudoCityCode;

    /**
     * 获取airItinerary属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AirItinerary }
     *     
     */
    public AirItinerary getAirItinerary() {
        return airItinerary;
    }

    /**
     * 设置airItinerary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AirItinerary }
     *     
     */
    public void setAirItinerary(AirItinerary value) {
        this.airItinerary = value;
    }

    /**
     * 获取travelerInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TravelerInfo }
     *     
     */
    public TravelerInfo getTravelerInfo() {
        return travelerInfo;
    }

    /**
     * 设置travelerInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TravelerInfo }
     *     
     */
    public void setTravelerInfo(TravelerInfo value) {
        this.travelerInfo = value;
    }

    /**
     * Gets the value of the ticketing property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ticketing property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTicketing().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ticketing }
     * 
     * 
     */
    public List<Ticketing> getTicketing() {
        if (ticketing == null) {
            ticketing = new ArrayList<Ticketing>();
        }
        return this.ticketing;
    }

    /**
     * Gets the value of the bookingReferenceID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingReferenceID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingReferenceID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BookingReferenceID }
     * 
     * 
     */
    public List<BookingReferenceID> getBookingReferenceID() {
        if (bookingReferenceID == null) {
            bookingReferenceID = new ArrayList<BookingReferenceID>();
        }
        return this.bookingReferenceID;
    }

    /**
     * Gets the value of the comment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getComment() {
        if (comment == null) {
            comment = new ArrayList<String>();
        }
        return this.comment;
    }

    /**
     * 获取priceInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PriceInfo }
     *     
     */
    public PriceInfo getPriceInfo() {
        return priceInfo;
    }

    /**
     * 设置priceInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PriceInfo }
     *     
     */
    public void setPriceInfo(PriceInfo value) {
        this.priceInfo = value;
    }

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * 获取lastModified属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     * 设置lastModified属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModified(String value) {
        this.lastModified = value;
    }

    /**
     * 获取createDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateDateTime() {
        return createDateTime;
    }

    /**
     * 设置createDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateDateTime(String value) {
        this.createDateTime = value;
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

}
