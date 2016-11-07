package com.tuniu.abt.intf.dto.intf.airline;

public class CabinCheckParam {

    // 1 = 验舱, 2 = 验价, 3 = 全部验证
    private int type;

    /**
     * 航班搜索参数，和搜索接口的入参一致
     */
    private FlightQueryParam flightQuery;

    /**
     * 要验舱的航班
     */
    private AirFlightItem flightItem;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AirFlightItem getFlightItem() {
        return flightItem;
    }

    public void setFlightItem(AirFlightItem flightItem) {
        this.flightItem = flightItem;
    }

    public FlightQueryParam getFlightQuery() {
        return flightQuery;
    }

    public void setFlightQuery(FlightQueryParam flightQuery) {
        this.flightQuery = flightQuery;
    }
}
