//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:16:23 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PassengerType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="PassengerType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADT"/>
 *     &lt;enumeration value="CHD"/>
 *     &lt;enumeration value="CHD_RULE"/>
 *     &lt;enumeration value="UM"/>
 *     &lt;enumeration value="INF"/>
 *     &lt;enumeration value="JC"/>
 *     &lt;enumeration value="GM"/>
 *     &lt;enumeration value="AA"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PassengerType")
@XmlEnum
public enum PassengerType {


    /**
     * 成人
     * 
     */
    ADT,

    /**
     * 儿童
     * 
     */
    CHD,

    /**
     * 需要按规则处理的儿童
     * 
     */
    CHD_RULE,

    /**
     * 无人陪伴儿童（旅客）
     * 
     */
    UM,

    /**
     * 婴儿
     * 
     */
    INF,

    /**
     * 因公带伤警察
     * 
     */
    JC,

    /**
     * 伤残军人
     * 
     */
    GM,

    /**
     * 用于PAT:A*AA指令的特殊旅客类型
     * 
     */
    AA,
    OTHER,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static PassengerType fromValue(String v) {
        return valueOf(v);
    }

}
