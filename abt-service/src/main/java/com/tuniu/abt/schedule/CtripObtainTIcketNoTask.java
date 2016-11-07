package com.tuniu.abt.schedule;

import com.tuniu.abt.service.issue.task.CtripObtainTicketNoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 获取携程票号 每5分钟执行一次
 * Created by huangsizhou on 16/3/25.
 */
@Service
public class CtripObtainTIcketNoTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripObtainTIcketNoTask.class);

    @Resource
    private CtripObtainTicketNoService ctripObtainTicketNoService;

    public void execute() {
        ctripObtainTicketNoService.obtainTicketNo();
    }


}