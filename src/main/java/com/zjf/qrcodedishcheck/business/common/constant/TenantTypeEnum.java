package com.zjf.qrcodedishcheck.business.common.constant;

/**
 * 扫码点餐商户类型
 */
public enum TenantTypeEnum {

    COMMON("common", "标版扫码点餐"), JIUMAOJIU("jiumaojiu", "九毛九");

    /**
     * 类型code
     */
    private String code;

    /**
     * 类型描述
     */
    private String desc;

    TenantTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 通过code获取枚举值
     */
    public static TenantTypeEnum getEnumByCode(String code) {
        for (TenantTypeEnum t : TenantTypeEnum.values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }

}
