package com.zjf.qrcodedishcheck.business.settingcheck.dto;

import lombok.Data;

/**
 * 数据源基本信息，每个数据源的配置都不一样。
 */
@Data
public class DataSourceDTO {

    /**
     * 数据源类型，根据业务线上的实际情况
     */
    private String busiDBType;

    /**
     * 数据源url
     */
    private String url;

    /**
     * 数据源用户名
     */
    private String username;

    /**
     * 数据源密码
     */
    private String password;
}
