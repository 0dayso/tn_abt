package com.tuniu.vla.base.tsp;

import com.tuniu.operation.platform.tsg.client.caller.rest.RequestParam;

import java.io.Serializable;

/**
 * 消费服务接口参数对象， 可用在TspInterface的方法参数中，任意位置
 *
 * Created by chengyao on 2015/11/16.
 */
public class TspRequestSetting extends RequestParam implements Serializable {

    private static final long serialVersionUID = -8262437767316033844L;

    /**
     * 请求参数转化设置，true=请求入参认为是对象，转成json字符串再发送
     * false=请求入参认为是String，直接发送
     */
    private boolean wrapReq = true;

    /**
     * 返回结果转化设置，true=把收到的String按照标准规则转化data里的数据为对象
     * false=直接把收到的数据返回
     */
    private boolean wrapResp = true;

    public boolean isWrapReq() {
        return wrapReq;
    }

    public void setWrapReq(boolean wrapReq) {
        this.wrapReq = wrapReq;
    }

    public boolean isWrapResp() {
        return wrapResp;
    }

    public void setWrapResp(boolean wrapResp) {
        this.wrapResp = wrapResp;
    }

}
