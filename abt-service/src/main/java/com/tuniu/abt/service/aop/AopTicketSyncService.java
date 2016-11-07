package com.tuniu.abt.service.aop;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.dto.aop.AopChangeSyncRequest;
import com.tuniu.abt.intf.dto.aop.AopRefundSyncRequest;
import com.tuniu.abt.intf.dto.aop.AopTicketSyncRequest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 开放平台出票同步服务
 *
 * Created by chengyao on 2016/3/23.
 */
@Service
public class AopTicketSyncService {

    @Resource
    private JmsTemplate jmsTemplate;

    @CommandTrace(name = TracerCmdEnum.MQ_SEND, type = "'TicketSyncRequest'", input = "#object")
    public void sendTicket(AopTicketSyncRequest object) {
        sendMqMessage(object, "TicketSyncRequest");
    }

    @CommandTrace(name = TracerCmdEnum.MQ_SEND, type = "'RefundSyncRequest'", input = "#object")
    public void sendRefund(AopRefundSyncRequest object) {
        sendMqMessage(object, "RefundSyncRequest");
    }

    @CommandTrace(name = TracerCmdEnum.MQ_SEND, type = "'ChangeSyncRequest'", input = "#object")
    public void sendChange(AopChangeSyncRequest object) {
        sendMqMessage(object, "ChangeSyncRequest");
    }

    private void sendMqMessage(Object object, final String jmsType) {
        final String textMessage = JSON.toJSONString(object);
        MessageCreator msg = new MessageCreator() {
            public Message createMessage(Session session)throws JMSException {
                TextMessage msg = session.createTextMessage(textMessage);
                msg.setJMSType(jmsType);
                return msg;
            }
        };
        jmsTemplate.send(msg);
    }
}
