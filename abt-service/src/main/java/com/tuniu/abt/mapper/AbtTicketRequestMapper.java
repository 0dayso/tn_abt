package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtTicketRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AbtTicketRequestMapper extends Mapper<AbtTicketRequest> {

    int batchUpdateStatusById(@Param("status") int status, @Param("ids") List<Long> ids);

    int batchUpdateCallbackStatusById(int callbackStatus, List<Long> ids);

}