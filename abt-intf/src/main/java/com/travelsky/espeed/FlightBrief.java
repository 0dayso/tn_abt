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
 *         &lt;element ref="{http://espeed.travelsky.com}FlightLegBrief" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StopQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="DepartureDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "flightLegBrief"
})
@XmlRootElement(name = "FlightBrief")
public class FlightBrief {

    @XmlElement(name = "FlightLegBrief", required = true)
    protected List<FlightLegBrief> flightLegBrief;
    @XmlAttribute(name = "StopQuantity")
    protected BigDecimal stopQuantity;
    @XmlAttribute(name = "DepartureDate")
    @XmlSchemaType(name = "date")
    protected String departureDate;

    /**
     * Gets the value of the flightLegBrief property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightLegBrief property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightLegBrief().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightLegBrief }
     * 
     * 
     */
    public List<FlightLegBrief> getFlightLegBrief() {
        if (flightLegBrief == null) {
            flightLegBrief = new ArrayList<FlightLegBrief>();
        }
        return this.flightLegBrief;
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

}
