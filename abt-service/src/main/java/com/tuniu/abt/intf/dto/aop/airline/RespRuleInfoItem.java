package com.tuniu.abt.intf.dto.aop.airline;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/31.
 */
public class RespRuleInfoItem implements Serializable {

    private static final long serialVersionUID = 2980226507202653099L;

    //退改签手续费收费日期, 比如：起飞前2小时内
    private String name;
    //退改签手续费收费规则或金额，比如：77元/人
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
