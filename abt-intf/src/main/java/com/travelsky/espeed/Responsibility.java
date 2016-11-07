//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

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
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="CRS" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OfficeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PNRno" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Responsibility")
public class Responsibility {

    @XmlAttribute(name = "RPH")
    protected Integer rph;
    @XmlAttribute(name = "CRS")
    protected String crs;
    @XmlAttribute(name = "OfficeCode")
    protected String officeCode;
    @XmlAttribute(name = "Remark")
    protected String remark;
    @XmlAttribute(name = "PNRno")
    protected String pnRno;

    /**
     * 获取rph属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRPH() {
        return rph;
    }

    /**
     * 设置rph属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRPH(Integer value) {
        this.rph = value;
    }

    /**
     * 获取crs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCRS() {
        return crs;
    }

    /**
     * 设置crs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCRS(String value) {
        this.crs = value;
    }

    /**
     * 获取officeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeCode() {
        return officeCode;
    }

    /**
     * 设置officeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeCode(String value) {
        this.officeCode = value;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * 获取pnRno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPNRno() {
        return pnRno;
    }

    /**
     * 设置pnRno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPNRno(String value) {
        this.pnRno = value;
    }

}
