package com.tuniu.abt.base.tracer;

import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.vla.base.taskpool.AbstractAsyncTask;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 提供单独处理跟踪日志的异步Task封装。
 *
 * 说明：对于某些异步执行任务，它可能在调用方结束后还在执行，所以不能按照标准的模式走，
 * 找到TracerInfo，获取基础信息，和TracerCommand合并单独输出，不再把TracerCommand合并到TracerInfo中。
 *
 * Created by chengyao on 2016/3/5.
 */
public abstract class SplitTracerAsyncTask<Z, T> extends AbstractAsyncTask<Z, T> {

    private static final long serialVersionUID = 7855586712388597226L;

    private FlatTraceInfo tracerCommand;

    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;
    @Resource
    private TracerProcessor tracerProcessor;

    /**
     * 异步执行前，处理TracerCommand， 需填入type, identity, input
     * @param callbackServiceName callbackServiceName
     * @param param param
     * @param tracerCommand FlatTraceInfo
     */
    abstract protected Object traceInput(String callbackServiceName, T param, FlatTraceInfo tracerCommand);

    /**
     * 执行成功后，处理TracerCommand, 需填入output
     * @param result result
     * @param tracerCommand FlatTraceInfo
     */
    abstract protected Object traceOutput(Z result, FlatTraceInfo tracerCommand);

    @Override
    protected void beforeExecute(String callbackServiceName, T param) {
        this.tracerCommand = new FlatTraceInfo();
        this.tracerCommand.setExecStart(new Date());
        Object input = traceInput(callbackServiceName, param, tracerCommand);
        tracerCommand.setInputParam(TracerProcessor.silenceToStr(input));
    }

    @Override
    protected void onSuccess(String callbackServiceName, Z result) {
        TracerProcessor.fillTracerToCmd(tracerCommand);
        Object output = traceOutput(result, tracerCommand);
        tracerCommand.setOutputResult(TracerProcessor.silenceToStr(output));
        tracerCommand.setExecEnd(new Date());
        tracerCommand.setExecDuration(tracerCommand.getExecEnd().getTime() - tracerCommand.getExecStart().getTime());

        tracerProcessor.doLog(tracerCommand);
    }

    @Override
    protected void onException(String callbackServiceName, Exception ex) {
        TracerProcessor.fillTracerToCmd(tracerCommand);
        Object output = traceOutput(null, tracerCommand);
        tracerCommand.setOutputResult(TracerProcessor.silenceToStr(output));
        tracerCommand.setExecEnd(new Date());
        tracerCommand.setExecDuration(tracerCommand.getExecEnd().getTime() - tracerCommand.getExecStart().getTime());
        tracerCommand.setExecStatus(FlatTraceInfo.FAIL);
        tracerCommand.setErrorCode(exceptionMessageUtils.findCodeString(ex));
        tracerCommand.setErrorDesc(exceptionMessageUtils.findAllExString(ex));

        tracerProcessor.doLog(tracerCommand);
    }

}
