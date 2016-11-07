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
 * <p>AirbookModifyType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="AirbookModifyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PNR_CANCEL"/>
 *     &lt;enumeration value="SEGMENT_ADD"/>
 *     &lt;enumeration value="SEGMENT_MODIFY"/>
 *     &lt;enumeration value="SEGMENT_CANCEL"/>
 *     &lt;enumeration value="PASSENGER_DELETE"/>
 *     &lt;enumeration value="PASSENGER_FOID_MODIFY"/>
 *     &lt;enumeration value="PASSENGER_INFO_MODIFY"/>
 *     &lt;enumeration value="REMARK_ADD"/>
 *     &lt;enumeration value="REMARK_MODIFY"/>
 *     &lt;enumeration value="REMARK_DELETE"/>
 *     &lt;enumeration value="SSR_ADD"/>
 *     &lt;enumeration value="OSI_ADD"/>
 *     &lt;enumeration value="OSI_MODIFY"/>
 *     &lt;enumeration value="OSI_DELETE"/>
 *     &lt;enumeration value="TC_ADD"/>
 *     &lt;enumeration value="PNR_SPLIT"/>
 *     &lt;enumeration value="ITEM_CANCEL"/>
 *     &lt;enumeration value="SEGMENT_CONFIRM"/>
 *     &lt;enumeration value="SEGMENT_NO"/>
 *     &lt;enumeration value="SEGMENT_RECONFIRM"/>
 *     &lt;enumeration value="EI_ADD"/>
 *     &lt;enumeration value="SEGMENT_GROUND_ADD"/>
 *     &lt;enumeration value="SEGMENT_GROUND_MODIFY"/>
 *     &lt;enumeration value="SEGMENT_GROUND_DELETE"/>
 *     &lt;enumeration value="GROUP_PASSENGER_ADD"/>
 *     &lt;enumeration value="SSR_DELETE"/>
 *     &lt;enumeration value="SSR_MODIFY"/>
 *     &lt;enumeration value="INFT_ADD_TYPE"/>
 *     &lt;enumeration value="TKTL_MODIFY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AirbookModifyType")
@XmlEnum
public enum AirbookModifyType {

    PNR_CANCEL,
    SEGMENT_ADD,
    SEGMENT_MODIFY,
    SEGMENT_CANCEL,
    PASSENGER_DELETE,
    PASSENGER_FOID_MODIFY,
    PASSENGER_INFO_MODIFY,
    REMARK_ADD,
    REMARK_MODIFY,
    REMARK_DELETE,
    SSR_ADD,
    OSI_ADD,
    OSI_MODIFY,
    OSI_DELETE,
    TC_ADD,
    PNR_SPLIT,
    ITEM_CANCEL,
    SEGMENT_CONFIRM,
    SEGMENT_NO,
    SEGMENT_RECONFIRM,
    EI_ADD,
    SEGMENT_GROUND_ADD,
    SEGMENT_GROUND_MODIFY,
    SEGMENT_GROUND_DELETE,
    GROUP_PASSENGER_ADD,
    SSR_DELETE,
    SSR_MODIFY,
    INFT_ADD_TYPE,
    TKTL_MODIFY;

    public String value() {
        return name();
    }

    public static AirbookModifyType fromValue(String v) {
        return valueOf(v);
    }

}
