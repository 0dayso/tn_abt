package com.tuniu.abt.service.ctrip.dto;

/**
 * Created by lanlugang on 2016/5/27.
 */
public enum CtripOrderStatusDisplay {

    UN_SUBMIT("Unsubmit", "待提交"),
    PROCESSING("Processing", "确认中"),
    PROCESSING_CONFIRMED("ProcessingConfirmed", "待出票"),
    CANCEL("Cancel", "已取消"),
    PRINTED_TICKET("RefundApplying", "退票申请中"),
    REFUND_APPLYING("FullRetire", "已退票"),
    FULL_RETIRE("FullRetire", "已退票"),
    PART_RETIRE("PartRetire", "部分退票"),
    SENDING_TICKET("SendingTicket", "已送票");

    private String enValue;

    private String zhValue;

    CtripOrderStatusDisplay(String enValue, String zhValue) {
        this.enValue = enValue;
        this.zhValue = zhValue;
    }

    public static CtripOrderStatusDisplay valueOfEnValue(String enValue) {
        for (CtripOrderStatusDisplay ctripOrderStatusDisplay : CtripOrderStatusDisplay.values()) {
            if (ctripOrderStatusDisplay.getEnValue().equals(enValue)) {
                return ctripOrderStatusDisplay;
            }
        }
        return null;
    }

    public String getEnValue() {
        return enValue;
    }

    public String getZhValue() {
        return zhValue;
    }
}
