package com.yobtc.api.mq.product.forback;

import com.yobtc.api.mq.product.ifac.FinanceCoinBaseInfoIfac;
import com.yobtc.common.DataModel;
import com.yobtc.common.ResultCode;
import com.yobtc.domain.vo.FinanceCoinBaseInfo;
import com.yobtc.domain.vo.FinanceCoinTradeInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FinanceCoinBaseInfoForback implements FallbackFactory<FinanceCoinBaseInfoIfac> {
    private static final Logger logger = LoggerFactory.getLogger(FinanceCoinBaseInfoForback.class);

    @Override
    public FinanceCoinBaseInfoIfac create(Throwable throwable) {
        logger.info("FinanceCoinBaseInfoForback异常熔断，原因：", throwable);
        return new FinanceCoinBaseInfoIfac() {
            @Override
            public DataModel<FinanceCoinBaseInfo> getCoinInfoByCoinName(String coinEnName) {
                DataModel<FinanceCoinBaseInfo> json = new DataModel<>();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setModel(new FinanceCoinBaseInfo());
                return json;
            }

            @Override
            public DataModel<FinanceCoinTradeInfo> getFinanceCoinTradeInfo(Integer coinId, Integer payCoinId) {
                DataModel<FinanceCoinTradeInfo> json = new DataModel<>();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setModel(new FinanceCoinTradeInfo());
                return json;
            }
        };
    }
}