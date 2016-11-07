package com.tuniu.aop.unittest.common;

import com.tuniu.abt.mapper.AbtPnrFlightMapper;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chengyao on 2016/3/30.
 */
public class Common1Test extends BaseTest {

    @Resource
    private AbtPnrFlightMapper abtPnrFlightMapper;

    @Test
    public void s1() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -10);

        abtPnrFlightMapper.queryMaliceBookingFlights(0, calendar.getTime(), new Date());
    }
}
