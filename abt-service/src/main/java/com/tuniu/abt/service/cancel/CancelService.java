package com.tuniu.abt.service.cancel;

import com.tuniu.abt.dbservice.AbtPnrMainService;
import com.tuniu.abt.intf.dto.cancel.CancelOrderGroup;
import com.tuniu.abt.intf.dto.cancel.CancelResult;
import com.tuniu.abt.intf.dto.cancel.ProcCancelData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 取消操作服务
 *
 * Created by chengyao on 2016/2/5.
 */
@Service
public class CancelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelService.class);

    @Resource
    private AbtPnrMainService abtPnrMainService;

    @Resource
    private CancelProcessorFactory cancelProcessorFactory;

    public Object cancel(ProcCancelData cancelData) {

        // 准备通用的数据，request对象，pnr表数据
        int vendorId = cancelData.getAbtBookingRequest().getVendorId();
        List<CancelOrderGroup> cancelOrders = cancelData.getCancelOrderGroups();

        List<CancelResult> results = cancelProcessorFactory.findProcessor(vendorId).cancelPnrs(cancelOrders);
        cancelData.setCancelResult(results);

        // 更新pnr表的占位状态为取消成功、或失败
        abtPnrMainService.updateCancelStatus(results, cancelData.getPnrs());
        return results;
    }


}
