package com.xrt.bzj.web.controller;

import com.xrt.bzj.service.mq.RabbitmqService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/mq")
@Api(value = "API", tags = {"消息中间件API"})
public class RabbitmqController {
    @Autowired
    private RabbitmqService rabbitmqService;

    @GetMapping("sendWork")
    public Object sendWork() {
        rabbitmqService.sendWork();
        return "发送成功...";
    }
}
