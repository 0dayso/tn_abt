package com.tuniu.abt.intf.dto.custom.ctrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/3/8.
 */
public class CtripRepaySingleResult {

    private String error;

    private List<String> infos = new ArrayList<String>();

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getInfos() {
        return infos;
    }

    public void setInfos(List<String> infos) {
        this.infos = infos;
    }
}
