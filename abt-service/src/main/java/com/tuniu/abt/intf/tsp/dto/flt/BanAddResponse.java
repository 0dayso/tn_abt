package com.tuniu.abt.intf.tsp.dto.flt;

import java.io.Serializable;

/**
 * 增加屏蔽航班请求
 * Created by chengyao on 2016/3/30.
 */
public class BanAddResponse implements Serializable {

    private static final long serialVersionUID = 5746189008684575949L;

    private boolean isAdd;

    public boolean getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(boolean isAdd) {
        this.isAdd = isAdd;
    }
}
