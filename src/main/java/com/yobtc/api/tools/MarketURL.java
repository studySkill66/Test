package com.yobtc.api.tools;

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
    MARKET_SYMBOL_TRADE_DETAIL("market.%s.trade.detail", "market\\.[a-zA-Z0-9\\-]{1,15}_[a-zA-Z0-9\\-]{1,15}\\.trade\\.detail",
            new HashMap<String, Integer>() {{
                put(MarketParams.SYMBOL, 1);
            }}),
    MARKET_SYMBOL_DETAIL("market.%s.detail", "market\\.[a-zA-Z0-9\\-]{1,15}_[a-zA-Z0-9\\-]{1,15}\\.detail", new HashMap<String, Integer>() {{
        put(MarketParams.SYMBOL, 1);
    }}),
    MARKET_SYMBOL_DEPTH_TYPE("market.%s.depth.%s", "market\\.[a-zA-Z0-9\\-]{1,15}_[a-zA-Z0-9\\-]{1,15}\\.depth\\.[a-zA-Z0-9]{1,15}", new HashMap<String, Integer>() {{
        put(MarketParams.SYMBOL, 1);
        put(MarketParams.TYPE, 3);
    }}),
    MARKET_DETAIL_LIST_PAYCOINID("market.detail.paycoinid.%s.userid.%s", "market\\.detail\\.paycoinid\\.[0-9]{1,3}\\.userid\\.[0-9]{1,11}",
            new HashMap<String, Integer>() {{
                put(MarketParams.PAY_COIN_ID, 3);
                put(MarketParams.USERID,5);
            }}),
    MARKET_SYMBOL_KLINE_PERIOD("market.%s.kline.%s", "market\\.[a-zA-Z0-9\\-]{1,15}_[a-zA-Z0-9\\-]{1,15}\\.kline\\.[a-zA-Z0-9]{1,15}",
                                       new HashMap<String, Integer>() {{
        put(MarketParams.SYMBOL, 1);
        put(MarketParams.PERIOD, 3);
    }}),

    MARKET_SYMBOL_ENTRUST_PERIOD("market.%s.entrust.%s", "market\\.[a-zA-Z0-9\\-]{1,15}_[a-zA-Z0-9\\-]{1,15}\\.entrust\\.[a-zA-Z0-9]{1,15}\\.[\\-a-zA-Z0-9]{1,15}\\.[a-zA-Z0-9\\,]{1,15}",
            new HashMap<String, Integer>() {{
                put(MarketParams.SYMBOL, 1);
                put(MarketParams.USERID, 3);
                put(MarketParams.TYPE, 4);
                put(MarketParams.STATUS, 5);
            }}),

    MARKET_SYMBOL_USERWALLET_PERIOD("market.%s.userwallet.%s", "market\\.[a-zA-Z0-9\\-]{1,15}_[a-zA-Z0-9\\-]{1,15}\\.userwallet\\.[a-zA-Z0-9]{1,15}",
            new HashMap<String, Integer>() {{
                put(MarketParams.SYMBOL, 1);
                put(MarketParams.USERID, 3);
            }});

    MarketURL(String value, String regex, HashMap<String, Integer> paramIndexMap) {
        this.value = value;
        this.regex = regex;
        this.paramIndexMap = paramIndexMap;
    }

    /**
     * 主题模板，%s参数占位符
     */
    private String value;
    /**
     * 主题校验的正则表达式
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
