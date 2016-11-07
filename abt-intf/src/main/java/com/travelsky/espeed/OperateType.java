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
 * <p>OperateType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="OperateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AddOrder"/>
 *     &lt;enumeration value="UpdateOrder"/>
 *     &lt;enumeration value="CancelOrder"/>
 *     &lt;enumeration value="Book"/>
 *     &lt;enumeration value="BookModify"/>
 *     &lt;enumeration value="Ticket"/>
 *     &lt;enumeration value="RefundApply"/>
 *     &lt;enumeration value="VoidApply"/>
 *     &lt;enumeration value="Date_ReissueApply"/>
 *     &lt;enumeration value="Seat_ReissueApply"/>
 *     &lt;enumeration value="SuspendApply"/>
 *     &lt;enumeration value="ResumeApply"/>
 *     &lt;enumeration value="Refund"/>
 *     &lt;enumeration value="Void"/>
 *     &lt;enumeration value="Date_Reissue"/>
 *     &lt;enumeration value="Seat_Reissue"/>
 *     &lt;enumeration value="Suspend"/>
 *     &lt;enumeration value="Resume"/>
 *     &lt;enumeration value="RefundRefused"/>
 *     &lt;enumeration value="VoidRefused"/>
 *     &lt;enumeration value="Date_ReissueRefused"/>
 *     &lt;enumeration value="Seat_ReissueRefused"/>
 *     &lt;enumeration value="SuspendRefused"/>
 *     &lt;enumeration value="ResumeRefused"/>
 *     &lt;enumeration value="CancelPNR"/>
 *     &lt;enumeration value="SeperatePNR"/>
 *     &lt;enumeration value="Others"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OperateType")
@XmlEnum
public enum OperateType {

    @XmlEnumValue("AddOrder")
    ADD_ORDER("AddOrder"),
    @XmlEnumValue("UpdateOrder")
    UPDATE_ORDER("UpdateOrder"),
    @XmlEnumValue("CancelOrder")
    CANCEL_ORDER("CancelOrder"),
    @XmlEnumValue("Book")
    BOOK("Book"),
    @XmlEnumValue("BookModify")
    BOOK_MODIFY("BookModify"),
    @XmlEnumValue("Ticket")
    TICKET("Ticket"),
    @XmlEnumValue("RefundApply")
    REFUND_APPLY("RefundApply"),
    @XmlEnumValue("VoidApply")
    VOID_APPLY("VoidApply"),
    @XmlEnumValue("Date_ReissueApply")
    DATE_REISSUE_APPLY("Date_ReissueApply"),
    @XmlEnumValue("Seat_ReissueApply")
    SEAT_REISSUE_APPLY("Seat_ReissueApply"),
    @XmlEnumValue("SuspendApply")
    SUSPEND_APPLY("SuspendApply"),
    @XmlEnumValue("ResumeApply")
    RESUME_APPLY("ResumeApply"),
    @XmlEnumValue("Refund")
    REFUND("Refund"),
    @XmlEnumValue("Void")
    VOID("Void"),
    @XmlEnumValue("Date_Reissue")
    DATE_REISSUE("Date_Reissue"),
    @XmlEnumValue("Seat_Reissue")
    SEAT_REISSUE("Seat_Reissue"),
    @XmlEnumValue("Suspend")
    SUSPEND("Suspend"),
    @XmlEnumValue("Resume")
    RESUME("Resume"),
    @XmlEnumValue("RefundRefused")
    REFUND_REFUSED("RefundRefused"),
    @XmlEnumValue("VoidRefused")
    VOID_REFUSED("VoidRefused"),
    @XmlEnumValue("Date_ReissueRefused")
    DATE_REISSUE_REFUSED("Date_ReissueRefused"),
    @XmlEnumValue("Seat_ReissueRefused")
    SEAT_REISSUE_REFUSED("Seat_ReissueRefused"),
    @XmlEnumValue("SuspendRefused")
    SUSPEND_REFUSED("SuspendRefused"),
    @XmlEnumValue("ResumeRefused")
    RESUME_REFUSED("ResumeRefused"),
    @XmlEnumValue("CancelPNR")
    CANCEL_PNR("CancelPNR"),
    @XmlEnumValue("SeperatePNR")
    SEPERATE_PNR("SeperatePNR"),
    @XmlEnumValue("Others")
    OTHERS("Others");
    private final String value;

    OperateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OperateType fromValue(String v) {
        for (OperateType c: OperateType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
