package com.tuniu.abt.service.travelsky.ibe.module;

import com.travelsky.ibe.client.pnr.*;
import com.travelsky.ibe.exceptions.IBEException;
import com.travelsky.ibe.exceptions.SSSegmentMissingException;
import com.travelsky.util.QDateTime;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.book.main.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chengyao on 2016/3/14.
 */
@Service
public class IbeCreatePnrWrapModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbeCreatePnrWrapModule.class);
    
    @Resource
    private IbeInterfaceService ibeInterfaceService;
    @Resource
    private SystemConfig systemConfig;

    /**
     * 
     * Description: <br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param request
     * @return <br>
     */
    public PnrInfo createIBEPnr(BookingData request) {
        PnrInfo pnrInfo = null;
        try {
            BookInfomation bookInfomation = convertToBookInfomation(request);
            // 完成PNR必需信息输入，递交主机，生成PNR
            boolean flag = true;
            int trycount = 0;
            SSResult ssr = null;
            while (flag) {
                try {
                    // 如果因为SSSegmentMissingException异常导致生成PNR失败，则重复申请 3次
                    ssr = ibeInterfaceService.createPnr(bookInfomation);
                    if (null == ssr) {
                        throw new BizzException(BizzEx.IBE_INTF_RESULT_NONE, "ibe's SSResult is null");
                    }
                } catch (SSSegmentMissingException ibe) {
                    LOGGER.error("生成PNR失败，可以重新尝试3次." + ibe.getMessage(), ibe);
                    trycount++;
                    if (trycount < 3) {
                        continue;
                    }
                }
                flag = false;
            }
            pnrInfo = fromIBEPnrResponse(ssr);
        } catch (IBEException ibee) {
            LOGGER.error("调用IBE生成PNR失败", ibee);
            String message = convertNumToName(ibee.getMessage(), request.getPassengers());
            throw new BizzException(BizzEx.IBE_INTF_EX, "调用IBE生成PNR失败:" + message, ibee);
        } catch (Exception e) {
            LOGGER.error("调用IBE生成PNR失败", e);
            throw new BizzException(BizzEx.IBE_INTF_EX, "调用IBE生成PNR失败:" + e.getMessage(), e);
        }
        return pnrInfo;
    }
    
    private BookInfomation convertToBookInfomation(BookingData request) throws Exception {
        BookInfomation bookInfo = new BookInfomation();
        /** 添加航段信息 **/
        List<Segment> segments = request.getSegments();
        String airlineComCode = segments.get(0).getAirlineCompanyIataCode();
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            // 缺口程处理
            if (i > 0 && !segment.getOrgAirportIataCode()
                         .equals(segments.get(i-1).getDstAirportIataCode())) {
                BookAirSeg arnkSeg = new BookAirSeg(segments.get(i-1).getDstAirportIataCode(),
                        segment.getOrgAirportIataCode());
                bookInfo.addAirSeg(arnkSeg);
            }
            //
            String departureDate = segment.getDepartureDate();
            bookInfo.addAirSeg(segment.getFlightNo(), segment.getSeatCode().charAt(0),
                    segment.getOrgAirportIataCode(), segment.getDstAirportIataCode(),
                    "NN", request.getPassengers().size(), departureDate);
        }
        /** 添加旅客姓名 **/
        Map<Long, String> psgRphMap = new HashMap<Long, String>();
        for (Passenger passenger : request.getPassengers()) {
            psgRphMap.put(passenger.getPersonId(), passenger.getPassengerName());
        }
        boolean childFlag = false;
        for (Passenger passenger : request.getPassengers()) {
            if (BizzConstants.PSG_TYPE_CODE_ADT.equals(passenger.getPassengerTypeCode())) {
                bookInfo.addAdult(passenger.getPassengerName());
                bookInfo.addSSR_FOID(airlineComCode, "NI", passenger.getIdentityNo(), passenger.getPassengerName());
            } else if (BizzConstants.PSG_TYPE_CODE_CHD.equals(passenger.getPassengerTypeCode())) {
                childFlag = true;
                bookInfo.addChild(passenger.getPassengerName());
                bookInfo.addSSR_FOID(airlineComCode, "NI", passenger.getIdentityNo(), passenger.getPassengerName());
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyy",java.util.Locale.ENGLISH);
                String birthDate = passenger.getBirthday();
                BookSSR ssrChild = new BookSSR("CHLD", airlineComCode, "hk", 1, "", "", ' ', "",
                        QDateTime.dateToString(dateFormat.parse(DateTimeUtils
                                .str2strdate(birthDate)), "/ddmmmyy"), passenger.getPassengerName());
                bookInfo.addSSR(ssrChild);
            } else if (BizzConstants.PSG_TYPE_CODE_INF.equals(passenger.getPassengerTypeCode())) {
                String  carrierName = psgRphMap.get(passenger.getRefPersonId());
                bookInfo.addInfant(passenger.getBirthday(),
                        carrierName, passenger.getPassengerName());
                String infantBirth = DateTimeUtils.str2strdate(passenger.getBirthday());
                /* 为婴儿添加SSR INFT */
                String freeText = "";
                String enName = passenger.getLastName()
                        + "/" + passenger.getFirstName();
                for (Segment segment : request.getSegments()) {
                    String flightno = segment.getFlightNo();
                    String airline = segment.getAirlineCompanyIataCode();
                    String orgDst = segment.getOrgAirportIataCode()
                            + segment.getDstAirportIataCode();
                    String seatCode = segment.getSeatCode().substring(0, 1);
                    String date = DateTimeUtils.str2strdate(segment.getDepartureDate());
                    bookInfo.addSSR_INFT(airline, orgDst, flightno,
                            seatCode, date, enName, infantBirth, freeText,
                            carrierName);
                }
            }
        }
        // 如果有儿童,增加儿童的成人备注项信息
        if(childFlag) {
            PnrExternalInfo pnrExternalInfo = request.getPnrExternalInfo();
            if(null != pnrExternalInfo) {
                String adultPnr = pnrExternalInfo.getAdultPnr();
                String adultSeatCode = pnrExternalInfo.getAdultSeatCode();
                List<String> seatCodeList = Arrays.asList(adultSeatCode.split("#"));
                //所有航司成人带儿童的客票增加PNR备注信息功能
                if(StringUtils.isNotBlank(adultPnr)) {
                    for (int i=0; i < segments.size(); i++) {
                        bookInfo.addSSR(setBookSSR(segments.get(i), adultPnr, seatCodeList.get(i)));
                    }
                }
            }
        }
        /** OSI CTCT、CTCM、RMK项  **/
        String ctct = "CTCT" + request.getContactTel();
        String ctcm = "CTCM" + request.getContactTel() + "/P1";
        bookInfo.addOSI(airlineComCode, ctct);
        bookInfo.addOSI(airlineComCode, ctcm);
        addAuthorizeRemarks(bookInfo, request);
        /** 添加旅客出票时限 **/
        Date date = new Date();
        Date onedate = DateTimeUtils.addDays(date, 1);
        String departureDateStr = segments.get(0).getDepartureDate() 
                               + " " + segments.get(0).getDepartureTime();
        Date departureDate = DateTimeUtils.convertDate(departureDateStr, DateTimeUtils.DATETIME_M);
        // IBE出票时限:延长出票时限 ，1天以外24H，1天之内 之内航班起飞时间
        String ibelimitdate = "";
        if (departureDate.compareTo(onedate) > 0) {
            ibelimitdate = DateTimeUtils.dateConvert(onedate, DateTimeUtils.DATETIME_PATTERN);
        } else {
            ibelimitdate = DateTimeUtils.dateConvert(departureDate, DateTimeUtils.DATETIME_PATTERN);
        }
        bookInfo.setTimelimit(ibelimitdate);
        // 设置封口方式。如果不是"K","I","KI","IK"则会用默认方式封口"@",
        // 多航段需要强制封口
        if (segments.size() > 1) {
            bookInfo.setEnvelopType("I");
        }
        return bookInfo;
    }

    /**
     * 给供应商office号授权
     * @param bookInfo
     * @param request
     * @throws Exception
     */
    private void addAuthorizeRemarks(BookInfomation bookInfo, BookingData request) throws Exception {
        // 给供应商office号授权
        if (StringUtils.isNotBlank(request.getVendorOfficeNo())
                && !request.getVendorOfficeNo().equals(systemConfig.getIbeplusOfficeNo())) {
            bookInfo.addRMK(getAuthorizeRmk(request.getVendorOfficeNo()));
        }
        // 给出票office号授权
        if (StringUtils.isNotBlank(request.getEtdzOfficeNo())
                && !request.getEtdzOfficeNo().equals(systemConfig.getIbeplusOfficeNo())) {
            bookInfo.addRMK(getAuthorizeRmk(request.getEtdzOfficeNo()));
        }
    }

    private BookRMK getAuthorizeRmk(String officeNo) {
        String rmkText = "TJ AUTH " + officeNo + "/T";
        BookRMK bookRMK = new BookRMK();
        bookRMK.setRmkinfo(rmkText);
        return bookRMK;
    }


    private BookSSR setBookSSR(Segment segment,String adultPnr,String adultSeatCode) throws Exception
    {
        String serveCode="OTHS";
        StringBuffer serveInfo=new StringBuffer();
        //旅行日期
        String month= DateTimeUtils.convertMonth(segment.getDepartureDate().substring(5, 7));
        String day = segment.getDepartureDate().substring(8, 10);
        serveInfo.append(segment.getAirlineCompanyIataCode()).append(" ADT").append(" ")
                .append(adultPnr).append(" ")
                .append(segment.getFlightNo()).append(" ")
                .append(day+month).append(" ").append(adultSeatCode);
        BookSSR bookSSR = new BookSSR(serveCode, serveInfo.toString());
        return bookSSR;
    }


    /**
     * 失信旅客号码转化为名字
     *
     * @param: ibe失信人异常信息，乘客信息
     * @return 转换后的信息
     */
    public String convertNumToName(String info, List<Passenger> passengers) {
        String message = info;
        Pattern pat = Pattern.compile("(.*旅客)(.*)(为失信被执行人.*)");
        Matcher march = pat.matcher(message);
        if(march.find()){
            String str1 = march.group(1);
            String str2 = march.group(2);
            String str3 = march.group(3);
            String num[] = str2.split("/");
            StringBuffer sb = new StringBuffer();
            sb.append(passengers.get(Integer.parseInt(num[0])-1).getPassengerName());
            for (int i=1; i<num.length; i++) {
                sb.append("/");
                sb.append(passengers.get(Integer.parseInt(num[i])-1).getPassengerName());
            }
            //message = str1 + "**" + sb.toString() + "**" + str3;
            message = str1+sb.toString()+str3;
        }
        return message;
    }

    @SuppressWarnings("unchecked")
    private PnrInfo fromIBEPnrResponse(SSResult ibePnrResponse) {
        PnrInfo pnrData = new PnrInfo();
        List<String> actionCodes = new ArrayList<String>();
        if (ibePnrResponse != null) {
            pnrData.setPnrNo(ibePnrResponse.getPnrno());
            for (int i = 0; i < ibePnrResponse.getSegmentsCount(); i++) {
                SSSegment s = ibePnrResponse.getSegmentAt(i);
                String rscode = s.getActionCode();
                actionCodes.add(rscode);
            }
            // 返回内容添加comments项 added by @lanlugang 2015-9-24
            pnrData.setComments(ibePnrResponse.getComments());
            pnrData.setActionCodes(actionCodes);
        }
        return pnrData;
    }

}
