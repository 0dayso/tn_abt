package com.tuniu.vla.base.tsp;

/**
 * Created by chengyao on 2016/3/2.
 */
public class TspClientRunStatSupport {

    private static ThreadLocal<TspClientRunStat> tspClientRunStatThreadLocal = new ThreadLocal<TspClientRunStat>();

    private TspClientInvocationListener tspClientInvocationListener;

    private boolean recordRunStatus = false;

    public TspClientInvocationListener getTspClientInvocationListener() {
        return tspClientInvocationListener;
    }

    public void setTspClientInvocationListener(TspClientInvocationListener tspClientInvocationListener) {
        this.tspClientInvocationListener = tspClientInvocationListener;
    }

    public boolean isRecordRunStatus() {
        return recordRunStatus;
    }

    public void setRecordRunStatus(boolean recordRunStatus) {
        this.recordRunStatus = recordRunStatus;
    }


    public void statusStart(TspRequestSetting tspRequestSetting) {
        if (!recordRunStatus) return;
        TspClientRunStat status = new TspClientRunStat();
        status.setTspRequestSetting(tspRequestSetting);
        status.setStart(System.currentTimeMillis());
        tspClientRunStatThreadLocal.set(status);
    }

    public void statusInput(String input) {
        if (!recordRunStatus) return;
        TspClientRunStat status = tspClientRunStatThreadLocal.get();
        if (status != null) {
            status.setInput(input);
        }
    }

    public void statusOutput(String output) {
        if (!recordRunStatus) return;
        TspClientRunStat status = tspClientRunStatThreadLocal.get();
        if (status != null) {
            status.setOutput(output);
        }
    }

    public void statusEnd(boolean success, Throwable throwable) {
        if (!recordRunStatus) return;
        TspClientRunStat status = tspClientRunStatThreadLocal.get();
        if (status != null) {
            status.setEnd(System.currentTimeMillis());
            status.setSuccess(success);
            status.setThrowable(throwable);
            tspClientInvocationListener.statue(status);
            tspClientRunStatThreadLocal.remove();
        }
    }
}
