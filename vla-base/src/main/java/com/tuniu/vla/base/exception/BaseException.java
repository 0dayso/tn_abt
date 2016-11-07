package com.tuniu.vla.base.exception;

/**
 * 基础异常类
 * 
 * @author chengyao
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -2755280599112405301L;

    private int code;

    private Object[] messageArgs;

    public BaseException(int code) {
        super();
        this.code = code;
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(int code, Throwable cause) {
        super(null, cause);
        this.code = code;
    }

    public BaseException(int code, Object[] messageArgs) {
        super(null, null);
        this.code = code;
        this.messageArgs = messageArgs;
    }

    public BaseException(int code, Object[] messageArgs, Throwable cause) {
        super(null, cause);
        this.code = code;
        this.messageArgs = messageArgs;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    public void setMessageArgs(Object[] messageArgs) {
        this.messageArgs = messageArgs;
    }

    public String getNoWrapMessage() {
        return super.getMessage();
    }

    public String getNativeMessage() {
        return super.getMessage();
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(code).append("]");
        if (super.getMessage() != null) {
            sb.append(super.getMessage());
        }
        return sb.toString();
    }


}
