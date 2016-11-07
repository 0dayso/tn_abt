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
 *         &lt;element name="PricingSolutionSequenceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FlightSegmentRPH" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="BookingClass" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
    "pricingSolutionSequenceNumber",
    "flightSegmentRPH",
    "bookingClass"
})
@XmlRootElement(name = "PricingItineraryBindingOption")
public class PricingItineraryBindingOption {

    @XmlElement(name = "PricingSolutionSequenceNumber", required = true)
    protected String pricingSolutionSequenceNumber;
    @XmlElement(name = "FlightSegmentRPH", required = true)
    protected List<String> flightSegmentRPH;
    @XmlElement(name = "BookingClass", required = true)
    protected List<String> bookingClass;

    /**
     * 获取pricingSolutionSequenceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricingSolutionSequenceNumber() {
        return pricingSolutionSequenceNumber;
    }

    /**
     * 设置pricingSolutionSequenceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricingSolutionSequenceNumber(String value) {
        this.pricingSolutionSequenceNumber = value;
    }

    /**
     * Gets the value of the flightSegmentRPH property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightSegmentRPH property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightSegmentRPH().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFlightSegmentRPH() {
        if (flightSegmentRPH == null) {
            flightSegmentRPH = new ArrayList<String>();
        }
        return this.flightSegmentRPH;
    }

    /**
     * Gets the value of the bookingClass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingClass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingClass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBookingClass() {
        if (bookingClass == null) {
            bookingClass = new ArrayList<String>();
        }
        return this.bookingClass;
    }

}
