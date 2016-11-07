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
 *         &lt;element ref="{http://espeed.travelsky.com}FlightLegInfo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TotalMiles" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="TotalFlightTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="TotalGroundTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="TotalTripTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="MessageStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "flightLegInfo"
})
@XmlRootElement(name = "FlightInfoDetails")
public class FlightInfoDetails {

    @XmlElement(name = "FlightLegInfo", required = true)
    protected List<FlightLegInfo> flightLegInfo;
    @XmlAttribute(name = "FlightNumber")
    protected String flightNumber;
    @XmlAttribute(name = "TotalMiles")
    protected BigDecimal totalMiles;
    @XmlAttribute(name = "TotalFlightTime")
    protected String totalFlightTime;
    @XmlAttribute(name = "TotalGroundTime")
    protected String totalGroundTime;
    @XmlAttribute(name = "TotalTripTime")
    protected String totalTripTime;
    @XmlAttribute(name = "MessageStatus")
    protected String messageStatus;

    /**
     * Gets the value of the flightLegInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightLegInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightLegInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightLegInfo }
     * 
     * 
     */
    public List<FlightLegInfo> getFlightLegInfo() {
        if (flightLegInfo == null) {
            flightLegInfo = new ArrayList<FlightLegInfo>();
        }
        return this.flightLegInfo;
    }

    /**
     * 获取flightNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * 设置flightNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightNumber(String value) {
        this.flightNumber = value;
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
     * 获取messageStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageStatus() {
        return messageStatus;
    }

    /**
     * 设置messageStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageStatus(String value) {
        this.messageStatus = value;
    }

}
