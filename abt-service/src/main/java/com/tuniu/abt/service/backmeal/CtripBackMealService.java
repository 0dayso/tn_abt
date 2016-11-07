package com.tuniu.abt.service.backmeal;

import com.tuniu.abt.intf.constants.BackMealEx;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.backmeal.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.TspResInterface;
import com.tuniu.abt.intf.tsp.dto.res.FlightIndivPrice;
import com.tuniu.abt.intf.tsp.dto.res.ResCtripSearchRelationReq;
import com.tuniu.abt.intf.tsp.dto.res.ResCtripSearchRelationResp;
import com.tuniu.abt.service.ctrip.module.CtripRefundModule;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.adapter.flightTicket.domain.CtripRuleInfo;
import com.tuniu.adapter.flightTicket.domain.FlightIndivSeatPrice;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundEndorseFee.FltRefundEndorseFeeEntityType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundEndorseFee.FltRefundEndorseFeeResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundEndorseFee.RefundEndorseEntityType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 携程退改规则查询服务
 * Created by chengyao on 2016/4/5.
 */
@Service
public class CtripBackMealService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripBackMealService.class);

    @Resource
    private TspResInterface tspResInterface;

    @Resource
    private CtripRefundModule ctripRefundModule;


    /**
     * 计算退票费
     * @param request request
     * @return 退票费用
     */
    public RespBackMealPrice calculateRefundPrice(ReqBackMeal request) {
        ResCtripSearchRelationReq req = CommonUtils.transform(request, ResCtripSearchRelationReq.class);
        ResCtripSearchRelationResp resp = tspResInterface.getCabinPriceIndivPriceCtripRuleCtripSeatPrice(req);
        /** 校验数据有效性 */
        RespBackMealPrice respBackMealPrice = new RespBackMealPrice();
        try {
            checkResCtripSearchRelationResp(resp);
        } catch (Exception e) {
            LOGGER.error("从资源获取数据缺失：{}", e.getMessage(), e);
            respBackMealPrice.setRefundAllow(false);
            respBackMealPrice.setChangeAllow(false);
            return respBackMealPrice;
        }
        if (request.getCostPrice() != null && request.getCostPrice() > 0) {
            resp.getPrices().get(0).setCost(request.getCostPrice().intValue());
        }
        resp.setAirlineCompanyIataCode(request.getFlightNo().substring(0, 2));
        FltRefundEndorseFeeResponse fltRefundEndorseFeeResponse = ctripRefundModule.refundEndorseFee(resp);

        int subsidy = resp.getSubsidy();
        try {
            BackMealPriceWrap fee = findSingleFee(request, fltRefundEndorseFeeResponse, RespRuleInfo.FLAG_REFUND);
            respBackMealPrice.setRefundAllow(true);
            respBackMealPrice.setRefundAmount(fee.getPrice().add(new BigDecimal(subsidy)));
            respBackMealPrice.setRefundExpires(fee.getDate());
        } catch (BizzException ex) {
            LOGGER.debug("未匹配上规则。 {}", ex.getMessage());
            respBackMealPrice.setRefundAllow(false);
        }

        try {
            BackMealPriceWrap fee = findSingleFee(request, fltRefundEndorseFeeResponse, RespRuleInfo.FLAG_SAME);
            respBackMealPrice.setChangeAllow(true);
            respBackMealPrice.setChangeAmount(fee.getPrice().add(new BigDecimal(subsidy)));
            respBackMealPrice.setChangeExpires(fee.getDate());
        } catch (BizzException ex) {
            LOGGER.debug("未匹配上规则。 {}", ex.getMessage());
            respBackMealPrice.setChangeAllow(false);
        }
        return respBackMealPrice;
    }

    private BackMealPriceWrap findSingleFee(ReqBackMeal request, FltRefundEndorseFeeResponse fltRefundEndorseFeeResponse, int ruleFlag) {
        FltRefundEndorseFeeEntityType fltRefundEndorseFeeEntityType = fltRefundEndorseFeeResponse.getFltRefundEndorseFeeResponse().
                getRefundEndorseFeeList().getFltRefundEndorseFeeEntity().get(0);
        if (fltRefundEndorseFeeEntityType == null) {
            throw new BizzException(BackMealEx.NO_RULES_FIND);
        }

        String departureTimeString = request.getDepartureDate() + " " + request.getDepartureTime();
        Date departureTime = DateTimeUtils.parseDateTime(departureTimeString);
        if (departureTime == null) {
            throw new BizzException(BackMealEx.PARSE_DATETIME_ERROR);
        }
        BackMealPriceWrap value = findRefundPrice(fltRefundEndorseFeeEntityType, departureTime, ruleFlag);
        if (value == null) {
            throw new BizzException(BackMealEx.DEPARTURE_TIME_NOT_MATCH_RULE);
        }
        return value;
    }


    /**
     * 规则查询
     *
     * @param request request
     * @return 规则response
     */
    public RespRuleRoot queryBackMealRule(ReqBackMeal request) {
        ResCtripSearchRelationReq req = CommonUtils.transform(request, ResCtripSearchRelationReq.class);
        ResCtripSearchRelationResp resp = tspResInterface.getCabinPriceIndivPriceCtripRuleCtripSeatPrice(req);
        RespRuleRoot respRuleRoot = new RespRuleRoot();
        /** 校验数据有效性 */
        try {
            checkResCtripSearchRelationResp(resp);
        } catch (Exception e) {
            LOGGER.error("从资源获取数据缺失：{}", e.getMessage(), e);
            respRuleRoot.setNoticeMsg("请您拨打4007-999-999人工咨询具体的退改签规则。");
            return respRuleRoot;
        }
        if (request.getCostPrice() != null && request.getCostPrice() > 0) {
            resp.getPrices().get(0).setCost(request.getCostPrice().intValue());
        }
        resp.setAirlineCompanyIataCode(request.getFlightNo().substring(0, 2));
        FltRefundEndorseFeeResponse fltRefundEndorseFeeResponse = ctripRefundModule.refundEndorseFee(resp);

        int subsidy = resp.getSubsidy();
        // 携程转途牛
        FltRefundEndorseFeeEntityType fltRefundEndorseFeeEntityType = fltRefundEndorseFeeResponse.getFltRefundEndorseFeeResponse().
                getRefundEndorseFeeList().getFltRefundEndorseFeeEntity().get(0);

        if (fltRefundEndorseFeeEntityType == null) {
            respRuleRoot.setNoticeMsg("请您拨打4007-999-999人工咨询具体的退改签规则。");
            return respRuleRoot;
        }

        List<RespRuleInfo> respRuleInfos = new ArrayList<RespRuleInfo>();
        getRespRuleInfo(respRuleInfos, fltRefundEndorseFeeEntityType, resp, subsidy, RespRuleInfo.FLAG_REFUND);
        getRespRuleInfo(respRuleInfos, fltRefundEndorseFeeEntityType, resp, subsidy, RespRuleInfo.FLAG_SAME);
        respRuleRoot.setRuleInfos(respRuleInfos);
        respRuleRoot.setChildRuleDesc("请您拨打4007-999-999人工咨询具体的退改签规则。");
        if (respRuleInfos.size() == 0) {
            respRuleRoot.setNoticeMsg("请您拨打4007-999-999人工咨询具体的退改签规则。");
        }
        return respRuleRoot;
    }

    private List<RefundEndorseEntityType> getRefundFeeList(FltRefundEndorseFeeEntityType fltRefundEndorseFeeEntityType) {
        if (fltRefundEndorseFeeEntityType.getRefundFeeList() == null) {
            return null;
        }
        List<RefundEndorseEntityType> result = fltRefundEndorseFeeEntityType.getRefundFeeList().getRefundEndorseEntity();
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result;
    }

    private List<RefundEndorseEntityType> getEndorseFeeList(FltRefundEndorseFeeEntityType fltRefundEndorseFeeEntityType) {
        if (fltRefundEndorseFeeEntityType.getEndorseFeeList() == null) {
            return null;
        }
        List<RefundEndorseEntityType> result = fltRefundEndorseFeeEntityType.getEndorseFeeList().getRefundEndorseEntity();
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result;
    }

    private BackMealPriceWrap findRefundPrice(FltRefundEndorseFeeEntityType fltRefundEndorseFeeEntityType, Date departureTime, int ruleFlag) {
        List<RefundEndorseEntityType> list;
        if (ruleFlag == RespRuleInfo.FLAG_REFUND) {
            list = getRefundFeeList(fltRefundEndorseFeeEntityType);
        } else {
            list = getEndorseFeeList(fltRefundEndorseFeeEntityType);
        }

        if (list == null) {
            return null;
        }

        List<RefundEndorseEntityType> beforeDepartureEntityTypes = new ArrayList<>();
        List<RefundEndorseEntityType> afterDepartureEntityTypes = new ArrayList<>();
        for (RefundEndorseEntityType refundEndorseEntityType : list) {
            Integer endHour = Integer.valueOf(refundEndorseEntityType.getWindowEndHour());
            if (endHour < 0) {
                afterDepartureEntityTypes.add(refundEndorseEntityType);
            } else {
                beforeDepartureEntityTypes.add(refundEndorseEntityType);
            }
        }
        BackMealPriceWrap result = new BackMealPriceWrap();
        Date curDateTime = new Date();
        if (CollectionUtils.isNotEmpty(beforeDepartureEntityTypes)) {
            sortAndResetEndHour(beforeDepartureEntityTypes);
            for (RefundEndorseEntityType refundEndorseEntityType : beforeDepartureEntityTypes) {
                int beginHour = Integer.valueOf(refundEndorseEntityType.getWindowBeginHour());
                Date beginTime = DateUtils.addHours(departureTime, -beginHour);
                int endHour = Integer.valueOf(refundEndorseEntityType.getWindowEndHour());
                Date endTime = DateUtils.addHours(departureTime, -endHour);
                if (curDateTime.compareTo(endTime) > 0 && curDateTime.compareTo(beginTime) <= 0) {
                    result.setPrice(refundEndorseEntityType.getFeeAmount());
                    result.setDate(beginTime);
                    return result;
                }
            }
        }
        if (CollectionUtils.isNotEmpty(afterDepartureEntityTypes)) {
            sortAndResetEndHour(afterDepartureEntityTypes);
            for (RefundEndorseEntityType refundEndorseEntityType : afterDepartureEntityTypes) {
                int endHour = Integer.valueOf(refundEndorseEntityType.getWindowEndHour());
                Date endTime = DateUtils.addHours(departureTime, -endHour);
                int beginHour = Integer.valueOf(refundEndorseEntityType.getWindowBeginHour());
                Date beginTime = DateUtils.addHours(departureTime, -beginHour);
                if (curDateTime.compareTo(beginTime) > 0 && curDateTime.compareTo(endTime) <= 0) {
                    result.setPrice(refundEndorseEntityType.getFeeAmount());
                    result.setDate(endTime);
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 按时间区间重排序, 并且修正各区间的结束时间点 使得各区间连续
     * @param refundEndorseEntityTypes
     */
    private void sortAndResetEndHour(List<RefundEndorseEntityType> refundEndorseEntityTypes) {
        Collections.sort(refundEndorseEntityTypes,
                (o1, o2) -> Integer.compare(Math.abs(Integer.valueOf(o1.getWindowBeginHour())), Math.abs(Integer.valueOf(o1.getWindowBeginHour()))));
        for (int i = 0; i < refundEndorseEntityTypes.size(); i++) {
            RefundEndorseEntityType  curEntity = refundEndorseEntityTypes.get(i);
            if (i > 0) {
                RefundEndorseEntityType preEntity = refundEndorseEntityTypes.get(i-1);
                if (Math.abs(Integer.valueOf(preEntity.getWindowEndHour())) > Math.abs(Integer.valueOf(curEntity.getWindowBeginHour()))) {
                    preEntity.setWindowEndHour(curEntity.getWindowBeginHour());
                }
            }
        }
    }

    private void getRespRuleInfo(List<RespRuleInfo> rulesInfos, FltRefundEndorseFeeEntityType fltRefundEndorseFeeEntityType,
            ResCtripSearchRelationResp resp, int subsidy, int ruleFlag) {
        // 查询是否不允许退票或改签
        boolean noOp = findQueryNoRefund(resp, ruleFlag);
        if (noOp) {
            RespRuleInfo respRuleInfo = new RespRuleInfo();
            respRuleInfo.setRuleFlag(ruleFlag);
            respRuleInfo.setRuleName(ruleFlag == RespRuleInfo.FLAG_REFUND ? "退票手续费" : "同舱改期手续费");
            respRuleInfo.setRuleRemark(ruleFlag == RespRuleInfo.FLAG_SAME ? "不得退票。" : "不得更改。");
            rulesInfos.add(respRuleInfo);
            return;
        }

        List<RefundEndorseEntityType> list;
        if (ruleFlag == RespRuleInfo.FLAG_REFUND) {
            list = getRefundFeeList(fltRefundEndorseFeeEntityType);
        } else {
            list = getEndorseFeeList(fltRefundEndorseFeeEntityType);
        }

        if (list == null) {
            return;
        }
        List<RespRuleInfoItem> rules = new ArrayList<RespRuleInfoItem>();

        for (RefundEndorseEntityType refundEndorseEntityType : list) {
            RespRuleInfoItem ruleFee = new RespRuleInfoItem();
            Integer beginHour =Integer.valueOf( refundEndorseEntityType.getWindowBeginHour());
            Integer endHour = Integer.valueOf(refundEndorseEntityType.getWindowEndHour()) ;
            float price = refundEndorseEntityType.getFeeAmount().floatValue() ;
            String hourStr = "";

            if (beginHour > 24 && endHour > 0) {
                hourStr = beginHour / 24 + "天前";
            } else if (beginHour != 0 && endHour < 0) {
                hourStr = beginHour + "小时后";
            } else if (beginHour != 0 && endHour > 0) {
                hourStr = beginHour + "小时前";
            }
            if (beginHour > 0) {
                ruleFee.setName("起飞前" + hourStr);
            } else if (beginHour == 0 && endHour > 0) {
                ruleFee.setName("起飞前");
            } else if (beginHour == 0 && endHour < 0) {
                ruleFee.setName("起飞后");
            }
            if (price == 0) {
                ruleFee.setValue("免费");
            } else {
                price = price + subsidy;
                ruleFee.setValue((int) price + "元/人");

            }
            rules.add(ruleFee);
        }
        RespRuleInfo respRuleInfo = new RespRuleInfo();
        respRuleInfo.setRuleFlag(ruleFlag);
        respRuleInfo.setRuleName(ruleFlag == RespRuleInfo.FLAG_REFUND ? "退票手续费" : "同舱改期手续费");
        respRuleInfo.setRuleFeeList(rules);
        rulesInfos.add(respRuleInfo);
    }


    // 在查询包中找到是否不允许退票、改签
    public boolean findQueryNoRefund(ResCtripSearchRelationResp searchRelation, int ruleFlag) {
        Map<String, CtripRuleInfo> ctripRuleInfoMap = CtripRefundModule.toRuleMap(searchRelation.getRules());
        CtripRuleInfo ruleInfo;
        if (ruleFlag == RespRuleInfo.FLAG_REFUND) {
            ruleInfo = ctripRuleInfoMap.get("ADT-Refund");
        } else {
            ruleInfo = ctripRuleInfoMap.get("ADT-Modification");
        }
        if (ruleInfo != null && "T".equals(ruleInfo.getRuleRestriction())) {
            return true;
        }
        return false;
    }

    private void checkResCtripSearchRelationResp(ResCtripSearchRelationResp resp) {
        // 过滤非成人的数据
        if (CollectionUtils.isNotEmpty(resp.getRules())) {
            for (Iterator<CtripRuleInfo> iterator = resp.getRules().iterator(); iterator.hasNext();) {
                CtripRuleInfo rule = iterator.next();
                if (rule.getPersonType() != BizzConstants.PERSON_TYPE_ADULT) {
                    iterator.remove();
                }
            }
        }
        if (CollectionUtils.isEmpty(resp.getRules())) {
            throw new BizzException(BackMealEx.CTRIP_RES_RULE_INFO_NONE, "资源库中获取成人规则id数据为空");
        }
        if (CollectionUtils.isNotEmpty(resp.getPrices())) {
            for (Iterator<FlightIndivPrice> iterator = resp.getPrices().iterator(); iterator.hasNext();) {
                FlightIndivPrice flightIndivPrice = iterator.next();
                if (!BizzConstants.PSG_TYPE_CODE_ADT.equals(flightIndivPrice.getPassengerCode())) {
                    iterator.remove();
                }
            }
        }
        if (CollectionUtils.isEmpty(resp.getPrices())) {
            throw new BizzException(BackMealEx.CTRIP_RES_PRICE_INFO_NONE, "资源库中获取成人价格数据为空");
        }
        if (CollectionUtils.isNotEmpty(resp.getSeatPrices())) {
            for (Iterator<FlightIndivSeatPrice> iterator = resp.getSeatPrices().iterator(); iterator.hasNext();) {
                FlightIndivSeatPrice seatPrice = iterator.next();
                if (BizzConstants.PERSON_TYPE_ADULT != seatPrice.getPersonType()) {
                    iterator.remove();
                }
            }
        }
        if (CollectionUtils.isEmpty(resp.getSeatPrices())) {
            throw new BizzException(BackMealEx.CTRIP_RES_SEAT_PRICE_NONE, "资源库中获取携程价格数据为空");
        }
    }
}
