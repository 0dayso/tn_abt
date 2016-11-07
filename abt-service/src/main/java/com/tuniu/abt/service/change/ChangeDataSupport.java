package com.tuniu.abt.service.change;

import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 改期共享数据服务支持
 *
 * Created by chengyao on 2016/3/2.
 */
public class ChangeDataSupport {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 工作对象，保存请求、中间处理数据及结果数据
    private static final String KEY_CHANGE_DATA = "_CHANGE_DATA";

    public static Map<String, Object> getModel() {
        return DataSharedSupport.getModel();
    }

    public static void setData(Object data) {
        getModel().put(KEY_CHANGE_DATA, data);
    }

    public static <T> T getData() {
        return (T) getModel().get(KEY_CHANGE_DATA);
    }

}
