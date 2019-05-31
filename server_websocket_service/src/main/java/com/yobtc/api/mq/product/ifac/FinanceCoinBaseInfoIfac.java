package com.yobtc.api.mq.product.ifac;

import com.yobtc.api.mq.product.forback.FinanceCoinBaseInfoForback;
import com.yobtc.common.DataModel;
import com.yobtc.domain.vo.FinanceCoinBaseInfo;
import com.yobtc.domain.vo.FinanceCoinTradeInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yobtc-product-service-base", fallbackFactory = FinanceCoinBaseInfoForback.class)
public interface FinanceCoinBaseInfoIfac {

    /**
     * 根据币种英文名查询币种基础信息
     * @param coinEnName 币种英文名称
     * @return
     */
    @RequestMapping(value = "/coinBaseInfo/v1/getCoinInfoByCoinName", method = RequestMethod.GET)
    DataModel<FinanceCoinBaseInfo> getCoinInfoByCoinName(@RequestParam("coinEnName") String coinEnName);

    /**
     * 根据币种ID和支付币种ID查询币种交易信息
     * @param coinId
     * @param payCoinId
     * @return
     */
    @RequestMapping(value = "/coinDetail/v1/getFinanceCoinTradeInfo", method = RequestMethod.GET)
    DataModel<FinanceCoinTradeInfo> getFinanceCoinTradeInfo(
            @RequestParam("coinId") Integer coinId, @RequestParam("payCoinId") Integer payCoinId);
}
