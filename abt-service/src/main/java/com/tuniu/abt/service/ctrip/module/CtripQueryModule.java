package com.tuniu.abt.service.ctrip.module;

import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.ctrip.CtripInterfaceService;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.FlightSearchResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.Response;
import com.tuniu.adapter.flightTicket.vo.connector.AirSearchRequest;
import com.tuniu.adapter.flightTicket.vo.inquiry.ReverseQueryFlightSegVo;
import com.tuniu.adapter.flightTicket.vo.inquiry.ReverseRequestVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/2/25.
 */
@Service
public class CtripQueryModule {
    
    @Resource
    private CtripInterfaceService ctripInterfaceService;

    public Response flightSearch(AirSearchRequest airSearchRequest) {
        return ctripInterfaceService.flightSearch(airSearchRequest);
    }

    /**
     * 
     * Description: 携程反向查询<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param reverseRequestVo
     * @return <br>
     */
    public Response flightReverseSearch(ReverseRequestVo reverseRequestVo) {
        return ctripInterfaceService.flightReverseSearch(reverseRequestVo);
    }

    /**
     * 通过反向查询，获取productID
     *
     * @param request
     * @return
     */
    public FlightSearchResponse flightReverseSearch(BookingData request) {
        ReverseRequestVo reverseRequestVo = new ReverseRequestVo();
        List<ReverseQueryFlightSegVo> reverseQueryFlightSegVoList = new ArrayList<ReverseQueryFlightSegVo>();
        ReverseQueryFlightSegVo reverseQueryFlightSegVo = null;
        for (Segment flightSegment : request.getSegments()) {
            reverseQueryFlightSegVo = new ReverseQueryFlightSegVo();
            reverseQueryFlightSegVo.setOrgCityIataCode(flightSegment.getOrgCityIataCode());
            reverseQueryFlightSegVo.setDstCityIataCode(flightSegment.getDstCityIataCode());
            reverseQueryFlightSegVo.setDepartureDate(flightSegment.getDepartureDate());
            reverseQueryFlightSegVo.setFlightNo(flightSegment.getFlightNo());
            reverseQueryFlightSegVo.setAirline(flightSegment.getAirlineCompanyIataCode());
            reverseQueryFlightSegVo.setSeatClass(flightSegment.getSeatCode());//传具体的舱位
            reverseQueryFlightSegVo.setPrice(String.valueOf(request.getPriceInfo().getAdultPrice().getPrice()));//传成人成本价
            reverseQueryFlightSegVo.setSaleType(flightSegment.getSaleTypeCtrip());
            reverseQueryFlightSegVo.setPid(flightSegment.getPidCtrip());
            reverseQueryFlightSegVoList.add(reverseQueryFlightSegVo);
        }
        reverseRequestVo.setFlightSegList(reverseQueryFlightSegVoList);
        Response response = flightReverseSearch(reverseRequestVo);
        // 调携程接口返回失败
        if ("Fail".equalsIgnoreCase(response.getHeader().getResultCode())
                || "Error".equals(response.getHeader().getResultCode())
                || null == response.getFlightSearchResponse().getResult().getFlightListGroupList()) {
            throw new BizzException(BizzEx.CTRIP_INTF_RESULT_ERROR, response.getHeader().getResultMsg());
        }
        return response.getFlightSearchResponse();
    }

}
