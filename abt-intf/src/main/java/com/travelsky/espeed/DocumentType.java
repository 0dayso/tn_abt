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
 * <p>DocumentType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="DocumentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NI"/>
 *     &lt;enumeration value="PP"/>
 *     &lt;enumeration value="MI"/>
 *     &lt;enumeration value="DL"/>
 *     &lt;enumeration value="FF"/>
 *     &lt;enumeration value="CC"/>
 *     &lt;enumeration value="HV"/>
 *     &lt;enumeration value="MT"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DocumentType")
@XmlEnum
public enum DocumentType {


    /**
     * national identity 身份证
     * 
     */
    NI,

    /**
     * passport 护照
     * 
     */
    PP,

    /**
     * military identification 军官证
     * 
     */
    MI,

    /**
     * drivers license 驾照
     * 
     */
    DL,

    /**
     * frequent flier 常客卡
     * 
     */
    FF,

    /**
     * credit card 信用卡
     * 
     */
    CC,

    /**
     * home visit permit 回乡证
     * 
     */
    HV,

    /**
     * mainland travel permits for taiwan residents 台胞证
     * 					
     * 
     */
    MT,
    OTHER,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static DocumentType fromValue(String v) {
        return valueOf(v);
    }

}
