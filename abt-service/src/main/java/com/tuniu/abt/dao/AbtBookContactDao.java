package com.tuniu.abt.dao;

import org.springframework.stereotype.Repository;

import com.tuniu.abt.intf.entity.AbtBookContact;
import com.tuniu.abt.mapper.AbtBookContactMapper;

import javax.annotation.Resource;

@Repository
public class AbtBookContactDao {

    @Resource
    private AbtBookContactMapper abtBookContactMapper;
    
    public int save(AbtBookContact record){
        return abtBookContactMapper.insertSelective(record);
    }
}