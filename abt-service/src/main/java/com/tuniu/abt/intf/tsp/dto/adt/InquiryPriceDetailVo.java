/**
 * Copyright (C) 2006-2015 Tuniu All rights reserved
 */
package com.tuniu.abt.intf.tsp.dto.adt;

import java.io.Serializable;

/**
 * TODO: description Date: 2015-12-01
 * 
 * @author mac
 */
public class InquiryPriceDetailVo implements Serializable {
    private static final long serialVersionUID = -2851816751688368054L;

    private int price;// 原售卖价

    private int salePrice;// 新售卖价（运算后）

    private int saleId;// 销售控制的id(运算的规则id)

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

}
