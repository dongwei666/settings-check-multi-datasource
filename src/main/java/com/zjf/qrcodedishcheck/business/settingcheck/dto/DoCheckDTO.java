package com.zjf.qrcodedishcheck.business.settingcheck.dto;

import lombok.Data;

import java.util.List;

/**
 * 配置校验项数据入参DTO
 */
@Data
public class DoCheckDTO {

    /**
     * 商户校验类型：标版、商户定制等
     */
    private String tenantType;

    /**
     * 用户sessionID
     */
    private String tenantId;

    /**
     * 数据源信息
     */
    private List<DataSourceDTO> dsList;

}
