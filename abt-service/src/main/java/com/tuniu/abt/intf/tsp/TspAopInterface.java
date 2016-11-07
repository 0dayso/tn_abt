package com.tuniu.abt.intf.tsp;

import com.tuniu.adapter.flightTicket.vo.policy.aop.QueryPoliciesReq;
import com.tuniu.adapter.flightTicket.vo.policy.aop.QueryPoliciesRsp;
import com.tuniu.vla.base.tsp.intf.TspInterface;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.abt.intf.tsp.dto.aop.AopPolicyReq;
import com.tuniu.adapter.flightTicket.vo.policy.aop.PolicyDetail;
import org.springframework.stereotype.Component;

/**
 * Created by chengyao on 2016/1/16.
 */
@Component
public interface TspAopInterface extends TspInterface {


    @TspMethod(serviceName = "AOP.MAIN.PolicyOpenAPI.detail")
    PolicyDetail policyDetail(AopPolicyReq aopPolicyReq);

    @TspMethod(serviceName = "AOP.MAIN.PolicyOpenAPI.inquiry")
    QueryPoliciesRsp policyInquiry(QueryPoliciesReq queryPoliciesReq);

}
