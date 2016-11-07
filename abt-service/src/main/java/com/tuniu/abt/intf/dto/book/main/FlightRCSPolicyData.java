package com.tuniu.abt.intf.dto.book.main;

import java.io.Serializable;

/**
 * Created by lanlugang on 2016/4/9.
 */
public class FlightRCSPolicyData implements Serializable {

    private static final long serialVersionUID = -614211879509516038L;

    /**
     * 抬头(南京---香港)
     */
    private String title;

    /**
     * 退票规则
     */
    private String returnRule;

    /**
     * 更改规则
     */
    private String modifyRule;

    /**
     * 签转规则
     */
    private String endorseRule;

    /**
     * 误机
     */
    private String noshowRule;

    /**
     * 备注
     */
    private String bakRule;

    /**
     * 最少停留天数
     */
    private String minStay;

    /**
     * 最大停留天数
     */
    private String maxStay;

    /**
     * 提前出票时间
     */
    private String advReservation;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReturnRule() {
        return returnRule;
    }

    public void setReturnRule(String returnRule) {
        this.returnRule = returnRule;
    }

    public String getModifyRule() {
        return modifyRule;
    }

    public void setModifyRule(String modifyRule) {
        this.modifyRule = modifyRule;
    }

    public String getEndorseRule() {
        return endorseRule;
    }

    public void setEndorseRule(String endorseRule) {
        this.endorseRule = endorseRule;
    }

    public String getNoshowRule() {
        return noshowRule;
    }

    public void setNoshowRule(String noshowRule) {
        this.noshowRule = noshowRule;
    }

    public String getBakRule() {
        return bakRule;
    }

    public void setBakRule(String bakRule) {
        this.bakRule = bakRule;
    }

    public String getMinStay() {
        return minStay;
    }

    public void setMinStay(String minStay) {
        this.minStay = minStay;
    }

    public String getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(String maxStay) {
        this.maxStay = maxStay;
    }

    public String getAdvReservation() {
        return advReservation;
    }

    public void setAdvReservation(String advReservation) {
        this.advReservation = advReservation;
    }
}
