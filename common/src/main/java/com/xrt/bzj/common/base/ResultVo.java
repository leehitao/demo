package com.xrt.bzj.common.base;

/**
 * @Author: lee
 * @Date: 2020/7/29 19:24
 * @Version 1.0
 */
public class ResultVo<T> implements Result{

	// 需要传递的代号
	private int code;
	// 需要传递的信息，例如错误信息
	private String message;
	// 需要传递的数据
	private T data;

	public ResultVo() {
	}

	public ResultVo(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResultVo(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultVo success(T data) {
		return new ResultVo(200, "success", data);
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
