package com.tuniu.abt.service.ctrip;

import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.utils.JaxbXmlMapper;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.cancelOrder.FltOpenCancelOrderRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.cancelOrder.FltOpenCancelOrderResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.cancelOrder.ResponseHeader;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.Response;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.SearchRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.data.messagetypes.HeaderType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderDetailResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.FlightAlipayRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.FlightAlipayResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundEndorseFee.FltRefundEndorseFeeRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundEndorseFee.FltRefundEndorseFeeResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundOrderApplyRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundOrderApplyResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.OrderDetailRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.Request;
import com.tuniu.adapter.flightTicket.vo.connector.AirSearchRequest;
import com.tuniu.adapter.flightTicket.vo.inquiry.ReverseRequestVo;
import com.tuniu.flight.connector.client.ctrip.convert.domestic.FlightReverseSearchConvert;
import com.tuniu.flight.connector.client.ctrip.convert.domestic.FlightSearchConvert;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/2/24.
 */
@Service
public class CtripInterfaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripInterfaceService.class);

    @Resource
    private CtripInterfaceAdapterService ctripInterfaceAdapterService;


    public OrderDetailResponse viewOrderListDetails(OrderDetailRequest request) {
        OrderDetailResponse result = query(CtripIntf.VIEW_ORDER_LIST_DETAILS, request, OrderDetailResponse.class);
        checkHeader(result.getResponseHeader());
        return result;
    }

    public FltRefundEndorseFeeResponse refundEndorseFee(FltRefundEndorseFeeRequest request) {
        FltRefundEndorseFeeResponse result = query(CtripIntf.REFUND_ENDORSE_FEE, request, FltRefundEndorseFeeResponse.class);
        checkHeader(result.getHeader());
        return result;
    }

    public FltOpenCancelOrderResponse openCancelOrder(FltOpenCancelOrderRequest request) {
        FltOpenCancelOrderResponse result = query(CtripIntf.OPEN_CANCEL_ORDER, request, FltOpenCancelOrderResponse.class);
        // TODO
        ResponseHeader requestHeader = result.getHeader();
        if ("Fail".equals(requestHeader.getResultCode())) {
            throw new BizzException(BizzEx.CTRIP_INTF_RESULT_ERROR, new Object[] { requestHeader.getResultNo(),
                    requestHeader.getResultMsg() });
        }
        return result;
    }

    public Response flightSearch(AirSearchRequest airSearchRequest) {
        SearchRequest request = FlightSearchConvert.toRequest(airSearchRequest);
        Response result = query(CtripIntf.SEARCH, request, Response.class);
        checkHeader(result.getHeader());
        return result;
    }

    public Response flightReverseSearch(ReverseRequestVo request) {
        SearchRequest searchRequest = FlightReverseSearchConvert.toRequest(request);
        Response result = query(CtripIntf.REVERSE_SEARCH, searchRequest, Response.class);
        checkHeader(result.getHeader());
        return result;
    }

    public RefundResponse reviseCondition(RefundRequest request) {
        RefundResponse result = query(CtripIntf.REVISE_CONDITION, request, RefundResponse.class);
        checkHeader(result.getHeader());
        return result;
    }

    public com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.Response saveOrder(Request request) {
        com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.Response result =
                query(CtripIntf.CREATE_ORDER, request,
                        com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.Response.class);
        com.tuniu.adapter.flightTicket.vendor.ctrip.domain.common.ResponseHeader requestHeader = result.getResponseHeader();
        if ("Fail".equals(requestHeader.getResultCode())) {
            throw new BizzException(BizzEx.CTRIP_INTF_RESULT_ERROR, new Object[] { requestHeader.getResultNo(),
                    requestHeader.getResultMsg() });
        }

        return result;
    }

    public RefundOrderApplyResponse refundOrder(RefundOrderApplyRequest request) {
        RefundOrderApplyResponse result = query(CtripIntf.REFUND_ORDER, request, RefundOrderApplyResponse.class);
        checkHeader(result.getHeader());
        return result;
    }

    public FlightAlipayResponse flightAlipay(FlightAlipayRequest request) {
       FlightAlipayResponse result = query(CtripIntf.ALIPAY, request, FlightAlipayResponse.class);
       return result;
    }

    private void checkHeader(HeaderType headerType) {
        if ("Fail".equals(headerType.getResultCode())) {
            throw new BizzException(BizzEx.CTRIP_INTF_RESULT_ERROR, new Object[] { headerType.getResultNo(),
                    headerType.getResultMsg() });
        }
    }

    public <T> T query(CtripIntf intf, Object request, Class<T> clazz) {
        T responseObject;
        try {
            String requestStr = JaxbXmlMapper.toXml(request, CharEncoding.UTF_8);
            String resXml = ctripInterfaceAdapterService.call(intf, requestStr);
            responseObject = JaxbXmlMapper.fromXml(resXml, clazz);
        } catch (Exception ex) {
            throw new BizzException(BizzEx.CTRIP_INTF_EX, new Object[] {}, ex);
        }
        if (responseObject == null) {
            throw new BizzException(BizzEx.CTRIP_INTF_RESULT_NONE);
        }
        return responseObject;
    }
}



