package com.xrt.bzj.service.rocketmq.consumer;

import org.apache.rocketmq.common.message.MessageExt;

public interface PointProcessor {

    boolean handle(MessageExt messageExt);

}