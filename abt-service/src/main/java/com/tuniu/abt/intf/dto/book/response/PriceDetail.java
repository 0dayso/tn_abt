package com.tuniu.abt.intf.dto.book.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/2/15.
 */
public class PriceDetail implements Serializable{

    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 9185151352384228646L;

    private int adultNum;
    
    private int childNum;
    
    private int babyNum;
    
    private PriceItem adultPrice;
    
    private PriceItem childPrice;

    @JsonProperty("infantPrice")
    @JSONField(name = "infantPrice")
    private PriceItem babyPrice;

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

    public PriceItem getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(PriceItem adultPrice) {
        this.adultPrice = adultPrice;
    }

    public PriceItem getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(PriceItem childPrice) {
        this.childPrice = childPrice;
    }

    public PriceItem getBabyPrice() {
        return babyPrice;
    }

    public void setBabyPrice(PriceItem babyPrice) {
        this.babyPrice = babyPrice;
    }
    
}
