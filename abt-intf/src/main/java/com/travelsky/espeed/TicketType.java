//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 04:03:21 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TicketType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="TicketType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="eTicket"/>
 *     &lt;enumeration value="Paper"/>
 *     &lt;enumeration value="EMD"/>
 *     &lt;enumeration value="MCO"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TicketType")
@XmlEnum
public enum TicketType {

    @XmlEnumValue("eTicket")
    E_TICKET("eTicket"),
    @XmlEnumValue("Paper")
    PAPER("Paper"),
    EMD("EMD"),
    MCO("MCO"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown");
    private final String value;

    TicketType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TicketType fromValue(String v) {
        for (TicketType c: TicketType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
