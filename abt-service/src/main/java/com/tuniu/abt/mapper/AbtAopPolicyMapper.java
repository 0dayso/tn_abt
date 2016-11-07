package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtAopPolicy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AbtAopPolicyMapper extends Mapper<AbtAopPolicy> {

    AbtAopPolicy findByPnrAndOrderId(@Param("pnr") String pnr, @Param("orderId") Long orderId);

}