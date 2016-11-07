package com.tuniu.abt.intf.tsp.dto.abp;

import java.io.Serializable;
import java.util.List;

/**
 * 券查询返回报文
 */
public class CouponOccupyResponse implements Serializable {

    private static final long serialVersionUID = 2917402833804309927L;

    /**
     * 捆绑规则ID
     */
    private int bindingRuleId;

    /**
     * 机票立减金额
     */
    private double discountAmout;

    /**
     * 优惠券列表
     */
    private List<CouponOccupyResource> coupons;

    public int getBindingRuleId() {
        return bindingRuleId;
    }

    public void setBindingRuleId(int bindingRuleId) {
        this.bindingRuleId = bindingRuleId;
    }

    public double getDiscountAmout() {
        return discountAmout;
    }

    public void setDiscountAmout(double discountAmout) {
        this.discountAmout = discountAmout;
    }

    public List<CouponOccupyResource> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponOccupyResource> coupons) {
        this.coupons = coupons;
    }
}
