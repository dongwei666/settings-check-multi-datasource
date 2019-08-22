package com.zjf.qrcodedishcheck.common.advice;

import com.zjf.qrcodedishcheck.common.constant.ResponseCodeEnum;
import com.zjf.qrcodedishcheck.common.entity.MessageBody;
import com.zjf.qrcodedishcheck.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常拦截器
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    /**
     * 业务抛出异常处理
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageBody handleBusinessException(HttpServletRequest request, BusinessException e) {
        log.info("业务异常，请求路径 = {}， 错误信息 = {}, 堆栈信息 = {}", request.getRequestURI(), e.getMessage(), e);
        return MessageBody.getMessageBody(
                (StringUtils.isEmpty(e.getCode()) ? ResponseCodeEnum.BUSINESS_ERROR.getCode() : e.getCode()),
                e.getMessage(), e.getData());
    }

    /**
     * 程序异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageBody handleException(HttpServletRequest request, Exception e) {
        log.error("系统异常，请求路径  = {}， 错误信息 = {}, 堆栈信息 = {}", request.getRequestURI(), e.getMessage(), e);
        return MessageBody.getMessageBody(ResponseCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

}
