package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtTracerLogDetail;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AbtTracerLogDetailMapper extends Mapper<AbtTracerLogDetail> {

    int insertBatch(List<AbtTracerLogDetail> list);
}