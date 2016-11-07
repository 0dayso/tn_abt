package com.tuniu.abt.service.refund;

import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.dao.AbtTicketMainDao;
import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.constants.RefundEx;
import com.tuniu.abt.intf.dto.refund.ProcRefundData;
import com.tuniu.abt.intf.dto.refund.ReqRefund;
import com.tuniu.abt.intf.dto.refund.ReqRefundItem;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 初步校验和准备先期数据
 * Created by chengyao on 2016/4/22.
 */
@Service
@Validated
public class RefundPrepareService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefundPrepareService.class);
    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;
    @Resource
    private AbtTicketMainDao abtTicketMainDao;

    /**
     * 检查入参，准备数据表记录
     * @param request
     */
    void checkAndPrepareData(@Valid ReqRefund request) {
        ProcRefundData procRefundData = new ProcRefundData();

        // 找到出票请求数据
        final AbtTicketRequest abtTicketRequest = abtTicketRequestDao.findByOrder(request.getOrderId(), request.getFlightItemId());
        if (abtTicketRequest == null) {
            throw new BizzException(RefundEx.REFUND_FAIL, "未找到出票记录");
        }
        if (abtTicketRequest.getStatus() != AbtTicketRequest.ISSUE_SUCCESS) {
            throw new BizzException(RefundEx.REFUND_FAIL, "查询到出票状态不为已出票");
        }

        // 补充日志
        FlatTraceInfo flatTraceInfo = DataSharedSupport.getTracer();
        flatTraceInfo.setVendorId(abtTicketRequest.getVendorId().toString());
        flatTraceInfo.setSystemId(abtTicketRequest.getSystemId().toString());
        flatTraceInfo.setOrderId(abtTicketRequest.getOrderId().toString());

        // 找到ticket_main表数据
        List<AbtTicketMain> refundTicketMains = buildRefundTicketMains(abtTicketRequest.getId(), request);
        procRefundData.setReqRefund(request);
        procRefundData.setAbtTicketRequest(abtTicketRequest);
        procRefundData.setRefundTicketMains(refundTicketMains);

        RefundDataSupport.setData(procRefundData);
    }

    private List<AbtTicketMain> buildRefundTicketMains(Long ticketRequestId, ReqRefund reqRefund) {
        List<AbtTicketMain> ticketMains = abtTicketMainDao.findListByRequestId(ticketRequestId);

        // 整理出请求中的要退票票号
        List<ReqRefundItem> items = reqRefund.getItems();
        Set<String> ticketNos = new HashSet<String>();
        for (ReqRefundItem item : items) {
            String ticketNo = item.getTicketNo();
            ticketNos.add(ticketNo);
        }

        // 选出符合的出票出记录
        List<AbtTicketMain> result = new ArrayList<AbtTicketMain>();
        Set<String> readyToRefunds = new HashSet<String>();
        for (AbtTicketMain ticketMain : ticketMains) {
            if (ticketNos.contains(ticketMain.getTicketNo()) && ticketMain.getStatus() == AbtTicketMain.STATUS_INIT) {
                result.add(ticketMain);
                readyToRefunds.add(ticketMain.getTicketNo());
            }
        }

        // 校验比对请求退票号都在记录中
        for (String ticketNo : ticketNos) {
            if (!readyToRefunds.contains(ticketNo)) {
                throw new BizzException(RefundEx.REFUND_FAIL, "退票票号：" + ticketNo + " 不在可退票记录中");
            }
        }

        return result;
    }

}
