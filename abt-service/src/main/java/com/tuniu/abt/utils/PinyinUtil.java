/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author：meixuesong
 * Date：2012-6-29
 * Description:
 */

package com.tuniu.abt.utils;

import com.google.common.base.Charsets;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *
 * 此类描述的是：拼音转换类
 * @author yusongtao
 */
public class PinyinUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PinyinUtil.class);

    /**
     *
     * 此方法描述的是：汉字转拼音
     * @param src
     * @return HashMap<String, Object>
     */
    public static String getPinyin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];

        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);

        StringBuilder t4 = new StringBuilder();
        int t0 = t1.length;
        for (int i = 0; i < t0; i++) {

            // 判断能否为汉字字符
            if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    LOG.error(src, e);
                }

                // 将汉字的几种全拼都存到t2数组中
                t4.append(t2[0]).append(" ");
            } else {

                // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                t4.append(Character.toString(t1[i]));
            }
        }
        return t4.toString().trim();
    }

    public static void main(String[] args) {
        String t = "浩t%S藏";
        System.out.println(getMultiplePronounciationsWithoutTone(t));
    }

    /**
     * @description 返回多音字的全部拼音（不区分声调）
     * @author lanlugang
     * @date 2015-4-28 17:35:25
     * @param src
     * @return
     */
    public static List<String> getMultiplePronounciationsWithoutTone(String src) {
        List<String> dstPinyinList = new ArrayList<String>();
        List<String> tempPinyinList = new ArrayList<String>();
        String[] curCharPinyin = null;
        Set<String> curPinyinSet = null;

        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] srcCharArray = src.toCharArray();
        for (char curChar : srcCharArray) {
            String curCharString = Character.toString(curChar);
            // 判断能否为汉字字符
            if (curCharString.getBytes(Charsets.UTF_8).length != curCharString.length()) {
                if (curCharString.matches("[\\u4E00-\\u9FA5]+")) {
                    try {
                        curCharPinyin = PinyinHelper.toHanyuPinyinStringArray(curChar, outputFormat);
                        if (null == curCharPinyin) {
                            LOG.error("【" + Character.toString(curChar) + "】字转换拼音失败：转换结果为空！");
                            return null;
                        }
                        //集合用于去除声调不同的重复拼音
                        curPinyinSet = new HashSet<String>();
                        for (int i = 0; i < curCharPinyin.length; i++) {
                            if (!curPinyinSet.contains(curCharPinyin[i])) {
                                curPinyinSet.add(curCharPinyin[i]);
                            } else {
                                continue;
                            }
                        }
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        LOG.error("【" + Character.toString(curChar) + "】字转换拼音失败：" + e.getMessage(), e);
                        return null;
                    }
                } else {
                    //不在Unicode汉字编码内的字符，返回null
                    LOG.error("【" + Character.toString(curChar) + "】字转换拼音失败：转换结果为空！");
                    return null;
                }
            } else {
                // 如果不是汉字字符，则直接将字符放入当前字符拼音集合
                curPinyinSet = new HashSet<String>();
                curPinyinSet.add(Character.toString(curChar));
            }
            //进行输出拼音字串拼接
            Iterator<String> iter = null;
            if (dstPinyinList.size() == 0) {
                iter = curPinyinSet.iterator();
                while (iter.hasNext()) {
                    String curPinyin = (String) iter.next();
                    dstPinyinList.add(curPinyin);
                }
            } else {
                for (String dstPinyin : dstPinyinList) {
                    iter = curPinyinSet.iterator();
                    while (iter.hasNext()) {
                        String curPinyin = (String) iter.next();
                        tempPinyinList.add(dstPinyin + curPinyin);
                    }
                }
                dstPinyinList.clear();
                dstPinyinList.addAll(tempPinyinList);
                tempPinyinList.clear();
            }

        }
        return dstPinyinList;
    }

}
