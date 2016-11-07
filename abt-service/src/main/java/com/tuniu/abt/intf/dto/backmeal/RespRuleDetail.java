package com.tuniu.abt.intf.dto.backmeal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chengyao on 2016/3/31.
 */
public class RespRuleDetail implements Serializable {

    private static final long serialVersionUID = 5068499237386085276L;

    /**
     * 对客退票费
     */
    private BigDecimal amount;

    /**
     * 对客退票费率
     */
    private BigDecimal rate;

    /**
     * 出发前 开始小时数
     */
    private Integer start;

    /**
     * 出发前 结束小时数
     */
    private Integer end;

    /**
     * 是否允许退票、改期操作
     */
    private boolean allow;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }
}
