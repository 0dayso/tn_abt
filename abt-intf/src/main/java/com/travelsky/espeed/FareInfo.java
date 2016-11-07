//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import java.math.BigDecimal;
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
 *         &lt;element ref="{http://espeed.travelsky.com}FlightSegmentRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}MarketingAirline" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}DepartureAirport" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}ArrivalAirport" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Endorsements" minOccurs="0"/>
 *         &lt;element name="TourCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FareBasisCodes" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}AgreementInfo" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}CommissionInfo" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}LengthOfStayRules" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Rule" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RuleRPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Cabin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ResBookDesigCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FareType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="JourneyType" type="{http://espeed.travelsky.com}JourneyType" />
 *       &lt;attribute name="OutBoundInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="InBoundInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "flightSegmentRef",
    "marketingAirline",
    "departureAirport",
    "arrivalAirport",
    "endorsements",
    "tourCode",
    "fareBasisCodes",
    "agreementInfo",
    "commissionInfo",
    "lengthOfStayRules",
    "rule"
})
@XmlRootElement(name = "FareInfo")
public class FareInfo {

    @XmlElement(name = "FlightSegmentRef")
    protected List<FlightSegmentRef> flightSegmentRef;
    @XmlElement(name = "MarketingAirline")
    protected List<MarketingAirline> marketingAirline;
    @XmlElement(name = "DepartureAirport")
    protected DepartureAirport departureAirport;
    @XmlElement(name = "ArrivalAirport")
    protected ArrivalAirport arrivalAirport;
    @XmlElement(name = "Endorsements")
    protected Endorsements endorsements;
    @XmlElement(name = "TourCode")
    protected String tourCode;
    @XmlElement(name = "FareBasisCodes")
    protected FareBasisCodes fareBasisCodes;
    @XmlElement(name = "AgreementInfo")
    protected AgreementInfo agreementInfo;
    @XmlElement(name = "CommissionInfo")
    protected CommissionInfo commissionInfo;
    @XmlElement(name = "LengthOfStayRules")
    protected LengthOfStayRules lengthOfStayRules;
    @XmlElement(name = "Rule")
    protected Rule rule;
    @XmlAttribute(name = "RPH")
    protected String rph;
    @XmlAttribute(name = "RuleRPH")
    protected String ruleRPH;
    @XmlAttribute(name = "Amount")
    protected BigDecimal amount;
    @XmlAttribute(name = "CurrencyCode")
    protected String currencyCode;
    @XmlAttribute(name = "Cabin")
    protected String cabin;
    @XmlAttribute(name = "ResBookDesigCode")
    protected String resBookDesigCode;
    @XmlAttribute(name = "FareType")
    protected String fareType;
    @XmlAttribute(name = "JourneyType")
    protected JourneyType journeyType;
    @XmlAttribute(name = "OutBoundInd")
    protected Boolean outBoundInd;
    @XmlAttribute(name = "InBoundInd")
    protected Boolean inBoundInd;

    /**
     * Gets the value of the flightSegmentRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightSegmentRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightSegmentRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightSegmentRef }
     * 
     * 
     */
    public List<FlightSegmentRef> getFlightSegmentRef() {
        if (flightSegmentRef == null) {
            flightSegmentRef = new ArrayList<FlightSegmentRef>();
        }
        return this.flightSegmentRef;
    }

    /**
     * Gets the value of the marketingAirline property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketingAirline property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketingAirline().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketingAirline }
     * 
     * 
     */
    public List<MarketingAirline> getMarketingAirline() {
        if (marketingAirline == null) {
            marketingAirline = new ArrayList<MarketingAirline>();
        }
        return this.marketingAirline;
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
     * 获取endorsements属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Endorsements }
     *     
     */
    public Endorsements getEndorsements() {
        return endorsements;
    }

    /**
     * 设置endorsements属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Endorsements }
     *     
     */
    public void setEndorsements(Endorsements value) {
        this.endorsements = value;
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
     * 获取fareBasisCodes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareBasisCodes }
     *     
     */
    public FareBasisCodes getFareBasisCodes() {
        return fareBasisCodes;
    }

    /**
     * 设置fareBasisCodes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareBasisCodes }
     *     
     */
    public void setFareBasisCodes(FareBasisCodes value) {
        this.fareBasisCodes = value;
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
     * 获取rule属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Rule }
     *     
     */
    public Rule getRule() {
        return rule;
    }

    /**
     * 设置rule属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Rule }
     *     
     */
    public void setRule(Rule value) {
        this.rule = value;
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
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
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
     * 获取cabin属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabin() {
        return cabin;
    }

    /**
     * 设置cabin属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabin(String value) {
        this.cabin = value;
    }

    /**
     * 获取resBookDesigCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResBookDesigCode() {
        return resBookDesigCode;
    }

    /**
     * 设置resBookDesigCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResBookDesigCode(String value) {
        this.resBookDesigCode = value;
    }

    /**
     * 获取fareType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareType() {
        return fareType;
    }

    /**
     * 设置fareType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareType(String value) {
        this.fareType = value;
    }

    /**
     * 获取journeyType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JourneyType }
     *     
     */
    public JourneyType getJourneyType() {
        return journeyType;
    }

    /**
     * 设置journeyType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JourneyType }
     *     
     */
    public void setJourneyType(JourneyType value) {
        this.journeyType = value;
    }

    /**
     * 获取outBoundInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOutBoundInd() {
        return outBoundInd;
    }

    /**
     * 设置outBoundInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOutBoundInd(Boolean value) {
        this.outBoundInd = value;
    }

    /**
     * 获取inBoundInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInBoundInd() {
        return inBoundInd;
    }

    /**
     * 设置inBoundInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInBoundInd(Boolean value) {
        this.inBoundInd = value;
    }

}
