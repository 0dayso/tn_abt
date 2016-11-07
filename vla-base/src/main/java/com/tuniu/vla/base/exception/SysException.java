package com.tuniu.vla.base.exception;

/**
 * 系统异常类
 *
 * @author chengyao
 */
public class SysException extends BaseException {

    private static final long serialVersionUID = 9036811059885306320L;

    public SysException(int code) {
        super(code);
    }

    public SysException(int code, String message) {
        super(code, message);
    }

    public SysException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public SysException(int code, Throwable cause) {
        super(code, cause);
    }

    public SysException(int code, Object[] messageArgs) {
        super(code, messageArgs);
    }

    public SysException(int code, Object[] messageArgs, Throwable cause) {
        super(code, messageArgs, cause);
    }
}
