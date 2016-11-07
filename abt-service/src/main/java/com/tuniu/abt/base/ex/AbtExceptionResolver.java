package com.tuniu.abt.base.ex;

import com.tuniu.abt.base.tracer.TracerProcessor;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import com.tuniu.vla.base.framework.CommonExceptionResolver;
import com.tuniu.vla.base.framework.resp.ApiResp;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 * <p/>
 * Created by chengyao on 2015/11/19.
 */
public class AbtExceptionResolver extends CommonExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbtExceptionResolver.class);

    @Resource
    private TracerProcessor tracerProcessor;

    @Override
    public ModelAndView doResolveException(HttpServletRequest request,
                                           HttpServletResponse response, Object handler, Exception ex) {

        // API_RESP_WRAP 类型错误，直接输出异常中带的ApiResp参数，用于自定义错误输出
        if (ex instanceof BizzException) {
            BizzException bizzException = (BizzException) ex;
            if (BizzEx.API_RESP_WRAP == bizzException.getCode()) {
                ModelAndView modelAndView = doResolveApiResp(response, (ApiResp) bizzException.getMessageArgs()[0]);
                tracerProcessor.actionFail(exceptionMessageUtils.findCodeString(ex),
                        exceptionMessageUtils.findAllExString(ex));
                return modelAndView;
            }
        }

        if (!(ex instanceof ConstraintViolationException)) {
            LOGGER.error(ex.getMessage(), ex);
        }

        ModelAndView modelAndView = super.doResolveException(request, response, handler, ex);
        tracerProcessor.actionFail(exceptionMessageUtils.findCodeString(ex),
                exceptionMessageUtils.findAllExString(ex));
        return modelAndView;
    }


    protected void write(HttpServletResponse response, boolean success, String code, String msg, Object data) {
        PrintWriter out = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", success);

            String transId = DataSharedSupport.getTransId();
            if (transId != null) {
                map.put("transId", transId);
            }
            map.put("systemId", BizzConstants.SYSTEM_ME);
            map.put("intl", 0);

            if (msg != null) map.put("msg", msg);
            if (code != null) map.put("errorCode", code);
            if (data != null) map.put("data", data);

            String t = JsonUtil.toString(map);
            FlatTraceInfo action = DataSharedSupport.getTracer();
            if (action != null) {
                action.setOutputResult(t);
            }

            String resStr = Base64.encodeBase64String(t.getBytes("utf-8"));
            //response.setHeader("Access-Control-Allow-Origin", "*");
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

    public ModelAndView doResolveApiResp(HttpServletResponse response, ApiResp resp) {
        write(response, resp.getSuccess(), String.valueOf(resp.getCode()), resp.getMsg(), resp.getData());
        return null;
    }

}
