package com.tuniu.abt.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuniu.abt.intf.entity.AbtAopPolicy;
import com.tuniu.abt.mapper.AbtAopPolicyMapper;

@Repository
public class AbtAopPolicyDao {

    @Resource
    private AbtAopPolicyMapper abtAopPolicyMapper;

    public AbtAopPolicy find(Long id) {
        return abtAopPolicyMapper.selectByPrimaryKey(id);
    }

    public Long save(AbtAopPolicy record) {
        abtAopPolicyMapper.insertSelective(record);
        return record.getId();
    }

    public AbtAopPolicy findByPnrAndOrderId(String pnr, Long orderId){
        return abtAopPolicyMapper.findByPnrAndOrderId(pnr, orderId);
    }
}