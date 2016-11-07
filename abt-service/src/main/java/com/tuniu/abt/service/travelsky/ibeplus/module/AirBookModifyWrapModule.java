package com.tuniu.abt.service.travelsky.ibeplus.module;

import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.PassengerItem;
import com.tuniu.abt.service.travelsky.dto.ReqPassenger;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusInterfaceService;
import com.tuniu.abt.service.travelsky.ibeplus.converter.BookModifyConverter;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.ListConverter;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * IBE PLUS AIR_BOOK_MODIFY 接口再次封装
 * <p/>
 * Created by chengyao on 2016/2/20.
 */
@Service
public class AirBookModifyWrapModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirBookModifyWrapModule.class);

    @Resource
    private IbePlusInterfaceService ibePlusInterfaceService;

    @Resource
    private BookModifyConverter bookModifyConverter;

    /**
     * IBEPLUS国内取消占位接口
     *
     * @param pnrNo pnrNo
     */
    public void cancelPnr(String pnrNo) {
        try {
            airBookModify(AirbookModifyType.PNR_CANCEL, pnrNo);
        } catch (BizzException ex) {
            // 校验pnr已被取消的情况，不抛异常
            if (BizzEx.IBE_PLUS_INTF_RESULT_ERROR == ex.getCode()) {
                String msg = (String) ex.getMessageArgs()[0];
                if (msg.contains("PNR:" + pnrNo + " was entirely cancelled.")) {
                    LOGGER.warn("PNR重复取消：" + msg);
                    return;
                }
            }
            throw ex;
        }
    }


    /**
     * 调用modify接口添加EI项
     * @param pnrNo
     * @return
     */
    public OTAAirBookRS addEIInPnr(String pnrNo, String eiText) {
        if (null == pnrNo || "".equals(pnrNo) || null == eiText || "".equals(eiText)) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX,
                    "调用IBEPLUS添加EI项失败，PNR【" + pnrNo + "】或者EI项【"+eiText+"】为空");
        }
        return airBookModify(AirbookModifyType.EI_ADD, pnrNo, eiText);
    }

    public OTAAirBookRS addOsi(String pnrNo, String airlineCode, String osiInfo) {
        return airBookModify(AirbookModifyType.OSI_ADD, pnrNo, airlineCode, osiInfo);
    }


    /**
     * 调用modify接口添加RMK项
     *
     * @param pnrNo
     * @return
     */
    public OTAAirBookRS addRMKInPnr(String pnrNo, String rmkText) {
        return airBookModify(AirbookModifyType.REMARK_ADD, pnrNo, rmkText);
    }

    /**
     * 分离pnr
     * @param pnrNo pnrNo
     * @param passengers passengers
     * @return
     */
    public String splitPnr(String pnrNo, final List<ReqPassenger> passengers) {
        List<AirTraveler> airTravelers = CommonUtils
                .transformList(passengers, new ListConverter<AirTraveler, ReqPassenger>() {
                    @Override
                    public AirTraveler convert(ReqPassenger reqPassenger) throws Exception {
                        AirTraveler airTraveler = new AirTraveler();
                        if (reqPassenger.getPassengerType() == AbtPnrPassenger.PASSENGER_TYPE_ADULT) {
                            airTraveler.setPassengerTypeCode(PassengerType.ADT);
                        } else if (reqPassenger.getPassengerType() == AbtPnrPassenger.PASSENGER_TYPE_CHILD) {
                            airTraveler.setPassengerTypeCode(PassengerType.CHD);
                        }
                        airTraveler.getPersonName().add(new PersonName());
                        airTraveler.getPersonName().get(0).setSurname(reqPassenger.getPassengerName());
                        return airTraveler;
                    }
                });
        OTAAirBookRS rs = airBookModify(AirbookModifyType.PNR_SPLIT, pnrNo, airTravelers);
        return rs.getAirReservation().get(0).getBookingReferenceID().get(0).getID();
    }

    /**
     * Description: 按行删除PNR中的项<br>
     *
     * @param pnrNo
     * @param rphList
     * @return <br>
     * @author lanlugang<br>
     * @date 2015-7-11
     * @taskId PTICKET-1578<br>
     */
    public OTAAirBookRS delItemsInPNR(String pnrNo, Collection<String> rphList) {
        return airBookModify(AirbookModifyType.ITEM_CANCEL, pnrNo, rphList);
    }

    /**
     * 删除旅客
     * @param pnrNo pnrNo
     * @param passengers passengers
     * @return
     */
    public OTAAirBookRS delPassengerInPNR(String pnrNo, List<PassengerItem> passengers) {
        return airBookModify(AirbookModifyType.PASSENGER_DELETE, pnrNo, passengers);
    }

    /**
     * SSR ADD
     * @param pnrNo
     * @param specialServiceRequest
     * @param travelers
     * @return
     */
    public OTAAirBookRS ssrAdd(String pnrNo, SpecialServiceRequest specialServiceRequest, List<AirTraveler> travelers) {
        return airBookModify(AirbookModifyType.SSR_ADD, pnrNo, specialServiceRequest, travelers);
    }

    /**
     * Description: IBEPLUS航段NO位补位接口<br>
     *
     * @param pnrNo
     * @param segmentList
     * @return <br>
     * @author lanlugang<br>
     * @date 2015-7-7 13:39:51
     * @taskId PTICKET-1509<br>
     */
    public OTAAirBookRS segmentNo2KByIBEPLUS(String pnrNo, List<FlightSegment2> segmentList) {
        // 1.筛选座位状态为NO的航段
        List<FlightSegment2> segmentNoList = new ArrayList<FlightSegment2>();
        for (FlightSegment2 segment : segmentList) {
            if (segment.getStatus().equals("NO")) {
                segmentNoList.add(segment);
            }
        }
        if (CollectionUtils.isEmpty(segmentNoList)) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX,
                    "调用IBEPLUS航段NO位补位失败，PNR【" + pnrNo + "】中没有座位状态为NO的航段，请检查入参！");
        }
        // 2.调用modify接口进行NO位补位
        return airBookModify(AirbookModifyType.SEGMENT_NO, pnrNo, segmentList);
    }

    private OTAAirBookRS airBookModify(AirbookModifyType airbookModifyType, String pnr, Object... params) {
        OTAAirBookModifyRQ otaAirBookModifyRQ = bookModifyConverter.makeRQ(pnr, airbookModifyType, params);
        return ibePlusInterfaceService.airBookModify(otaAirBookModifyRQ);
    }


}
