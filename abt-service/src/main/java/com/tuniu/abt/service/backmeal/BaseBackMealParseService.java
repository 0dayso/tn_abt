package com.tuniu.abt.service.backmeal;

import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.constants.BackMealEx;
import com.tuniu.abt.intf.dto.backmeal.RespRuleDetail;
import com.tuniu.abt.intf.dto.backmeal.RespRuleInfo;
import com.tuniu.abt.intf.dto.backmeal.RespRuleInfoItem;
import com.tuniu.abt.intf.entity.AbtBackMealRule;
import com.tuniu.abt.intf.exception.BizzException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 退改签规则解析服务
 *
 * Created by chengyao on 2015/12/7.
 */
@Service
public class BaseBackMealParseService {

    // 单条规则解析
    @CommandTrace(name = TracerCmdEnum.BACK_MEAL_PARSE, onlyException = true)
    public List<RespRuleInfo> parseBackMealShowRoll(AbtBackMealRule abtBackMealRule, BigDecimal basePrice, BigDecimal costPrice) {

        List<RespRuleInfo> results = new ArrayList<RespRuleInfo>();

        RespRuleInfo refundInfo = new RespRuleInfo();
        RespRuleInfo sameInfo = new RespRuleInfo();

        refundInfo.setRuleFlag(RespRuleInfo.FLAG_REFUND);
        refundInfo.setBasePrice(basePrice);
        refundInfo.setRuleString(abtBackMealRule.getReRule());
        refundInfo.setCalculateType(abtBackMealRule.getReCalculateType());
        refundInfo.setRuleRemark(abtBackMealRule.getReRemark());
        refundInfo.setCostPrice(costPrice);

        sameInfo.setRuleFlag(RespRuleInfo.FLAG_SAME);
        sameInfo.setBasePrice(basePrice);
        sameInfo.setRuleString(abtBackMealRule.getSameRule());
        sameInfo.setCalculateType(abtBackMealRule.getSameCalculateType());
        sameInfo.setRuleRemark(abtBackMealRule.getSameRemark());
        sameInfo.setCostPrice(costPrice);

        try {
            refundInfo.setRuleItems(parseRoll(basePrice, refundInfo.getRuleString()));
            sameInfo.setRuleItems(parseRoll(basePrice, sameInfo.getRuleString()));
        } catch (ParseException e) {
            throw new BizzException(BackMealEx.RULE_PARSE_ERROR, "解析规则文本错误，解析数字失败", e);
        } catch (Exception e) {
            throw new BizzException(BackMealEx.RULE_PARSE_ERROR, "解析规则文本错误", e);
        }

        fillTextContent(refundInfo);
        fillTextContent(sameInfo);

        results.add(refundInfo);
        results.add(sameInfo);

        return results;
    }

    // 按字符串解析规则
    private List<RespRuleDetail> parseRoll(BigDecimal price, String rollString) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();

        // 起飞前和起飞后后规则数组
        String[] beforeAfterArr = StringUtils.split(rollString, ',');
        String afterRollString = beforeAfterArr[1];
        String beforeRollString = beforeAfterArr[0];

        // 起飞前规则解析
        String[] beforeRollArray = StringUtils.split(beforeRollString, '/');
        Integer start = null;
        List<RespRuleDetail> items = new ArrayList<RespRuleDetail>();
        for (int i = 0; i < beforeRollArray.length; i++) {
            String rateString = beforeRollArray[i];
            boolean allow = !"100%".equals(rateString);
            Number rate = numberFormat.parse(rateString);

            i++;
            Integer end;
            if (i >= beforeRollArray.length) {
                end = null;
            } else {
                end = Integer.valueOf(beforeRollArray[i]);
            }

            RespRuleDetail item = new RespRuleDetail();

            item.setRate(toBigDecimal(rate));
            item.setStart(start);
            item.setEnd(end);
            item.setAmount(countFee(price, item.getRate()));
            item.setAllow(allow);
            start = end;
            items.add(item);
        }

        // 起飞后规则解析
        boolean allow = !"100%".equals(afterRollString);
        Number rate = numberFormat.parse(afterRollString);
        RespRuleDetail item = new RespRuleDetail();
        item.setRate(toBigDecimal(rate));
        item.setAmount(countFee(price, item.getRate()));
        item.setAllow(allow);
        items.add(item);

        return items;
    }



    // 算实际扣费,四舍五入
    private BigDecimal countFee(BigDecimal price, BigDecimal rate) {
        if (price != null && rate != null && rate.floatValue() >= 0f) {
            return price.multiply(rate).setScale(0, BigDecimal.ROUND_HALF_UP);
        }
        return null;
    }

    private BigDecimal toBigDecimal(Number value) {
        return value == null ? null : new BigDecimal(value.toString());
    }


    public void fillTextContent(RespRuleInfo respRuleInfo) {
        // 最高退票费限定
        int max = Integer.MAX_VALUE;
        if (respRuleInfo.getCostPrice() != null) {
            int tmp = respRuleInfo.getCostPrice().setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            if (tmp > 0) {
                max = tmp;
            }
        }

        if (respRuleInfo.getRuleFlag() == RespRuleInfo.FLAG_REFUND) {
            respRuleInfo.setRuleName("退票手续费");
        } else if (respRuleInfo.getRuleFlag() == RespRuleInfo.FLAG_SAME) {
            respRuleInfo.setRuleName("同舱改期手续费");
        }

        respRuleInfo.setRuleFeeList(new ArrayList<RespRuleInfoItem>());
        List<RespRuleInfoItem> items = respRuleInfo.getRuleFeeList();

        List<RespRuleDetail> ruleItems = respRuleInfo.getRuleItems();
        for (int i = 0; i < ruleItems.size(); i++) {
            boolean isAfter = (ruleItems.size() > 1 && i == ruleItems.size() - 1);
            RespRuleDetail detail = ruleItems.get(i);

            RespRuleInfoItem item = new RespRuleInfoItem();
            item.setName(makeName(detail.getStart(), detail.getEnd(), isAfter));
            item.setValue(makeValue(detail.getAmount(), detail.getRate(), detail.isAllow(), max));
            items.add(item);
        }
    }


    private String makeName(Integer start, Integer end, boolean isAfter) {
        if (isAfter) {
            return "起飞后";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("起飞前");
        if (start == null && end == null) {
            // DO NOTHING;
        } else if (start == null) {
            sb.append(getRuleDistrict(end)).append("内");
        } else if (end == null) {
            sb.append(getRuleDistrict(start)).append("外");
        } else {
            sb.append(getRuleDistrict(start)).append("至").append(getRuleDistrict(end));
        }
        return sb.toString();
    }

    private String getRuleDistrict(int hour) {
        if (hour > 24) {
            return hour / 24 + "天";
        } else {
            return hour + "小时";
        }
    }

    private String makeValue(BigDecimal amount, BigDecimal rate, boolean allow, int max) {
        if (!allow) {
            return "不允许";
        }

        float rateFloat = rate.floatValue();
        int v = amount.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        if (rateFloat == 0.0) {
            return "免费";
        } else {
            return ((v > max) ? max : v) + "元/人";
        }
    }
}
