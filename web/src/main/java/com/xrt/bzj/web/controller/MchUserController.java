package com.xrt.bzj.web.controller;

import com.xrt.bzj.dao.po.MchUser;
import com.xrt.bzj.service.mchuser.MchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/mchUser")
public class MchUserController {

    @Autowired
    private MchUserService mchUserService;

    @RequestMapping(value = "findMchUser", method = RequestMethod.POST)
    @ResponseBody
    public MchUser saveMchUser(@RequestBody MchUser mchUserBO) throws Exception {
       MchUser mchUser = mchUserService.findMchUser(mchUserBO);
       return mchUser;
    }


}
