package com.tuniu.abt.service.cancel;

import com.tuniu.abt.intf.dto.book.response.PassengerInfo;
import com.tuniu.abt.intf.dto.cancel.CancelOrderGroup;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * 工具类
 * Created by chengyao on 2016/4/11.
 */
public class CancelWorkUtils {

    /**
     * 构造输出文本
     * @param dbPnrs dbpnrs
     * @param pnrs pnrs
     * @return
     */
    public static String buildPnrMessage(List<AbtPnrMain> dbPnrs, List<String> pnrs) {
        StringBuilder sb = new StringBuilder();
        sb.append("已存在的订单：[");
        for (AbtPnrMain dbPnr : dbPnrs) {
            sb.append("[pnr=").append(dbPnr.getPnr()).append(",");
            sb.append("outMainOrderId=").append(dbPnr.getOutMainOrderId()).append(",");
            sb.append("outOrderId=").append(dbPnr.getOutOrderId()).append(",");
            sb.append("status=").append(dbPnr.getStatus()).append("]");
        }
        sb.append("], 待取消的订单：").append(pnrs);
        return sb.toString();
    }


    public static boolean checkIfExistPassengerType(CancelOrderGroup cancelOrderGroup, int passengerType) {
        List<AbtPnrPassenger> allPassengers = cancelOrderGroup.getAllPassengers();
        List<AbtPnrPassenger> delPassengers = cancelOrderGroup.getPassengers();
        Collection leftPassengers = CollectionUtils.subtract(allPassengers, delPassengers);
        for (Object object : leftPassengers) {
            AbtPnrPassenger leftPassenger = (AbtPnrPassenger) object;
            if (leftPassenger.getPassengerType() == AbtPnrPassenger.PASSENGER_TYPE_ADULT) {
                return true;
            }
        }
        return false;
    }


    public static CancelOrderGroup findGroupByPnr(List<CancelOrderGroup> groups, String pnr) {
        for (CancelOrderGroup group : groups) {
            if (group.getMainOrderId().equals(pnr)) {
                return group;
            }
        }
        return null;
    }

    public static AbtPnrMain findPnrMainByPnr(List<AbtPnrMain> pnrMains, String pnr) {
        for (AbtPnrMain pnrMain : pnrMains) {
            if (pnr.equals(pnrMain.getPnr())) {
                return pnrMain;
            }
        }
        return null;
    }

    public static boolean containName(List<PassengerInfo> reqPassengers, String name) {
        for (PassengerInfo reqPassenger : reqPassengers) {
            if (name.equals(reqPassenger.getName())) {
                return true;
            }
        }
        return false;
    }

    public static AbtPnrMain findContainsAdultPnr(List<CancelOrderGroup> groups) {
        for (CancelOrderGroup group : groups) {
            if (CancelWorkUtils.checkIfExistPassengerType(group, AbtPnrPassenger.PASSENGER_TYPE_ADULT)) {
                return group.getPnrMain();
            }
        }
        return null;
    }

}
