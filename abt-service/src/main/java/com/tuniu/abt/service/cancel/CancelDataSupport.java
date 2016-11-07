package com.tuniu.abt.service.cancel;

import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 取消占位共享数据服务支持
 *
 * Created by chengyao on 2016/3/2.
 */
public class CancelDataSupport {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 请求
    private static final String KEY_REQUEST = "_CANCEL_REQUEST";

    // 工作对象，保存请求、中间处理数据及结果数据
    private static final String KEY_CANCEL_DATA = "_CANCEL_DATA";

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
        getModel().put(KEY_CANCEL_DATA, data);
    }

    public static <T> T getData() {
        return (T) getModel().get(KEY_CANCEL_DATA);
    }

}
