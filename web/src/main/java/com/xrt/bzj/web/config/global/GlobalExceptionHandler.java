package com.xrt.bzj.web.config.global;


import com.xrt.bzj.common.base.BizException;
import com.xrt.bzj.common.base.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler({BizException.class})
	@ResponseBody
	public ResultVo handleBizException(HttpServletRequest request, BizException e) {
		logger.info("异常接口:" + request.getRequestURI());
		logger.info("逻辑异常:" + e.getMsg());
		return new ResultVo(e.getCode(), e.getMsg());
	}

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ResultVo handleException(HttpServletRequest request, Exception e) {
		logger.info("异常接口:" + request.getRequestURI());
		logger.error("异常信息:", e.getMessage(), e);
		return new ResultVo(500, "系统异常");
	}

}
