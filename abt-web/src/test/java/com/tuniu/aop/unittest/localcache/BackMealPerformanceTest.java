package com.tuniu.aop.unittest.localcache;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.backmeal.ReqBackMeal;
import com.tuniu.abt.service.backmeal.StandardBackMealService;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by chengyao on 2016/4/1.
 */
public class BackMealPerformanceTest extends BaseTest {


    @Resource
    private StandardBackMealService standardBackMealService;


    private static final AtomicLong count = new AtomicLong(0l);

    @Test
    public void start() throws InterruptedException {

        final ReqBackMeal reqBackMeal = new ReqBackMeal();
        reqBackMeal.setFlightNo("MF2011");
        reqBackMeal.setCabin("H");
        reqBackMeal.setDepartureDate("2016-01-02");
        reqBackMeal.setOrgCityCode("SHA");
        reqBackMeal.setDstCityCode("PEK");
        reqBackMeal.setVendorId(BizzConstants.V_TS);
        reqBackMeal.setCostPrice(1291f);
        reqBackMeal.setFullPrice(1500f);

        final long start = System.currentTimeMillis();

        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.interrupted()) {
                        standardBackMealService.queryBackMealRule(reqBackMeal);
                        long t = count.incrementAndGet();

                        if (t % 1000 == 0) {
                            long duration = System.currentTimeMillis() - start;

                            BigDecimal r = new BigDecimal(t).divide(new BigDecimal(duration), 5, BigDecimal.ROUND_HALF_UP)
                                    .multiply(new BigDecimal(1000));
                            int x = r.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();

                            System.out.println("tps: " + x);
                        }

                    }
                }
            });
            list.add(t);
            t.start();
        }

        for (Thread thread : list) {
            thread.join();
        }

    }


    class Runner extends Thread {

        @Override
        public void run() {
            super.run();
        }
    }


}
