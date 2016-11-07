//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:30:57 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;
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
 *         &lt;element name="FlightSegments" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://espeed.travelsky.com}FlightSegment2" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://espeed.travelsky.com}OriginDestinationOptions2" maxOccurs="unbounded"/>
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
    "flightSegments",
    "originDestinationOptions"
})
@XmlRootElement(name = "AirItinerary")
public class AirItinerary2 {

    @XmlElement(name = "FlightSegments")
    protected AirItinerary2.FlightSegments flightSegments;
    @XmlElement(name = "OriginDestinationOptions", required = true)
    protected List<OriginDestinationOptions2> originDestinationOptions;

    /**
     * 获取flightSegments属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AirItinerary2.FlightSegments }
     *     
     */
    public AirItinerary2.FlightSegments getFlightSegments() {
        return flightSegments;
    }

    /**
     * 设置flightSegments属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AirItinerary2.FlightSegments }
     *     
     */
    public void setFlightSegments(AirItinerary2.FlightSegments value) {
        this.flightSegments = value;
    }

    /**
     * Gets the value of the originDestinationOptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the originDestinationOptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOriginDestinationOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OriginDestinationOptions2 }
     * 
     * 
     */
    public List<OriginDestinationOptions2> getOriginDestinationOptions() {
        if (originDestinationOptions == null) {
            originDestinationOptions = new ArrayList<OriginDestinationOptions2>();
        }
        return this.originDestinationOptions;
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
     *         &lt;element ref="{http://espeed.travelsky.com}FlightSegment2" maxOccurs="unbounded"/>
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
        "flightSegment"
    })
    public static class FlightSegments {

        @XmlElement(name = "FlightSegment", required = true)
        protected List<FlightSegment2> flightSegment;

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
         * {@link FlightSegment2 }
         * 
         * 
         */
        public List<FlightSegment2> getFlightSegment() {
            if (flightSegment == null) {
                flightSegment = new ArrayList<FlightSegment2>();
            }
            return this.flightSegment;
        }

    }

}
