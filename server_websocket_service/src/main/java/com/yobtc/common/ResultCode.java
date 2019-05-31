package com.yobtc.common;

public enum ResultCode {
    OK(0, "OK"),
    PARAM_IS_NOT_EMPTY(5001, "参数不能为空"),
    SERVICE_INVOKE_ERROR(5004, "服务调用失败"),
    COIN_BASE_INFO_NOT_EXISTS(5006, "币种基本信息不存在");



    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
