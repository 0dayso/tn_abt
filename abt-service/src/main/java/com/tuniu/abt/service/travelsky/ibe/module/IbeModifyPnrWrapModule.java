package com.tuniu.abt.service.travelsky.ibe.module;

import com.travelsky.ibe.client.pnr.BookPassenger;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.ReqPassenger;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

@Component
public class IbeModifyPnrWrapModule {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IbeModifyPnrWrapModule.class);

    @Resource
    private IbeInterfaceService ibeInterfaceService;

    public String delItemsInPNR(String pnrNo, Collection<String> rphList) {
        try {
            int[] indexes = new int[rphList.size()];
            int i = 0;
            for (String rph : rphList) {
                indexes[i] = Integer.parseInt(rph);
                i++;
            }
            return ibeInterfaceService.deletePnrItem(pnrNo, indexes);
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "删除PNR指定编号的组失败", e);
        }
    }

    public String addRMK4PNR(String pnrNo, String rmktype, String rmkinfo) {
        try {
            return ibeInterfaceService.addRMK4PNR(pnrNo, rmktype, rmkinfo);
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "添加备注组失败", e);
        }
    }
    
    public String addOSI4PNR(String pnrNo, String airCode, String osi) {
        try {
            return ibeInterfaceService.addOSI4PNR(pnrNo, airCode, osi);
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "添加OSI信息失败", e);
        }
    }

    public String splitPnr(String pnrNo, final List<ReqPassenger> passengers) {
        try {
            Vector<BookPassenger> bookPassengers = new Vector<BookPassenger>();
            for (ReqPassenger reqPassenger: passengers) {
                BookPassenger bookPassenger = new BookPassenger();
                bookPassenger.setName(reqPassenger.getPassengerName());
                int passengerType = reqPassenger.getPassengerType();
                if (passengerType == AbtPnrPassenger.PASSENGER_TYPE_ADULT) {
                    bookPassenger.setType(BookPassenger.PASSENGER_ADULT);
                } else if (passengerType == AbtPnrPassenger.PASSENGER_TYPE_CHILD) {
                    bookPassenger.setType(BookPassenger.PASSENGER_CHILD);
                }
                bookPassengers.add(bookPassenger);
            }
            return ibeInterfaceService.splitPnr(pnrNo, bookPassengers);
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "分离PNR失败", e);
        }
    }

}
