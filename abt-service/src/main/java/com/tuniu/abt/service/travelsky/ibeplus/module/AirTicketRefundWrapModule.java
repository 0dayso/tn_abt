package com.tuniu.abt.service.travelsky.ibeplus.module;

import com.travelsky.espeed.*;
import com.tuniu.abt.service.travelsky.dto.RefundPriceResponse;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusInterfaceService;
import com.tuniu.abt.service.travelsky.ibeplus.converter.IbePlusRefundConverter;
import com.tuniu.abt.utils.SystemConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * ibe+ 退票功能封装
 * Created by chengyao on 2016/4/13.
 */
@Service
public class AirTicketRefundWrapModule {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private IbePlusInterfaceService ibePlusInterfaceService;


    /**
     * 退票操作，返回价格信息
     * @param ticketNo 票号
     * @return 价格组
     */
    public RefundPriceResponse ticketRefundWithPrice(String ticketNo) {
        return IbePlusRefundConverter.toPriceResponse(ticketRefund(ticketNo));
    }

    /**
     * 查询退票信息
     * @param ticketNo 票号
     * @return 价格组
     */
    public RefundPriceResponse queryTicketRefundPrice(String ticketNo) {
        List<String> ticketNos = new ArrayList<String>();
        ticketNos.add(ticketNo);
        return IbePlusRefundConverter.toPriceResponse(queryTicketRefundPrice(ticketNos));
    }

    /**
     * 退票操作，返回原始报文
     * @param ticketNo 票号
     * @return 原始报文
     */
    public TESAirTicketRefundRS ticketRefund(String ticketNo) {
        List<String> ticketNos = new ArrayList<String>();
        ticketNos.add(ticketNo);
        TESAirTicketRefundRS resp = airTicketRefund(ticketNos, false);
        airTicketRefund(ticketNos, true);
        return resp;
    }

    private TESAirTicketRefundRS queryTicketRefundPrice(List<String> ticketNos) {
        BigInteger pno = BigInteger.valueOf(Long.parseLong(systemConfig.getIbeplusPrinterNo()));

        TESAirTicketRefundRQ request = new TESAirTicketRefundRQ();
        // POS
        request.setPOS(new POS());
        request.getPOS().getSource().add(new Source());
        request.getPOS().getSource().get(0).setPseudoCityCode(systemConfig.getIbeplusOfficeNo());

        // TicketRefundInfoDetails
        request.setTicketRefundInfoDetails(new TicketRefundInfoDetails());
        for (String ticketNumber : ticketNos) {
            TicketRefundInfoDetail ticketRefundInfoDetail = new TicketRefundInfoDetail();
            ticketRefundInfoDetail.setTicketItemInfo(new TicketItemInfo());
            ticketRefundInfoDetail.getTicketItemInfo().setTicketNumber(ticketNumber);
            ticketRefundInfoDetail.setPrinterInfo(new PrinterInfo());
            ticketRefundInfoDetail.getPrinterInfo().setNumber(pno);
            request.getTicketRefundInfoDetails().getTicketRefundInfoDetail().add(ticketRefundInfoDetail);
        }
        return ibePlusInterfaceService.ticketRefundPrice(request);
    }


    private TESAirTicketRefundRS airTicketRefund(List<String> ticketNos, boolean confirm) {
        BigInteger pno = BigInteger.valueOf(Long.parseLong(systemConfig.getIbeplusPrinterNo()));

        TESAirTicketRefundRQ request = new TESAirTicketRefundRQ();
        // POS
        request.setPOS(new POS());
        request.getPOS().getSource().add(new Source());
        request.getPOS().getSource().get(0).setPseudoCityCode(systemConfig.getIbeplusOfficeNo());

        // TicketRefundInfoDetails
        request.setTicketRefundInfoDetails(new TicketRefundInfoDetails());
        for (String ticketNumber : ticketNos) {
            TicketRefundInfoDetail ticketRefundInfoDetail = new TicketRefundInfoDetail();
            ticketRefundInfoDetail.setReturnRefundFormInfoInd(Boolean.TRUE);
            ticketRefundInfoDetail.setConfirmRefundInd(confirm);
            ticketRefundInfoDetail.setTicketItemInfo(new TicketItemInfo());
            ticketRefundInfoDetail.getTicketItemInfo().setTicketNumber(ticketNumber);
            ticketRefundInfoDetail.setPrinterInfo(new PrinterInfo());
            ticketRefundInfoDetail.getPrinterInfo().setNumber(pno);
            ticketRefundInfoDetail.getPrinterInfo().setType(ETicketType.BSP_DOMESTIC_ETICKET);
            request.getTicketRefundInfoDetails().getTicketRefundInfoDetail().add(ticketRefundInfoDetail);
        }
        return confirm ? ibePlusInterfaceService.ticketRefundConfirm(request) : ibePlusInterfaceService.ticketRefund(request);
    }

}
