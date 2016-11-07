package com.tuniu.vla.base.tsp;

import com.tuniu.operation.platform.tsg.client.config.ConsumerConfig;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.vla.base.tsp.intf.TspInterface;
import com.tuniu.vla.base.utils.ClassUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * TSP CLIENT 接口搜索
 *
 * 通过搜索TspMethod注解，自动把服务名加入RefferenceService
 *
 *
 *
 * Created by chengyao on 2016/2/14.
 */
public class TspClientScannerConfigurer extends ConsumerConfig implements InitializingBean,
        BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private String basePackage;

    private String tspClientInvocationListenerName;

    private ApplicationContext applicationContext;

    /**
     * This property lets you set the base package for your tsp client interface files.
     * <p>
     * You can set more than one package by using a semicolon or comma as a separator.
     * <p>
     * Mappers will be searched for recursively starting in the specified package(s).
     *
     * @param basePackage base package name
     */
    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setTspClientInvocationListenerName(String tspClientInvocationListenerName) {
        this.tspClientInvocationListenerName = tspClientInvocationListenerName;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        TspClassPathInterfaceScanner scanner = new TspClassPathInterfaceScanner(registry);
        scanner.setTspClientInvocationListenerName(this.tspClientInvocationListenerName);
        scanner.setMarkerInterface(TspInterface.class);
        scanner.setResourceLoader(this.applicationContext);
        scanner.registerFilters();
        scanner.scan(StringUtils
                .tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // left intentionally blank
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // 通过搜索TspMethod注解，自动把服务名加入RefferenceService
    @Override
    public void afterPropertiesSet() throws Exception {
        Set<String> serviceNames = new HashSet<String>();
        Set<Class<?>> classes = ClassUtils.findClasses(basePackage, true);
        for (Class<?> clazz : classes) {
            if (clazz.isInterface() && TspInterface.class.isAssignableFrom(clazz)) {
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    TspMethod tspMethod = method.getAnnotation(TspMethod.class);
                    if (tspMethod != null) {
                        serviceNames.add(tspMethod.serviceName());
                    }
                }
            }
        }
        if (this.getRefferenceService() == null) {
            this.setRefferenceService(new ArrayList<String>());
        }
        for (String serviceName : serviceNames) {
            this.getRefferenceService().add(serviceName);
        }
    }
}
