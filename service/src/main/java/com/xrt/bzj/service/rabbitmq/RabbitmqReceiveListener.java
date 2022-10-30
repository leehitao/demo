package com.xrt.bzj.service.rabbitmq;

import com.rabbitmq.client.Channel;
import com.xrt.bzj.common.constant.RabbitMQConstant;
import com.xrt.bzj.dao.entity.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// 2个消费者
@Component
public class RabbitmqReceiveListener {

    private static int count = 0;

    @RabbitListener(queues = RabbitMQConstant.ORDER_SYNC_QUEUE)
    public void receiveMessage(Order order, Channel channel, Message message) {
        System.out.println("1接收到消息：" + order);


    }

    @RabbitListener(queues = RabbitMQConstant.ORDER_SYNC_QUEUE)
    public void receiveMessage2(Order order, Channel channel, Message message) {
        System.out.println("2接收到消息：" + order);

    }
}
