package com.tuniu.vla.base.tsp;

/**
 * TSP接口返回false异常
 *
 * @copyright(C) 2006-2012 Tuniu All rights reserved
 * @author xiehui
 */
class TspErrorCodeException extends RuntimeException {


    private static final long serialVersionUID = -7026557876228668588L;

    private String errorCode;

    public TspErrorCodeException(String errorCode) {
        this.errorCode = errorCode;
    }

    public TspErrorCodeException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public TspErrorCodeException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public TspErrorCodeException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getLocalizedMessage() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(errorCode).append("-");
        buffer.append(super.getLocalizedMessage());
        return buffer.toString();
    }
}
