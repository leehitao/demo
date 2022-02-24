package com.xrt.bzj.web.controller;

import com.xrt.bzj.common.base.ResultVo;
import com.xrt.bzj.dao.param.AverageUserParam;
import com.xrt.bzj.dao.vo.AverageUserVo;
import com.xrt.bzj.service.AverageUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("api/mchUser")
@Api(value = "API", tags = {"用户API"})
public class AverageUserController {

    @Autowired
    private AverageUserService averageUserService;

    @PostMapping("findMchUser")
    @ApiOperation(value = "查询用户接口", response = AverageUserVo.class)
    @ResponseBody
    public ResultVo saveMchUser(HttpServletResponse response, @RequestBody AverageUserParam averageUserParam) throws Exception {
        AverageUserVo averageUserVo = averageUserService.findAverageUser(averageUserParam);
        return ResultVo.success(averageUserVo);
    }


}
