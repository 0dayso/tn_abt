package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtBackMealAlertRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

@Repository
public interface AbtBackMealAlertRecordMapper extends Mapper<AbtBackMealAlertRecord>,
        InsertListMapper<AbtBackMealAlertRecord> {
}