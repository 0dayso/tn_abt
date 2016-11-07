package com.tuniu.aop.unittest.dbtest;

import com.tuniu.abt.dao.AbtTicketMainDao;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/4/20.
 */
public class TestDbWriteTest extends BaseTest {

    @Resource
    private AbtTicketMainDao abtTicketMainDao;

    @Test
    public void s() {
        abtTicketMainDao.updateTicketNo(1L, "3", "4", "5", "6", 9);
    }
}
