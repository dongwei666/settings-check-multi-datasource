package com.zjf.qrcodedishcheck.common.entity;

import com.zjf.qrcodedishcheck.common.constant.ResponseCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回体
 */
@Data
public class MessageBody<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码，业务使用，用来区分接口处理的结果：约定200代表成功
     */
    private String errorCode;

    /**
     * 接口返回提示语
     */
    private String errorMsg;

    /**
     * 堆栈信息，调试时可以使用
     */
    private String stackTrace;

    /**
     * 返回数据体，接口正常和异常都可以使用
     */
    private T data;

    /**
     * 通过ResponseCodeEnum构造接口返回体，推荐使用
     */
    public static MessageBody getMessageBody(ResponseCodeEnum res) {
        MessageBody body = new MessageBody();
        body.setErrorCode(res.getCode());
        body.setErrorMsg(res.getDesc());
        return body;
    }

    /**
     * 通过ResponseCodeEnum构造接口返回体，推荐使用
     */
    public static MessageBody getMessageBody(ResponseCodeEnum res, Object data) {
        MessageBody body = getMessageBody(res);
        body.setData(data);
        return body;
    }

    /**
     * 自定义各个属性构造接口返回体
     */
    public static MessageBody getMessageBody(String errorCode, String errorMsg) {
        MessageBody body = new MessageBody();
        body.setErrorCode(errorCode);
        body.setErrorMsg(errorMsg);
        return body;
    }

    /**
     * 自定义各个属性构造接口返回体
     */
    public static MessageBody getMessageBody(String errorCode, String errorMsg, Object data) {
        MessageBody body = getMessageBody(errorCode, errorMsg);
        body.setData(data);
        return body;
    }

    /**
     * 返回成功
     */
    public static MessageBody getSuccessMessageBody(String errorMsg) {
        MessageBody body = new MessageBody();
        body.setErrorCode("200");
        body.setErrorMsg(errorMsg);
        return body;
    }

    /**
     * 返回成功
     */
    public static MessageBody getSuccessMessageBody(String errorMsg, Object data) {
        MessageBody body = getSuccessMessageBody(errorMsg);
        body.setData(data);
        return body;
    }

}
