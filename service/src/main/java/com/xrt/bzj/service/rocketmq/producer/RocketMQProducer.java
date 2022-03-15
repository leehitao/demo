package com.xrt.bzj.service.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description：生产者配置
 */
@Configuration
@Slf4j
public class RocketMQProducer {

    @Value("${rocketmq.producer.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String nameserAddr;

    @Value("${rocketmq.producer.instanceName}")
    private String instanceName;

    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize;

    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout;

    @Autowired
    private ProductListen productListen;

    @Bean(name = "TransactionMQProducer", initMethod = "start", destroyMethod = "shutdown")
    public TransactionMQProducer getRocketMQProducer() {
        TransactionMQProducer producer = new TransactionMQProducer(groupName);
        producer.setNamesrvAddr(nameserAddr);
        producer.setInstanceName("tran");
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setVipChannelEnabled(false);
        producer.setTransactionListener(productListen);
        log.info("================>生产者创建完成，ProducerGroupName{}<================", groupName);
        return producer;
    }


    @Bean(name = "DefaultMQProducer", initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQProducer getDefaultMQProducer1() {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(nameserAddr);
        producer.setInstanceName("simple");
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setVipChannelEnabled(false);
        log.info("================>生产者创建完成，ProducerGroupName{}<================", groupName);
        return producer;
    }

}