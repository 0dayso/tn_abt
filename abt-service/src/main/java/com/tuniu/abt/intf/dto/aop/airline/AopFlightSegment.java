package com.tuniu.abt.intf.dto.aop.airline;

import com.tuniu.adapter.flightTicket.vo.channel.Cabin;
import com.tuniu.adapter.flightTicket.vo.other.CabinInfoVo;

import java.util.Map;

/**
 * Created by huanggao on 2016/4/22.
 */
public class AopFlightSegment {

    /**
     * 航班号
     */
    private String flightNO;

    /**
     * 共享航班号
     */
    private String codeShare;

    /**
     * 机型
     */
    private String craftType;

    /**
     * 出发机场信息
     */
    private AopAirPortInfo departure;

    /**
     * 到达机场信息
     */
    private AopAirPortInfo arrival;

    /**
     * 舱位信息
     */
    private AopCabinInfo cabin;

    /**
     * 附加信息
     */
    private Map<String,Object> extraInfo;

    public String getFlightNO() {
        return flightNO;
    }

    public void setFlightNO(String flightNO) {
        this.flightNO = flightNO;
    }

    public String getCodeShare() {
        return codeShare;
    }

    public void setCodeShare(String codeShare) {
        this.codeShare = codeShare;
    }

    public String getCraftType() {
        return craftType;
    }

    public void setCraftType(String craftType) {
        this.craftType = craftType;
    }

    public AopAirPortInfo getDeparture() {
        return departure;
    }

    public void setDeparture(AopAirPortInfo departure) {
        this.departure = departure;
    }

    public AopAirPortInfo getArrival() {
        return arrival;
    }

    public void setArrival(AopAirPortInfo arrival) {
        this.arrival = arrival;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }

    public AopCabinInfo getCabin() {
        return cabin;
    }

    public void setCabin(AopCabinInfo cabin) {
        this.cabin = cabin;
    }

}
