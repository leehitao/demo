package com.xrt.bzj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xrt.bzj.service.file.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ThreadPoolExecutor;

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
        fileDownloadService.downloadUserInfo(response);
    }


    @RequestMapping("userinfoMix")
    public void downloadUserInfoMix(HttpServletResponse response, @RequestBody String strJson){
        fileDownloadService.downloadUserInfoMix(response);

    }

    @RequestMapping("userinfoMixByExcutor")
    public void downloadUserInfoByExcutor(HttpServletResponse response, @RequestBody String strJson){
        fileDownloadService.downloadUserInfoMixByExcutor(response);

    }


}
