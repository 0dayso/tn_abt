package com.tuniu.abt.service.book.async;

import com.tuniu.abt.base.tracer.SplitTracerAsyncTask;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.service.travelsky.dto.PatByPnrRes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * pnr异步穿入运价
 * Created by lanlugang on 2016/4/16.
 */
@Service
public class PatByPnrStoreAsyncTask extends SplitTracerAsyncTask<Object, Map<String, Object>> {

    private static final long serialVersionUID = -3618257353879775632L;

    @Resource
    private TravelSkyInterface travelSkyInterface;

    @Override
    protected Object traceInput(String callbackServiceName, Map<String, Object> param, FlatTraceInfo tracerCommand) {
        tracerCommand.setBizCmd(TracerCmdEnum.PNR_PRICE_STORE_ASYNC.name());
        return param;
    }

    @Override
    protected Object traceOutput(Object result, FlatTraceInfo tracerCommand) {
        return null;
    }

    @Override
    protected Object exec(Map<String, Object> param) throws Exception {
        List<PatByPnrRes> patByPnrResList = (List<PatByPnrRes>) param.get("requests");
        Integer systemId = (Integer) param.get("systemId");
        for (PatByPnrRes patByPnrRes : patByPnrResList) {
            travelSkyInterface.patPriceByPnrStore(patByPnrRes, systemId);
        }
        return null;
    }
}
