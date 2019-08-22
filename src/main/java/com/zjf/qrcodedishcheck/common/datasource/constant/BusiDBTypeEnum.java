package com.zjf.qrcodedishcheck.common.datasource.constant;

/**
 * 扫码点餐商户类型
 */
public enum BusiDBTypeEnum {

    BOH("boh", "oracle", "oracle.jdbc.driver.OracleDriver", "classpath:mybatis/boh/*.xml", "boh数据源"), QRCODE_DISH(
            "qrcode_dish", "mysql", "com.mysql.jdbc.Driver", "classpath:mybatis/qrcodedish/*.xml", "扫码点餐后端服务数据源");

    /**
     * 类型code
     */
    private String code;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * 数据库类型-驱动类型
     */
    private String driverClassName;

    /**
     * Mybatis Map文件路径
     */
    private String mapperLocations;

    /**
     * 类型描述
     */
    private String desc;

    BusiDBTypeEnum(String code, String dbType, String driverClassName, String mapperLocations, String desc) {
        this.code = code;
        this.dbType = dbType;
        this.driverClassName = driverClassName;
        this.mapperLocations = mapperLocations;
        this.desc = desc;
    }

    /**
     * 通过code获取枚举值
     */
    public static BusiDBTypeEnum getEnumByCode(String code) {
        for (BusiDBTypeEnum t : BusiDBTypeEnum.values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDbType() {
        return dbType;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public String getDesc() {
        return desc;
    }

}
