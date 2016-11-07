package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtJobItemExecutionContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AbtJobItemExecutionContextMapper extends Mapper<AbtJobItemExecutionContext> {
    AbtJobItemExecutionContext findByEntryIdAndJobName(@Param("entryId") Long entryId, @Param("jobName") String jobName);
}