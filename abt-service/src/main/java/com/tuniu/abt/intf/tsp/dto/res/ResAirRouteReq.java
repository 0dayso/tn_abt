package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * 路线查询请求参数
 * Created by chengyao on 2016/2/3.
 */
public class ResAirRouteReq implements Serializable {

    private static final long serialVersionUID = 579720447220280682L;

    // 出发到达三字码查询：2个字段同时有或无。 无：忽略此条件。 有：按出发到达查询。
    /**
     * 到达机场三字码
     */
    private String arrivalAirportCode;

    /**
     * 出发机场三字码
     */
    private String departureAirportCode;

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public ResAirRouteReq setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
        return this;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public ResAirRouteReq setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
        return this;
    }
}
