package com.yobtc.api.mq.wallet;


import com.yobtc.common.ResultJson;

import java.util.Map;

public interface FinanceWalletService {

    /**
     * 查询表单币种交易、用户信息
     * @param coinName
     * @param payCoinName
     * @return
     */
    ResultJson<Map<String, Object>> selectCoinTradeInfoAndWalletInfo(String coinName, String payCoinName, Integer userId);
}
