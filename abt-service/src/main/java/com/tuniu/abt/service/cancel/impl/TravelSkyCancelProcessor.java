package com.tuniu.abt.service.cancel.impl;

import com.google.common.collect.Maps;
import com.tuniu.abt.dao.AbtPnrPassengerDao;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.constants.CancelEx;
import com.tuniu.abt.intf.dto.book.response.PassengerInfo;
import com.tuniu.abt.intf.dto.cancel.CancelOrderGroup;
import com.tuniu.abt.intf.dto.cancel.CancelResult;
import com.tuniu.abt.intf.dto.cancel.ProcCancelData;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.cancel.CancelDataSupport;
import com.tuniu.abt.service.cancel.CancelWorkUtils;
import com.tuniu.abt.service.cancel.TravelSkyCommonChecker;
import com.tuniu.abt.service.cancel.TravelSkyDelPassengerService;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 航信取消操作逻辑
 * Created by chengyao on 2016/3/1.
 */
@Service
public class TravelSkyCancelProcessor extends AbstractCancelProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelSkyCancelProcessor.class);

    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;
    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;
    @Resource
    private TravelSkyCommonChecker travelSkyCommonChecker;
    @Resource
    private TravelSkyDelPassengerService travelSkyDelPassengerService;

    @Override
    public List<CancelResult> cancelPnrs(List<CancelOrderGroup> groups) {

        ProcCancelData procCancelData = CancelDataSupport.getData();
        long orderId = procCancelData.getAbtBookingRequest().getOrderId();
        int systemId = procCancelData.getAbtBookingRequest().getSystemId();

        // 校验
        travelSkyCommonChecker.check(orderId, groups);

        List<CancelResult> cancelResults = new ArrayList<CancelResult>();
        for (CancelOrderGroup group : groups) {
            String pnr = group.getMainOrderId();
            String refPnr = group.getRefOrderId();

            CancelResult cancelResult = new CancelResult();
            cancelResult.setCancelPnr(pnr);

            try {
                if (!group.isCancelPnr()) {
                    List<PassengerInfo> passengers = new ArrayList<>();
                    cancelResult.setPassengers(passengers);
                    group.getPassengers().forEach(p -> passengers.add(new PassengerInfo(p.getBookName())));
                    cancelResult.setCancelPassengers(group.getPassengers());
                    AbtPnrMain newRef = null;
                    if (!pnr.equals(refPnr)) { // 儿童备注成人pnr的情况
                        newRef = findNewRef(refPnr, procCancelData);
                    }
                    travelSkyDelPassengerService.cancelPnrPassenger(pnr, systemId, group.getPassengers(), newRef);
                } else {
                    travelSkyDelPassengerService.cancelPnr(pnr, systemId);
                }
            } catch (Exception ex) {
                LOGGER.error("航信取消PNR失败，PNR=" + pnr, ex);
                cancelResult.setThrowable(ex);
                cancelResult.setErrorCode(exceptionMessageUtils.findCodeString(ex));
                cancelResult.setSuccess(false);
            }

            dealRemark(cancelResult);
            cancelResults.add(cancelResult);
        }

        return cancelResults;
    }




    /**
     * 找到新的备注pnr项目
     * @param refPnr 老备注项目
     * @param procCancelData cancelData
     * @return 新的备注项目， 如果为null，标识不用更新备注
     */
    private AbtPnrMain findNewRef(String refPnr, ProcCancelData procCancelData) {
        List<AbtPnrMain> availablePnrMains = procCancelData.getAvailablePnrMains();
        List<AbtPnrMain> delPnrMains = procCancelData.getPnrs();
        List<CancelOrderGroup> groups = procCancelData.getCancelOrderGroups();

        // 找到
        AbtPnrMain refPnrMain = CancelWorkUtils.findPnrMainByPnr(availablePnrMains, refPnr);
        if (refPnrMain == null) {
            throw new BizzException(CancelEx.NOT_FIND_CHILD_REF);
        }

        AbtPnrMain adultPnrMain = null;
        if (delPnrMains.contains(refPnrMain)) { // 如果这个备注pnr信息在删除列表中，需要额外处理
            CancelOrderGroup findGroup = CancelWorkUtils.findGroupByPnr(groups, refPnrMain.getPnr());
            if (findGroup == null) {
                throw new BizzException(BizzEx.COMM_EX_WRAP, "在CancelOrderGroup中未找到取消项目");
            }
            if (findGroup.isCancelPnr()
                    || !CancelWorkUtils.checkIfExistPassengerType(findGroup, AbtPnrPassenger.PASSENGER_TYPE_ADULT)) {
                // 如果备注pnr是要全部取消的, 或者取消了全部成人
                adultPnrMain = CancelWorkUtils.findContainsAdultPnr(groups);
            }
        }
        return adultPnrMain;
    }




    @Override
    public List<CancelOrderGroup> checkCancelRequest(List<AbtPnrMain> availablePnrMains, List<String> pnrs,
            Map<String, List<PassengerInfo>> passengers) {
        // 航信每个group标识为一个pnr
        Map<String, CancelOrderGroup> result = Maps.newHashMap();
        for (AbtPnrMain availablePnrMain : availablePnrMains) {
            String orderId = availablePnrMain.getOutOrderId();
            String refOrderId = availablePnrMain.getOutMainOrderId();
            // result数据处理。
            if (!pnrs.contains(orderId)) { //过滤不需要取消的订单
                continue;
            }
            if (!result.containsKey(orderId)) { // 按pnr保存cancelORderGroup
                CancelOrderGroup cancelOrderGroup = new CancelOrderGroup();

                // 拿到请求要删除的乘客对应的数据库数据
                List<PassengerInfo> reqPassengers = passengers.get(availablePnrMain.getOutOrderId());
                boolean isCancelPnr = true;
                if (CollectionUtils.isNotEmpty(reqPassengers)) {
                    List<AbtPnrPassenger> allPassengers = abtPnrPassengerDao.findAvailableByMainPnrId(availablePnrMain.getId());
                    List<AbtPnrPassenger> cancelPassengers = new ArrayList<AbtPnrPassenger>();
                    for (AbtPnrPassenger abtPnrPassenger : allPassengers) {
                        String name = abtPnrPassenger.getBookName();
                        if (CancelWorkUtils.containName(reqPassengers, name)) {
                            cancelPassengers.add(abtPnrPassenger);
                        } else {
                            isCancelPnr = false;
                        }
                    }
                    cancelOrderGroup.setPassengers(cancelPassengers);
                    cancelOrderGroup.setAllPassengers(allPassengers);
                }
                cancelOrderGroup.setCancelPnr(isCancelPnr);
                cancelOrderGroup.setPnrMain(availablePnrMain);

                cancelOrderGroup.setMainOrderId(orderId);
                cancelOrderGroup.setRefOrderId(refOrderId);
                result.put(orderId, cancelOrderGroup);
            }
        }
        return new ArrayList<CancelOrderGroup>(result.values());
    }



}
