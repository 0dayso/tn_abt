package com.tuniu.abt.base.tracer.pojo;

/**
 * Action ENUM定义
 * Created by chengyao on 2016/2/29.
 */
public enum TracerActionEnum {

    /** 占位 */
    BOOK,

    /** 取消占位 */
    CANCEL,

    /** 出票 */
    TICKET,

    /** 退票 */
    REFUND,

    /** 改期 */
    CHANGE,

    /** 携程，查询是否可退票 */
    CTRIP_CHECK_AUTO_REFUND,

    /** 携程改升支付 */
    CTRIP_CHANGE_PAY,

    /** 修改携程主表状态 */
    CTRIP_UPDATE_MAIN_ORDER_STATUS,

    /** 携程二次支付接口:对待支付的携程订单进行支付 */
    CUSTOM_CTRIP_REPAY,

    /** 订单号查询已出票或已支付成功的关联pnr及航班信息 */
    CUSTOM_QUERY_ORDER_PNR_FLIGHT,

    /** 51book申请退废票 */
    LIANTUO_AUTO_REFUND,

    /** 51book反馈票号 */
    LIANTUO_TICKET_GET,

    /** 51book根据退票单号修改退票处理状态 */
    LIANTUO_MODIFY_TICKET_STATUS,

    /** 通过PNR从库中或RT接口获取票号 */
    TICKET_REFRESH_TICKET,

    /** 退票申请（51Book、携程） */
    APPLY_AUTO_REFUND,

    /** 查询票号接口 */
    GET_TICKET_INFOS,

    /** 修改PNR：向PNR中添加OSI项 */
    MODIFY_PNR_OSI,

    /** 退改签费用查询 */
    BACK_MEAL_PRICE,

    /** 退改签规则查询 */
    BACK_MEAL_RULE,

    /** 开放平台操作结果反馈接收 */
    AOP_FEEDBACK,

    /** 恶意占位处理 */
    BAN_FLIGHT_ADD,

    /** 轮询票号 */
    OBTAIN_TICKET,
}
