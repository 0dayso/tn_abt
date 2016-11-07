package com.tuniu.aop.unittest.order;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.intf.dto.backmeal.ReqBackMeal;
import com.tuniu.abt.service.backmeal.CtripBackMealService;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by lanlugang on 2016/5/26.
 */
public class BackMealTest extends BaseTest {

    @Resource
    private CtripBackMealService ctripBackMealService;


    @Test
    public void testCtripBackMealService() {

        ReqBackMeal request = new ReqBackMeal();

        request.setFlightNo("CA4541");
        request.setCabin("Y");
        request.setOrgCityCode("CKG");
        request.setDstCityCode("SHA");
        request.setDepartureDate("2016-06-21");
        request.setVendorId(6);
        request.setCostPrice(1518f);
        request.setFullPrice(1550f);

        try {
            Object obj = ctripBackMealService.queryBackMealRule(request);
            System.out.println(JSON.toJSONString(obj));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
