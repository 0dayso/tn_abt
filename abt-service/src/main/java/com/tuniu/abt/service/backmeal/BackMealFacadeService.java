package com.tuniu.abt.service.backmeal;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.backmeal.ReqBackMeal;
import com.tuniu.abt.intf.dto.backmeal.RespBackMealPrice;
import com.tuniu.abt.intf.dto.backmeal.RespRuleRoot;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退改签查询服务
 *
 * Created by chengyao on 2016/3/26.
 */
@Service
public class BackMealFacadeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackMealFacadeService.class);

    @Resource
    private StandardBackMealService standardBackMealService;
    @Resource
    private CtripBackMealService ctripBackMealService;
    @Resource
    private SystemConfig systemConfig;

    /**
     * 获取退改规则价格
     * @param request 请求
     * @return 价格对象
     */
    public RespBackMealPrice calculatePrice(ReqBackMeal request) {
        RespBackMealPrice resp = new RespBackMealPrice();
        try {
            if (request.getVendorId() == BizzConstants.V_CTRIP) {
                resp = ctripBackMealService.calculateRefundPrice(request);
            } else {
                resp = standardBackMealService.calculateRefundPrice(request);
            }
        } catch (BizzException ex) {
            LOGGER.debug("未匹配上规则。 {}", ex.getMessage());
            resp.setRefundAllow(false);
            resp.setChangeAllow(false);
            // resp.setRemark(ex.getMessage());
        }
        // 如果是度假的，并且是不允许退票，则返回100%退票费, 有效期1年
        if (!resp.isRefundAllow() && null != request.getSystemId() && request.getSystemId() != 0
                && systemConfig.getVacationSystemIds().contains(String.valueOf(request.getSystemId()))) {
            resp.setRefundAmount(new BigDecimal(request.getCostPrice()));
            resp.setRefundExpires(DateUtils.addYears(new Date(), 1));
        }
        return resp;
    }

    /**
     * 获取退改规则文本及结构化数据
     * @param request 请求
     * @return back meal root对象
     */
    public RespRuleRoot queryRule(ReqBackMeal request) {
        if (request.getVendorId() == BizzConstants.V_CTRIP) {
            return ctripBackMealService.queryBackMealRule(request);
        } else {
            return standardBackMealService.queryBackMealRule(request);
        }
    }

}
