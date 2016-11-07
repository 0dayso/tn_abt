package com.tuniu.abt.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用工具类
 *
 * @author chengyao
 */
public class CommonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    private CommonUtils() {

    }

    public static <Z> Z transform(Object source, Class<Z> tClass) {
        if (source == null) {
            return null;
        }
        try {
            Z target = tClass.newInstance();
            BeanUtils.copyProperties(target, source);
            return target;
        } catch (Exception ex) {
            LOGGER.error("transform error.", ex);
            return null;
        }
    }

    public static <Z, T> List<Z> transformList(Collection<T> sources, Class<Z> tClass) {
        try {
            List<Z> result = new ArrayList<Z>();
            if (sources == null) {
                return result;
            }
            for (T source : sources) {
                if (source == null) {
                    continue;
                }
                Z target = tClass.newInstance();
                BeanUtils.copyProperties(target, source);
                result.add(target);
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error("transformList error.", ex);
            return Collections.emptyList();
        }
    }

    public static <Z, T> List<Z> transformList(Collection<T> sources, ListConverter<Z, T> converter) {
        try {
            List<Z> result = new ArrayList<Z>();
            for (T source : sources) {
                result.add(converter.convert(source));
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error("transformList error.", ex);
            return Collections.emptyList();
        }
    }

    public static void copyPropertiesSilence(Object target, Object source) {
        if (source == null || target == null) {
            return;
        }
        try {
            BeanUtils.copyProperties(target, source);
        } catch (Exception ex) {
            LOGGER.error("copyProperties error.", ex);
        }
    }

    public static <Z, T> List<Z> transformListRemoveNone(Collection<T> sources, ListConverter<Z, T> converter) {
        try {
            List<Z> result = new ArrayList<Z>();
            for (T source : sources) {
                Z z = converter.convert(source);
                if (z == null) {
                    continue;
                }
                result.add(z);
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error("transformList error.", ex);
            return Collections.emptyList();
        }
    }

    /**
     * 获取指定多少天前的日期（凌晨）
     *
     * @param day day
     * @return time in mill
     */
    public static long getDayAgoInMill(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    //对整数的个位数进行四舍五入
    public static int intRound(int input) {
        return (int) (Math.round((input) * 0.1)) * 10;
    }

    /**
     * 对象深拷贝
     *
     * @param t
     * @return
     */
    public static <T> T deepCloneObject(T t) {
        //将对象写到流里
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ObjectOutputStream objectOutput = null;
        ObjectInputStream objectInput = null;
        try {
            objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeObject(t);
            //从流里读出来
            ByteArrayInputStream byteInput = new ByteArrayInputStream(byteOutput.toByteArray());
            objectInput = new ObjectInputStream(byteInput);
            return (T) objectInput.readObject();
        } catch (IOException e) {
            throw new RuntimeException("对象深拷贝异常:" + t.getClass(), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("对象深拷贝异常(" + t.getClass() + "):没有发现该类.", e);
        } finally {
            IOUtils.closeQuietly(objectOutput);
            IOUtils.closeQuietly(objectInput);
        }
    }
    
}
