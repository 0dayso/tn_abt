package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AbtPnrPassengerMapper extends Mapper<AbtPnrPassenger> {
    
    List<AbtPnrPassenger> findByPnrAndFlightItemIdAndOrderId(@Param("pnr") String pnr, @Param("flightItemId") Long flightItemId, @Param("orderId") Long orderId);

    AbtPnrPassenger findByPnrAndFlightItemIdAndOrderIdAndName(@Param("pnr") String pnr, @Param("flightItemId") Long flightItemId, @Param("orderId") Long orderId, @Param("name") String name);

    AbtPnrPassenger findByOrderIdAndPnrAndPersonId(@Param("orderId") Long orderId, @Param("pnr") String pnr, @Param("personId") Long personId);

}