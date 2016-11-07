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
 *         &lt;element ref="{http://espeed.travelsky.com}AirTraveler" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PassengerName" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}ConjunctiveTicket" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FlightSegment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TravelerRefNumber" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TicketNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Type" type="{http://espeed.travelsky.com}TicketType" />
 *       &lt;attribute name="TotalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PaymentType" type="{http://espeed.travelsky.com}PayType" />
 *       &lt;attribute name="Endorsement" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TicketingStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ExchangeInfo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IATANo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OriginalIssue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IssueAirline" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ISI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IssueDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="MoreTax" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IT" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ReceiptPrinted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="PassengerID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ETicketType" type="{http://espeed.travelsky.com}ETicketType" />
 *       &lt;attribute name="OrgCity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DstCity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Tax" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airTraveler",
    "passengerName",
    "conjunctiveTicket",
    "flightSegment",
    "travelerRefNumber"
})
@XmlRootElement(name = "TicketItemInfo")
public class TicketItemInfo {

    @XmlElement(name = "AirTraveler")
    protected AirTraveler airTraveler;
    @XmlElement(name = "PassengerName")
    protected PassengerName passengerName;
    @XmlElement(name = "ConjunctiveTicket")
    protected List<ConjunctiveTicket> conjunctiveTicket;
    @XmlElement(name = "FlightSegment")
    protected List<FlightSegment> flightSegment;
    @XmlElement(name = "TravelerRefNumber")
    protected TravelerRefNumber travelerRefNumber;
    @XmlAttribute(name = "TicketNumber")
    protected String ticketNumber;
    @XmlAttribute(name = "Type")
    protected TicketType type;
    @XmlAttribute(name = "TotalAmount")
    protected BigDecimal totalAmount;
    @XmlAttribute(name = "CurrencyCode")
    protected String currencyCode;
    @XmlAttribute(name = "PaymentType")
    protected PayType paymentType;
    @XmlAttribute(name = "Endorsement")
    protected String endorsement;
    @XmlAttribute(name = "TicketingStatus")
    protected String ticketingStatus;
    @XmlAttribute(name = "Remark")
    protected String remark;
    @XmlAttribute(name = "RPH")
    protected Integer rph;
    @XmlAttribute(name = "ExchangeInfo")
    protected String exchangeInfo;
    @XmlAttribute(name = "IATANo")
    protected String iataNo;
    @XmlAttribute(name = "OriginalIssue")
    protected String originalIssue;
    @XmlAttribute(name = "IssueAirline")
    protected String issueAirline;
    @XmlAttribute(name = "ISI")
    protected String isi;
    @XmlAttribute(name = "IssueDate")
    @XmlSchemaType(name = "date")
    protected String issueDate;
    @XmlAttribute(name = "MoreTax")
    protected Boolean moreTax;
    @XmlAttribute(name = "IT")
    protected Boolean it;
    @XmlAttribute(name = "ReceiptPrinted")
    protected Boolean receiptPrinted;
    @XmlAttribute(name = "PassengerID")
    protected String passengerID;
    @XmlAttribute(name = "ETicketType")
    protected ETicketType eTicketType;
    @XmlAttribute(name = "OrgCity")
    protected String orgCity;
    @XmlAttribute(name = "DstCity")
    protected String dstCity;
    @XmlAttribute(name = "Tax")
    protected BigDecimal tax;

    /**
     * 获取airTraveler属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AirTraveler }
     *     
     */
    public AirTraveler getAirTraveler() {
        return airTraveler;
    }

    /**
     * 设置airTraveler属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AirTraveler }
     *     
     */
    public void setAirTraveler(AirTraveler value) {
        this.airTraveler = value;
    }

    /**
     * 获取passengerName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PassengerName }
     *     
     */
    public PassengerName getPassengerName() {
        return passengerName;
    }

    /**
     * 设置passengerName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PassengerName }
     *     
     */
    public void setPassengerName(PassengerName value) {
        this.passengerName = value;
    }

    /**
     * Gets the value of the conjunctiveTicket property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conjunctiveTicket property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConjunctiveTicket().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConjunctiveTicket }
     * 
     * 
     */
    public List<ConjunctiveTicket> getConjunctiveTicket() {
        if (conjunctiveTicket == null) {
            conjunctiveTicket = new ArrayList<ConjunctiveTicket>();
        }
        return this.conjunctiveTicket;
    }

    /**
     * Gets the value of the flightSegment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightSegment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightSegment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightSegment }
     * 
     * 
     */
    public List<FlightSegment> getFlightSegment() {
        if (flightSegment == null) {
            flightSegment = new ArrayList<FlightSegment>();
        }
        return this.flightSegment;
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
     * 获取ticketNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketNumber() {
        return ticketNumber;
    }

    /**
     * 设置ticketNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketNumber(String value) {
        this.ticketNumber = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TicketType }
     *     
     */
    public TicketType getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TicketType }
     *     
     */
    public void setType(TicketType value) {
        this.type = value;
    }

    /**
     * 获取totalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置totalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
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
     * 获取paymentType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PayType }
     *     
     */
    public PayType getPaymentType() {
        return paymentType;
    }

    /**
     * 设置paymentType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PayType }
     *     
     */
    public void setPaymentType(PayType value) {
        this.paymentType = value;
    }

    /**
     * 获取endorsement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndorsement() {
        return endorsement;
    }

    /**
     * 设置endorsement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndorsement(String value) {
        this.endorsement = value;
    }

    /**
     * 获取ticketingStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketingStatus() {
        return ticketingStatus;
    }

    /**
     * 设置ticketingStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketingStatus(String value) {
        this.ticketingStatus = value;
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
     * 获取exchangeInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeInfo() {
        return exchangeInfo;
    }

    /**
     * 设置exchangeInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeInfo(String value) {
        this.exchangeInfo = value;
    }

    /**
     * 获取iataNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIATANo() {
        return iataNo;
    }

    /**
     * 设置iataNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIATANo(String value) {
        this.iataNo = value;
    }

    /**
     * 获取originalIssue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalIssue() {
        return originalIssue;
    }

    /**
     * 设置originalIssue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalIssue(String value) {
        this.originalIssue = value;
    }

    /**
     * 获取issueAirline属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueAirline() {
        return issueAirline;
    }

    /**
     * 设置issueAirline属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueAirline(String value) {
        this.issueAirline = value;
    }

    /**
     * 获取isi属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISI() {
        return isi;
    }

    /**
     * 设置isi属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISI(String value) {
        this.isi = value;
    }

    /**
     * 获取issueDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * 设置issueDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueDate(String value) {
        this.issueDate = value;
    }

    /**
     * 获取moreTax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMoreTax() {
        return moreTax;
    }

    /**
     * 设置moreTax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMoreTax(Boolean value) {
        this.moreTax = value;
    }

    /**
     * 获取it属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIT() {
        return it;
    }

    /**
     * 设置it属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIT(Boolean value) {
        this.it = value;
    }

    /**
     * 获取receiptPrinted属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReceiptPrinted() {
        return receiptPrinted;
    }

    /**
     * 设置receiptPrinted属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReceiptPrinted(Boolean value) {
        this.receiptPrinted = value;
    }

    /**
     * 获取passengerID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassengerID() {
        return passengerID;
    }

    /**
     * 设置passengerID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassengerID(String value) {
        this.passengerID = value;
    }

    /**
     * 获取eTicketType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ETicketType }
     *     
     */
    public ETicketType getETicketType() {
        return eTicketType;
    }

    /**
     * 设置eTicketType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ETicketType }
     *     
     */
    public void setETicketType(ETicketType value) {
        this.eTicketType = value;
    }

    /**
     * 获取orgCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgCity() {
        return orgCity;
    }

    /**
     * 设置orgCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgCity(String value) {
        this.orgCity = value;
    }

    /**
     * 获取dstCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstCity() {
        return dstCity;
    }

    /**
     * 设置dstCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstCity(String value) {
        this.dstCity = value;
    }

    /**
     * 获取tax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * 设置tax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTax(BigDecimal value) {
        this.tax = value;
    }

}
