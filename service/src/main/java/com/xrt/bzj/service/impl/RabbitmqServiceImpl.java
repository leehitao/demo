package com.xrt.bzj.service.impl;

import com.xrt.bzj.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    RabbitmqMapper rabbitmqMapper;

    @Override
    public void sendWork() {
        rabbitmqMapper.sendWork();
    }
}
