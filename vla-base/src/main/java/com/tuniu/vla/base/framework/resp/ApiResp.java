package com.tuniu.vla.base.framework.resp;

import java.util.HashMap;
import java.util.Map;

/**
 * 标准输出对象封装
 *
 * <p>
 * {success:true, errorCode:0, msg:"", data:{}}
 * </p>
 * <p>
 * {success:false, errorCode:1002001, msg:"失败"}
 * </p>
 *
 * @author chengyao
 */
public class ApiResp extends HashMap<String, Object> {

    private static final long serialVersionUID = 262937477576590088L;

    /**
     * 返回代码的key值
     */
    public static final String RET_KEY = "success";

    /**
     * 返回文字消息的key值
     */
    public static final String MSG_KEY = "msg";

    public static final String CODE_KEY = "errorCode";

    /**
     * 返回数据的key值
     */
    public static final String DATA_KEY = "data";

    /**
     * 只返回成功标志
     *
     * @return ApiResp
     */
    public static ApiResp success() {
        return json(true);
    }

    /**
     * 返回成功且带消息
     * @param msg
     * @return
     */
    public static ApiResp success(String msg) {
        return json(true).json(MSG_KEY, msg);
    }

    /**
     * 错误，错误码及错误提示
     *
     * @param code 错误码
     * @param msg 提示
     * @return ApiResp
     */
    public static ApiResp error(int code, String msg) {
        return json(false).json(CODE_KEY, code).json(MSG_KEY, msg);
    }

    /**
     * 加入键值对
     *
     * @param key key
     * @param value value
     * @return ApiResp
     */
    public ApiResp json(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public void setCode(int code) {
        this.put(CODE_KEY, code);
    }
    public void setMsg(String msg) {
        this.put(MSG_KEY, msg);
    }
    public void setSuccess(boolean success) {
        this.put(RET_KEY, success);
    }

    public int getCode() {
        return (Integer) this.get(CODE_KEY);
    }
    public boolean getSuccess() {
        return (Boolean) this.get(RET_KEY);
    }
    public String getMsg() {
        return (String) this.get(MSG_KEY);
    }
    public Object getData() {
        return this.get(DATA_KEY);
    }

    /**
     * 加入数据项
     *
     * @param value value
     * @return ApiResp
     */
    public ApiResp jsonData(Object value) {
        this.put(DATA_KEY, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public ApiResp jsonData(String key, Object value) {
        Object o = this.get(DATA_KEY);
        if (o == null) {
            this.put(DATA_KEY, new HashMap<String, Object>());
            o = this.get(DATA_KEY);
        }
        if (o instanceof Map) {
            Map<String, Object> data = (Map<String, Object>) o;
            data.put(key, value);
        }
        return this;
    }

    /**
     * new json object对象
     *
     * @return ApiResp
     */
    public static ApiResp json() {
        return new ApiResp();
    }

    /**
     * 加入返回码
     *
     * @param success success
     * @return ApiResp
     */
    public static ApiResp json(boolean success) {
        return json().json(RET_KEY, success).json(CODE_KEY, null);
    }

}
