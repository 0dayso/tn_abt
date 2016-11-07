package com.tuniu.abt.service.cancel;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.service.cancel.impl.AirlineCancelProcessor;
import com.tuniu.abt.service.cancel.impl.CtripCancelProcessor;
import com.tuniu.abt.service.cancel.impl.TravelSkyCancelProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * CancelProcessorFactory
 * Created by chengyao on 2016/4/11.
 */
@Component
public class CancelProcessorFactory {

    @Resource
    private CtripCancelProcessor ctripCancelProcessor;

    @Resource
    private TravelSkyCancelProcessor travelSkyCancelProcessor;

    @Resource
    private AirlineCancelProcessor airlineCancelProcessor;

    /**
     * 获取 CancelProcessor
     * @param vendorId 供应商id
     * @return CancelProcessor
     */
    CancelProcessor findProcessor(int vendorId) {
        switch (vendorId) {
            case BizzConstants.V_CTRIP:
                return ctripCancelProcessor;
            case BizzConstants.V_TS:
                return travelSkyCancelProcessor;
            case BizzConstants.V_AIRLINE:
                return airlineCancelProcessor;
            default:
                return travelSkyCancelProcessor;
        }
    }

}
