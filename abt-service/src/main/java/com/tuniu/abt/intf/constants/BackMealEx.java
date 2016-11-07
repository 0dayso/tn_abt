package com.tuniu.abt.intf.constants;

/**
 * Created by chengyao on 2016/4/5.
 */
public class BackMealEx {

    // 缓存中获取航司下退改规则列表为空
    public static final int NO_REDIS_ITEMS = 7089001;

    // 无法匹配到入参及乘客类型所需要的规则
    public static final int NO_MATCH_BY_PASSENGER_TYPE = 7089002;

    // 根据原始规则，无法获取规则对象，解析规则文本异常等
    public static final int NO_RULES_FIND = 7089003;

    // 出发时间未在规则中配置
    public static final int DEPARTURE_TIME_NOT_MATCH_RULE = 7089004;

    // 解析异常
    public static final int RULE_PARSE_ERROR = 7089005;

    // 3.5折不允许退票
    public static final int DISCOUNT_MATCH = 7089006;

    // 查询不到舱等全价
    public static final int NO_FULL_PRICE = 7089007;


    // 重复的规则录入
    public static final int DUPLICATE_RULE = 7089101;

    // 修改规则异常
    public static final int UPDATE_ERROR = 7089102;

    // departureTime解析失败
    public static final int PARSE_DATETIME_ERROR = 7089103;

    // 资源库中获取规则id数据为空
    public static final int CTRIP_RES_RULE_INFO_NONE = 7089201;
    // 资源库中获取价格数据为空
    public static final int CTRIP_RES_PRICE_INFO_NONE = 7089202;
    // 资源库中获取携程价格数据为空
    public static final int CTRIP_RES_SEAT_PRICE_NONE = 7089203;
}
