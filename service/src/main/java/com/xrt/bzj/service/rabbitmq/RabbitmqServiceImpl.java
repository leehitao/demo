package com.xrt.bzj.service.rabbitmq;

import com.xrt.bzj.common.constant.RabbitMQConstant;
import com.xrt.bzj.dao.entity.Order;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.mq.impl
 * @date 2021/7/1 22:31
 */
@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(Order order) {
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCAHNGE, RabbitMQConstant.ROUTING_KEY, order);
    }


    public void publishWishPros() {
        Order order = new Order();
        order.setId(13);
        order.setOrderNo("MES98322");
        order.setCreateTime(new Date());
        order.setTotalFee(100);
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCAHNGE, RabbitMQConstant.ROUTING_KEY, order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setCorrelationId("123");
                return message;
            }
        });
    }
}
