package com.tuniu.aop.unittest.vendor;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.service.ctrip.module.CtripOrderModule;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/2/5.
 */
public class Test1 extends BaseTest {

    @Resource
    private SystemConfig systemConfig;
    @Resource
    private CtripOrderModule ctripOrderModule;

    @Test
    public void s2() {
        List<String> ids = new ArrayList<String>();
        ids.add("25826650");
        Object o = ctripOrderModule.viewOrderListDetails(ids);
        System.out.println(JSON.toJSONString(o));

    }
    @Test
    public void s() throws InterruptedException {
        String envName = systemConfig.getEnvName();

        while (true) {
            System.out.println(systemConfig.getEnvName());

            Thread.sleep(1000l);
        }

    }
}
