package com.tuniu.abt.service.commons;

import com.tuniu.abt.base.mail.SendEmail;
import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.dto.common.MaliceFlightDto;
import com.tuniu.abt.intf.tsp.TspFltInterface;
import com.tuniu.abt.intf.tsp.dto.flt.BanAddRequest;
import com.tuniu.abt.mapper.AbtPnrFlightMapper;
import com.tuniu.abt.schedule.BanFlightAddTask;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 恶意占座基础服务
 * Created by chengyao on 2016/4/8.
 */
@Service
public class BanFlightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BanFlightAddTask.class);

    @Resource
    private AbtPnrFlightMapper abtPnrFlightMapper;

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private TspFltInterface tspFltInterface;

    @Resource
    private SendEmail sendEmail;

    @Value("${maliceFindBeforeDepartureNum:80}")
    private int maliceFindBeforeDepartureNum = 80;
    @Value("${maliceFindDayRangeNum:30}")
    private int maliceFindDayRangeNum = 30;
    @Value("${maliceFindMinuteRangeNum:15}")
    private int maliceFindMinuteRangeNum = 15;
    @Value("${maliceFindAlertNum:10}")
    private int maliceFindAlertNum = 10;

    /**
     * 找到恶意占座的记录
     * @return
     */
    @CommandTrace(name = TracerCmdEnum.BAN_FLIGHT_ADD, type = "#root.methodName")
    public List<MaliceFlightDto> findAllMaliceFlights() {
        // 一天时间内的恶意占座查询
        Date currDate = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(currDate);//设置时间
        c.add(Calendar.DATE, -1);//设置的时间减去一天
        Date startDate = c.getTime();//就是最后得到的时间
        List<MaliceFlightDto> list = new ArrayList<MaliceFlightDto>();
        List<MaliceFlightDto> list1 = abtPnrFlightMapper.queryMaliceBookingFlights(maliceFindDayRangeNum, startDate, currDate);
        setType(list1, 1);
        if (list1 != null && list1.size() > 0) {
            list.addAll(list1);
        }

        // 15分钟内的恶意占座查询
        c.setTime(currDate);
        c.add(Calendar.MINUTE, -15);//前15分钟
        startDate = c.getTime();
        List<MaliceFlightDto> list2 = abtPnrFlightMapper.queryMaliceBookingFlights(maliceFindMinuteRangeNum, startDate, currDate);
        setType(list2, 2);
        if (list2 != null && list2.size() > 0) {
            if (list.size() > 0) {
                list = removeDuplicates(list, list2);
            } else {
                list.addAll(list2);
            }
        }

        // 起飞前恶意占座查询。80个人次
        List<MaliceFlightDto> list3 = abtPnrFlightMapper.queryMaliceBookingFlightsByDeparture(maliceFindBeforeDepartureNum, currDate);
        setType(list3, 3);
        if (list3 != null && list3.size() > 0) {
            if (list.size() > 0) {
                list = removeDuplicates(list, list3);
            } else {
                list.addAll(list3);
            }
        }
        return list;
    }

    @CommandTrace(name = TracerCmdEnum.BAN_FLIGHT_ADD, type = "#root.methodName")
    public List<MaliceFlightDto> findAlertList() {
        // 获取当前时间
        Date currDate = new Date();
        // 当天起飞的航班超过10个人
        List<MaliceFlightDto> pnrFlightList = abtPnrFlightMapper.queryMaliceBookingFlightsForAlert(maliceFindAlertNum,
                currDate, DateTimeUtils.dateConvert(currDate, "yyyy-MM-dd"));

        List<MaliceFlightDto> sendEmailList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(pnrFlightList)) {
            // 4表示 当前起飞的航班占位人数超过10人
            setType(pnrFlightList, 4);
            sendEmailList.addAll(pnrFlightList);
        }
        // 第二天起飞的航班超过10个人
        Calendar c = new GregorianCalendar();
        c.setTime(currDate);// 设置时间
        c.add(Calendar.DATE, 1);// 设置的时间加一天
        Date tomorrowTime = c.getTime();// 就是最后得到的时间
        List<MaliceFlightDto> tomorrowPnrFlightList = abtPnrFlightMapper.queryMaliceBookingFlightsForAlert(
                maliceFindAlertNum, null, DateTimeUtils.dateConvert(tomorrowTime, "yyyy-MM-dd"));
        if (CollectionUtils.isNotEmpty(tomorrowPnrFlightList)) {
            // 5表示 第二天起飞的航班占位人数超过10人
            setType(tomorrowPnrFlightList, 5);
            sendEmailList.addAll(tomorrowPnrFlightList);
        }
        return sendEmailList;
    }


    @CommandTrace(name = TracerCmdEnum.BAN_FLIGHT_ADD, type = "#root.methodName")
    public List<MaliceFlightDto> findNewMaliceFlights(List<MaliceFlightDto> maliceFlightDtos) {
        // 生成屏蔽航班，请求加入屏蔽列表
        List<MaliceFlightDto> sendEmailList = new ArrayList<>();
        List<BanAddRequest> banAddRequests = new ArrayList<>();
        for (MaliceFlightDto item : maliceFlightDtos) {
            BanAddRequest banAddRequest = new BanAddRequest();
            banAddRequest.setFlightNo(item.getFlightNo());
            banAddRequest.setDepartureDateStart(item.getDepartureDate());
            banAddRequest.setDepartureDateEnd(item.getDepartureDate());
            banAddRequest.setSolutionId(String.valueOf(item.getVendorId()));
            banAddRequest.setSystemId("1/2/3");
            banAddRequest.setOrgAirportCode(item.getOrgAirportCode());
            banAddRequest.setDstAirportCode(item.getDstAirportCode());
            banAddRequest.setStatus(0);
            banAddRequest.setRemark("恶意占座屏蔽");
            banAddRequest.setAddUserId(0);
            banAddRequest.setAddUserName("系统");
            banAddRequest.setUpdateUserId(0);
            banAddRequest.setUpdateUserName("系统");
            banAddRequest.setAirlineCompany(banAddRequest.getFlightNo().substring(0, 2));
            banAddRequest.setBanRuleType(item.getType());
            banAddRequests.add(banAddRequest);
        }

        List<Long> result = tspFltInterface.addBanFlight(banAddRequests);
        if (CollectionUtils.isNotEmpty(result)) {
            for (int i = 0; i < maliceFlightDtos.size(); i++) {
                if (result.get(i) != null && result.get(i) > 0) {
                    sendEmailList.add(maliceFlightDtos.get(i));
                }
            }
        }
        return sendEmailList;
    }

    /**
     * 15分钟扫描一次，发现系统中购买当天起飞或第二天起飞的航班超过10人，报警出来，不屏蔽。
     *
     */
    @CommandTrace(name = TracerCmdEnum.BAN_FLIGHT_ADD, type = "#root.methodName", onlyException = true)
    void sendAlertMail(List<MaliceFlightDto> sendEmailList) {
        if (CollectionUtils.isEmpty(sendEmailList)) {
            return;
        }

        StringBuilder text = new StringBuilder();
        // 发邮件
        for (MaliceFlightDto flightPnrFlight : sendEmailList) {

            Set<String> orders = new HashSet<>();
            String pnr = flightPnrFlight.getOrderId();
            String[] pairs = StringUtils.split(pnr, ",");
            for (String pair : pairs) {
                String[] orderPnr = StringUtils.split(pair, ":");
                orders.add(orderPnr[0]);
            }
            String orderIds = StringUtils.join(orders, "、");

            if (flightPnrFlight.getType() == 4) {
                text.append("【恶意占位预警，不会屏蔽航班】当天起飞航班超过10人").append("航班号：").append(flightPnrFlight.getFlightNo())
                        .append("，").append(flightPnrFlight.getDepartureDate()).append("（")
                        .append(flightPnrFlight.getOrgTime()).append("起飞").append("/")
                        .append(flightPnrFlight.getDstTime()).append("降落").append("）").append("该航班占位和取消共计")
                        .append(flightPnrFlight.getMaliceOccupyNum()).append("订单号：" + orderIds)
                        .append("，请核实！").append("<br/>");
            }
            if (flightPnrFlight.getType() == 5) {
                text.append("【恶意占位预警，不会屏蔽航班】第二天起飞航班超过10人").append("航班号：").append(flightPnrFlight.getFlightNo())
                        .append("，").append(flightPnrFlight.getDepartureDate()).append("（")
                        .append(flightPnrFlight.getOrgTime()).append("起飞").append("/")
                        .append(flightPnrFlight.getDstTime()).append("降落").append("）").append("该航班占位和取消共计")
                        .append(flightPnrFlight.getMaliceOccupyNum()).append("订单号：" + orderIds)
                        .append("，请核实！").append("<br/>");
            }
        }
        //采用公司统一的邮件发送模板 add by baodw at 20160528
        sendEmail.sendEmail("航班屏蔽预警通知", text.toString(), systemConfig.getMaliceOccupyEmailTo(), null);
    }


    // 组装内容，发送邮件
    @CommandTrace(name = TracerCmdEnum.BAN_FLIGHT_ADD, type = "#root.methodName", onlyException = true)
    void sendMail(List<MaliceFlightDto> sendEmailList) {
        if (CollectionUtils.isEmpty(sendEmailList)) {
            return;
        }

        StringBuilder text = new StringBuilder();
        // 发邮件
        for (MaliceFlightDto flightPnrFlight : sendEmailList) {

            Set<String> orders = new HashSet<String>();
            String oms = flightPnrFlight.getOrderId();
            String[] pairs = StringUtils.split(oms, ",");
            for (String pair : pairs) {
                String[] orderPnr = StringUtils.split(pair, ":");
                orders.add(orderPnr[0]);
            }
            String orderIds = StringUtils.join(orders, "、");
            if (flightPnrFlight.getType() == 1) {
                text.append("航班号：").append(flightPnrFlight.getFlightNo()).append("，")
                        .append(flightPnrFlight.getDepartureDate()).append("（")
                        .append(flightPnrFlight.getOrgTime()).append("起飞").append("/")
                        .append(flightPnrFlight.getDstTime()).append("降落").append("）")
                        .append("一天内占位和取消共计").append(flightPnrFlight.getMaliceOccupyNum())
                        .append("人，此航班被屏蔽，").append("订单号：" + orderIds).append("，请核实！")
                        .append("<br/>");
            }
            if (flightPnrFlight.getType() == 2) {
                text.append("航班号：").append(flightPnrFlight.getFlightNo()).append("，")
                        .append(flightPnrFlight.getDepartureDate()).append("（")
                        .append(flightPnrFlight.getOrgTime()).append("起飞").append("/")
                        .append(flightPnrFlight.getDstTime()).append("降落").append("）")
                        .append("15分钟内占位和取消共计").append(flightPnrFlight.getMaliceOccupyNum())
                        .append("人，此航班被屏蔽，").append("订单号：" + orderIds).append("，请核实！")
                        .append("<br/>");
            }
            if (flightPnrFlight.getType() == 3) {
                text.append("航班号：").append(flightPnrFlight.getFlightNo()).append("，")
                        .append(flightPnrFlight.getDepartureDate()).append("（")
                        .append(flightPnrFlight.getOrgTime()).append("起飞").append("/")
                        .append(flightPnrFlight.getDstTime()).append("降落").append("）").append("占位和取消共计")
                        .append(flightPnrFlight.getMaliceOccupyNum()).append("人，此航班被屏蔽，")
                        .append("订单号：" + orderIds).append("，请核实！").append("<br/>");
            }
        }
        //采用公司统一的邮件发送模板 add by baodw at 20160528
        sendEmail.sendEmail("航班屏蔽通知", text.toString(), systemConfig.getMaliceOccupyEmailTo(), null);
    }


    // 移除list和flist的重复项
    private List<MaliceFlightDto> removeDuplicates(List<MaliceFlightDto> list, List<MaliceFlightDto> flist) {
        List<MaliceFlightDto> tmpList = new ArrayList<MaliceFlightDto>();
        tmpList.addAll(list);
        for (MaliceFlightDto item : list) {
            for (MaliceFlightDto fitem : flist) {
                if (!fitem.getDepartureDate().equals(item.getDepartureDate()) && !fitem
                        .getFlightNo().equals(item.getFlightNo())) {
                    tmpList.add(fitem);
                }
            }
        }
        return tmpList;
    }

    // setType
    private void setType(List<MaliceFlightDto> list, int type) {
        if (null != list && list.size() > 0) {
            for (MaliceFlightDto maliceFlightDto : list) {
                maliceFlightDto.setType(type);
            }
        }
    }

}
