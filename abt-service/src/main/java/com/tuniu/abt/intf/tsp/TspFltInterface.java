package com.tuniu.abt.intf.tsp;

import com.tuniu.abt.intf.page.Pager;
import com.tuniu.abt.intf.tsp.dto.flt.BanAddRequest;
import com.tuniu.abt.intf.tsp.dto.flt.BanQueryRequest;
import com.tuniu.abt.intf.tsp.dto.flt.BanQueryResponse;
import com.tuniu.abt.intf.tsp.dto.flt.LuggageQueryRequest;
import com.tuniu.adapter.flightTicket.vo.inquiry.AirportSeatClassLuggageInfo;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.vla.base.tsp.intf.TspInterface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 老适配TSP接口
 * Created by chengyao on 2016/3/30.
 */
@Component
public interface TspFltInterface extends TspInterface {

    /**
     * 添加屏蔽航班数据
     * @param request 请求
     * @return 针对请求值，如果返回了list，为空的情况，失败。 或者个数=请求list个数，每一项=0表示添加重复或失败。
     */
    @TspMethod(serviceName = "VLA.SUP.FlightBanAPI.save")
    List<Long> addBanFlight(List<BanAddRequest> request);

    @TspMethod(serviceName = "VLA.SUP.FlightBanAPI.checkBan")
    BanQueryResponse checkBan(BanQueryRequest request);

    @TspMethod(serviceName = "VLA.SUP.FlightOperateAPI.queryLuggageInfo")// TODO: 2016/5/28 llg
    Pager<AirportSeatClassLuggageInfo> queryLuggageInfo(LuggageQueryRequest request);

}
