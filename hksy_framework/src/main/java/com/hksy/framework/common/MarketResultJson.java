package com.hksy.framework.common;

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

    /**
     * 心跳请求消息
     */
    private String ping;

    /**
     * 心跳响应消息
     */
    private String pong;
    /**
     * 订阅成功，响应的主题
     */
    private String subbed;

    /**
     * 取消订阅响应的主题
     */
    private String unsubbed;

}
