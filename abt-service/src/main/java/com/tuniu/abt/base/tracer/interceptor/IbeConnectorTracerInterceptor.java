package com.tuniu.abt.base.tracer.interceptor;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.base.tracer.TracerProcessor;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * ibe接口的跟踪日志记录aop
 * Created by chengyao on 2016/3/15.
 */
@Component
@Aspect
public class IbeConnectorTracerInterceptor implements Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbeConnectorTracerInterceptor.class);

    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;
    @Resource
    private TracerProcessor tracerProcessor;
    /**
     * 切入方法
     *
     * @param pjp ProceedingJoinPoint
     * @return Object
     * @throws Throwable
     */
    @Around("execution(* com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService.*(..)))")
    public Object aroundIbeCall(ProceedingJoinPoint pjp) throws Throwable { //NOSONAR
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        // trace//
        FlatTraceInfo cmd = TracerProcessor.makeCmd();
        cmd.setBizCmd(TracerCmdEnum.IBE_INTF_CALL.name());
        cmd.setExecStart(new Date());
        cmd.setInputParam(parseInput(pjp.getArgs()));
        String identity = signature.getMethod().getName();
        cmd.setBizType(identity);

        Object result = null;
        try {
            result = pjp.proceed();
            return result;
        } catch (Exception ex) {
            // trace//
            cmd.setExecStatus(FlatTraceInfo.FAIL);
            cmd.setErrorCode(exceptionMessageUtils.findCodeString(ex));
            cmd.setErrorDesc(exceptionMessageUtils.findAllExString(ex));
            throw ex;
        } finally {
            // trace//
            cmd.setOutputResult(TracerProcessor.silenceToStr(result));
            cmd.setExecEnd(new Date());
            cmd.setExecDuration(cmd.getExecEnd().getTime() - cmd.getExecStart().getTime());
            tracerProcessor.doLog(cmd);
        }
    }

    private String parseInput(Object[] args) {
        try {
            return JSON.toJSONString(args);
        } catch (Exception ex) {
            LOGGER.error("parse object to string error." + ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
