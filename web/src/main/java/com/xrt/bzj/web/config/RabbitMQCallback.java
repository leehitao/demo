package com.xrt.bzj.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
public class RabbitMQCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送到exchange成功");
        } else {
            log.info("消息发送到exchange失败，原因：{}", cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        //失败的回调
        String correlationId = message.getMessageProperties().getCorrelationId();
        log.info("消息：{} 发送失败, 应答码：{} 原因：{} 路由键：{}",
                correlationId, replyCode, replyText, exchange, routingKey);

    }
}