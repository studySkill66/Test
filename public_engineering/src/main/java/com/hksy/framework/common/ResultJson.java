package com.hksy.framework.common;

public class ResultJson<T> extends Result{

    private Integer code;

    private String message;

    public ResultJson(){

    }

    public ResultJson(StatusEnum statusEnum){
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
    }

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
