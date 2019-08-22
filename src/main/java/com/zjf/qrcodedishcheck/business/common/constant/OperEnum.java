package com.zjf.qrcodedishcheck.business.common.constant;

import lombok.Getter;

/**
 * 用户操作类型，对应页面上的按钮操作
 */
@Getter
public enum OperEnum {

    DOCHECK("docheck", "执行校验");

    /**
     * 类型code
     */
    private String code;

    /**
     * 类型描述
     */
    private String desc;

    OperEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过code获取枚举值
     */
    public static OperEnum getEnumByCode(String code) {
        for (OperEnum t : values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }

}
