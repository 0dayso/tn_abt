package com.tuniu.abt.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tuniu.vla.base.conf.BaseSystemConfig;

/**
 * spring 配置服务
 *
 * @author chengyao
 */
@Service
public class SystemConfig extends BaseSystemConfig {

    //调用机票政策系统地址 add by baodw at 20151012
    @Value("${rest.airTicketPolicy.url}")
    private String airTicketPolicyUrl;
    @Value("${rest.res.url}")
    private String restResUrl;
    @Value("${rest.airlineConnector.url")
    private String restAirlineConnectorUrl;

    // 退改签过期一天前提醒邮件
    @Value("${backMealExpire.email.to:}")
    private String backMealExpireEmailTo;

    @Value("${backMealExpire.email.cc:}")
    private String backMealExpireEmailCc;

    //航司舱等及行李额配置待维护规则通知的对象集合 add by wangshouxiao at 2015-09-28
    @Value("${seatClassAndLuggage.email.to:}")
    private String seatClassAndLuggageEmailTo;

    @Value("${seatClassAndLuggage.email.cc:}")
    private String seatClassAndLuggageEmailCc;

    @Value("${malice.occupy.email.to:}")
    private String maliceOccupyEmailTo;


    // 航信IBE Plus office号
    @Value("${ibeplus.officeNo:NKG166}")
    private String ibeplusOfficeNo;
    @Value("${ibeplus.pwd:tw79yy}")
    private String ibeplusPwd;
    // 航信占位需求的CTCT号码，不要使用“-”连接区号以及分机号
    @Value("${travelSky.ctct}")
    private String travelSkyCtct;

    // 航信出票printer no
    @Value("${ibeplus.printerNo:111}")
    private String ibeplusPrinterNo;

    // 携程报文相关参数
    @Value("${ctrip.auth.allianceID}")
    private String ctripAuthAllianceID;
    @Value("${ctrip.auth.SID}")
    private String ctripAuthSID;
    @Value("${ctrip.auth.key}")
    private String ctripAuthKey;
    @Value("${ctrip.cardNo}")
    private String ctripCardNo;
    @Value("${ctrip.serviceNo}")
    private String ctripServiceNo;
    @Value("${ctrip.mobilePhoneNo}")
    private String ctripMobilePhoneNo;
    @Value("${ctrip.contactEmail}")
    private String ctripContactEmail;
    // RT等待时间ms
    @Value("${travelsky.rtWaitTime:200}")
    private int rtWaitTime;
    // RT重试次数
    @Value("${tavelsky.rtRetryCount:2}")
    private int rtRetryCount;
    @Value("${travelsky.ticketLimitDay:0}")
    private int ticketLimitDay;
    // 出票时限,预留小时
    @Value("${travelsky.ticektLimitHour:2}")
    private int ticketLimitHour;
    // 出票时限,预留分钟
    @Value("${travelsky.ticketLimitMm:60}")
    private int ticketLimitMm;
    @Value("${travelsky.issueOfficeNo:NKG166}")
    private String issueOfficeNo;

    @Value("${issue.Feedback}")
    private String issueFeedbackUrl;
    /**
     * 机票频道的systemId(多个以','分隔)
     */
    @Value("${flightChannelSystemIds:51,52,53,54,59}")
    private String flightChannelSystemIds;

    /**
     * 度假的systemId(多个以','分隔)
     */
    @Value("${vacationSystemIds:11,21,71}")
    private String vacationSystemIds;

    /**
     * 退票费指定值和实际值的差额阀值，默认为无限大，即不判断
     */
    @Value("${refund.difference.threshold:999999}")
    private float refundDifferenceThreshold;

    /**
     * 取消失败重试定时任务：重试多少分钟之前的数据
     */
    @Value("${cancel.retry.beforeMinutes:30}")
    private int cancelRetryBeforeMinutes;

    public String getBackMealExpireEmailTo() {
        return backMealExpireEmailTo;
    }

    public void setBackMealExpireEmailTo(String backMealExpireEmailTo) {
        this.backMealExpireEmailTo = backMealExpireEmailTo;
    }

    public String getBackMealExpireEmailCc() {
        return backMealExpireEmailCc;
    }

    public void setBackMealExpireEmailCc(String backMealExpireEmailCc) {
        this.backMealExpireEmailCc = backMealExpireEmailCc;
    }

    public String getRestAirlineConnectorUrl() {
        return restAirlineConnectorUrl;
    }

    public void setRestAirlineConnectorUrl(String restAirlineConnectorUrl) {
        this.restAirlineConnectorUrl = restAirlineConnectorUrl;
    }

    public int getTicketLimitDay() {
        return ticketLimitDay;
    }

    public void setTicketLimitDay(int ticketLimitDay) {
        this.ticketLimitDay = ticketLimitDay;
    }

    public int getTicketLimitHour() {
        return ticketLimitHour;
    }

    public void setTicketLimitHour(int ticketLimitHour) {
        this.ticketLimitHour = ticketLimitHour;
    }

    public int getTicketLimitMm() {
        return ticketLimitMm;
    }

    public void setTicketLimitMm(int ticketLimitMm) {
        this.ticketLimitMm = ticketLimitMm;
    }

    public String getIbeplusPrinterNo() {
        return ibeplusPrinterNo;
    }

    public void setIbeplusPrinterNo(String ibeplusPrinterNo) {
        this.ibeplusPrinterNo = ibeplusPrinterNo;
    }

    public String getCtripServiceNo() {
        return ctripServiceNo;
    }

    public void setCtripServiceNo(String ctripServiceNo) {
        this.ctripServiceNo = ctripServiceNo;
    }

    public String getCtripMobilePhoneNo() {
        return ctripMobilePhoneNo;
    }

    public void setCtripMobilePhoneNo(String ctripMobilePhoneNo) {
        this.ctripMobilePhoneNo = ctripMobilePhoneNo;
    }

    public String getCtripContactEmail() {
        return ctripContactEmail;
    }

    public void setCtripContactEmail(String ctripContactEmail) {
        this.ctripContactEmail = ctripContactEmail;
    }

    public String getCtripAuthAllianceID() {
        return ctripAuthAllianceID;
    }

    public void setCtripAuthAllianceID(String ctripAuthAllianceID) {
        this.ctripAuthAllianceID = ctripAuthAllianceID;
    }

    public String getCtripAuthSID() {
        return ctripAuthSID;
    }

    public void setCtripAuthSID(String ctripAuthSID) {
        this.ctripAuthSID = ctripAuthSID;
    }

    public String getCtripAuthKey() {
        return ctripAuthKey;
    }

    public void setCtripAuthKey(String ctripAuthKey) {
        this.ctripAuthKey = ctripAuthKey;
    }

    public String getCtripCardNo() {
        return ctripCardNo;
    }

    public void setCtripCardNo(String ctripCardNo) {
        this.ctripCardNo = ctripCardNo;
    }

    public String getIbeplusOfficeNo() {
        return ibeplusOfficeNo;
    }

    public void setIbeplusOfficeNo(String ibeplusOfficeNo) {
        this.ibeplusOfficeNo = ibeplusOfficeNo;
    }

    public String getIbeplusPwd() {
        return ibeplusPwd;
    }

    public void setIbeplusPwd(String ibeplusPwd) {
        this.ibeplusPwd = ibeplusPwd;
    }

    public String getTravelSkyCtct() {
        return travelSkyCtct;
    }

    public void setTravelSkyCtct(String travelSkyCtct) {
        this.travelSkyCtct = travelSkyCtct;
    }

    public String getSeatClassAndLuggageEmailTo() {
        return seatClassAndLuggageEmailTo;
    }

    public void setSeatClassAndLuggageEmailTo(String seatClassAndLuggageEmailTo) {
        this.seatClassAndLuggageEmailTo = seatClassAndLuggageEmailTo;
    }

    public String getSeatClassAndLuggageEmailCc() {
        return seatClassAndLuggageEmailCc;
    }

    public void setSeatClassAndLuggageEmailCc(String seatClassAndLuggageEmailCc) {
        this.seatClassAndLuggageEmailCc = seatClassAndLuggageEmailCc;
    }

    public String getMaliceOccupyEmailTo() {
        return maliceOccupyEmailTo;
    }

    public void setMaliceOccupyEmailTo(String maliceOccupyEmailTo) {
        this.maliceOccupyEmailTo = maliceOccupyEmailTo;
    }

    public String getAirTicketPolicyUrl() {
        return airTicketPolicyUrl;
    }

    public void setAirTicketPolicyUrl(String airTicketPolicyUrl) {
        this.airTicketPolicyUrl = airTicketPolicyUrl;
    }

    public String getRestResUrl() {
        return restResUrl;
    }

    public void setRestResUrl(String restResUrl) {
        this.restResUrl = restResUrl;
    }

    public int getRtWaitTime() {
        return rtWaitTime;
    }

    public void setRtWaitTime(int rtWaitTime) {
        this.rtWaitTime = rtWaitTime;
    }

    public int getRtRetryCount() {
        return rtRetryCount;
    }

    public void setRtRetryCount(int rtRetryCount) {
        this.rtRetryCount = rtRetryCount;
    }

    public String getIssueOfficeNo() {
        return issueOfficeNo;
    }

    public void setIssueOfficeNo(String issueOfficeNo) {
        this.issueOfficeNo = issueOfficeNo;
    }

    public String getIssueFeedbackUrl() {
        return issueFeedbackUrl;
    }

    public void setIssueFeedbackUrl(String issueFeedbackUrl) {
        this.issueFeedbackUrl = issueFeedbackUrl;
    }

    public String getFlightChannelSystemIds() {
        return flightChannelSystemIds;
    }

    public void setFlightChannelSystemIds(String flightChannelSystemIds) {
        this.flightChannelSystemIds = flightChannelSystemIds;
    }

    public String getVacationSystemIds() {
        return vacationSystemIds;
    }

    public void setVacationSystemIds(String vacationSystemIds) {
        this.vacationSystemIds = vacationSystemIds;
    }

    public float getRefundDifferenceThreshold() {
        return refundDifferenceThreshold;
    }

    public void setRefundDifferenceThreshold(float refundDifferenceThreshold) {
        this.refundDifferenceThreshold = refundDifferenceThreshold;
    }

    public int getCancelRetryBeforeMinutes() {
        return cancelRetryBeforeMinutes;
    }

    public void setCancelRetryBeforeMinutes(int cancelRetryBeforeMinutes) {
        this.cancelRetryBeforeMinutes = cancelRetryBeforeMinutes;
    }
}
