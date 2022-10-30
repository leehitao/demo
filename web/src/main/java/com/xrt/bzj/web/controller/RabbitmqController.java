package com.xrt.bzj.web.controller;

import com.xrt.bzj.dao.entity.Order;
import com.xrt.bzj.service.rabbitmq.RabbitmqService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rabbitMQ")
@Api(value = "API", tags = {"rabbitMQ-API"})
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RabbitmqController {
    @Autowired
    private RabbitmqService rabbitmqService;

    @PostMapping("simple")
    @ResponseBody
    public String publish(@RequestBody Order order) {
        rabbitmqService.publish(order);
        return "success";
    }

    @PostMapping("publishWithProps")
    @ResponseBody
    public String publishWithProps(@RequestBody Order order) {
        rabbitmqService.publishWishPros(order);
        return "success";
    }


    @PostMapping("publishWishConfirms")
    @ResponseBody
    public String publishWishConfirms(@RequestBody Order order) {
        rabbitmqService.publishWishConfirms(order);
        return "success";
    }
}
