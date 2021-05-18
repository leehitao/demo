package com.xrt.bzj.web.config.global;

import com.xrt.bzj.common.base.BizException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lee
 * @Date: 2020/8/3 15:19
 * @Version 1.0
 */
@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		//获取statusCode:401,404,500
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == 500) {
			throw new BizException(statusCode, "系统异常");
		} else if (statusCode == 404) {
			throw new BizException(statusCode, "NOT FOUND!");
		} else if (statusCode == 403) {
			throw new BizException(statusCode, "FORBIDDEN!");
		} else {
			throw new BizException(500, "系统异常");
		}

	}


	@Override
	public String getErrorPath() {
		return "/error";
	}

}
