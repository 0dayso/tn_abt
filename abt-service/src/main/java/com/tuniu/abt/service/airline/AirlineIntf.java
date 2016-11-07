package com.tuniu.abt.service.airline;

/**
 * 航司直连接口定义
 * Created by chengyao on 2016/3/22.
 */
public enum AirlineIntf {

    /** 获取票号 */
    TICKET_OUT("ticketOut"),

    /** 可退票查询 */
    REFUND_APPLY("refundApply"),

    /** 支付 */
    PAY("pay"),

    /** 下单占位 */
    ADD_ORDER("addOrder"),

    /** 验舱验价 */
    CHECK_CABIN_PRICE("checkCabinPrice"),

    /** 取消占位 */
    CANCEL_ORDER("cancelOrder");

    private final String value;

    String getValue(){
        return value;
    }

    AirlineIntf(String value){
        this.value=value;
    }

    public static AirlineIntf get(String value) {
        for (AirlineIntf v : values()) {
            if (v.getValue().equals(value)) return v;
        }
        throw new IllegalArgumentException();
    }
}
