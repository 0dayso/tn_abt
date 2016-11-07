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
 * Describes the seat
 * 					attributes.
 * 
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="SeatNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SeatStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SeatPosition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "AirSeat")
public class AirSeat {

    @XmlAttribute(name = "SeatNumber")
    protected String seatNumber;
    @XmlAttribute(name = "SeatStatus")
    protected String seatStatus;
    @XmlAttribute(name = "SeatPosition")
    protected String seatPosition;

    /**
     * 获取seatNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatNumber() {
        return seatNumber;
    }

    /**
     * 设置seatNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatNumber(String value) {
        this.seatNumber = value;
    }

    /**
     * 获取seatStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatStatus() {
        return seatStatus;
    }

    /**
     * 设置seatStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatStatus(String value) {
        this.seatStatus = value;
    }

    /**
     * 获取seatPosition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatPosition() {
        return seatPosition;
    }

    /**
     * 设置seatPosition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatPosition(String value) {
        this.seatPosition = value;
    }

}
