package com.tuniu.abt.intf.dto.aop;

import com.tuniu.abt.intf.entity.AopPolicyRecord;
import com.tuniu.adapter.flightTicket.vo.policy.aop.FeeRate;

import java.util.List;

/**
 * Created by chengyao on 2016/2/17.
 */
public class AopPolicyRecordDto extends AopPolicyRecord {

    // 成人票面价
    private int adultPrice;

    //成人税费总额
    private int adultTotalFee;

    // 儿童票面价
    private int childPrice;

    //儿童税费总额
    private int childTotalFee;

    private List<FeeRate> feeRateList;

    public List<FeeRate> getFeeRateList() {
        return feeRateList;
    }

    public void setFeeRateList(List<FeeRate> feeRateList) {
        this.feeRateList = feeRateList;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getAdultTotalFee() {
        return adultTotalFee;
    }

    public void setAdultTotalFee(int adultTotalFee) {
        this.adultTotalFee = adultTotalFee;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public int getChildTotalFee() {
        return childTotalFee;
    }

    public void setChildTotalFee(int childTotalFee) {
        this.childTotalFee = childTotalFee;
    }
}
