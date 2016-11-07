package com.tuniu.abt.api.aop;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.intf.dto.aop.AopFeedbackRequest;
import com.tuniu.abt.service.aop.AopFacadeService;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 开放平台对接服务
 *
 * Created by chengyao on 2016/3/26.
 */
@Controller
@RequestMapping("/aop")
public class AopApi {

    @Resource
    public AopFacadeService aopFacadeService;

    /**
     * 费用计算查询
     */
    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    @TSPServiceInfo(name = "ATS.ABT.AopApi.feedback", description = "开放平台操作结果反馈接收")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.AOP_FEEDBACK)
    public Object feedback(@Json AopFeedbackRequest request) {
        aopFacadeService.receiveFeedback(request);
        return null;
    }

}
