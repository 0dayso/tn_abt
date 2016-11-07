package com.tuniu.abt.service.travelsky.ibeplus;

import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.adapter.common.init.VendorConfig;
import com.tuniu.adapter.flightTicket.vo.occupy.HttpClientRequest;
import com.tuniu.flight.connector.client.util.TraceIdGenerator;
import com.tuniu.flight.connector.ibeplus.IIbePlusDConnector;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/21.
 */
@Service
public class IbePlusInterfaceAdapterService {

    @Resource
    private IIbePlusDConnector iIbePlusDConnector;

    @CommandTrace(name = TracerCmdEnum.IBE_PLUS_INTF_CALL, type = "#intf", input = "#request")
    public String call(IbePlusIntf intf, String request) throws Exception {
        String traceId = TraceIdGenerator.generator("ibeplus:" + intf.getValue());
        switch (intf) {
        case AIR_AVAIL_D:
            return iIbePlusDConnector.flightAirAvail(traceId, request);
        case AIR_FARE_DISPLAY_D:
            return iIbePlusDConnector.flightAirFareDisplay(traceId, request);
        case AIR_PRICE_BY_SEG_D:
            return iIbePlusDConnector.flightAirPriceBySeg(traceId, request);
        case AIR_PRICE_D:
            return iIbePlusDConnector.flightAirPrice(traceId, request);
        case AIR_BOOK:
            return iIbePlusDConnector.flightAirBook(traceId, request);
        case AIR_BOOK_SPECIAL:
            return iIbePlusDConnector.flightSpecialAirBook(traceId, request, getHttpClientRequest());
        case AIR_BOOK_MODIFY:
            return iIbePlusDConnector.flightAirBookModify(traceId, request);
        case AIR_ISSUE_TICKET_BY_BOP:
            return iIbePlusDConnector.flightIssueTicketByBop(traceId, request);
        case AIR_RES_RET:
            return iIbePlusDConnector.flightAirResRet(traceId, request);
        case AIR_TICKET_REFUND_PRICE:
            return iIbePlusDConnector.flightAirRefundPrice(traceId, request);
        case AIR_TICKET_REFUND:
            return iIbePlusDConnector.flightAirTicketRefund(traceId, request);
        case AIR_TICKET_REFUND_CONFIRM:
            return iIbePlusDConnector.flightAirTicketRefundConfirm(traceId, request);
        default:
            throw new IllegalAccessError("ibe plus interface 指定错误=" + intf.getValue());
        }
    }

    private HttpClientRequest getHttpClientRequest() {
        HttpClientRequest httpClientRequest = new HttpClientRequest();
        httpClientRequest.setUserName(VendorConfig.aopAccount);
        httpClientRequest.setPassword(VendorConfig.aopPassword);
        return httpClientRequest;
    }

}
