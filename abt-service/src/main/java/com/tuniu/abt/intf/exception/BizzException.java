package com.tuniu.abt.intf.exception;

import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.vla.base.exception.BaseException;
import com.tuniu.vla.base.framework.resp.ApiResp;

/**
 * 业务异常类
 *
 * @author chengyao
 */
public class BizzException extends BaseException {

    private static final long serialVersionUID = 7171231342776131010L;

    public BizzException(int code) {
        super(code);
    }

    public BizzException(int code, String message) {
        super(code, message);
    }

    public BizzException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BizzException(int code, Throwable cause) {
        super(code, cause);
    }

    public BizzException(int code, Object[] messageArgs) {
        super(code, messageArgs);
    }

    public BizzException(int code, Object[] messageArgs, Throwable cause) {
        super(code, messageArgs, cause);
    }

    public BizzException(ApiResp resp) {
        super(BizzEx.API_RESP_WRAP);
        this.setMessageArgs(new Object[] { resp });
    }
}
