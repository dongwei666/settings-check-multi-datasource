package com.zjf.qrcodedishcheck.business.settingcheck.websocket;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * 编码器，用于websocket往客户端写对象时使用
 */
@Slf4j
@Component
public class Encode implements Encoder.Text<Object>{

	@Override
	public String encode(Object object)  {
		try {
			return JSON.toJSONString(object);
		} catch (Exception e) {
			log.error("对象转json失败, errMsg = {}, error stack = {}", e.getMessage(), e);
			return null;
		}
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
	}

	@Override
	public void destroy() {
	}
}
