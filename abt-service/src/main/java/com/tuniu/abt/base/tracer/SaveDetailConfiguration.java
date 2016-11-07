package com.tuniu.abt.base.tracer;

import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.tuniu.abt.base.tracer.pojo.FlatTraceInfo.LEVEL_ACTION;

/**
 * 保存报文过滤配置
 * Created by chengyao on 2016/6/3.
 */
@Component
public class SaveDetailConfiguration implements InitializingBean {

    @Value("${log.saveDetail.includeAction}")
    private String logSaveDetailIncludeAction;
    @Value("${log.saveDetail.includeCmd}")
    private String logSaveDetailIncludeCmd;

    private Set<String> includeAction = new HashSet<>();
    private Set<String> includeCmd = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isNotEmpty(logSaveDetailIncludeAction)) {
            Collections.addAll(includeAction, StringUtils.split(logSaveDetailIncludeAction, ","));
        }
        if (StringUtils.isNotEmpty(logSaveDetailIncludeCmd)) {
            Collections.addAll(includeCmd, StringUtils.split(logSaveDetailIncludeCmd, ","));
        }
    }

    public boolean isSaveDetail(FlatTraceInfo flatTraceInfo) {
        if (LEVEL_ACTION == flatTraceInfo.getLevel()) {
            if (includeAction.contains(flatTraceInfo.getBizAction())) {
                return true;
            } else {
                return false;
            }
        } else {
            StringBuilder sb = new StringBuilder().append(flatTraceInfo.getBizCmd()).append(":")
                    .append(flatTraceInfo.getBizType());
            if (includeCmd.contains(sb.toString())) {
                return true;
            }
        }
        return false;
    }
}
