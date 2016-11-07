//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 04:03:21 PM CST 
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
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element ref="{http://espeed.travelsky.com}Warnings" minOccurs="0"/>
 *           &lt;element name="AirResRet" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://espeed.travelsky.com}FlightSegments" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}BookingReferenceID"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}AirTraveler2" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}Ticketing" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}TicketItemInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}Responsibility" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}OpenAccountAddress" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}GroupInfo" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}Others" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}FN" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}FP" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}FC" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}EI" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}OI" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}TC" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}ContactInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}SpecialRemark" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}OtherServiceInformation" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element ref="{http://espeed.travelsky.com}SpecialServiceRequest" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element name="CreateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/sequence>
 *         &lt;element ref="{http://espeed.travelsky.com}Errors"/>
 *       &lt;/choice>
 *       &lt;attribute name="EchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SequenceNmbr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "warnings",
    "airResRet",
    "errors"
})
@XmlRootElement(name = "OTA_AirResRetRS")
public class OTAAirResRetRS {

    @XmlElement(name = "Warnings")
    protected Warnings warnings;
    @XmlElement(name = "AirResRet")
    protected OTAAirResRetRS.AirResRet airResRet;
    @XmlElement(name = "Errors")
    protected Errors errors;
    @XmlAttribute(name = "EchoToken")
    protected String echoToken;
    @XmlAttribute(name = "TimeStamp")
    protected String timeStamp;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "Target")
    protected String target;
    @XmlAttribute(name = "SequenceNmbr")
    protected String sequenceNmbr;

    /**
     * 获取warnings属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Warnings }
     *     
     */
    public Warnings getWarnings() {
        return warnings;
    }

    /**
     * 设置warnings属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Warnings }
     *     
     */
    public void setWarnings(Warnings value) {
        this.warnings = value;
    }

    /**
     * 获取airResRet属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OTAAirResRetRS.AirResRet }
     *     
     */
    public OTAAirResRetRS.AirResRet getAirResRet() {
        return airResRet;
    }

    /**
     * 设置airResRet属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OTAAirResRetRS.AirResRet }
     *     
     */
    public void setAirResRet(OTAAirResRetRS.AirResRet value) {
        this.airResRet = value;
    }

    /**
     * 获取errors属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Errors }
     *     
     */
    public Errors getErrors() {
        return errors;
    }

    /**
     * 设置errors属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Errors }
     *     
     */
    public void setErrors(Errors value) {
        this.errors = value;
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
     * 获取sequenceNmbr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequenceNmbr() {
        return sequenceNmbr;
    }

    /**
     * 设置sequenceNmbr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequenceNmbr(String value) {
        this.sequenceNmbr = value;
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
     *         &lt;element ref="{http://espeed.travelsky.com}FlightSegments" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}BookingReferenceID"/>
     *         &lt;element ref="{http://espeed.travelsky.com}AirTraveler2" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}Ticketing" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}TicketItemInfo" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}Responsibility" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}OpenAccountAddress" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}GroupInfo" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}Others" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}FN" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}FP" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}FC" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}EI" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}OI" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}TC" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}ContactInfo" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}SpecialRemark" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}OtherServiceInformation" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element ref="{http://espeed.travelsky.com}SpecialServiceRequest" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="CreateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
        "flightSegments",
        "bookingReferenceID",
        "airTraveler",
        "ticketing",
        "ticketItemInfo",
        "responsibility",
        "openAccountAddress",
        "groupInfo",
        "others",
        "fn",
        "fp",
        "fc",
        "ei",
        "oi",
        "tc",
        "contactInfo",
        "specialRemark",
        "otherServiceInformation",
        "specialServiceRequest",
        "createTime"
    })
    public static class AirResRet {

        @XmlElement(name = "FlightSegments")
        protected List<FlightSegments2> flightSegments;
        @XmlElement(name = "BookingReferenceID", required = true)
        protected BookingReferenceID2 bookingReferenceID;
        @XmlElement(name = "AirTraveler")
        protected List<AirTraveler2> airTraveler;
        @XmlElement(name = "Ticketing")
        protected List<Ticketing2> ticketing;
        @XmlElement(name = "TicketItemInfo")
        protected List<TicketItemInfo2> ticketItemInfo;
        @XmlElement(name = "Responsibility")
        protected Responsibility responsibility;
        @XmlElement(name = "OpenAccountAddress")
        protected List<OpenAccountAddress> openAccountAddress;
        @XmlElement(name = "GroupInfo")
        protected GroupInfo groupInfo;
        @XmlElement(name = "Others")
        protected List<Others> others;
        @XmlElement(name = "FN")
        protected List<FN> fn;
        @XmlElement(name = "FP")
        protected List<FP> fp;
        @XmlElement(name = "FC")
        protected List<FC> fc;
        @XmlElement(name = "EI")
        protected List<EI> ei;
        @XmlElement(name = "OI")
        protected List<OI> oi;
        @XmlElement(name = "TC")
        protected List<TC> tc;
        @XmlElement(name = "ContactInfo")
        protected List<ContactInfo> contactInfo;
        @XmlElement(name = "SpecialRemark")
        protected List<SpecialRemark> specialRemark;
        @XmlElement(name = "OtherServiceInformation")
        protected List<OtherServiceInformation> otherServiceInformation;
        @XmlElement(name = "SpecialServiceRequest")
        protected List<SpecialServiceRequest> specialServiceRequest;
        @XmlElement(name = "CreateTime")
        @XmlSchemaType(name = "dateTime")
        protected String createTime;

        /**
         * Gets the value of the flightSegments property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the flightSegments property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFlightSegments().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FlightSegments2 }
         * 
         * 
         */
        public List<FlightSegments2> getFlightSegments() {
            if (flightSegments == null) {
                flightSegments = new ArrayList<FlightSegments2>();
            }
            return this.flightSegments;
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
         * Gets the value of the airTraveler property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the airTraveler property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAirTraveler().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AirTraveler2 }
         * 
         * 
         */
        public List<AirTraveler2> getAirTraveler() {
            if (airTraveler == null) {
                airTraveler = new ArrayList<AirTraveler2>();
            }
            return this.airTraveler;
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
         * {@link Ticketing2 }
         * 
         * 
         */
        public List<Ticketing2> getTicketing() {
            if (ticketing == null) {
                ticketing = new ArrayList<Ticketing2>();
            }
            return this.ticketing;
        }

        /**
         * Gets the value of the ticketItemInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ticketItemInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTicketItemInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TicketItemInfo2 }
         * 
         * 
         */
        public List<TicketItemInfo2> getTicketItemInfo() {
            if (ticketItemInfo == null) {
                ticketItemInfo = new ArrayList<TicketItemInfo2>();
            }
            return this.ticketItemInfo;
        }

        /**
         * 获取responsibility属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Responsibility }
         *     
         */
        public Responsibility getResponsibility() {
            return responsibility;
        }

        /**
         * 设置responsibility属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Responsibility }
         *     
         */
        public void setResponsibility(Responsibility value) {
            this.responsibility = value;
        }

        /**
         * Gets the value of the openAccountAddress property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the openAccountAddress property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOpenAccountAddress().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OpenAccountAddress }
         * 
         * 
         */
        public List<OpenAccountAddress> getOpenAccountAddress() {
            if (openAccountAddress == null) {
                openAccountAddress = new ArrayList<OpenAccountAddress>();
            }
            return this.openAccountAddress;
        }

        /**
         * 获取groupInfo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link GroupInfo }
         *     
         */
        public GroupInfo getGroupInfo() {
            return groupInfo;
        }

        /**
         * 设置groupInfo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link GroupInfo }
         *     
         */
        public void setGroupInfo(GroupInfo value) {
            this.groupInfo = value;
        }

        /**
         * Gets the value of the others property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the others property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOthers().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Others }
         * 
         * 
         */
        public List<Others> getOthers() {
            if (others == null) {
                others = new ArrayList<Others>();
            }
            return this.others;
        }

        /**
         * Gets the value of the fn property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fn property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFN().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FN }
         * 
         * 
         */
        public List<FN> getFN() {
            if (fn == null) {
                fn = new ArrayList<FN>();
            }
            return this.fn;
        }

        /**
         * Gets the value of the fp property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fp property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFP().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FP }
         * 
         * 
         */
        public List<FP> getFP() {
            if (fp == null) {
                fp = new ArrayList<FP>();
            }
            return this.fp;
        }

        /**
         * Gets the value of the fc property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fc property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFC().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FC }
         * 
         * 
         */
        public List<FC> getFC() {
            if (fc == null) {
                fc = new ArrayList<FC>();
            }
            return this.fc;
        }

        /**
         * Gets the value of the ei property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ei property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEI().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EI }
         * 
         * 
         */
        public List<EI> getEI() {
            if (ei == null) {
                ei = new ArrayList<EI>();
            }
            return this.ei;
        }

        /**
         * Gets the value of the oi property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the oi property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOI().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OI }
         * 
         * 
         */
        public List<OI> getOI() {
            if (oi == null) {
                oi = new ArrayList<OI>();
            }
            return this.oi;
        }

        /**
         * Gets the value of the tc property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tc property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTC().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TC }
         * 
         * 
         */
        public List<TC> getTC() {
            if (tc == null) {
                tc = new ArrayList<TC>();
            }
            return this.tc;
        }

        /**
         * Gets the value of the contactInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contactInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContactInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ContactInfo }
         * 
         * 
         */
        public List<ContactInfo> getContactInfo() {
            if (contactInfo == null) {
                contactInfo = new ArrayList<ContactInfo>();
            }
            return this.contactInfo;
        }

        /**
         * Gets the value of the specialRemark property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the specialRemark property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSpecialRemark().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SpecialRemark }
         * 
         * 
         */
        public List<SpecialRemark> getSpecialRemark() {
            if (specialRemark == null) {
                specialRemark = new ArrayList<SpecialRemark>();
            }
            return this.specialRemark;
        }

        /**
         * Gets the value of the otherServiceInformation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otherServiceInformation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtherServiceInformation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OtherServiceInformation }
         * 
         * 
         */
        public List<OtherServiceInformation> getOtherServiceInformation() {
            if (otherServiceInformation == null) {
                otherServiceInformation = new ArrayList<OtherServiceInformation>();
            }
            return this.otherServiceInformation;
        }

        /**
         * Gets the value of the specialServiceRequest property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the specialServiceRequest property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSpecialServiceRequest().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SpecialServiceRequest }
         * 
         * 
         */
        public List<SpecialServiceRequest> getSpecialServiceRequest() {
            if (specialServiceRequest == null) {
                specialServiceRequest = new ArrayList<SpecialServiceRequest>();
            }
            return this.specialServiceRequest;
        }

        /**
         * 获取createTime属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreateTime() {
            return createTime;
        }

        /**
         * 设置createTime属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreateTime(String value) {
            this.createTime = value;
        }

    }

}
