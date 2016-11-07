package com.tuniu.abt.base.tracer;

import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.vla.base.tsp.TspClientInvocationListener;
import com.tuniu.vla.base.tsp.TspClientRunStat;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用于TSP输出CMD日志的LISTENER
 *
 * Created by chengyao on 2016/3/2.
 */
public class TspClientTracerListener implements TspClientInvocationListener {

    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;
    @Resource
    private TracerProcessor tracerProcessor;

    @Override
    public void statue(TspClientRunStat status) {
        FlatTraceInfo cmd = TracerProcessor.makeCmd();

        cmd.setExecStart(new Date(status.getStart()));
        cmd.setExecEnd(new Date(status.getEnd()));
        cmd.setExecDuration(status.getEnd() - status.getStart());
        cmd.setErrorDesc(exceptionMessageUtils.findCodeString(status.getThrowable()));
        cmd.setErrorDesc(exceptionMessageUtils.findAllExString(status.getThrowable()));
        cmd.setInputParam(status.getInput());
        cmd.setOutputResult(status.getOutput());
        cmd.setExecStatus(status.isSuccess() ? FlatTraceInfo.SUCCESS : FlatTraceInfo.FAIL);
        cmd.setBizCmd(TracerCmdEnum.TSP_CALL.name());
        cmd.setBizType(status.getTspRequestSetting().getServiceName());

        tracerProcessor.doLog(cmd);
    }
}
