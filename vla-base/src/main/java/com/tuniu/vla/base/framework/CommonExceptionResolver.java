package com.tuniu.vla.base.framework;

import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 *
 * Created by chengyao on 2015/11/19.
 */
public class CommonExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionResolver.class);

    protected ExceptionMessageUtils exceptionMessageUtils;

    public CommonExceptionResolver() {

    }

    public void setExceptionMessageUtils(ExceptionMessageUtils exceptionMessageUtils) {
        this.exceptionMessageUtils = exceptionMessageUtils;
    }

    @Override
    public ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        String code = exceptionMessageUtils.findCodeString(ex);
        String msg = exceptionMessageUtils.findWrappedMessage(ex);
        LOGGER.error("code[{}], message: {}", code, exceptionMessageUtils.findAllEx(ex));
        write(response, false,  code, msg, null);
        return null;
    }

    protected void write(HttpServletResponse response, boolean success, String code, String msg, Object data) {
        PrintWriter out = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", success);
            if (msg != null) map.put("msg", msg);
            if (code != null) map.put("errorCode", code);
            if (data != null) map.put("data", data);

            String resStr = Base64.encodeBase64String(JsonUtil.toString(map).getBytes("utf-8"));
            response.setHeader("Content-Type","application/json;charset=UTF-8");
            out = response.getWriter();
            out.write(resStr);
        } catch (Exception ex) {
            LOGGER.error("error on exception wrapper output.", ex);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

}
