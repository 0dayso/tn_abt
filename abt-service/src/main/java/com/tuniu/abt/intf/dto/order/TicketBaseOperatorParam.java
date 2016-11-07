package com.tuniu.abt.intf.dto.order;

import java.io.Serializable;

/**
 * 操作类接口父类
 * Created by ysma on 2015/12/18.
 */
public class TicketBaseOperatorParam implements Serializable{

    private static final long serialVersionUID = 4827281558028332572L;

    private String operatorId;

    private String operatorName;

    private Integer id;

    private Integer vendorId;

    private String source;

    /**
     * 审核拒绝原因：1=非合理拒绝，9=其他
     */
    private Integer reason;

    /**
     * 审核备注--备注说明
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
