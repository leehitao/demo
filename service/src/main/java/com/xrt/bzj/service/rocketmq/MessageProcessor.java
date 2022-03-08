package com.xrt.bzj.service.rocketmq;

import org.apache.rocketmq.common.message.MessageExt;

public interface MessageProcessor {

    boolean handle(MessageExt messageExt);

}