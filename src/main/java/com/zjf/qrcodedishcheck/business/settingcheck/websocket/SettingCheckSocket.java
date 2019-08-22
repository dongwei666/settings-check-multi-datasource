package com.zjf.qrcodedishcheck.business.settingcheck.websocket;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zjf.qrcodedishcheck.business.common.constant.OperEnum;
import com.zjf.qrcodedishcheck.business.settingcheck.dto.SettingCheckDTO;
import com.zjf.qrcodedishcheck.business.settingcheck.service.AllSettingCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 配置校验websocket
 */
@ServerEndpoint(value = "/ws/setting-check", encoders = Encode.class)
@Component
@Slf4j
public class SettingCheckSocket {

    @Autowired
    private AllSettingCheckService allConfigCheckService;

    /**
     * 打开链接
     */
    @OnOpen
    public void onOpen(Session session) {
        log.info("Websocket成功创建链接，用户sessionID：" + session.getId());
        sendMessage(session, "Websocket成功创建链接，用户sessionID：" + session.getId());
    }

    /**
     * 收到客户端消息的回调方法,调用群发消息方法向客户端群发消息
     *
     * @param message 客户端传过来的消息
     * @param session 对应的session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到到消息 = {}，用户sessionID = {}", message, session.getId());

        SettingCheckDTO settingCheckDTO;

        try {
            settingCheckDTO = JSONObject.parseObject(message, SettingCheckDTO.class);
        } catch (JSONException e) {
            log.error("消息转化DTO失败, errMsg = {}, error stack = {}", e.getMessage(), e);
            return;
        }

        // 如果是执行校验
        if (OperEnum.getEnumByCode(settingCheckDTO.getOper()) == OperEnum.DOCHECK) {
            allConfigCheckService.checkAllSettings(settingCheckDTO.getDoCheckDTO().getTenantId() + session.getId(),
                    settingCheckDTO.getDoCheckDTO(), resultVo -> sendMessage(session, resultVo));
        }

    }

    /**
     * 发生错误的回调方法
     *
     * @param session 用户session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误，用户sessionId = {}, errMsg = {}, error stack = {}", session.getId(), error.getMessage(), error);
        error.printStackTrace();
    }

    /**
     * 关闭连接的回调方法，当页面关闭的时候意味着就关闭一个websocket请求
     */
    @OnClose
    public void onClose(Session session) {
        log.info("sessionID为：" + session.getId() + "的用户断开链接");
    }

    /**
     * 服务端向客户端单独推送消息的方法
     *
     * @param session
     * @param message
     */
    private static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("服务端向客户端输出信息失败，errMsg = {} ,error stack = {}", e.getMessage(), e);
        }
    }

    /**
     * 服务端向客户端单独推送消息的方法
     *
     * @param session
     * @param obj
     */
    private static void sendMessage(Session session, Object obj) {
        try {
            session.getBasicRemote().sendObject(obj);
        } catch (IOException e) {
            log.error("服务端向客户端输出对象信息失败，errMsg = {} ,error stack = {}", e.getMessage(), e);
        } catch (EncodeException e) {
            log.error("服务端向客户端输出对象信息失败，errMsg = {} ,error stack = {}", e.getMessage(), e);
        }
    }

}
