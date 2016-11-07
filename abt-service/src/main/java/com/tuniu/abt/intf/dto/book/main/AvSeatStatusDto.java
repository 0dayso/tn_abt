package com.tuniu.abt.intf.dto.book.main;

import java.io.Serializable;

/**
 * 余位数据传输类
 * Created by lanlugang on 2016/5/5.
 */
public class AvSeatStatusDto implements Serializable {

    private static final long serialVersionUID = -5342100474668735016L;
    /**
     * 舱位
     */
    private String seatCode;

    /**
     * 座位数
     */
    private int seatNum;

    /**
     * 是否原始指定的舱位
     */
    private boolean original;

    /**
     * 原始舱位所在的舱等
     */
    private String seatClass;

    /**
     * 所在航班号
     */
    private String flightNo;

    /**
     * 参考成人价
     */
    private double refAdtPrice;

    /**
     * 参考折扣
     */
    private double discount;

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public double getRefAdtPrice() {
        return refAdtPrice;
    }

    public void setRefAdtPrice(double refAdtPrice) {
        this.refAdtPrice = refAdtPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
