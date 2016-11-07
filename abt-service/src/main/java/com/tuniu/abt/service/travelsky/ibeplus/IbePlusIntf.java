package com.tuniu.abt.service.travelsky.ibeplus;

/**
 * Ibe Plus 接口项目定义
 *
 * Created by chengyao on 2016/2/19.
 */
public enum IbePlusIntf {

    /** 航班可用舱位查询 */
    AIR_AVAIL_D("flightAirAvail"),

    /** 查询公布运价 */
    AIR_FARE_DISPLAY_D("flightAirFareDisplay"),

    /** 运价计算，航段 */
    AIR_PRICE_BY_SEG_D("flightAirPriceBySeg"),

    /** 按PNR的运价计算（存储）*/
    AIR_PRICE_D("flightAirPrice"),

    /** 自动预定 */
    AIR_BOOK("flightAirBook"),

    /** 自动预定(指定用户名密码) */
    AIR_BOOK_SPECIAL("flightSpecialAirBook"),

    /** 自动修改 */
    AIR_BOOK_MODIFY("flightAirBookModify"),

    /** PNR 结构化信息提取 */
    AIR_RES_RET("AirResRet"),

    AIR_ISSUE_TICKET_BY_BOP("flightIssueTicketByBop"),

    /** 计算退票费 */
    AIR_TICKET_REFUND("flightAirTicketRefund"),

    /** 查询退票费 */
    AIR_TICKET_REFUND_PRICE("flightAirTicketRefundPrice"),

    /** 确认退票 */
    AIR_TICKET_REFUND_CONFIRM("flightAirTicketRefundConfirm"),

    TODELETE("toBeDeleted");

    private final String value;

    public String getValue(){
        return value;
    }

    IbePlusIntf(String value){
        this.value=value;
    }

    public static IbePlusIntf get(String value) {
        for (IbePlusIntf v : values()) {
            if (v.getValue().equals(value)) return v;
        }
        throw new IllegalArgumentException();
    }

}
