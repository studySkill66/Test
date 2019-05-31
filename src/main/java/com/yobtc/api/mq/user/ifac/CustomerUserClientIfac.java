package com.yobtc.api.mq.user.ifac;

import com.yobtc.api.mq.user.forback.CustomerUserClientForback;
import com.yobtc.common.DataModel;
import com.yobtc.domain.CustomerSecurityPolicy;
import com.yobtc.domain.CustomerUser;
import com.yobtc.domain.vo.LevelAndAuthVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yobtc-user-service-base", fallbackFactory = CustomerUserClientForback.class)
public interface CustomerUserClientIfac {

    /**
     * 根据服务用户Id查询会员等级和权限
     * @param userId
     * @return
     */
    @RequestMapping(value = "/level/v1/getLevelAndAuthByUserId", method = RequestMethod.GET)
    DataModel<LevelAndAuthVO> getLevelAndAuthByUserId(@RequestParam("userId") Integer userId);

    /**
     * 根据主键查询用户安全策略
     */
    @RequestMapping(value = "/security/v1/getSecurityPolicyById", method = RequestMethod.GET)
    DataModel<CustomerSecurityPolicy> getCustomerSecurityPolicyById(@RequestParam("id") Integer id);
}
