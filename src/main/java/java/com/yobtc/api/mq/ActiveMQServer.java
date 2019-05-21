package com.yobtc.api.mq;

import com.alibaba.fastjson.JSONArray;
import com.hksy.framework.common.MarketResultJson;
import com.yobtc.api.mq.kline.ifac.KlineIfac;
import com.yobtc.api.mq.market.ifac.MarketIfac;
import com.yobtc.api.tools.MarketParams;
import com.yobtc.api.tools.MarketURL;
import com.yobtc.api.tools.ZipUtil;
import com.yobtc.api.ws.MarketWebSocket;
import com.yobtc.common.DataList;
import com.yobtc.common.DataModel;
import com.yobtc.domain.TradeAre;
import com.yobtc.domain.TradeClinch;
import com.yobtc.domain.TradeDetailResult;
import com.yobtc.domain.vo.MarketDepthVo;
import com.yobtc.domain.vo.MarketDetailListVo;
import com.yobtc.domain.vo.MarketDetailVo;
import com.yobtc.domain.vo.TradeDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
public class ActiveMQServer {


    @Resource
    MarketIfac marketIfac;
    @Resource
    KlineIfac klineIfac;

    @Autowired
    private JmsTemplate jmsTemplate;

    public static final String LISTENET_MARKET_DETAIL_QUEUE ="listener.market_detail.queue";

    public void tradeClinchMessageCompensatio(TradeClinch tradeClinch){
        log.info("行情推送异常，从新加回点对点模式队列:"+LISTENET_MARKET_DETAIL_QUEUE, JSON.toJSONString(tradeClinch));
        jmsTemplate.convertAndSend(new ActiveMQQueue(LISTENET_MARKET_DETAIL_QUEUE),tradeClinch);
    }

    /**
     * 【前端】推送最新成交记录
     * <br/>
     *
     * @param msgResult TradeDetailResult<TradeDetailVo>
     * @author Mr.Lming
     * @date 2019/4/23 19:46
     **/
    @JmsListener(destination = "market.trade.detail.topic",containerFactory = "jmsListenerContainerTopic")
    public void sendTradeDetailWebSocket(TradeDetailResult<TradeDetailVo> msgResult) throws IOException, EncodeException {

        if (msgResult == null || StringUtils.isBlank(msgResult.getSymbol())) {
            return;
        }

        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_TRADE_DETAIL.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketWebSocket : sockets) {
                if (marketWebSocket.getSession() != null) {
                    // 接受MQ的交易对 与 当前WebSocket回话连接的交易对一致
                    if (msgResult.getSymbol().equalsIgnoreCase(String.valueOf(marketWebSocket.getParamsMap().get(MarketParams.SYMBOL)))) {
                        MarketResultJson json = new MarketResultJson();
                        json.setCh(String.format(MarketURL.MARKET_SYMBOL_TRADE_DETAIL.getValue(), msgResult.getSymbol()));
                        json.setTs(System.currentTimeMillis());
                        json.setTick(msgResult);
                        ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(), json);
                        log.info("最新成交记录WebSocket推送：" + JSONArray.toJSONString(json));
                    }
                }
            }
        }
    }

    @JmsListener(destination = "market.detail.topic",containerFactory = "jmsListenerContainerTopic")
    public void receive(MarketDetailVo marketDetailVo) throws IOException, EncodeException {
        if (marketDetailVo == null || StringUtils.isBlank(marketDetailVo.getSymbol())) {
            return;
        }
        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_DETAIL.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketWebSocket : sockets) {
                if (marketWebSocket.getSession() != null) {
                    if (marketDetailVo.getSymbol().equalsIgnoreCase(String.valueOf(marketWebSocket.getParamsMap().get(MarketParams.SYMBOL)))) {
                        MarketResultJson json = new MarketResultJson();
                        json.setCh(String.format(MarketURL.MARKET_SYMBOL_DETAIL.getValue(), marketDetailVo.getSymbol()));
                        json.setTick(marketDetailVo);
                        ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(), json);
                    }
                }
            }
        }
    }

    @JmsListener(destination = "marketdetail.list",containerFactory = "jmsListenerContainerQueue")
    public void receive(List<MarketDetailVo> list) throws IOException, EncodeException {
        if (list == null) {
            return;
        }
        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_DETAIL_LIST_PAYCOINID.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketDepthWebSocket : sockets) {
                if (marketDepthWebSocket.getSession() != null) {
                    MarketResultJson json = new MarketResultJson();
                    json.setTick(list);
                    ZipUtil.gzAndSendWesocket(marketDepthWebSocket.getSession(), json);
                }
            }
        }
    }

//    @JmsListener(destination = "user.interest.marketdetail")
//    public void userinterest(MarketDetailListVo listVo) throws EncodeException, IOException {
//        if(listVo.getListVo()==null||listVo.getUserId()==null){
//            log.info("数据为空或者userId为空");
//            return;
//        }
//        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_DETAIL_USER_INTEREST.getValue());
//        if (sockets.size() != 0) {
//            for (MarketWebSocket marketDepthWebSocket : sockets) {
//                if (marketDepthWebSocket.getSession() != null) {
//                    if(marketDepthWebSocket.getParamsMap().get(MarketParams.USERID).equals(listVo.getUserId())) {
//                        MarketResultJson json = new MarketResultJson();
//                        json.setTick(listVo.getListVo());
//                        ZipUtil.gzAndSendWesocket(marketDepthWebSocket.getSession(), json);
//                    }
//                }
//            }
//        }
//    }
    @JmsListener(destination = "market_depth.topic")
    public void receive(MarketDepthVo marketDepthVo) throws IOException, EncodeException {
        if (marketDepthVo == null || StringUtils.isBlank(marketDepthVo.getSymbol())) {
            return;
        }
        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_DEPTH_TYPE.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketDepthWebSocket : sockets) {
                if (marketDepthWebSocket.getSession() != null) {
                    if (marketDepthWebSocket.getParamsMap().get(MarketParams.TYPE).toString().equalsIgnoreCase(marketDepthVo.getType())) {
                        if (marketDepthVo.getSymbol().equalsIgnoreCase(String.valueOf(marketDepthWebSocket.getParamsMap().get(MarketParams.SYMBOL)))) {
                            MarketResultJson json = new MarketResultJson();
                            json.setCh(String.format(MarketURL.MARKET_SYMBOL_DEPTH_TYPE.getValue(), marketDepthVo.getSymbol(), marketDepthWebSocket.getParamsMap().get(MarketParams.TYPE)));
                            json.setTick(marketDepthVo);
                            ZipUtil.gzAndSendWesocket(marketDepthWebSocket.getSession(), json);
                        }
                    }
                }
            }
        }
    }

    @JmsListener(destination = "listener.trade_are.queue",containerFactory = "jmsListenerContainerQueue")
    public void listenerTradeAre(TradeAre tradeAre) throws IOException, EncodeException {
        if (null == tradeAre.getCoinid() || null == tradeAre.getPaycoinid()) {
            log.info("参数为空，推送失败，币种ID：" + tradeAre.getCoinid() + "支付币种ID:" + tradeAre.getPaycoinid());
            return;
        }
        DataList dataList  = marketIfac.selectTradeAre(tradeAre.getCoinid(), tradeAre.getPaycoinid(), tradeAre.getType());//查出来直接在trade推了，后期优化
        if(dataList.getCode()!=0){

        }
    }

    @JmsListener(destination = LISTENET_MARKET_DETAIL_QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void listenerMarketDetail(TradeClinch tradeClinch) throws IOException, EncodeException {
        if (null == tradeClinch.getCoinid() || null == tradeClinch.getAlternatefield()) {
            log.info("参数为空，推送失败，币种ID：" + tradeClinch.getCoinid() + "支付币种ID:" + tradeClinch.getAlternatefield());
            return;
        }
        DataModel<MarketDetailListVo> marketDetailsModel = new DataModel<>();
        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_DETAIL_LIST_PAYCOINID.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketDepthWebSocket : sockets) {
                if (marketDepthWebSocket.getSession() != null) {
                   Integer paycoinId = Integer.valueOf(marketDepthWebSocket.getParamsMap().get(MarketParams.PAY_COIN_ID).toString().toString());
                   Integer userId = Integer.valueOf(marketDepthWebSocket.getParamsMap().get(MarketParams.USERID).toString());
                   if(userId.equals("0")){//0代表查所有其余的就是按登录的userId来算
                       userId = null;
                   }
                    marketDetailsModel =marketIfac.selectMarketDetails(tradeClinch.getCoinid(), tradeClinch.getAlternatefield(),userId);//查出来直接在trade推了，后期优化
                    DataModel dataModel =klineIfac.selectKlineAdd(tradeClinch);//查出来直接在kline推送
                    if(marketDetailsModel.getCode()!=0){//没异常，并且数据不为空才推送
                        if(marketDetailsModel.getModel().getListVo().size()!=0&& marketDetailsModel.getModel().getMarketDetailVo()!=null){
                            if(marketDetailsModel.getModel().getPayCoinId().equals(paycoinId)){
                                MarketResultJson allJson = new MarketResultJson();
                                allJson.setTick(marketDetailsModel.getModel().getListVo());//推全部
                                ZipUtil.gzAndSendWesocket(marketDepthWebSocket.getSession(), allJson);
                                if(userId!=null){
                                    if(marketDetailsModel.getModel().getUserInterestList().size()!=0){
                                        if(userId.equals(marketDetailsModel.getModel().getUserId())){
                                            MarketResultJson userJson = new MarketResultJson();
                                            allJson.setTick(marketDetailsModel.getModel().getUserInterestList());//推用户关注的
                                            ZipUtil.gzAndSendWesocket(marketDepthWebSocket.getSession(), userJson);
                                        }
                                    }
                                }
                            }
                            MarketDetailVo marketDetailVo = new MarketDetailVo();
                            BeanUtils.copyProperties(marketDetailsModel.getModel().getMarketDetailVo(),marketDetailVo);
                            //向交易中心的行情深度发送消息，然后在那边推送给新交易所以及teleman
                            jmsTemplate.convertAndSend(new ActiveMQTopic("market.detail.topic"),marketDetailVo);
                        }
                    }else{//异常就消息补偿，从新加入队列
                        log.info("行情深度websocket推送程序异常，请查看" + marketDetailsModel.getMessage());
                        tradeClinchMessageCompensatio(tradeClinch);//消息补偿
                    }
                }
            }
        }
//

//        if(marketDetailsModel.getCode()!=0){
//            log.info("行情详情websocket推送程序异常，请查看:" + marketDetailsModel.getMessage());
//        }
//        if(dataModel.getCode()!=0){
//            log.info("K线增量websocket推送程序异常，请查看:" + dataModel.getMessage());
//        }
    }
}
