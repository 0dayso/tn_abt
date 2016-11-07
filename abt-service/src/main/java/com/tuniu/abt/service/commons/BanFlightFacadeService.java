package com.tuniu.abt.service.commons;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.intf.dto.common.MaliceFlightDto;
import com.tuniu.abt.schedule.BanFlightAddTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 恶意占座服务
 * Created by chengyao on 2016/4/8.
 */
@Service
public class BanFlightFacadeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BanFlightAddTask.class);

    @Resource
    private BanFlightService banFlightService;

    /**
     * 1、起飞前80单进行屏蔽，即此航班我司一共占位和取消过80个客人，进行屏蔽
     * 2、一天时间内，有30个客人预订记录在取消或者占位状态的，进行屏蔽
     * 3、15分钟内，有15个客人预订记录在取消或者占位状态的，进行屏蔽
     */
    @ActionTrace(action = TracerActionEnum.BAN_FLIGHT_ADD, recordBody = false)
    public void process() {
        try {
            List<MaliceFlightDto> alerts = banFlightService.findAlertList();
            banFlightService.sendAlertMail(alerts);
        } catch (Exception ex) {
            LOGGER.error("统计恶意占位警告信息出错。" + ex.getMessage(), ex);
        }

        List<MaliceFlightDto> maliceFlightDtos = banFlightService.findAllMaliceFlights();
        if (maliceFlightDtos.size() == 0) {
            LOGGER.debug("统计结果无恶意占座。");
            return;
        }
        List<MaliceFlightDto> sendList = banFlightService.findNewMaliceFlights(maliceFlightDtos);
        if (sendList.size() == 0) {
            LOGGER.debug("统计结果无需发送提醒邮件。");
            return;
        }
        banFlightService.sendMail(sendList);
    }
}
