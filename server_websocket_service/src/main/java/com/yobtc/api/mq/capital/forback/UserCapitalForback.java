package com.yobtc.api.mq.capital.forback;

import com.yobtc.api.mq.capital.ifac.UserCapitalClientIfac;
import com.yobtc.common.DataList;
import com.yobtc.common.DataModel;
import com.yobtc.common.ResultCode;
import com.yobtc.domain.FinanceRechargeRmb;
import com.yobtc.domain.FinanceWallet;
import com.yobtc.domain.vo.C2cSellCoinTradeCapitalVO;
import com.yobtc.domain.vo.FinanceBaseInfoVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/7 0007.
 */
@Component
@Slf4j
public class UserCapitalForback implements FallbackFactory<UserCapitalClientIfac> {

    @Override
    public UserCapitalClientIfac create(Throwable throwable) {
        log.error("UserCapitalForback调用异常熔断，异常原因",throwable);
        return new UserCapitalClientIfac() {
            @Override
            public DataModel<Boolean> getIsUserCoinFreeCharge(Integer userId, Integer coinId) {
                DataModel<Boolean> json = new DataModel<>();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setModel(false);
                return json;
            }
        };
    }
}
