package com.tuniu.abt.intf.tsp.dto.adt;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.tuniu.adapter.flightTicket.vo.dfpPolicy.DetailFligthSegVo;

public class FreightCalculateRequestVo {

    private int journeyType;

    private int systemId;

    private int solutionId;

    // 航信和携程实际的价格
    private int price;

    @NotEmpty(message = "FreightService.qryDetailPrice.segVo.notEmpty")
    private List<DetailFligthSegVo> flightSegList = new ArrayList<DetailFligthSegVo>();

    public int getJourneyType() {
        return journeyType;
    }

    public void setJourneyType(int journeyType) {
        this.journeyType = journeyType;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<DetailFligthSegVo> getFlightSegList() {
        return flightSegList;
    }

    public void setFlightSegList(List<DetailFligthSegVo> flightSegList) {
        this.flightSegList = flightSegList;
    }

}
