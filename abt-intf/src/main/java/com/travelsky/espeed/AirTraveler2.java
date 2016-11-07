//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:30:57 PM CST 
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
 *         &lt;element ref="{http://espeed.travelsky.com}PersonName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Telephone" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ContactInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Address" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Document" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PassengerTypeQuantity2" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerRefNumber" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FlightSegmentRPHs" minOccurs="0"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AddressFlightBinding" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AddressRPH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FlightSegmentRPH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DocumentFlightBinding" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DocumentRPH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FlightSegmentRPH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="BirthDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Gender" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PassengerTypeCode" type="{http://espeed.travelsky.com}PassengerType" />
 *       &lt;attribute name="AccompaniedByInfant" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
    "personName",
    "telephone",
    "contactInfo",
    "email",
    "address",
    "document",
    "passengerTypeQuantity",
    "travelerRefNumber",
    "flightSegmentRPHs",
    "comment",
    "addressFlightBinding",
    "documentFlightBinding"
})
@XmlRootElement(name = "AirTraveler")
public class AirTraveler2 {

    @XmlElement(name = "PersonName")
    protected List<PersonName> personName;
    @XmlElement(name = "Telephone")
    protected List<Telephone> telephone;
    @XmlElement(name = "ContactInfo")
    protected String contactInfo;
    @XmlElement(name = "Email")
    protected List<String> email;
    @XmlElement(name = "Address")
    protected List<Address> address;
    @XmlElement(name = "Document")
    protected List<Document> document;
    @XmlElement(name = "PassengerTypeQuantity")
    protected PassengerTypeQuantity passengerTypeQuantity;
    @XmlElement(name = "TravelerRefNumber")
    protected TravelerRefNumber travelerRefNumber;
    @XmlElement(name = "FlightSegmentRPHs")
    protected FlightSegmentRPHs flightSegmentRPHs;
    @XmlElement(name = "Comment")
    protected String comment;
    @XmlElement(name = "AddressFlightBinding")
    protected List<AirTraveler2.AddressFlightBinding> addressFlightBinding;
    @XmlElement(name = "DocumentFlightBinding")
    protected List<AirTraveler2.DocumentFlightBinding> documentFlightBinding;
    @XmlAttribute(name = "BirthDate")
    @XmlSchemaType(name = "date")
    protected String birthDate;
    @XmlAttribute(name = "Gender")
    protected String gender;
    @XmlAttribute(name = "PassengerTypeCode")
    protected PassengerType passengerTypeCode;
    @XmlAttribute(name = "AccompaniedByInfant")
    protected Boolean accompaniedByInfant;
    @XmlAttribute(name = "RPH")
    protected String rph;

    /**
     * Gets the value of the personName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonName }
     * 
     * 
     */
    public List<PersonName> getPersonName() {
        if (personName == null) {
            personName = new ArrayList<PersonName>();
        }
        return this.personName;
    }

    /**
     * Gets the value of the telephone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the telephone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTelephone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Telephone }
     * 
     * 
     */
    public List<Telephone> getTelephone() {
        if (telephone == null) {
            telephone = new ArrayList<Telephone>();
        }
        return this.telephone;
    }

    /**
     * 获取contactInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * 设置contactInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactInfo(String value) {
        this.contactInfo = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the email property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEmail() {
        if (email == null) {
            email = new ArrayList<String>();
        }
        return this.email;
    }

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Address }
     * 
     * 
     */
    public List<Address> getAddress() {
        if (address == null) {
            address = new ArrayList<Address>();
        }
        return this.address;
    }

    /**
     * Gets the value of the document property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the document property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Document }
     * 
     * 
     */
    public List<Document> getDocument() {
        if (document == null) {
            document = new ArrayList<Document>();
        }
        return this.document;
    }

    /**
     * 获取passengerTypeQuantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PassengerTypeQuantity }
     *     
     */
    public PassengerTypeQuantity getPassengerTypeQuantity() {
        return passengerTypeQuantity;
    }

    /**
     * 设置passengerTypeQuantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PassengerTypeQuantity }
     *     
     */
    public void setPassengerTypeQuantity(PassengerTypeQuantity value) {
        this.passengerTypeQuantity = value;
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
     * 获取flightSegmentRPHs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FlightSegmentRPHs }
     *     
     */
    public FlightSegmentRPHs getFlightSegmentRPHs() {
        return flightSegmentRPHs;
    }

    /**
     * 设置flightSegmentRPHs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FlightSegmentRPHs }
     *     
     */
    public void setFlightSegmentRPHs(FlightSegmentRPHs value) {
        this.flightSegmentRPHs = value;
    }

    /**
     * 获取comment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置comment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the addressFlightBinding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressFlightBinding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressFlightBinding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AirTraveler2.AddressFlightBinding }
     * 
     * 
     */
    public List<AirTraveler2.AddressFlightBinding> getAddressFlightBinding() {
        if (addressFlightBinding == null) {
            addressFlightBinding = new ArrayList<AirTraveler2.AddressFlightBinding>();
        }
        return this.addressFlightBinding;
    }

    /**
     * Gets the value of the documentFlightBinding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentFlightBinding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentFlightBinding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AirTraveler2.DocumentFlightBinding }
     * 
     * 
     */
    public List<AirTraveler2.DocumentFlightBinding> getDocumentFlightBinding() {
        if (documentFlightBinding == null) {
            documentFlightBinding = new ArrayList<AirTraveler2.DocumentFlightBinding>();
        }
        return this.documentFlightBinding;
    }

    /**
     * 获取birthDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * 设置birthDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * 获取gender属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置gender属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * 获取passengerTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PassengerType }
     *     
     */
    public PassengerType getPassengerTypeCode() {
        return passengerTypeCode;
    }

    /**
     * 设置passengerTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PassengerType }
     *     
     */
    public void setPassengerTypeCode(PassengerType value) {
        this.passengerTypeCode = value;
    }

    /**
     * 获取accompaniedByInfant属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAccompaniedByInfant() {
        return accompaniedByInfant;
    }

    /**
     * 设置accompaniedByInfant属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccompaniedByInfant(Boolean value) {
        this.accompaniedByInfant = value;
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
     *         &lt;element name="AddressRPH" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FlightSegmentRPH" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "addressRPH",
        "flightSegmentRPH"
    })
    public static class AddressFlightBinding {

        @XmlElement(name = "AddressRPH", required = true)
        protected String addressRPH;
        @XmlElement(name = "FlightSegmentRPH", required = true)
        protected String flightSegmentRPH;

        /**
         * 获取addressRPH属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAddressRPH() {
            return addressRPH;
        }

        /**
         * 设置addressRPH属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAddressRPH(String value) {
            this.addressRPH = value;
        }

        /**
         * 获取flightSegmentRPH属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFlightSegmentRPH() {
            return flightSegmentRPH;
        }

        /**
         * 设置flightSegmentRPH属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFlightSegmentRPH(String value) {
            this.flightSegmentRPH = value;
        }

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
     *         &lt;element name="DocumentRPH" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FlightSegmentRPH" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "documentRPH",
        "flightSegmentRPH"
    })
    public static class DocumentFlightBinding {

        @XmlElement(name = "DocumentRPH", required = true)
        protected String documentRPH;
        @XmlElement(name = "FlightSegmentRPH", required = true)
        protected String flightSegmentRPH;

        /**
         * 获取documentRPH属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDocumentRPH() {
            return documentRPH;
        }

        /**
         * 设置documentRPH属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDocumentRPH(String value) {
            this.documentRPH = value;
        }

        /**
         * 获取flightSegmentRPH属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFlightSegmentRPH() {
            return flightSegmentRPH;
        }

        /**
         * 设置flightSegmentRPH属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFlightSegmentRPH(String value) {
            this.flightSegmentRPH = value;
        }

    }

}