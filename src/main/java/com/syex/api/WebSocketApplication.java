package com.syex.api;

import com.yobtc.domain.TradeDetailResult;
import com.yobtc.domain.vo.TradeDetailVo;
import com.syex.api.mq.test.ActiveMQClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.syex.api.mq", "com.syex.api.ws"})
public class WebSocketApplication {
    private static Logger log = LogManager.getLogger(WebSocketApplication.class);

    @Autowired
    ActiveMQClient client;

    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }

    @GetMapping("/send")
    public void send() throws IOException, TimeoutException {
        client.send("ok");
    }

    @GetMapping("/sendToTradeDetailWebSocket")
    public void sendToTradeDetailWebSocket(String symbol){

        TradeDetailVo detailVo = new TradeDetailVo("146507451359183894799", new BigDecimal(401.74), System.currentTimeMillis(),
                new BigDecimal(0.0099), "buy", System.currentTimeMillis());
        // 消息ID
        String id = (int) (Math.random() * 1000000) + String.valueOf(System.currentTimeMillis()).substring(0, 5);
        TradeDetailResult<TradeDetailVo> result = new TradeDetailResult<>(id, System.currentTimeMillis(),
                detailVo, symbol);
        client.sendToTradeDetailWebSocket(result);
    }
}
