package com.tuniu.abt.service.travelsky.ibe.module;

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
public class IbeIssueWrapModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbeIssueWrapModule.class);

    private static final int IBE_PRINTER_NO = 1;

    @Resource
    private IbeInterfaceService ibeInterfaceService;

    public void issueTicketByBSP(String pnrNo) {
        try {
            String res = ibeInterfaceService.issueTicket(pnrNo, IBE_PRINTER_NO);
            if (!"OK".equals(res) || res.indexOf("Error") != -1) {
                throw new BizzException(BizzEx.IBE_INTF_RESULT_ERROR,
                        "BSP出票失败, pnr:" + pnrNo + "失败原因:" + res);
            }
        } catch (BizzException bizEx) {
            throw bizEx;
        } catch (Exception e) {
            if(e.getMessage().indexOf("PNR TICKETED") != -1) {
                LOGGER.error(pnrNo + "已出票", e);
            } else {
                throw new BizzException(BizzEx.IBE_INTF_EX,
                        "BSP出票异常, pnr:" + pnrNo + ",异常信息:" + e.getMessage(), e);
            }
        }

    }

}
