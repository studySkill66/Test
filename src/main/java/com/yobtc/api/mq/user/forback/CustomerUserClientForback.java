package com.yobtc.api.mq.user.forback;

import com.yobtc.api.mq.user.ifac.CustomerUserClientIfac;
import com.yobtc.common.DataModel;
import com.yobtc.common.ResultCode;
import com.yobtc.domain.CustomerSecurityPolicy;
import com.yobtc.domain.vo.LevelAndAuthVO;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserClientForback implements FallbackFactory<CustomerUserClientIfac> {

    private static final Logger logger = LoggerFactory.getLogger(CustomerUserClientForback.class);

    @Override
    public CustomerUserClientIfac create(Throwable throwable) {
        logger.info("CustomerUserClientForback调用异常熔断，异常原因",throwable);
        return new CustomerUserClientIfac(){
            @Override
            public DataModel<LevelAndAuthVO> getLevelAndAuthByUserId(Integer userId) {
                DataModel<LevelAndAuthVO> json = new DataModel<>();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setModel(new LevelAndAuthVO());
                return json;
            }
            /**
             * 根据主键查询用户安全策略
             */
            @Override
            public DataModel<CustomerSecurityPolicy> getCustomerSecurityPolicyById(Integer id) {
                DataModel<CustomerSecurityPolicy> json = new DataModel<>();
                json.setCode(ResultCode.SERVICE_INVOKE_ERROR.getCode());
                json.setMessage(ResultCode.SERVICE_INVOKE_ERROR.getMessage());
                json.setModel(new CustomerSecurityPolicy());
                return json;
            }
        };

    }
}