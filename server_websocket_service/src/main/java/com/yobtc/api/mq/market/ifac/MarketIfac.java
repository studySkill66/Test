package com.yobtc.api.mq.market.ifac;

import com.yobtc.api.mq.market.forback.MarketForback;
import com.yobtc.common.DataList;
import com.yobtc.common.DataModel;
import com.yobtc.domain.vo.MarketDepthVo;
import com.yobtc.domain.vo.MarketDetailListVo;
import com.yobtc.domain.vo.MarketDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yobtc-trade-service-base", fallbackFactory = MarketForback.class)
public interface MarketIfac {
    /**
     * 查询最新挂单消息
     */
    @RequestMapping(value = "/are/v1/selectTradeAre", method = RequestMethod.POST)
    DataList<MarketDepthVo> selectTradeAre(@RequestParam("coinId") Integer coinId, @RequestParam("payCoinId") Integer payCoinId, @RequestParam("type") Byte type );

    @RequestMapping(value = "/clinch/v1/selectMarketDetailsWebsocket",method = RequestMethod.POST)
    DataModel<MarketDetailListVo> selectMarketDetails(@RequestParam("coinId") Integer coinId, @RequestParam("payCoinId") Integer payCoinId, @RequestParam(value = "userId",required = false) Integer userId) ;

    /**
     * 查询单条行情详情
     * @return
     */
    @RequestMapping(value = "/clinch/v1/getMarketDetails", method = RequestMethod.POST)
    DataModel<MarketDetailVo> getMarketDetails(@RequestParam("coinId") Integer coinId, @RequestParam("payCoinId") Integer payCoinId);



}
