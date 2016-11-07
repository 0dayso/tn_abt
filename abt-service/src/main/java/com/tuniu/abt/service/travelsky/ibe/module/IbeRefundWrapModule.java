package com.tuniu.abt.service.travelsky.ibe.module;

import com.travelsky.ibe.client.pnr.TRFDResult;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.RefundPriceResponse;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import com.tuniu.abt.service.travelsky.ibe.converter.IbeRefundConverter;
import com.tuniu.abt.utils.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ibe 退票功能封装
 * Created by chengyao on 2016/4/13.
 */
@Service
public class IbeRefundWrapModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbeRtWrapModule.class);
    @Resource
    private IbeInterfaceService ibeInterfaceService;
    @Resource
    private SystemConfig systemConfig;

    /**
     * 退票操作，返回价格信息
     * @param ticketNo 票号
     * @return 价格组
     */
    public RefundPriceResponse ticketRefundWithPrice(String ticketNo) {
        return IbeRefundConverter.toPriceResponse(ticketRefund(ticketNo));
    }

    /**
     * 退票操作，返回原始信息
     * @param ticketNo 票号
     * @return 原始报文
     */
    public TRFDResult ticketRefund(String ticketNo) {
        try {
            int printerNo = Integer.parseInt(systemConfig.getIbeplusPrinterNo());
            TRFDResult trfdResult = ibeInterfaceService.automaticRefund(ticketNo, printerNo);
            ibeInterfaceService.confirmAutomaticRefund(ticketNo, printerNo, trfdResult);
            return trfdResult;
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "执行退票异常.", e);
        }
    }



}
