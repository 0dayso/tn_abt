package com.tuniu.abt.intf.constants;

/**
 * Created by chengyao on 2016/1/16.
 */
public class BizzConstants {

    // 国内适配底层
    public static final int SYSTEM_ME = 7;

    // 供应商id: 1航信 6携程 8开放平台 9航司直连
    public static final int V_TS = 1;
    public static final int V_CTRIP = 6;
    public static final int V_AOP = 8;
    public static final int V_AIRLINE = 9;

    public static final int PERSON_TYPE_ADULT = 1;
    public static final int PERSON_TYPE_CHILD = 2;
    public static final int PERSON_TYPE_BABY = 3;

    public static final String PSG_TYPE_CODE_ADT = "ADT";
    public static final String PSG_TYPE_CODE_CHD = "CHD";
    public static final String PSG_TYPE_CODE_INF = "INF";

    /**
     * 证件类型: 0未知
     */
    public static final int ID_TYPE_0 = 0;
    public static final String ID_TYPE_0_NAME = "未知";
    /**
     * 证件类型: 1身份证
     */
    public static final int D_TYPE_1 = 1;
    public static final String ID_TYPE_1_NAME = "身份证";
    /**
     * 证件类型: 2护照
     */
    public static final int ID_TYPE_2 = 2;
    public static final String ID_TYPE_2_NAME = "护照";
    /**
     * 证件类型: 3军官证
     */
    public static final int ID_TYPE_3 = 3;
    public static final String ID_TYPE_3_NAME = "军官证";
    /**
     * 证件类型: 4港澳通行证
     */
    public static final int ID_TYPE_4 = 4;
    public static final String ID_TYPE_4_NAME = "港澳通行证";
    /**
     * 证件类型: 6其他
     */
    public static final int ID_TYPE_6 = 6;
    public static final String ID_TYPE_6_NAME = "其他";
    /**
     * 证件类型: 7台胞证
     */
    public static final int ID_TYPE_7 = 7;
    public static final String ID_TYPE_7_NAME = "台胞证";
       /**
     * 证件类型: 8回乡证
     */
    public static final int ID_TYPE_8 = 8;
    public static final String ID_TYPE_8_NAME = "回乡证";
    /**
     * 证件类型: 9户口簿
     */
    public static final int ID_TYPE_9 = 9;
    public static final String ID_TYPE_9_NAME = "户口簿";
    /**
     * 证件类型: 10出生证明
     */
    public static final int ID_TYPE_10 = 10;
    public static final String ID_TYPE_10_NAME = "出生证明";
    /**
     * 证件类型: 11台湾通行证
     */
    public static final int ID_TYPE_11 = 11;
    public static final String ID_TYPE_11_NAME = "台湾通行证";

    /**
     * 性别
     */
    public static final int SEX_MALE_TYPE = 1;
    public static final String SEX_MALE = "男";

    public static final int SEX_FEMALE_TYPE = 0;
    public static final String SEX_FEMALE = "女";

    public static final int SEX_NA_TYPE = 9;
    public static final String SEX_NA = "未知";
    
    public static final String ORDER_CREATE_FAIL = "0";
    public static final String ORDER_CANCELD = "3";

    public static final int ISSUE_WAITING = 0;//待出票操作
    public static final int AOP_ISSUE_SUCCESS = 11; //已出票
    public static final int AOP_ISSUE_FAIL = 12;


    public static final int ISSUE_CALLBACK_YES = 1;
    public static final int ISSUE_CALLBACK_NO = 0;

    public static final int BATCH_OBTAIN_TICKET_NO_SIZE = 50;

    public static final String CTRIP_ADULT = "ADU";
    public static final String CTRIP_CHILD = "CHI";


    public static final String CTRIP_ISSUED = "PrintedTicket";
    public static final String CTRIP_ISSUE_ING = "ProcessingConfirmed";

    public static final int ISSUE_BATCH_PROCESSING = -1;//正在批处理中


    /**
     * 占位状态 1成功 2失败
     */
    public static final int BOOK_STATUS_SUCCESS = 1;

    public static final int BOOK_STATUS_FAILED = 2;


    //奥凯航空头等舱舱位字符串
    public static String BK_FIRSTCLASS_SEAT_CODE="F";
    //奥凯航空经济舱舱位字符串
    public static String BK_ECONOMYCLASS_SEAT_CODE="Y,B,H,K,M,L,N,Q,X,E,U,T,O,J,Z";
    //奥凯航空超级经济舱字符串
    public static String BK_SUPERECONOMYCLASS_SEAT_CODE="W";

}
