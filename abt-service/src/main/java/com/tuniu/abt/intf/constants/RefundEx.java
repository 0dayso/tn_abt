package com.tuniu.abt.intf.constants;

/**
 *  <Description> 异常码<br>
 *    第一位数字 标识 大交通，暂定使用 7<br>
 *    第二位数字 标识 业务线，机票（0）/用车（1）/汽车票（2）/火车票（3）<br>
 *    第三到四位数字 标识 系统/模块 <br>
 *    第五到八位数字 标识 异常码本身<br>
 * Created by chengyao on 2016/3/3.
 */
public class RefundEx {

    public static final int REFUND_FAIL = 7084000;

    //////// 退票查询
    /** 根据途牛订单号查询不到对应的携程主订单号 */
    public static final int CTRIP_ORDER_NOT_FOUND = 7084001;

    /** 根据携程主订单号查询不到对应的携程子订单号 */
    public static final int CTRIP_SUB_ORDER_NOT_FOUND = 7084002;

    //////// 退票申请
    /** 根据orderId获取该订单下的所有的人员信息为空 */
    public static final int CTRIP_PERSON_NOT_FOUND = 7084003;
    /** 退票后必须满足一成人携带两儿童 */
    public static final int CTRIP_CONDITION_ERROR = 7084004;
    /** 携程退票接口单项目调用后，保存数据库失败 */
    public static final int CTRIP_REFUND_ITEM_ERROR = 7084005;
    /** 携程退票接口调用失败 */
    public static final int CTRIP_REFUND_ERROR = 7084006;
    /** 51book返回结果显示为失败 */
    public static final int LIANTUO_REFUND_RESULT_ERROR = 7084011;
    /** 对客及对供应商差价超过阀值 */
    public static final int REFUND_DIFFERENCE_FAIL = 7084012;
    /** 未找到退票会话 */
    public static final int REFUND_SESSION_NOT_FOUND = 7084013;
}
