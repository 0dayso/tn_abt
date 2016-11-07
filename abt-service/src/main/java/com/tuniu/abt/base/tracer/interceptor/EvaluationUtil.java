package com.tuniu.abt.base.tracer.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.expression.EvaluationContext;

import java.lang.reflect.Method;

/**
 * Created by chengyao on 2016/4/5.
 */
class EvaluationUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluationUtil.class);

    static Object getExpressionValue(ExpressionEvaluator expressionEvaluator, ProceedingJoinPoint pjp,
            Method method, String expressionStr) {
        Object result = null;
        if (org.springframework.util.StringUtils.hasText(expressionStr)) {
            try {
                Object target = pjp.getTarget();
                Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);
                if (targetClass == null) {
                    targetClass = target.getClass();
                }
                EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(method, pjp.getArgs(), target, targetClass,
                        ExpressionEvaluator.NO_RESULT);
                result = expressionEvaluator.expression(expressionStr, method, evaluationContext);
            } catch (Exception ex) {
                LOGGER.debug("parse expression error.", ex);
            }
        }
        return result;
    }
}
