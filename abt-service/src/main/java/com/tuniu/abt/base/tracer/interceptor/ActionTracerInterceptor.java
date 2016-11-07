package com.tuniu.abt.base.tracer.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.tuniu.abt.base.tracer.TracerProcessor;
import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * ActionTrace注解的切片组件
 */
@Component
@Aspect
public class ActionTracerInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionTracerInterceptor.class);
    private static final TimeBasedGenerator timeBasedGenerator =
            Generators.timeBasedGenerator(EthernetAddress.fromInterface());
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    @Resource
    private TracerProcessor tracerProcessor;
    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;

    /**
     * 切入方法
     *
     * @param pjp ProceedingJoinPoint
     * @return Object
     * @throws Throwable
     */
    @Around("execution(@com.tuniu.abt.base.tracer.annotation.ActionTrace * *(..))")
    public Object aroundActionTrace(ProceedingJoinPoint pjp) throws Throwable { //NOSONAR
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        ActionTrace actionTrace = method.getAnnotation(ActionTrace.class);

        FlatTraceInfo action = DataSharedSupport.getTracer();
        boolean hasAction = false;
        if (action == null) {
            action = new FlatTraceInfo();
            action.setTraceId(newTraceId());
            action.setBizAction(actionTrace.action().name());
            action.setExecStart(new Date());
        } else {
            hasAction = true;
        }

        // 不记录body的情况，执行解析获取参数中的input
        if (!actionTrace.recordBody()) {
            Object inputParam = EvaluationUtil.getExpressionValue(evaluator, pjp, method, actionTrace.input());
            action.setInputParam(TracerProcessor.silenceToStr(inputParam));
            fillBizz(action);
        }

        // 如果未在HandlerInterceptor中设置过action，则put进共享对象
        if (!hasAction) DataSharedSupport.putTracer(action);

        Object result = null;
        try {
            result = pjp.proceed();

            // 成功记录
            if (actionTrace.outputReturn()) {
                action.setOutputResult(TracerProcessor.silenceToStr(result));
            } else if (org.springframework.util.StringUtils.hasText(actionTrace.output())) {
                action.setOutputResult(TracerProcessor.silenceToStr(
                        EvaluationUtil.getExpressionValue(evaluator, pjp, method, actionTrace.output())));
            }
            tracerProcessor.actionSuccess();

            return result;
        } catch (Exception ex) {
            if (!actionTrace.recordBody()) { // 不是controller的action
                tracerProcessor.actionFail(exceptionMessageUtils.findCodeString(ex),
                        exceptionMessageUtils.findAllExString(ex));
            }
            throw ex;
        }
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            ActionTrace actionTrace = method.getAnnotation(ActionTrace.class);
            if (actionTrace != null) {
                FlatTraceInfo action = new FlatTraceInfo();
                action.setTraceId(newTraceId());
                action.setBizAction(actionTrace.action().name());
                action.setExecStart(new Date());

                if (actionTrace.recordBody()) {
                    action.setInputParam(getRequestBody(request));
                    action.setRequestUrl(request.getRequestURL().toString());
                    action.setServerName(request.getServerName());
                    action.setServerPort(request.getServerPort());
                    action.setRemoteHost(request.getRemoteHost());
                    fillBizz(action);
                }

                DataSharedSupport.putTracer(action);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }


    private String getRequestBody(HttpServletRequest request) throws IOException {
        String method = request.getMethod();
        if (HttpMethod.GET.name().equals(method) || HttpMethod.DELETE.name().equals(method)) {
            return request.getQueryString();
        }

        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter();
        copyLimit(request.getReader(), stringBuilderWriter, 4096 * 100); // maxLength=400k
        return stringBuilderWriter.toString();
    }


    private void fillBizz(FlatTraceInfo action) {
        Object req = null;
        try {
            req = JSON.parseObject(action.getInputParam());
            if (req == null) {
                return;
            }
        } catch (Exception ex) {
            // ignore;
        }

        // default, 直接在json 请求中寻找业务字段
        String transId = toStr(JSONPath.eval(req, "$.transId"));
        String systemId = toStr(JSONPath.eval(req, "$.systemId"));
        String orderId = toStr(JSONPath.eval(req, "$.orderIdTuniu"));
        String callback = toStr(JSONPath.eval(req, "$.callback"));

        DataSharedSupport.putTransId(transId);
        DataSharedSupport.putSystemId(toInt(systemId));
        DataSharedSupport.putOrderId(orderId);
        DataSharedSupport.putCallback(callback);

        action.setSessionId(transId);
        action.setOrderId(orderId);
        action.setSystemId(systemId);
        action.setVendorId(toStr(JSONPath.eval(req, "$.vendorId")));
    }

    private static String newTraceId() {
        return StringUtils.remove(timeBasedGenerator.generate().toString(), '-');
    }

    private String toStr(Object o) {
        return o == null ? null : o.toString();
    }

    private int toInt(Object o) {
        return o == null ? 0 : NumberUtils.toInt(o.toString());
    }

    private static long copyLimit(Reader input, Writer output, int maxLength) throws IOException {
        long count = 0;
        int n = 0;
        char[] buff = new char[4096];
        while (-1 != (n = input.read(buff))) {
            output.write(buff, 0, n);
            count += n;
            if (count >= maxLength) {
                break;
            }
        }
        return count;
    }


}