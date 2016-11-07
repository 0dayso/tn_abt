package com.tuniu.abt.service.refund.ctrip;

import com.tuniu.abt.intf.constants.RefundEx;
import com.tuniu.abt.intf.dto.custom.ctrip.CtripRefundRequest;
import com.tuniu.abt.intf.dto.refund.ProcRefundData;
import com.tuniu.abt.intf.dto.refund.ReqRefund;
import com.tuniu.abt.intf.dto.refund.RespRefund;
import com.tuniu.abt.intf.dto.refund.RespRefundItem;
import com.tuniu.abt.intf.entity.AbtRefundItem;
import com.tuniu.abt.intf.entity.AbtRefundOrder;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.ctrip.module.CtripRefundModule;
import com.tuniu.abt.service.ctrip.module.CtripReviseModule;
import com.tuniu.abt.service.refund.RefundCommonUtils;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.entity.SubOrderIdAndPersonVo;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.*;
import com.tuniu.operation.platform.base.text.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 携程退票申请服务
 * Created by chengyao on 2016/3/4.
 */
@Service
public class CtripRefundApplyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripRefundApplyService.class);

    @Resource
    private CtripRefundModule ctripRefundModule;
    @Resource
    private CtripReviseModule ctripReviseModule;
    @Resource
    private SystemConfig systemConfig;

    /**
     * 携程退票操作
     * @param procRefundData procRefundData
     * @return 操作结果
     */
    public RespRefund refund(ProcRefundData procRefundData) {
        List<AbtRefundItem> refundItems = procRefundData.getAbtRefundItems();
        boolean auto = AbtRefundOrder.REFUND_SOURCE_A.equals(procRefundData.getAbtRefundOrder().getRefundSource());

        // 检验阀值
        if (auto) {
            RefundCommonUtils.checkDifferenceThreshold(refundItems, systemConfig.getRefundDifferenceThreshold());
        }

        RespRefund respRefund = new RespRefund();
        respRefund.setItems(new ArrayList<RespRefundItem>());

        List<AbtTicketMain> ticketMains = procRefundData.getRefundTicketMains();
        for (AbtTicketMain ticketMain : ticketMains) {
            RespRefundItem item = singleRefund(ticketMain, procRefundData.getReqRefund());

            // 更新表状态
            List<AbtTicketMain> updates = new ArrayList<AbtTicketMain>();
            updates.add(ticketMain);
            RefundCommonUtils.updateDealStatus(updates, procRefundData);

            respRefund.getItems().add(item);
        }

        procRefundData.getAbtRefundOrder().setStatus(AbtRefundOrder.STATUS_OK);

        return respRefund;
    }

    /**
     * 单个项目的退票操作
     * @param abtTicketMain abtTicketMain
     * @param reqRefund reqRefund
     * @return 退票返回项目
     */
    private RespRefundItem singleRefund(AbtTicketMain abtTicketMain, ReqRefund reqRefund) {
        RespRefundItem result = new RespRefundItem();
        result.setTicketNo(abtTicketMain.getTicketNo());
        result.setSegments(null);
        result.setRefundAmount(new BigDecimal(0));
        result.setSubsidy(new BigDecimal(0));

        String orderId = abtTicketMain.getPnr();

        CtripRefundRequest ctripRefundRequest = new CtripRefundRequest();
        String segmentPosition = reqRefund.getSegmentPosition();
        if (StringUtils.isEmpty(segmentPosition)) {
            segmentPosition = ReqRefund.DEFAULT_SEGMENT_POSITION;
        }
        Boolean receivedSegment = reqRefund.getReceivedSegment();
        if (receivedSegment == null) {
            receivedSegment = ReqRefund.DEFAULT_RECEIVED_SEGMENT;
        }
        ctripRefundRequest.setSegementPosition(segmentPosition);
        ctripRefundRequest.setRecievedSegment(receivedSegment);
        ctripRefundRequest.setRefundType(reqRefund.getRefundType());
        ctripRefundRequest.setOrderID(orderId);

        String passengerName = abtTicketMain.getPassengerName();

        // 查询可退票验证接口，填充退票请求的子项目，更新退票金额
        buildCtripRefundRequest(orderId, passengerName, ctripRefundRequest, result);

        // TODO 是否需要额外的失败判断
        ctripRefundModule.refundOrderV2(ctripRefundRequest);

        return result;
    }


    private void buildCtripRefundRequest(String orderId, String passengerName, CtripRefundRequest ctripRefundRequest, RespRefundItem result) {
        RefundResponse refundResponse = ctripReviseModule.reviseCondition(orderId);
        FlightReviseConditionResponse flightReviseConditionResponse = refundResponse.getFlightReviseConditionResponse();

        SegmentsType segmentsType = flightReviseConditionResponse.getSegments();
        List<SegmentInfo> segmentInfos = segmentsType.getSegmentInfoList();
        boolean found = false;
        for (SegmentInfo segmentInfo : segmentInfos) {
            RefundPassengerInfo refundPassengerInfo = segmentInfo.getPassengerInfo();
            if (passengerName.equals(refundPassengerInfo.getName())) {
                boolean refundable = Boolean.valueOf(refundPassengerInfo.getRefundable());
                if (!refundable) {
                    throw new BizzException(RefundEx.CTRIP_REFUND_ERROR, "查询旅客：" + passengerName + " 不可退票");
                }
                boolean refundFeeAvailable = Boolean.valueOf(segmentInfo.getRefundFeeAvailable());
                if (!refundFeeAvailable) {
                    throw new BizzException(RefundEx.CTRIP_REFUND_ERROR, "查询旅客：" + passengerName + " 不能计算出退票费用");
                }
                RefundOrderFlight refundOrderFlight = new RefundOrderFlight();
                refundOrderFlight.setPassengerName(passengerName);
                refundOrderFlight.setSequence(Integer.parseInt(segmentInfo.getSequence()));
                refundOrderFlight.setRefund(segmentInfo.getRefundFee());
                refundOrderFlight.setRefundRate(segmentInfo.getRate());
                ctripRefundRequest.setItems(new ArrayList<RefundOrderFlight>());
                ctripRefundRequest.getItems().add(refundOrderFlight);
                result.setSubsidy(result.getSubsidy().add(new BigDecimal(segmentInfo.getSubsidy())));
                result.setRefundAmount(result.getRefundAmount().add(new BigDecimal(segmentInfo.getRefundFee())));
                found = true;
            }
        }
        if (!found) {
            throw new BizzException(RefundEx.CTRIP_REFUND_ERROR, "查询旅客：" + passengerName + " 不在可退票信息中");
        }
    }


    /**
     * 国内机票退票申请功能
     *
     * @param flightOrderRefundVo FlightOrderRefundVo
     * @return CtripRefundApplyResult
     */
//    public CtripRefundApplyResult autoRefund(FlightOrderRefundVo flightOrderRefundVo) {
//        String itemRefundErrorRemark = null;
//        int orderId = flightOrderRefundVo.getOrderId();
//
//        List<ApplyFlightOrderRefundVo> refundInfoList = flightOrderRefundVo.getRefundInfoList();
//        if (null == refundInfoList || refundInfoList.size() == 0) {
//            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "refundInfoList为空");
//        }
//
//        List<SubOrderIdAndPersonVo> subOrderIdAndPersonVoList = ctripService.getPersonInfoByOrderId(orderId);
//        if (subOrderIdAndPersonVoList == null || subOrderIdAndPersonVoList.size() == 0) {
//            throw new BizzException(RefundEx.CTRIP_PERSON_NOT_FOUND);
//        }
//
//        try {
//            //modified by lirongrong3 2015-12-23 begin
//            List<FlightPnrRefund> refundedList = ctripService.queryFlightRefundListByOrderId(orderId);
//
//            boolean checkFlag = this.checkPersonNum(subOrderIdAndPersonVoList, refundInfoList, refundedList, orderId);
//            //modified by lirongrong3 2015-12-23 end
//            if (!checkFlag) {
//                throw new BizzException(RefundEx.CTRIP_CONDITION_ERROR);
//            }
//
//            List<ApplyFlightOrderRefundResVo> resList = new ArrayList<ApplyFlightOrderRefundResVo>();
//            for (ApplyFlightOrderRefundVo applyFlightOrderRefundVo : refundInfoList) {
//                itemRefundErrorRemark = processItemRefund(flightOrderRefundVo, applyFlightOrderRefundVo,
//                        itemRefundErrorRemark, resList);
//            }
//
//            if (StringUtils.isNotEmpty(itemRefundErrorRemark)) {
//                throw new BizzException(RefundEx.CTRIP_REFUND_ITEM_ERROR, itemRefundErrorRemark);
//            }
//
//            CtripRefundApplyResult result = new CtripRefundApplyResult();
//            result.setRefundApplyResList(resList);
//            return result;
//        } catch (BizzException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            LOGGER.error("退票申请：调用携程接口失败，对应的途牛订单号为：" + orderId, ex);
//            throw new BizzException(RefundEx.CTRIP_REFUND_ERROR, ex);
//        }
//
//        // 和原有逻辑相比，如果出现某项目保存失败，抛出错误
////        } catch (Exception e) {
////            LOG.info("【国内机票退票申请】调用携程接口失败，对应的途牛订单号为：" + orderId + "，错误信息为：", e);
////            responseVo.setSuccess(false);
////            responseVo.setMsg("【国内机票退票申请】调用携程接口失败");
////        } finally {
////            if (!"".equals(errorString) && null != errorString) {
////                errorString = errorString.substring(0, (errorString.length() - 1));
////                responseVo.setSuccess(true);
////                responseVo.setMsg(errorString);
////            }
////        }
//    }
//
//    private String processItemRefund(FlightOrderRefundVo flightOrderRefundVo, ApplyFlightOrderRefundVo applyFlightOrderRefundVo,
//            String errorString, List<ApplyFlightOrderRefundResVo> resList) {
//        int orderId = flightOrderRefundVo.getOrderId();
//        int uId = flightOrderRefundVo.getuId();
//        String uName = flightOrderRefundVo.getuName();
//        String operateDate = flightOrderRefundVo.getOperateDate();
//        String remark = flightOrderRefundVo.getRemark();
//
//        RefundOrderApplyResponse refundResponse = ctripRefundModule.refundOrder(applyFlightOrderRefundVo, remark);
//
//        //将携程请求、响应记录到数据库中
//        FlightPnrRefundLog flightPnrRefundLog = new FlightPnrRefundLog();
//        flightPnrRefundLog.setOrderId(orderId);
//        flightPnrRefundLog.setSubOrderId(applyFlightOrderRefundVo.getSubOrderId());
//        flightPnrRefundLog.setPassengerName(applyFlightOrderRefundVo.getPassengerName());
//        flightPnrRefundLog.setPersonId(applyFlightOrderRefundVo.getPersonId());
//        flightPnrRefundLog.setSequence(applyFlightOrderRefundVo.getSequence());
//        //flightPnrRefundLog.setRes(responseString);
//        //flightPnrRefundLog.setReq(reqXml);
//        flightPnrRefundLog.setType(1);
//        //向表flight_pnr_refund_log_ctrip中新增一条记录
//        ctripService.saveFlightPnrRefundLog(flightPnrRefundLog);
//
//        ApplyFlightOrderRefundResVo applyResVo = new ApplyFlightOrderRefundResVo();
//        applyResVo.setSubOrderId(applyFlightOrderRefundVo.getSubOrderId());
//        applyResVo.setPassengerName(applyFlightOrderRefundVo.getPassengerName());
//        applyResVo.setSequence(applyFlightOrderRefundVo.getSequence());
//
//        RefundOrderResponse refundOrderResponse = refundResponse.getRefundOrderResponse();
//        //0成功，非零失败
//        int resultStatus = refundOrderResponse.getResultStatus();
//        if (resultStatus == 0) {
//            applyResVo.setApplyRefundStatus(true);
//            String subOrderId = applyFlightOrderRefundVo.getSubOrderId();
//            try {
//                //将退票申请对象保存到数据库表flight_pnr_refund_ctrip中
//                FlightPnrRefund flightCTripOrderRefund = new FlightPnrRefund();
//                flightCTripOrderRefund.setSubOrderId(subOrderId);
//                flightCTripOrderRefund.setPassengerName(applyFlightOrderRefundVo.getPassengerName());
//                flightCTripOrderRefund.setPersonId(applyFlightOrderRefundVo.getPersonId());
//                flightCTripOrderRefund.setSequence(applyFlightOrderRefundVo.getSequence());
//                flightCTripOrderRefund.setRefundFee(applyFlightOrderRefundVo.getRefundFee());
//                flightCTripOrderRefund.setRefundRate(applyFlightOrderRefundVo.getRefundRate());
//                flightCTripOrderRefund.setRefundType(applyFlightOrderRefundVo.getRefundType());
//                flightCTripOrderRefund.setRecievedSegment(applyFlightOrderRefundVo.getRecievedSegment());
//                flightCTripOrderRefund.setSegementPosition(applyFlightOrderRefundVo.getSegementPosition());
//                flightCTripOrderRefund.setRefundFlyFee(applyFlightOrderRefundVo.getRefundFlyFee());
//                flightCTripOrderRefund.setRefundReasonRemark(applyFlightOrderRefundVo.getRefundReasonRemark());
//                flightCTripOrderRefund.setuId(uId);
//                flightCTripOrderRefund.setuName(uName);
//                flightCTripOrderRefund.setOperateDate(operateDate);
//                flightCTripOrderRefund.setAuditStatus("F");
//                flightCTripOrderRefund.setOrderId(orderId);
//                flightCTripOrderRefund.setPersonType(applyFlightOrderRefundVo.getPersonType());
//                flightCTripOrderRefund.setRemark(remark);
//                ctripService.saveFlightPnrRefund(flightCTripOrderRefund);
//            } catch (Exception ex) {
//                LOGGER.error("退票申请：调用携程申请退票接口成功，插入数据库时异常，对应的途牛订单号为：" +
//                        orderId + "，携程订单号为：" + subOrderId, ex);
//
//                if (StringUtils.isEmpty(errorString)) {
//                    errorString = "退票申请：调用携程申请退票接口成功，插入数据库时异常，对应的途牛订单号为："
//                                    + orderId + "，携程订单号为：" + subOrderId + "、";
//                } else {
//                    errorString = errorString + subOrderId + "、";
//                }
//            }
//        } else {
//            applyResVo.setApplyRefundStatus(false);
//        }
//        resList.add(applyResVo);
//        return errorString;
//    }

    /**
     * @param subOrderIdAndPersonVoList 订单总人数
     * @param refundInfoList            本次申请退票的人员信息
     * @param refundedList              已经申请退票的人员信息
     * @param orderId
     * @return
     */
    public boolean checkPersonNum(List<SubOrderIdAndPersonVo> subOrderIdAndPersonVoList,
            List<ApplyFlightOrderRefundVo> refundInfoList, List<FlightPnrRefund> refundedList, int orderId) {
        boolean flag = true;

        //得到已经申请退票的人数信息
        Map<String, Map<Integer, Integer>> refundMap = this.getPersonNumMap(refundedList, orderId);

        List<FlightPnrRefund> flightPnrRefundListAll = new ArrayList<FlightPnrRefund>();
        for (SubOrderIdAndPersonVo subOrderIdAndPerson : subOrderIdAndPersonVoList) {
            FlightPnrRefund flightPnrRefundObj = new FlightPnrRefund();
            flightPnrRefundObj.setSubOrderId(subOrderIdAndPerson.getSubOrderId());
            flightPnrRefundObj.setPersonType(subOrderIdAndPerson.getPersonType());
            flightPnrRefundListAll.add(flightPnrRefundObj);
        }

        //得到该订单的总人数信息
        Map<String, Map<Integer, Integer>> allMap = this.getPersonNumMap(flightPnrRefundListAll, orderId);

        List<FlightPnrRefund> needFlightPnrRefundList = new ArrayList<FlightPnrRefund>();
        for (ApplyFlightOrderRefundVo applyObj : refundInfoList) {
            FlightPnrRefund flightPnrRefundObjc = new FlightPnrRefund();
            flightPnrRefundObjc.setSubOrderId(applyObj.getSubOrderId());
            flightPnrRefundObjc.setPersonType(applyObj.getPersonType());
            needFlightPnrRefundList.add(flightPnrRefundObjc);
        }

        //得到需要申请退票的人数信息
        Map<String, Map<Integer, Integer>> needRefundMap = this.getPersonNumMap(needFlightPnrRefundList, orderId);

        //判断退票后是否满足一个成人至多带二个儿童
        if (needRefundMap != null && needRefundMap.size() > 0) {
            Iterator<String> ite = needRefundMap.keySet().iterator();
            while (ite.hasNext()) {
                String departureDateKey = ite.next();
                int adultNum = 0;
                int childNum = 0;

                //需要申请退票的人数
                Map<Integer, Integer> needMap = needRefundMap.get(departureDateKey);
                if (null != needMap && needMap.size() > 0) {
                    Iterator<Integer> iterator = needMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        int personType = iterator.next();
                        int personNum = 0;
                        if (needMap.get(personType) != null) {
                            personNum = needMap.get(personType);
                        }

                        if (personType == 1) {
                            adultNum = personNum;
                        }
                        if (personType == 2) {
                            childNum = personNum;
                        }
                    }
                }

                //获取订单的总人数
                int adultSum = 0;
                int childSum = 0;
                Map<Integer, Integer> allPersonMap = allMap.get(departureDateKey);
                if (null != allPersonMap && allPersonMap.size() > 0) {
                    Iterator<Integer> iterator = allPersonMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        int personType = iterator.next();
                        int personNumAll = 0;

                        if (allPersonMap.get(personType) != null) {
                            personNumAll = allPersonMap.get(personType);
                        }
                        if (personType == 1) {
                            adultSum = personNumAll;
                        }
                        if (personType == 2) {
                            childSum = personNumAll;
                        }
                    }
                }

                //获取已经申请退票的人员信息
                int adultRefundNum = 0;
                int childRefundNum = 0;
                if (refundMap != null && refundMap.size() > 0) {
                    if (refundMap.containsKey(departureDateKey)) {
                        Map<Integer, Integer> refundPersonMap = refundMap.get(departureDateKey);
                        if (null != refundPersonMap && refundPersonMap.size() > 0) {
                            Iterator<Integer> iterator = refundPersonMap.keySet().iterator();
                            while (iterator.hasNext()) {
                                int personType = iterator.next();
                                int personNum = 0;
                                if (refundPersonMap.get(personType) != null) {
                                    personNum = refundPersonMap.get(personType);
                                }
                                if (personType == 1) {
                                    adultRefundNum = personNum;
                                }
                                if (personType == 2) {
                                    childRefundNum = personNum;
                                }
                            }
                        }
                    }
                }

                int adtNum = adultSum - adultNum - adultRefundNum;
                int cnnNum = childSum - childNum - childRefundNum;

                if ((adtNum < 0) || (cnnNum < 0)) {
                    flag = false;
                    break;
                }
                if ((adtNum == 0) && (cnnNum > 0)) {
                    flag = false;
                    break;
                }
                if ((adtNum > 0) && (cnnNum > 0)) {
                    if (adtNum * 2 < cnnNum) {
                        flag = false;
                        break;
                    }
                }
            }

        }

        return flag;
    }

    /**
     * getPersonNumMap
     * @param refundedList
     * @param orderId
     * @return
     */
    public Map<String, Map<Integer, Integer>> getPersonNumMap(List<FlightPnrRefund> refundedList, int orderId) {
        return null;
//        Map<String, Map<Integer, Integer>> refundMap = new HashMap<String, Map<Integer, Integer>>();
//        if (null != refundedList && refundedList.size() > 0) {
//            for (FlightPnrRefund flightPnrRefundObj : refundedList) {
//                int personTypea = flightPnrRefundObj.getPersonType();
//                List<FlightPnrFlight> flightPnrFlightList = flightPnrFlightService
//                        .queryFlightPnrFlightByPnr(flightPnrRefundObj.getSubOrderId(), orderId);
//
//                if (null != flightPnrFlightList && flightPnrFlightList.size() > 0) {
//                    for (FlightPnrFlight flightPnrFlightObj : flightPnrFlightList) {
//                        String departureTimea = flightPnrFlightObj.getDepartureTime();
//
//                        if (refundMap.containsKey(departureTimea)) {
//                            Map<Integer, Integer> personMap = refundMap.get(departureTimea);
//                            int personNumber = 0;
//                            if (personMap.get(personTypea) == null) {
//                                personNumber = 1;
//                            } else {
//                                personNumber = personMap.get(personTypea) + 1;
//                            }
//
//                            refundMap.remove(departureTimea);
//                            personMap.remove(personTypea);
//                            personMap.put(personTypea, personNumber);
//                            refundMap.put(departureTimea, personMap);
//                        } else {
//                            Map<Integer, Integer> personMap = new HashMap<Integer, Integer>();
//                            personMap.put(personTypea, 1);
//                            refundMap.put(departureTimea, personMap);
//                        }
//                    }
//
//                }
//
//            }
//        }
//
//        return refundMap;
    }

    public static class CtripRefundApplyResult {
        private List<ApplyFlightOrderRefundResVo> refundApplyResList;

        public List<ApplyFlightOrderRefundResVo> getRefundApplyResList() {
            return refundApplyResList;
        }

        public void setRefundApplyResList(List<ApplyFlightOrderRefundResVo> refundApplyResList) {
            this.refundApplyResList = refundApplyResList;
        }
    }

}
