//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://espeed.travelsky.com}OffBlock" minOccurs="0"/>
 *         &lt;element ref="{http://espeed.travelsky.com}Airborne" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Scheduled" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="Estimated" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="Actual" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "offBlock",
    "airborne"
})
@XmlRootElement(name = "DepartureDateTime")
public class DepartureDateTime {

    @XmlElement(name = "OffBlock")
    protected OffBlock offBlock;
    @XmlElement(name = "Airborne")
    protected Airborne airborne;
    @XmlAttribute(name = "Scheduled")
    @XmlSchemaType(name = "dateTime")
    protected String scheduled;
    @XmlAttribute(name = "Estimated")
    @XmlSchemaType(name = "dateTime")
    protected String estimated;
    @XmlAttribute(name = "Actual")
    @XmlSchemaType(name = "dateTime")
    protected String actual;
    @XmlAttribute(name = "ReasonCode")
    protected String reasonCode;

    /**
     * 获取offBlock属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OffBlock }
     *     
     */
    public OffBlock getOffBlock() {
        return offBlock;
    }

    /**
     * 设置offBlock属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OffBlock }
     *     
     */
    public void setOffBlock(OffBlock value) {
        this.offBlock = value;
    }

    /**
     * 获取airborne属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Airborne }
     *     
     */
    public Airborne getAirborne() {
        return airborne;
    }

    /**
     * 设置airborne属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Airborne }
     *     
     */
    public void setAirborne(Airborne value) {
        this.airborne = value;
    }

    /**
     * 获取scheduled属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduled() {
        return scheduled;
    }

    /**
     * 设置scheduled属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduled(String value) {
        this.scheduled = value;
    }

    /**
     * 获取estimated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstimated() {
        return estimated;
    }

    /**
     * 设置estimated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstimated(String value) {
        this.estimated = value;
    }

    /**
     * 获取actual属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActual() {
        return actual;
    }

    /**
     * 设置actual属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActual(String value) {
        this.actual = value;
    }

    /**
     * 获取reasonCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * 设置reasonCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonCode(String value) {
        this.reasonCode = value;
    }

}
