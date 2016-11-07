package com.tuniu.abt.intf.dto.backmeal;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengyao on 2016/3/31.
 */
public class RespRuleRoot implements Serializable {

    private static final long serialVersionUID = 8283341547062189486L;

    // 提示信息
    private String noticeMsg;

    // 儿童退改签说明
    private String childRuleDesc;

    // 规则，idx=0 退，idx=1 改
    private List<RespRuleInfo> ruleInfos;

    public String getChildRuleDesc() {
        return childRuleDesc;
    }

    public void setChildRuleDesc(String childRuleDesc) {
        this.childRuleDesc = childRuleDesc;
    }

    public String getNoticeMsg() {
        return noticeMsg;
    }

    public void setNoticeMsg(String noticeMsg) {
        this.noticeMsg = noticeMsg;
    }

    public List<RespRuleInfo> getRuleInfos() {
        return ruleInfos;
    }

    public void setRuleInfos(List<RespRuleInfo> ruleInfos) {
        this.ruleInfos = ruleInfos;
    }
}
