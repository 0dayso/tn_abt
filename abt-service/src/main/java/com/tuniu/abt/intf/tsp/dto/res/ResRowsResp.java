package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengyao on 2016/3/10.
 */
public class ResRowsResp<T> implements Serializable {

    private static final long serialVersionUID = 2173687449187351549L;

    private int count;

    private List<T> rows;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
