package com.tuniu.abt.intf.dto.backmeal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退费封装，包含价格和有效期
 *
 * Created by chengyao on 2016/5/28.
 */
public class BackMealPriceWrap implements Serializable {

    private static final long serialVersionUID = 8169586341150981614L;

    /**
     * 退改费
     */
    private BigDecimal price;

    /**
     * 有效期
     */
    private Date date;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
