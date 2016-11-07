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
 * <p>TransactionType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AFD"/>
 *     &lt;enumeration value="PFD"/>
 *     &lt;enumeration value="NFD"/>
 *     &lt;enumeration value="FN"/>
 *     &lt;enumeration value="FR"/>
 *     &lt;enumeration value="PAT"/>
 *     &lt;enumeration value="SFC"/>
 *     &lt;enumeration value="NFP"/>
 *     &lt;enumeration value="LFS"/>
 *     &lt;enumeration value="LFR"/>
 *     &lt;enumeration value="FLS"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransactionType")
@XmlEnum
public enum TransactionType {

    AFD,
    PFD,
    NFD,
    FN,
    FR,
    PAT,
    SFC,
    NFP,
    LFS,
    LFR,
    FLS,
    OTHER,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static TransactionType fromValue(String v) {
        return valueOf(v);
    }

}
