package com.tuniu.vla.base.utils;

import com.tuniu.vla.base.constants.SysEx;
import com.tuniu.vla.base.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 异常消息相关工具方法
 * <p>
 * Created by chengyao on 2015/12/28.
 */
public class ExceptionMessageUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMessageUtils.class);

    @Resource
    private ResourceBundleMessageSource messageSource;

    /**
     * 获取异常码
     *
     * @param throwable 异常
     * @return 本地的异常码
     */
    public int findCode(Throwable throwable) {
        int code;
        if (throwable instanceof BaseException) {
            code = ((BaseException) throwable).getCode();
        } else {
            if (throwable instanceof ConstraintViolationException) {
                code = SysEx.VERIFY_INPUT_ERROR;
            } else if (throwable instanceof DataAccessException || throwable instanceof SQLException) {
                code = SysEx.SQL_EXCEPTION;
            } else if (throwable instanceof IOException) {
                code = SysEx.IO_EXCEPTION;
            } else {
                code = SysEx.UNKNOWN_EXCEPTION;
            }
        }
        return code;
    }

    public String findWrappedMessage(Throwable throwable) {
        if (throwable instanceof BaseException) {
            BaseException baseException = (BaseException) throwable;
            return parseMessage(baseException);
        } else if (throwable instanceof ConstraintViolationException) {
            ConstraintViolationException validationEx = (ConstraintViolationException) throwable;
            StringBuilder errorMsg = new StringBuilder();
            for (ConstraintViolation violation : validationEx.getConstraintViolations()) {
                errorMsg.append(violation.getMessage()).append(";");
            }
            return errorMsg.toString();
        } else {
            return findChineseMessage(throwable);
        }
    }


    public String findCodeString(Throwable throwable) {
        return String.valueOf(findCode(throwable));
    }

    /**
     * 获取异常对应可读错误文本，中文资源
     *
     * @param throwable 异常
     * @return 错误信息
     */
    public String findChineseMessage(Throwable throwable) {
        String code = findCodeString(throwable);
        return findLocaleMessage(code, throwable, Locale.SIMPLIFIED_CHINESE);
    }

    /**
     * 获取异常对应可读错误文本，自动判断资源
     *
     * @param code      异常码
     * @param throwable 异常
     * @return 错误信息
     */
    public String findLocaleMessage(String code, Throwable throwable) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Locale locale = RequestContextUtils.getLocale(request);
        return findLocaleMessage(code, throwable, locale);
    }

    /**
     * 获取异常对应可读错误文本
     *
     * @param code      异常码
     * @param throwable 异常
     * @param locale    本地代码
     * @return 错误信息
     */
    public String findLocaleMessage(String code, Throwable throwable, Locale locale) {
        Object[] args = null;
        if (throwable instanceof BaseException) {
            if (throwable.getMessage() != null) {
                return throwable.getMessage();
            }
            args = ((BaseException) throwable).getMessageArgs();
        }
        return messageSource.getMessage(code, args, throwable.getMessage(), locale);
    }

    public List<String> findAllEx(Throwable throwable) {
        return findExArray(throwable, true, true);
    }

    public String findAllExString(Throwable throwable) {
        List<String> exs = findAllEx(throwable);
        StringBuilder sb = new StringBuilder();
        for (String ex : exs) {
            sb.append(ex).append("\n");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String findSimpleEx(Throwable throwable) {
        List<String> result = findExArray(throwable, false, false);
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    private List<String> findExArray(Throwable throwable, boolean loop, boolean findDetail) {
        List<String> bizzMessages = new ArrayList<String>();
        findBizzExMessage(throwable, bizzMessages, loop, findDetail);
        return bizzMessages;
    }

    private void findBizzExMessage(Throwable ex, List<String> messages, boolean loop, boolean findDetail) {
        if (ex == null || messages.size() > 5) { // 最多显示5层堆栈
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (findDetail) {
            StackTraceElement stacks = ex.getStackTrace()[0];
            sb.append("[").append(extractSimpleClassName(stacks.getClassName()))
                    .append(".").append(stacks.getMethodName()).append("]")
                    .append("[").append(stacks.getLineNumber()).append("]");
            if (ex instanceof BaseException) {
                BaseException baseException = (BaseException) ex;
                sb.append("[").append(baseException.getCode()).append("]");
                sb.append("[").append(parseMessage(baseException)).append("]");
            } else if (ex instanceof ConstraintViolationException) {
                sb.append("[").append(findWrappedMessage(ex)).append("]");
            } else {
                if (ex.toString() != null) {
                    sb.append("[").append(ex.toString()).append("]");
                }
            }
        } else {
            if (ex instanceof BaseException) {
                BaseException baseException = (BaseException) ex;
                sb.append(parseMessage(baseException));
            } else {
                if (ex.toString() != null) {
                    sb.append(ex.toString());
                }
            }
        }
        messages.add(sb.toString());
        if (loop) {
            findBizzExMessage(ex.getCause(), messages, true, findDetail);
        }
    }

    private String parseMessage(BaseException e) {
        String exLocalMessage = e.getNoWrapMessage();
        if (StringUtils.isEmpty(exLocalMessage)) {
            exLocalMessage = messageSource
                    .getMessage(String.valueOf(e.getCode()), e.getMessageArgs(),
                            e.getNoWrapMessage(), Locale.SIMPLIFIED_CHINESE);
        }
        return exLocalMessage;
    }

    public static String extractPackageName(String fullClassName) {
        if ((null == fullClassName) || ("".equals(fullClassName)))
            return "";

        int lastDot = fullClassName.lastIndexOf('.');
        if (0 >= lastDot)
            return "";
        return fullClassName.substring(0, lastDot);
    }

    public static String extractSimpleClassName(String fullClassName) {
        if ((null == fullClassName) || ("".equals(fullClassName)))
            return "";

        int lastDot = fullClassName.lastIndexOf('.');
        if (0 > lastDot)
            return fullClassName;

        return fullClassName.substring(++lastDot);
    }

    public static String extractDirectClassName(String simpleClassName) {
        if ((null == simpleClassName) || ("".equals(simpleClassName)))
            return "";

        int lastSign = simpleClassName.lastIndexOf('$');
        if (0 > lastSign)
            return simpleClassName;
        return simpleClassName.substring(++lastSign);
    }

    public static String unmungeSimpleClassName(String simpleClassName) {
        if ((null == simpleClassName) || ("".equals(simpleClassName)))
            return "";
        return simpleClassName.replace('$', '.');
    }
}
