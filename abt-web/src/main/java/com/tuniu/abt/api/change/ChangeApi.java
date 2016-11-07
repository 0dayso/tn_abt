package com.tuniu.abt.api.change;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.intf.dto.change.ReqChange;
import com.tuniu.abt.service.change.ChangeFacadeService;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/5/3.
 */
@Controller
@RequestMapping("/change")
public class ChangeApi {

    @Resource
    private ChangeFacadeService changeFacadeService;

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @TSPServiceInfo(name = "ATS.ABT.ChangeApi.apply", description = "改期申请")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.CHANGE)
    public Object apply(@Json ReqChange request) {
        return changeFacadeService.change(request);
    }

}
