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
 *         &lt;element ref="{http://espeed.travelsky.com}AirTraveler" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}SpecialReqDetails" minOccurs="0"/>
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
    "airTraveler",
    "specialReqDetails"
})
@XmlRootElement(name = "TravelerInfo")
public class TravelerInfo {

    @XmlElement(name = "AirTraveler")
    protected List<AirTraveler> airTraveler;
    @XmlElement(name = "SpecialReqDetails")
    protected SpecialReqDetails specialReqDetails;

    /**
     * Gets the value of the airTraveler property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the airTraveler property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAirTraveler().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AirTraveler }
     * 
     * 
     */
    public List<AirTraveler> getAirTraveler() {
        if (airTraveler == null) {
            airTraveler = new ArrayList<AirTraveler>();
        }
        return this.airTraveler;
    }

    /**
     * 获取specialReqDetails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SpecialReqDetails }
     *     
     */
    public SpecialReqDetails getSpecialReqDetails() {
        return specialReqDetails;
    }

    /**
     * 设置specialReqDetails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SpecialReqDetails }
     *     
     */
    public void setSpecialReqDetails(SpecialReqDetails value) {
        this.specialReqDetails = value;
    }

}
