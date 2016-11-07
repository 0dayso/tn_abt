package com.tuniu.abt.base.tracer;

import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.intf.entity.AbtTracerLog;
import com.tuniu.abt.intf.entity.AbtTracerLogDetail;
import com.tuniu.abt.mapper.AbtTracerLogDetailMapper;
import com.tuniu.abt.mapper.AbtTracerLogMapper;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.vla.base.asyncexecutor.AbstractAsyncQueuedHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedResource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Tracer日志记入数据库
 *
 * Created by chengyao on 2016/2/15.
 */
@ManagedResource(objectName= "com.tuniu.abt:type=AsyncQueuedExecutor,name=DbTracerLogHandler" , description= "日志入库" )
public class DbTracerLogHandler extends AbstractAsyncQueuedHandler<FlatTraceInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbTracerLogHandler.class);

    @Resource
    private AbtTracerLogMapper abtTracerLogMapper;
    @Resource
    private AbtTracerLogDetailMapper abtTracerLogDetailMapper;
    @Resource
    private SaveDetailConfiguration saveDetailConfiguration;


    @Override
    public void execute(FlatTraceInfo object) {
        AbtTracerLog abtTracerLog = toTracerLog(object);
        abtTracerLogMapper.insertSelective(abtTracerLog);

        if (saveDetailConfiguration.isSaveDetail(object)) {
            AbtTracerLogDetail abtTracerLogDetail = toTracerLogDetail(abtTracerLog.getId(), object);
            abtTracerLogDetailMapper.insertSelective(abtTracerLogDetail);
        }
    }

    @Override
    public void executeBatch(List<FlatTraceInfo> objects) throws Exception {
        List<AbtTracerLog> abtTracerLogs = CommonUtils.transformList(objects, this::toTracerLog);
        abtTracerLogMapper.insertList(abtTracerLogs);

        int size = objects.size();
        List<AbtTracerLogDetail> abtTracerLogDetails = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            FlatTraceInfo flatTraceInfo = objects.get(i);
            if (saveDetailConfiguration.isSaveDetail(flatTraceInfo)) {
                long id = abtTracerLogs.get(i).getId();
                AbtTracerLogDetail abtTracerLogDetail = toTracerLogDetail(id, flatTraceInfo);
                abtTracerLogDetails.add(abtTracerLogDetail);
            }
        }
        if (CollectionUtils.isNotEmpty(abtTracerLogDetails)) {
            abtTracerLogDetailMapper.insertBatch(abtTracerLogDetails);
        }
    }

    private AbtTracerLog toTracerLog(FlatTraceInfo flatTraceInfo) {
        AbtTracerLog abtTracerLog = CommonUtils.transform(flatTraceInfo, AbtTracerLog.class);
        if (StringUtils.length(abtTracerLog.getErrorDesc()) > 1000) {
            abtTracerLog.setErrorDesc(abtTracerLog.getErrorDesc().substring(0, 1000));
        }
        if (StringUtils.length(abtTracerLog.getBizType()) > 100) {
            abtTracerLog.setBizType(abtTracerLog.getBizType().substring(0, 100));
        }
        return abtTracerLog;
    }

    private AbtTracerLogDetail toTracerLogDetail(Long id, FlatTraceInfo flatTraceInfo) {
        AbtTracerLogDetail abtTracerLogDetail = new AbtTracerLogDetail();
        abtTracerLogDetail.setId(id);
        abtTracerLogDetail.setInputParam(flatTraceInfo.getInputParam() == null ? StringUtils.EMPTY : flatTraceInfo.getInputParam());
        abtTracerLogDetail.setOutputResult(flatTraceInfo.getOutputResult() == null ? StringUtils.EMPTY : flatTraceInfo.getOutputResult());
        return abtTracerLogDetail;
    }

}
