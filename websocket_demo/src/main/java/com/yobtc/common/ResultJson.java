package com.yobtc.common;

import org.apache.commons.lang3.StringUtils;

public class ResultJson<T> {
    private T model;
    private ResultCode resultCode;
    private int count;
    private String message;
    public ResultJson(){
    }

    public ResultJson(ResultCode resultCode) {
        this.resultCode.setCode(resultCode.getCode());
        this.resultCode.setMessage(resultCode.getMessage());
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public Integer getCode() {
        return resultCode.getCode();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return StringUtils.isBlank(message) ? resultCode.getMessage() : message;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultCode(Integer code, String message) {
        this.resultCode.setCode(code);
        this.resultCode.setMessage(message);
    }

}
