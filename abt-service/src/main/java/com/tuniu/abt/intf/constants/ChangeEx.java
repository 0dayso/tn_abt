package com.tuniu.abt.intf.constants;

/**
 * <Description> 异常码<br>
 *    第一位数字 标识 大交通，暂定使用 7<br>
 *    第二位数字 标识 业务线，机票（0）/用车（1）/汽车票（2）/火车票（3）<br>
 *    第三到四位数字 标识 系统/模块 <br>
 *    第五到八位数字 标识 异常码本身<br>
 * Created by chengyao on 2016/3/10.
 */
public class ChangeEx {

    public static final int CHANGE_FAIL = 7086000;

    public static final int CTRIP_NOT_FOUND_PAY_LIST = 7086001;

    public static final int CTRIP_CHANGE_PAY_FAIL = 7086002;

    public static final int CHANGE_SESSION_NOT_FOUND = 7086003;

}
