package com.tuniu.abt.intf.constants;

/**
 * Redis相关常量
 *
 * @author chengyao
 */
public class RedisConstants {

    /**
     * 1分钟（毫秒单位）
     */
    public static final int MIN_IN_MILL = 60 * 1000;

    /**
     * 10分钟
     */
    public static final int TEN_IN_MIN = 10 * 60;

    /**
     * 10秒（毫秒单位）
     */
    public static final int SEC_10_IN_MILL = 10 * 1000;

    /**
     * 一天的时间
     */
    public static final int DAYTIME = 24 * 60 * 60;

    /**
     * 三天的时间
     */
    public static final int DAY_3 = 3 * 24 * 60 * 60;
    /**
     * 一周的时间
     */
    public static final int WEEKTIME = 7 * 24 * 60 * 60;

    public static final int HALF_MONTH_TIME = 15 * 24 * 60 * 60;

    /**
     * 3个小时
     */
    public static final int HOUR_3 = 3 * 60 * 60;

    /**
     * 1个小时
     */
    public static final int HOUR_1 = 1 * 60 * 60;
    /**
     * 2个小时
     */
    public static final int HOUR_2 = 2 * 60 * 60;

    /**
     * 20年的时间
     */
    public static final int YEARTIME = 20 * 360 * 24 * 60 * 60;

    /**
     * 通用过期时间
     */
    public static final int COMMON_EXPIRE_TIME = 60 * 60;

    /**
     * 一个月时间
     */
    public static final int MONTHTIME = 30 * 24 * 60 * 60;
    

    public static final String BACK_MEAL_KEY = "abt:backMeal:main:";

    public static final String BACK_MEAL_RULE_KEY = "abt:backMeal:rule:";

    public static final String DFP_POLICY = "abt:dfp:policy:";

    // 从IBE接口查询的FD价缓存key
    public static final String CACHE_FD_IBE = "abt:fd:price:ibe:";

    public static final String LUGGAGE_INFO_KEY = "abt:luggageInfo:main:";

    private int version;

    public RedisConstants() {
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
