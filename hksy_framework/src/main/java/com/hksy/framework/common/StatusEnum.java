package com.hksy.framework.common;

/**
 * 异常状态
 */
public enum StatusEnum {

    EXCEPTION(999999, "系统异常");

    private final Integer code;
    private final String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
