package com.tuniu.abt.intf.dto.common;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/25.
 */
public class AopAttachment implements Serializable {

    private static final long serialVersionUID = 7592666084560501882L;

    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
