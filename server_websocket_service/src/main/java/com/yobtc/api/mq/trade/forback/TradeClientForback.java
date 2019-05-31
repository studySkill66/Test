package com.yobtc.api.mq.trade.forback;

import com.hksy.framework.common.ResultJson;
import com.hksy.framework.domain.vo.TradeEntrustVO;
import com.yobtc.api.mq.trade.ifac.TradeClientIfac;
import com.yobtc.common.DataModel;
import com.yobtc.common.ResultCode;
import com.yobtc.domain.FinanceWallet;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TradeClientForback implements FallbackFactory<TradeClientIfac>{
    @Override
    public TradeClientIfac create(Throwable throwable) {
        log.info("TradeForback调用异常熔断，异常原因",throwable);
        return new TradeClientIfac(){
            @Override
            public DataModel<FinanceWallet> selectWalletInfo(Integer userId, Integer coinId) {
                DataModel json = new DataModel();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setModel(null);
                return json;
            }

            @Override
            public ResultJson<TradeEntrustVO> userEntrustListWebsocket(Integer userId, String symbol, Byte type, String status) {
                ResultJson json = new ResultJson();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setModel(null);
                return json;
            }
        };
    }
}
