package com.tuniu.abt.intf.tsp.dto.pla;

import java.util.List;

/**
 * 
 *
 * @author baodawei
 * @CreateDate 2016-5-28 
 * @Description
 */
public class SendEmailInternalRequest {
    /**
     * 系统码
     */
    private static final String SYSTEM_CODE_ASC = "ASC";

    public enum BusinessId {
        ASC_20160525
    }

    /**
     * 系统码
     */
    private String systemCode = SYSTEM_CODE_ASC;
    /**
     * 业务id jira中申请
     */
    private String businessId = BusinessId.ASC_20160525.toString();
    /**
     * 发邮件人名字，由于是掉别人接口实际可能为robot
     */
    private String senderName;
    /**
     * 收件人列表
     */
    private List<String> recipientEmails;
    /**
     * 抄送人列表
     */
    private List<String> ccEmails;
    /**
     * 主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String msgText;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public List<String> getRecipientEmails() {
        return recipientEmails;
    }

    public void setRecipientEmails(List<String> recipientEmails) {
        this.recipientEmails = recipientEmails;
    }

    public List<String> getCcEmails() {
        return ccEmails;
    }

    public void setCcEmails(List<String> ccEmails) {
        this.ccEmails = ccEmails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }
}
