package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtTracerLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface AbtTracerLogMapper extends Mapper<AbtTracerLog>, MySqlMapper<AbtTracerLog> {

}