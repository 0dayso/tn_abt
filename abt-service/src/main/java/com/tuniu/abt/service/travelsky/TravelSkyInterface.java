package com.tuniu.abt.service.travelsky;

import com.travelsky.espeed.AirTraveler;
import com.travelsky.espeed.OTAAirResRetRS;
import com.travelsky.espeed.SpecialServiceRequest;
import com.travelsky.ibe.client.pnr.RTResult;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.PnrInfo;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.abt.service.travelsky.bop.BopIssueTicketWrapModule;
import com.tuniu.abt.service.travelsky.dto.*;
import com.tuniu.abt.service.travelsky.ibe.converter.RtConverter;
import com.tuniu.abt.service.travelsky.ibe.module.*;
import com.tuniu.abt.service.travelsky.ibeplus.module.*;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.abt.utils.TsConfig;
import com.tuniu.adapter.flightTicket.vo.connector.AirAvailRequest;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightFDPriceRes;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * replace TravelSkyFacadeService;
 *
 * Created by chengyao on 2016/4/16.
 */
@Service
public class TravelSkyInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelSkyInterface.class);

    @Resource
    private SystemConfig systemConfig;
    @Resource
    private TsConfig tsConfig;
    @Resource
    private AirResRetWrapModule airResRetWrapModule;
    @Resource
    private AirBookModifyWrapModule airBookModifyWrapModule;
    @Resource
    private AirTicketRefundWrapModule airTicketRefundWrapModule;
    @Resource
    private AirFareDisplayWrapModule airFareDisplayWrapModule;
    @Resource
    private IbeRtWrapModule ibeRtWrapModule;
    @Resource
    private IbeCleanPnrWrapModule ibeCleanPnrWrapModule;
    @Resource
    private IbeModifyPnrWrapModule ibeModifyPnrWrapModule;
    @Resource
    private IbeRefundWrapModule ibeRefundWrapModule;
    @Resource
    private IbeFdPriceWrapModule ibeFdPriceWrapModule;
    @Resource
    private IbeAvWrapModule ibeAvWrapModule;
    @Resource
    private AirAvailDWrapModule airAvailDWrapModule;
    @Resource
    private IbeIssueWrapModule ibeIssueWrapModule;
    @Resource
    private BopIssueTicketWrapModule bopIssueTicketWrapModule;
    @Resource
    private AirBookWrapModule airBookWrapModule;
    @Resource
    private IbeCreatePnrWrapModule ibeCreatePnrWrapModule;
    @Resource
    private AirPriceBySegDWrapModule airPriceBySegDWrapModule;
    @Resource
    private AirPriceDWrapModule airPriceDWrapModule;
    @Resource
    private IbePataWrapModule ibePataWrapModule;
    @Resource
    private IbePataBySegWrapModule ibePataBySegWrapModule;


    /**
     *
     * Description: 创建pnr<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param request
     * @return <br>
     */
    public PnrInfo airBookPnr(BookingData request) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.AIRBOOK, request.getSystemId());
        PnrInfo pnrInfo = null;
        if (ibeplusFlag || !request.getOccupyOfficeNo().equals(systemConfig.getIbeplusOfficeNo())) {
            AirBookRsWrapper r = airBookWrapModule.airBook(request);
            pnrInfo = CommonUtils.transform(r, PnrInfo.class);
        } else {
            pnrInfo = ibeCreatePnrWrapModule.createIBEPnr(request);
        }
        // set associated pnrNo
        if (null != request.getPnrExternalInfo()) {
            pnrInfo.setAssociatedPnrNo(request.getPnrExternalInfo().getAdultPnr());
        } else {
            pnrInfo.setAssociatedPnrNo(pnrInfo.getPnrNo());
        }
        pnrInfo.setOccupyType(request.getOccupyType());
        pnrInfo.setHasBaby(request.isHasBaby());
        pnrInfo.setPolicyId(request.getPolicyId());
        pnrInfo.setSegments(request.getSegments());
        pnrInfo.setPassengers(request.getPassengers());
        pnrInfo.setOfficeNo(request.getOccupyOfficeNo());
        if (request.getOccupyType() == BizzConstants.PERSON_TYPE_ADULT
                || request.isChdUseAdtPrice()) {
            pnrInfo.setPatType(BizzConstants.PSG_TYPE_CODE_ADT);
        } else {
            pnrInfo.setPatType(BizzConstants.PSG_TYPE_CODE_CHD);
        }
        return pnrInfo;
    }

    /**
     * rt
     */
    public OTAAirResRetRS.AirResRet rt(String pnr) {
        return rt(pnr, 0);
    }

    /**
     * rt
     */
    public OTAAirResRetRS.AirResRet rt(String pnr, int systemId) {
        systemId = convertSystemId(systemId);
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.RT, systemId);
        if (ibeplusFlag) {
            return airResRetWrapModule.rt(pnr).getAirResRet();
        } else {
            RTResult rtResult = ibeRtWrapModule.rt(pnr);
            return RtConverter.toPlus(rtResult).getAirResRet();
        }
    }

    public void ssrAdd(String pnrNo, SpecialServiceRequest specialServiceRequest, List<AirTraveler> travelers) {
        airBookModifyWrapModule.ssrAdd(pnrNo, specialServiceRequest, travelers);
    }

    /**
     * 取消pnr, office号默认为NKG166
     * @param pnr
     */
    public void cancelPnr(String pnr) {
        cancelPnr(pnr, 0, systemConfig.getIbeplusOfficeNo());
    }

    public void cancelPnr(String pnr, String officeNo) {
        cancelPnr(pnr, 0, officeNo);
    }

    public void cancelPnr(String pnr, int systemId) {
        cancelPnr(pnr, systemId, systemConfig.getIbeplusOfficeNo());
    }

    /**
     * 取消pnr, 如果取消失败或者pnr不存在 则底层抛异常<br>
     *
     * @author lanlugang
     */
    public void cancelPnr(String pnr, int systemId, String officeNo) {
        systemId = convertSystemId(systemId);
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.CANCEL, systemId);
        if (ibeplusFlag || !systemConfig.getIbeplusOfficeNo().equals(officeNo)) {
            airBookModifyWrapModule.cancelPnr(pnr);
        } else {
            ibeCleanPnrWrapModule.cleanPnr(pnr);
        }
    }

    public void delPnrItem(String pnr, Collection<String> rphList) {
        if (CollectionUtils.isEmpty(rphList)) {
            return;
        }
        int systemId = convertSystemId(0);
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.DEL_PNR_ITEM, systemId);
        if (ibeplusFlag) {
            airBookModifyWrapModule.delItemsInPNR(pnr, rphList);
        } else {
            ibeModifyPnrWrapModule.delItemsInPNR(pnr, rphList);
        }
    }

    /**
     * 分离pnr
     * @param pnrNo pnr号
     * @param passengers 要分离的乘客信息
     * @return 分离出的pnr号
     */
    public String splitPnr(String pnrNo, final List<ReqPassenger> passengers) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.SPLIT_PNR, 0);
        if (ibeplusFlag) {
            return airBookModifyWrapModule.splitPnr(pnrNo, passengers);
        } else {
            return ibeModifyPnrWrapModule.splitPnr(pnrNo, passengers);
        }
    }

    /**
     * 执行退票，返回结果
     * @param ticketNo 票号
     * @return 结果
     */
    public RefundPriceResponse ticketRefundWithPrice(String ticketNo) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.REFUND, 0);
        if (ibeplusFlag) {
            return airTicketRefundWrapModule.ticketRefundWithPrice(ticketNo);
        } else {
            return ibeRefundWrapModule.ticketRefundWithPrice(ticketNo);
        }
    }

    /**
     * 查询退票费用
     * @param ticketNo 票号
     * @return 结果
     */
    public RefundPriceResponse queryRefundPrice(String ticketNo) {
        return airTicketRefundWrapModule.queryTicketRefundPrice(ticketNo);
    }

    // IBE接口不支持
    public void delPnrPassengers(String pnr, List<PassengerItem> passengerItems) {
        if (CollectionUtils.isEmpty(passengerItems)) {
            return;
        }
        airBookModifyWrapModule.delPassengerInPNR(pnr, passengerItems);
    }


    private int convertSystemId(int systemId) {
        if (systemId == 0) {
            Integer t = DataSharedSupport.getSystemId();
            if (t == null) {
                t = 0;
            }
            systemId = t;
        }
        return systemId;
    }


    /**
     *
     * Description: 查询FD运价<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param param
     * @return <br>
     */
    public FlightFDPriceRes queryFDPrice(FdPriceParam param) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.FD, param.getSystemId());
        FlightFDPriceRes result;
        if (ibeplusFlag) {
            result = airFareDisplayWrapModule.queryAirFareDisplay(param);
        } else {
            result = ibeFdPriceWrapModule.queryFDPriceByIBE(param);
        }
        return result;
    }


    /**
     *
     * Description: AV余位查询<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param request
     * @param systemId
     * @return <br>
     */
    public AirAvailResult airAvail(AirAvailRequest request, int systemId) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.AV, convertSystemId(systemId));
        AirAvailResult airAvailResult = null;
        if (ibeplusFlag) {
            airAvailResult = airAvailDWrapModule.airAvailD(request);
        } else {
            airAvailResult = ibeAvWrapModule.getAvail(request);
        }
        return airAvailResult;
    }

    /**
     * 修改PNR，向PNR添加OSI项
     * @param pnrNo
     * @param airlineCode
     * @param osiInfo
     * @return
     */
    public void addOsiInfo(String pnrNo, String airlineCode, String osiInfo) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.OSI_ADD, convertSystemId(0));
        if (ibeplusFlag) {
            airBookModifyWrapModule.addOsi(pnrNo, airlineCode, osiInfo);
        } else {
            ibeModifyPnrWrapModule.addOSI4PNR(pnrNo, airlineCode, osiInfo);
        }
    }

    /**
     *
     * Description: <br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param pnrDataList
     * @param supplyOfficeNo <br>
     */
    public void authorizePnr(List<PnrInfo> pnrDataList, String supplyOfficeNo) {
        if (StringUtils.isNotEmpty(supplyOfficeNo)) {
            String rmkText = "TJ AUTH " + supplyOfficeNo + "/T";
            for (PnrInfo pnrData : pnrDataList) {
                String pnrNo = pnrData.getPnrNo();
                try {
                    addRMKInfo(pnrNo, rmkText, convertSystemId(0));
                } catch (Exception e) {
                    LOGGER.error("给PNR授权出票失败,pnr:" + pnrNo + ",失败原因：" + e);
                }
            }
        }
    }

    public void addRMKInfo(String pnrNo, String rmkTxt, int systemId) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.RMK_ADD, convertSystemId(systemId));
        if (ibeplusFlag) {
            airBookModifyWrapModule.addRMKInPnr(pnrNo, rmkTxt);
        } else {
            ibeModifyPnrWrapModule.addRMK4PNR(pnrNo, "", rmkTxt);
        }
    }

    /**
     *
     * Description: 添加EI项<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param pnrNo
     * @param eiText <br>
     */
    public void addEIInPnr(String pnrNo, String eiText) {
        airBookModifyWrapModule.addEIInPnr(pnrNo, eiText);
    }

    /**
     *
     * Description: BSP出票<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param pnr
     * @return <br>
     */
    public void issueTicketByBSP(String pnr) {
        ibeIssueWrapModule.issueTicketByBSP(pnr);
    }

    /**
     *
     * Description: BOP出票<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param pnr
     * @return <br>
     */
    public void issueTicketByBOP(String pnr) {
        bopIssueTicketWrapModule.issueTicketByBop(pnr);
    }

    /**
     *
     * Description: 按航段查询指定航班舱位运价<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param segmentList
     * @param psgType
     * @return <br>
     */
    public PataBySegRes airPriceBySegD(List<Segment> segmentList, String psgType) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.PATA_BY_SEG, convertSystemId(0));
        if (ibeplusFlag) {
            return airPriceBySegDWrapModule.airPriceBySegD(segmentList, psgType);
        } else {
            return ibePataBySegWrapModule.pataBySegments(segmentList, psgType);
        }
    }

    /**
     *
     * Description: 计算并存储价格<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param patByPnrReq
     * @return <br>
     */
    public PatByPnrRes doPatPriceByPnr(PatByPnrReq patByPnrReq, int systemId) {
        PatByPnrRes patByPnrRes = patPriceByPnrCalculate(patByPnrReq, convertSystemId(systemId));
        patPriceByPnrStore(patByPnrRes, systemId);
        return patByPnrRes;
    }

    /**
     *
     * Description: 计算pnr运价<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param patByPnrReq
     * @param systemId
     * @return <br>
     */
    public PatByPnrRes patPriceByPnrCalculate(PatByPnrReq patByPnrReq, int systemId) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.PATA, convertSystemId(systemId));
        if (ibeplusFlag && !PatByPnrReq.FARE_TYPE_FD.equals(patByPnrReq.getFareType())) {
            return airPriceDWrapModule.patPriceByPnrCalculate(patByPnrReq);
        } else {// FD价只能用ibe接口
            return ibePataWrapModule.patPriceByPnrCalculate(patByPnrReq);
        }
    }

    /**
     *
     * Description: 存储pnr运价<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param patByPnrRes
     * @param systemId <br>
     */
    public void patPriceByPnrStore(PatByPnrRes patByPnrRes, int systemId) {
        boolean ibeplusFlag = tsConfig.usePlus(TsConfig.PATA, convertSystemId(systemId));
        if (ibeplusFlag) {
            airPriceDWrapModule.patPriceByPnrStore(patByPnrRes);
        } else {
            ibePataWrapModule.patPriceByPnrStore(patByPnrRes);
        }
    }

}
