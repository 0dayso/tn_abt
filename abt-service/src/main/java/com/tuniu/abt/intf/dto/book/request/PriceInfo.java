package com.tuniu.abt.intf.dto.book.request;

import java.io.Serializable;

/**
 * 价格信息
 */
public class PriceInfo implements Serializable {

    private static final long serialVersionUID = -1727937069470199897L;

    /**
     * 成人价
     */
    private PriceInfoItem adultPrice;

    /**
     * 儿童价
     */
    private PriceInfoItem childPrice;

    /**
     * 婴儿价
     */
    private PriceInfoItem infantPrice;

    public PriceInfoItem getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(PriceInfoItem adultPrice) {
        this.adultPrice = adultPrice;
    }

    public PriceInfoItem getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(PriceInfoItem childPrice) {
        this.childPrice = childPrice;
    }

    public PriceInfoItem getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(PriceInfoItem infantPrice) {
        this.infantPrice = infantPrice;
    }
}