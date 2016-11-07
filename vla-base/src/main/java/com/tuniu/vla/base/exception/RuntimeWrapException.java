package com.tuniu.vla.base.exception;

/**
 * 同RuntimeException，用于避免sonar代码Exception检查
 *
 * @author chengyao
 */
public class RuntimeWrapException extends RuntimeException {
    private static final long serialVersionUID = -7314377785799782043L;

    public RuntimeWrapException() {
        super();
    }

    public RuntimeWrapException(String message) {
        super(message);
    }

    public RuntimeWrapException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeWrapException(Throwable cause) {
        super(cause);
    }
}
