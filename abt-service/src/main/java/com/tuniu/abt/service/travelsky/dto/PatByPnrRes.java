package com.tuniu.abt.service.travelsky.dto;

import java.io.Serializable;

/**
 * Created by lanlugang on 2016/4/14.
 */
public class PatByPnrRes<T> implements Serializable {

    private static final long serialVersionUID = 8604958748333392428L;

    /**
     * pat的请求数据
     */
    private PatByPnrReq patByPnrReq;

    /**
     * pnr使用的运价
     */
    private PnrFareItem fareItem;

    /**
     * 婴儿使用的运价
     */
    private PnrFareItem babyFareItem;

    /**
     * 航信pat接口返回的原始result对象
     */
    private T patRes;

    /**
     * 婴儿运价计算结果
     */
    private T babyPatRes;

    public PatByPnrReq getPatByPnrReq() {
        return patByPnrReq;
    }

    public void setPatByPnrReq(PatByPnrReq patByPnrReq) {
        this.patByPnrReq = patByPnrReq;
    }

    public PnrFareItem getFareItem() {
        return fareItem;
    }

    public void setFareItem(PnrFareItem fareItem) {
        this.fareItem = fareItem;
    }

    public PnrFareItem getBabyFareItem() {
        return babyFareItem;
    }

    public void setBabyFareItem(PnrFareItem babyFareItem) {
        this.babyFareItem = babyFareItem;
    }

    public T getPatRes() {
        return patRes;
    }

    public void setPatRes(T patRes) {
        this.patRes = patRes;
    }

    public T getBabyPatRes() {
        return babyPatRes;
    }

    public void setBabyPatRes(T babyPatRes) {
        this.babyPatRes = babyPatRes;
    }
}
