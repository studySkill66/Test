package com.yobtc.api.mq.kline.forback;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import com.yobtc.api.mq.kline.ifac.KlineIfac;
import com.yobtc.common.DataList;
import com.yobtc.common.DataModel;
import com.yobtc.common.ResultCode;
import com.yobtc.domain.TradeClinch;
import com.yobtc.domain.vo.KLineDetailVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KlineForback implements FallbackFactory<KlineIfac> {

    private static final Logger logger = LoggerFactory.getLogger(KlineForback.class);


    @Override
    public KlineIfac create(Throwable throwable) {
        logger.info("KlineForback调用异常熔断，异常原因",throwable);
        return new KlineIfac() {
            @Override
            public DataList<KLineDetailVo> selectKlineAdd(TradeClinch tradeClinch) {
                DataList<KLineDetailVo> json = new DataList<KLineDetailVo>();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setResultSet(null);
                return json;
            }
        };
    }
}
