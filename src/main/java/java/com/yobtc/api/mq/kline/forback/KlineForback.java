package com.yobtc.api.mq.kline.forback;

import com.alibaba.nacos.client.logger.Logger;
import com.alibaba.nacos.client.logger.LoggerFactory;
import com.yobtc.api.mq.kline.ifac.KlineIfac;
import com.yobtc.common.DataModel;
import com.yobtc.common.ResultCode;
import com.yobtc.domain.TradeClinch;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KlineForback implements FallbackFactory<KlineIfac> {

    private static final Logger logger = LoggerFactory.getLogger(KlineForback.class);

    @Override
    public KlineIfac create(Throwable throwable) {
        return new KlineIfac() {
            @Override
            public DataModel selectKlineAdd(TradeClinch tradeClinch) {
                DataModel json = new DataModel();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setModel(null);
                return json;
            }
        };
    }
}
