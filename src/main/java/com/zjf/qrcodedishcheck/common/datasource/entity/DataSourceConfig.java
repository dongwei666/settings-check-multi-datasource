package com.zjf.qrcodedishcheck.common.datasource.entity;

import com.zjf.qrcodedishcheck.common.datasource.constant.BusiDBTypeEnum;
import lombok.Data;

/**
 * 数据源基本信息，每个数据源的配置都不一样。连接池的信息省略。
 */
@Data
public class DataSourceConfig {

    /**
     * 数据源类型，根据业务线上的实际情况
     */
    private String busiDBType;

    /**
     * 通过判断驱动的名字，可以使系统支持多数据源，目前先支持一种mysql
     */
    private String driverClassName;

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

    /**
     * 数据源扫描的map路径
     */
    private String mapperLocations;

    /**
     * 根据业务线数据的类型设置数据源信息
     */
    public void setDataSourceInfoByType() {
        BusiDBTypeEnum busiDBTypeEnum = BusiDBTypeEnum.getEnumByCode(busiDBType);
        this.driverClassName = busiDBTypeEnum.getDriverClassName();
        this.mapperLocations = busiDBTypeEnum.getMapperLocations();
    }

}
