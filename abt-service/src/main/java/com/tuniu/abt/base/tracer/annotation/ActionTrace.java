package com.tuniu.abt.base.tracer.annotation;

import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action跟踪注解
 * Created by chengyao on 2016/2/29.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionTrace {

    /**
     * actionCode
     */
    TracerActionEnum action();

    /**
     * 把请求内容作为input, 如果为true，则 input 参数不起作用
     */
    boolean recordBody() default true;

    /**
     * 输入对象表达式(SPEL)
     */
    String input() default "";

    /**
     * 输出返回值对象。设为true后，output参数无用
     */
    boolean outputReturn() default true;

    /**
     * 输出对象表达式(SPEL)
     */
    String output() default "";
}
