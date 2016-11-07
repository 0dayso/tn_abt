package com.tuniu.abt.intf.dto.book.response;

import java.io.Serializable;

/**
 * 行李额信息
 * Created by lanlugang on 2016/5/28.
 */
public class LuggageInfo implements Serializable {

    private static final long serialVersionUID = 7997920777933377803L;

    /**
     * 赠送行李额计量
     */
    private Integer freeLuggage;

    /**
     * 赠送行李额计量单位:KG
     */
    private String freeLuggageUnit = "KG";

    /**
     * 逾重行李额收费规则
     */
    private String overloadLuggage;

    /**
     * 备注
     */
    private String remark;

    public Integer getFreeLuggage() {
        return freeLuggage;
    }

    public void setFreeLuggage(Integer freeLuggage) {
        this.freeLuggage = freeLuggage;
    }

    public String getFreeLuggageUnit() {
        return freeLuggageUnit;
    }

    public void setFreeLuggageUnit(String freeLuggageUnit) {
        this.freeLuggageUnit = freeLuggageUnit;
    }

    public String getOverloadLuggage() {
        return overloadLuggage;
    }

    public void setOverloadLuggage(String overloadLuggage) {
        this.overloadLuggage = overloadLuggage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
