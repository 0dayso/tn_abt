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
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="Night" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubClass" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PseudoCityCode" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ResBookDesigCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ResBookDesigQuantity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "night",
    "subClass",
    "pseudoCityCode"
})
@XmlRootElement(name = "BookingClassAvail")
public class BookingClassAvail {

    @XmlElement(name = "Night")
    protected String night;
    @XmlElement(name = "SubClass")
    protected List<String> subClass;
    @XmlElement(name = "PseudoCityCode")
    protected List<String> pseudoCityCode;
    @XmlAttribute(name = "RPH")
    protected String rph;
    @XmlAttribute(name = "ResBookDesigCode")
    protected String resBookDesigCode;
    @XmlAttribute(name = "ResBookDesigQuantity")
    protected String resBookDesigQuantity;

    /**
     * 获取night属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNight() {
        return night;
    }

    /**
     * 设置night属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNight(String value) {
        this.night = value;
    }

    /**
     * Gets the value of the subClass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subClass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubClass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSubClass() {
        if (subClass == null) {
            subClass = new ArrayList<String>();
        }
        return this.subClass;
    }

    /**
     * Gets the value of the pseudoCityCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pseudoCityCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPseudoCityCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPseudoCityCode() {
        if (pseudoCityCode == null) {
            pseudoCityCode = new ArrayList<String>();
        }
        return this.pseudoCityCode;
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
     * 获取resBookDesigCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResBookDesigCode() {
        return resBookDesigCode;
    }

    /**
     * 设置resBookDesigCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResBookDesigCode(String value) {
        this.resBookDesigCode = value;
    }

    /**
     * 获取resBookDesigQuantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResBookDesigQuantity() {
        return resBookDesigQuantity;
    }

    /**
     * 设置resBookDesigQuantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResBookDesigQuantity(String value) {
        this.resBookDesigQuantity = value;
    }

}
