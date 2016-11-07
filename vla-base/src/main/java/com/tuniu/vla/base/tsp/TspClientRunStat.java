package com.tuniu.vla.base.tsp;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/2.
 */
public class TspClientRunStat implements Serializable {

    private static final long serialVersionUID = -8029999974716413671L;

    private TspRequestSetting tspRequestSetting;

    private long start;

    private long end;

    private boolean success;

    private Throwable throwable;

    private String input;

    private String output;

    public TspRequestSetting getTspRequestSetting() {
        return tspRequestSetting;
    }

    public void setTspRequestSetting(TspRequestSetting tspRequestSetting) {
        this.tspRequestSetting = tspRequestSetting;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
