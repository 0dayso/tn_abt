package com.tuniu.abt.intf.dto.aop.airline;

/**
 * Created by huanggao on 2016/4/22.
 */
public class AopCabinInfo {
    /**
     * 舱等,譬如: F 头等舱，J 公务舱， Y 经济舱
     */
    private String seatType;

    /**
     * 舱位，譬如：R，Y，（底层用，前台不用管）
     */
    private String cabinClass;

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }
}
