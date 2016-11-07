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
 *         &lt;element ref="{http://espeed.travelsky.com}Airline"/>
 *         &lt;element ref="{http://espeed.travelsky.com}DepartureAirport"/>
 *         &lt;element ref="{http://espeed.travelsky.com}ArrivalAirport"/>
 *         &lt;element name="EndorsementInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}AgreementInfo" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}LengthOfStayRules" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}CommissionInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RuleRPH" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Amount" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CurrencyCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BookingClass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CabinClass" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FareBasisCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="JourneyType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TourCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OutBoundInd" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="InBoundInd" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "departureAirport",
    "arrivalAirport",
    "endorsementInfo",
    "agreementInfo",
    "lengthOfStayRules",
    "commissionInfo"
})
@XmlRootElement(name = "NetFare")
public class NetFare {

    @XmlElement(name = "Airline", required = true)
    protected Airline airline;
    @XmlElement(name = "DepartureAirport", required = true)
    protected DepartureAirport departureAirport;
    @XmlElement(name = "ArrivalAirport", required = true)
    protected ArrivalAirport arrivalAirport;
    @XmlElement(name = "EndorsementInfo")
    protected String endorsementInfo;
    @XmlElement(name = "AgreementInfo")
    protected AgreementInfo agreementInfo;
    @XmlElement(name = "LengthOfStayRules")
    protected LengthOfStayRules lengthOfStayRules;
    @XmlElement(name = "CommissionInfo")
    protected CommissionInfo commissionInfo;
    @XmlAttribute(name = "RPH", required = true)
    protected String rph;
    @XmlAttribute(name = "RuleRPH", required = true)
    protected String ruleRPH;
    @XmlAttribute(name = "Amount", required = true)
    protected String amount;
    @XmlAttribute(name = "CurrencyCode", required = true)
    protected String currencyCode;
    @XmlAttribute(name = "BookingClass", required = true)
    protected String bookingClass;
    @XmlAttribute(name = "CabinClass")
    protected String cabinClass;
    @XmlAttribute(name = "FareBasisCode", required = true)
    protected String fareBasisCode;
    @XmlAttribute(name = "JourneyType")
    protected String journeyType;
    @XmlAttribute(name = "TourCode")
    protected String tourCode;
    @XmlAttribute(name = "OutBoundInd", required = true)
    protected String outBoundInd;
    @XmlAttribute(name = "InBoundInd", required = true)
    protected String inBoundInd;

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
     * 获取departureAirport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DepartureAirport }
     *     
     */
    public DepartureAirport getDepartureAirport() {
        return departureAirport;
    }

    /**
     * 设置departureAirport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DepartureAirport }
     *     
     */
    public void setDepartureAirport(DepartureAirport value) {
        this.departureAirport = value;
    }

    /**
     * 获取arrivalAirport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrivalAirport }
     *     
     */
    public ArrivalAirport getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * 设置arrivalAirport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrivalAirport }
     *     
     */
    public void setArrivalAirport(ArrivalAirport value) {
        this.arrivalAirport = value;
    }

    /**
     * 获取endorsementInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndorsementInfo() {
        return endorsementInfo;
    }

    /**
     * 设置endorsementInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndorsementInfo(String value) {
        this.endorsementInfo = value;
    }

    /**
     * 获取agreementInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AgreementInfo }
     *     
     */
    public AgreementInfo getAgreementInfo() {
        return agreementInfo;
    }

    /**
     * 设置agreementInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AgreementInfo }
     *     
     */
    public void setAgreementInfo(AgreementInfo value) {
        this.agreementInfo = value;
    }

    /**
     * 获取lengthOfStayRules属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LengthOfStayRules }
     *     
     */
    public LengthOfStayRules getLengthOfStayRules() {
        return lengthOfStayRules;
    }

    /**
     * 设置lengthOfStayRules属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LengthOfStayRules }
     *     
     */
    public void setLengthOfStayRules(LengthOfStayRules value) {
        this.lengthOfStayRules = value;
    }

    /**
     * 获取commissionInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CommissionInfo }
     *     
     */
    public CommissionInfo getCommissionInfo() {
        return commissionInfo;
    }

    /**
     * 设置commissionInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CommissionInfo }
     *     
     */
    public void setCommissionInfo(CommissionInfo value) {
        this.commissionInfo = value;
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
     * 获取ruleRPH属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleRPH() {
        return ruleRPH;
    }

    /**
     * 设置ruleRPH属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleRPH(String value) {
        this.ruleRPH = value;
    }

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * 获取bookingClass属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingClass() {
        return bookingClass;
    }

    /**
     * 设置bookingClass属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingClass(String value) {
        this.bookingClass = value;
    }

    /**
     * 获取cabinClass属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabinClass() {
        return cabinClass;
    }

    /**
     * 设置cabinClass属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabinClass(String value) {
        this.cabinClass = value;
    }

    /**
     * 获取fareBasisCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareBasisCode() {
        return fareBasisCode;
    }

    /**
     * 设置fareBasisCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareBasisCode(String value) {
        this.fareBasisCode = value;
    }

    /**
     * 获取journeyType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJourneyType() {
        return journeyType;
    }

    /**
     * 设置journeyType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJourneyType(String value) {
        this.journeyType = value;
    }

    /**
     * 获取tourCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourCode() {
        return tourCode;
    }

    /**
     * 设置tourCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourCode(String value) {
        this.tourCode = value;
    }

    /**
     * 获取outBoundInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutBoundInd() {
        return outBoundInd;
    }

    /**
     * 设置outBoundInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutBoundInd(String value) {
        this.outBoundInd = value;
    }

    /**
     * 获取inBoundInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInBoundInd() {
        return inBoundInd;
    }

    /**
     * 设置inBoundInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInBoundInd(String value) {
        this.inBoundInd = value;
    }

}
