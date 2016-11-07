//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ObjectType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ObjectType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Order"/>
 *     &lt;enumeration value="PNR"/>
 *     &lt;enumeration value="Ticket"/>
 *     &lt;enumeration value="Others"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ObjectType")
@XmlEnum
public enum ObjectType {

    @XmlEnumValue("Order")
    ORDER("Order"),
    PNR("PNR"),
    @XmlEnumValue("Ticket")
    TICKET("Ticket"),
    @XmlEnumValue("Others")
    OTHERS("Others");
    private final String value;

    ObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjectType fromValue(String v) {
        for (ObjectType c: ObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
