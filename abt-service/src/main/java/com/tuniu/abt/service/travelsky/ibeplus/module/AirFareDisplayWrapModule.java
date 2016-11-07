package com.tuniu.abt.service.travelsky.ibeplus.module;

import com.travelsky.espeed.OTAAirFareDisplayRQ;
import com.travelsky.espeed.OTAAirFareDisplayRS;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusInterfaceService;
import com.tuniu.abt.service.travelsky.ibeplus.converter.IbePlusPriceConverter;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightFDPriceRes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/4/16.
 */
@Service
public class AirFareDisplayWrapModule {

    @Resource
    private IbePlusInterfaceService ibePlusInterfaceService;

    @Resource
    private SystemConfig systemConfig;

    /**
     * 不指定航司、机型查询Fd价(只返回fd价不返回税费)
     */
    public FlightFDPriceRes queryAirFareDisplay(FdPriceParam fdPriceParam) {
        OTAAirFareDisplayRS airFareDisplayDRS = airFareDisplay(fdPriceParam);
        return IbePlusPriceConverter.toResponse(fdPriceParam, airFareDisplayDRS);
    }

    public OTAAirFareDisplayRS airFareDisplay(FdPriceParam fdPriceParam) {
        OTAAirFareDisplayRQ request = IbePlusPriceConverter.toRequest(fdPriceParam, systemConfig.getIbeplusOfficeNo());
        return ibePlusInterfaceService.airFareDisplay(request);
    }

}
