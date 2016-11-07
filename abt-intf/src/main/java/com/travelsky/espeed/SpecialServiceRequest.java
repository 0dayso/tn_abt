//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
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
 *         &lt;element ref="{http://espeed.travelsky.com}Airline" minOccurs="0"/>
 *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FlightRefNumber" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerRefNumber" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FlightLeg" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}DocumentRefNumber" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SSRCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ServiceQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="Status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BirthDate" type="{http://www.w3.org/2001/XMLSchema}date" />
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
    "airline",
    "text",
    "flightRefNumber",
    "travelerRefNumber",
    "flightLeg",
    "documentRefNumber"
})
@XmlRootElement(name = "SpecialServiceRequest")
public class SpecialServiceRequest {

    @XmlElement(name = "Airline")
    protected Airline airline;
    @XmlElement(name = "Text")
    protected String text;
    @XmlElement(name = "FlightRefNumber")
    protected List<FlightRefNumber> flightRefNumber;
    @XmlElement(name = "TravelerRefNumber")
    protected List<TravelerRefNumber> travelerRefNumber;
    @XmlElement(name = "FlightLeg")
    protected List<FlightLeg> flightLeg;
    @XmlElement(name = "DocumentRefNumber")
    protected List<DocumentRefNumber> documentRefNumber;
    @XmlAttribute(name = "SSRCode")
    protected String ssrCode;
    @XmlAttribute(name = "ServiceQuantity")
    protected BigDecimal serviceQuantity;
    @XmlAttribute(name = "Status")
    protected String status;
    @XmlAttribute(name = "BirthDate")
    @XmlSchemaType(name = "date")
    protected String birthDate;
    @XmlAttribute(name = "RPH")
    protected String rph;

    /**
     * 获取airline属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Airline }
     *     
     */
    public Airline getAirline() {
        return airline;
    }

    /**
     * 设置airline属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Airline }
     *     
     */
    public void setAirline(Airline value) {
        this.airline = value;
    }

    /**
     * 获取text属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * 设置text属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the flightRefNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightRefNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightRefNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightRefNumber }
     * 
     * 
     */
    public List<FlightRefNumber> getFlightRefNumber() {
        if (flightRefNumber == null) {
            flightRefNumber = new ArrayList<FlightRefNumber>();
        }
        return this.flightRefNumber;
    }

    /**
     * Gets the value of the travelerRefNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the travelerRefNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTravelerRefNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TravelerRefNumber }
     * 
     * 
     */
    public List<TravelerRefNumber> getTravelerRefNumber() {
        if (travelerRefNumber == null) {
            travelerRefNumber = new ArrayList<TravelerRefNumber>();
        }
        return this.travelerRefNumber;
    }

    /**
     * Gets the value of the flightLeg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightLeg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightLeg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightLeg }
     * 
     * 
     */
    public List<FlightLeg> getFlightLeg() {
        if (flightLeg == null) {
            flightLeg = new ArrayList<FlightLeg>();
        }
        return this.flightLeg;
    }

    /**
     * Gets the value of the documentRefNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentRefNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentRefNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentRefNumber }
     * 
     * 
     */
    public List<DocumentRefNumber> getDocumentRefNumber() {
        if (documentRefNumber == null) {
            documentRefNumber = new ArrayList<DocumentRefNumber>();
        }
        return this.documentRefNumber;
    }

    /**
     * 获取ssrCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSRCode() {
        return ssrCode;
    }

    /**
     * 设置ssrCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSSRCode(String value) {
        this.ssrCode = value;
    }

    /**
     * 获取serviceQuantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getServiceQuantity() {
        return serviceQuantity;
    }

    /**
     * 设置serviceQuantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setServiceQuantity(BigDecimal value) {
        this.serviceQuantity = value;
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
