package com.tuniu.abt.base.framework;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.operation.platform.tsg.base.core.method.impl.AbstractBeanWrapper;
import com.tuniu.vla.base.framework.resp.ApiResp;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.springframework.core.MethodParameter;

/**
 * 透明化transId的返回
 * Created by chengyao on 2016/3/29.
 */
public class DefaultBeanTransIdWrapper extends AbstractBeanWrapper {

    public Object wrap(Object bean) {
        ApiResp apiResp = ApiResp.success();
        apiResp.put("data", bean);
        String transId = DataSharedSupport.getTransId();
        if (transId != null) {
            apiResp.put("transId", transId);
        }
        apiResp.put("systemId", BizzConstants.SYSTEM_ME);
        apiResp.put("intl", 0);
        return apiResp;
    }

    @Override
    public boolean supports(MethodParameter returnType) {
        return true;
    }

}
