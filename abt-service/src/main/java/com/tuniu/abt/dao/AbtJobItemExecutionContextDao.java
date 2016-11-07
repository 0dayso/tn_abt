package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtJobItemExecutionContext;
import com.tuniu.abt.mapper.AbtJobItemExecutionContextMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by huangsizhou on 16/3/29.
 */
@Repository
public class AbtJobItemExecutionContextDao {

    @Resource
    private AbtJobItemExecutionContextMapper abtJobItemExecutionContextMapper;

    public AbtJobItemExecutionContext findByEntryIdAndJobName(Long entryId, String jobName){
        return abtJobItemExecutionContextMapper.findByEntryIdAndJobName(entryId, jobName);
    }

    public int save(AbtJobItemExecutionContext abtJobItemExecutionContext){
        return abtJobItemExecutionContextMapper.insertSelective(abtJobItemExecutionContext);
    }

    public int updateRetryCountById(Long id, int retryCount){
        AbtJobItemExecutionContext abtJobItemExecutionContext = new AbtJobItemExecutionContext();
        abtJobItemExecutionContext.setId(id);
        abtJobItemExecutionContext.setRetryCount(retryCount);
        return abtJobItemExecutionContextMapper.updateByPrimaryKeySelective(abtJobItemExecutionContext);
    }
}
