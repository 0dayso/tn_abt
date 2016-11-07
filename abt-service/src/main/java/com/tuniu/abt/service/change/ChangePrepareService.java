package com.tuniu.abt.service.change;

import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.dao.AbtTicketMainDao;
import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.ChangeEx;
import com.tuniu.abt.intf.constants.RefundEx;
import com.tuniu.abt.intf.dto.change.ProcChangeData;
import com.tuniu.abt.intf.dto.change.ReqChange;
import com.tuniu.abt.intf.dto.change.ReqChangeItem;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.refund.RefundDataSupport;
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
public class ChangePrepareService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangePrepareService.class);
    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;
    @Resource
    private AbtTicketMainDao abtTicketMainDao;

    /**
     * 检查入参，准备数据表记录
     * @param request
     */
    void checkAndPrepareData(@Valid ReqChange request) {
        ProcChangeData procChangeData = new ProcChangeData();

        // 找到出票请求数据
        final AbtTicketRequest abtTicketRequest = abtTicketRequestDao.findByOrder(request.getOrderId(), request.getFlightItemId());
        if (abtTicketRequest == null) {
            throw new BizzException(ChangeEx.CHANGE_FAIL, "未找到出票记录");
        }
        if (abtTicketRequest.getStatus() != AbtTicketRequest.ISSUE_SUCCESS) {
            throw new BizzException(ChangeEx.CHANGE_FAIL, "查询到出票状态不为已出票");
        }

        // 补充日志
        FlatTraceInfo flatTraceInfo = DataSharedSupport.getTracer();
        flatTraceInfo.setVendorId(abtTicketRequest.getVendorId().toString());
        flatTraceInfo.setSystemId(abtTicketRequest.getSystemId().toString());
        flatTraceInfo.setOrderId(abtTicketRequest.getOrderId().toString());

        procChangeData.setReqChange(request);
        procChangeData.setAbtTicketRequest(abtTicketRequest);

        // 找到ticket_main表数据（携程不需要）
        if (procChangeData.getAbtTicketRequest().getVendorId() != BizzConstants.V_CTRIP) {
            List<AbtTicketMain> refundTicketMains = buildChangeTicketMains(abtTicketRequest.getId(), request);
            procChangeData.setAbtTicketMains(refundTicketMains);
        }

        ChangeDataSupport.setData(procChangeData);
    }

    private List<AbtTicketMain> buildChangeTicketMains(Long ticketRequestId, ReqChange reqChange) {
        List<AbtTicketMain> ticketMains = abtTicketMainDao.findListByRequestId(ticketRequestId);

        // 整理出请求中的要退票票号
        List<ReqChangeItem> items = reqChange.getItems();
        Set<String> ticketNos = new HashSet<String>();
        for (ReqChangeItem item : items) {
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
