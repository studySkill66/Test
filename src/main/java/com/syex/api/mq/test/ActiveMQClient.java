package com.syex.api.mq.test;

import com.yobtc.domain.vo.MarketDepthVo;
import com.yobtc.domain.vo.MarketDetailVo;
import com.yobtc.domain.TradeDetailResult;
import com.yobtc.domain.vo.TradeDetailVo;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

/**
 * @program: syex-api-parent
 * @description:
 * @author: Mr.Lming
 * @create: 2019-04-24 09:12
 **/
@Component
public class ActiveMQClient {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Topic topic;//使用MqConfig累注入topic主题

    public void send(String message) {
        jmsTemplate.convertAndSend("zhisheng", message);
    }

    public void sendToTradeDetailWebSocket(TradeDetailResult<TradeDetailVo> msgResult) {
        jmsTemplate.convertAndSend(topic, msgResult);
    }

    public void test(MarketDetailVo msgResult) {
        jmsTemplate.convertAndSend(new ActiveMQTopic("marketdetail"), msgResult);
    }

    public void test1(MarketDepthVo msgResult) {
        jmsTemplate.convertAndSend(new ActiveMQTopic("marketdepth"), msgResult);
    }
}
