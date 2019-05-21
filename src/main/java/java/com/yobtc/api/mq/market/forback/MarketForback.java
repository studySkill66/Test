package com.yobtc.api.mq.market.forback;

import com.alibaba.nacos.client.logger.Logger;
import com.alibaba.nacos.client.logger.LoggerFactory;
import com.hksy.framework.common.Result;
import com.yobtc.api.mq.market.ifac.MarketIfac;
import com.yobtc.common.DataList;
import com.yobtc.common.DataModel;
import com.yobtc.common.ResultCode;
import com.yobtc.domain.vo.MarketDepthVo;
import com.yobtc.domain.vo.MarketDetailListVo;
import com.yobtc.domain.vo.MarketDetailVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MarketForback implements FallbackFactory<MarketIfac> {
    private static final Logger logger = LoggerFactory.getLogger(MarketForback.class);
    @Override
    public MarketIfac create(Throwable throwable) {
        logger.info("CoinMarketForback调用异常熔断，异常原因",throwable);
        return new MarketIfac() {
            @Override
            public DataList<MarketDepthVo> selectTradeAre(Integer coinId, Integer payCoinId, Byte type) {
                DataList<MarketDepthVo> json = new DataList<MarketDepthVo>();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setResultSet(null);
                return json;
            }

            @Override
            public DataModel<MarketDetailListVo> selectMarketDetails(Integer coinId, Integer payCoinId, Integer userId) {
                DataModel<MarketDetailListVo> json = new DataModel<MarketDetailListVo>();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setModel(null);
                return json;
            }
        };
    }
}
