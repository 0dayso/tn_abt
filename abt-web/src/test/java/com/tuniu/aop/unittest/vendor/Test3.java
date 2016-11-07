package com.tuniu.aop.unittest.vendor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuniu.adapter.flightTicket.vo.ibe.rt.RTPnrReply;
import com.tuniu.adapter.flightTicket.vo.ibe.rt.RTPnrVo;
import com.tuniu.operation.platform.base.rest.RestClient;
import com.tuniu.operation.platform.base.rest.RestMethod;
import com.tuniu.vla.base.constants.SysEx;
import com.tuniu.vla.base.exception.BaseException;
import com.tuniu.vla.base.exception.SysException;
import com.tuniu.vla.base.utils.ObjectMapperFactory;

/**
 * Created by chengyao on 2016/2/2.
 */
public class Test3 {

    public static void main(String[] args) {

        RTPnrReply reply = rtPnrInfo(new RTPnrVo());
        System.out.println(reply);
    }


    /**
     * Description: rtPnr信息(不抛异常)<br>
     *
     * @param rtPnrVo
     * @return
     * @throws Exception <br>
     * @author lanlugang<br>
     * @date 2015-8-28
     * @taskId AIR-94<br>
     */
    public static RTPnrReply rtPnrInfo(RTPnrVo rtPnrVo) {
        String url = "http://localhost:8082/abt" + "/supplier/flight/ibe/rt";


        try {
            RTPnrReply result = restExecute(url, RestMethod.GET, JSON.toJSONString(rtPnrVo), RTPnrReply.class);

            return result;
        } catch (SysException ex) {
            throw new SysException(SysEx.REST_CLIENT_ERROR, "RT获取PNR信息异常:" + ex.getMessage(), ex);
        }
    }

    static ObjectMapper objectMapper = ObjectMapperFactory.getDefaultObjectMapper();

    private static <T>  T restExecute(String url, String method, String data, Class<T> returnType) {
        RestClient restClient = new RestClient(url, method, data);
        try {
            String resultString = restClient.execute();
            if (resultString == null) {
                throw new BaseException(SysEx.REST_CLIENT_ERROR, "REQ[" + url + "] DETAIL[接口返回数据为空]");
            }
            final JsonNode node = objectMapper.readTree(resultString);

            if (!node.path("success").asBoolean()) {
                String code = node.has("errorCode") ? node.path("errorCode").asText() : "";
                String msg = node.has("msg") ? node.path("msg").asText() : "";
                throw new BaseException(SysEx.REST_CLIENT_ERROR, "REQ[" + url + "] DETAIL[接口返回错误代码: [" + code + "]" + msg + "]");
            }

            if (!node.has("data")) {
                throw new BaseException(SysEx.REST_CLIENT_ERROR, "REQ[" + url + "] DETAIL[接口返回数据data节点为空]");
            }

            JsonNode realNode = node.get("data");
            return objectMapper.convertValue(realNode, returnType);
        } catch (BaseException ex) {
            throw new SysException(ex.getCode(), ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new SysException(SysEx.REST_CLIENT_ERROR, ex.getMessage(), ex);
        }
    }
}
