package com.tuniu.vla.base.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * RequestMappingHandlerAdapter重置处理类
 * 
 * @copyright(C) 2006-2012 Tuniu All rights reserved
 * @author xiehui
 */
public class RequestMappingHandlerPostProcessor implements BeanPostProcessor {

    private List<Class<HandlerMethodArgumentResolver>> removedArgumentResolvers = new ArrayList<Class<HandlerMethodArgumentResolver>>();
    private List<Class<HandlerMethodReturnValueHandler>> removedReturnValueHandlers = new ArrayList<Class<HandlerMethodReturnValueHandler>>();

    public List<Class<HandlerMethodArgumentResolver>> getRemovedArgumentResolvers() {
        return removedArgumentResolvers;
    }

    public void setRemovedArgumentResolvers(List<Class<HandlerMethodArgumentResolver>> removedArgumentResolvers) {
        this.removedArgumentResolvers = removedArgumentResolvers;
    }

    public List<Class<HandlerMethodReturnValueHandler>> getRemovedReturnValueHandlers() {
        return removedReturnValueHandlers;
    }

    public void setRemovedReturnValueHandlers(List<Class<HandlerMethodReturnValueHandler>> removedReturnValueHandlers) {
        this.removedReturnValueHandlers = removedReturnValueHandlers;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
            removeArgumentResolvers(adapter.getArgumentResolvers(), adapter);
            removeReturnValueHandlers(adapter.getReturnValueHandlers(), adapter);
        }
        return bean;
    }

    /**
     * 移除指定的系统默认加载ArgumentResolver
     * 
     * @param argumentResolvers
     */
    private void removeArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers,
            RequestMappingHandlerAdapter adapter) {
        if (removedArgumentResolvers == null || removedArgumentResolvers.size() == 0)
            return;
        List<HandlerMethodArgumentResolver> resolvers = new ArrayList<HandlerMethodArgumentResolver>();
        for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
            if (removedArgumentResolvers.contains(argumentResolver.getClass())) {
                continue;
            }
            resolvers.add(argumentResolver);
        }
        adapter.setArgumentResolvers(resolvers);
    }

    /**
     * 移除指定的系统默认加载的MehtodReturnValueHandler
     * 
     * @param returnValueHandlers
     */
    private void removeReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers,
            RequestMappingHandlerAdapter adapter) {
        if (removedReturnValueHandlers == null || removedReturnValueHandlers.size() == 0)
            return;
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<HandlerMethodReturnValueHandler>();
        for (HandlerMethodReturnValueHandler returnValueHandler : returnValueHandlers) {
            if (removedReturnValueHandlers.contains(returnValueHandler.getClass())) {
                continue;
            }
            handlers.add(returnValueHandler);
        }
        adapter.setReturnValueHandlers(handlers);
    }

}
