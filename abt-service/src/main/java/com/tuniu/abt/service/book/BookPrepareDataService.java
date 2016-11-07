package com.tuniu.abt.service.book;

import com.google.common.base.Charsets;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.book.main.BookVendorId;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.Passenger;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.intf.dto.book.request.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.utils.IdcardValidator;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.abt.utils.TsConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
/**
 * Created by chengyao on 2016/2/17.
 */
@Service
public class BookPrepareDataService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BookPrepareDataService.class);

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private TsConfig tsConfig;

    @Resource
    private BookBaseService bookBaseService;

    public BookingData convert2InnerObject(BookingRequest bookingRequest) {
        BookingData bookingData = new BookingData();
        bookingData.setOrderId(bookingRequest.getOrderId());
        bookingData.setSystemId(bookingRequest.getSystemId());
        bookingData.setVendorId(bookingRequest.getVendorId());
        BookingDetail bookingDetail = bookingRequest.getBookingDetail();
        bookingData.setActionCode(bookingDetail.getActionCode());
        bookingData.setPolicyId(bookingDetail.getPolicyId());
        // 乘机人信息
        List<BookingPassenger> passengers = bookingDetail.getPassengers();
        Passenger psgInfo = null;
        List<Passenger> adtPsgList = null;
        List<Passenger> infPsgList = null;
        for (BookingPassenger passenger : passengers) {
            psgInfo = new Passenger();
            psgInfo.setPassengerName(passenger.getName());
            psgInfo.setOrgPassengerName(passenger.getName());
            psgInfo.setFirstName(passenger.getFirstName());
            psgInfo.setLastName(passenger.getLastName());
            psgInfo.setIdentityType(Integer.valueOf(passenger.getIdType()));
            psgInfo.setIdentityNo(passenger.getIdNumber());
            psgInfo.setBirthday(passenger.getBirthday());
            psgInfo.setGender(passenger.getSex());
            psgInfo.setContactNum(passenger.getTel());
            psgInfo.setPersonId(passenger.getPersonId());
            psgInfo.setRefPersonId(passenger.getRefPersonId());
            psgInfo.setPassengerTypeCode(passenger.getPassengerTypeCode());
            if (BizzConstants.PSG_TYPE_CODE_ADT.equals(passenger.getPassengerTypeCode())) {
                bookingData.getPassengerCount()[0]++;
                if (null == adtPsgList) {
                    adtPsgList = new ArrayList<Passenger>();
                }
                adtPsgList.add(psgInfo);
            } else if (BizzConstants.PSG_TYPE_CODE_CHD.equals(passenger.getPassengerTypeCode())) {
                bookingData.getPassengerCount()[1]++;
            } else if (BizzConstants.PSG_TYPE_CODE_INF.equals(passenger.getPassengerTypeCode())) {
                bookingData.getPassengerCount()[2]++;
                if (null == infPsgList) {
                    infPsgList = new ArrayList<Passenger>();
                }
                infPsgList.add(psgInfo);
            } else {
                throw new BizzException(BookEx.INVALID_PSG_TYPE, "入参中人员类型错误");
            }
            bookingData.getPassengers().add(psgInfo);
        }
        infPsgRefPersonIdHandler(adtPsgList, infPsgList);
        // 航段信息
        int segmentNum = 0;
        Segment segment = null;
        for (FlightOption flightOption : bookingDetail.getFlightItem().getFlightOptions()) {
            for (TravelSegment travelSegment : flightOption.getTravelSegments()) {
                segment = new Segment();
                segment.setSegmentNumber(++segmentNum);
                segment.setFlightNo(travelSegment.getFlightNo());
                segment.setPlaneType(travelSegment.getCraftType());
                segment.setSeatClass(travelSegment.getCabin().getSeatClass());
                segment.setSeatCode(travelSegment.getCabin().getSeatCode());
                segment.setAirlineCompanyIataCode(segment.getFlightNo().substring(0, 2));
                // 出发机场信息
                segment.setOrgAirportIataCode(travelSegment.getDeparture().getAirportCode());
                segment.setOrgAirportName(travelSegment.getDeparture().getAirportName());
                segment.setOrgCityIataCode(travelSegment.getDeparture().getCityIataCode());
                segment.setOrgCityName(travelSegment.getDeparture().getCityName());
                segment.setDepartureDate(travelSegment.getDeparture().getDate());
                segment.setDepartureTime(travelSegment.getDeparture().getTime());
                segment.setDepartureAirportTerminal(travelSegment.getDeparture().getTerminal());
                // 到达机场信息
                segment.setDstAirportIataCode(travelSegment.getArrival().getAirportCode());
                segment.setDstAirportName(travelSegment.getArrival().getAirportName());
                segment.setDstCityIataCode(travelSegment.getArrival().getCityIataCode());
                segment.setDstCityName(travelSegment.getArrival().getCityName());
                segment.setArriveDate(travelSegment.getArrival().getDate());
                segment.setArrivalTime(travelSegment.getArrival().getTime());
                segment.setArrivalAirportTerminal(travelSegment.getArrival().getTerminal());
                bookingData.getSegments().add(segment);
            }
        }
        if (segmentNum == 1) {
            bookingData.setFlightType(1);
        } else if (segmentNum == 2) {
            bookingData.setFlightType(2);
        } else {
            bookingData.setFlightType(3);
        }
        // 价格信息
        bookingData.setPriceInfo(bookingDetail.getFlightItem().getPriceInfo());
        // 联系方式
        String contactNum = systemConfig.getTravelSkyCtct();// 默认配置的联系方式
        if (null != bookingDetail.getContactInfo()
                && StringUtils.isNotBlank(bookingDetail.getContactInfo().getContactPersonTel())) {
            boolean useDefaultContactNum = tsConfig.isAllowed(TsConfig.USE_DEFAULT_CONTACT_NUM,
                    bookingRequest.getSystemId(), bookingRequest.getVendorId());
            if (!useDefaultContactNum) {
                contactNum = bookingDetail.getContactInfo().getContactPersonTel().replaceAll("-", "");
            }
        }
        bookingData.setContactTel(contactNum);
        // 占位office初始化为默认office号
        bookingData.setOccupyOfficeNo(systemConfig.getIbeplusOfficeNo());
        return bookingData;
    }

    private void infPsgRefPersonIdHandler(List<Passenger> adtPsgList, List<Passenger>  infPsgList) {
        if (null == infPsgList) {
            return;
        }
        for (Iterator<Passenger> iterInf = infPsgList.iterator(); iterInf.hasNext();) {
            Passenger inf = iterInf.next();
            if (null == inf.getRefPersonId()
                    || inf.getRefPersonId() == 0) {
                continue;
            }
            for (Iterator<Passenger> iterAdt = adtPsgList.iterator(); iterAdt.hasNext();) {
                Passenger adt = iterAdt.next();
                if (inf.getRefPersonId().longValue() == adt.getPersonId()) {
                    iterInf.remove();
                    iterAdt.remove();
                    break;
                }
            }
        }
        for (Iterator<Passenger> iterInf = infPsgList.iterator(); iterInf.hasNext();) {
            Passenger inf = iterInf.next();
            for (Iterator<Passenger> iterAdt = adtPsgList.iterator(); iterAdt.hasNext();) {
                Passenger adt = iterAdt.next();
                inf.setRefPersonId(adt.getPersonId());
                iterInf.remove();
                iterAdt.remove();
                break;
            }
        }
    }

    /**
     * 
     * Description: 校验航段、人员信息<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param bookingData
     * @throws Exception <br>
     */
    public void checkBookingData(BookingData bookingData) throws Exception {
        // 校验航段信息
        checkSegment(bookingData);
        // 校验人员信息
        checkPassengerInfo(bookingData);
        // 校验是否是屏蔽航班
        checkBan(bookingData);
    }

    /**
     * 校验是否屏蔽航班
     * @param bookingData
     */
    private void checkBan(BookingData bookingData) {
        List<Segment> segments = bookingData.getSegments();
        for (Segment segment : segments) {
            boolean isBanned = bookBaseService.checkBan(bookingData.getVendorId(), bookingData.getSystemId(), segment);
            if (isBanned) {
                throw new BizzException(BookEx.SEGMENT_BANNED, "航班已被屏蔽，不能发起占位，航班号:" + segment.getFlightNo());
            }
        }
    }
    
    /**
     * Description: 校验航段信息<br>
     * 
     * @author lanlugang<br>
     * @taskId <br>
     * @param bookingData
     * @throws Exception <br>
     */
    private void checkSegment(BookingData bookingData) throws Exception {
        List<Segment> segments = bookingData.getSegments();
        if (bookingData.getVendorId() == BookVendorId.AOP.intValue() && segments.size() > 1) {
            throw new BizzException(BookEx.VERIFY_PARAM_FAILED, "供应商为开放平台，暂不支持多航段同时占位，请按航段分开发起占位");
        } else if (bookingData.getVendorId() == BookVendorId.CTRIP.intValue() && segments.size() > 1) {
            throw new BizzException(BookEx.VERIFY_PARAM_FAILED, "供应商为携程，暂不支持多航段同时占位，请按航段分开发起占位");
        }
        for (int i = 0; i < segments.size(); i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Segment segment = segments.get(i);
            Date curDateTime = new Date();
            Date flightDepartTime = dateFormat.parse(segment.getDepartureDate() + " "
                    + segment.getDepartureTime());
            // 航班起飞时间早于当前时间
            if (curDateTime.compareTo(flightDepartTime) > 0) {
                throw new BizzException(BookEx.VERIFY_PARAM_FAILED, "请检查航班起飞时间，保证航班起飞时间晚于当前时间。");
            }
            if (i > 0) {
                Segment flight1 = segments.get(i);
                Segment flight0 = segments.get(i - 1);
                SimpleDateFormat dateFormat1 = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm");
                Date date0 = dateFormat1.parse(flight0.getDepartureDate() + " "
                        + flight0.getDepartureTime());
                Date date1 = dateFormat1.parse(flight1.getDepartureDate() + " "
                        + flight1.getDepartureTime());
                if (date0.compareTo(date1) > 0) {
                    throw new BizzException(BookEx.VERIFY_PARAM_FAILED, "请检查每段航程出发时间，保证返程出发时间大于去程出发时间。");
                }
            }
        }
    }

    /**
     * Description: 校验乘客信息<br>
     * 
     * @author lanlugang<br>
     * @taskId <br>
     * @param bookingData
     * @throws Exception <br>
     */
    private void checkPassengerInfo(BookingData bookingData) throws Exception {
        // 1. 校验人员比例
        int adultNum = bookingData.getPassengerCount()[0];
        int childNum = bookingData.getPassengerCount()[1];
        int babyNum = bookingData.getPassengerCount()[2];
        if (adultNum == 0) {
            if (childNum != 0 && babyNum != 0) {
                throw new BizzException(BookEx.VERIFY_PARAM_FAILED,"暂不支持儿童和婴儿单独出行，请由成人携带！");
            } else if (childNum != 0 && babyNum == 0) {
                throw new BizzException(BookEx.VERIFY_PARAM_FAILED,"暂不支持儿童单独出行，请由成人携带！");
            } else if (childNum == 0 && babyNum != 0) {
                throw new BizzException(BookEx.VERIFY_PARAM_FAILED,"暂不支持婴儿单独出行，请由成人携带！");
            } else {
                throw new BizzException(BookEx.VERIFY_PARAM_FAILED,"没有出行人信息");
            }
        } else {
            // 除了成人外，有儿童、婴儿
            if (childNum != 0 && babyNum != 0)
            {
                if (2 * adultNum < (childNum + babyNum))
                {
                    throw new BizzException(BookEx.VERIFY_PARAM_FAILED,"1成人支持携带婴儿加儿童数最多为2！");
                }
            }
            // 除了成人外，只有儿童
            else if (childNum != 0 && babyNum == 0)
            {
                if (2 * adultNum < childNum)
                {
                    throw new BizzException(BookEx.VERIFY_PARAM_FAILED,"1成人最多支持携带两个儿童！");
                }
            }
            // 除了成人外，只有婴儿
            else if (childNum == 0 && babyNum != 0)
            {
                if (adultNum < babyNum)
                {
                    throw new BizzException(BookEx.VERIFY_PARAM_FAILED,"1成人最多支持携带1个婴儿！");
                }
            }
        }
        for (Passenger info : bookingData.getPassengers()) {
            // 2. 校验乘客证件号
            checkPassegerID(info);
            // 3. 校验乘客姓名
            if (StringUtils.isEmpty(info.getPassengerName())) {
                throw new BizzException(BookEx.INVALID_PSG_NAME, "出游人姓名不能为空.");
            } else if (bookingData.getVendorId() == BizzConstants.V_CTRIP) {
                if (babyNum > 0) {
                    LOGGER.error("【携程占位】暂不支持婴儿预定，请选择其它供应商航班，重新预定！");
                    throw new BizzException(BookEx.INVALID_PSG_TYPE,
                            "【携程占位】暂不支持婴儿预定，请选择其它供应商航班，重新预定！");
                }
                checkPassegerName4CtripOccupy(info);
            } else {
                checkPassegerName(info);
            }
        }
    }

    /**
     * 
     * Description: 校验乘客名字<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param info
     * @return <br>
     */
    private void checkPassegerName(Passenger info) {
        // 名字中如果包含中文，则校验每个字符是否都是中文
        String pName = info.getPassengerName();
        if (pName.getBytes(Charsets.UTF_8).length != pName.length()) { // 包含中文
            if (!Pattern.matches("[\u4e00-\u9fa5]*", pName)) {// 包含中文以外的其他字符
                if (!Pattern.matches("[\u4e00-\u9fa5]*[a-zA-Z]+", pName)) {
                    throw new BizzException(BookEx.INVALID_PSG_NAME,
                            "出游人姓名如果中英文混杂，则必须汉字在前且往后都为字母.");
                }
            }
            for (int i = 0; i < pName.length(); i++) {
                String sinName = pName.substring(i, i + 1);
                if (!Charset.forName("GB2312").newEncoder().canEncode(sinName)
                        && (StringUtils.isBlank(info.getFirstName())
                                || StringUtils.isBlank(info.getLastName()))) {
                    throw new BizzException(BookEx.INVALID_PSG_NAME,
                            "出游人姓名包含生僻字'" + sinName
                            + "',请使用汉语拼音替代,或者输入姓和名的拼音.");
                }
            }
        } else {
            // 全拼音，需要包含"/"
            if (!Pattern.matches("[a-zA-Z-]*\\/[a-zA-Z-]*", pName)) {
                throw new BizzException(BookEx.INVALID_PSG_NAME,
                        "出游人姓名填写全汉语拼音或者全英文时，姓名必须以 '/'隔开.");
            }
        }
    }
    
    public void checkPassegerName4CtripOccupy(Passenger passenger) {
        String passengerName = passenger.getPassengerName();
        // 名字中如果包含中文
        if (passengerName.getBytes(Charsets.UTF_8).length != passengerName.length()) {
            // 必须全部是汉字
            if (!Pattern.matches("[\u4e00-\u9fa5]*", passengerName)) {
                throw new BizzException(BookEx.INVALID_PSG_NAME,
                        "【携程占位失败】乘机人【"+passengerName+"】姓名必须是简体中文且不能是汉字拼音的组合！");
            }
        } else {
            // 全拼音，需要包含"/"
            if (!Pattern.matches("[a-zA-Z-]*\\/[a-zA-Z-]*", passengerName)) {
                throw new BizzException(BookEx.INVALID_PSG_NAME,
                        "【携程占位失败】乘机人【"+passengerName+"】姓名填写全汉语拼音或者全英文时，姓名必须以'/'隔开！");
            }
        }
    }

    /**
     * 
     * Description: 校验乘客证件<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param info <br>
     */
    private void checkPassegerID(Passenger info) {
        if (info.getIdentityType().intValue() == BizzConstants.D_TYPE_1) {
            if (!IdcardValidator.isValidatedAllIdcard(info.getIdentityNo())) {
                throw new BizzException(BookEx.INVALID_ID_NUMBER,
                        info.getPassengerName() + "身份证号码格式不正确，请检查出游人身份证号码");
            }
        }
        if ((BizzConstants.PSG_TYPE_CODE_CHD.equals(info.getPassengerTypeCode())
                || BizzConstants.PSG_TYPE_CODE_INF.equals(info.getPassengerTypeCode()))) {
            if (info.getBirthday() == null) {
                throw new BizzException(BookEx.INVALID_BIRTH_DATE, "儿童或婴儿的出生日期不能为空");
            } else {
                String regex = "(20\\d{2})-((1[0-2])|(0[1-9]))-(([12][0-9])|(3[01])|(0[1-9]))";
                if (!info.getBirthday().matches(regex)) {
                    throw new BizzException(BookEx.INVALID_BIRTH_DATE,
                            info.getPassengerName()
                            + "出生日期格式不正确，请按照YYYY-MM-DD格式输入出生日期，如2012-05-23");
                }
            }
        }
    }

}
