package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtBookingRequest;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AbtBookingRequestMapper extends Mapper<AbtBookingRequest> {
    List<AbtBookingRequest> queryCurDateRequestsByStatus(int status);
}