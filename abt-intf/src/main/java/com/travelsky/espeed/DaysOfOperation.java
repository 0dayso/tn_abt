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
 *       &lt;sequence>
 *         &lt;element ref="{http://espeed.travelsky.com}OperationSchedule"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Start" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="End" type="{http://www.w3.org/2001/XMLSchema}time" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "operationSchedule"
})
@XmlRootElement(name = "DaysOfOperation")
public class DaysOfOperation {

    @XmlElement(name = "OperationSchedule", required = true)
    protected OperationSchedule operationSchedule;
    @XmlAttribute(name = "Start")
    @XmlSchemaType(name = "time")
    protected String start;
    @XmlAttribute(name = "End")
    @XmlSchemaType(name = "time")
    protected String end;

    /**
     * 获取operationSchedule属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OperationSchedule }
     *     
     */
    public OperationSchedule getOperationSchedule() {
        return operationSchedule;
    }

    /**
     * 设置operationSchedule属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OperationSchedule }
     *     
     */
    public void setOperationSchedule(OperationSchedule value) {
        this.operationSchedule = value;
    }

    /**
     * 获取start属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStart() {
        return start;
    }

    /**
     * 设置start属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStart(String value) {
        this.start = value;
    }

    /**
     * 获取end属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnd() {
        return end;
    }

    /**
     * 设置end属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnd(String value) {
        this.end = value;
    }

}
