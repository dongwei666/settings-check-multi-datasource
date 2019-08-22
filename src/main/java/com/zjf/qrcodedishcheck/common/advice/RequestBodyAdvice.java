package com.zjf.qrcodedishcheck.common.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

/**
 * 请求统一拦截打印日志
 */
@ControllerAdvice
public class RequestBodyAdvice extends RequestBodyAdviceAdapter {
    private static Logger logger = LoggerFactory.getLogger(RequestBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.debug("接收到请求路径= {}, 方法名 = {}, IP地址 = {}", request.getRequestURI(), request.getMethod(),
                request.getRemoteAddr());
        logger.debug("Body参数: {}", body);
        return body;
    }

}
