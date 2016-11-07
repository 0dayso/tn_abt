package com.tuniu.abt.service.travelsky.ibe.module;

import com.travelsky.ibe.client.pnr.RTResult;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/14.
 */
@Service
public class IbeRtWrapModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(IbeRtWrapModule.class);
    @Resource
    private IbeInterfaceService ibeInterfaceService;

    public RTResult rt(String pnr) {
        RTResult rtResult = null;
        try {
            rtResult = ibeInterfaceService.rtPnr(pnr);
        } catch (Exception e) {
            LOGGER.error("【机票IBE方式RT】出错, PNR:" + pnr + "，错误原因：" + e.getMessage(), e);
            throw new BizzException(BizzEx.IBE_INTF_EX, e.getMessage(), e);
        }
        if (null == rtResult) {
            throw new BizzException(BizzEx.IBE_INTF_RESULT_NONE, "ibe RT result is null, pnrNo = " + pnr);
        }
        return rtResult;
    }

}
