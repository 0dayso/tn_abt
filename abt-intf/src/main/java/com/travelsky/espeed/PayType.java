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
 * <p>PayType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="PayType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CASH"/>
 *     &lt;enumeration value="CREDIT_CARD"/>
 *     &lt;enumeration value="DEBIT_CARD"/>
 *     &lt;enumeration value="CHECK"/>
 *     &lt;enumeration value="DEPOSIT"/>
 *     &lt;enumeration value="PREPAY"/>
 *     &lt;enumeration value="GUARANTEE"/>
 *     &lt;enumeration value="MCO"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PayType")
@XmlEnum
public enum PayType {

    CASH,
    CREDIT_CARD,
    DEBIT_CARD,
    CHECK,
    DEPOSIT,
    PREPAY,
    GUARANTEE,
    MCO,
    OTHER,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static PayType fromValue(String v) {
        return valueOf(v);
    }

}
