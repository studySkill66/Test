package com.yobtc.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * WebSocket成交记录响应对象
 *
 * @program: syex-exchange
 * @description: WebSocket成交记录响应对象
 * @author: Mr.Lming
 * @create: 2019-04-23 15:16
 **/

@Data

public class TradeDetailResult<T> implements Serializable  {
    private static final long serialVersionUID = -921008687184331557L;
    /**
     * 响应消息ID
     */
    private String id;

    /**
     * 响应时间戳
     */
    private Long ts;

    /**
     * 响应数据
     */
    private T data;
    /**
     * 交易对
     */
    private String symbol;

    public TradeDetailResult(String id, Long ts, T data, String symbol) {
        this.id = id;
        this.ts = ts;
        this.data = data;
        this.symbol = symbol;
    }
}
