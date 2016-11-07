package com.tuniu.abt.intf.tsp.dto.pla;

import java.io.Serializable;

/**
 * 发送邮件结果回包
 *
 * Created by chengyao on 2015/12/24.
 */
public class SendEmailResponse implements Serializable {

    private static final long serialVersionUID = -9104852011589009332L;

    /**
     * 发送是否成功
     */
    private Boolean sendResult;

    /**
     * 错误消息
     */
    private String msg;

    public Boolean getSendResult() {
        return sendResult;
    }

    public void setSendResult(Boolean sendResult) {
        this.sendResult = sendResult;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
