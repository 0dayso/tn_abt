package com.tuniu.abt.base.tracer.interceptor;

import com.tuniu.abt.base.tracer.TracerProcessor;
import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * cmd 跟踪注解处理aop
 */
@Component
@Aspect
public class CommandTracerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandTracerInterceptor.class);

    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

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
    @Around("execution(@com.tuniu.abt.base.tracer.annotation.CommandTrace * *(..))")
    public Object aroundCommandTrace(ProceedingJoinPoint pjp) throws Throwable { //NOSONAR
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        CommandTrace commandTrace = method.getAnnotation(CommandTrace.class);

        // trace//
        FlatTraceInfo cmd = TracerProcessor.makeCmd();
        cmd.setBizCmd(commandTrace.name().name());
        cmd.setExecStart(new Date());
        if (StringUtils.hasText(commandTrace.input())) {
            Object inputParam = EvaluationUtil.getExpressionValue(evaluator, pjp, method, commandTrace.input());
            cmd.setInputParam(TracerProcessor.silenceToStr(inputParam));
        }

        String identity = commandTrace.type();
        if (StringUtils.hasText(identity)) {
            cmd.setBizType(String.valueOf(EvaluationUtil.getExpressionValue(evaluator, pjp, method, identity)));
        }

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
            // 异常的情况，或设置为只处理异常=false
            if (!commandTrace.onlyException() || cmd.getExecStatus() == FlatTraceInfo.FAIL) {
                // trace//
                if (commandTrace.outputReturn()) {
                    cmd.setOutputResult(TracerProcessor.silenceToStr(result));
                } else if (StringUtils.hasText(commandTrace.output())) {
                    cmd.setOutputResult(TracerProcessor.silenceToStr(
                            EvaluationUtil.getExpressionValue(evaluator, pjp, method, commandTrace.output())));
                }
                cmd.setExecEnd(new Date());
                cmd.setExecDuration(cmd.getExecEnd().getTime() - cmd.getExecStart().getTime());
                tracerProcessor.doLog(cmd);
            }
        }
    }

}