package com.tuniu.abt.intf.dto.aop.airline;

/**
 * Created by huanggao on 2016/4/25.
 */
public class AopPriceDetail {

    /**
     * 成人数量
     */
    private int adultNum;

    /**
     * 儿童数量
     */
    private int childNum;

    /**
     * 婴儿数量
     */
    private int babyNum;

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

    public int getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(int adultNum) {
        this.adultNum = adultNum;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public int getBabyNum() {
        return babyNum;
    }

    public void setBabyNum(int babyNum) {
        this.babyNum = babyNum;
    }

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
