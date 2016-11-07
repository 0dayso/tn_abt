package com.tuniu.abt.intf.tsp.dto.abp;

import java.io.Serializable;

/**
 * 券信息
 */
public class CouponOccupyResource implements Serializable {

    private static final long serialVersionUID = -7052197924331652075L;

    /**
     * 券ID
     */
    private int id;

    /**
     * 优惠券对应的PMF活动ID
     */
    private int pmfPsId;

    /**
     * 售卖价
     */
    private double salePrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPmfPsId() {
        return pmfPsId;
    }

    public void setPmfPsId(int pmfPsId) {
        this.pmfPsId = pmfPsId;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}
