package com.tuniu.abt.service.ctrip;

import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.flight.connector.client.util.TraceIdGenerator;
import com.tuniu.flight.connector.ctrip.ICtripDConnector;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chengyao on 2016/3/21.
 */
@Service
public class CtripInterfaceAdapterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripInterfaceAdapterService.class);

    @Resource
    private ICtripDConnector iCtripDConnector;

    @CommandTrace(name = TracerCmdEnum.CTRIP_INTF_CALL, type = "#intf", input = "#request")
    public String call(CtripIntf intf, String request) throws Exception {
        String traceId = TraceIdGenerator.generator("ctrip:" + intf.getValue());
        switch (intf) {
        case VIEW_ORDER_LIST_DETAILS:
            return iCtripDConnector.flightViewOrderListDetails(traceId, request);
        case REFUND_ENDORSE_FEE:
            return iCtripDConnector.flightRefundEndorseFee(traceId, request);
        case OPEN_CANCEL_ORDER:
            return iCtripDConnector.flightOpenCancelOrder(traceId, request);
        case REVERSE_SEARCH:
            return iCtripDConnector.flightReverseSearch(traceId, request);
        case REVISE_CONDITION:
            return iCtripDConnector.flightReviseCondition(traceId, request);
        case CREATE_ORDER:
            return iCtripDConnector.flightCreateOrder(traceId, request);
        case SUBMIT_ORDER:
            return iCtripDConnector.flightSubmitOrder(traceId, request);
        case REFUND_ORDER:
            return iCtripDConnector.flightRefundOrder(traceId, request);
        case ALIPAY:
            String param = addPrefix4FlightAlipayRequest(request);
            String xml = iCtripDConnector.flightAlipay(traceId, param);
            return parseFlightAlipayResponse(xml);
        case SEARCH:
            return iCtripDConnector.flightSearch(traceId, request);
        default:
            throw new IllegalAccessError("ctrip interface 指定错误=" + intf.getValue());
        }
    }

    /**
     * 支付请求参数需要加前缀：requestXML=
     * @param param
     * @return
     */
    private String addPrefix4FlightAlipayRequest(String param) {
        int index = param.indexOf("<FlightAlipayRequest>");
        param = param.substring(index);
        param = "requestXML=" + param;
        try {
            return URLEncoder.encode(param, CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("FlightAlipayRequest encoding error:{}", e.getMessage(), e);
            throw new BizzException(BizzEx.CTRIP_TO_REQ, "FlightAlipayRequest encoding error:" + e.getMessage(), e);
        }
    }

    /**
     * 解析携程支付响应，如果有多个response 则返回false的response，否则 返回第一个response
     * @param xml
     * @return
     */
    private String parseFlightAlipayResponse(String xml) {
        List<String> responseStrList = new ArrayList<String>();
        Pattern p = Pattern.compile("((<FlightAlipayResponse>)[\\s\\S]*?(</FlightAlipayResponse>))");
        Matcher m = p.matcher(xml);
        while (m.find()) {
            String response = m.group(1);
            if (response.contains("false")) {
                return response;
            }
            responseStrList.add(response);
        }
        if (CollectionUtils.isNotEmpty(responseStrList)) {
            return responseStrList.get(0);
        }
        return null;
    }
}
