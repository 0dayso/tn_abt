package com.tuniu.vla.base.constants;

/**
 * 系统异常定义常量
 *
 * @author chengyao
 */
public class SysEx {

    /** 入参校验 */
    public static final int VERIFY_INPUT_ERROR = 7081001;

    /**
     * 未捕获异常
     */
    public static final int UNKNOWN_EXCEPTION = 7100001;

    /**
     * IO异常
     */
    public static final int IO_EXCEPTION = 7100002;

    /**
     * SQL异常
     */
    public static final int SQL_EXCEPTION = 7100003;

    /**
     * TSP 无可用服务
     */
    public static final int TSP_NO_SERVICE_AVAILABLE = 7100005;

    /**
     * TSP 服务拒绝
     */
    public static final int TSP_SERVICE_FORBIDDEN = 7100006;

    /**
     * TSP客户端执行出错
     */
    public static final int TSP_CLIENT_INVOKE_ERROR = 7100007;

    /**
     * TSP服务返回失败回包
     */
    public static final int TSP_RESPONSE_FALSE = 7100010;

    /**
     * REST 执行出错
     */
    public static final int REST_CLIENT_ERROR = 7100008;

    /**
     * 单线程异步执行队列满
     */
    public static final int ASYNC_QUEUE_EXECUTOR_FULL = 7100010;

    /**
     * json入参解析异常
     */
    public static final int JSON_PARSE_EXCEPTION = 7100009;



}
