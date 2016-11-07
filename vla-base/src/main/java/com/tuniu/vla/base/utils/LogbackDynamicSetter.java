package com.tuniu.vla.base.utils;

import com.tuniu.zkconfig.client.event.ConfigChangeEvent;
import com.tuniu.zkconfig.client.event.ConfigChangeHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * 监听配置变更，修改logback 日志级别
 * <p>
 * Created by chengyao on 2016/5/21.
 */
public class LogbackDynamicSetter implements ApplicationListener<ConfigChangeEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackDynamicSetter.class);

    /**
     * Dynamically sets the logback log level for the given class to the specified level.
     *
     * @param loggerName Name of the logger to set its log level. If blank, root logger will be used.
     * @param logLevel   One of the supported log levels: TRACE, DEBUG, INFO, WARN, ERROR, FATAL,
     *                   OFF. {@code null} value is considered as 'OFF'.
     */
    private static void setLogLevel(String loggerName, String logLevel) {
        LOGGER.debug("修改日志级别：{} -> {}", loggerName, logLevel);
        final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(loggerName);
        if (!(logger instanceof ch.qos.logback.classic.Logger)) {
            return;
        }
        ch.qos.logback.classic.Logger logbackLogger = (ch.qos.logback.classic.Logger) logger;
        ch.qos.logback.classic.Level level = ch.qos.logback.classic.Level.valueOf(logLevel);
        logbackLogger.setLevel(level);
    }

    @Override
    public void onApplicationEvent(ConfigChangeEvent event) {
        ConfigChangeHolder holder = (ConfigChangeHolder) event.getConfig();
        String configName = holder.getConfigNmae();

        if (StringUtils.startsWith(holder.getConfigNmae(), "logback.")) {
            if (StringUtils.isEmpty(holder.getNewVal())) return;
            try {
                String loggerName = StringUtils.removeStart(configName, "logback.");
                setLogLevel(loggerName, holder.getNewVal());
            } catch (Exception ex) {
                LOGGER.warn("修改日志级别失败" + ex.getMessage(), ex);
            }
        }
    }

}
