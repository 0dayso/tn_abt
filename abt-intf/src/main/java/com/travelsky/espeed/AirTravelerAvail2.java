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
 *         &lt;element ref="{http://espeed.travelsky.com}PassengerTypeQuantity2" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://espeed.travelsky.com}AirTraveler2" minOccurs="0"/>
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
    "passengerTypeQuantity",
    "airTraveler"
})
@XmlRootElement(name = "AirTravelerAvail")
public class AirTravelerAvail2 {

    @XmlElement(name = "PassengerTypeQuantity", required = true)
    protected List<PassengerTypeQuantity2> passengerTypeQuantity;
    @XmlElement(name = "AirTraveler")
    protected AirTraveler2 airTraveler;

    /**
     * Gets the value of the passengerTypeQuantity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the passengerTypeQuantity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPassengerTypeQuantity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PassengerTypeQuantity2 }
     * 
     * 
     */
    public List<PassengerTypeQuantity2> getPassengerTypeQuantity() {
        if (passengerTypeQuantity == null) {
            passengerTypeQuantity = new ArrayList<PassengerTypeQuantity2>();
        }
        return this.passengerTypeQuantity;
    }

    /**
     * 获取airTraveler属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AirTraveler2 }
     *     
     */
    public AirTraveler2 getAirTraveler() {
        return airTraveler;
    }

    /**
     * 设置airTraveler属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AirTraveler2 }
     *     
     */
    public void setAirTraveler(AirTraveler2 value) {
        this.airTraveler = value;
    }

}
