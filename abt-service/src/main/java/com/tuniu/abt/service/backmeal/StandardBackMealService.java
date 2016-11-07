package com.tuniu.abt.service.backmeal;

import com.tuniu.abt.intf.constants.BackMealEx;
import com.tuniu.abt.intf.dto.backmeal.*;
import com.tuniu.abt.intf.entity.AbtBackMeal;
import com.tuniu.abt.intf.entity.AbtBackMealRule;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.TspResInterface;
import com.tuniu.abt.intf.tsp.dto.res.ResFlightCabinfareReq;
import com.tuniu.abt.service.cachework.BackMealCacheWorker;
import com.tuniu.abt.service.cachework.BackMealRuleCacheWorker;
import com.tuniu.abt.service.cachework.FdPriceCacheWorker;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.adapter.flightTicket.domain.FlightIndivCabinFare;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 平台自维护标准退改签规则 服务
 * Created by chengyao on 2016/3/31.
 */
@Service
public class StandardBackMealService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandardBackMealService.class);

    @Resource
    private BackMealCacheWorker backMealCacheWorker;
    @Resource
    private BackMealRuleCacheWorker backMealRuleCacheWorker;
    @Resource
    private BaseBackMealParseService baseBackMealParseService;
    @Resource
    private FdPriceCacheWorker fdPriceCacheWorker;
    @Resource
    private TspResInterface tspResInterface;

    /**
     * 计算退票费
     * @param request request
     * @return 退票费用/改签费用
     */
    public RespBackMealPrice calculateRefundPrice(ReqBackMeal request) {
        String airlineCompany = StringUtils.substring(request.getFlightNo(), 0, 2);
        List<BackMealRedisItem> redisItems = backMealCacheWorker.find(airlineCompany);
        if (CollectionUtils.isEmpty(redisItems)) {
            throw new BizzException(BackMealEx.NO_REDIS_ITEMS);
        }

        BackMealRedisItem matchItem = findRedisItem(request, redisItems, request.getPassengerType());
        if (matchItem == null) {
            throw new BizzException(BackMealEx.NO_MATCH_BY_PASSENGER_TYPE);
        }

        // 折扣判断
        float costPrice = request.getCostPrice() == null ? 0f : request.getCostPrice();
        float fullPrice = request.getFullPrice() == null ? findFullPrice(request).floatValue() : request.getFullPrice();
        request.setFullPrice(fullPrice);

        BigDecimal dividend = new BigDecimal(costPrice);
        BigDecimal divisor = new BigDecimal(fullPrice);
        float discount = dividend.divide(divisor, 3, BigDecimal.ROUND_HALF_UP).floatValue();
        if (discount <= 0.355 && !"HU".equals(request.getFlightNo().substring(0, 2))) {
            throw new BizzException(BackMealEx.DISCOUNT_MATCH);
        }

        List<RespRuleInfo> rules = getRuleInfos(request, matchItem);
        if (rules == null) {
            throw new BizzException(BackMealEx.NO_RULES_FIND);
        }

        RespBackMealPrice respBackMealPrice = new RespBackMealPrice();
        try {
            if (isAllow(rules, RespRuleInfo.FLAG_REFUND)) {
                BackMealPriceWrap fee = findFee(request, rules, RespRuleInfo.FLAG_REFUND);
                respBackMealPrice.setRefundAllow(true);
                respBackMealPrice.setRefundAmount(fee.getPrice());
                respBackMealPrice.setRefundExpires(fee.getDate());
            } else {
                respBackMealPrice.setRefundAllow(false);
            }
        } catch (BizzException ex) {
            LOGGER.debug("未匹配上规则。 {}", ex.getMessage());
            respBackMealPrice.setRefundAllow(false);
        }

        try {
            if (isAllow(rules, RespRuleInfo.FLAG_SAME)) {
                BackMealPriceWrap fee = findFee(request, rules, RespRuleInfo.FLAG_SAME);
                respBackMealPrice.setChangeAllow(true);
                respBackMealPrice.setChangeAmount(fee.getPrice());
                respBackMealPrice.setChangeExpires(fee.getDate());
            } else {
                respBackMealPrice.setChangeAllow(false);
            }
        } catch (BizzException ex) {
            LOGGER.debug("未匹配上规则。 {}", ex.getMessage());
            respBackMealPrice.setChangeAllow(false);
        }
        return respBackMealPrice;
    }

    private boolean isAllow(List<RespRuleInfo> rules, int ruleFlag) {
        boolean bingo = false;
        for (RespRuleInfo rule : rules) {
            if (ruleFlag == rule.getRuleFlag()) {
                bingo = true;
                if (StringUtils.isNotBlank(rule.getRuleRemark())) {
                    return false;
                }
            }
        }
        return bingo;
    }

    // 找到退票、改签费
    private BackMealPriceWrap findFee(ReqBackMeal request, List<RespRuleInfo> rules, int ruleFlag) {
        // 出发前时间
        String departureTimeString = request.getDepartureDate() + " " + request.getDepartureTime();
        Date departureTime = DateTimeUtils.parseDateTime(departureTimeString);
        if (departureTime == null) {
            throw new BizzException(BackMealEx.PARSE_DATETIME_ERROR);
        }
        int beforeHour = beforeDeparture(new Date(), departureTime);
        List<RespRuleDetail> ruleDetails = getRefundDetails(rules, ruleFlag);
        RespRuleDetail ruleDetail = findMatchDetail(ruleDetails, beforeHour);
        if (ruleDetail == null) {
            throw new BizzException(BackMealEx.DEPARTURE_TIME_NOT_MATCH_RULE);
        }
        BackMealPriceWrap result = new BackMealPriceWrap();
        BigDecimal fee = ruleDetail.getAmount();
        if (request.getCostPrice() != null && fee.floatValue() > request.getCostPrice()) {
            fee = new BigDecimal(request.getCostPrice());
        }
        result.setPrice(fee);
        if (beforeHour < 0) { // 起飞后,退票有效期为1年
            result.setDate(DateUtils.addYears(departureTime, 1));
        } else if (null == ruleDetail.getStart()) {
            result.setDate(departureTime);
        } else {
            result.setDate(DateUtils.addHours(departureTime, -ruleDetail.getStart()));
        }
        return result;
    }



    // 根据起飞时间，规则项目中找到符合的记录
    private RespRuleDetail findMatchDetail(List<RespRuleDetail> ruleDetails, int beforeHour) {
        for (int i = 0; i < ruleDetails.size(); i++) {
            RespRuleDetail ruleDetail = ruleDetails.get(i);
            Integer start = ruleDetail.getStart();
            Integer end = ruleDetail.getEnd();

            if (!ruleDetail.isAllow()) {
                continue;
            }

            if (beforeHour < 0) { // 起飞后
                boolean isAfter = (ruleDetails.size() > 1 && i == ruleDetails.size() - 1);
                if (isAfter) {
                    return ruleDetail;
                }
            } else {
                if (start == null && end == null) { // 起飞前
                    return ruleDetail;
                } else if (start == null) { // 小时内
                    if (beforeHour < end) {
                        return ruleDetail;
                    }
                } else if (end == null) { // 小时外
                    if (beforeHour >= start) {
                        return ruleDetail;
                    }
                } else { // 中间
                    if (beforeHour >= start && beforeHour < end) {
                        return ruleDetail;
                    }
                }
            }
        }
        return null;
    }

    // 找到退票项目
    private List<RespRuleDetail> getRefundDetails(List<RespRuleInfo> rules, int ruleFlag) {
        for (RespRuleInfo rule : rules) {
            if (rule.getRuleFlag() == ruleFlag) {
                return rule.getRuleItems();
            }
        }
        return null;
    }

    // 计算离出发时间距离多少小时，如果是出发后，返回-1
    private int beforeDeparture(Date calculateTime, Date departureTime) {
        long abs = departureTime.getTime() - calculateTime.getTime();
        if (abs < 0) {
            return -1;
        }
        return (int) (abs / (1000 * 60 * 60));
    }

    /**
     * 规则查询
     * @param request request
     * @return 规则response
     */
    public RespRuleRoot queryBackMealRule(ReqBackMeal request) {
        RespRuleRoot respRuleRoot = new RespRuleRoot();
        // 查询使用当前日期作为出票日期
        request.setTicketDate(FastDateFormat.getInstance("yyyy-MM-dd").format(new Date()));

        // 折扣判断
        float costPrice = request.getCostPrice() == null ? 0f : request.getCostPrice();
        float fullPrice = request.getFullPrice() == null ? findFullPrice(request).floatValue() : request.getFullPrice();
        request.setFullPrice(fullPrice);

        BigDecimal dividend = new BigDecimal(costPrice);
        BigDecimal divisor = new BigDecimal(fullPrice);
        float discount = dividend.divide(divisor, 3, BigDecimal.ROUND_HALF_UP).floatValue();
        if (discount <= 0.355 && !"HU".equals(request.getFlightNo().substring(0, 2))) {
            respRuleRoot.setNoticeMsg("不得更改、签转、退票，非自愿退票不收取手续费。");
            return respRuleRoot;
        }

        String airlineCompany = StringUtils.substring(request.getFlightNo(), 0, 2);
        List<BackMealRedisItem> redisItems = backMealCacheWorker.find(airlineCompany);

        if (CollectionUtils.isEmpty(redisItems)) {
            respRuleRoot.setNoticeMsg("请您拨打4007-999-999人工咨询具体的退改签规则。");
            return respRuleRoot;
        }

        // 成人退改签规则
        BackMealRedisItem adultMatch = findRedisItem(request, redisItems, AbtBackMeal.PASSENGER_TYPE_ADULT);

        if (adultMatch == null) {
            respRuleRoot.setNoticeMsg("请您拨打4007-999-999人工咨询具体的退改签规则。");
            return respRuleRoot;
        }

        respRuleRoot.setChildRuleDesc("请您拨打4007-999-999人工咨询具体的退改签规则。");
        respRuleRoot.setRuleInfos(getRuleInfos(request, adultMatch));

        if (respRuleRoot.getRuleInfos() == null) {
            respRuleRoot.setNoticeMsg("请您拨打4007-999-999人工咨询具体的退改签规则。");
        }

        specialDealWithPn(airlineCompany, respRuleRoot);
        specialDealWithRemark(respRuleRoot);
        return respRuleRoot;
    }

    // 西部航空增加备注
    private void specialDealWithPn(String airlineCompany, RespRuleRoot respRuleRoot) {
        if ("PN".equals(airlineCompany) &&  CollectionUtils.isNotEmpty(respRuleRoot.getRuleInfos())) {
            for (RespRuleInfo ruleInfo : respRuleRoot.getRuleInfos()){
                if (ruleInfo !=null && ruleInfo.getRuleFlag() == RespRuleInfo.FLAG_REFUND) {
                    String limit = "部分低价票，无免费托运行李额。仅可免费携带一件重量不超过5公斤、尺寸不超过40cm*30cm*20cm的非托运行李额。无免费餐饮服务。西部航空航班取消或延误3小时以上，才可全额退款，无任何经济赔偿。";
                    ruleInfo.setRuleRemark(StringUtils.isEmpty(ruleInfo.getRuleRemark())? limit :ruleInfo.getRuleRemark()+limit);
                }
            }
        }
    }

    private void specialDealWithRemark(RespRuleRoot respRuleRoot) {
        if (respRuleRoot.getRuleInfos() == null) return;
        for (RespRuleInfo respRuleInfo : respRuleRoot.getRuleInfos()) {
            if (StringUtils.isNotEmpty(respRuleInfo.getRuleRemark())) {
                respRuleInfo.setRuleFeeList(null);
            }
        }
    }

    private BackMealRedisItem findRedisItem(ReqBackMeal request, List<BackMealRedisItem> items, int passengerType) {
        BackMealRedisItem matchItem = null;
        for (BackMealRedisItem redisItem : items) {
            if (match(request, redisItem)) {
                if (redisItem.getPassengerType() == passengerType) {
                    matchItem = redisItem;
                    break;
                }
            }
        }
        return matchItem;
    }

    // 获取规则详情，解析规则文本
    private List<RespRuleInfo> getRuleInfos(ReqBackMeal request, BackMealRedisItem matchItem) {
        AbtBackMealRule rule = backMealRuleCacheWorker.find(matchItem.getId());
        if (rule == null) {
            LOGGER.warn("未找到规则相关数据，abt_back_meal#id=" + matchItem.getId());
            return null;
        }
        BigDecimal basePrice = findBasePrice(request, rule.getReCalculateType());
        if (basePrice.intValue() == 0) {
            LOGGER.warn("基础价格为0, calculateType=" + rule.getReCalculateType());
            return null;
        }
        try {
            return baseBackMealParseService.parseBackMealShowRoll(rule, basePrice, new BigDecimal(request.getCostPrice().toString()));
        } catch (Exception ex) {
            LOGGER.warn("解析规则失败。" + ex.getMessage(), ex);
            return null;
        }
    }

    private BigDecimal findBasePrice(ReqBackMeal reqBackMeal, int reCalculateType) {
        if (reCalculateType == AbtBackMealRule.CALCULATE_TYPE_FD) {
            return findFdPrice(reqBackMeal);
        } else if (reCalculateType == AbtBackMealRule.CALCULATE_TYPE_COST) {
            return new BigDecimal(reqBackMeal.getCostPrice().toString());
        } else if (reCalculateType == AbtBackMealRule.CALCULATE_TYPE_FULL) {
            return findFullPrice(reqBackMeal);
        } else {
            throw new IllegalArgumentException("换算基础必须为票面价、FD价、舱等全价三种类型中的一种");
        }
    }

    private BigDecimal findFdPrice(ReqBackMeal reqBackMeal) {
        FdPriceParam fdPriceParam = new FdPriceParam();
        fdPriceParam.setDepartureDate(reqBackMeal.getDepartureDate());
        fdPriceParam.setOrgCityIataCode(reqBackMeal.getOrgCityCode());
        fdPriceParam.setDstCityIataCode(reqBackMeal.getDstCityCode());

        String airlineCompany = StringUtils.substring(reqBackMeal.getFlightNo(), 0, 2);
        String cabin = reqBackMeal.getCabin();
        fdPriceParam.setAirlineCompany(airlineCompany);
        fdPriceParam.setCabin(cabin);
        BigDecimal result = fdPriceCacheWorker.find(fdPriceParam);
        return result == null ? new BigDecimal(0) : result;
    }

    private BigDecimal findFullPrice(ReqBackMeal reqBackMeal) {
        Float fullPrice = reqBackMeal.getFullPrice();
        if (fullPrice != null) {
            return new BigDecimal(fullPrice.toString());
        }

        // 查询舱等全价
        ResFlightCabinfareReq req = new ResFlightCabinfareReq();
        req.setFlightNos(new String[] {reqBackMeal.getFlightNo()});
        req.setOrgCityIataCode(reqBackMeal.getOrgCityCode());
        req.setDstCityIataCode(reqBackMeal.getDstCityCode());
        req.setSolutionIds(new int[] { reqBackMeal.getVendorId() });
        req.setSeatCode(reqBackMeal.getCabin());

        List<FlightIndivCabinFare> fares = tspResInterface.findFlightCabinFares(req);
        if (fares != null && fares.size() > 0) {
            return new BigDecimal(fares.get(0).getCabinPrice());
        } else {
            throw new BizzException(BackMealEx.NO_FULL_PRICE);
        }
    }

    private boolean match(ReqBackMeal request, BackMealRedisItem backMealRedisItem) {

        // 舱位匹配
        String cabin = request.getCabin();
        if (!backMealRedisItem.getCabins().contains(cabin)) {
            return false;
        }

        // 航段匹配
        String segment = request.getOrgCityCode() + "-" + request.getDstCityCode();
        Set<String> segmentRules = backMealRedisItem.getCityOptions();
        if (backMealRedisItem.getCityOptionType() == AbtBackMeal.CITY_OPTION_TYPE_INCLUDE) {
            if (!segmentRules.contains(segment)) {
                return false;
            }
        } else if (backMealRedisItem.getCityOptionType() == AbtBackMeal.CITY_OPTION_TYPE_EXCLUDE) {
            if (segmentRules.contains(segment)) {
                return false;
            }
        }

        // 出票时间匹配
        String ticketDate = request.getTicketDate(); // 对于规则查询，出票时间点用当前时间
        if (StringUtils.isNotEmpty(backMealRedisItem.getTicketDateStart())
                && StringUtils.isNotEmpty(backMealRedisItem.getTicketDateEnd())) {
            if (backMealRedisItem.getTicketDateStart().compareTo(ticketDate) > 0
                    || ticketDate.compareTo(backMealRedisItem.getTicketDateEnd()) > 0) {
                return false;
            }
        }

        // 起飞时间匹配
        if (StringUtils.isNotEmpty(backMealRedisItem.getDepartureDateStart())
                && StringUtils.isNotEmpty(backMealRedisItem.getDepartureDateEnd())) {
            String departureDate = request.getDepartureDate();
            if (backMealRedisItem.getDepartureDateStart().compareTo(departureDate) > 0
                    || departureDate.compareTo(backMealRedisItem.getDepartureDateEnd()) > 0) {
                return false;
            }
        }

        return true;
    }
}
