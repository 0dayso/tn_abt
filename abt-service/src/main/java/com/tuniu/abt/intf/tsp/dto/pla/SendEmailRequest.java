package com.tuniu.abt.intf.tsp.dto.pla;

import java.util.List;

/**
 * 发送邮件请求对象
 *
 * Created by chengyao on 2015/12/24.
 */
public class SendEmailRequest {

    /**
     * edm平台该消息模板的id,需要在edm平台中注册邮件模板
     */
    private String msgId;

    /**
     * 收件人邮箱地址
     */
    private String emailAddress;

    /**
     * 是否通过途牛smtp邮件服务器发送
     * true:通过途牛smtp邮件服务器发送, false:通过edm发送，不填默认false
     */
    private Boolean smtpSend;

    /**
     * 参数列表
     */
    private List<SendEmailParam> paramList;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean getSmtpSend() {
        return smtpSend;
    }

    public void setSmtpSend(Boolean smtpSend) {
        this.smtpSend = smtpSend;
    }

    public List<SendEmailParam> getParamList() {
        return paramList;
    }

    public void setParamList(List<SendEmailParam> paramList) {
        this.paramList = paramList;
    }

    /*
    msgId	消息id	String	是	，详细说明见最后。
emailAddress	收件人邮箱地址	String	是
smtpSend
paramList	参数列表	List	不是	模板中的参数
paramId	参数id	int	不是	平台中的参数id
paramValue	参数的值	String	不是

入参json示例	"[
    {
        ""msgId"": ""6Q7QJdkH7"",
        ""emailAddress"": ""abc@de.com"",
        ""smtpSend"": false,
        ""paramList"": [
            {
                ""paramId"": 1,
                ""paramValue"": ""value""
            }
        ]
    }
]"	入参是json数组形式，支持批量发送*/



}
