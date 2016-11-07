package com.tuniu.abt.intf.dto.cancel;

import com.travelsky.espeed.OTAAirResRetRS;
import com.tuniu.abt.service.travelsky.dto.PassengerItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chengyao on 2016/4/9.
 */
public class RtForDelete extends OTAAirResRetRS.AirResRet implements Serializable {

    private static final long serialVersionUID = 3016531207127433834L;

    // 删除后是否还剩余有婴儿
    private boolean surplusBabyFlag = false;

    // 要删除的行项目
    private Set<String> delRphs = new HashSet<String>();

    // 要删除的乘客项目
    private List<PassengerItem> delPassengerItems = new ArrayList<PassengerItem>();

    public Set<String> getDelRphs() {
        return delRphs;
    }

    public void setDelRphs(Set<String> delRphs) {
        this.delRphs = delRphs;
    }

    public boolean isSurplusBabyFlag() {
        return surplusBabyFlag;
    }

    public void setSurplusBabyFlag(boolean surplusBabyFlag) {
        this.surplusBabyFlag = surplusBabyFlag;
    }

    public List<PassengerItem> getDelPassengerItems() {
        return delPassengerItems;
    }

    public void setDelPassengerItems(List<PassengerItem> delPassengerItems) {
        this.delPassengerItems = delPassengerItems;
    }
}
