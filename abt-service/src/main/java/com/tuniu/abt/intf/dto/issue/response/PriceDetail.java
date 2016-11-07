package com.tuniu.abt.intf.dto.issue.response;

/**
 * Created by huangsizhou on 16/3/21.
 */
public class PriceDetail {

    private int adultNum;

    private int childNum;

    private int babyNum;

    private PriceInfo adultPrice = new PriceInfo();

    private PriceInfo childPrice = new PriceInfo();

    private PriceInfo babyPrice = new PriceInfo();

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

    public PriceInfo getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(PriceInfo adultPrice) {
        this.adultPrice = adultPrice;
    }

    public PriceInfo getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(PriceInfo childPrice) {
        this.childPrice = childPrice;
    }

    public PriceInfo getBabyPrice() {
        return babyPrice;
    }

    public void setBabyPrice(PriceInfo babyPrice) {
        this.babyPrice = babyPrice;
    }

}
