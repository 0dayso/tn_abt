package com.tuniu.abt.utils;

/**
 * Created by chengyao on 2016/4/29.
 */
public class XmlParseException extends RuntimeException {

    private static final long serialVersionUID = -7366768913025277805L;

    public XmlParseException() {
        super();
    }

    public XmlParseException(String message) {
        super(message);
    }

    public XmlParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlParseException(Throwable cause) {
        super(cause);
    }

}
