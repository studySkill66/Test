package com.yobtc;

import com.hksy.framework.domain.vo.EntrustFormVO;
import com.yobtc.api.mq.EntrustMQServer;
import com.yobtc.common.ResultCode;
import com.yobtc.common.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.yobtc.api.mq", "com.yobtc.api.ws"})
@RestController
public class WebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }

    @Autowired
    private EntrustMQServer entrustMQServer;

    @PostMapping("/test")
    public ResultJson test(){
        entrustMQServer.sendEntrustFormQueue(new EntrustFormVO("btc_usdt",10));
        return new ResultJson(ResultCode.OK);
    }
}
