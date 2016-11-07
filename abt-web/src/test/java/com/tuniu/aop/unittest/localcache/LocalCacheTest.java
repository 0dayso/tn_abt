package com.tuniu.aop.unittest.localcache;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.backmeal.BackMealRedisItem;
import com.tuniu.abt.intf.dto.backmeal.ReqBackMeal;
import com.tuniu.abt.intf.dto.backmeal.RespRuleRoot;
import com.tuniu.abt.intf.tsp.dto.res.ResAirport;
import com.tuniu.abt.service.backmeal.StandardBackMealService;
import com.tuniu.abt.service.cachework.BackMealCacheWorker;
import com.tuniu.abt.service.res.ResourceBaseCacheService;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by chengyao on 2016/1/29.
 */
public class LocalCacheTest extends BaseTest {

    @Resource
    private ResourceBaseCacheService resourceBaseCacheService;

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private BackMealCacheWorker backMealCacheWorker;
    @Resource
    private StandardBackMealService standardBackMealService;

    @Test
    public void s() {
        System.out.println(systemConfig.isLocal());
    }

    @Test
    public void s1() {
        List<BackMealRedisItem> items = backMealCacheWorker.find("CZ");
        System.out.println(JSON.toJSONString(items));
    }

    @Test
    public void s2() {
        ReqBackMeal reqBackMeal = new ReqBackMeal();
        reqBackMeal.setFlightNo("MF2011");
        reqBackMeal.setCabin("H");
        reqBackMeal.setDepartureDate("2016-01-02");
        reqBackMeal.setOrgCityCode("SHA");
        reqBackMeal.setDstCityCode("PEK");
        reqBackMeal.setVendorId(BizzConstants.V_TS);
        reqBackMeal.setCostPrice(1291f);
        reqBackMeal.setFullPrice(1500f);

        RespRuleRoot root = standardBackMealService.queryBackMealRule(reqBackMeal);
        System.out.println(JSON.toJSONString(root));
    }

    @Test
    public void st() throws InterruptedException, IOException {
        ResAirport airport = resourceBaseCacheService.getAirport("1");

        Thread.sleep(4000l);

        ResAirport airport2 = resourceBaseCacheService.getAirport("1");


        System.in.read();

    }
}
