package com.tuniu.abt.intf.dto.aop;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 操作结果通知子项目
 *
 * Created by chengyao on 2015/12/5.
 */
public class AopFeedbackItem implements Serializable {

    private static final long serialVersionUID = -8151708800894601878L;

    /**
     * 航段信息
     */
    @NotBlank(message = "{AopFeedbackItem.leg.notEmpty}")
    private String leg;

    /**
     * FAB ID
     */
    @NotNull(message = "{AopFeedbackItem.personId.notEmpty}")
    private Long personId;

    /**
     * PNR编码
     */
    @NotBlank(message = "{AopFeedbackItem.pnrCode.notEmpty}")
    private String pnrCode;

    /**
     * 票号
     */
    @NotBlank(message = "{AopFeedbackItem.ticketNo.notEmpty}")
    private String ticketNo;

    public String getLeg() {
        return leg;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPnrCode() {
        return pnrCode;
    }

    public void setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AopFeedbackItem{");
        sb.append("leg='").append(leg).append('\'');
        sb.append(", personId=").append(personId);
        sb.append(", pnrCode='").append(pnrCode).append('\'');
        sb.append(", ticketNo='").append(ticketNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
