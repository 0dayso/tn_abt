package com.tuniu.abt.utils;

/**
 * 列表对象转换接口
 *
 * @author chengyao
 */
public interface ListConverter<Z, T> {

    public Z convert(T t) throws Exception; //NOSONAR
}
