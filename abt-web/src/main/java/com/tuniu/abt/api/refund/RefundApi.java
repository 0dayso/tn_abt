package com.tuniu.abt.api.refund;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.intf.dto.refund.ReqRefund;
import com.tuniu.abt.service.refund.RefundFacadeService;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 退票相关API
 * Created by chengyao on 2016/3/3.
 */
@Controller
@RequestMapping("/refund")
public class RefundApi {

    @Resource
    private RefundFacadeService refundFacadeService;

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @TSPServiceInfo(name = "ATS.ABT.RefundApi.apply", description = "退票申请")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.REFUND)
    public Object apply(@Json ReqRefund request) {
        return refundFacadeService.refund(request);
    }

}
