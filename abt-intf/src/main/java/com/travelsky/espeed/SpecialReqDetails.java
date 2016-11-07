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
 *         &lt;element name="SeatRequests" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://espeed.travelsky.com}SeatRequest" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SpecialServiceRequests" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://espeed.travelsky.com}SpecialServiceRequest" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="OtherServiceInformations" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://espeed.travelsky.com}OtherServiceInformation" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Remarks" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://espeed.travelsky.com}Remark" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SpecialRemarks" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://espeed.travelsky.com}SpecialRemark" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
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
    "seatRequests",
    "specialServiceRequests",
    "otherServiceInformations",
    "remarks",
    "specialRemarks"
})
@XmlRootElement(name = "SpecialReqDetails")
public class SpecialReqDetails {

    @XmlElement(name = "SeatRequests")
    protected SpecialReqDetails.SeatRequests seatRequests;
    @XmlElement(name = "SpecialServiceRequests")
    protected SpecialReqDetails.SpecialServiceRequests specialServiceRequests;
    @XmlElement(name = "OtherServiceInformations")
    protected SpecialReqDetails.OtherServiceInformations otherServiceInformations;
    @XmlElement(name = "Remarks")
    protected SpecialReqDetails.Remarks remarks;
    @XmlElement(name = "SpecialRemarks")
    protected SpecialReqDetails.SpecialRemarks specialRemarks;

    /**
     * 获取seatRequests属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SpecialReqDetails.SeatRequests }
     *     
     */
    public SpecialReqDetails.SeatRequests getSeatRequests() {
        return seatRequests;
    }

    /**
     * 设置seatRequests属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SpecialReqDetails.SeatRequests }
     *     
     */
    public void setSeatRequests(SpecialReqDetails.SeatRequests value) {
        this.seatRequests = value;
    }

    /**
     * 获取specialServiceRequests属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SpecialReqDetails.SpecialServiceRequests }
     *     
     */
    public SpecialReqDetails.SpecialServiceRequests getSpecialServiceRequests() {
        return specialServiceRequests;
    }

    /**
     * 设置specialServiceRequests属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SpecialReqDetails.SpecialServiceRequests }
     *     
     */
    public void setSpecialServiceRequests(SpecialReqDetails.SpecialServiceRequests value) {
        this.specialServiceRequests = value;
    }

    /**
     * 获取otherServiceInformations属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SpecialReqDetails.OtherServiceInformations }
     *     
     */
    public SpecialReqDetails.OtherServiceInformations getOtherServiceInformations() {
        return otherServiceInformations;
    }

    /**
     * 设置otherServiceInformations属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SpecialReqDetails.OtherServiceInformations }
     *     
     */
    public void setOtherServiceInformations(SpecialReqDetails.OtherServiceInformations value) {
        this.otherServiceInformations = value;
    }

    /**
     * 获取remarks属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SpecialReqDetails.Remarks }
     *     
     */
    public SpecialReqDetails.Remarks getRemarks() {
        return remarks;
    }

    /**
     * 设置remarks属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SpecialReqDetails.Remarks }
     *     
     */
    public void setRemarks(SpecialReqDetails.Remarks value) {
        this.remarks = value;
    }

    /**
     * 获取specialRemarks属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SpecialReqDetails.SpecialRemarks }
     *     
     */
    public SpecialReqDetails.SpecialRemarks getSpecialRemarks() {
        return specialRemarks;
    }

    /**
     * 设置specialRemarks属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SpecialReqDetails.SpecialRemarks }
     *     
     */
    public void setSpecialRemarks(SpecialReqDetails.SpecialRemarks value) {
        this.specialRemarks = value;
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
     *         &lt;element ref="{http://espeed.travelsky.com}OtherServiceInformation" maxOccurs="unbounded"/>
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
        "otherServiceInformation"
    })
    public static class OtherServiceInformations {

        @XmlElement(name = "OtherServiceInformation", required = true)
        protected List<OtherServiceInformation> otherServiceInformation;

        /**
         * Gets the value of the otherServiceInformation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otherServiceInformation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtherServiceInformation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OtherServiceInformation }
         * 
         * 
         */
        public List<OtherServiceInformation> getOtherServiceInformation() {
            if (otherServiceInformation == null) {
                otherServiceInformation = new ArrayList<OtherServiceInformation>();
            }
            return this.otherServiceInformation;
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
     *         &lt;element ref="{http://espeed.travelsky.com}Remark" maxOccurs="unbounded"/>
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
        "remark"
    })
    public static class Remarks {

        @XmlElement(name = "Remark", required = true)
        protected List<Remark> remark;

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
     *         &lt;element ref="{http://espeed.travelsky.com}SeatRequest" maxOccurs="unbounded"/>
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
        "seatRequest"
    })
    public static class SeatRequests {

        @XmlElement(name = "SeatRequest", required = true)
        protected List<SeatRequest> seatRequest;

        /**
         * Gets the value of the seatRequest property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the seatRequest property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSeatRequest().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SeatRequest }
         * 
         * 
         */
        public List<SeatRequest> getSeatRequest() {
            if (seatRequest == null) {
                seatRequest = new ArrayList<SeatRequest>();
            }
            return this.seatRequest;
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
     *         &lt;element ref="{http://espeed.travelsky.com}SpecialRemark" maxOccurs="unbounded"/>
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
        "specialRemark"
    })
    public static class SpecialRemarks {

        @XmlElement(name = "SpecialRemark", required = true)
        protected List<SpecialRemark> specialRemark;

        /**
         * Gets the value of the specialRemark property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the specialRemark property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSpecialRemark().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SpecialRemark }
         * 
         * 
         */
        public List<SpecialRemark> getSpecialRemark() {
            if (specialRemark == null) {
                specialRemark = new ArrayList<SpecialRemark>();
            }
            return this.specialRemark;
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
     *         &lt;element ref="{http://espeed.travelsky.com}SpecialServiceRequest" maxOccurs="unbounded"/>
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
        "specialServiceRequest"
    })
    public static class SpecialServiceRequests {

        @XmlElement(name = "SpecialServiceRequest", required = true)
        protected List<SpecialServiceRequest> specialServiceRequest;

        /**
         * Gets the value of the specialServiceRequest property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the specialServiceRequest property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSpecialServiceRequest().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SpecialServiceRequest }
         * 
         * 
         */
        public List<SpecialServiceRequest> getSpecialServiceRequest() {
            if (specialServiceRequest == null) {
                specialServiceRequest = new ArrayList<SpecialServiceRequest>();
            }
            return this.specialServiceRequest;
        }

    }

}
