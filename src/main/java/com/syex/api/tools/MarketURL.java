package com.syex.api.tools;

import java.util.HashMap;

/**
 * WebSocket连接地址
 *
 * @program: syex-api-parent
 * @description: WebSocket连接地址
 * @author: Mr.Lming
 * @create: 2019-04-25 09:46
 **/

public enum MarketURL {
    MARKET_SYMBOL_TRADE_DETAIL("market.{symbol}.trade.detail", "market\\.[a-zA-Z0-9\\-]{1,15}\\.trade\\.detail",
            new HashMap<String, Integer>() {{
                put(MarketParams.SYMBOL, 1);
            }}),
    MARKET_SYMBOL_DETAIL("market.{symbol}.detail", "market\\.[a-zA-Z0-9\\-]{1,15}\\.detail", new HashMap<String, Integer>() {{
        put(MarketParams.SYMBOL, 1);
    }}),
    MARKET_SYMBOL_DEPTH_TYPE("market.{symbol}.depth.{type}", "market\\.[a-zA-Z0-9\\-]{1,15}\\.depth\\.[a-zA-Z0-9]{1,15}", new HashMap<String, Integer>() {{
        put(MarketParams.SYMBOL, 1);
        put(MarketParams.TYPE, 3);
    }}),
    MARKET_SYMBOL_KLINE_PERIOD("market.{symbol}.kline.{period}", "market\\.[a-zA-Z0-9\\-]{1,15}\\.kline\\.[a-zA-Z0-9]{1,15}",
                                       new HashMap<String, Integer>() {{
        put(MarketParams.SYMBOL, 1);
        put(MarketParams.PERIOD, 3);
    }});

    MarketURL(String value, String regex, HashMap<String, Integer> paramIndexMap) {
        this.value = value;
        this.regex = regex;
        this.paramIndexMap = paramIndexMap;
    }

    /**
     * 模型
     */
    private String value;
    /**
     * 正则表达式
     */
    private String regex;
    /**
     * 参数名称与参数对应的索引
     */
    private HashMap<String, Integer> paramIndexMap;

    public HashMap<String, Integer> getParamIndexMap() {
        return paramIndexMap;
    }

    public void setParamIndexMap(HashMap<String, Integer> paramIndexMap) {
        this.paramIndexMap = paramIndexMap;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

}
