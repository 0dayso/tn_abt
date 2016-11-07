package com.tuniu.abt.service.backmeal;

import com.tuniu.abt.base.mail.SendEmail;
import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.entity.AbtBackMeal;
import com.tuniu.abt.intf.entity.AbtBackMealAlertRecord;
import com.tuniu.abt.mapper.AbtBackMealAlertRecordMapper;
import com.tuniu.abt.mapper.AbtBackMealMapper;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.operation.platform.base.text.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 退改签规则过期邮件提醒
 * Created by chengyao on 2016/5/4.
 */
@Service
public class BackMealCheckExpireService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackMealCheckExpireService.class);

    @Resource
    private AbtBackMealMapper abtBackMealMapper;
    @Resource
    private AbtBackMealAlertRecordMapper abtBackMealAlertRecordMapper;

    @Resource
    SendEmail sendEmail;
    @Resource
    SystemConfig systemConfig;

    @CommandTrace(name = TracerCmdEnum.BACK_MEAL_EXPIRE_CHECK)
    public void check() {
        Calendar calendar = Calendar.getInstance();
        // 是不凌晨执行的话，用当前日期+1。
        if (calendar.get(Calendar.HOUR_OF_DAY) > 7) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        String findDateString = FastDateFormat.getInstance("yyyy-MM-dd").format(calendar);

        List<AbtBackMeal> records = abtBackMealMapper.findAlertRecords(findDateString);

        // 待save的ids
        List<Long> ids = CommonUtils.transformList(records, AbtBackMeal::getId);
        // 保存已发送的告警记录
        saveSendRecord(ids);

        Iterator<AbtBackMeal> iterator = records.iterator();
        while (iterator.hasNext()) {
            AbtBackMeal record = iterator.next();
            // 过滤不符合条件的记录
            if (!needAlert(findDateString, record)) iterator.remove();
        }

        if (CollectionUtils.isEmpty(records)) {
            LOGGER.info("没有即将过期的退改签规则，定时任务结束.");
            return;
        }

        sendAlertEmail(records);
    }

    private void sendAlertEmail(List<AbtBackMeal> records) {
        StringBuilder content = new StringBuilder();
        content.append("<p>以下退改规则已过期，请注意及时维护新的退改规则</p>");
        for (AbtBackMeal record : records) {
            content.append("编号：").append(record.getId()).append("，");
            content.append("航司：").append(record.getAirlineCompanyName())
                    .append("(").append(record.getAirlineCompany()).append(")，");
            content.append("舱位：").append(StringUtils.replace(record.getCabin(), "/", " "));
            content.append("<br/>");
        }
        sendEmail.sendEmail("退改规则即将失效，请注意及时维护新的退改规则", content.toString(),
                systemConfig.getBackMealExpireEmailTo(), systemConfig.getBackMealExpireEmailCc());
    }


    // 校验2个日期都有值，且大的那个为搜索条件的，不需要告警，因为不符合过滤条件
    private boolean needAlert(String findDateString, AbtBackMeal record) {
        String date1 = record.getDepartureDateEnd();
        String date2 = record.getTicketDateEnd();

        if (StringUtils.isNotEmpty(date1) && StringUtils.isNotEmpty(date2)) {
            if (!date1.equals(date2)) {
                if (max(date1, date2).equals(findDateString)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 判断是否已发送
//    private boolean hasSend(AbtBackMeal record) {
//        Example example = new Example(AbtBackMealAlertRecord.class);
//        example.createCriteria().andEqualTo("backMealId", record.getId());
//        List<AbtBackMealAlertRecord> list = abtBackMealAlertRecordMapper.selectByExample(example);
//        return CollectionUtils.isNotEmpty(list);
//    }

    private void saveSendRecord(List<Long> ids) {
        List<AbtBackMealAlertRecord> inserts = new ArrayList<AbtBackMealAlertRecord>();
        Date now = new Date();
        for (Long id : ids) {
            AbtBackMealAlertRecord abtBackMealAlertRecord = new AbtBackMealAlertRecord();
            abtBackMealAlertRecord.setAlertType(0); // 默认的1天前警告
            abtBackMealAlertRecord.setBackMealId(id);
            abtBackMealAlertRecord.setAddTime(now);
            inserts.add(abtBackMealAlertRecord);
        }
        if (CollectionUtils.isNotEmpty(inserts)) {
            abtBackMealAlertRecordMapper.insertList(inserts);
        }
    }


    private static String max(String date1, String date2) {
        return date1.compareTo(date2) > 0 ? date1 : date2;
    }

}
