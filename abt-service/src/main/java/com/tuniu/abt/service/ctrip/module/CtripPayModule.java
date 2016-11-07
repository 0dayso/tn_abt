package com.tuniu.abt.service.ctrip.module;

import com.tuniu.abt.service.ctrip.CtripHeaderSupport;
import com.tuniu.abt.service.ctrip.CtripInterfaceService;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.CtripPayReq;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.FlightAlipayRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.FlightAlipayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/2/25.
 */
@Service
public class CtripPayModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripPayModule.class);

    private static final String PAY_TYPE_NORMAL = "FlightAlipayV1";

    private static final String PAY_TYPE_CHANGE = "DomesticFlightChangeV1";

    @Resource
    private CtripHeaderSupport ctripHeaderSupport;

    @Resource
    private CtripInterfaceService ctripInterfaceService;

    /**
     * 正常订单支付
     * @param request
     * @return
     */
    public FlightAlipayResponse flightAlipay(CtripPayReq request) {
        return  flightAlipay(request, PAY_TYPE_NORMAL);
    }

    /**
     * 改升订单支付
     * @param request
     * @return
     */
    public FlightAlipayResponse flightAlipayChange(CtripPayReq request) {
        return  flightAlipay(request, PAY_TYPE_CHANGE);
    }

    private FlightAlipayResponse flightAlipay(CtripPayReq request, String payType) {
        FlightAlipayRequest flightAlipayRequest = getCtripFlightAlipayRequest(request, payType);
        return ctripInterfaceService.flightAlipay(flightAlipayRequest);
    }

    /**
     * 生成携程支付入参
     *
     * @param param
     * @return
     */
    private FlightAlipayRequest getCtripFlightAlipayRequest(CtripPayReq param, String payType) {
        FlightAlipayRequest request = ctripHeaderSupport.buildAlipayRequest(payType);
        request.setAllianceOrderID(param.getOrderID());
        request.setExternalNo(param.getExternalNo());
        request.setMainOrderID(param.getMainOrderID());
        request.setOrderList(param.getOrderList());
        request.setChangeOrderID(param.getChangeOrderId());
        return request;
    }

}
