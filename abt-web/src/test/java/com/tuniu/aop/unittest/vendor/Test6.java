package com.tuniu.aop.unittest.vendor;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.service.ctrip.CtripInterfaceService;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.common.EnumCtripRequestType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.common.RequestHeader;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.Response;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.SearchRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundEndorseFee.*;
import com.tuniu.adapter.flightTicket.vo.connector.AirSearchRequest;
import com.tuniu.adapter.flightTicket.vo.connector.FlightSegVo;
import com.tuniu.adapter.flightTicket.vo.inquiry.RefundEndorseReqesutVo;
import com.tuniu.aop.unittest.BaseTest;
import com.tuniu.flight.connector.client.ctrip.CtripDConnectorClient;
import com.tuniu.flight.connector.client.ctrip.convert.domestic.FlightRefundEndorseFeeConvert;
import com.tuniu.flight.connector.client.ctrip.convert.domestic.FlightSearchConvert;
import com.tuniu.flight.connector.client.util.CommonResponse;
import com.tuniu.flight.connector.client.util.TraceIdGenerator;
import com.tuniu.flight.connector.ctrip.ICtripDConnector;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/4/5.
 */
public class Test6 extends BaseTest {

    @Resource
    private CtripInterfaceService ctripInterfaceService;

    @Resource
    private CtripDConnectorClient ctripDConnectorClient;
    @Resource
    private ICtripDConnector iCtripDConnector;

    @Test
    public void s0() {
        AirSearchRequest airSearchRequest = new AirSearchRequest();
        airSearchRequest.addFlightSeg(new FlightSegVo("SHA", "PEK", "2016-04-28"));
        CommonResponse<Response> resp = s(airSearchRequest);
        System.out.println(JSON.toJSONString(resp, true));
    }

    @Test
    public void s2() {
        RefundEndorseReqesutVo refundEndorseReqesutVo = new RefundEndorseReqesutVo();



        Object o  = flightRefundEndorseFee(refundEndorseReqesutVo);
        System.out.println(JSON.toJSONString(o, true));
    }

    public static FltRefundEndorseFeeRequest toRequest() {
        FltRefundEndorseFeeRequest request = new FltRefundEndorseFeeRequest();
        // header 节点
        String requestType = "FltRefundEndorseFee";
        RequestHeader header = new RequestHeader(requestType);
        request.setHeader(header);

        // FlightSearchRequest 节点
        FltRefundEndorseFeeRequestType fltRefundEndorseFeeRequest = new FltRefundEndorseFeeRequestType();
        RefundEndorseFeeListType refundEndorseFeeListType = new RefundEndorseFeeListType();
        List<RefundEndorseFeeEntityType> refundEndorseFeeEntityList = new ArrayList<RefundEndorseFeeEntityType>();
        refundEndorseFeeListType
                .setRefundEndorseFeeEntity(refundEndorseFeeEntityList);
        fltRefundEndorseFeeRequest
                .setRefundEndorseFeeList(refundEndorseFeeListType);


        RefundEndorseFeeEntityType refundEndorseFeeEntity = new RefundEndorseFeeEntityType();
            // 携程成人缩写是ADU

            refundEndorseFeeEntity.setPassengerType("ADU");
            refundEndorseFeeEntity.setRid(String.valueOf(190));
            refundEndorseFeeEntity.setCid(String
                    .valueOf(752));
            refundEndorseFeeEntity.setPrice(String.valueOf(1261));
            refundEndorseFeeEntity.setFdPrice(String
                    .valueOf(1242));
            refundEndorseFeeEntity.setStandPrice("1243");
            refundEndorseFeeEntity.setPrintPrice(String
                    .valueOf(1244));
            refundEndorseFeeEntity.setProductSourceNum(String
                    .valueOf(2));
            refundEndorseFeeEntity.setIsResetPolicy("false");
            refundEndorseFeeEntity.setNonRef("false");
                refundEndorseFeeEntity.setNonRer("true");

            refundEndorseFeeEntity.setRcid(String.valueOf(0));
            refundEndorseFeeEntity.setOrderId("0");
            refundEndorseFeeEntity.setType("1");
            refundEndorseFeeEntityList.add(refundEndorseFeeEntity);

        request.setFltRefundEndorseFeeRequest(fltRefundEndorseFeeRequest);
        return request;
    }



    public CommonResponse<FltRefundEndorseFeeResponse> flightRefundEndorseFee(
            RefundEndorseReqesutVo request) {
        String traceId = TraceIdGenerator
                .generator("ctrip:flightRefundEndorseFee");

        CommonResponse<FltRefundEndorseFeeResponse> result = new CommonResponse<FltRefundEndorseFeeResponse>(
                EnumCtripRequestType.Refund_FlightRefundEndorseFee.getUrl(),
                traceId);

        try {
            ;

            String requestString = FlightRefundEndorseFeeConvert.object2Xml(
                    traceId, toRequest());


            result.setRequestString(requestString);

            String responseString = iCtripDConnector.flightRefundEndorseFee(
                    traceId, requestString);

            result.setResponseString(responseString);

            FltRefundEndorseFeeResponse response = FlightRefundEndorseFeeConvert
                    .xml2Object(traceId, responseString);
            result.setResponse(response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public CommonResponse<com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.Response> s(AirSearchRequest request) {
        String traceId = TraceIdGenerator.generator("ctrip:flightSearch");

        CommonResponse<com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.Response> result = new CommonResponse<com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.Response>(
                EnumCtripRequestType.OAE_FlightSearch.getUrl(), traceId);

        try {
            SearchRequest searchRequest = FlightSearchConvert
                    .toRequest(request);

            searchRequest.getFlightSearchRequest().getResultControl().setMaxResultCount(1);

            String requestString = FlightSearchConvert.object2Xml(traceId,
                    searchRequest);


            result.setRequestString(requestString);
            String responseString = iCtripDConnector.flightSearch(traceId,
                    requestString);

            result.setResponseString(responseString);
            com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.Response response = FlightSearchConvert
                    .xml2Object(traceId, responseString);

            result.setResponse(response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
