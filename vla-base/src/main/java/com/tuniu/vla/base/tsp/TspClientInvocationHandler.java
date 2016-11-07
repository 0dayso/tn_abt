package com.tuniu.vla.base.tsp;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuniu.operation.platform.tsg.client.caller.rest.ResponseResult;
import com.tuniu.operation.platform.tsg.client.common.AppContext;
import com.tuniu.operation.platform.tsg.client.exception.NoServiceAvailableException;
import com.tuniu.operation.platform.tsg.client.exception.ServiceForbidenException;
import com.tuniu.operation.platform.tsg.client.proxy.tsg.TSPCommonClient;
import com.tuniu.vla.base.conf.BaseSystemConfig;
import com.tuniu.vla.base.constants.SysEx;
import com.tuniu.vla.base.exception.TspException;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.vla.base.utils.ObjectMapperFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TspInterface的接口代理Handler
 *
 * Created by chengyao on 2015/11/16.
 */
public class TspClientInvocationHandler implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TspClientInvocationHandler.class);

    private ObjectMapper objectMapper;

    private TspClientRunStatSupport tspClientRunStatSupport;

    public TspClientInvocationHandler() {
        objectMapper = ObjectMapperFactory.getDefaultObjectMapper();
    }

    public TspClientInvocationHandler(TspClientInvocationListener tspClientInvocationListener) {
        this();
        tspClientRunStatSupport = new TspClientRunStatSupport();
        tspClientRunStatSupport.setTspClientInvocationListener(tspClientInvocationListener);
        if (tspClientInvocationListener != null) {
            tspClientRunStatSupport.setRecordRunStatus(true);
        }
    }


    private Object innerInvoke(Method method, Object[] args) throws Throwable {
        ApplicationContext appContext = AppContext.getApplicationContext();
        TSPCommonClient tspCommonClient = appContext.getBean(TSPCommonClient.class);

        TspMethod tspMethod = method.getAnnotation(TspMethod.class);
        if (tspMethod == null) {
            throw new IllegalArgumentException("[TspClientInvocationHandler] TspMethod annotation miss. method=" + method.getName());
        }

        String serviceName = tspMethod.serviceName();
        if (serviceName == null || serviceName.length() == 0) {
            throw new IllegalArgumentException("[TspClientInvocationHandler] TspMethod annotation\'s serviceName is empty. method=" + method.getName());
        }


        // 查询输入参数是否有定制对象，有的话覆盖注解配置
        ParamsContainer paramsContainer = findParams(method, args);
        TspRequestSetting requestSetting = paramsContainer.getTspRequestSetting();
        TspUrlParam tspUrlParam = paramsContainer.getTspUrlParam();

        if (tspMethod.connectTimeout() > 0) {
            tspCommonClient.setConnectTimeout(tspMethod.connectTimeout());
        }
        if (tspMethod.socketTimeout() > 0) {
            tspCommonClient.setSocketTimeout(tspMethod.socketTimeout());
        }
        if (requestSetting == null) {
            requestSetting = new TspRequestSetting();
            requestSetting.setMethod(StringUtils.isEmpty(tspMethod.method()) ? null : tspMethod.method());
            requestSetting.setRequestLog(tspMethod.requestLog());
            requestSetting.setResponseLog(tspMethod.responseLog());
            requestSetting.setRegion(StringUtils.isEmpty(tspMethod.wantRegion()) ? null : tspMethod.wantRegion());
            requestSetting.setWrapReq(tspMethod.wrapReq());
            requestSetting.setWrapResp(tspMethod.wrapResp());
        }
        tspClientRunStatSupport.statusStart(requestSetting);

        try {
            Object data = findDataParam(args);
            requestSetting.setData(requestSetting.isWrapReq() ? objectMapper.writeValueAsString(data) : (String) data);
            requestSetting.setServiceName(tspMethod.serviceName());
            if (tspUrlParam != null) {
                requestSetting.setUrlParam(tspUrlParam);
            }
            tspClientRunStatSupport.statusInput(requestSetting.getData());

            BaseSystemConfig systemConfig = appContext.getBean(BaseSystemConfig.class);
            ResponseResult result;
            if (systemConfig.isProduct()) {
                result = tspCommonClient.responseCaller(requestSetting);
            } else {
                // 除生产环境额外查询tsp-profile.properties配置，跳过tsp服务获取接口地址，直接使用配置地址
                LocalRestCaller localRestCaller = appContext.getBean(LocalRestCaller.class);
                String address = localRestCaller.getAddress(requestSetting);

                if (address == null) {
                    result = tspCommonClient.responseCaller(requestSetting);
                } else {
                    String m = localRestCaller.getMethod(requestSetting);
                    requestSetting.setMethod(m);
                    result = localRestCaller.call(requestSetting, address);
                }
            }

            tspClientRunStatSupport.statusOutput(result.getReturnStr());

            if (ResponseResult.class == method.getReturnType()) {
                return result;
            } else if (!requestSetting.isWrapResp()) {
                return result.getReturnStr();
            } else {
                return unpack(result.getReturnStr(), method);
            }
        } catch (TspErrorCodeException e) {
            throw new TspException(SysEx.TSP_RESPONSE_FALSE, "[TSP调用失败] TspErrorCodeException[code:"
                    + e.getErrorCode() + " msg:" + e.getMessage() + "] serviceName=" +
                    tspMethod.serviceName(),  e);

        } catch (NoServiceAvailableException e) {
            throw new TspException(SysEx.TSP_NO_SERVICE_AVAILABLE, "[TSP调用异常] NoServiceAvailableException serviceName=" +
                    tspMethod.serviceName(),  e);
        } catch (ServiceForbidenException e) {
            throw new TspException(SysEx.TSP_SERVICE_FORBIDDEN, "[TSP调用异常] ServiceForbiddenException serviceName=" +
                    tspMethod.serviceName(), e);
        } catch (Exception e) {
            if (e.getCause() != null && "Read timed out".equals(e.getCause().getMessage())) {
                throw new TspException(SysEx.TSP_CLIENT_INVOKE_ERROR, "[TSP调用异常] ReadTimeoutException serviceName=" +
                        tspMethod.serviceName(), e);
            } else {
                throw new TspException(SysEx.TSP_CLIENT_INVOKE_ERROR, "[TSP调用异常] OtherException serviceName=" +
                        tspMethod.serviceName(), e);
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object object = innerInvoke(method, args);
            tspClientRunStatSupport.statusEnd(true, null);
            return object;
        } catch (Exception e) {
            tspClientRunStatSupport.statusEnd(false, e);
            throw e;
        }
    }

    /**
     * 封装的解包操作，标准报文是 {"success":true, "data": object} 样式，直接返回object， 对于错误直接抛出TspErrorCodeException
     * @param ret 返回数据字符串
     * @param method 切片的方法，用于取getReturnType和getGenericReturnType
     * @return 报文中的data对象
     * @throws IOException
     */
    private Object unpack(String ret, Method method) throws IOException {
        boolean returnVoid = "void".equals(method.getReturnType().getName());
        final JsonNode node = objectMapper.readTree(ret);
        if (node.path("success").asBoolean()) {
            if (node.has("data") && !returnVoid) {
                JsonNode realNode = node.get("data");
                JavaType javaType = objectMapper.getTypeFactory().constructType(method.getGenericReturnType());
                return objectMapper.convertValue(realNode, javaType);
            } else {
                return null;
            }
        } else {
            String code = node.has("errorCode") ? node.path("errorCode").asText() : "";
            String msg = node.has("msg") ? node.path("msg").asText() : "";
            throw new TspErrorCodeException(code, msg);
        }
    }

    private Object findDataParam(Object[] args) {
        if (args != null && args.length > 0) {
            return args[0];
        }
        return null;
    }

    private ParamsContainer findParams(Method method, Object[] args) {
        ParamsContainer paramsContainer = new ParamsContainer();
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (parameterType == TspRequestSetting.class) {
                paramsContainer.setTspRequestSetting((TspRequestSetting) args[i]);
            } else if (parameterType == TspUrlParam.class) {
                paramsContainer.setTspUrlParam((TspUrlParam) args[i]);
            }
        }
        return paramsContainer;
    }

    static class ParamsContainer {
        private TspRequestSetting tspRequestSetting;
        private TspUrlParam tspUrlParam;

        public TspRequestSetting getTspRequestSetting() {
            return tspRequestSetting;
        }

        public void setTspRequestSetting(TspRequestSetting tspRequestSetting) {
            this.tspRequestSetting = tspRequestSetting;
        }

        public TspUrlParam getTspUrlParam() {
            return tspUrlParam;
        }

        public void setTspUrlParam(TspUrlParam tspUrlParam) {
            this.tspUrlParam = tspUrlParam;
        }
    }
}
