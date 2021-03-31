package com.xrt.bzj.dao.core;

/**
 * @Author: lee
 * @Date: 2020/7/29 19:24
 * @Version 1.0
 */
public class ResultBase<T> implements Result{

	// 需要传递的代号
	private int code;
	// 需要传递的信息，例如错误信息
	private String message;
	// 需要传递的数据
	private T data;

	public ResultBase() {
	}

	public ResultBase(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResultBase(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultBase success(T data) {
		return new ResultBase(200, "请求成功!", data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
