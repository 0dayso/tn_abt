package com.tuniu.abt.service.cancel;

import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.dao.AbtPnrPassengerDao;
import com.tuniu.abt.intf.constants.CancelEx;
import com.tuniu.abt.intf.dto.cancel.CancelOrderGroup;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import com.tuniu.abt.intf.exception.BizzException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 航信判断总人数比例服务
 * Created by chengyao on 2016/4/12.
 */
@Component
public class TravelSkyCommonChecker {

    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;

    /**
     * 判断未删除的婴儿所绑定的成人在是否在删除列表中，如果在，则不允许取消
     * @param allPassengers 所有乘客
     * @param delPassengers 待删除乘客
     */
    private void checkBindBaby(List<AbtPnrPassenger> allPassengers , List<AbtPnrPassenger> delPassengers) {
        // 现存所有婴儿绑定的成人RPH
        Set<Long> babyRef = new HashSet<Long>();

        Collection leftPassengers = CollectionUtils.subtract(allPassengers, delPassengers);

        // 找到babyRef
        for (Object object : leftPassengers) {
            AbtPnrPassenger leftPassenger = (AbtPnrPassenger) object;
            if (leftPassenger.getPassengerType() == AbtPnrPassenger.PASSENGER_TYPE_BABY) {
                Long ref = leftPassenger.getRefPsgId();
                babyRef.add(ref);
            }
        }

        for (AbtPnrPassenger delPassenger : delPassengers) {
            if (babyRef.contains(delPassenger.getId())) {
                throw new BizzException(CancelEx.PASSENGER_BIND_BABY);
            }
        }
    }


    /**
     * 1. 校验订单纬度的成人儿童比率
     *    整个订单中，要保证成人数大于等于(儿童+婴儿)*2
     * 2. 校验未删除的婴儿所绑定的成人在是否在删除列表中，如果在，则不允许取消
     * 3. 剩余乘客中无成人
     *
     */
    public void check(long orderId, List<CancelOrderGroup> cancelOrderGroups) {
        // 待取消的pnr
        Set<String> cancelPnrs = new HashSet<String>();
        // 待取消的乘客
        List<AbtPnrPassenger> cancelPassengers = new ArrayList<AbtPnrPassenger>();
        // 获取待取消的pnr和待取消的乘客
        for (CancelOrderGroup cancelOrderGroup : cancelOrderGroups) {
            if (cancelOrderGroup.isCancelPnr()) {
                cancelPnrs.add(cancelOrderGroup.getMainOrderId());
            } else {
                List<AbtPnrPassenger> delPassengers = cancelOrderGroup.getPassengers();
                cancelPassengers.addAll(delPassengers);
                // 额外校验成人绑定婴儿的情况
                checkBindBaby(cancelOrderGroup.getAllPassengers(), delPassengers);
            }
        }
        // 剩余成人、儿童、婴儿数量
        int leftAdultCount = 0;
        int leftChildCount = 0;
        int leftBabyCount = 0;
        // 搜索数据表，填充剩余乘客数量
        List<AbtPnrMain> orderPnrMains = abtPnrMainDao.findByOrderId(orderId);
        for (AbtPnrMain orderPnrMain : orderPnrMains) {
            if (cancelPnrs.contains(orderPnrMain.getPnr())) continue;
            List<AbtPnrPassenger> passengers = abtPnrPassengerDao.findAvailableByMainPnrId(orderPnrMain.getId());
            for (AbtPnrPassenger passenger : passengers) {
                if (existPassenger(cancelPassengers, passenger)) continue;
                if (passenger.getPassengerType() == AbtPnrPassenger.PASSENGER_TYPE_ADULT) {
                    leftAdultCount++;
                } else if (passenger.getPassengerType() == AbtPnrPassenger.PASSENGER_TYPE_CHILD) {
                    leftChildCount++;
                } else if (passenger.getPassengerType() == AbtPnrPassenger.PASSENGER_TYPE_BABY) {
                    leftBabyCount++;
                }
            }
        }
        if (leftAdultCount == 0 && leftBabyCount == 0 && leftChildCount == 0) {
            return;
        }
        // 剩余乘客中无成人
        if (leftAdultCount == 0) {
            throw new BizzException(CancelEx.NO_ADULT);
        }
        // 成人 < (儿童+婴儿)*2  抛出异常
        if (leftAdultCount < (leftChildCount + leftBabyCount) * 2) {
            throw new BizzException(CancelEx.ADULT_COUNT_NOT_ENOUGH);
        }

    }

    /**
     * 判断乘客存在在列表中，根据pnr_id+bookName确定乘客
     * @param passengers 乘客列表
     * @param passenger 要判断的乘客对象
     * @return
     */
    private boolean existPassenger(List<AbtPnrPassenger> passengers, AbtPnrPassenger passenger) {
        for (AbtPnrPassenger abtPnrPassenger : passengers) {
            if (passenger.getPnrId().equals(abtPnrPassenger.getPnrId())
                    && passenger.getBookName().equals(abtPnrPassenger.getBookName())) {
                return true;
            }
        }
        return false;
    }


}
