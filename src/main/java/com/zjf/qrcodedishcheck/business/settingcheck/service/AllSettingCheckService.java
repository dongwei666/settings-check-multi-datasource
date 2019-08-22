package com.zjf.qrcodedishcheck.business.settingcheck.service;

import com.zjf.qrcodedishcheck.business.settingcheck.dto.DoCheckDTO;
import com.zjf.qrcodedishcheck.business.settingcheck.vo.SettingCheckResultVo;

import java.util.function.Consumer;

public interface AllSettingCheckService {
    void checkAllSettings(String userId, DoCheckDTO doCheckDTO, Consumer<SettingCheckResultVo> sendMsg);
}
