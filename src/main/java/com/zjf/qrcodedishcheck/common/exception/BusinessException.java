package com.zjf.qrcodedishcheck.common.exception;

import com.zjf.qrcodedishcheck.common.constant.ResponseCodeEnum;

/**
 * 业务异常类
 */
public class BusinessException extends RuntimeException {

    private String code = "500";

    private Object data;

    public BusinessException() {
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResponseCodeEnum responseEumn) {
        super(responseEumn.getDesc());
        this.code = responseEumn.getCode();
    }

    public BusinessException(String code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
