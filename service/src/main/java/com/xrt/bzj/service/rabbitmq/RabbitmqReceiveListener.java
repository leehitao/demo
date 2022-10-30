package com.xrt.bzj.service.rabbitmq;

import com.rabbitmq.client.Channel;
import com.xrt.bzj.common.constant.RabbitMQConstant;
import com.xrt.bzj.dao.entity.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 2个消费者
@Component
public class RabbitmqReceiveListener {


    @RabbitListener(queues = RabbitMQConstant.ORDER_SYNC_QUEUE)
    public void consume1(Order msg, Channel channel, Message message) throws IOException {
        System.out.println("队列1接收到消息：" + msg.toString());
        String correlationId = message.getMessageProperties().getCorrelationId();
        System.out.println("唯一标识：" + correlationId);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);


    }

    @RabbitListener(queues = RabbitMQConstant.ORDER_SYNC_QUEUE)
    public void consume2(Order msg, Channel channel, Message message) throws IOException {
        System.out.println("队列2接收到消息：" + msg.toString());
        String correlationId = message.getMessageProperties().getCorrelationId();
        System.out.println("唯一标识：" + correlationId);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
