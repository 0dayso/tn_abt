package com.tuniu.aop.unittest.redis;

import com.tuniu.abt.service.cachework.FdPriceCacheWorker;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by chengyao on 2016/4/14.
 */
public class FdPriceCacheTest extends BaseTest {
    @Resource
    FdPriceCacheWorker fdPriceCacheWorker;

    @Test
    public void s() {

        FdPriceParam fdPriceParam = new FdPriceParam();
        fdPriceParam.setAirlineCompany("MU");
        fdPriceParam.setCabin("Y");
        fdPriceParam.setDepartureDate("2016-05-20");
        fdPriceParam.setOrgCityIataCode("SHA");
        fdPriceParam.setDstCityIataCode("PEK");
        fdPriceParam.setSystemId(7);

        BigDecimal price = fdPriceCacheWorker.find(fdPriceParam);
        System.out.println(price);
    }
}
