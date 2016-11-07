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
 *         &lt;element ref="{http://espeed.travelsky.com}FlightLegDetails" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TotalFlightTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="TotalGroundTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="TotalTripTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="TotalMiles" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="StopQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "flightLegDetails"
})
@XmlRootElement(name = "FlightDetails")
public class FlightDetails {

    @XmlElement(name = "FlightLegDetails", required = true)
    protected List<FlightLegDetails> flightLegDetails;
    @XmlAttribute(name = "TotalFlightTime")
    protected String totalFlightTime;
    @XmlAttribute(name = "TotalGroundTime")
    protected String totalGroundTime;
    @XmlAttribute(name = "TotalTripTime")
    protected String totalTripTime;
    @XmlAttribute(name = "TotalMiles")
    protected BigDecimal totalMiles;
    @XmlAttribute(name = "StopQuantity")
    protected BigDecimal stopQuantity;

    /**
     * Gets the value of the flightLegDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightLegDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightLegDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightLegDetails }
     * 
     * 
     */
    public List<FlightLegDetails> getFlightLegDetails() {
        if (flightLegDetails == null) {
            flightLegDetails = new ArrayList<FlightLegDetails>();
        }
        return this.flightLegDetails;
    }

    /**
     * 获取totalFlightTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalFlightTime() {
        return totalFlightTime;
    }

    /**
     * 设置totalFlightTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalFlightTime(String value) {
        this.totalFlightTime = value;
    }

    /**
     * 获取totalGroundTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalGroundTime() {
        return totalGroundTime;
    }

    /**
     * 设置totalGroundTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalGroundTime(String value) {
        this.totalGroundTime = value;
    }

    /**
     * 获取totalTripTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalTripTime() {
        return totalTripTime;
    }

    /**
     * 设置totalTripTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalTripTime(String value) {
        this.totalTripTime = value;
    }

    /**
     * 获取totalMiles属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalMiles() {
        return totalMiles;
    }

    /**
     * 设置totalMiles属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalMiles(BigDecimal value) {
        this.totalMiles = value;
    }

    /**
     * 获取stopQuantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStopQuantity() {
        return stopQuantity;
    }

    /**
     * 设置stopQuantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStopQuantity(BigDecimal value) {
        this.stopQuantity = value;
    }

}
