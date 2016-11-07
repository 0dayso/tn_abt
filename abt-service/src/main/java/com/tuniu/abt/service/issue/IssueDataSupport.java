package com.tuniu.abt.service.issue;

import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by chengyao on 2016/3/2.
 */
public class IssueDataSupport {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String KEY_REQUEST = "_CONFIRM_REQUEST";

    private static final String KEY_CONFIRM_DATA = "_CONFIRM_DATA";

    public static Map<String, Object> getModel() {
        return DataSharedSupport.getModel();
    }

    public static void setRequest(Object request) {
        getModel().put(KEY_REQUEST, request);
    }

    public static <T> T getRequest() {
        return (T) getModel().get(KEY_REQUEST);
    }

    public static void setData(Object data) {
        getModel().put(KEY_CONFIRM_DATA, data);
    }

    public static <T> T getData() {
        return (T) getModel().get(KEY_CONFIRM_DATA);
    }

}
