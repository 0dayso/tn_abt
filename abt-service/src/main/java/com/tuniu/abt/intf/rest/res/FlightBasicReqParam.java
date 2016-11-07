package com.tuniu.abt.intf.rest.res;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源详情接口请求参数对象
 *
 * Created by chengyao on 2015/11/28.
 */
public class FlightBasicReqParam {

    /**
     * 资源团期列表
     */
    private List<FlightBasicReqItem> resourceDeptList;

    public List<FlightBasicReqItem> getResourceDeptList() {
        return resourceDeptList;
    }

    public void setResourceDeptList(List<FlightBasicReqItem> resourceDeptList) {
        this.resourceDeptList = resourceDeptList;
    }

    public static FlightBasicReqParam build(Integer resId, String departureDate) {
        FlightBasicReqItem item = new FlightBasicReqItem();
        item.setResId(resId);
        item.setDepartureDate(departureDate);
        List<FlightBasicReqItem> items = new ArrayList<FlightBasicReqItem>();
        items.add(item);
        FlightBasicReqParam flightBasicQueryParam = new FlightBasicReqParam();
        flightBasicQueryParam.setResourceDeptList(items);
        return flightBasicQueryParam;
    }

}
