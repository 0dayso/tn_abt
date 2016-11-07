package com.tuniu.abt.intf.rest.fab;

import java.util.ArrayList;
import java.util.List;

/**
 * FAB 查询入参对象
 *
 * Created by chengyao on 2015/11/28.
 */
public class FabReqParam<T> {

    /**
     * 关联人信息查询 func name
     */
    public static final String GET_TOURIST_BY_ID = "getTouristById";

    private String func;

    private List<T> params;

    public FabReqParam(String func) {
        this.func = func;
    }

    public FabReqParam(String func, T... params) {
        this.func = func;
        this.params = new ArrayList<T>();
        for (T param : params) {
            this.params.add(param);
        }
    }

    public FabReqParam(String func, List<T> params) {
        this.func = func;
        this.params = params;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public List<T> getParams() {
        return params;
    }

    public void setParams(List<T> params) {
        this.params = params;
    }
}
