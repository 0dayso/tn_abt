package com.tuniu.vla.base.exception;

/**
 * Created by chengyao on 2015/11/20.
 */
public class TspException extends BaseException {

    private static final long serialVersionUID = 3229759415579598909L;

    public TspException(int code) {
        super(code);
    }

    public TspException(int code, String message) {
        super(code, message);
    }

    public TspException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public TspException(int code, Throwable cause) {
        super(code, cause);
    }

    public TspException(int code, Object[] messageArgs) {
        super(code, messageArgs);
    }

    public TspException(int code, Object[] messageArgs, Throwable cause) {
        super(code, messageArgs, cause);
    }
}
