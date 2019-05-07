package com.syex.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class MarketResultJson<T> implements Serializable {
    private static final long serialVersionUID = -911008687184331557L;
    private String id;
    private String ch;
    private String rep;
    private String status = "ok";
    private T tick;
    private T data;
    private Long ts = System.currentTimeMillis();
    private String err_code;
    private String err_msg;

}
