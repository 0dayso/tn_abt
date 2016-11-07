//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
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
 *         &lt;element ref="{http://espeed.travelsky.com}ItinTotalFare" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}FareInfos" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ScheduleValidStartDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="ScheduleValidEndDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "itinTotalFare",
    "fareInfos"
})
@XmlRootElement(name = "AirItineraryPricingInfo")
public class AirItineraryPricingInfo {

    @XmlElement(name = "ItinTotalFare")
    protected List<ItinTotalFare> itinTotalFare;
    @XmlElement(name = "FareInfos")
    protected FareInfos fareInfos;
    @XmlAttribute(name = "ScheduleValidStartDate")
    @XmlSchemaType(name = "date")
    protected String scheduleValidStartDate;
    @XmlAttribute(name = "ScheduleValidEndDate")
    @XmlSchemaType(name = "date")
    protected String scheduleValidEndDate;

    /**
     * Gets the value of the itinTotalFare property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itinTotalFare property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItinTotalFare().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItinTotalFare }
     * 
     * 
     */
    public List<ItinTotalFare> getItinTotalFare() {
        if (itinTotalFare == null) {
            itinTotalFare = new ArrayList<ItinTotalFare>();
        }
        return this.itinTotalFare;
    }

    /**
     * 获取fareInfos属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareInfos }
     *     
     */
    public FareInfos getFareInfos() {
        return fareInfos;
    }

    /**
     * 设置fareInfos属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareInfos }
     *     
     */
    public void setFareInfos(FareInfos value) {
        this.fareInfos = value;
    }

    /**
     * 获取scheduleValidStartDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduleValidStartDate() {
        return scheduleValidStartDate;
    }

    /**
     * 设置scheduleValidStartDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduleValidStartDate(String value) {
        this.scheduleValidStartDate = value;
    }

    /**
     * 获取scheduleValidEndDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduleValidEndDate() {
        return scheduleValidEndDate;
    }

    /**
     * 设置scheduleValidEndDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduleValidEndDate(String value) {
        this.scheduleValidEndDate = value;
    }

}
