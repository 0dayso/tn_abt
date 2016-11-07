package com.tuniu.vla.base.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.DefaultCorsProcessor;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改默认的corsProcessor，解决原处理方法对途牛的GET请求带json字符串解析失败的问题。
 * Created by chengyao on 2016/4/25.
 */
public class SafeDefaultCorsProcessor extends DefaultCorsProcessor implements BeanPostProcessor {

    @Override
    public boolean processRequest(CorsConfiguration config, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (!CorsUtils.isCorsRequest(request)) {
            return true;
        }

        ServletServerHttpResponse serverResponse = new ServletServerHttpResponse(response);
        ServletServerHttpRequest serverRequest = new ServletServerHttpRequest(request);

        if (isSameOrigin(request)) {
            return true;
        }
        if (responseHasCors(serverResponse)) {
            return true;
        }

        boolean preFlightRequest = CorsUtils.isPreFlightRequest(request);
        if (config == null) {
            if (preFlightRequest) {
                rejectRequest(serverResponse);
                return false;
            }
            else {
                return true;
            }
        }

        return handleInternal(serverRequest, serverResponse, config, preFlightRequest);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractHandlerMapping) {
            AbstractHandlerMapping abstractHandlerMapping = (AbstractHandlerMapping) bean;
            abstractHandlerMapping.setCorsProcessor(this);
        }
        return bean;
    }



    private boolean responseHasCors(ServerHttpResponse response) {
        boolean hasAllowOrigin = false;
        try {
            hasAllowOrigin = (response.getHeaders().getAccessControlAllowOrigin() != null);
        }
        catch (NullPointerException npe) {
            // SPR-11919 and https://issues.jboss.org/browse/WFLY-3474
        }
        return hasAllowOrigin;
    }

    /**
     * Check if the request is a same-origin one, based on {@code Origin}, {@code Host},
     * {@code Forwarded} and {@code X-Forwarded-Host} headers.
     * @return {@code true} if the request is a same-origin one, {@code false} in case
     * of cross-origin request.
     * @since 4.2
     */
    public static boolean isSameOrigin(HttpServletRequest httpServletRequest) {
        String origin = httpServletRequest.getHeader(HttpHeaders.ORIGIN);
        if (origin == null) {
            return true;
        }
        UriComponents actualUrl = UriComponentsBuilder.fromHttpUrl(httpServletRequest.getRequestURL().toString()).build();
        UriComponents originUrl = UriComponentsBuilder.fromOriginHeader(origin).build();
        return (actualUrl.getHost().equals(originUrl.getHost()) && getPort(actualUrl) == getPort(originUrl));
    }

    private static int getPort(UriComponents component) {
        int port = component.getPort();
        if (port == -1) {
            if ("http".equals(component.getScheme()) || "ws".equals(component.getScheme())) {
                port = 80;
            }
            else if ("https".equals(component.getScheme()) || "wss".equals(component.getScheme())) {
                port = 443;
            }
        }
        return port;
    }
}
