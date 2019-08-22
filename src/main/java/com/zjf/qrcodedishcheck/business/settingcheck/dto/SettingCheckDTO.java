package com.zjf.qrcodedishcheck.business.settingcheck.dto;

import lombok.Data;

/**
 * 配置校验项数据入参DTO
 */
@Data
public class SettingCheckDTO {

    /**
     * 操作类型
     */
    private String oper;

    /**
     * 执行校验DTO
     */
    private DoCheckDTO doCheckDTO;

}
