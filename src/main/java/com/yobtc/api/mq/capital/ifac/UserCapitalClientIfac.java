package com.yobtc.api.mq.capital.ifac;

import com.hksy.framework.annotaion.MethodType;
import com.hksy.framework.annotaion.Token;
import com.yobtc.api.mq.capital.forback.UserCapitalForback;
import com.yobtc.common.DataList;
import com.yobtc.common.DataModel;
import com.yobtc.domain.FinanceRechargeRmb;
import com.yobtc.domain.FinanceWallet;
import com.yobtc.domain.vo.C2cSellCoinTradeCapitalVO;
import com.yobtc.domain.vo.FinanceBaseInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "yobtc-capital-service-biz", fallbackFactory = UserCapitalForback.class)
public interface UserCapitalClientIfac {

    /**
     * 查询用户是否免手续费
     */
    @RequestMapping(value = "/customerCoinFreeCharge/v1/isFreeCharge", method = RequestMethod.GET)
    DataModel<Boolean> getIsUserCoinFreeCharge(@RequestParam("userId") Integer userId,
                                               @RequestParam("coinId") Integer coinId);
}
