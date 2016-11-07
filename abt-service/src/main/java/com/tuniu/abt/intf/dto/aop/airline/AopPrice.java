package com.tuniu.abt.intf.dto.aop.airline;

/**
 * Created by huanggao on 2016/4/22.
 */
public class AopPrice {

    /**
     * 成人价格
     */
    private AopPriceInfo adultPrice;

    /**
     * 儿童价格
     */
    private AopPriceInfo childPrice;

    /**
     * 婴儿价格
     */
    private AopPriceInfo infantPrice;

    public AopPriceInfo getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(AopPriceInfo adultPrice) {
        this.adultPrice = adultPrice;
    }

    public AopPriceInfo getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(AopPriceInfo childPrice) {
        this.childPrice = childPrice;
    }

    public AopPriceInfo getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(AopPriceInfo infantPrice) {
        this.infantPrice = infantPrice;
    }
}
