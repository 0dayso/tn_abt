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
 *         &lt;element ref="{http://espeed.travelsky.com}ItinTotalFare" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}PTC_FareBreakdowns" minOccurs="0"/>
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
    "itinTotalFare",
    "ptcFareBreakdowns"
})
@XmlRootElement(name = "PriceInfo")
public class PriceInfo {

    @XmlElement(name = "ItinTotalFare")
    protected List<ItinTotalFare> itinTotalFare;
    @XmlElement(name = "PTC_FareBreakdowns")
    protected PTCFareBreakdowns ptcFareBreakdowns;

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
     * 获取ptcFareBreakdowns属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PTCFareBreakdowns }
     *     
     */
    public PTCFareBreakdowns getPTCFareBreakdowns() {
        return ptcFareBreakdowns;
    }

    /**
     * 设置ptcFareBreakdowns属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PTCFareBreakdowns }
     *     
     */
    public void setPTCFareBreakdowns(PTCFareBreakdowns value) {
        this.ptcFareBreakdowns = value;
    }

}
