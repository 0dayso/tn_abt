//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ETicketType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ETicketType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AIRLINE_DOMESTIC_ETICKET"/>
 *     &lt;enumeration value="AIRLINE_INTERNATIONAL_ETICKET"/>
 *     &lt;enumeration value="BSP_DOMESTIC_ETICKET"/>
 *     &lt;enumeration value="BSP_INTERNATIONAL_ETICKET"/>
 *     &lt;enumeration value="OTHER_TYPE_ETICKET"/>
 *     &lt;enumeration value="UNDEFINED_ETICKET_TYPE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ETicketType")
@XmlEnum
public enum ETicketType {

    AIRLINE_DOMESTIC_ETICKET,
    AIRLINE_INTERNATIONAL_ETICKET,
    BSP_DOMESTIC_ETICKET,
    BSP_INTERNATIONAL_ETICKET,
    OTHER_TYPE_ETICKET,
    UNDEFINED_ETICKET_TYPE;

    public String value() {
        return name();
    }

    public static ETicketType fromValue(String v) {
        return valueOf(v);
    }

}
