package com.tuniu.abt.intf.tsp.dto.pla;

/**
 * 参数对象
 *
 * Created by chengyao on 2015/12/24.
 */
public class SendEmailParam {

    /**
     * 平台中的参数id
     */
    private Integer paramId;

    /**
     * 参数对应的值
     */
    private String paramValue;

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
