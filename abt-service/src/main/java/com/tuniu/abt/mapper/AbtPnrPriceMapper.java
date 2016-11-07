package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.entity.AbtPnrPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AbtPnrPriceMapper extends Mapper<AbtPnrPrice> {

    AbtPnrPrice findByPnrAndPriceType(@Param("pnr") String pnr, @Param("priceType") int priceType);

    List<AbtPnrPrice> findByPnrAndFlightItemIdAndOrderId(@Param("pnr") String pnr, @Param("flightItemId") Long flightItemId, @Param("orderId") Long orderId);

    AbtPnrPrice findByPnrAndPriceTypeAndFlightItemIdAndOrderId(@Param("pnr") String pnr, @Param("priceType") Integer priceType, @Param("flightItemId") Long flightItemId, @Param("orderId") Long orderId);
}