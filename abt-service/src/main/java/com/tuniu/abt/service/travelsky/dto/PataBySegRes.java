package com.tuniu.abt.service.travelsky.dto;

import com.tuniu.abt.intf.dto.book.main.Segment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanlugang on 2016/4/27.
 */
public class PataBySegRes<T> {

    public static String PATA_PSG_TYPE_ADT = "ADT";

    public static String PATA_PSG_TYPE_CHD = "CHD";

    public static String PATA_PSG_TYPE_INF = "INF";

    /**
     * 运价类型：ADT成人，CHD儿童，INF婴儿
     */
    private String psgType;

    /**
     * 请求的航段信息
     */
    private List<Segment> segments;

    /**
     * 最低运价
     */
    private PnrFareItem fareItem;

    /**
     * 所有运价List
     */
    private List<PnrFareItem> fareItems;

    /**
     * 航信接口返回的原始数据
     */
    private T pataResult;

    public String getPsgType() {
        return psgType;
    }

    public void setPsgType(String psgType) {
        this.psgType = psgType;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public PnrFareItem getFareItem() {
        return fareItem;
    }

    public void setFareItem(PnrFareItem fareItem) {
        this.fareItem = fareItem;
    }

    public List<PnrFareItem> getFareItems() {
        if (null == fareItems) {
            fareItems = new ArrayList<PnrFareItem>();
        }
        return fareItems;
    }

    public void setFareItems(List<PnrFareItem> fareItems) {
        this.fareItems = fareItems;
    }

    public T getPataResult() {
        return pataResult;
    }

    public void setPataResult(T pataResult) {
        this.pataResult = pataResult;
    }
}
