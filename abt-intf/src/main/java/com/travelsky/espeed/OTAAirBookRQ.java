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
 *         &lt;element ref="{http://espeed.travelsky.com}POS"/>
 *         &lt;element name="SegmentStatusValid" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://espeed.travelsky.com}AirItinerary"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerInfo"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Ticketing" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}BookingReferenceID" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TPA_Extensions" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AutoARNKInd" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="GroupName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="GroupSize" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="SubscribePnrInd" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="CheckSegmentNumberInd" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="SegmentCheckInd" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
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
    "segmentStatusValid",
    "airItinerary",
    "travelerInfo",
    "ticketing",
    "bookingReferenceID",
    "tpaExtensions"
})
@XmlRootElement(name = "OTA_AirBookRQ")
public class OTAAirBookRQ {

    @XmlElement(name = "POS", required = true)
    protected POS pos;
    @XmlElement(name = "SegmentStatusValid")
    protected OTAAirBookRQ.SegmentStatusValid segmentStatusValid;
    @XmlElement(name = "AirItinerary", required = true)
    protected AirItinerary airItinerary;
    @XmlElement(name = "TravelerInfo", required = true)
    protected TravelerInfo travelerInfo;
    @XmlElement(name = "Ticketing")
    protected Ticketing ticketing;
    @XmlElement(name = "BookingReferenceID")
    protected BookingReferenceID bookingReferenceID;
    @XmlElement(name = "TPA_Extensions")
    protected TPAExtensions tpaExtensions;
    @XmlAttribute(name = "EchoToken")
    protected String echoToken;
    @XmlAttribute(name = "TimeStamp")
    protected String timeStamp;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "Target")
    protected String target;
    @XmlAttribute(name = "AutoARNKInd")
    protected Boolean autoARNKInd;
    @XmlAttribute(name = "GroupName")
    protected String groupName;
    @XmlAttribute(name = "GroupSize")
    protected Integer groupSize;
    @XmlAttribute(name = "SubscribePnrInd")
    protected Boolean subscribePnrInd;
    @XmlAttribute(name = "CheckSegmentNumberInd")
    protected Boolean checkSegmentNumberInd;
    @XmlAttribute(name = "SegmentCheckInd")
    protected Boolean segmentCheckInd;

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
     * 获取segmentStatusValid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OTAAirBookRQ.SegmentStatusValid }
     *     
     */
    public OTAAirBookRQ.SegmentStatusValid getSegmentStatusValid() {
        return segmentStatusValid;
    }

    /**
     * 设置segmentStatusValid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OTAAirBookRQ.SegmentStatusValid }
     *     
     */
    public void setSegmentStatusValid(OTAAirBookRQ.SegmentStatusValid value) {
        this.segmentStatusValid = value;
    }

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
     * 获取ticketing属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Ticketing }
     *     
     */
    public Ticketing getTicketing() {
        return ticketing;
    }

    /**
     * 设置ticketing属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Ticketing }
     *     
     */
    public void setTicketing(Ticketing value) {
        this.ticketing = value;
    }

    /**
     * 获取bookingReferenceID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BookingReferenceID }
     *     
     */
    public BookingReferenceID getBookingReferenceID() {
        return bookingReferenceID;
    }

    /**
     * 设置bookingReferenceID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BookingReferenceID }
     *     
     */
    public void setBookingReferenceID(BookingReferenceID value) {
        this.bookingReferenceID = value;
    }

    /**
     * 获取tpaExtensions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TPAExtensions }
     *     
     */
    public TPAExtensions getTPAExtensions() {
        return tpaExtensions;
    }

    /**
     * 设置tpaExtensions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TPAExtensions }
     *     
     */
    public void setTPAExtensions(TPAExtensions value) {
        this.tpaExtensions = value;
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
     * 获取autoARNKInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAutoARNKInd() {
        if (autoARNKInd == null) {
            return false;
        } else {
            return autoARNKInd;
        }
    }

    /**
     * 设置autoARNKInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoARNKInd(Boolean value) {
        this.autoARNKInd = value;
    }

    /**
     * 获取groupName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置groupName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * 获取groupSize属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGroupSize() {
        return groupSize;
    }

    /**
     * 设置groupSize属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGroupSize(Integer value) {
        this.groupSize = value;
    }

    /**
     * 获取subscribePnrInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSubscribePnrInd() {
        if (subscribePnrInd == null) {
            return false;
        } else {
            return subscribePnrInd;
        }
    }

    /**
     * 设置subscribePnrInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSubscribePnrInd(Boolean value) {
        this.subscribePnrInd = value;
    }

    /**
     * 获取checkSegmentNumberInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCheckSegmentNumberInd() {
        if (checkSegmentNumberInd == null) {
            return false;
        } else {
            return checkSegmentNumberInd;
        }
    }

    /**
     * 设置checkSegmentNumberInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCheckSegmentNumberInd(Boolean value) {
        this.checkSegmentNumberInd = value;
    }

    /**
     * 获取segmentCheckInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSegmentCheckInd() {
        if (segmentCheckInd == null) {
            return false;
        } else {
            return segmentCheckInd;
        }
    }

    /**
     * 设置segmentCheckInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSegmentCheckInd(Boolean value) {
        this.segmentCheckInd = value;
    }


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
     *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "status"
    })
    public static class SegmentStatusValid {

        @XmlElement(name = "Status", required = true)
        protected List<String> status;

        /**
         * Gets the value of the status property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the status property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStatus().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getStatus() {
            if (status == null) {
                status = new ArrayList<String>();
            }
            return this.status;
        }

    }

}
