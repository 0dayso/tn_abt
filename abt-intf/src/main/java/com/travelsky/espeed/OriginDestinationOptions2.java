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
 *         &lt;element ref="{http://espeed.travelsky.com}OriginDestinationOption2" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Filter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DepartureDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="DOW" type="{http://espeed.travelsky.com}DayOfWeekType" />
 *       &lt;attribute name="DepartureAirport" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ArrivalAirport" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EndDOW" type="{http://espeed.travelsky.com}DayOfWeekType" />
 *       &lt;attribute name="OriginDOW" type="{http://espeed.travelsky.com}DayOfWeekType" />
 *       &lt;attribute name="OriginDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "originDestinationOption"
})
@XmlRootElement(name = "OriginDestinationOptions")
public class OriginDestinationOptions2 {

    @XmlElement(name = "OriginDestinationOption", required = true)
    protected List<OriginDestinationOption2> originDestinationOption;
    @XmlAttribute(name = "RPH")
    protected String rph;
    @XmlAttribute(name = "Filter")
    protected String filter;
    @XmlAttribute(name = "DepartureDate")
    @XmlSchemaType(name = "date")
    protected String departureDate;
    @XmlAttribute(name = "DOW")
    protected DayOfWeekType dow;
    @XmlAttribute(name = "DepartureAirport")
    protected String departureAirport;
    @XmlAttribute(name = "ArrivalAirport")
    protected String arrivalAirport;
    @XmlAttribute(name = "EndDOW")
    protected DayOfWeekType endDOW;
    @XmlAttribute(name = "OriginDOW")
    protected DayOfWeekType originDOW;
    @XmlAttribute(name = "OriginDate")
    @XmlSchemaType(name = "date")
    protected String originDate;
    @XmlAttribute(name = "EndDate")
    @XmlSchemaType(name = "date")
    protected String endDate;

    /**
     * Gets the value of the originDestinationOption property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the originDestinationOption property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOriginDestinationOption().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OriginDestinationOption2 }
     * 
     * 
     */
    public List<OriginDestinationOption2> getOriginDestinationOption() {
        if (originDestinationOption == null) {
            originDestinationOption = new ArrayList<OriginDestinationOption2>();
        }
        return this.originDestinationOption;
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
     * 获取filter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilter() {
        return filter;
    }

    /**
     * 设置filter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilter(String value) {
        this.filter = value;
    }

    /**
     * 获取departureDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * 设置departureDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureDate(String value) {
        this.departureDate = value;
    }

    /**
     * 获取dow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DayOfWeekType }
     *     
     */
    public DayOfWeekType getDOW() {
        return dow;
    }

    /**
     * 设置dow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DayOfWeekType }
     *     
     */
    public void setDOW(DayOfWeekType value) {
        this.dow = value;
    }

    /**
     * 获取departureAirport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureAirport() {
        return departureAirport;
    }

    /**
     * 设置departureAirport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureAirport(String value) {
        this.departureAirport = value;
    }

    /**
     * 获取arrivalAirport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * 设置arrivalAirport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalAirport(String value) {
        this.arrivalAirport = value;
    }

    /**
     * 获取endDOW属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DayOfWeekType }
     *     
     */
    public DayOfWeekType getEndDOW() {
        return endDOW;
    }

    /**
     * 设置endDOW属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DayOfWeekType }
     *     
     */
    public void setEndDOW(DayOfWeekType value) {
        this.endDOW = value;
    }

    /**
     * 获取originDOW属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DayOfWeekType }
     *     
     */
    public DayOfWeekType getOriginDOW() {
        return originDOW;
    }

    /**
     * 设置originDOW属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DayOfWeekType }
     *     
     */
    public void setOriginDOW(DayOfWeekType value) {
        this.originDOW = value;
    }

    /**
     * 获取originDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginDate() {
        return originDate;
    }

    /**
     * 设置originDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginDate(String value) {
        this.originDate = value;
    }

    /**
     * 获取endDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

}
