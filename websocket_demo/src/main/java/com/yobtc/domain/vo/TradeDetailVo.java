package com.yobtc.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 最新成交记录WebSocket传输VO JavaBean
 *
 * @program: syex-exchange
 * @description: 最新成交记录WebSocket传输VO JavaBean
 * @author: Mr.Lming
 * @create: 2019-04-23 14:57
 **/

@Data
public class TradeDetailVo implements Serializable  {
    private static final long serialVersionUID = -921228687184331557L;
    /**
     * 成交ID
     */
    private String tradeId;
    /**
     * 成交价
     */
    private BigDecimal price;
    /**
     * 成交时间
     */
    private Long time;
    /**
     * 成交量
     */
    private BigDecimal amount;
    /**
     * 成交方向,buy OR sell
     */
    private String direction;
    /**
     * 时间戳
     */
    private Long ts;

    public TradeDetailVo(String tradeId, BigDecimal price, Long time, BigDecimal amount, String direction, Long ts) {
        this.tradeId = tradeId;
        this.price = price;
        this.time = time;
        this.amount = amount;
        this.direction = direction;
        this.ts = ts;
    }
}
