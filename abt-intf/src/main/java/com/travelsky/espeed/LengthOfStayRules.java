//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import java.math.BigDecimal;
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
 *         &lt;element name="MinimumStay" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="MinStay" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *                 &lt;attribute name="StayUnit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MaximumStay" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="MaxStay" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *                 &lt;attribute name="StayUnit" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "minimumStay",
    "maximumStay"
})
@XmlRootElement(name = "LengthOfStayRules")
public class LengthOfStayRules {

    @XmlElement(name = "MinimumStay")
    protected LengthOfStayRules.MinimumStay minimumStay;
    @XmlElement(name = "MaximumStay")
    protected LengthOfStayRules.MaximumStay maximumStay;

    /**
     * 获取minimumStay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LengthOfStayRules.MinimumStay }
     *     
     */
    public LengthOfStayRules.MinimumStay getMinimumStay() {
        return minimumStay;
    }

    /**
     * 设置minimumStay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LengthOfStayRules.MinimumStay }
     *     
     */
    public void setMinimumStay(LengthOfStayRules.MinimumStay value) {
        this.minimumStay = value;
    }

    /**
     * 获取maximumStay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LengthOfStayRules.MaximumStay }
     *     
     */
    public LengthOfStayRules.MaximumStay getMaximumStay() {
        return maximumStay;
    }

    /**
     * 设置maximumStay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LengthOfStayRules.MaximumStay }
     *     
     */
    public void setMaximumStay(LengthOfStayRules.MaximumStay value) {
        this.maximumStay = value;
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
     *       &lt;attribute name="MaxStay" type="{http://www.w3.org/2001/XMLSchema}decimal" />
     *       &lt;attribute name="StayUnit" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class MaximumStay {

        @XmlAttribute(name = "MaxStay")
        protected BigDecimal maxStay;
        @XmlAttribute(name = "StayUnit")
        protected String stayUnit;

        /**
         * 获取maxStay属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getMaxStay() {
            return maxStay;
        }

        /**
         * 设置maxStay属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setMaxStay(BigDecimal value) {
            this.maxStay = value;
        }

        /**
         * 获取stayUnit属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStayUnit() {
            return stayUnit;
        }

        /**
         * 设置stayUnit属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStayUnit(String value) {
            this.stayUnit = value;
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
     *       &lt;attribute name="MinStay" type="{http://www.w3.org/2001/XMLSchema}decimal" />
     *       &lt;attribute name="StayUnit" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class MinimumStay {

        @XmlAttribute(name = "MinStay")
        protected BigDecimal minStay;
        @XmlAttribute(name = "StayUnit")
        protected String stayUnit;

        /**
         * 获取minStay属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getMinStay() {
            return minStay;
        }

        /**
         * 设置minStay属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setMinStay(BigDecimal value) {
            this.minStay = value;
        }

        /**
         * 获取stayUnit属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStayUnit() {
            return stayUnit;
        }

        /**
         * 设置stayUnit属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStayUnit(String value) {
            this.stayUnit = value;
        }

    }

}
