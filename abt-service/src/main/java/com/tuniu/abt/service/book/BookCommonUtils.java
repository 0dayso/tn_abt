package com.tuniu.abt.service.book;

import com.google.common.base.Charsets;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.Passenger;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.PinyinUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.*;

/**
 * 占位通用方法
 *
 * Created by chengyao on 2016/1/11.
 */
public class BookCommonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookCommonUtils.class);


    /**
     * 
     * Description: 如果乘客姓名包含生僻字，则进行生僻字处理<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param passengers <br>
     */
    public static void rarelyCNCharHandler(List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            String passengerName = passenger.getPassengerName();
            if (hasUncommonCNChar(passengerName)) {
                replacePsgNameUsePinyin(passenger);
            }
        }
    }
    
    /**
     * @description 判断字串中是否包含中文生僻字（不在GB2312编码内的汉字）
     * @author lanlugang
     * @date 2015-4-29 10:02:24
     * @param context
     * @return
     */
    public static boolean hasUncommonCNChar(String context)
    {
        boolean flag = false;
        if (context.getBytes(Charsets.UTF_8).length != context.length())
        { // 包含中文
            String curCNChar = null;
            for (int i = 0; i < context.length(); i++)
            {
                curCNChar = context.substring(i, i + 1);
                if (!Charset.forName("GB2312").newEncoder().canEncode(curCNChar))
                {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * @description 将乘客姓名中的生僻字替换为拼音, eg: 张和囧 --> 张和jiong 张槑飞 --> 张meifei 弢嘂飞 --> tao/jiaofei
     * @author lanlugang
     * @date 2015-4-29 09:57:15
     * @param passengerInfo
     * @throws Exception
     */
    public static void replacePsgNameUsePinyin(Passenger passengerInfo) {
        String passengerName = passengerInfo.getPassengerName();// 中文姓名
        String firstName = passengerInfo.getFirstName();// 旅客名(拼音)
        String lastName = passengerInfo.getLastName();// 旅客姓(拼音)
        if (null != passengerName && !passengerName.trim().isEmpty()
                && null != firstName && !firstName.trim().isEmpty()
                && null != lastName && !lastName.trim().isEmpty())
        {
            firstName = firstName.trim().toLowerCase();
            lastName = lastName.trim().toLowerCase();
            String psgNamePinyin = lastName + firstName;
            char[] chineseChars = passengerName.toCharArray();
            String replacePsgName = "";
            if (!hasUncommonCNChar(String.valueOf(chineseChars[0])))
            {
                replacePsgName += String.valueOf(chineseChars[0]);
                String curChar = null;
                int len = chineseChars.length;
                for (int i = 1; i < len; i++)
                {
                    curChar = String.valueOf(chineseChars[i]);
                    if (hasUncommonCNChar(curChar))
                    {// 当前字是生僻字
                        String remainder = String.copyValueOf(chineseChars, i, (len - i));
                        List<String> remainderPinyinList = PinyinUtil.getMultiplePronounciationsWithoutTone(remainder);
                        if (null != remainderPinyinList && remainderPinyinList.size() > 0)
                        {
                            for (String remainderPinyin : remainderPinyinList)
                            {
                                // [(生僻字及其后面字串)的拼音]匹配[输入姓名拼音的尾串]成功，则替换姓名为： 生僻字之前的汉字+生僻字及其后汉字的拼音
                                if (psgNamePinyin.endsWith(remainderPinyin))
                                {// (注意：如果存在多个匹配成功的字串，则第一个匹配成功即返回)
                                    replacePsgName += remainderPinyin;
                                    passengerInfo.setPassengerName(replacePsgName);
                                    return;
                                }
                                else
                                {
                                    continue;
                                }
                            }
                            // 以上没有找到可以匹配的拼音，则抛出异常
                            LOGGER.error("【占位失败】失败原因：旅客姓名带有生僻字，生僻字及其后面字串转换的拼音结果与输入的拼音不一致，请检查输入是否有误！");
                            throw new BizzException(BookEx.VERIFY_PARAM_FAILED,
                                    "旅客姓名带有生僻字，生僻字及其后面字串转换的拼音结果与输入的拼音不一致，请检查输入是否有误！");
                        }
                        else
                        {
                            LOGGER.error("【占位失败】失败原因：旅客姓名带有生僻字，生僻字及其后面的字串转换为拼音失败！");
                            throw new BizzException(BookEx.VERIFY_PARAM_FAILED,
                                    "旅客姓名带有生僻字，生僻字及其后面的字串转换为拼音失败！");
                        }
                    }
                    else
                    {
                        replacePsgName += curChar;
                    }
                }
            }
            else
            {// 姓名中第一个汉字是生僻字，则替换姓名为： 姓拼音+/+名拼音
                List<String> fullNamePinyinList = PinyinUtil.getMultiplePronounciationsWithoutTone(passengerName);
                for (String fullNamePinyin : fullNamePinyinList)
                {
                    if (fullNamePinyin.equals(psgNamePinyin))
                    {
                        replacePsgName = firstName + "/" + lastName;
                        passengerInfo.setPassengerName(replacePsgName);
                        return;
                    }
                    else
                    {
                        continue;
                    }
                }
                // 以上没有找到可以匹配的拼音，则抛出异常
                LOGGER.error("【占位失败】，失败原因：旅客姓名中第一个字为生僻字，生僻字及其后面字串转换的拼音结果与输入的拼音不一致，请检查输入是否有误！");
                throw new BizzException(BookEx.VERIFY_PARAM_FAILED,
                        "旅客姓名中第一个字为生僻字，生僻字及其后面字串转换的拼音结果与输入的拼音不一致，请检查输入是否有误！");
            }
        }
        else
        {
            LOGGER.error("【占位失败】失败原因：旅客姓名带有生僻字，则旅客姓(拼音)、旅客名(拼音)字段不能为空(或者请用拼音代替生僻字)！");
            throw new BizzException(BookEx.VERIFY_PARAM_FAILED,
                    "旅客姓名带有生僻字，则旅客姓(拼音)、旅客名(拼音)字段不能为空(或者请用拼音代替生僻字)！");
        }
    }
    
    /**
     * Description: 把乘机人按乘客类型、单pnr人数限制、是否重名等进行分组<br>
     *
     * @param passengers
     * @param maxPsgNumLimit
     * @return <br>
     * @author lanlugang<br>
     * @taskId AIR-665<br>
     */
    public static List<List<Passenger>> classifyPsgInfosByType(List<Passenger> passengers, int maxPsgNumLimit) {
        // 2.按乘客类型把成人、儿童、婴儿分类
        Map<String, List<Passenger>> psgTypeMap = splitPassengersByType(passengers);
        List<List<Passenger>> psgInfosTotalList = new ArrayList<List<Passenger>>();
        List<Passenger> infPsgInfoList = null;
        for (String psgType : psgTypeMap.keySet()) {
            if (BizzConstants.PSG_TYPE_CODE_INF.equals(psgType)) {
                infPsgInfoList = psgTypeMap.get(psgType);
                continue;
            }
            // 重名、同音处理
            duplicatedPsgNamesHandler(psgInfosTotalList, psgTypeMap.get(psgType));
        }
        // 单pnr人数限制
        maxPsgNumLimitHandler(psgInfosTotalList, maxPsgNumLimit);
        if (CollectionUtils.isNotEmpty(infPsgInfoList)) {
            for (Passenger infPsg : infPsgInfoList) {
                addInf2RefPsgList(infPsg, psgInfosTotalList);
            }
        }
        return psgInfosTotalList;
    }

    private static void addInf2RefPsgList(Passenger infPsg, List<List<Passenger>> psgInfosTotalList) {
        long refPersonId = infPsg.getRefPersonId();
        List<Passenger> passengers = null;
        for (List<Passenger> passengerList : psgInfosTotalList) {
            for (Passenger passenger : passengerList) {
                if (refPersonId == passenger.getPersonId()) {
                    passengers = passengerList;
                    break;
                }
            }
        }
        passengers.add(infPsg);
    }

    private static void maxPsgNumLimitHandler(List<List<Passenger>> psgInfosTotalList, int maxPsgNumLimit) {
        List<List<Passenger>> splitPassengers = new ArrayList<List<Passenger>>();
        for (List<Passenger> passengers : psgInfosTotalList) {
            while (passengers.size() > maxPsgNumLimit) {
                List<Passenger> curPsgs = new ArrayList<Passenger>();
                for (Iterator<Passenger> iterator = passengers.iterator(); iterator.hasNext();) {
                    Passenger passenger = iterator.next();
                    curPsgs.add(passenger);
                    iterator.remove();
                    if (curPsgs.size() == maxPsgNumLimit) {
                        break;
                    }
                }
                splitPassengers.add(curPsgs);
            }
        }
        if (CollectionUtils.isNotEmpty(splitPassengers)) {
            psgInfosTotalList.addAll(splitPassengers);
        }
    }

    private static void duplicatedPsgNamesHandler(List<List<Passenger>> psgInfosTotalList, List<Passenger> passengers) {
        if (hasDuplicatedPsgNames(passengers)) {
            Set<String> psgNames = new HashSet<String>();
            List<Passenger> curPsgInfoList = null;
            for (Iterator<Passenger> iterator = passengers.iterator(); iterator.hasNext();) {
                Passenger passenger = iterator.next();
                String psgName = passenger.getPassengerName().replaceAll("/", "");
                String psgNamePinyin = PinyinUtil.getPinyin(psgName);
                if (psgNames.contains(psgName) || psgNames.contains(psgNamePinyin)) {
                    curPsgInfoList = new ArrayList<Passenger>();
                    curPsgInfoList.add(passenger);
                    psgInfosTotalList.add(curPsgInfoList);
                    iterator.remove();
                } else {
                    psgNames.add(psgName);
                    psgNames.add(psgNamePinyin);
                }
            }
        }
        psgInfosTotalList.add(passengers);
    }

    public static Map<String, List<Passenger>> splitPassengersByType(List<Passenger> passengers) {
        Map<String, List<Passenger>> passengersMap = new HashMap<String, List<Passenger>>();
        String psgType = null;
        List<Passenger> psgInfoList = null;
        for (Passenger passenger : passengers) {
            psgType = passenger.getPassengerTypeCode();
            // 按乘客类型把成人、儿童、婴儿分组
            if (!passengersMap.containsKey(psgType)) {
                psgInfoList = new ArrayList<Passenger>();
                psgInfoList.add(passenger);
                passengersMap.put(psgType, psgInfoList);
            } else {
                passengersMap.get(psgType).add(passenger);
            }
        }
        return passengersMap;
    }
    
    /**
     * Description: 判断乘客姓名是否存在重名或者同音<br>
     * 
     * @author lanlugang<br>
     * @date 2015-9-24
     * @taskId AIR-665<br>
     * @param passengers
     * @return <br>
     */
    public static boolean hasDuplicatedPsgNames(List<Passenger> passengers) {
        if (CollectionUtils.isEmpty(passengers)) {
            return false;
        }
        Set<String> psgNameSet = new HashSet<String>();
        String psgName = null;
        String psgNamePinyin = null;
        for (Passenger passenger : passengers) {
            if (BizzConstants.PSG_TYPE_CODE_INF
                    .equals(passenger.getPassengerTypeCode())) {
                continue;// 不计婴儿
            }
            psgName = passenger.getPassengerName().replaceAll("/", "");
            psgNamePinyin = PinyinUtil.getPinyin(psgName);
            if (psgNameSet.contains(psgName)
                    || psgNameSet.contains(psgNamePinyin)) {
                return true;
            } else {
                psgNameSet.add(psgName);
                psgNameSet.add(psgNamePinyin);
            }
        }
        return false;
    }

    /**
     *
     * Description: 转换占位失败的第三方信息为自定义的错误信息<br>
     *
     * @author lanlugang<br>
     * @date 2015-6-23 15:43:14
     * @taskId PTICKET-1389<br>
     * @param msg
     * @return <br>
     */
    public static String convertToResMsg(String msg) {
        // 座位数不足
        String[] noSeatMsgs = {
                "UNABLE TO SELL.PLEASE CHECK THE AVAILABILITY WITH \"AV\" AGAIN",
                "指定的航班在指定的日期不执行或指定的舱位已经无法订取"
        };
        if (isConvertMsg(msg, noSeatMsgs)) {
            return "指定的航班在指定的日期不执行或指定的舱位已经无法订取";
        }
        // 证件号码有误
        String[] invalidFOIDMsgs = {
                "不正确的证件号码",
                "INVALID FOID",
                "DUP ID"
        };
        if (isConvertMsg(msg, invalidFOIDMsgs)) {
            return "证件号码错误或重复，请检查您的证件信息后重新预订！";
        }
        // PNR项错误
        String[] infantNameMsgs = {
                "INFANT NAME"
        };
        if (isConvertMsg(msg, infantNameMsgs)) {
            return "婴儿名有误，请检查入参是否有婴儿的姓名拼音";
        }
        // IBE连接异常
        String[] connExceptionMsgs = {
                "Socket denied:too many sockets connection",
                "java.net.SocketTimeoutException: Read timed out",
                "Connect to", "Connection reset", "java.io.EOFException",
                "No Available Host Connection or Host Connection Failed",
                "com.travelsky.ibe.exceptions.AccessDenyException",
                "java.net.SocketException"
        };
        if (isConvertMsg(msg, connExceptionMsgs)) {
            return "网络连接异常，请稍后再试！";
        }
        // OFFICE_NOT_PERMITTED
        String[] officeNotpermitted = {
                "OFFICE_NOT_PERMITTED","office permission denied","office认证失败","需要授权"
        };
        if (isConvertMsg(msg, officeNotpermitted)) {
            return "OFFICE_NOT_PERMITTED";
        }
        return msg;
    }

    private static boolean isConvertMsg(String srcMsg, String... msgs) {
        List<String> msgList = Arrays.asList(msgs);
        for (String s : msgList) {
            if (srcMsg.contains(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 按每组占位最多9人进行分组(婴儿不计人数)
     * @param passengers
     * @param maxPsgNumLimit
     * @return
     */
    public static List<List<Passenger>> splitPassengersByMaxPsgNumLimit(List<Passenger> passengers, int maxPsgNumLimit) {
        List<Passenger> adtPsgList = new ArrayList<>();
        passengers.forEach(p -> {if (BizzConstants.PSG_TYPE_CODE_ADT.equals(p.getPassengerTypeCode())) adtPsgList.add(p);});
        List<Passenger> chdPsgList = new ArrayList<>();
        passengers.forEach(p -> {if (BizzConstants.PSG_TYPE_CODE_CHD.equals(p.getPassengerTypeCode())) chdPsgList.add(p);});
        List<Passenger> infPsgList = new ArrayList<>();
        passengers.forEach(p -> {if (BizzConstants.PSG_TYPE_CODE_INF.equals(p.getPassengerTypeCode())) infPsgList.add(p);});
        List<List<Passenger>> psgGroupUnitList = new ArrayList<>();
        List<Passenger> psgGroupUnit = null; // 人员组最小单元(1成人，1成人1儿童 或 1成人2儿童)
        for (Passenger adtPsg : adtPsgList) {
            psgGroupUnit = new ArrayList<>();
            psgGroupUnit.add(adtPsg);
            if (CollectionUtils.isNotEmpty(chdPsgList)) {
                int i = 0;
                for (Iterator<Passenger> iterator = chdPsgList.iterator(); iterator.hasNext();) {
                    if (i >= 2) {// 1成人最多带2儿童
                        break;
                    }
                    Passenger chdPsg = iterator.next();
                    psgGroupUnit.add(chdPsg);
                    iterator.remove();
                    i++;
                }

            }
            psgGroupUnitList.add(psgGroupUnit);
        }
        List<List<Passenger>> passengersList = mergePassengerGroupList(psgGroupUnitList, maxPsgNumLimit);
        // 添加婴儿
        if (CollectionUtils.isNotEmpty(infPsgList)) {
            for (Passenger infPsg : infPsgList) {
                addInf2RefPsgList(infPsg, passengersList);
            }

        }
        return passengersList;
    }

    private static List<List<Passenger>> mergePassengerGroupList(List<List<Passenger>> psgGroupUnitList, int maxPsgNumLimit) {
        List<List<Passenger>> passengersList = new ArrayList<>();
        List<Passenger> psgList = null;
        for (List<Passenger> psgGroupUnit : psgGroupUnitList) {
            if (null == psgList
                    || (psgList.size() + psgGroupUnit.size() > maxPsgNumLimit)) {
                psgList = new ArrayList<>();
                passengersList.add(psgList);
            }
            psgList.addAll(psgGroupUnit);
        }
        return passengersList;
    }

    /**
     * 重新组装bookingData: 单次占位pnr人数限制
     * @param bookingData
     * @return
     */
    public static List<BookingData> splitBookingDataByPsgsList(BookingData bookingData, List<List<Passenger>> psgInfosList) {
        List<BookingData> bookingDataList = new ArrayList<BookingData>();
        BookingData request = null;
        for (List<Passenger> psgInfos : psgInfosList) {
            request = CommonUtils.deepCloneObject(bookingData);
            request.getPassengers().clear();
            request.getPassengers().addAll(psgInfos);
            bookingDataList.add(request);
        }
        return bookingDataList;
    }

}
