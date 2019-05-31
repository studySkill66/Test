package com.hksy.framework.util;

import com.hksy.framework.cache.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 币种工具类
 */
@Component
public class CurrencyUtils {

    private static RedisCache cache;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RestTemplate restTemplate;

    private static RestTemplate rest;

    @PostConstruct
    public void init() {
        cache = redisCache;
        rest = restTemplate;
    }

    /**
     * 币种redis中的key
     */
    public final static String TRADE_COPE_KEY = "tradeCopeCoinIdMap";

    public static final Long USDT_COIN_ID = 69L;

    /***
     * 支付币种
     */
    public static Map<Long, String> payCurrencyMap = new HashMap<>();

    static {
        payCurrencyMap.put(USDT_COIN_ID, "USDT");
    }

    /**
     * 根据币种名称,拼接交易对
     *
     * @param coinName
     * @param payCoinName
     * @return
     */
    public static String tradeCopeName(String coinName, String payCoinName) {
        return coinName.replace(".", "").toLowerCase() + "_" + payCoinName.toLowerCase();
    }

    /**
     * 根据币种id,拼接交易对
     *
     * @param coinId
     * @param payCoinId
     * @return
     */
    public static String tradeCopeId(Long coinId, Long payCoinId) {
        Map<String, String> coinMap = cache.getObject(TRADE_COPE_KEY, HashMap.class);
        if(coinMap == null || coinMap.size() == 0) {
            String url = "http://yobtc-trade-service-base/clinch/v1/currencyDepositRedis";
            rest.getForObject(url, List.class);
            coinMap = cache.getObject(TRADE_COPE_KEY, HashMap.class);
        }
        String coinName = coinMap.get(String.valueOf(coinId));
        String payCoinName = payCurrencyMap.get(payCoinId);
        if(coinName == null || payCoinName == null){
            return null;
        }
        return tradeCopeName(coinName, payCoinName).toLowerCase();
    }

}
