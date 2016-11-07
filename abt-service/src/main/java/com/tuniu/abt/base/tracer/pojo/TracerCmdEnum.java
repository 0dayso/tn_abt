package com.tuniu.abt.base.tracer.pojo;

/**
 * Action ENUM定义
 * Created by chengyao on 2016/2/29.
 */
public enum TracerCmdEnum {

    /** action的异步执行cmd */
    ACTION_ASYNC,

    /** 异步保存刷新票号的结果 */
    SAVE_TICKET_INFO,

    /** TSP调用 */
    TSP_CALL,

    /** REST调用 */
    REST_CALL,

    /** 携程接口调用 */
    CTRIP_INTF_CALL,

    /** IBE接口调用 */
    IBE_INTF_CALL,

    /** IBEPLUS接口调用 */
    IBE_PLUS_INTF_CALL,

    /** 航司直连接口调用 */
    AIRLINE_INTF_CALL,

    /** 退改规则解析 */
    BACK_MEAL_PARSE,

    /** 退改签过期提醒 */
    BACK_MEAL_EXPIRE_CHECK,

    BAN_FLIGHT_ADD,

    /** pnr异常写入pat运价*/
    PNR_PRICE_STORE_ASYNC,

    /** mq接口发送 */
    MQ_SEND,
}
