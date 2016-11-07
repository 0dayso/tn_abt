package com.tuniu.vla.base.framework;

import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.base.core.method.BeanWrapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
/**
 * returnValue ResponseJson 处理器
 * 
 * @copyright(C) 2006-2012 Tuniu All rights reserved
 * @author xiehui
 */
public class ResponseJsonMethodProcessor implements HandlerMethodReturnValueHandler, InitializingBean {

    private HttpMessageConverter messageConverter;

    private List<BeanWrapper> beanWrappers;

    public List<BeanWrapper> getBeanWrappers() {
        return beanWrappers;
    }

    public void setBeanWrappers(List<BeanWrapper> beanWrappers) {
        this.beanWrappers = beanWrappers;
    }

    public HttpMessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(HttpMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getMethodAnnotation(ResponseJson.class) != null;
    }

    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws Exception {
        Object result = returnValue;	
        mavContainer.setRequestHandled(true);
        ServletServerHttpResponse outputMessage = createOutputMessage(webRequest);

        for (BeanWrapper beanWrapper : beanWrappers) {
            if (beanWrapper.supportsType(returnType)) {
                result = beanWrapper.wrap(returnValue);
                break;
            }
        }

        //返回json+base64
        messageConverter.write(result,
            new MediaType(MediaType.APPLICATION_JSON, Collections.singletonMap("charset", "UTF-8")), outputMessage);
    }

    protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        return new ServletServerHttpResponse(response);
    }

    public void afterPropertiesSet() throws Exception {
        if (beanWrappers == null || beanWrappers.size() == 0) {
            throw new Exception("beanWrappers undefined");
        }
        // sortWrapper();
    }
}
