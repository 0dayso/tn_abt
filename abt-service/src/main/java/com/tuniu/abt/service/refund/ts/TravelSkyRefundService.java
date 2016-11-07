package com.tuniu.abt.service.refund.ts;

import com.travelsky.espeed.OTAAirResRetRS;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.cancel.RtForDelete;
import com.tuniu.abt.intf.dto.refund.ProcRefundData;
import com.tuniu.abt.intf.dto.refund.RespRefund;
import com.tuniu.abt.intf.dto.refund.RespRefundItem;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import com.tuniu.abt.intf.entity.AbtRefundItem;
import com.tuniu.abt.intf.entity.AbtRefundOrder;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.cancel.TravelSkyDelPassengerService;
import com.tuniu.abt.service.refund.RefundCommonUtils;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.service.travelsky.dto.RefundPriceResponse;
import com.tuniu.abt.service.travelsky.dto.ReqPassenger;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.zkconfig.client.utils.ConfigApi;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by chengyao on 2016/4/13.
 */
@Service
public class TravelSkyRefundService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelSkyRefundService.class);
    @Resource
    private TravelSkyInterface travelSkyInterface;
    @Resource
    private TravelSkyDelPassengerService travelSkyDelPassengerService;
    @Resource
    private SystemConfig systemConfig;
    @Resource
    private ConfigApi configApi;

    public RespRefund refund(ProcRefundData procRefundData) {
        List<AbtRefundItem> refundItems = procRefundData.getAbtRefundItems();
        boolean auto = AbtRefundOrder.REFUND_SOURCE_A.equals(procRefundData.getAbtRefundOrder().getRefundSource());

        // 检验阀值
        if (auto) {
            RefundCommonUtils.checkDifferenceThreshold(refundItems, systemConfig.getRefundDifferenceThreshold());
        }

        RespRefund respRefund = new RespRefund();
        respRefund.setItems(new ArrayList<RespRefundItem>());

        List<AbtTicketMain> refundTickets = procRefundData.getRefundTicketMains();

        // 按pnr分组
        Map<String, List<AbtTicketMain>> refundTicketMainGroup = new HashedMap<String, List<AbtTicketMain>>();
        for (AbtTicketMain refundTicket : refundTickets) {
            String pnr = refundTicket.getPnr();
            if (!refundTicketMainGroup.containsKey(pnr)) {
                refundTicketMainGroup.put(pnr, new ArrayList<AbtTicketMain>());
            }
            refundTicketMainGroup.get(pnr).add(refundTicket);
        }

        for (String pnr : refundTicketMainGroup.keySet()) {
            List<AbtTicketMain> abtTicketMains = refundTicketMainGroup.get(pnr);

            List<AbtPnrPassenger> delBabies = new ArrayList<AbtPnrPassenger>();
            List<ReqPassenger> splitPassengers = new ArrayList<ReqPassenger>();
            for (AbtTicketMain abtTicketMain : abtTicketMains) {
               // 放入要处理的婴儿数据
                if (abtTicketMain.getPassengerType() == AbtTicketMain.PASSENGER_TYPE_BABY) {
                    delBabies.add(new AbtPnrPassenger());
                    delBabies.get(0).setBookName(abtTicketMain.getPassengerName());
                    delBabies.get(0).setPassengerType(abtTicketMain.getPassengerType());
                    continue;
                }

                // 放入要分离的对象
                ReqPassenger reqPassenger = new ReqPassenger();
                reqPassenger.setPassengerName(abtTicketMain.getPassengerName());
                reqPassenger.setPassengerType(abtTicketMain.getPassengerType());
                splitPassengers.add(reqPassenger);
            }

            // 假接口
            if (systemConfig.isProduct() || !"true".equals(configApi.get("useFakeRefund"))) {
                processInterfaceRefund(respRefund, pnr, abtTicketMains, delBabies, splitPassengers);
            } else {
                fakeProcessInterfaceRefund(respRefund, refundItems, abtTicketMains);
            }

            RefundCommonUtils.updateDealStatus(abtTicketMains, procRefundData);
        }

        procRefundData.getAbtRefundOrder().setStatus(AbtRefundOrder.STATUS_OK);

        return respRefund;
    }

    // 测试环境假退票，只写数据库
    private void fakeProcessInterfaceRefund(RespRefund respRefund, List<AbtRefundItem> items,
            List<AbtTicketMain> abtTicketMains) {
        // 退票。对应单个PNR中包含的票
        for (AbtTicketMain abtTicketMain : abtTicketMains) {
            AbtRefundItem abtRefundItem = RefundCommonUtils.findByTicketNo(items, abtTicketMain.getTicketNo());
            if (abtRefundItem == null) {
                throw new BizzException(BizzEx.COMM_EX_WRAP, "can't find abt_refund_item. ticketNo=" + abtTicketMain.getTicketNo());
            }
            RespRefundItem respRefundItem = new RespRefundItem();
            respRefundItem.setTicketNo(abtTicketMain.getTicketNo());
            respRefundItem.setRefundAmount(new BigDecimal(abtRefundItem.getRealRefundAmount().toString()));
            respRefund.getItems().add(respRefundItem);
        }
    }

    // 处理接口退票
    private void processInterfaceRefund(RespRefund respRefund, String pnr, List<AbtTicketMain> abtTicketMains,
            List<AbtPnrPassenger> delBabies, List<ReqPassenger> splitPassengers) {

        // 处理PNR
        dealPnr(pnr, delBabies, splitPassengers);

        // 退票。对应单个PNR中包含的票
        for (AbtTicketMain abtTicketMain : abtTicketMains) {
            RefundPriceResponse refundPriceResponse = travelSkyInterface.ticketRefundWithPrice(abtTicketMain.getTicketNo());
            BigDecimal refundAmount = countRefundAmount(refundPriceResponse);

            RespRefundItem respRefundItem = new RespRefundItem();
            respRefundItem.setTicketNo(abtTicketMain.getTicketNo());
            respRefundItem.setRefundAmount(refundAmount);
            respRefund.getItems().add(respRefundItem);
        }
    }

    private void dealPnr(String pnr, Collection<AbtPnrPassenger> delBabies, List<ReqPassenger> splitPassengers) {
        // RT操作
        OTAAirResRetRS.AirResRet airResRet;
        try {
            airResRet = travelSkyInterface.rt(pnr);
        } catch (Exception e) {
            if (e.getMessage().contains("PNR:"+pnr+" was entirely cancelled.")) {
                LOGGER.warn("待退票的PNR已取消，略过处理PNR的操作");
                return;
            }
            throw new BizzException(BizzEx.IBE_INTF_RESULT_ERROR, "RT获取PNR信息异常", e);
        }

        // 从pnr中删除要退票的婴儿
        if (CollectionUtils.isNotEmpty(delBabies)) {
            RtForDelete rtForDelete = CommonUtils.transform(airResRet, RtForDelete.class);
            travelSkyDelPassengerService.dealDeleteBabies(rtForDelete, delBabies);
        }

        // 处理需要从pnr中分离的乘客
        if (CollectionUtils.isNotEmpty(splitPassengers)) {
            // 分离pnr
            String newPnr = travelSkyInterface.splitPnr(pnr, splitPassengers);
            // 取消pnr
            travelSkyInterface.cancelPnr(newPnr, airResRet.getResponsibility().getOfficeCode());
        }

        // ibe文档描述，等待6秒
        // 1，上述自动退票操作需要在出票代理人本OFFICE配置下做。
        // 2，自动生成退票单的操作（TRFD:Z）与上一步的删除PNR的操作之间的时间间隔最好大于5秒，否则可能会提示自动退票单数据还未生成。
        // 3，该自动退票操作会自动修改票面状态为Refunded，不需要再做ETRF
        try {
            Thread.sleep(6000L);
        } catch (InterruptedException e) {
            LOGGER.debug("wait after cancel pnr thread InterruptedException.", e);
        }
    }

    private BigDecimal countRefundAmount(RefundPriceResponse refundPriceResponse) {
        return refundPriceResponse.getDeduction();
    }


}
