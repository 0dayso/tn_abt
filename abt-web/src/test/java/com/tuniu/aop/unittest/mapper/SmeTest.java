package com.tuniu.aop.unittest.mapper;

import com.tuniu.abt.schedule.BackMealCheckExpireTask;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by chengyao on 2016/5/4.
 */
public class SmeTest extends BaseTest {

    @Resource
    BackMealCheckExpireTask backMealCheckExpireTask;

    @Test
    public void s1() throws IOException {
        backMealCheckExpireTask.process();
        System.in.read();
    }
}
