package com.tuniu.abt.intf.tsp;

import com.tuniu.vla.base.tsp.intf.TspInterface;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.adapter.flightTicket.vo.dfpPolicy.DetailResponseVo;
import com.tuniu.adapter.flightTicket.vo.dfpPolicy.PolicyDetailRequestVo;
import org.springframework.stereotype.Component;

/**
 * Created by chengyao on 2016/1/16.
 */
@Component
public interface TspDfpInterface extends TspInterface {

    @TspMethod(serviceName = "DFP.PolicyOpenAPI.detail")
    DetailResponseVo detail(PolicyDetailRequestVo request);

}
