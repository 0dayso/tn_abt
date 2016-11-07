package com.tuniu.abt.intf.dto.ticket;

/**
 * 航信出票供应商
 * Created by lanlugang on 2016/5/23.
 */
public enum TravelSkyIssueSolution {

    BSP("8594","南京途之旅票务服务有限公司"),
    BOP("18884","机票/BOP出票专用账户");

    private String solutionId;

    private String solutionName;

    TravelSkyIssueSolution(String solutionId, String solutionName) {
        this.solutionId = solutionId;
        this.solutionName = solutionName;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public String getSolutionName() {
        return solutionName;
    }

}
