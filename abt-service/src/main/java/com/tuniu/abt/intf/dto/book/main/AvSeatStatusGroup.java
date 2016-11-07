package com.tuniu.abt.intf.dto.book.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanlugang on 2016/5/5.
 */
public class AvSeatStatusGroup implements Serializable {

    private static final long serialVersionUID = 1231076893720810929L;
    /**
     * 舱位组合的打包价格(总价)
     */
    private double refPrice;

    /**
     * 余位信息
     */
    private List<AvSeatStatusDto> avSeatStatusDtos;

    /**
     * 儿童是否使用成人运价
     */
    private boolean chdUseAdtPrice;

    /**
     * 开放平台政策id
     */
    private Long policyId = new Long(0);

    public double getRefPrice() {
        return refPrice;
    }

    public void setRefPrice(double refPrice) {
        this.refPrice = refPrice;
    }

    public List<AvSeatStatusDto> getAvSeatStatusDtos() {
        if (null == avSeatStatusDtos) {
            avSeatStatusDtos = new ArrayList<AvSeatStatusDto>();
        }
        return avSeatStatusDtos;
    }

    public void setAvSeatStatusDtos(List<AvSeatStatusDto> avSeatStatusDtos) {
        this.avSeatStatusDtos = avSeatStatusDtos;
    }

    public boolean isChdUseAdtPrice() {
        return chdUseAdtPrice;
    }

    public void setChdUseAdtPrice(boolean chdUseAdtPrice) {
        this.chdUseAdtPrice = chdUseAdtPrice;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }
}
