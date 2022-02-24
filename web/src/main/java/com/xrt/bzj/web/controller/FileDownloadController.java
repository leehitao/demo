package com.xrt.bzj.web.controller;

import com.xrt.bzj.service.FileDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(value = "API", tags = {"文件下载API"})
public class FileDownloadController {

    @Autowired
    FileDownloadService fileDownloadService;

    @GetMapping("userinfo")
    @ApiOperation(value = "用户资料下载")
    public void downloadUserInfo(HttpServletResponse response){
        fileDownloadService.downloadUserInfo(response);
    }


    @RequestMapping("userinfoMix")
    @ApiOperation(value = "合并下载")
    public void downloadUserInfoMix(HttpServletResponse response){
        fileDownloadService.downloadUserInfoMix(response);

    }

    @RequestMapping("userinfoMixByExcutor")
    @ApiOperation(value = "合并下载-线程池")
    public void downloadUserInfoByExcutor(HttpServletResponse response){
        fileDownloadService.downloadUserInfoMixByExcutor(response);

    }


}
