package com.xrt.bzj.web.controller;

import com.xrt.bzj.common.base.Result;
import com.xrt.bzj.common.base.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public Result saveOrder(HttpServletRequest request, @RequestBody String order){

        return new ResultVo<>();
    }
}
