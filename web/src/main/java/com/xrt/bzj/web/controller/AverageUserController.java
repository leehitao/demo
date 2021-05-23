package com.xrt.bzj.web.controller;

import com.xrt.bzj.dao.po.AverageUser;
import com.xrt.bzj.service.mchuser.AverageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("api/mchUser")
public class AverageUserController {

    @Autowired
    private AverageUserService averageUserService;

    @RequestMapping(value = "findMchUser", method = RequestMethod.POST)
    @ResponseBody
    public AverageUser saveMchUser(HttpServletResponse response,@RequestBody AverageUser averageUser) throws Exception {
        AverageUser averageUserDTO = averageUserService.findAverageUser(averageUser);
       return averageUserDTO;
    }


}
