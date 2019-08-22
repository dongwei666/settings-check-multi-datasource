package com.zjf.qrcodedishcheck.business.common.constant;

import lombok.Getter;

/**
 * 检测结果返回
 * 
 * @author zhaojufei
 */
@Getter
public enum CheckResEnum {
    BOH_DEMO(600, "Boh示例检查通过", "Boh示例检查警告", "Boh示例检查失败"),
    QRCODE_DISH_DEMO(601, "扫码点餐示例检查通过", "扫码点餐示例检查警告", "扫码点餐示例检查失败");

    private Integer checkCode;

    private String successInfo;

    private String warnInfo;

    private String errorInfo;

    CheckResEnum(Integer checkCode, String successInfo, String warnInfo, String errorInfo) {
        this.checkCode = checkCode;
        this.successInfo = successInfo;
        this.warnInfo = warnInfo;
        this.errorInfo = errorInfo;
    }

    /**
     * 根据code获取枚举
     */
    public static CheckResEnum getCheckResEnumByCode(Integer code) {

        for (CheckResEnum obj : values()) {
            if (code.equals(obj.getCheckCode())) {
                return obj;
            }
        }
        return null;
    }

}
