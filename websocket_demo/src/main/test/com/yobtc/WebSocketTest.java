package com.yobtc;

import com.hksy.framework.common.ResultJson;
import com.hksy.framework.domain.vo.TradeEntrustVO;
import com.yobtc.api.mq.trade.ifac.TradeClientIfac;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebSocketTest.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketTest {

    @Autowired
    private TradeClientIfac tradeClientIfac;

    @Test
    public void testEntrust(){
        Integer userId = 10;
        String symbol = "ETH_usdt";
        Byte type = -1;
        String status = "0,1,2,7";
        ResultJson<TradeEntrustVO> resultJson = tradeClientIfac.userEntrustListWebsocket(userId,symbol,type,status);
        System.out.println(resultJson.getCode()+resultJson.getMessage()+"|"+resultJson.getCount()+"|");
    }
}
