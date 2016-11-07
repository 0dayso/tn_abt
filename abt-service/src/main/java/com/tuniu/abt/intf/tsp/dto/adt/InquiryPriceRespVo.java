/**
 * Copyright (C) 2006-2015 Tuniu All rights reserved
 */
package com.tuniu.abt.intf.tsp.dto.adt;

import java.io.Serializable;

/**
 * TODO: description
 * Date: 2015-12-01
 *
 * @author mac
 */
public class InquiryPriceRespVo implements Serializable {

    private static final long serialVersionUID = -2851816751688368054L;
    private boolean success;//	结果
    private String errorCode;//	错误码
    private String msg;//	说明
    private InquiryPriceDetailVo data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public InquiryPriceDetailVo getData() {
        return data;
    }

    public void setData(InquiryPriceDetailVo data) {
        this.data = data;
    }
}
