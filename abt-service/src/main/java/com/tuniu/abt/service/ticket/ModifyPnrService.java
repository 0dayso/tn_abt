package com.tuniu.abt.service.ticket;

import com.tuniu.abt.intf.dto.custom.other.ModifyPnrRequest;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.vla.base.framework.resp.ApiResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/10.
 */
@Service
public class ModifyPnrService {

    @Resource
    private TravelSkyInterface travelSkyInterface;

    /**
     * 根据订单号和手机号修改对应PNR，向其中添加OSI项
     * 南航：
     * OSI 航司二字码 CT客人电话
     * 南航外其他所有航司格式：
     * OSI 航司二字码 CTCT客人电话
     *
     * @return
     */
    public ApiResp modifyPnrOsiByOrderIdAndPhone(ModifyPnrRequest request) {
//        if (request == null || request.getOrderId() == null || request.getOrderId() == 0) {
//            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "订单号不能为空");
//        }
//
//        List<FlightPnrMain> pnrMainList = flightPnrMainService.queryPnrByOrderId(request.getOrderId());
//        if (CollectionUtils.isEmpty(pnrMainList)) {
//            throw new BizzException(BizzEx.COMM_EX_WRAP, "该订单没有国内机票散客票待出票或者已出票的记录，订单号：" + request.getOrderId());
//        }
//        int count = 0;
//        StringBuilder buffer = new StringBuilder();
//        for (FlightPnrMain flightPnrMain : pnrMainList) {
//            FlightPnrFlight flightPnrFlight = flightPnrFlightService
//                    .getFlightPnrFlightByPnrId(flightPnrMain.getId());
//            //pnr
//            String pnrNo = flightPnrMain.getPnr();
//            //airCode
//            String airCode = "";
//            String flightNo = flightPnrFlight.getFlightNo();
//            airCode = flightNo.substring(0, 2);
//            try {
//                modifyPnrByAddOsi(pnrNo, airCode, request.getTel());
//            } catch (Exception e) {
//                count++;
//                buffer.append(exceptionMessageUtils.findSimpleEx(e)).append("。");
//            }
//        }
//        if (count > 0 && pnrMainList.size() == count) {
//            return ApiResp.error(BizzEx.COMM_EX_WRAP, buffer.toString());
//        } else {
//            return ApiResp.success("修改PNR，添加OSI项成功。");
//        }
        return null;
    }

//    private String modifyPnrByAddOsi(String pnrNo, String airCode, String tel) {
//        FlightChannelSetVo flightChannelSetVo = new FlightChannelSetVo();
//        flightChannelSetVo.setState(1);
//        flightChannelSetVo.setType("modifyPnrD");
//        //osiInfo
//        String osiInfo = "";
//        //南方航空
//        if (airCode.equalsIgnoreCase("CZ")) {
//            osiInfo = "CT" + tel;
//        } else {
//            osiInfo = "CTCT" + tel;
//        }
//        return tsFacadeService.modifyPnrInfoOfIbe(pnrNo, airCode, osiInfo);
//    }

}
