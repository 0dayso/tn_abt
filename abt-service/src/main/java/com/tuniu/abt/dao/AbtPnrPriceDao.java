package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtPnrPrice;
import com.tuniu.abt.mapper.AbtPnrPriceMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AbtPnrPriceDao {

    @Resource
    private AbtPnrPriceMapper abtPnrPriceMapper;

    public Long save(AbtPnrPrice record) {
        abtPnrPriceMapper.insertSelective(record);
        return record.getId();
    }

    public AbtPnrPrice findByMainPnrIdAndPassengerType(Long pnrId, Integer priceType) {
        AbtPnrPrice pnrPrice = new AbtPnrPrice();
        pnrPrice.setPnrId(pnrId);
        pnrPrice.setPriceType(priceType);

        return abtPnrPriceMapper.selectOne(pnrPrice);
    }

    public List<AbtPnrPrice> findByPnrAndFlightItemIdAndOrderId(String pnr, Long flightItemId, Long orderId) {
        return abtPnrPriceMapper.findByPnrAndFlightItemIdAndOrderId(pnr, flightItemId, orderId);
    }

    public AbtPnrPrice findByPnrAndPriceTypeAndFlightItemIdAndOrderId(String pnr, Integer priceType, Long flightItemId, Long orderId) {
        return abtPnrPriceMapper.findByPnrAndPriceTypeAndFlightItemIdAndOrderId(pnr, priceType, flightItemId, orderId);
    }
}