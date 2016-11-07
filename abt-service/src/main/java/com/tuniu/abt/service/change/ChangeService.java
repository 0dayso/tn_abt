package com.tuniu.abt.service.change;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.change.ProcChangeData;
import com.tuniu.abt.intf.dto.change.RespChange;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.change.aop.AopChangeApplyService;
import com.tuniu.abt.service.change.ctrip.CtripChangeApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/25.
 */
@Service
public class ChangeService {

    @Resource
    private CtripChangeApplyService ctripChangeApplyService;
    @Resource
    private AopChangeApplyService aopChangeApplyService;
    @Resource
    private ChangeDbBaseService changeDbBaseService;


    public RespChange applyChange(ProcChangeData changeData) {
        int vendorId = changeData.getAbtTicketRequest().getVendorId();

        changeDbBaseService.recordChangeOrder(changeData);

        try {
            RespChange respChange = null;

            // 开放平台、航信 通过接口查询退票费
            if (vendorId == BizzConstants.V_AOP) {
                aopChangeApplyService.applyChange(changeData);
            } else if (vendorId == BizzConstants.V_CTRIP) {
                respChange = ctripChangeApplyService.applyChange(changeData.getReqChange());
            } else {
                throw new BizzException(BizzEx.COMM_EX_WRAP, "渠道 " + vendorId + " 不支持改期.");
            }

            if (respChange != null) {
                respChange.setOrderId(changeData.getReqChange().getOrderId());
                respChange.setFlightItemId(changeData.getReqChange().getFlightItemId());
                return respChange;
            }

        } finally {
            if (vendorId != BizzConstants.V_AOP)
                changeDbBaseService.updateChangeStatus(changeData, true);
        }

        return null;
    }

}
