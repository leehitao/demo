package com.xrt.bzj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xrt.bzj.service.file.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.web.controller
 * @date 2021/5/23 17:21
 */
@Controller
@RequestMapping("/download")
public class FileDownloadController {

    @Autowired
    FileDownloadService fileDownloadService;

    @RequestMapping("userinfo")
    public void downloadUserInfo(HttpServletResponse response, @RequestBody String strJson){
        JSONObject jsonObject = JSONObject.parseObject(strJson);
        Integer type = jsonObject.getInteger("type");
        fileDownloadService.downloadUserInfo(response,type);
    }


    @RequestMapping("userinfoMix")
    public void downloadUserInfoMix(HttpServletResponse response, @RequestBody String strJson){
        JSONObject jsonObject = JSONObject.parseObject(strJson);
        Integer type = jsonObject.getInteger("type");
        fileDownloadService.downloadUserInfoMix(response,type);
    }


}
