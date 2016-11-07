package com.tuniu.abt.intf.tsp.dto.abp;

import java.io.Serializable;

/**
 * 优惠券信息获取请求
 */
public class CouponOccupyRequest implements Serializable {

    private static final long serialVersionUID = -253564017457899012L;

    /**
     * 出发团期
     */
    private String departDate;

    /**
     * 捆绑规则ID
     */
    private int bindingRuleId;

    /**
     * 优惠券IDS，格式:"1/2/3"
     */
    private String couponIds;

    public String getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(String couponIds) {
        this.couponIds = couponIds;
    }

    public int getBindingRuleId() {
        return bindingRuleId;
    }

    public void setBindingRuleId(int bindingRuleId) {
        this.bindingRuleId = bindingRuleId;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }
}
