package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

@Repository
public interface AbtPnrMainMapper extends Mapper<AbtPnrMain> {

    List<AbtPnrMain> findPnrMainList(List<String> pnrList);

    List<AbtPnrMain> findByFlightItemIdAndOrderId(@Param("flightItemId") Long flightItemId, @Param("orderId") Long orderId);

    int updatePayStatus(@Param("flightItemId") Long flightItemId, @Param("orderId") Long orderId, @Param("pnr") String pnr, @Param("status") int status);

    List<AbtPnrMain> findByStatusAndSpecificTimeInterval(@Param("status") int status, @Param("from") Date from, @Param("to") Date to);

    int updateStatusByOrderIdAndPnr(@Param("orderId") Long orderId, @Param("pnr") String pnr, @Param("status") int status);

    List<AbtTicketMain> buildAbtTicketMainByBookData(@Param("orderId") Long orderId, @Param("flightItemId") Long flightItemId);
}