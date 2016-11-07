package com.tuniu.abt.intf.dto.aop.airline;

import java.io.Serializable;

public class AopPassengerInfo implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = -8166124609476484346L;

    /**
     * 乘机人姓名(实际占位pnr名)
     */
    private String name;
    
    /**
     * 证件号
     */
    private String idNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
}
