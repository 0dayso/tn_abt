package com.tuniu.abt.base.framework;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通用拦截器
 *
 * @author chengyao
 */
public class CorsInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER_INPUT = LoggerFactory.getLogger("abt.request.body");

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 允许跨域ajax请求
        // response.setHeader("Access-Control-Allow-Origin", "*");
        // request body 日志记录
        if (LOGGER_INPUT.isTraceEnabled() && !(request instanceof MultipartRequest)) {
            logRequest(request);
        }
	    return true;
    }

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void logRequest(HttpServletRequest request) throws IOException {
        String method = request.getMethod();
        String body = null;
        if ("POST".equals(method)) {
            body = dealReturnChar(IOUtils.toString(request.getReader()));
        }
        String queryString = dealReturnChar(request.getQueryString());
        LOGGER_INPUT.trace("url:{}, queryString:{}, request:{}", request.getRequestURL(),
                queryString, body);
    }

    private String dealReturnChar(String text) {
        text = StringUtils.remove(text, '\n');
        return StringUtils.remove(text, '\r');
    }

}
