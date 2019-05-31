package com.hksy.framework.util;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtils {

    @Bean
    @LoadBalanced
        //必须要加上，它不仅进行负载均衡，默认是轮询调用，还要从注册中心获取服务列表地址
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
