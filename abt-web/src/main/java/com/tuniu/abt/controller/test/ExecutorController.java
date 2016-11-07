package com.tuniu.abt.controller.test;

import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by chengyao on 2016/6/7.
 */
@Controller
@RequestMapping("/test/")
@Profile("local")
public class ExecutorController implements ApplicationContextAware {

    ApplicationContext applicationContext;


    @RequestMapping(value = "/executor", method = RequestMethod.GET)
    @ResponseJson
    public Object executor(@Json InvokeObj invokeObj) throws InvocationTargetException, IllegalAccessException {

        String beanName = invokeObj.getBean();
        String methodName = invokeObj.getMethod();
        Object[] param = invokeObj.getParam();

        Object o = applicationContext.getBean(beanName);
        Method method = ReflectionUtils.findMethod(o.getClass(), methodName);
        Object result = method.invoke(o, param);
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    static class InvokeObj {
        public InvokeObj() {

        }
        private String bean;

        private String method;

        private Object[] param;

        public String getBean() {
            return bean;
        }

        public void setBean(String bean) {
            this.bean = bean;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Object[] getParam() {
            return param;
        }

        public void setParam(Object[] param) {
            this.param = param;
        }
    }
}
