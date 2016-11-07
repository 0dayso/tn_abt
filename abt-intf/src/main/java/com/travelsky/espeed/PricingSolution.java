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
 *         &lt;element ref="{http://espeed.travelsky.com}FareComponents"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Taxes"/>
 *         &lt;element ref="{http://espeed.travelsky.com}RoutInfos" maxOccurs="unbounded"/>
 *         &lt;element name="EndorsementInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SequenceNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Amount" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CurrencyCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BrandFareType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ItineraryType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FareBasisCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AccountCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FareCalculation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FareBox" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TourCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ZValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fareComponents",
    "taxes",
    "routInfos",
    "endorsementInfo"
})
@XmlRootElement(name = "PricingSolution")
public class PricingSolution {

    @XmlElement(name = "FareComponents", required = true)
    protected FareComponents fareComponents;
    @XmlElement(name = "Taxes", required = true)
    protected Taxes taxes;
    @XmlElement(name = "RoutInfos", required = true)
    protected List<RoutInfos> routInfos;
    @XmlElement(name = "EndorsementInfo")
    protected String endorsementInfo;
    @XmlAttribute(name = "SequenceNumber", required = true)
    protected String sequenceNumber;
    @XmlAttribute(name = "Amount", required = true)
    protected String amount;
    @XmlAttribute(name = "CurrencyCode", required = true)
    protected String currencyCode;
    @XmlAttribute(name = "BrandFareType")
    protected String brandFareType;
    @XmlAttribute(name = "ItineraryType", required = true)
    protected String itineraryType;
    @XmlAttribute(name = "FareBasisCode", required = true)
    protected String fareBasisCode;
    @XmlAttribute(name = "AccountCode")
    protected String accountCode;
    @XmlAttribute(name = "FareCalculation")
    protected String fareCalculation;
    @XmlAttribute(name = "FareBox")
    protected String fareBox;
    @XmlAttribute(name = "TourCode")
    protected String tourCode;
    @XmlAttribute(name = "ZValue")
    protected String zValue;

    /**
     * 获取fareComponents属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareComponents }
     *     
     */
    public FareComponents getFareComponents() {
        return fareComponents;
    }

    /**
     * 设置fareComponents属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareComponents }
     *     
     */
    public void setFareComponents(FareComponents value) {
        this.fareComponents = value;
    }

    /**
     * 获取taxes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Taxes }
     *     
     */
    public Taxes getTaxes() {
        return taxes;
    }

    /**
     * 设置taxes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Taxes }
     *     
     */
    public void setTaxes(Taxes value) {
        this.taxes = value;
    }

    /**
     * Gets the value of the routInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the routInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoutInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoutInfos }
     * 
     * 
     */
    public List<RoutInfos> getRoutInfos() {
        if (routInfos == null) {
            routInfos = new ArrayList<RoutInfos>();
        }
        return this.routInfos;
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
     * 获取sequenceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * 设置sequenceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequenceNumber(String value) {
        this.sequenceNumber = value;
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
     * 获取brandFareType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandFareType() {
        return brandFareType;
    }

    /**
     * 设置brandFareType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandFareType(String value) {
        this.brandFareType = value;
    }

    /**
     * 获取itineraryType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItineraryType() {
        return itineraryType;
    }

    /**
     * 设置itineraryType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItineraryType(String value) {
        this.itineraryType = value;
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
     * 获取accountCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * 设置accountCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCode(String value) {
        this.accountCode = value;
    }

    /**
     * 获取fareCalculation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareCalculation() {
        return fareCalculation;
    }

    /**
     * 设置fareCalculation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareCalculation(String value) {
        this.fareCalculation = value;
    }

    /**
     * 获取fareBox属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareBox() {
        return fareBox;
    }

    /**
     * 设置fareBox属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareBox(String value) {
        this.fareBox = value;
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
     * 获取zValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZValue() {
        return zValue;
    }

    /**
     * 设置zValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZValue(String value) {
        this.zValue = value;
    }

}
