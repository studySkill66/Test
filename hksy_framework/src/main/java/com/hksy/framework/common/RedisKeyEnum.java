package com.hksy.framework.common;

/**
 * Redis 常用Key模板
 *
 * @program: new-syex
 * @description: Redis 常用Key模板
 * @author: Mr.Lming
 * @create: 2019-05-28 09:15
 **/

public enum RedisKeyEnum {

    /**
     * 存储 交易对 货币详细信息
     */
    FINANCE_COIN_TRADE_INFO_KEY("FINANCE_COIN_TRADE_INFO:coinId:%s:payCoinId:%s", 3600 * 24 * 7),

    /**
     * 存储 交易对 最新成交记录【列表】
     */
    TRADE_CLINCH_INFO_KEY("TradeClinch_New_Info:coinId:%s:payCoinId:%s", 3600 * 24 * 7),

    /**
     * 存储 币种详细
     */
    FINANCE_COIN_BASE_INFO_KEY("FINANCE_COIN_BASE_INFO_KEY:coinId:%s", 3600 * 24);

    private String key;
    private Integer seconds;

    RedisKeyEnum(String key, Integer seconds) {
        this.key = key;
        this.seconds = seconds;
    }


    public java.lang.String getKey() {
        return key;
    }

    public void setKey(java.lang.String key) {
        this.key = key;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }
}
