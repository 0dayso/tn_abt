//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:30:57 PM CST 
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
 *         &lt;element ref="{http://espeed.travelsky.com}OriginDestinationOptionRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FareInfoRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}BaseFare" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Taxes" minOccurs="0"/>
 *         &lt;element name="Fees" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Fee" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="FeeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *                           &lt;attribute name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://espeed.travelsky.com}TotalFare" minOccurs="0"/>
 *         &lt;element name="PseudoCityCode" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Endorsements" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FareBasisCodes" minOccurs="0"/>
 *         &lt;element name="TourCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Remark" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PricingInfos" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Eqviu" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ItineraryType" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="ZValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PassengerType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "originDestinationOptionRef",
    "fareInfoRef",
    "baseFare",
    "taxes",
    "fees",
    "totalFare",
    "pseudoCityCode",
    "endorsements",
    "fareBasisCodes",
    "tourCode",
    "remark",
    "pricingInfos",
    "eqviu"
})
@XmlRootElement(name = "ItinTotalFare")
public class ItinTotalFare2 {

    @XmlElement(name = "OriginDestinationOptionRef")
    protected List<OriginDestinationOptionRef> originDestinationOptionRef;
    @XmlElement(name = "FareInfoRef")
    protected List<FareInfoRef> fareInfoRef;
    @XmlElement(name = "BaseFare")
    protected BaseFare baseFare;
    @XmlElement(name = "Taxes")
    protected Taxes taxes;
    @XmlElement(name = "Fees")
    protected ItinTotalFare2.Fees fees;
    @XmlElement(name = "TotalFare")
    protected TotalFare totalFare;
    @XmlElement(name = "PseudoCityCode")
    protected List<String> pseudoCityCode;
    @XmlElement(name = "Endorsements")
    protected Endorsements endorsements;
    @XmlElement(name = "FareBasisCodes")
    protected FareBasisCodes fareBasisCodes;
    @XmlElement(name = "TourCode")
    protected String tourCode;
    @XmlElement(name = "Remark")
    protected Remark remark;
    @XmlElement(name = "PricingInfos")
    protected PricingInfos pricingInfos;
    @XmlElement(name = "Eqviu")
    protected Eqviu eqviu;
    @XmlAttribute(name = "RPH")
    protected String rph;
    @XmlAttribute(name = "ItineraryType")
    protected BigDecimal itineraryType;
    @XmlAttribute(name = "ZValue")
    protected String zValue;
    @XmlAttribute(name = "PassengerType")
    protected String passengerType;

    /**
     * Gets the value of the originDestinationOptionRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the originDestinationOptionRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOriginDestinationOptionRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OriginDestinationOptionRef }
     * 
     * 
     */
    public List<OriginDestinationOptionRef> getOriginDestinationOptionRef() {
        if (originDestinationOptionRef == null) {
            originDestinationOptionRef = new ArrayList<OriginDestinationOptionRef>();
        }
        return this.originDestinationOptionRef;
    }

    /**
     * Gets the value of the fareInfoRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fareInfoRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFareInfoRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FareInfoRef }
     * 
     * 
     */
    public List<FareInfoRef> getFareInfoRef() {
        if (fareInfoRef == null) {
            fareInfoRef = new ArrayList<FareInfoRef>();
        }
        return this.fareInfoRef;
    }

    /**
     * 获取baseFare属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BaseFare }
     *     
     */
    public BaseFare getBaseFare() {
        return baseFare;
    }

    /**
     * 设置baseFare属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BaseFare }
     *     
     */
    public void setBaseFare(BaseFare value) {
        this.baseFare = value;
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
     * 获取fees属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ItinTotalFare2.Fees }
     *     
     */
    public ItinTotalFare2.Fees getFees() {
        return fees;
    }

    /**
     * 设置fees属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ItinTotalFare2.Fees }
     *     
     */
    public void setFees(ItinTotalFare2.Fees value) {
        this.fees = value;
    }

    /**
     * 获取totalFare属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TotalFare }
     *     
     */
    public TotalFare getTotalFare() {
        return totalFare;
    }

    /**
     * 设置totalFare属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TotalFare }
     *     
     */
    public void setTotalFare(TotalFare value) {
        this.totalFare = value;
    }

    /**
     * Gets the value of the pseudoCityCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pseudoCityCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPseudoCityCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPseudoCityCode() {
        if (pseudoCityCode == null) {
            pseudoCityCode = new ArrayList<String>();
        }
        return this.pseudoCityCode;
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
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Remark }
     *     
     */
    public Remark getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Remark }
     *     
     */
    public void setRemark(Remark value) {
        this.remark = value;
    }

    /**
     * 获取pricingInfos属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PricingInfos }
     *     
     */
    public PricingInfos getPricingInfos() {
        return pricingInfos;
    }

    /**
     * 设置pricingInfos属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PricingInfos }
     *     
     */
    public void setPricingInfos(PricingInfos value) {
        this.pricingInfos = value;
    }

    /**
     * 获取eqviu属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Eqviu }
     *     
     */
    public Eqviu getEqviu() {
        return eqviu;
    }

    /**
     * 设置eqviu属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Eqviu }
     *     
     */
    public void setEqviu(Eqviu value) {
        this.eqviu = value;
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
     * 获取itineraryType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getItineraryType() {
        return itineraryType;
    }

    /**
     * 设置itineraryType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setItineraryType(BigDecimal value) {
        this.itineraryType = value;
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

    /**
     * 获取passengerType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassengerType() {
        return passengerType;
    }

    /**
     * 设置passengerType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassengerType(String value) {
        this.passengerType = value;
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
     *         &lt;element name="Fee" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="FeeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
     *                 &lt;attribute name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "fee"
    })
    public static class Fees {

        @XmlElement(name = "Fee", required = true)
        protected List<ItinTotalFare2.Fees.Fee> fee;

        /**
         * Gets the value of the fee property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fee property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFee().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ItinTotalFare2.Fees.Fee }
         * 
         * 
         */
        public List<ItinTotalFare2.Fees.Fee> getFee() {
            if (fee == null) {
                fee = new ArrayList<ItinTotalFare2.Fees.Fee>();
            }
            return this.fee;
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
         *       &lt;attribute name="FeeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
         *       &lt;attribute name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Fee {

            @XmlAttribute(name = "FeeCode")
            protected String feeCode;
            @XmlAttribute(name = "Amount")
            protected BigDecimal amount;
            @XmlAttribute(name = "CurrencyCode")
            protected String currencyCode;

            /**
             * 获取feeCode属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFeeCode() {
                return feeCode;
            }

            /**
             * 设置feeCode属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFeeCode(String value) {
                this.feeCode = value;
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

        }

    }

}
