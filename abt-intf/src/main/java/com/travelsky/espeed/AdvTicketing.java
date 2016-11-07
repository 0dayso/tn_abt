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
 *       &lt;attribute name="MaxFromDepartPeriod" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="MaxFromDepartUnit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MinFromDepartPeriod" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="MinFromDepartUnit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "AdvTicketing")
public class AdvTicketing {

    @XmlAttribute(name = "MaxFromDepartPeriod")
    protected BigDecimal maxFromDepartPeriod;
    @XmlAttribute(name = "MaxFromDepartUnit")
    protected String maxFromDepartUnit;
    @XmlAttribute(name = "MinFromDepartPeriod")
    protected BigDecimal minFromDepartPeriod;
    @XmlAttribute(name = "MinFromDepartUnit")
    protected String minFromDepartUnit;

    /**
     * 获取maxFromDepartPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxFromDepartPeriod() {
        return maxFromDepartPeriod;
    }

    /**
     * 设置maxFromDepartPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxFromDepartPeriod(BigDecimal value) {
        this.maxFromDepartPeriod = value;
    }

    /**
     * 获取maxFromDepartUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxFromDepartUnit() {
        return maxFromDepartUnit;
    }

    /**
     * 设置maxFromDepartUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxFromDepartUnit(String value) {
        this.maxFromDepartUnit = value;
    }

    /**
     * 获取minFromDepartPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinFromDepartPeriod() {
        return minFromDepartPeriod;
    }

    /**
     * 设置minFromDepartPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinFromDepartPeriod(BigDecimal value) {
        this.minFromDepartPeriod = value;
    }

    /**
     * 获取minFromDepartUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinFromDepartUnit() {
        return minFromDepartUnit;
    }

    /**
     * 设置minFromDepartUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinFromDepartUnit(String value) {
        this.minFromDepartUnit = value;
    }

}
