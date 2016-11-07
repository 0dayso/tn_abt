package com.tuniu.abt.intf.dto.aop.airline;

/**
 * Created by huanggao on 2016/4/25.
 */
public class FlightRCSPolicyData {

    /**
     * 改升规则
     */
    private RespRuleInfo modifyRule;

    /**
     * 退票规则
     */
    private RespRuleInfo returnRule;

    /**
     * key
     */
    private String title;

    public RespRuleInfo getModifyRule() {
        return modifyRule;
    }

    public void setModifyRule(RespRuleInfo modifyRule) {
        this.modifyRule = modifyRule;
    }

    public RespRuleInfo getReturnRule() {
        return returnRule;
    }

    public void setReturnRule(RespRuleInfo returnRule) {
        this.returnRule = returnRule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
