package com.tuniu.abt.intf.dto.book.response;

import java.io.Serializable;

public class PassengerInfo implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = -8166124609476484346L;

    public PassengerInfo() {
    }

    public PassengerInfo(String name) {
        this.name = name;
    }

    public PassengerInfo(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

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
