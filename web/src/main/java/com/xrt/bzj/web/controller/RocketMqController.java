package com.xrt.bzj.web.controller;

import com.xrt.bzj.service.rocketmq.producer.RocketMQProducer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description：
 */
@Slf4j
@RestController
@RequestMapping("/rocket-mq")
@Api(value = "API", tags = {"rocketMQ-API"})
public class RocketMqController {

    @Autowired
    @Qualifier("rocketMQProducer")
    RocketMQProducer rocketMQProducer;

    @GetMapping("/test")
    public void TestSend() {
        DefaultMQProducer producer = rocketMQProducer.getRocketMQProducer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            for (int i = 0; i < 35; i++) {
                String body = "hi RocketMQ" + i + ", now is  " + sdf.format(new Date());
                Message message = new Message("topic2020", "test", body.getBytes());
                message.putUserProperty("age", String.valueOf(i));
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}