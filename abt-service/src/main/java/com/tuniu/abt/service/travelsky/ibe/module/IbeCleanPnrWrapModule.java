package com.tuniu.abt.service.travelsky.ibe.module;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelsky.ibe.exceptions.PMNoPNRException;
import com.travelsky.ibe.exceptions.PMPNRCancelledException;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;

/**
 * 取消pnr
 * Created by chengyao on 2016/3/14.
 */
@Service
public class IbeCleanPnrWrapModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbeCleanPnrWrapModule.class);

    @Resource
    private IbeInterfaceService ibeInterfaceService;

    public void cleanPnr(String pnr) {
        cleanPnr(pnr, null);
    }

    /**
     * 清除PNR
     */
    public void cleanPnr(String pnr, String officeNo) {
        try {
            ibeInterfaceService.deletePnr(pnr, officeNo);
        } catch (PMPNRCancelledException e) {
            LOGGER.warn("PNR=" + pnr + "已被取消，本次操作视为操作取消成功", e);
        } catch (PMNoPNRException e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "PNR=" + pnr + "不存在", e);
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "PNR=" + pnr + "取消失败", e);
        }
    }

}
