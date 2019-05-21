package com.yobtc.api.mq.kline.ifac;

import com.yobtc.api.mq.kline.forback.KlineForback;
import com.yobtc.common.DataModel;
import com.yobtc.domain.TradeClinch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "yobtc-kline-service-biz", fallbackFactory = KlineForback.class)
public interface KlineIfac {


    /**
     * 查询用户感兴趣的货币市场
     * @return
     */
    @RequestMapping(value = "/kline/v1/selectKlineAdd", method = RequestMethod.POST)
    DataModel selectKlineAdd(@RequestBody TradeClinch tradeClinch);
}
