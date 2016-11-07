package com.tuniu.abt.intf.tsp.dto.aop;

/**
 * Created by chengyao on 2016/1/16.
 */
public class AopPolicyReq {

    private long policyId;

    public AopPolicyReq() {
    }

    public AopPolicyReq(long policyId) {
        this.policyId = policyId;
    }

    public long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }

    public static AopPolicyReq build(long policyId) {
        return new AopPolicyReq(policyId);
    }
}
