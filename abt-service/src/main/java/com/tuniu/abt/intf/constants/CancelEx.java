package com.tuniu.abt.intf.constants;

/**
 * <Description> 异常码<br>
 *    第一位数字 标识 大交通，暂定使用 7<br>
 *    第二位数字 标识 业务线，机票（0）/用车（1）/汽车票（2）/火车票（3）<br>
 *    第三到四位数字 标识 系统/模块 <br>
 *    第五到八位数字 标识 异常码本身<br>
 * Created by chengyao on 2016/2/29.
 */
public class CancelEx {

    //【国内机票占位】入参格式不正确，请检查参数
    public static final int VERIFY_CANCEL_INPUT = 7083001;

    public static final int NO_CANCEL_PNR = 7083002;

    // 同一订单中1成人携带儿童+婴儿总数不得超过两人，暂时不支持本次删除操作。
    public static final int PASSENGERS_CHECK_ERROR_RESULT = 7083003;

    // 适配库中没有该PNR对应的人员信息
    public static final int PNR_PASSENGERS_MISS = 7083004;

    public static final int CANCEL_FAIL = 7083005;

    public static final int CANCEL_MODULE_INTF_FAIL = 7083006;

    public static final int CANCEL_DB_FAIL = 7083007;

    public static final int CANCEL_CTRIP_FAIL = 7083008;

    // 待删除成人已绑定婴儿
    public static final int PASSENGER_BIND_BABY = 7083009;

    // 不能找到儿童的关联PNR
    public static final int NOT_FIND_CHILD_REF = 7083010;

    // 订单中剩余成人数量不足（成人<(婴儿+儿童)*2）
    public static final int ADULT_COUNT_NOT_ENOUGH = 7083011;

    // 订单中剩余乘客无成人
    public static final int NO_ADULT = 7083012;

    // PNR已取消
    public static final int ALREADY_CANCELED = 7083013;
}
