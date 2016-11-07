package com.tuniu.abt.intf.dto.aop.airline;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by chengyao on 2016/3/31.
 */
public class RespRuleInfo implements Serializable {

    private static final long serialVersionUID = -591251244528895470L;

    public static final int FLAG_REFUND = 1;

    public static final int FLAG_SAME = 2;

    // 1退票，2改期
    private int ruleFlag;

    // 名称
    private String ruleName;

    // 起飞前 n天(小时)内
    private List<RespRuleInfoItem> ruleFeeList;

    // 备注
    private String ruleRemark;

    // 规则基价
    private BigDecimal basePrice;

    // 换算规则：1=FD公布运价，2=舱等全价
    private Integer calculateType;

    /**
     * 出发前规则string
     */
    @JsonIgnore
    @JSONField(serialize=false)
    private String ruleString;

    /**
     * 起飞前退票规则明细
     */
    @JsonIgnore
    @JSONField(serialize=false)
    private List<RespRuleDetail> ruleItems;

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }

    public List<RespRuleDetail> getRuleItems() {
        return ruleItems;
    }

    public void setRuleItems(List<RespRuleDetail> ruleItems) {
        this.ruleItems = ruleItems;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
    }

    public int getRuleFlag() {
        return ruleFlag;
    }

    public void setRuleFlag(int ruleFlag) {
        this.ruleFlag = ruleFlag;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<RespRuleInfoItem> getRuleFeeList() {
        return ruleFeeList;
    }

    public void setRuleFeeList(List<RespRuleInfoItem> ruleFeeList) {
        this.ruleFeeList = ruleFeeList;
    }

    public String getRuleRemark() {
        return ruleRemark;
    }

    public void setRuleRemark(String ruleRemark) {
        this.ruleRemark = ruleRemark;
    }
}
