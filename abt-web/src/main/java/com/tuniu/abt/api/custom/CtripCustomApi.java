package com.tuniu.abt.api.custom;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.service.ctrip.module.CtripCommonModule;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.entity.PnrVendorMain;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 定制化需求服务
 *
 * Created by chengyao on 2016/3/8.
 */
@Controller
@RequestMapping("/custom")
public class CtripCustomApi {

}
