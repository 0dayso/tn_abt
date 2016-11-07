package com.tuniu.abt.schedule;

import com.tuniu.abt.dao.AbtBookingRequestDao;
import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.intf.dto.cancel.ReqCancel;
import com.tuniu.abt.intf.dto.cancel.ReqCancelDetail;
import com.tuniu.abt.intf.dto.cancel.ReqCancelPnr;
import com.tuniu.abt.intf.entity.AbtBookingRequest;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.service.cancel.CancelFacadeService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试环境取消pnr的定时任务
 * 注意：生产环境不要调用
 * Created by lanlugang on 2016/4/13.
 */
@Component
@Profile({"test","sit","local"})
public class CancelPnrInTestEnvTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelPnrInTestEnvTask.class);
    @Resource
    private CancelFacadeService cancelFacadeService;
    @Resource
    private AbtBookingRequestDao abtBookingRequestDao;
    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    public void execute() {
        // 查询数据
        List<ReqCancel> reqCancelList = queryBookDataInDb();
        // 发起清位
        for (ReqCancel reqCancel : reqCancelList) {
            try {
                cancelFacadeService.cancel(reqCancel);
            } catch (Exception e) {
                LOGGER.error("测试环境定时清位异常,订单号:{}", reqCancel.getOrderId(), e);
            }
        }
    }

    private List<ReqCancel> queryBookDataInDb() {
        List<ReqCancel> reqCancelList = new ArrayList<ReqCancel>();
        // 查当天占位成功的数据
        List<AbtBookingRequest> bookingRequests = abtBookingRequestDao.queryCurDateRequestsByStatus(1);
        ReqCancel reqCancel = null;
        ReqCancelDetail cancelDetail = null;
        List<ReqCancelPnr> cancelPnrs = null;
        for (AbtBookingRequest abtBookingRequest : bookingRequests) {
            reqCancel = new ReqCancel();
            reqCancel.setOrderId(abtBookingRequest.getOrderId());
            reqCancel.setSystemId(abtBookingRequest.getSystemId());
            reqCancel.setTransId(abtBookingRequest.getSessionId());
            reqCancel.setVendorId(abtBookingRequest.getVendorId());
            reqCancel.setChannel("C");// 默认值随便写的
            reqCancel.setSource("S");
            cancelDetail = new ReqCancelDetail();
            reqCancel.setCancelDetail(cancelDetail);
            cancelDetail.setFlightItemId(abtBookingRequest.getFlightItemId());
            cancelPnrs = new ArrayList<ReqCancelPnr>();
            cancelDetail.setCancelPnrs(cancelPnrs);
            List<AbtPnrMain> pnrs = abtPnrMainDao.findListByRequestId(abtBookingRequest.getId());
            ReqCancelPnr cancelPnr = null;
            for (AbtPnrMain pnr : pnrs) {
                if (pnr.getStatus() != AbtPnrMain.STATUS_INIT
                        && pnr.getStatus() != AbtPnrMain.STATUS_CANCEL_FAIL) {
                    continue;
                }
                if (pnr.getStatus() == AbtPnrMain.STATUS_CANCEL_FAIL) {
                    abtPnrMainDao.updateStatus(pnr.getId(), AbtPnrMain.STATUS_INIT);
                }
                cancelPnr = new ReqCancelPnr();
                cancelPnr.setOrderId(pnr.getPnr());
                cancelPnrs.add(cancelPnr);
            }
            if (CollectionUtils.isNotEmpty(cancelPnrs)) {
                reqCancelList.add(reqCancel);
            }
        }
        return reqCancelList;
    }

}
