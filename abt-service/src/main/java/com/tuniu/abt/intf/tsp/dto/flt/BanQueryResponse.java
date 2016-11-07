package com.tuniu.abt.intf.tsp.dto.flt;

import java.io.Serializable;

/**
 * 查询是否屏蔽航班 返回对象
 *
 * Created by chengyao on 2016/3/30.
 */
public class BanQueryResponse implements Serializable {

    private static final long serialVersionUID = -3314373745125636561L;

    /**
     * 是否屏蔽，true=是
     */
    private boolean banned;

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
