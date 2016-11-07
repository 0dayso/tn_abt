package com.tuniu.abt.intf.constants;

/**
 * <Description> 异常码<br>
 *    第一位数字 标识 大交通，暂定使用 7<br>
 *    第二位数字 标识 业务线，机票（0）/用车（1）/汽车票（2）/火车票（3）<br>
 *    第三到四位数字 标识 系统/模块 <br>
 *    第五到八位数字 标识 异常码本身<br>
 * Created by chengyao on 2016/1/14.
 */
public class BookEx {

    public static final int DUPLICATED_REQUEST = 7082000;

    public static final int VERIFY_PARAM_FAILED = 7082001;

    public static final int INVALID_VENDOR_ID = 7082002;

    public static final int INVALID_PSG_NAME = 7082003;

    public static final int INVALID_PSG_TYPE = 7082004;

    public static final int INVALID_ID_NUMBER = 7082005;

    public static final int INVALID_BIRTH_DATE = 7082006;

    public static final int VENDOR_INTF_CONNECTOR_EX = 7082007;

    public static final int POST_DATA_PACK_ERROR = 7082008;

    public static final int POST_DATA_CRUD_ERROR = 7082009;

    public static final int PASSENGER_BREAK_EXECUTION_BANNED = 7082010;

    public static final int SEGMENT_BANNED = 7082011;

    public static final int TRAVELSKY_CREAT_PNR_ERROR = 7082101;

    public static final int TRAVELSKY_SEATS_NOT_ENOUGH = 7082102;

    public static final int TRAVELSKY_PAT_RESULT_NONE = 7082103;

    public static final int TRAVELSKY_RT_PNR_ERROR = 7082104;

    public static final int CTRIP_PRE_DATA_ERROR = 7082201;

    public static final int CTRIP_CREATE_ORDER_FAILED = 7082202;

    public static final int CTRIP_ORDER_INVALID = 7082203;

    public static final int CTRIP_SUBMIT_ORDER_FAILED = 7082204;

    public static final int CTRIP_QUERY_ORDER_FAILED = 7082205;

    public static final int CTRIP_REVERT_SEARCH_POLICY_FAILED = 7082206;

    public static final int AOP_PATA_BY_SEG_ERROR = 7082301;

    public static final int AOP_INVALID_PRICE = 7082302;

    public static final int AOP_QUERY_ERROR = 7082303;

    public static final int AOP_INVALID_POLICY = 7082304;



}
