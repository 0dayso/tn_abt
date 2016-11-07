package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;

/**
 * 地区信息查询入参对象
 * Created by chengyao on 2016/2/3.
 */
public class ResDistrictReq implements Serializable {

    private static final long serialVersionUID = 2744542221488373361L;

    /**
     * 按id查询。null=不限
     */
    private Integer id;

    /**
     * 按城市三字码查询。null=不限
     */
    private String iataCode;

    public Integer getId() {
        return id;
    }

    public ResDistrictReq setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getIataCode() {
        return iataCode;
    }

    public ResDistrictReq setIataCode(String iataCode) {
        this.iataCode = iataCode;
        return this;
    }
}
