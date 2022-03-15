package com.xrt.bzj.web.controller;

import com.xrt.bzj.service.rabbitmq.RabbitmqService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mq")
@Api(value = "API", tags = {"rabbitMQ-API"})
public class RabbitmqController {
    @Autowired
    private RabbitmqService rabbitmqService;

    @GetMapping("sendWork")
    public Object sendWork() {
        rabbitmqService.sendWork();
        return "发送成功...";
    }
}
