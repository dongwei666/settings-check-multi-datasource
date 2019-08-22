package com.zjf.qrcodedishcheck.business.common.service;

import com.zjf.qrcodedishcheck.business.common.constant.CheckResEnum;
import com.zjf.qrcodedishcheck.business.settingcheck.vo.SettingCheckResultVo;

/**
 * @author zhaojufei
 */
public abstract class AbstractCheckService {

    private final Integer SUCCESS_FLAG = 1;
    private final Integer WARN_FLAG = 2;
    private final Integer ERROR_FLAG = 3;

    protected SettingCheckResultVo retSuccess(CheckResEnum checkResEnum) {
        SettingCheckResultVo result = new SettingCheckResultVo();
        result.setSuccessFlag(SUCCESS_FLAG);
        result.setCode(checkResEnum.getCheckCode());
        result.setInfo(checkResEnum.getSuccessInfo());
        return result;
    }

    protected SettingCheckResultVo retWarn(CheckResEnum checkResEnum) {
        SettingCheckResultVo result = new SettingCheckResultVo();
        result.setSuccessFlag(WARN_FLAG);
        result.setCode(checkResEnum.getCheckCode());
        result.setInfo(checkResEnum.getWarnInfo());
        return result;
    }

    protected SettingCheckResultVo retError(CheckResEnum checkResEnum) {
        SettingCheckResultVo result = new SettingCheckResultVo();
        result.setSuccessFlag(ERROR_FLAG);
        result.setCode(checkResEnum.getCheckCode());
        result.setInfo(checkResEnum.getErrorInfo());
        return result;
    }

    public abstract SettingCheckResultVo check(String userId, String tenantId);

}
