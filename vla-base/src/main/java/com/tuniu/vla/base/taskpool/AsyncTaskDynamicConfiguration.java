package com.tuniu.vla.base.taskpool;

import com.tuniu.zkconfig.client.event.ConfigChangeEvent;
import com.tuniu.zkconfig.client.event.ConfigChangeHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * asyncTask的通用配置类
 * [asynctask.][taskName.][propName]=xxx
 *
 * Created by chengyao on 2016/2/22.
 */
public class AsyncTaskDynamicConfiguration implements EnvironmentAware, ApplicationListener<ConfigChangeEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskDynamicConfiguration.class);

    // 允许远程设置的属性
    private static final Set<String> ALLOW_PROP = new HashSet<String>();
    static {
        ALLOW_PROP.add("threadNamePrefix");
        ALLOW_PROP.add("corePoolSize");
        ALLOW_PROP.add("maxPoolSize");
        ALLOW_PROP.add("keepAliveSeconds");
        ALLOW_PROP.add("queueCapacity");
        ALLOW_PROP.add("allowCoreThreadTimeOut");
        ALLOW_PROP.add("threadGroupName");
    }
    // 设置前缀
    private static final String PREFIX = "asynctask.";
    // 默认设置名
    private static final String DEFAULT_TASK_NAME = "default";

    // 保有的异步任务对象
    protected Map<String, AbstractAsyncTask> tasks = new TreeMap<String, AbstractAsyncTask>();

    // 对应任务类的属性设置方法缓存
    private Map<String, Map<String, Method>> methodCache = new HashMap<String, Map<String, Method>>();

    private Environment environment;

    @Override
    public void onApplicationEvent(ConfigChangeEvent event) {
        ConfigChangeHolder holder = (ConfigChangeHolder) event.getConfig();
        String configName = holder.getConfigNmae();
        if (configName.startsWith(PREFIX)) {
            LOGGER.trace("处理异步任务设置：configName={}, old value={}, new value={}",
                    holder.getConfigNmae(), holder.getOldVal(), holder.getNewVal());
            changeAsyncTaskSetting(configName, holder.getNewVal());
        }
    }

    /**
     * 执行task的配置修改
     * @param configName 配置名， 比如 asynctask.SaveTicketInfoAsyncTask.corePoolSize
     * @param value 新的值
     */
    private void changeAsyncTaskSetting(String configName, String value) {
        String[] t = StringUtils.split(configName, '.');
        if (t.length < 3) {
            LOGGER.trace("配置项不正确：configName={}", configName);
            return;
        }
        String taskName = t[1]; // taskName(异步类名)
        String propName = t[2]; // 要修改的属性名

        // taskName为default，表示要修改所有无具体配置项的配置
        // 比如有 BookAsyncTask SaveTicketInfoAsyncTask 2个异步任务，
        // 已在配置中设置了 asynctask.SaveTicketInfoAsyncTask.corePoolSize = 5
        // 但是没有 asynctask.BookAsyncTask.corePoolSize 配置项
        // 那么一旦配置了 asynctask.default.corePoolSize = 9，
        // 那么BookAsyncTask的对应配置就会被修改，SaveTicketInfoAsyncTask不会变动
        if (DEFAULT_TASK_NAME.equals(taskName)) {
            // 默认的属性配置修改，找到所有task对应的prop设置，如果没有则修改。
            for (String tName : tasks.keySet()) {
                String key = PREFIX + tName + "." + propName;
                if (!this.environment.containsProperty(key)) {
                    setProp(configName, value, tName, propName);
                }
            }
        } else {
            setProp(configName, value, taskName, propName);
        }
    }

    /**
     * 给定配置项目名和新值，匹配任务名和属性名，进行配置的修改。如果任务名不存在，则略过
     * @param configName 配置项名
     * @param value 新值
     * @param taskName 任务名
     * @param propName 属性名
     */
    private void setProp(String configName, String value, String taskName, String propName) {
        Object target = tasks.get(taskName);
        if (target != null) {
            Map<String, Method> setter = findSetMethods(target.getClass());
            Method method = setter.get(propName);
            if (method == null) {
                LOGGER.trace("未找到属性设置方法或属性不可设置. propName={}", propName);
            }
            singleSetProp(target, method, value);
        } else {
            LOGGER.trace("未找到异步任务名, taskName={}, configName={}", taskName, configName);
        }
    }

    /**
     * 从远程获取修改已配置的修改项目，进行设置
     * @param target 目标异步任务类实例
     */
    public void loadRemoteSetting(Object target) {
        Map<String, Method> setter = findSetMethods(target.getClass());
        for (Map.Entry<String, Method> entry : setter.entrySet()) {
            // 读取默认配置
            singleSetProp(target, DEFAULT_TASK_NAME, entry.getKey(), entry.getValue());
            // 读取单独配置
            singleSetProp(target, target.getClass().getSimpleName(), entry.getKey(), entry.getValue());
        }
    }


    private void singleSetProp(Object target, String taskName, String propName, Method method) {
        String configName = PREFIX + taskName + "." + propName;
        String value = this.environment.getProperty(configName);
        if (value == null) {
            LOGGER.trace("找不到远程设置, configName={}", configName);
        }
        singleSetProp(target, method, value);
    }

    private void singleSetProp(Object target, Method method, String value) {
        if (StringUtils.isNotEmpty(value)) {
            try {
                Object invokeValue;
                Class<?> clazz = method.getParameterTypes()[0];
                if ("java.lang.String".equals(clazz.getName())) {
                    invokeValue = value;
                } else if ("int".equals(clazz.getName()) || "java.lang.Integer".equals(clazz.getName())) {
                    invokeValue = Integer.parseInt(value);
                } else if ("boolean".equals(clazz.getName()) || "java.lang.Boolean".equals(clazz.getName())) {
                    invokeValue = Boolean.parseBoolean(value);
                } else {
                    return;
                }
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("执行异步任务属性更新: {}#{}={}", target.getClass().getName(),
                            method.getName(), invokeValue);
                }
                method.invoke(target, invokeValue);
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("执行异步任务属性更新异常, message=" + e.getMessage() + ", "
                            + "prop=" + target.getClass().getName() + "#" + method.getName() + ", "
                            + "value=" + value);
                }
            }
        }
    }

    // 获取实例的有效属性setter方法集合
    private Map<String, Method> findSetMethods(Class<?> clazz) {
        String cacheKey = clazz.getName();
        Map<String, Method> result = methodCache.get(cacheKey);
        if (result != null) {
            return result;
        }

        result = new HashMap<String, Method>();
        for (Method method : clazz.getMethods()) {
            String methodName = method.getName();
            if (methodName.length() < 4) {
                continue;
            }
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            if (!method.getReturnType().equals(Void.TYPE)) {
                continue;
            }
            if (method.getParameterTypes().length != 1) {
                continue;
            }
            if (methodName.startsWith("set") && Character.isUpperCase(methodName.charAt(3))) {
                String propertyName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                if (ALLOW_PROP.contains(propertyName)) {
                    result.put(propertyName, method);
                    method.setAccessible(true);
                }
            }
        }
        methodCache.put(cacheKey, result);
        return result;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
