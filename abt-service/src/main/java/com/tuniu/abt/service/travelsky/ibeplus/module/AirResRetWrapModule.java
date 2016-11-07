package com.tuniu.abt.service.travelsky.ibeplus.module;

import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusInterfaceService;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/2/22.
 */
@Service
public class AirResRetWrapModule {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private IbePlusInterfaceService ibePlusInterfaceService;

    public OTAAirResRetRS rt(String pnr) {
        if (StringUtils.isBlank(pnr)) {
            throw new BizzException(BizzEx.COMM_EX_WRAP, "查询pnr为空");
        }
        OTAAirResRetRQ request = this.convertRTRequest(pnr);
        return ibePlusInterfaceService.airResRet(request);
    }

    private OTAAirResRetRQ convertRTRequest(String pnr) {
        OTAAirResRetRQ airResRetRQ = new OTAAirResRetRQ();
        airResRetRQ.setPOS(new POS());
        airResRetRQ.getPOS().getSource().add(new Source());
        airResRetRQ.getPOS().getSource().get(0).setPseudoCityCode(systemConfig.getIbeplusOfficeNo());
        airResRetRQ.setBookingReferenceID(new BookingReferenceID2());
        airResRetRQ.getBookingReferenceID().setID(pnr);
        return airResRetRQ;
    }


}
