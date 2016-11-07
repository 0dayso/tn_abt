package com.tuniu.vla.base.tsp;

import java.util.HashMap;

/**
 * url param container, use for replace url string param
 * 
 * @author chengyao
 */
public class TspUrlParam extends HashMap<String, String> {

    private static final long serialVersionUID = 5787561484807300223L;

    /**
     * 加入键值对
     * 
     * @param key key
     * @param value value
     * @return TspUrlParam
     */
    public TspUrlParam set(String key, String value) {
        this.put(key, value);
        return this;
    }

    /**
     * new object对象
     * 
     * @return TspUrlParam
     */
    public static TspUrlParam create() {
        return new TspUrlParam();
    }

}
