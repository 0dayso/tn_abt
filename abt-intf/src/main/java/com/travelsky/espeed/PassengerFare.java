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
 *         &lt;element ref="{http://espeed.travelsky.com}TotalFare" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}BaseFare" minOccurs="0"/>
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
 *         &lt;element name="TourCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Remark" maxOccurs="10" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Taxes" minOccurs="0"/>
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
    "totalFare",
    "baseFare",
    "fees",
    "tourCode",
    "remark",
    "taxes"
})
@XmlRootElement(name = "PassengerFare")
public class PassengerFare {

    @XmlElement(name = "TotalFare")
    protected TotalFare totalFare;
    @XmlElement(name = "BaseFare")
    protected BaseFare baseFare;
    @XmlElement(name = "Fees")
    protected PassengerFare.Fees fees;
    @XmlElement(name = "TourCode")
    protected String tourCode;
    @XmlElement(name = "Remark")
    protected List<Remark> remark;
    @XmlElement(name = "Taxes")
    protected Taxes taxes;

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
     * 获取fees属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PassengerFare.Fees }
     *     
     */
    public PassengerFare.Fees getFees() {
        return fees;
    }

    /**
     * 设置fees属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PassengerFare.Fees }
     *     
     */
    public void setFees(PassengerFare.Fees value) {
        this.fees = value;
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
     * Gets the value of the remark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Remark }
     * 
     * 
     */
    public List<Remark> getRemark() {
        if (remark == null) {
            remark = new ArrayList<Remark>();
        }
        return this.remark;
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
        protected List<PassengerFare.Fees.Fee> fee;

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
         * {@link PassengerFare.Fees.Fee }
         * 
         * 
         */
        public List<PassengerFare.Fees.Fee> getFee() {
            if (fee == null) {
                fee = new ArrayList<PassengerFare.Fees.Fee>();
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
