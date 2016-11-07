//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:10:40 PM CST 
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
 *         &lt;element ref="{http://espeed.travelsky.com}POS"/>
 *         &lt;element name="AirBookModifyRQ">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://espeed.travelsky.com}AirReservation" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="ModificationType" use="required" type="{http://espeed.travelsky.com}AirbookModifyType" />
 *                 &lt;attribute name="ModificationInfo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="CheckActionCode" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://espeed.travelsky.com}AirReservation" minOccurs="0"/>
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
    "pos",
    "airBookModifyRQ",
    "airReservation"
})
@XmlRootElement(name = "OTA_AirBookModifyRQ")
public class OTAAirBookModifyRQ {

    @XmlElement(name = "POS", required = true)
    protected POS pos;
    @XmlElement(name = "AirBookModifyRQ", required = true)
    protected OTAAirBookModifyRQ.AirBookModifyRQ airBookModifyRQ;
    @XmlElement(name = "AirReservation")
    protected AirReservation airReservation;

    /**
     * 获取pos属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POS }
     *     
     */
    public POS getPOS() {
        return pos;
    }

    /**
     * 设置pos属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POS }
     *     
     */
    public void setPOS(POS value) {
        this.pos = value;
    }

    /**
     * 获取airBookModifyRQ属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OTAAirBookModifyRQ.AirBookModifyRQ }
     *     
     */
    public OTAAirBookModifyRQ.AirBookModifyRQ getAirBookModifyRQ() {
        return airBookModifyRQ;
    }

    /**
     * 设置airBookModifyRQ属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OTAAirBookModifyRQ.AirBookModifyRQ }
     *     
     */
    public void setAirBookModifyRQ(OTAAirBookModifyRQ.AirBookModifyRQ value) {
        this.airBookModifyRQ = value;
    }

    /**
     * 获取airReservation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AirReservation }
     *     
     */
    public AirReservation getAirReservation() {
        return airReservation;
    }

    /**
     * 设置airReservation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AirReservation }
     *     
     */
    public void setAirReservation(AirReservation value) {
        this.airReservation = value;
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
     *         &lt;element ref="{http://espeed.travelsky.com}AirReservation" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="ModificationType" use="required" type="{http://espeed.travelsky.com}AirbookModifyType" />
     *       &lt;attribute name="ModificationInfo" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="CheckActionCode" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "airReservation"
    })
    public static class AirBookModifyRQ {

        @XmlElement(name = "AirReservation")
        protected AirReservation airReservation;
        @XmlAttribute(name = "ModificationType", required = true)
        protected AirbookModifyType modificationType;
        @XmlAttribute(name = "ModificationInfo")
        protected String modificationInfo;
        @XmlAttribute(name = "CheckActionCode")
        protected Boolean checkActionCode;

        /**
         * 获取airReservation属性的值。
         * 
         * @return
         *     possible object is
         *     {@link AirReservation }
         *     
         */
        public AirReservation getAirReservation() {
            return airReservation;
        }

        /**
         * 设置airReservation属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link AirReservation }
         *     
         */
        public void setAirReservation(AirReservation value) {
            this.airReservation = value;
        }

        /**
         * 获取modificationType属性的值。
         * 
         * @return
         *     possible object is
         *     {@link AirbookModifyType }
         *     
         */
        public AirbookModifyType getModificationType() {
            return modificationType;
        }

        /**
         * 设置modificationType属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link AirbookModifyType }
         *     
         */
        public void setModificationType(AirbookModifyType value) {
            this.modificationType = value;
        }

        /**
         * 获取modificationInfo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModificationInfo() {
            return modificationInfo;
        }

        /**
         * 设置modificationInfo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModificationInfo(String value) {
            this.modificationInfo = value;
        }

        /**
         * 获取checkActionCode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isCheckActionCode() {
            if (checkActionCode == null) {
                return false;
            } else {
                return checkActionCode;
            }
        }

        /**
         * 设置checkActionCode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setCheckActionCode(Boolean value) {
            this.checkActionCode = value;
        }

    }

}
