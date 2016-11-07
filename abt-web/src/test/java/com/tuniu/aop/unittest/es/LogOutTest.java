package com.tuniu.aop.unittest.es;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;

import java.util.Date;

/**
 * Created by chengyao on 2016/3/14.
 */
public class LogOutTest {

    public static void main(String[] args) {

        FlatTraceInfo flatTraceInfo = new FlatTraceInfo();
        flatTraceInfo.setBizAction(TracerActionEnum.BOOK.name());
        flatTraceInfo.setBizCmd(TracerCmdEnum.ACTION_ASYNC.name());
        flatTraceInfo.setBizType("type");
        flatTraceInfo.setErrorCode("10002201");
        flatTraceInfo.setErrorDesc("错误信息说明");
        flatTraceInfo.setExecDuration(203);
        flatTraceInfo.setExecStart(new Date());
        flatTraceInfo.setExecEnd(new Date(System.currentTimeMillis() + 203));
        flatTraceInfo.setExecStatus(FlatTraceInfo.SUCCESS);
        flatTraceInfo.setInputParam("{\"input\":\"1\"}");
        flatTraceInfo.setOutputResult("{\"output\":\"1\"}");
        flatTraceInfo.setLevel(1);
        flatTraceInfo.setRemoteHost("172.1.12.1");
        flatTraceInfo.setRemotePort(12922);
        flatTraceInfo.setServerName("localhost");
        flatTraceInfo.setServerPort(8080);
        flatTraceInfo.setRequestURI("/abt/book/book");
        flatTraceInfo.setRequestUrl("http://localhost:8080/abt/book/book");
        flatTraceInfo.setTraceId("uuid");
        flatTraceInfo.setSessionId("99928111");
        flatTraceInfo.setSystemId("51");
        flatTraceInfo.setVendorId("6");
        flatTraceInfo.setOrderId("119");
        System.out.println(JSON.toJSONString(flatTraceInfo));
    }
}
