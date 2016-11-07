package com.tuniu.abt.schedule;

import com.tuniu.abt.service.commons.BanFlightFacadeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 恶意占座统计，及航班屏蔽
 *
 * Created by chengyao on 2016/3/30.
 */
@Component
public class BanFlightAddTask {

    @Resource
    private BanFlightFacadeService banFlightFacadeService;

    public void process() {
        banFlightFacadeService.process();
    }

}
