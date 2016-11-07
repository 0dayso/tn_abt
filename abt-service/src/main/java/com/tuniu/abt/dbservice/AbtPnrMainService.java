package com.tuniu.abt.dbservice;

import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.dao.AbtPnrPassengerDao;
import com.tuniu.abt.intf.dto.cancel.CancelResult;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ABT PNR MAIN表数据库层面服务
 *
 * Created by chengyao on 2016/3/17.
 */
@Service
public class AbtPnrMainService {

    @Resource
    private AbtPnrMainDao abtPnrMainDao;
    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;

    /**
     * 批量更新pnr表的状态
     * @param abtPnrMains po对象列表
     * @param status 待修改状态位
     * @return 影响行数
     */
    @Transactional
    public int[] updateStatus(List<AbtPnrMain> abtPnrMains, int status) {
        int[] results = new int[0];
        for (AbtPnrMain abtPnrMain : abtPnrMains) {
            int result = abtPnrMainDao.updateStatus(abtPnrMain.getId(), status);
            ArrayUtils.add(results, result);
        }
        return results;
    }

    @Transactional
    public int[] updateStatusById(List<Long> pnrMainIds, int status) {
        int[] results = new int[0];
        for (Long id : pnrMainIds) {
            int result = abtPnrMainDao.updateStatus(id, status);
            ArrayUtils.add(results, result);
        }
        return results;
    }

    @Transactional
    public void updateCancelStatus(List<CancelResult> results, List<AbtPnrMain> cancelPnrMains) {
        for (CancelResult cancelResult : results) {
            List<AbtPnrPassenger> cancelPassengers = cancelResult.getCancelPassengers();
            //根据返回结果更新abt_pnr_main表状态
            if (cancelResult.getThrowable() == null) {
                if (CollectionUtils.isNotEmpty(cancelPassengers)) {
                    for (AbtPnrPassenger cancelPassenger : cancelPassengers) {
                        abtPnrPassengerDao.updatePassengerStatus(cancelPassenger.getId(), AbtPnrPassenger.STATUS_DEL);
                    }
                } else {
                    updateStatusToCancel(cancelResult, cancelPnrMains, AbtPnrMain.STATUS_CANCEL_OK);
                }
            } else { // 异常的情况，更新对应条目为失败
                if (CollectionUtils.isNotEmpty(cancelPassengers)) {
                    for (AbtPnrPassenger cancelPassenger : cancelPassengers) {
                        abtPnrPassengerDao.updatePassengerStatus(cancelPassenger.getId(), AbtPnrPassenger.STATUS_DEL_FAIL);
                    }
                } else {
                    updateStatusToCancel(cancelResult, cancelPnrMains, AbtPnrMain.STATUS_CANCEL_FAIL);
                }
            }
        }
    }

    private void updateStatusToCancel(CancelResult cancelResult, List<AbtPnrMain> pnrMains, int status) {
        List<Long> toUpdateIds = new ArrayList<Long>();

        // 携程的执行结果，是分组结果
        if (cancelResult.getCancelOrderGroup() != null) {
            Long id = findPnrMainIdByOrder(pnrMains, cancelResult.getCancelOrderGroup().getMainOrderId());
            toUpdateIds.add(id);
            for (String subOrderId : cancelResult.getCancelOrderGroup().getRelationOrderIds()) {
                toUpdateIds.add(findPnrMainIdByOrder(pnrMains, subOrderId));
            }
        // 航信、51、航司直连的执行结果，是按pnr的结果
        } else if (cancelResult.getCancelPnr() != null) {
            toUpdateIds.add(findPnrMainIdByPnr(pnrMains, cancelResult.getCancelPnr()));
        }

        updateStatusById(toUpdateIds, status);
    }

    // 根据pnr找到pnrmain的id
    private Long findPnrMainIdByPnr(List<AbtPnrMain> pnrMains, String pnr) {
        for (AbtPnrMain pnrMain : pnrMains) {
            if (pnrMain.getPnr().equals(pnr)) {
                return pnrMain.getId();
            }
        }
        return null;
    }

    // 根据orderId找到pnrmain的id
    private Long findPnrMainIdByOrder(List<AbtPnrMain> pnrMains, String orderId) {
        for (AbtPnrMain pnrMain : pnrMains) {
            if (pnrMain.getOutOrderId().equals(orderId)) {
                return pnrMain.getId();
            }
        }
        return null;
    }


}
