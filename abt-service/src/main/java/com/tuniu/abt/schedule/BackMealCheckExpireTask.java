package com.tuniu.abt.schedule;

import com.tuniu.abt.service.backmeal.BackMealCheckExpireService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 退改签规则过期邮件提醒
 * Created by chengyao on 2016/5/4.
 */
@Component
public class BackMealCheckExpireTask {

    @Resource
    public BackMealCheckExpireService backMealCheckExpireService;

    public void process() {
        backMealCheckExpireService.check();
    }

}
