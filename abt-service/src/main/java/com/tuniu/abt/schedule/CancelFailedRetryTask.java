package com.tuniu.abt.schedule;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.dao.AbtBookingRequestDao;
import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.cancel.ReqCancel;
import com.tuniu.abt.intf.dto.cancel.ReqCancelDetail;
import com.tuniu.abt.intf.dto.cancel.ReqCancelPnr;
import com.tuniu.abt.intf.entity.AbtBookingRequest;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.service.cancel.CancelFacadeService;
import com.tuniu.abt.service.cancel.TravelSkyDelPassengerService;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 取消失败的订单重新取消 定时任务 (目前仅支持取消pnr)
 * Created by lanlugang on 2016/5/25.
 */
@Component
public class CancelFailedRetryTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelFailedRetryTask.class);

    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private TravelSkyDelPassengerService travelSkyDelPassengerService;

    @Resource
    private AbtBookingRequestDao abtBookingRequestDao;

    public void process() {

        LOGGER.info("CancelFailedRetryTask --- 执行开始");
        List<AbtPnrMain> abtPnrMainList = queryCancelFailedPnrs();
        if (CollectionUtils.isEmpty(abtPnrMainList)) {
            LOGGER.info("从库中获取取消失败的pnr记录为0，CancelFailedRetryTask --- 执行结束");
            return;
        }

        int success = 0;
        int fail = 0;
        List<String> sucOrderIds = new ArrayList<>();
        List<String> failOrderIds = new ArrayList<>();
        for (AbtPnrMain abtPnrMain : abtPnrMainList) {
            try {
                int systemId = 0;
                AbtBookingRequest abtBookingRequest = abtBookingRequestDao.findById(abtPnrMain.getRequestId());
                if (null != abtBookingRequest) {
                    systemId = abtBookingRequest.getSystemId();
                }
                travelSkyDelPassengerService.cancelPnr(abtPnrMain.getPnr(), systemId);
                abtPnrMainDao.updateStatus(abtPnrMain.getId(), AbtPnrMain.STATUS_CANCEL_OK);
                sucOrderIds.add(abtPnrMain.getPnr());
                success++;
            } catch (Exception e) {
                LOGGER.error("CancelFailedRetryTask --- 执行当前清位异常,pnr:{}", abtPnrMain.getPnr(), e);
                failOrderIds.add(abtPnrMain.getPnr());
                fail++;
            }
        }

        if (success > 0) {
            LOGGER.info("CancelFailedRetryTask --- 取消成功订单数{}, pnrs:{}", success, JSON.toJSONString(sucOrderIds));
        }
        if (fail > 0) {
            LOGGER.info("CancelFailedRetryTask --- 取消失败订单数{}, pnrs:{}", fail, JSON.toJSONString(failOrderIds));
        }

        LOGGER.info("CancelFailedRetryTask --- 执行结束");

    }

    private List<AbtPnrMain> queryCancelFailedPnrs() {
        Date to = new Date();
        Date from = DateUtils.addMinutes(to, -systemConfig.getCancelRetryBeforeMinutes());
        List<AbtPnrMain> abtPnrMains = abtPnrMainDao.findByStatusAndSpecificTimeInterval(AbtPnrMain.STATUS_CANCEL_FAIL, from, to);
        if (CollectionUtils.isEmpty(abtPnrMains)) {
            return null;
        }
        //过滤非航信的pnr
        for (Iterator<AbtPnrMain> iterator = abtPnrMains.iterator(); iterator.hasNext();) {
            AbtPnrMain abtPnrMain = iterator.next();
            if (!abtPnrMain.getPnr().matches("[0-9A-Z]{6}")) {
                iterator.remove();
            }
        }
        return abtPnrMains;
    }

}
