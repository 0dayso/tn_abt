package com.tuniu.vla.base.tsp.annotation;

import java.lang.annotation.*;

/**
 * TSP接口方法注解，设置方法对应的服务名
 *
 * Created by chengyao on 2015/11/16.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TspMethod {

    /**
     * 定义服务名，必须要输入
     * @return 服务名
     */
    String serviceName();

    /**
     * 调用的方法（GET、POST、DELETE、PUT）。
     * @return 调用的方法 默认null
     */
    String method() default "";

    /**
     * 调用请求数据是否log ，默认true 。
     * @return 调用请求数据是否log
     */
    boolean requestLog() default true;

    /**
     * 调用返回数据是否log ，默认 true 。
     * @return 调用返回数据是否log
     */
    boolean responseLog() default true;

    /**
     * 调用特定地域的服务，默认调用与调用者在同一地域的服务。
     * @return 默认null
     */
    String wantRegion() default "";

    /**
     * 请求参数自动封装为String
     * @return 默认true
     */
    boolean wrapReq() default true;

    /**
     * 返回结果自动封装为String
     * @return 默认true
     */
    boolean wrapResp() default true;

    /**
     * http connect timeout 参数，单位秒
     * @return connectTimeout
     */
    int connectTimeout() default -1;

    /**
     * http socket timeout 参数，单位秒
     * @return scoketTimeout
     */
    int socketTimeout() default -1;
}
