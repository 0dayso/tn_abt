package com.tuniu.abt.intf.constants;

/**
 * <Description> 异常码<br>
 *    第一位数字 标识 大交通，暂定使用 7<br>
 *    第二位数字 标识 业务线，机票（0）/用车（1）/汽车票（2）/火车票（3）<br>
 *    第三到四位数字 标识 系统/模块 <br>
 *    第五到八位数字 标识 异常码本身<br>
 * Created by chengyao on 2016/3/4.
 */
public class TicketEx {

    public static final int DUPLICATED_REQUEST = 7085001;

    public static final int REST_INTERFACE_FAIL = 7085002;

    public static final int UNEXISTS_ISSUE = 7085003;

    public static final int OBTAIN_TICKET_ERROR = 7085004;

    public static final int NOT_ALLOW_PAYMENT = 7085005;

    public static final int PAY_FAIL = 7085006;

    public static final int PAY_ERROR = 7085007;

    public static final int ISSUE_ERROR = 7085008;

    public static final int QUERY_PRICE_FROM_DB_FAILED = 7085009;

    public static final int SAVE_TICKET_TO_DB_FAILED = 7085010;

    public static final int CTRIP_ISSUED = 7085100;

    public static final int CTRIP_ISSUE_ING = 7085101;

    public static final int CTRIP_CANCEL = 7085102;

    public static final int TS_ALREADY_ISSUED = 7085201;

    public static final int TS_ISSUED_CHECK_FAILED = 7085202;

    public static final int TS_NOT_ISSUED = 7085203;

    public static final int TS_ISSUED_NO_TICKET = 7085204;

    public static final int TS_ISSUED_TICKET_MISSED = 7085205;

    public static final int TS_ISSUED_PSG_INFO_MISSED = 7085206;

    public static final int TS_ISSUED_FLIGHT_INFO_MISSED = 7085207;

    public static final int TS_ISSUED_PRICE_INFO_MISSED = 7085208;

    public static final int TS_ISSUED_GET_TICKET_FAILED = 7085209;

    public static final int TS_ISSUED_ADD_OSI_FAILED = 7085210;

    public static final int REFRESH_TIECKT_NO_ISSUE_REQUEST = 7085301;

    public static final int AOP_TICKET_REQUEST_NOT_FOUND = 7085401;

    public static final int AOP_TICKET_FAIL = 7085402;

    public static final int AOP_TICKET_BOOK_DATA_NOT_FOUND = 7085403;
}
