package com.yobtc.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Websocket返回内
 * @param <T> 内部具体数据结构
 */
@Data
public class KLineDetailResult<T> implements Serializable  {
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
     * 传输名形如"market.BTC_CQ.kline.1min"
     */
    private String ch;

    /**
     * 响应数据
     */
    private T tick;
    /**
     * 交易对
     */
    private String symbol;
    /**
     * 交易周期
     */
    private String period;

    public KLineDetailResult(String id, Long ts, String ch, T tick, String symbol, String period) {
        this.id = id;
        this.ts = ts;
        this.ch = ch;
        this.tick = tick;
        this.symbol = symbol;
        this.period = period;
    }
}
