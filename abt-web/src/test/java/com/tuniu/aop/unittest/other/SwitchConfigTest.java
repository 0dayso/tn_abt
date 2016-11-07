package com.tuniu.aop.unittest.other;

import com.tuniu.abt.utils.SwitchConfig;
import com.tuniu.abt.utils.TsConfig;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/21.
 */
public class SwitchConfigTest extends BaseTest {

    @Resource
    private SwitchConfig switchConfig;

    @Resource
    private TsConfig tsConfig;

    @Test
    public void s1() {
        boolean usePlus = tsConfig.usePlus(TsConfig.CANCEL, 71);
        System.out.println(usePlus);
    }
}
