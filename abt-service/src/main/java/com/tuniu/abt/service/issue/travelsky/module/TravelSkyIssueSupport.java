package com.tuniu.abt.service.issue.travelsky.module;

import com.travelsky.espeed.*;
import com.tuniu.abt.dao.AbtPnrPriceDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.entity.AbtPnrPrice;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.issue.IssueDataSupport;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.service.travelsky.dto.PatByPnrReq;
import com.tuniu.abt.service.travelsky.ibeplus.module.AirBookModifyWrapModule;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 国内机票公共方法类
 *
 * @author baodawei 2015-4-1 下午5:22:42
 */
@Service
public class TravelSkyIssueSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelSkyIssueSupport.class);

    @Resource
    private TravelSkyInterface travelSkyInterface;
    @Resource
    private AirBookModifyWrapModule airBookModifyWrapModule;
    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    /**
     * 判断PNR是否包含EI项
     *
     * @param rtResult IbePlus方式RT返回值
     * @return
     */
    private static boolean isEIInPnr(OTAAirResRetRS.AirResRet rtResult) {

        List<EI> listEi = rtResult.getEI();
        if (null == listEi || 0 == listEi.size()) {
            return false;
        }
        return true;
    }

    /**
     * 判断PNR是否包含运价组
     *
     * @param rtResult IbePlus方式RT返回值
     * @return
     */
    private static boolean isFlightPriceInPnr(OTAAirResRetRS.AirResRet rtResult) {
        List<FC> listFc = rtResult.getFC();
        List<FP> listFp = rtResult.getFP();
        List<FN> listFn = rtResult.getFN();
        if (null == listFc || null == listFp || null == listFn || 0 == listFc.size() || 0 == listFp.size()
                || 0 == listFn.size()) {
            return false;

        }
        return true;
    }

    /**
     * Description: 校验actionCode是否为K<br>
     *
     * @param actionCode
     * @return <br>
     * @author lanlugang<br>
     * @taskId AIR-1387<br>
     */
    private static boolean checkActionCode(String actionCode) {
        // 带K的表示可以出票
        List<String> actionCodeOKList = new ArrayList<String>();
        actionCodeOKList.add("DK");
        actionCodeOKList.add("HK");
        actionCodeOKList.add("TK");
        actionCodeOKList.add("KK");
        actionCodeOKList.add("KL");
        if (actionCodeOKList.contains(actionCode)) {
            return true;
        }
        return false;
    }

    /**
     * Description: 校验PNR是否可出票<br>
     * 1.校验座位状态是否为K，如果已NO位，则重新K位<br>
     * 2.校验是否缺失运价组，如果缺失运价组，则重新做运价<br>
     * 3.校验是否缺失EI项，如果没有EI项，则添加EI项<br>
     *
     * @param pnr
     * @return <br>
     * @author tangaizhong<br>
     * @taskId AIR-2166<br>
     */
    public void checkPNRIssueTicketIsOK(String pnr) {

        OTAAirResRetRS.AirResRet airResRet = travelSkyInterface.rt(pnr);
        // 1.校验座位状态是否为K，如果已NO位，则重新K位
        boolean isNo2KCalled = false;

        if (!isInfActionCodeValid(airResRet.getSpecialServiceRequest())) {
            throw new BizzException(TicketEx.TS_ISSUED_CHECK_FAILED,
                    "PNR为：" + pnr + "状态出错，rt(婴儿)状态不为K状态");
        }
        List<String> actionCodes = getActionCodesFromRtResult(airResRet);
        for (String rtActionCode : actionCodes) {
            if (rtActionCode.contains("NO")) {
                segmentNO2KAndRequote(airResRet);
                isNo2KCalled = true;
                // 如果补位成功，则再次rtPnr判断actionCode是否可出票
                airResRet = travelSkyInterface.rt(pnr);
                List<String> actionCodesAfter = getActionCodesFromRtResult(airResRet);
                for (String actionCode : actionCodesAfter) {
                    // 如果返回的PNR信息中不包含带K的信息，说明没有可以出的票号，返回PNR状态出错
                    if (!checkActionCode(actionCode)) {
                        throw new BizzException(TicketEx.TS_ISSUED_CHECK_FAILED,
                                "PNR为：" + pnr + "状态出错，航段NO位补位后再次rt状态为："
                                        + rtActionCode);
                    }
                }
                // 如果返回的PNR中actionCode都为可出票的状态，则补位成功，可以继续出票
                break;
            }
            // pnr状态为RR,则RT获取票号信息，并返回出票成功 added by @lanlugang 2015-12-15
            else if (rtActionCode.contains("RR")) {
                throw new BizzException(TicketEx.TS_ALREADY_ISSUED, "PNR:"+pnr+"rt状态为RR,已出票");
            }
            // 如果返回的PNR信息中不包含带K的信息，说明没有可以出的票号，返回PNR状态出错(如果是RR已出票，则直接返回)
            else if (!checkActionCode(rtActionCode)) {
                throw new BizzException(TicketEx.TS_ISSUED_CHECK_FAILED,
                        "PNR为：" + pnr + "状态出错，rt状态为：" + rtActionCode);
            }
        }

        // 2.校验是否缺失运价组，如果缺失运价组，则重新做运价
        if (!isNo2KCalled) {
            if (!isFlightPriceInPnr(airResRet)) {
                String psgType = getPsgType(airResRet);
                addPataItemsInPnr(pnr, psgType);
                airResRet = travelSkyInterface.rt(pnr);
            }
        }
        // 3.校验是否缺失EI项，如果没有EI项，则添加EI项
        if (!isEIInPnr(airResRet)) {
            travelSkyInterface.addEIInPnr(pnr, "不得签转");
        }
    }

    private List<FlightSegment2> getSegments(List<FlightSegments2> flightSegments) {
        List<FlightSegment2> segments = new ArrayList<FlightSegment2>();
        for (FlightSegments2 flightSegment : flightSegments) {
            for (FlightSegment2 segment : flightSegment.getFlightSegment()) {
                segments.add(segment);
            }
        }
        return segments;
    }

    /**
     * 验证婴儿的行动代码是否有效
     * @param specialServiceRequest
     * @return
     */
    private boolean isInfActionCodeValid(List<SpecialServiceRequest> specialServiceRequest) {
        for (SpecialServiceRequest ssr : specialServiceRequest) {
            if ("INFT".equals(ssr.getSSRCode())
                    && !checkActionCode(ssr.getStatus())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据IbePlusRt结果返回航段状态
     *
     * @param airResRet
     * @return 航段状态，缺口程为空字符串不返回
     * @author tangaizhong<br>
     * @taskId AIR-1387<br>
     */
    private static List<String> getActionCodesFromRtResult(OTAAirResRetRS.AirResRet airResRet) {
        List<String> actionCodes = new ArrayList<String>();
        for (FlightSegments2 segments : airResRet.getFlightSegments()) {
            for (FlightSegment2 segment : segments.getFlightSegment()) {
                if (null != segment.getStatus() && !"".equals(segment.getStatus())) {
                    actionCodes.add(segment.getStatus());
                }
            }
        }
        return actionCodes;
    }

    /**
     * Description: K座&重新计算存储运价组<br>
     *
     * @return <br>
     * @author lanlugang<br>
     * @date 2015-7-13
     * @taskId PTICKET-1578<br>
     */
    private void segmentNO2KAndRequote(OTAAirResRetRS.AirResRet airResRet) {
        String pnrNo = airResRet.getBookingReferenceID().getID();
        // LOG.info("开始调用IBEPLUS航段NO位补位&重新计算存储价格组接口，PNR号：" + pnrNo);
        // 0.获取乘客类型
        String psgType = getPsgType(airResRet);
        if (null == psgType) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_RESULT_ERROR,
                    "调用IBEPLUS航段NO位补位：获取RT结果中人员类型为空，PNR号：" + pnrNo);
        }
        // TODO 1. 如果有婴儿，则删除原婴儿项 SSR INFT ...; XN/IN ,重新rt 获取最新的ota_AirResRetRS
        Boolean infFlag = BizzConstants.PSG_TYPE_CODE_INF.equals(psgType);
        if (infFlag) {
            // 暂不支持婴儿NO位补位
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX,
                    "调用IBEPLUS航段NO位补位：该PNR中含有婴儿，暂不支持婴儿的NO位补位，PNR号：" + pnrNo);
        }
        // 2. NO位补位
        List<FlightSegment2> segmentList = getSegments(airResRet.getFlightSegments());
        airBookModifyWrapModule.segmentNo2KByIBEPLUS(pnrNo, segmentList);
        // 3. 删除原运价组(FC/FN/FP/EI)
        List<String> rphList = getFareGroupRphList(airResRet);
        if (CollectionUtils.isNotEmpty(rphList)) {
            travelSkyInterface.delPnrItem(pnrNo, rphList);
        }
        // TODO 4. 如果有婴儿，则重新写入婴儿项
        if (infFlag) {
            // TODO
        }
        // 5. 重新PATA(计算存储运价)
        addPataItemsInPnr(pnrNo, psgType);
    }

    /**
     * @param pnrNo
     * @param psgType 乘客类型列表
     * @return
     */
    private void addPataItemsInPnr(String pnrNo, String psgType) {
        if (null == pnrNo || null == psgType || "".equals(pnrNo)) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX,
                    "参数PNR或者乘客类型列表无效,添加运价组失败");
        }
        PatByPnrReq patByPnrReq = new PatByPnrReq();
        patByPnrReq.setPnrNo(pnrNo);
        if (psgType.equals(BizzConstants.PSG_TYPE_CODE_ADT)) {
            psgType = PatByPnrReq.PSG_TYPE_ADT;
        } else if (psgType.equals(BizzConstants.PSG_TYPE_CODE_CHD)) {
            psgType = getChdPatTypeFromDBPrice(pnrNo);// 从占位价格中获取运价类型
        } else if (psgType.equals(BizzConstants.PSG_TYPE_CODE_INF)) {
            psgType = PatByPnrReq.PSG_TYPE_ADT;
            patByPnrReq.setHasBaby(true);
        }
        patByPnrReq.setPsgType(psgType);
        travelSkyInterface.doPatPriceByPnr(patByPnrReq, 0);
    }

    /**
     * 从库中获取儿童的运价类型（儿童存在使用成人价的情况）
     * @param pnrNo
     * @return
     */
    private String getChdPatTypeFromDBPrice(String pnrNo) {
        IssueRequest issueRequest = IssueDataSupport.getRequest();

        List<AbtPnrPrice> prices = abtPnrPriceDao.findByPnrAndFlightItemIdAndOrderId(pnrNo,
                issueRequest.getIssueDetail().getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
        if (CollectionUtils.isNotEmpty(prices)) {
            for (AbtPnrPrice price : prices) {
                int patType = price.getPriceType();
                if (patType == BizzConstants.PERSON_TYPE_ADULT) {
                    return PatByPnrReq.PSG_TYPE_ADT;
                }
            }
        }
        return PatByPnrReq.PSG_TYPE_CHD;
    }

    /**
     * Description: 根据RT结果获取PNR的人员类型<br>
     *
     * @param airResRet
     * @return <br>
     * @author lanlugang<br>
     * @taskId PTICKET-1578<br>
     */
    private static String getPsgType(OTAAirResRetRS.AirResRet airResRet) {
        List<AirTraveler2> airTravelerList = airResRet.getAirTraveler();
        boolean adtFlag = false;
        boolean chdFlag = false;
        boolean infFlag = false;
        PassengerType pgsType = null;
        for (AirTraveler2 airTraveler : airTravelerList) {
            pgsType = airTraveler.getPassengerTypeQuantity().getCode();
            if (PassengerType.ADT.equals(pgsType)) {
                adtFlag = true;
            } else if (PassengerType.CHD.equals(pgsType)) {
                chdFlag = true;
            } else if (PassengerType.INF.equals(pgsType)) {
                infFlag = true;
            }
        }
        // 既有成人又有婴儿，则人员类型传婴儿(INF)
        if (adtFlag && infFlag) {
            return BizzConstants.PSG_TYPE_CODE_INF;
        } else if (adtFlag) {
            // 只有成人没有婴儿，人员类型传成人(ADT)
            return BizzConstants.PSG_TYPE_CODE_ADT;
        } else if (chdFlag) {
            // 有儿童，则人员类型传儿童(CHD)
            return BizzConstants.PSG_TYPE_CODE_CHD;
        }
        return null;
    }

    /**
     * Description: 获取RT结果中运价组的RphList<br>
     *
     * @param airResRet
     * @return <br>
     * @author lanlugang<br>
     * @date 2015-7-11
     * @taskId PTICKET-1578<br>
     */
    private static List<String> getFareGroupRphList(OTAAirResRetRS.AirResRet airResRet) {
        List<String> rphList = new ArrayList<String>();
        if (null != airResRet) {
            // 添加FC的rph
            List<FC> fcList = airResRet.getFC();
            if (CollectionUtils.isNotEmpty(fcList)) {
                for (com.travelsky.espeed.FC fc : fcList) {
                    rphList.add(String.valueOf(fc.getRPH()));
                }
            }
            // 添加EI的rph
            List<EI> eiList = airResRet.getEI();
            if (CollectionUtils.isNotEmpty(eiList)) {
                for (com.travelsky.espeed.EI ei : eiList) {
                    rphList.add(String.valueOf(ei.getRPH()));
                }
            }
            // 添加FP的rph
            List<FP> fpList = airResRet.getFP();
            if (CollectionUtils.isNotEmpty(fpList)) {
                for (com.travelsky.espeed.FP fp : fpList) {
                    rphList.add(String.valueOf(fp.getRPH()));
                }
            }
        }
        return rphList;
    }

}
