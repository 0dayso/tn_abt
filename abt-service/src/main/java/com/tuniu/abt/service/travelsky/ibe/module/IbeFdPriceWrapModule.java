package com.tuniu.abt.service.travelsky.ibe.module;

import com.travelsky.ibe.client.FDItem;
import com.travelsky.ibe.client.FDResult;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import com.tuniu.adapter.flightTicket.vo.connector.FDRequest;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightAirCompanyFDPrice;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightCabinFDPrice;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightFDPriceRes;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by chengyao on 2016/3/11.
 */
@Service
public class IbeFdPriceWrapModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbeFdPriceWrapModule.class);


    @Resource
    private IbeInterfaceService ibeInterfaceService;

    /**
     * IBE FD运价查询
     * @param fdPriceParam
     * @return
     */
    private FDResult queryFD(FdPriceParam fdPriceParam) {
        FDRequest fdReq = new FDRequest();
        fdReq.setDepartureDate(fdPriceParam.getDepartureDate());
        fdReq.setDstCityIataCode(fdPriceParam.getDstCityIataCode());
        fdReq.setOrgCityIataCode(fdPriceParam.getOrgCityIataCode());
        if (StringUtils.isNotBlank(fdPriceParam.getAirlineCompany())) {
            fdReq.setAirline(fdPriceParam.getAirlineCompany());
        } else {
            fdReq.setAirline("ALL");
        }
        fdReq.setPassType("ad");
        fdReq.setPlaneModel(null);
        fdReq.setFullFareBasis(true);
        FDResult fdResult = null;
        try {
            fdResult = ibeInterfaceService.fdPrice(fdReq);
            if (null == fdResult || fdResult.getElementNum() <= 0) {
                throw new BizzException(BizzEx.IBE_INTF_RESULT_NONE, "查询FD结果为空.");
            }
        } catch (Exception e) {
            LOGGER.error("查询IBE接口发生异常.", e);
            throw new BizzException(BizzEx.IBE_INTF_EX, "查询IBE接口发生异常", e);
        }
        return fdResult;
    }

    /**
     *
     * Description: 根据出发城市三字码+到达城市三字码+团期,从IBE查询FD价格<br>
     *
     * @author lanlugang<br>
     * @taskId AIR-1771<br>
     * @param fdPriceParam
     * @return <br>
     */
    public FlightFDPriceRes queryFDPriceByIBE(FdPriceParam fdPriceParam) {
        FlightFDPriceRes flightFDPriceRes = new FlightFDPriceRes();
        flightFDPriceRes.setOrgCityIataCode(fdPriceParam.getOrgCityIataCode());
        flightFDPriceRes.setDstCityIataCode(fdPriceParam.getDstCityIataCode());
        flightFDPriceRes.setDepartureDate(fdPriceParam.getDepartureDate());
        FDResult fdResult = queryFD(fdPriceParam);
        flightFDPriceRes.getFdPriceList().addAll(convert2FdPrices(fdResult));
        return flightFDPriceRes;
    }

    private List<FlightAirCompanyFDPrice> convert2FdPrices(FDResult fdResult) {
        List<FlightAirCompanyFDPrice> fdPrices = new ArrayList<FlightAirCompanyFDPrice>();
        FlightAirCompanyFDPrice fdPrice = null;
        FlightCabinFDPrice price = null;
        for (Iterator<String> it = fdResult.getFare().getHash().keySet().iterator(); it.hasNext();) {
            String airlineCode = it.next();
            fdPrice = new FlightAirCompanyFDPrice();
            fdPrices.add(fdPrice);
            fdPrice.setAirCompanyIataCode(airlineCode);
            List<FDItem> fdItems = (List<FDItem>) fdResult.getFare().getHash().get(airlineCode);
            for (FDItem fdItem : fdItems) {
                if (StringUtils.isBlank(fdItem.getOnewayPrice())) {
                    continue;
                }
                price = new FlightCabinFDPrice();
                price.setSeatClass(fdItem.getBasicCabin());
                price.setSeatCode(fdItem.getCabin());
                price.setFdPrice(Double.valueOf(fdItem.getOnewayPrice()));
                if ("XO".equals(airlineCode)) {
                    price.setSubSeatCode(fdItem.getCabin());
                } else {
                    String fareBaseCode = fdItem.getFareBasis();
                    if (fareBaseCode.matches("^[A-Z]{2}.*")) {
                        if (fareBaseCode.startsWith(fdItem.getBasicCabin())
                                || fareBaseCode.startsWith(fdItem.getCabin())) {
                            fareBaseCode = fareBaseCode.substring(1);
                        } else {
                            continue;
                        }
                    }
                    if (fareBaseCode.matches("^[A-Z][1-9].*")) {
                        fareBaseCode = fareBaseCode.substring(0, 2);
                    } else {
                        fareBaseCode = fareBaseCode.substring(0, 1);
                    }
                    price.setSubSeatCode(fareBaseCode);
                }
                fdPrice.getCabinFDPricelist().add(price);
            }
            filterDuplicatedCabin(fdPrice.getCabinFDPricelist());
        }
        return fdPrices;
    }

    /**
     * 如果有重复的舱位，则只取其中的最低价舱位
     * @param cabinFDPricelist
     */
    private void filterDuplicatedCabin(List<FlightCabinFDPrice> cabinFDPricelist) {
        if (CollectionUtils.isEmpty(cabinFDPricelist)) {
            return;
        }
        Map<String, FlightCabinFDPrice> cabinFDPriceMap = new HashMap<>();
        for (FlightCabinFDPrice flightCabinFDPrice : cabinFDPricelist) {
            String subSeatCode = flightCabinFDPrice.getSubSeatCode();
            if (cabinFDPriceMap.containsKey(subSeatCode)) {
                FlightCabinFDPrice preCabinFDPrice = cabinFDPriceMap.get(subSeatCode);
                if (flightCabinFDPrice.getFdPrice() < preCabinFDPrice.getFdPrice()) {
                    cabinFDPriceMap.put(subSeatCode, flightCabinFDPrice);
                }
            } else {
                cabinFDPriceMap.put(subSeatCode, flightCabinFDPrice);
            }
        }
        cabinFDPricelist.clear();
        cabinFDPriceMap.values().forEach(p -> cabinFDPricelist.add(p));
        Collections.sort(cabinFDPricelist, (o1, o2) -> -Double.compare(o1.getFdPrice(), o2.getFdPrice()));
    }


}
