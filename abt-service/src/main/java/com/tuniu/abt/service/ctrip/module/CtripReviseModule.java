package com.tuniu.abt.service.ctrip.module;

import com.tuniu.abt.service.ctrip.CtripHeaderSupport;
import com.tuniu.abt.service.ctrip.CtripInterfaceService;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.FlightReviseConditionRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/2/25.
 */
@Service
public class CtripReviseModule {

    @Resource
    private CtripHeaderSupport ctripHeaderSupport;
    @Resource
    private CtripInterfaceService ctripInterfaceService;

    public RefundResponse reviseCondition(String orderId) {
        RefundRequest request = getCheckAutoRefundReq(orderId);
        return ctripInterfaceService.reviseCondition(request);
    }




    public RefundRequest getCheckAutoRefundReq(String orderId) {
        RefundRequest request = new RefundRequest();

        FlightReviseConditionRequest flightReviseConditionRequest = new FlightReviseConditionRequest();
        flightReviseConditionRequest.setOrderID(orderId);
        flightReviseConditionRequest.setRebookRefundFlag("F");

        //创建header
        request.setFlightReviseConditionRequest(flightReviseConditionRequest);
        request.setHeader(ctripHeaderSupport.build("FlightReviseCondition"));

        return request;
    }


}
