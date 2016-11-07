package com.tuniu.abt.base.tracer.annotation;

import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Command跟踪注解
 * Created by chengyao on 2016/2/29.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandTrace {

    /**
     * Command类型，文本，标识指令的名称
     */
    TracerCmdEnum name();

    /**
     * 同一种类型再进行细分
     */
    String type() default "";

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

    /**
     * 只记录异常
     */
    boolean onlyException() default false;
}
