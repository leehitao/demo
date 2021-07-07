package com.xrt.bzj.web.controller;

import com.xrt.bzj.common.base.Result;
import com.xrt.bzj.common.base.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.web.controller
 * @date 2021/7/7 22:52
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    @RequestMapping("submit")
    public Result saveOrder(HttpServletRequest request ){

        // TODO 保存订单，发送消息到商品系统减库存


        return new ResultVo<>();
    }
}
