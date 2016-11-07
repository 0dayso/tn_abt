package com.tuniu.abt.intf.dto.order.tsp;

import java.io.Serializable;

/**
 * 获取待办数量服务 查询入参
 *
 * Created by chengyao on 2015/12/17.
 */
public class ToDoCountQuery implements Serializable {

    private static final long serialVersionUID = -5830049688946645982L;

    /**
     * 供应商ID(和vendorId一样)
     */
    private Integer agencyId;

    /**
     * 供应商ID (此参数最重要)
     */
    private Integer vendorId;

    /**
     * 1: NB侧标识，表示请求来自NB
     */
    private Integer nbFlag;

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public Integer getNbFlag() {
        return nbFlag;
    }

    public void setNbFlag(Integer nbFlag) {
        this.nbFlag = nbFlag;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }
}
