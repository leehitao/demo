package com.xrt.bzj.web.config.global;

/**
 * @Author: lee
 * @Date: 2020/7/29 19:05
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.xrt.bzj.common.base.ResultVo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;


/**
 * 全局返回值统一封装
 */
//@EnableWebMvc
//@Configuration
public class GlobalReturnConfig {

	@RestControllerAdvice
	static class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

		@Override
		public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
			return true;
		}

		@Override
		public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
			if (body instanceof ResultVo) {
				return body;
			}
			// 内容为空返回默认消息
			if (body == null) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("code", "200");
				resultMap.put("message", "请求成功!");
				return resultMap;
			}
			// 单独返回字符串需要特殊处理
			if (body instanceof String) {
				return JSON.toJSONString(new ResultVo().success(body));
			}
			return new ResultVo().success(body);
		}
	}

}
