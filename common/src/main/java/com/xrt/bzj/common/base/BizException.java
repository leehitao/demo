package com.xrt.bzj.common.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eric on 2018/9/26.
 * 统一问题处理
 */
public class BizException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private int code;

    private String msg;


    public BizException() {
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(String message) {
        super(message);
        this.code = 400;
        this.msg = message;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
