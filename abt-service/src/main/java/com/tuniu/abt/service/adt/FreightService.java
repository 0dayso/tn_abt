package com.tuniu.abt.service.adt;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.tuniu.abt.intf.tsp.dto.adt.InquiryPriceDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.TspAdtInterface;
import com.tuniu.abt.intf.tsp.dto.adt.FreightCalculateRequestVo;
import com.tuniu.abt.intf.tsp.dto.adt.FreightCalculateResultVo;
import com.tuniu.abt.intf.tsp.dto.res.ResAirport;
import com.tuniu.abt.service.res.ResourceService;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.adapter.flightTicket.vo.dfpPolicy.DetailFligthSegVo;

@Component
@Validated
public class FreightService {
    
    @Resource
    private TspAdtInterface tspAdtInf;
    
    @Resource
    private ResourceService resService;
    
    public FreightCalculateResultVo qryDetailPrice(@Valid FreightCalculateRequestVo requestVo) {
        checkQryDetailPriceInputparam(requestVo);
        FreightCalculateResultVo resultVo = null;
        try {
            InquiryPriceDetailVo data = tspAdtInf.qryDetailPrice(requestVo);
            resultVo = CommonUtils.transform(data, FreightCalculateResultVo.class);
        } catch (Exception e) {
            throw new BizzException(BizzEx.TSP_ADT_INTF_EX, "调运价接口获取销售价格失败", e);
        }
        return resultVo;
    }

    private void checkQryDetailPriceInputparam(FreightCalculateRequestVo requestVo) {
        for (DetailFligthSegVo segVo :requestVo.getFlightSegList()) {
            if (StringUtils.isBlank(segVo.getOrgAirportIataCode()) 
                    || StringUtils.isBlank(segVo.getDstAirportIataCode())) {
                throw new BizzException(BizzEx.TSP_ADT_INTF_REQ_ERROR,
                        "调运价接口获取销售价格失败,入参机场三字码不能为空");
            }
            if (StringUtils.isBlank(segVo.getOrgCityIataCode())) {
                ResAirport resAirport = resService.findAirPort(segVo.getOrgAirportIataCode());
                segVo.setOrgCityIataCode(resAirport.getIataCityCode());
            }
            if (StringUtils.isBlank(segVo.getDstCityIataCode())) {
                ResAirport resAirport = resService.findAirPort(segVo.getDstAirportIataCode());
                segVo.setDstCityIataCode(resAirport.getIataCityCode());
            }
        }
    }

}
