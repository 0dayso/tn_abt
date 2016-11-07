package com.tuniu.vla.base.framework;

import com.tuniu.operation.platform.tsg.base.core.method.impl.AbstractBeanWrapper;
import com.tuniu.vla.base.framework.resp.ApiResp;
import org.springframework.core.MethodParameter;

/**
 * Created by chengyao on 2016/3/10.
 */
public class ApiRespWrapper  extends AbstractBeanWrapper {

    public Object wrap(Object bean) {
        return bean;
    }

    @Override
    public boolean supports(MethodParameter returnType) {
        return ApiResp.class.isAssignableFrom(returnType.getParameterType());
    }

}
