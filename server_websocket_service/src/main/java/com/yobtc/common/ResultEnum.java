package com.yobtc.common;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResultEnum {

    public ResultEnum(ResultCode result, String language, String country) {
        try {
            Locale currentLocale = new Locale(language, country);

            ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", currentLocale);
            MessageFormat msg = new MessageFormat(rb.getString(result.toString()));
            this.code = result.getCode();
            this.message = msg.toPattern().trim();
        } catch (Exception e) {
            e.printStackTrace();
            this.code = result.getCode();
            this.message = result.getMessage();
        }
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