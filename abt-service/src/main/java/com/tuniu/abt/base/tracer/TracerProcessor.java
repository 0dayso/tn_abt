package com.tuniu.abt.base.tracer;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.vla.base.asyncexecutor.AsyncQueuedExecutor;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 业务跟踪日志工具
 *
 * Created by chengyao on 2016/2/29.
 */
@Component
public class TracerProcessor {

    private static final Logger LOGGER_LOCAL = LoggerFactory.getLogger(TracerProcessor.class);

    private static final Logger LOGGER_TRACER = LoggerFactory.getLogger("com.tuniu.abt.tracer");

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private AsyncQueuedExecutor asyncQueuedExecutor;

    public static void fillTracerToCmd(FlatTraceInfo cmd) {
        FlatTraceInfo action = DataSharedSupport.getTracer();
        cmd.setLevel(FlatTraceInfo.LEVEL_CMD);
        if (action != null) {
            cmd.setBizAction(action.getBizAction());
            cmd.setTraceId(action.getTraceId());
            cmd.setOrderId(action.getOrderId());
            cmd.setSessionId(action.getSessionId());
            cmd.setSystemId(action.getSystemId());
            cmd.setVendorId(action.getVendorId());
        }
    }

    public static FlatTraceInfo makeCmd() {
        FlatTraceInfo cmd = new FlatTraceInfo();
        fillTracerToCmd(cmd);
        return cmd;
    }

    public void actionSuccess() {
        FlatTraceInfo action = DataSharedSupport.getTracer();
        if (action != null) {
            action.setExecEnd(new Date());
            action.setExecDuration(action.getExecEnd().getTime() - action.getExecStart().getTime());
            action.setExecStatus(FlatTraceInfo.SUCCESS);
            doLog(action);
        }
    }

    public void actionFail(String errorCode, String errorDesc) {
        FlatTraceInfo action = DataSharedSupport.getTracer();
        if (action != null) {
            action.setExecEnd(new Date());
            action.setExecDuration(action.getExecEnd().getTime() - action.getExecStart().getTime());
            action.setExecStatus(FlatTraceInfo.FAIL);
            action.setErrorCode(errorCode);
            action.setErrorDesc(errorDesc);
            doLog(action);
        }
    }

    public void doLog(FlatTraceInfo object) {
        if (systemConfig.isProduct()) {
            LOGGER_TRACER.info("{}", JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss.SSS"));
        } else {
            LOGGER_TRACER.info("{}", object.toTestShowString());
        }
        asyncQueuedExecutor.offer(object);
    }

    public static String silenceToStr(Object o) {
        try {
            if (o == null) return null;
            if (o instanceof String) return (String) o;
            return JSON.toJSONString(o);
        } catch (Throwable ex) {
            LOGGER_LOCAL.error("parse object to string error." + ex.getMessage(), ex);
        }
        return null;
    }

}
