package com.yobtc.api.mq.trade.ifac;

import com.hksy.framework.common.ResultJson;
import com.hksy.framework.domain.vo.TradeEntrustVO;
import com.hksy.framework.domain.vo.TradeEntrustWebSocketVO;
import com.yobtc.api.mq.trade.forback.TradeClientForback;
import com.yobtc.common.DataModel;
import com.yobtc.domain.FinanceWallet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yobtc-trade-service-base", fallbackFactory = TradeClientForback.class)
public interface TradeClientIfac {

    /**
     *
     * @param userId
     * @param symbol
     * @param type
     * @param status
     * @return
     */
    @PostMapping(value = "/entrust/v1/userEntrustListWebsocket")
    ResultJson<TradeEntrustVO> userEntrustListWebsocket(@RequestParam("userId") Integer userId, @RequestParam("symbol") String symbol,
                                                                 @RequestParam("type") Byte type, @RequestParam("status") String status);

    /**
     * 根据用户ID币种ID查询钱包信息
     * @param userId
     * @param coinId
     * @return
     */
    @RequestMapping(value = "/entrust/v1/selectWalletInfo", method = RequestMethod.GET)
    DataModel<FinanceWallet> selectWalletInfo(
            @RequestParam("userId") Integer userId, @RequestParam("coinId") Integer coinId);
}
