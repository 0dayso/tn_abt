package com.tuniu.abt.service.ctrip;

/**
 * Created by chengyao on 2016/2/24.
 */
public enum CtripIntf {

    SEARCH("flightSearch"),
    REVERSE_SEARCH("flightReverseSearch"),
    CREATE_ORDER("flightCreateOrder"),
    SUBMIT_ORDER("flightSubmitOrder"),
    VIEW_ORDER_LIST_DETAILS("flightViewOrderListDetails"),
    ALIPAY("flightAlipay"),
    FLIGHT_VERIFY("flightVerify"),
    OPEN_CANCEL_ORDER("flightOpenCancelOrder"),

    REVISE_CONDITION("flightReviseCondition"),
    REFUND_ORDER("flightRefundOrder"),
    REFUND_ENDORSE_FEE("flightRefundEndorseFee");

    private final String value;

    String getValue(){
        return value;
    }

    CtripIntf(String value){
        this.value=value;
    }

    public static CtripIntf get(String value) {
        for (CtripIntf v : values()) {
            if (v.getValue().equals(value)) return v;
        }
        throw new IllegalArgumentException();
    }
}
