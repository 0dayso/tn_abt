package com.tuniu.vla.base.taskpool.datashare;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据共享方案，数据支持工具
 *
 * Created by chengyao on 2016/2/2.
 */
public class DataSharedSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSharedSupport.class);

    public static final String _TRACER = "_tracer";

    public static final String _TRANS_ID = "_transId";

    public static final String _SYSTEM_ID = "_systemId";

    public static final String _ORDER_ID = "_orderId";

    public static final String _CALLBACK = "_callback";

    /**
     * 线程相关的存储，存放共享数据容器（Map<String, Object>）
     */
    private static ThreadLocal<Map<String, Object>> sharedData = new ThreadLocal<Map<String, Object>>();

    public static void putData(Map<String, Object> data) {
        sharedData.set(data);
    }

    public static void removeData() {
        sharedData.remove();
    }

    public static <T> T getTracer() {
        return get(DataSharedSupport._TRACER);
    }

    public static void putTracer(Object tracer) {
        put(DataSharedSupport._TRACER, tracer);
    }

    public static String getTransId() {
        return get(DataSharedSupport._TRANS_ID);
    }

    public static void putTransId(String transId) {
        put(DataSharedSupport._TRANS_ID, transId);
    }

    public static Integer getSystemId() {
        return get(DataSharedSupport._SYSTEM_ID);
    }
    public static void putSystemId(Integer systemId) {
        put(DataSharedSupport._SYSTEM_ID, systemId);
    }

    public static String getOrderId() {
        return get(DataSharedSupport._ORDER_ID);
    }
    public static void putOrderId(String orderId) {
        put(DataSharedSupport._ORDER_ID, orderId);
    }

    public static String getCallback() {
        return get(DataSharedSupport._CALLBACK);
    }
    public static void putCallback(String callback) {
        put(DataSharedSupport._CALLBACK, callback);
    }


    /**
     * 获取共享数据对象,
     * @return Map[String,Object]
     */
    public static Map<String, Object> getModel() {
        Map<String, Object> result = sharedData.get();
        if(result == null) {
            result = new HashMap<String, Object>();
            sharedData.set(result);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("[DataSharedSupport] getModel [threadId={}, threadName={}, value={}]",
                    Thread.currentThread().getId(), Thread.currentThread().getName(), JSON.toJSONString(result));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        Map<String, Object> objectMap = getModel();
        T result = (T) (objectMap == null ? null : objectMap.get(key));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("[DataSharedSupport] GET [key={}, value={}]", key, JSON.toJSONString(result));
        }
        return result;
    }

    public static void put(String key, Object object) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("[DataSharedSupport] PUT [key={}, value={}]", key, JSON.toJSONString(object));
        }
        Map<String, Object> objectMap = getModel();
        if (objectMap != null && object != null) {
            objectMap.put(key, object);
        }
    }

}
