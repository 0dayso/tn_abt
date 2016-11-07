package com.tuniu.abt.api.backmeal;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.intf.dto.backmeal.CurdBackMeal;
import com.tuniu.abt.intf.dto.backmeal.ReqBackMeal;
import com.tuniu.abt.service.backmeal.BackMealFacadeService;
import com.tuniu.abt.service.backmeal.CurdBackMealService;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * 退改签服务
 *
 * Created by chengyao on 2016/3/26.
 */
@Controller
@RequestMapping("/backMeal")
public class BackMealApi {

    @Resource
    private BackMealFacadeService backMealFacadeService;
    @Resource
    private CurdBackMealService curdBackMealService;

    /**
     * 费用计算查询
     */
    @RequestMapping(value = "/calculatePrice", method = RequestMethod.POST)
    @TSPServiceInfo(name = "ATS.ABT.BackMealApi.calculatePrice", description = "费用计算查询")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.BACK_MEAL_PRICE)
    public Object calculatePrice(@Json ReqBackMeal request) {
        return backMealFacadeService.calculatePrice(request);
    }


    /**
     * 规则查询
     */
    @RequestMapping(value = "/queryRule", method = RequestMethod.POST)
    @TSPServiceInfo(name = "ATS.ABT.BackMealApi.queryRule", description = "规则查询")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.BACK_MEAL_RULE)
    public Object queryRule(@Json ReqBackMeal request) {
        return backMealFacadeService.queryRule(request);
    }



    /**
     * 录入退改签政策信息
     * @param request
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseJson
    public Object add(@Json CurdBackMeal request) {
        return curdBackMealService.create(request);
    }

    /**
     * 修改退改签政策信息
     * @param request po
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseJson
    public Object update(@Json CurdBackMeal request) {
        return curdBackMealService.update(request);
    }

    /**
     * 查询退改签政策信息
     * @param request po
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseJson
    public Object query(@Json CurdBackMeal request) throws UnsupportedEncodingException {
        return curdBackMealService.query(request);
    }

    /**
     * 根据ID查询退改签政策信息
     * @param request po
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    @ResponseJson
    public Object queryById(@Json CurdBackMeal request) throws UnsupportedEncodingException {
        return curdBackMealService.queryById(request.getId());
    }

}
