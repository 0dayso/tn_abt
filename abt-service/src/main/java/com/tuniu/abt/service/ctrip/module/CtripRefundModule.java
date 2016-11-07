package com.tuniu.abt.service.ctrip.module;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.custom.ctrip.CtripRefundRequest;
import com.tuniu.abt.intf.tsp.dto.res.ResCtripSearchRelationResp;
import com.tuniu.abt.service.ctrip.CtripHeaderSupport;
import com.tuniu.abt.service.ctrip.CtripInterfaceService;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.adapter.flightTicket.domain.CtripRuleInfo;
import com.tuniu.adapter.flightTicket.domain.FlightIndivSeatPrice;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.common.RequestHeader;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundEndorseFee.*;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.*;
import com.tuniu.adapter.flightTicket.vo.inquiry.RefundEndorseReqesutVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengyao on 2016/2/25.
 */
@Service
public class CtripRefundModule {
    @Resource
    private CtripHeaderSupport ctripHeaderSupport;
    @Resource
    private CtripInterfaceService ctripInterfaceService;

    public FltRefundEndorseFeeResponse refundEndorseFee(ResCtripSearchRelationResp searchRelation) {
        FltRefundEndorseFeeRequest request = toRequest(searchRelation);
        return ctripInterfaceService.refundEndorseFee(request);
    }

    public RefundOrderApplyResponse refundOrder(ApplyFlightOrderRefundVo applyFlightOrderRefundVo, String remark) {
        RefundOrderApplyRequest request = getApplyAutoRefundReq(applyFlightOrderRefundVo, remark);
        return ctripInterfaceService.refundOrder(request);
    }

    public RefundOrderApplyResponse refundOrderV2(CtripRefundRequest ctripRefundRequest) {
        RefundOrderApplyRequest request = toRefundOrderApplyRequest(ctripRefundRequest);
        return ctripInterfaceService.refundOrder(request);
    }

    private RefundOrderApplyRequest toRefundOrderApplyRequest(CtripRefundRequest request) {

        RefundOrderRequest refundOrderRequest = CommonUtils.transform(request, RefundOrderRequest.class);
        refundOrderRequest.setRuefundOrderID("0");
        refundOrderRequest.setSource("Distribution");
        refundOrderRequest.setCreateUser("tuniu");
        refundOrderRequest.setAdditionFee("0");
        refundOrderRequest.setInsuranceFee("0");
        refundOrderRequest.setServiceFee("0");
        refundOrderRequest.setSendTicketFee("0");
        refundOrderRequest.setNeedRefundInvoice(false);
        refundOrderRequest.setUrgent(true);
        refundOrderRequest.setAutoInsuranceRefund(false);

        String refundType = request.getRefundType();
        String remark = request.getRemark();
        String defaultRemark = parseDefaultRemarkString(refundType);

        if (StringUtils.isNotEmpty(defaultRemark)) {
            remark = defaultRemark + remark;
        }
        refundOrderRequest.setRemark(remark);

        refundOrderRequest.setRefundOrderFlightInfo(new RefundOrderFlightInfo());
        List<RefundOrderFlight> items = CommonUtils.transformList(request.getItems(), RefundOrderFlight.class);
        refundOrderRequest.getRefundOrderFlightInfo().setRefundOrderFlight(items);

        BigDecimal total = new BigDecimal(0);
        for (RefundOrderFlight refundOrderFlight : items) {
            // use passengerName/sequence/refund/refundRate
            refundOrderFlight.setRefundType(refundType);
            refundOrderFlight.setInv(false);
            refundOrderFlight.setRebookingListID("0");
            refundOrderFlight.setRbkID("0");
            total = total.add(new BigDecimal(refundOrderFlight.getRefund()));
        }
        refundOrderRequest.setRefundFee(total.toString());

        RefundOrderApplyRequest result = new RefundOrderApplyRequest();
        //创建header
        result.setRefundOrderRequest(refundOrderRequest);
        result.setHeader(ctripHeaderSupport.build("FlightRefundOrder"));
        return result;
    }

    private String parseDefaultRemarkString(String refundType) {
        String defaultRemark = StringUtils.EMPTY;
        if ("航变".equals(refundType)) {
            defaultRemark = "航班变动，请核对！";
        } else if ("病退".equals(refundType)) {
            defaultRemark = "病退，资料证明可线下先沟通！";
        } else if ("备降".equals(refundType)) {
            defaultRemark = "如需证明，线下沟通！";
        } else if ("重购全退".equals(refundType)) {
            defaultRemark = "请核对是否重购全退！";
        } else if ("其它".equals(refundType)) {
            defaultRemark = "具体退票信息以双方运营人员沟通处理！";
        }
        return defaultRemark;
    }

    public RefundOrderApplyRequest getApplyAutoRefundReq(ApplyFlightOrderRefundVo applyFlightOrderRefundVo, String remark) {
        RefundOrderApplyRequest request = new RefundOrderApplyRequest();
        RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
        refundOrderRequest.setRuefundOrderID("0");
        refundOrderRequest.setOrderID(applyFlightOrderRefundVo.getSubOrderId());
        refundOrderRequest.setSource("Distribution");
        refundOrderRequest.setCreateUser("tuniu");
        refundOrderRequest.setAdditionFee("0");
        refundOrderRequest.setRefundFee(applyFlightOrderRefundVo.getRefundFee());
        refundOrderRequest.setInsuranceFee("0");
        refundOrderRequest.setServiceFee("0");
        refundOrderRequest.setSendTicketFee("0");
        refundOrderRequest.setSegementPosition(applyFlightOrderRefundVo.getSegementPosition());

        String refundType = applyFlightOrderRefundVo.getRefundType();
        String defaultRemark = parseDefaultRemarkString(refundType);

        if (StringUtils.isNotEmpty(defaultRemark)) {
            remark = defaultRemark + remark;
        }

        refundOrderRequest.setRemark(remark);
        //是否收到行程单    0：收到 1：未收到
        if (applyFlightOrderRefundVo.getRecievedSegment() == 0) {
            refundOrderRequest.setRecievedSegment(true);
        } else {
            refundOrderRequest.setRecievedSegment(false);
        }
        refundOrderRequest.setNeedRefundInvoice(false);
        refundOrderRequest.setUrgent(true);

        refundOrderRequest.setAutoInsuranceRefund(false);

        RefundOrderFlightInfo refundOrderFlightInfo = new RefundOrderFlightInfo();
        List<RefundOrderFlight> refundOrderFlightList = new ArrayList<RefundOrderFlight>();
        RefundOrderFlight refundOrderFlight = new RefundOrderFlight();
        refundOrderFlight.setPassengerName(applyFlightOrderRefundVo.getPassengerName());
        refundOrderFlight.setSequence(applyFlightOrderRefundVo.getSequence());
        refundOrderFlight.setRefund(applyFlightOrderRefundVo.getRefundFee());
        refundOrderFlight.setRefundRate(applyFlightOrderRefundVo.getRefundRate());
        refundOrderFlight.setRefundType(refundType);
        refundOrderFlight.setInv(false);
        refundOrderFlight.setRebookingListID("0");
        refundOrderFlight.setRbkID("0");
        refundOrderFlightList.add(refundOrderFlight);
        refundOrderFlightInfo.setRefundOrderFlight(refundOrderFlightList);
        refundOrderRequest.setRefundOrderFlightInfo(refundOrderFlightInfo);

        //创建header
        request.setRefundOrderRequest(refundOrderRequest);
        request.setHeader(ctripHeaderSupport.build("FlightRefundOrder"));

        return request;
    }


    public FltRefundEndorseFeeRequest toRequest(ResCtripSearchRelationResp searchRelation) {
        Map<String, CtripRuleInfo> ctripRuleInfoMap = toRuleMap(searchRelation.getRules());

        FltRefundEndorseFeeRequest request = new FltRefundEndorseFeeRequest();
        // header 节点
        String requestType = "FltRefundEndorseFee";
        RequestHeader header = new RequestHeader(requestType);
        request.setHeader(header);

        // FlightSearchRequest 节点
        FltRefundEndorseFeeRequestType fltRefundEndorseFeeRequest = new FltRefundEndorseFeeRequestType();
        RefundEndorseFeeListType refundEndorseFeeListType = new RefundEndorseFeeListType();
        List<RefundEndorseFeeEntityType> refundEndorseFeeEntityList = new ArrayList<RefundEndorseFeeEntityType>();
        refundEndorseFeeListType
                .setRefundEndorseFeeEntity(refundEndorseFeeEntityList);
        fltRefundEndorseFeeRequest
                .setRefundEndorseFeeList(refundEndorseFeeListType);
        List<FlightIndivSeatPrice> prices = searchRelation.getSeatPrices();
        String fdSubclass = null;
        int airlineShowPrice = 0;
        String saleType = null;
        String isResetPolicy = null;
        String extensionFlags = null;
        String extensionFlags2 = null;
        if (CollectionUtils.isNotEmpty(prices)) {
            for (FlightIndivSeatPrice price : prices) {
                if (price.getPersonType() == BizzConstants.PERSON_TYPE_ADULT) {
                    fdSubclass = price.getFdSubclass();
                    airlineShowPrice = price.getAirlineShowPrice() == null ? 0 : price.getAirlineShowPrice();
                    saleType = price.getSaleType();
                    isResetPolicy = price.getIsResetPolicy();
                    extensionFlags = price.getExtensionFlags();
                    extensionFlags2 = price.getExtensionFlags2();
                }
            }
        }
        for (com.tuniu.abt.intf.tsp.dto.res.FlightIndivPrice flightIndivPrice : searchRelation.getPrices()) {
            RefundEndorseFeeEntityType refundEndorseFeeEntity = new RefundEndorseFeeEntityType();
            if ("ADT".equals(flightIndivPrice.getPassengerCode())) {
                // 携程成人缩写是ADU
                CtripRuleInfo adtRefundRuleInfo = ctripRuleInfoMap
                        .get(flightIndivPrice.getPassengerCode() + "-"
                                + "Refund");
                CtripRuleInfo adtModificationRuleInfo = ctripRuleInfoMap
                        .get(flightIndivPrice.getPassengerCode() + "-"
                                + "Modification");
                ctripRuleInfoMap.get(flightIndivPrice.getPassengerCode() + "-"
                        + "Endorsement");
                refundEndorseFeeEntity.setPassengerType("ADU");
                refundEndorseFeeEntity.setRid(String.valueOf(adtRefundRuleInfo
                        .getRuleId()));
                refundEndorseFeeEntity.setCid(String
                        .valueOf(adtModificationRuleInfo.getRuleId()));
                refundEndorseFeeEntity.setPrice(String.valueOf(flightIndivPrice
                        .getCost()));
                refundEndorseFeeEntity.setFdPrice(StringUtils.isEmpty(fdSubclass) ? String
                        .valueOf(flightIndivPrice.getFdPrice()):String
                        .valueOf(airlineShowPrice));

                refundEndorseFeeEntity.setStandPrice(searchRelation.getCabinPrice().toString());
                refundEndorseFeeEntity.setPrintPrice(String
                        .valueOf(flightIndivPrice.getPrintPrice()));
                refundEndorseFeeEntity.setProductSourceNum(String
                        .valueOf(searchRelation.getProductSource()));
                if(StringUtils.isNotEmpty(saleType)){
                    refundEndorseFeeEntity.setSaleType(saleType);
                }
                if(StringUtils.isNotEmpty(extensionFlags)){
                    refundEndorseFeeEntity.setExtensionFlags(extensionFlags);
                }
                if(StringUtils.isNotEmpty(extensionFlags2)){
                    refundEndorseFeeEntity.setExtensionFlags2(extensionFlags2);
                }
                refundEndorseFeeEntity.setIsResetPolicy(StringUtils.isEmpty(isResetPolicy) ? "false": isResetPolicy);
                refundEndorseFeeEntity.setAirline(searchRelation.getAirlineCompanyIataCode());
                refundEndorseFeeEntity.setIsResetPolicy("false");
                if ("T".equals(adtRefundRuleInfo.getRuleRestriction())) {
                    refundEndorseFeeEntity.setNonRef("true");
                } else {
                    refundEndorseFeeEntity.setNonRef("false");
                }
                if ("T".equals(adtModificationRuleInfo.getRuleRestriction())) {
                    refundEndorseFeeEntity.setNonRer("true");
                } else {
                    refundEndorseFeeEntity.setNonRer("false");
                }

                refundEndorseFeeEntity.setRcid(String.valueOf(adtRefundRuleInfo
                        .getRuleId()));
                refundEndorseFeeEntity.setOrderId("0");
                refundEndorseFeeEntity.setType("1");
                refundEndorseFeeEntityList.add(refundEndorseFeeEntity);
            }

        }
        request.setFltRefundEndorseFeeRequest(fltRefundEndorseFeeRequest);
        return request;
    }

    /**
     * 携程退改签费用查询请求 add by tangchuandong at 2015-05-07
     *
     * @param param
     * @return
     */
    public FltRefundEndorseFeeRequest getCtripFlightRefundEndorseFeeString(RefundEndorseReqesutVo param) {
        return null;
    }


    public static Map<String, CtripRuleInfo> toRuleMap(List<CtripRuleInfo> ruleInfos) {
        Map<String, CtripRuleInfo> ctripRuleInfoMap = new HashMap<String, CtripRuleInfo>();
        for (CtripRuleInfo ctripRuleInfo : ruleInfos) {
            // 成人 退票
            if (ctripRuleInfo.getPersonType() == 1 && ctripRuleInfo.getRuleType() == 1) {
                ctripRuleInfoMap.put("ADT" + "-" + "Refund", ctripRuleInfo);
            }
            // 成人 改期
            else if (ctripRuleInfo.getPersonType() == 1 && ctripRuleInfo.getRuleType() == 2) {
                ctripRuleInfoMap.put("ADT" + "-" + "Modification", ctripRuleInfo);
            }
            // 成人签转
            else if (ctripRuleInfo.getPersonType() == 1 && ctripRuleInfo.getRuleType() == 3) {
                ctripRuleInfoMap.put("ADT" + "-" + "Endorsement", ctripRuleInfo);
            }
            // 儿童退票
            else if (ctripRuleInfo.getPersonType() == 2 && ctripRuleInfo.getRuleType() == 1) {
                ctripRuleInfoMap.put("CHD" + "-" + "Refund", ctripRuleInfo);
            }
            // 儿童 改期
            else if (ctripRuleInfo.getPersonType() == 2 && ctripRuleInfo.getRuleType() == 2) {
                ctripRuleInfoMap.put("CHD" + "-" + "Modification", ctripRuleInfo);
            }
            // 儿童签转
            else if (ctripRuleInfo.getPersonType() == 2 && ctripRuleInfo.getRuleType() == 3) {
                ctripRuleInfoMap.put("CHD" + "-" + "Endorsement", ctripRuleInfo);
            }
            // 婴儿退票
            else if (ctripRuleInfo.getPersonType() == 3 && ctripRuleInfo.getRuleType() == 1) {
                ctripRuleInfoMap.put("BAB" + "-" + "Refund", ctripRuleInfo);
            }
            // 婴儿 改期
            else if (ctripRuleInfo.getPersonType() == 3 && ctripRuleInfo.getRuleType() == 2) {
                ctripRuleInfoMap.put("BAB" + "-" + "Modification", ctripRuleInfo);
            }
            // 婴儿签转
            else if (ctripRuleInfo.getPersonType() == 3 && ctripRuleInfo.getRuleType() == 3) {
                ctripRuleInfoMap.put("BAB" + "-" + "Endorsement", ctripRuleInfo);
            }
        }
        return ctripRuleInfoMap;
    }


}
