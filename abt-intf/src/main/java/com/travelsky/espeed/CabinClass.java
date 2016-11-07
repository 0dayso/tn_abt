//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import java.math.BigDecimal;
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
 *         &lt;element ref="{http://espeed.travelsky.com}AirRows"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CabinType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="StartingRow" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="EndingRow" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airRows"
})
@XmlRootElement(name = "CabinClass")
public class CabinClass {

    @XmlElement(name = "AirRows", required = true)
    protected AirRows airRows;
    @XmlAttribute(name = "CabinType", required = true)
    protected String cabinType;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "StartingRow")
    protected BigDecimal startingRow;
    @XmlAttribute(name = "EndingRow")
    protected BigDecimal endingRow;

    /**
     * 获取airRows属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AirRows }
     *     
     */
    public AirRows getAirRows() {
        return airRows;
    }

    /**
     * 设置airRows属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AirRows }
     *     
     */
    public void setAirRows(AirRows value) {
        this.airRows = value;
    }

    /**
     * 获取cabinType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabinType() {
        return cabinType;
    }

    /**
     * 设置cabinType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabinType(String value) {
        this.cabinType = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取startingRow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartingRow() {
        return startingRow;
    }

    /**
     * 设置startingRow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartingRow(BigDecimal value) {
        this.startingRow = value;
    }

    /**
     * 获取endingRow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEndingRow() {
        return endingRow;
    }

    /**
     * 设置endingRow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEndingRow(BigDecimal value) {
        this.endingRow = value;
    }

}
