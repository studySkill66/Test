package com.yobtc.api.mq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hksy.framework.common.ResultJson;
import com.hksy.framework.domain.vo.EntrustFormVO;
import com.hksy.framework.domain.vo.TradeEntrustVO;
import com.yobtc.api.tools.MarketParams;
import com.yobtc.api.tools.MarketURL;
import com.yobtc.api.tools.ZipUtil;
import com.yobtc.api.ws.MarketWebSocket;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * 监听activeMQ的消息,将消息使用websocket服务发送给已经订阅了的前端用户
 */
@Component
@Slf4j
public class EntrustMQServer {

    @Autowired
    private JmsTemplate jmsTemplate;

    //监听委托单与币种信和用户信息点对点模式
    @JmsListener(destination = EntrustFormVO.LISTENET_ENTRUSTFORM_QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void listenetEntrustFormQueue(EntrustFormVO entrustFormVO) throws EncodeException, IOException {
        if(entrustFormVO == null){
            log.info("委托单查询监听对象为空异常!!!");
            return;
        }
        this.sendEntrustFormTopic(entrustFormVO);
    }

    //解决集群多台服务器问题,监听委托单与币种信和用户信息发布订阅模式
    @JmsListener(destination = EntrustFormVO.LISTENET_ENTRUSTFORM_TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void listenetEntrustFormTopic(EntrustFormVO entrustFormVO) throws EncodeException, IOException {
        //查询委托单订阅的用户
        CopyOnWriteArraySet<MarketWebSocket> socketsEntrust = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_ENTRUST_PERIOD.getValue());
        //查币种和用户信息订阅的用户
        CopyOnWriteArraySet<MarketWebSocket> socketsWallet = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_USERWALLET_PERIOD.getValue());
        try {
            //查询委托单订阅被订阅的用户,将委托单信息全量推送给前端
            this.entrustQueue(entrustFormVO, socketsEntrust);
        } catch (Exception e) {
            log.error("查询委托单订阅被订阅的用户,将委托单信息全量推送给前端异常:",e);
        }

        try {
            //币种信息和用户信息被订阅的用户,将币种信息和用户信息推送给前端
            this.walletQueue(entrustFormVO, socketsWallet);
        } catch (Exception e) {
            log.error("币种信息和用户信息被订阅的用户,将币种信息和用户信息推送给前端异常:",e);
        }
    }

    //将监听委托单与币种信和用户信息使用topic发送MQ消息,解决集群引发的queue模式下出现的漏推给前端用户的情况
    public void sendEntrustFormTopic(EntrustFormVO entrustFormVO){
        log.info("【前端】刷新委托单与币种和用户信息数据," + EntrustFormVO.LISTENET_ENTRUSTFORM_TOPIC + "发布订阅模式成产消息：" + JSONObject.toJSONString(entrustFormVO));
        jmsTemplate.convertAndSend(new ActiveMQTopic(EntrustFormVO.LISTENET_ENTRUSTFORM_TOPIC),entrustFormVO);
    }

    //点对点发送消息
    public void sendEntrustFormQueue(EntrustFormVO entrustFormVO){
        log.info("点对点发送消息," + EntrustFormVO.LISTENET_ENTRUSTFORM_QUEUE + "发布订阅模式成产消息：" + JSONObject.toJSONString(entrustFormVO));
        jmsTemplate.convertAndSend(new ActiveMQQueue(EntrustFormVO.LISTENET_ENTRUSTFORM_QUEUE),entrustFormVO);
    }

    //币种信息和用户信息被订阅的用户,将币种信息和用户信息推送给前端
    private void walletQueue(EntrustFormVO entrustFormVO, CopyOnWriteArraySet<MarketWebSocket> socketsWallet) throws EncodeException, IOException {
        if(CollectionUtils.isEmpty(socketsWallet)){
            log.info("没有用户订阅查币种和用户信息主题!!!");
        }else{
            log.info("查询币种和用户信息订阅主题人数有:" + socketsWallet.size() + "个人。");
            //查询币种和用户信息主题,被哪些用户订阅了
            List<Integer> userIdList = socketsWallet.stream().filter(p -> entrustFormVO.getUserIdList()
                    .contains(Integer.valueOf(p.getParamsMap().get(MarketParams.USERID).toString())) &&
                                entrustFormVO.getSymbol().equals(p.getParamsMap().get(MarketParams.SYMBOL).toString()))
                    .map(p -> Integer.valueOf(p.getParamsMap().get(MarketParams.USERID).toString()))
                    .collect(Collectors.toList());

            if (userIdList == null || userIdList.size() == 0) {
                log.info("监听到的查询币种和用户信息主题没有用户订阅!!!");
                return;
            }

            //订阅查询币种和用户信息主题的用户,并推送给前端
            for(MarketWebSocket mws : socketsWallet){
                Object userIdObject = mws.getParamsMap().get(MarketParams.USERID);
                if(!ObjectUtils.allNotNull(userIdObject)){
                    log.info("查询币种和用户信息主题,用户Id为空userId!!!");
                    continue;
                }
                Object symbolObject = mws.getParamsMap().get(MarketParams.SYMBOL);
                if(!ObjectUtils.allNotNull(symbolObject)){
                    log.info("查询委托单主题,委托单交易对为空symbo!!!");
                    continue;
                }

                Integer userId = Integer.valueOf(String.valueOf(userIdObject));
                String symbol = String.valueOf(symbolObject);
                if (userIdList.contains(userId) && StringUtils.isNotBlank(symbol)) {
                    String[] symbolName = symbol.split("_");
                    if(symbolName.length == 0){
                        log.info("订阅查询币种和用户信息主题交易对参数异常!!!");
                        continue;
                    }
                    //调用查询数据服务
                    //com.yobtc.common.ResultJson<Map<String, Object>> resultJson = financeWalletService.selectCoinTradeInfoAndWalletInfo(symbolName[0],symbolName[1],userId);
                    //将数据压缩并推送给前端
                    //ZipUtil.gzAndSendWesocket(mws.getSession(), resultJson);
                }
            }
        }
    }

    //查询委托单订阅被订阅的用户,将委托单信息全量推送给前端
    private void entrustQueue(EntrustFormVO entrustFormVO, CopyOnWriteArraySet<MarketWebSocket> socketsEntrust) throws EncodeException, IOException {
        if(CollectionUtils.isEmpty(socketsEntrust)) {
            log.info("没有用户订阅查询委托单主题!!!");
        }else {
            log.info("查询委托单订阅主题人数有:" + socketsEntrust.size() + "个人。");
            //查询委托单主题,被哪些用户订阅了
            List<Integer> userIdList = socketsEntrust.stream().filter(p -> entrustFormVO.getUserIdList()
                    .contains(Integer.valueOf(p.getParamsMap().get(MarketParams.USERID).toString())) &&
                    entrustFormVO.getSymbol().equals(p.getParamsMap().get(MarketParams.SYMBOL).toString())&&
                    !entrustFormVO.isUserEntrustFlag())
                    .map(p -> Integer.valueOf(p.getParamsMap().get(MarketParams.USERID).toString()))
                    .collect(Collectors.toList());

            if (userIdList == null || userIdList.size() == 0) {
                log.info("监听到的查询委托单主题没有用户订阅!!!");
                return;
            }

            //订阅了查询委托单主题的用户,并推送给前端
            for (MarketWebSocket mws : socketsEntrust) {
                if (mws.getSession() != null) {
                    Object userIdObject = mws.getParamsMap().get(MarketParams.USERID);
                    if(!ObjectUtils.allNotNull(userIdObject)){
                        log.info("查询委托单主题,委托单用户Id为空userId!!!");
                        continue;
                    }
                    Object symbolObject = mws.getParamsMap().get(MarketParams.SYMBOL);
                    if(!ObjectUtils.allNotNull(symbolObject)){
                        log.info("查询委托单主题,委托单交易对为空symbo!!!");
                        continue;
                    }
                    Object typeObject = mws.getParamsMap().get(MarketParams.TYPE);
                    if(!ObjectUtils.allNotNull(typeObject)){
                        log.info("查询委托单主题,委托单类型对为空type!!!");
                        continue;
                    }
                    Object statusObject = mws.getParamsMap().get(MarketParams.STATUS);
                    if(!ObjectUtils.allNotNull(statusObject)){
                        log.info("查询委托单主题,委托单状态对为空type!!!");
                        continue;
                    }

                    Integer userId = Integer.valueOf(String.valueOf(userIdObject));
                    String symbol = String.valueOf(symbolObject);
                    Byte type = Byte.valueOf(String.valueOf(typeObject));
                    String status = String.valueOf(statusObject);
                    if (userIdList.contains(userId)) {
                        //调用查询数据服务
                        //ResultJson<TradeEntrustVO> resultJson = tradeClientIfac.userEntrustListWebsocket(userId, symbol, type, status);
                        //将数据压缩并推送给前端
                        //ZipUtil.gzAndSendWesocket(mws.getSession(), resultJson);
                    }
                }
            }
        }
    }

}
