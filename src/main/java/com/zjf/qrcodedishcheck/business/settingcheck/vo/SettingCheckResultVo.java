package com.zjf.qrcodedishcheck.business.settingcheck.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 配置检验结果展示
 */
@Data
public class SettingCheckResultVo {
    /**
     * 校验是否成功、通过标识：1：通过，2：警告，3：失败
     */
    private Integer successFlag;

    /**
     * 消息标识
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String info;

    /**
     * 返回的结果信息
     */
    private List<?> resList;

    /**
     * 检查进度，0 ~ 100
     */
    private BigDecimal progress;
}
