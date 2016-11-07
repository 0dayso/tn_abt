package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.dto.common.MaliceFlightDto;
import com.tuniu.abt.intf.entity.AbtPnrFlight;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

@Repository
public interface AbtPnrFlightMapper extends Mapper<AbtPnrFlight> {
    AbtPnrFlight findByPnrAndFlightNo(@Param("pnr") String pnr, @Param("flightNo") String flightNo);

    int saveAbtPnrFlightList(List<AbtPnrFlight> flights);

    List<AbtPnrFlight> findByPnrAndFlightItemIdAndOrderId(@Param("pnr") String pnr,
            @Param("flightItemId") Long flightItemId, @Param("orderId") Long orderId);

    List<MaliceFlightDto> queryMaliceBookingFlights(@Param("maliceNum") int maliceNum,
            @Param("start") Date start, @Param("end") Date end);

    List<MaliceFlightDto> queryMaliceBookingFlightsByDeparture(@Param("maliceNum") int maliceNum,
            @Param("date") Date date);

    List<MaliceFlightDto> queryMaliceBookingFlightsForAlert(@Param("maliceNum") int maliceNum,
            @Param("beginTime") Date beginTime, @Param("appointTime") String appointTime);
}