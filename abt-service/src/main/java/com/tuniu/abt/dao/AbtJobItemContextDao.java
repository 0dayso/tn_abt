package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtJobItemContext;
import com.tuniu.abt.mapper.AbtJobItemContextMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by huangsizhou on 16/3/29.
 */
@Repository
public class AbtJobItemContextDao {
    @Resource
    private AbtJobItemContextMapper abtJobItemContextMapper;

    public AbtJobItemContext findByJobName(String jobName) {
        AbtJobItemContext abtJobItemContext = new AbtJobItemContext();
        abtJobItemContext.setJobName(jobName);
        return abtJobItemContextMapper.selectOne(abtJobItemContext);
    }
}
