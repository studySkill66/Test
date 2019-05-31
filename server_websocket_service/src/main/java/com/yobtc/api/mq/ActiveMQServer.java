package com.yobtc.api.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.yobtc.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static final String MARKET_DETAIL_TOPIC = "market.detail.topic";

    public static final String MARKET_KLINE_ADD_TOPIC ="market.klineadd.topic";

    public static final String MARKET_DEPTH_TOPIC = "market_depth.topic";

    public static final String SEND_MARKET="market.topic";

    public void tradeClinchMessageCompensatio(TradeClinch tradeClinch){
        log.info("行情推送异常，从新加回点对点模式队列:"+LISTENET_MARKET_DETAIL_QUEUE, JSON.toJSONString(tradeClinch));
        jmsTemplate.convertAndSend(new ActiveMQQueue(LISTENET_MARKET_DETAIL_QUEUE),tradeClinch);
    }

    public void sendMarket(TradeClinch tradeClinch){
        log.info("webScoket集群推送数据>>>>>>");
        jmsTemplate.convertAndSend(new ActiveMQTopic(SEND_MARKET),tradeClinch);
    }

    public final static Map<String,Integer> stepMap = new HashMap<String, Integer>();
    static {
        stepMap.put("1min",60);//1分钟
        stepMap.put("5min",300);//5分钟
        stepMap.put("15min",900);//15分钟
        stepMap.put("30min",1800);//30分钟
        stepMap.put("1hour",3600);//1小时
        stepMap.put("4hour",14400);//4小时
        stepMap.put("1day",86400);//1天
        stepMap.put("1week",604800);//1周
        stepMap.put("1mon",2592000);//1月

    }

    public final static Map<Integer,String> stepMapStr = new HashMap<Integer, String>();
    static {
        stepMapStr.put(60,"1min");//1分钟
        stepMapStr.put(300,"5min");//5分钟
        stepMapStr.put(900,"15min");//15分钟
        stepMapStr.put(1800,"30min");//30分钟
        stepMapStr.put(3600,"1hour");//1小时
        stepMapStr.put(14400,"4hour");//4小时
        stepMapStr.put(86400,"1day");//1天
        stepMapStr.put(604800,"1week");//1周
        stepMapStr.put(2592000,"1mon");//一个月，这里是算的30天
    }
    @JmsListener(destination = SEND_MARKET,containerFactory = "jmsListenerContainerTopic")
    public void setSendMarketWebSocket(TradeClinch tradeClinch) throws IOException, EncodeException {
        ///-----------------k线
        klineWebSocket(tradeClinch);
        //------------------交易中心
        marketDetailWebSocket(tradeClinch.getCoinid(),tradeClinch.getAlternatefield());
        //------------------首页行情包括用户关注的
        marketDetailListWebSocket(tradeClinch.getCoinid(),tradeClinch.getAlternatefield());
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

    /**
     * @param marketDepthVo
     * @throws IOException
     * @throws EncodeException
     */
    @JmsListener(destination = MARKET_DEPTH_TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void receive(MarketDepthVo marketDepthVo) throws IOException, EncodeException {
        log.info("挂单ws取出数据：" + JSONObject.toJSONString(marketDepthVo));
        if (marketDepthVo == null || StringUtils.isBlank(marketDepthVo.getSymbol())) {
            return;
        }
        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_DEPTH_TYPE.getValue());
        if (sockets.size() != 0) {
            log.info("挂单主题订阅任务有: "+ sockets.size()+"个人。");
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


    @JmsListener(destination = LISTENET_MARKET_DETAIL_QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void listenerMarketDetail(TradeClinch tradeClinch) throws IOException, EncodeException {
        if (null == tradeClinch.getCoinid() || null == tradeClinch.getAlternatefield()) {
            log.info("参数为空，推送失败，币种ID：" + tradeClinch.getCoinid() + "支付币种ID:" + tradeClinch.getAlternatefield());
            return;
        }
        sendMarket(tradeClinch);
    }

    /**
     * 首页行情和用户关注行情推送
     * @param coinId
     * @param payCoinId
     * @throws EncodeException
     * @throws IOException
     */
    public void marketDetailListWebSocket(Integer coinId,Integer payCoinId) throws EncodeException, IOException {
        int num = 0;
        int error = 0;
        int count = 0;
        try {
            DataModel<MarketDetailListVo> marketDetailsModel = new DataModel<MarketDetailListVo>();
            CopyOnWriteArraySet<MarketWebSocket> detailListSocket = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_DETAIL_LIST_PAYCOINID.getValue());

            //推首页
            if (detailListSocket.size() != 0) { //首页订阅的人不等于0
                log.info("首页行情订阅主题人数有:"+detailListSocket.size()+"个人。");
                for (MarketWebSocket marketDepthWebSocket : detailListSocket) {
                    if (marketDepthWebSocket.getSession() != null) {
                        //先推送交易中心
                        Integer paycoinId = Integer.valueOf(marketDepthWebSocket.getParamsMap().get(MarketParams.PAY_COIN_ID).toString().toString());
                        Integer userId = Integer.valueOf(marketDepthWebSocket.getParamsMap().get(MarketParams.USERID).toString());
                        if (userId == 0) {//0代表查所有其余的就是按登录的userId来算
                            userId = null;
                        }
                        marketDetailsModel = marketIfac.selectMarketDetails(coinId, payCoinId, userId);//查出来直接在trade推了，后期优化
                        if (marketDetailsModel.getCode() == 0 ) {//没异常，并且数据都不为空才推送
                            Integer paycoinIds = marketDetailsModel.getModel().getPayCoinId();
                            if (paycoinIds.equals(paycoinId)) {
                                MarketResultJson allJson = new MarketResultJson();
                                allJson.setTick(marketDetailsModel.getModel().getListVo());//推全部
                                allJson.setCh("all");
                                log.info("开始推送首页行情支付币种ID为:" + paycoinId + "币种信息");
                                ZipUtil.gzAndSendWesocket(marketDepthWebSocket.getSession(), allJson);
                                log.info("结束推送首页行情支付币种ID为:" + paycoinId + "币种信息");

                                if (userId != null) {//userId不等于null 说明用户登录了，就要给用户关注的也推一下
                                    if (null != marketDetailsModel.getModel().getUserInterestList()) {
                                        if (userId.equals(marketDetailsModel.getModel().getUserId())) {
                                            MarketResultJson userJson = new MarketResultJson();
                                            userJson.setCh("interest");
                                            userJson.setTick(marketDetailsModel.getModel().getUserInterestList());//推用户关注的
                                            log.info("开始推送用户关注行情，用户ID为:" + userId + "关注的币种信息");
                                            ZipUtil.gzAndSendWesocket(marketDepthWebSocket.getSession(), userJson);
                                            log.info("结束推送用户关注行情，用户ID为:" + userId + "关注的币种信息");
                                        }
                                    }
                                }
                                num++;
                            }else{
                                count++;
                                log.info("首页行情没匹配上的有："+count+"条");
                            }
                        }
                    }else{
                        log.info("首页行情列表订阅成功了，但是session为null："+JSONObject.toJSONString(marketDepthWebSocket));
                        error++;
                    }
                }
                log.info("首页行情一共推送了"+num+"次给前端，有"+error+"次没有推。一起有"+detailListSocket.size()+"个人订阅了这个主题");
            }else{
                log.info("没有用于订阅首页行情主题>>>>>>>>>>>>>");
            }
        }catch (Exception e){
           log.info("首页行情推送异常，请查看："+e);
        }
    }

    /**
     * K线增量推送
     * @param tradeClinch
     * @throws IOException
     * @throws EncodeException
     */
    public void klineWebSocket(TradeClinch tradeClinch) throws IOException, EncodeException {
        //k线
        //向K线图发送消息，然后在哪里推送给交易所以及teleman
        try {
            DataList<KLineDetailVo> dataList = klineIfac.selectKlineAdd(tradeClinch);
            CopyOnWriteArraySet<MarketWebSocket> klineSocket = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_KLINE_PERIOD.getValue());
            if (klineSocket.size() != 0) {
                log.info("K线增量ws取出数据: " + JSONArray.toJSONString(dataList.getResultSet()));
                if (dataList.getCode()!=0) {
                    return;
                }
                int count = 0;
                int error = 0;
                int num = 0;
                log.info("K线增量主题订阅任务有: " + klineSocket.size() + "个人。");
                for (MarketWebSocket marketWebSocket : klineSocket) {
                    if (marketWebSocket.getSession() != null) {
                        Integer step = stepMap.get(marketWebSocket.getParamsMap().get(MarketParams.PERIOD));
                        // 接受MQ的交易对 与 当前WebSocket回话连接的交易对一致
                        for (KLineDetailVo vo : dataList.getResultSet()) {
                            if (vo.getSymbol() != null) {
                                String sysbol = vo.getSymbol().toLowerCase();
                                String websysbol = String.valueOf(marketWebSocket.getParamsMap().get(MarketParams.SYMBOL)).toLowerCase();
                                if (vo.getSymbol().equals(websysbol) && step.equals(vo.getStep())) {//判断session交易对以及时间是不是跟推送过来的数据相等，相等就推送
                                    MarketResultJson json = new MarketResultJson<KLineDetailVo>();
                                    json.setCh(String.format(MarketURL.MARKET_SYMBOL_KLINE_PERIOD.getValue(), vo.getSymbol(), stepMapStr.get(vo.getStep())));
                                    json.setTs(System.currentTimeMillis());
                                    json.setTick(vo);
                                    ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(), json);
                                    count++;
                                    log.info("最新成交KLine推送WebSocket服务：" + vo.getSymbol());
                                }else{
                                    log.info("订阅的交易对为："+websysbol+",最新成交的交易对为："+sysbol+"。订阅的时间为："+step+",返回的交易对为："+vo.getStep());
                                }
                            }
                        }
                    }else{
                        log.info("K线增量订阅成功了，但是session为null："+JSONObject.toJSONString(marketWebSocket));
                        error++;
                    }
                }
                log.info("K线增量一共推送了" + count + "次给前端，有"+error+"次没有推。一起有" + klineSocket.size() + "个人订阅了这个主题");
            }else{
                log.info("没有用户订阅K线增量主题>>>>>>>>>>>>>>");
            }
        } catch (Exception e) {
            log.info("K线推送数据异常，请查看 :"+e);
        }
    }
    public void marketDetailWebSocket(Integer coinId,Integer payCoinId) throws IOException, EncodeException {
        if (coinId == null || payCoinId == null) {
            return;
        }
        int num = 0;
        int error = 0;
        int count = 0;
        try {
            DataModel<MarketDetailVo> dataModel = marketIfac.getMarketDetails(coinId, payCoinId);
            CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_DETAIL.getValue());
            if (sockets.size() != 0) {//有人订阅才进来
                log.info("交易中心成交深度主题订阅任务有: "+ sockets.size()+"个人。");
                for (MarketWebSocket marketWebSocket : sockets) {
                    if (marketWebSocket.getSession() != null) {
                        String sysbol = dataModel.getModel().getSymbol().toLowerCase();
                        String websysbol =String.valueOf(marketWebSocket.getParamsMap().get(MarketParams.SYMBOL)).toLowerCase();
                        if (sysbol.equals(websysbol)) {//订阅的交易对跟成交的交易对一致才推
                            MarketResultJson json = new MarketResultJson();
                            json.setCh(String.format(MarketURL.MARKET_SYMBOL_DETAIL.getValue(), dataModel.getModel().getSymbol()));
                            json.setTick(dataModel.getModel());
                            ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(), json);
                            num++;
                        }else{
                            log.info("订阅的交易对为："+websysbol+",查出来的交易对为："+sysbol);
                            count++;
                        }
                    }else{
                        log.info("交易中心订阅成功了，但是session为null："+JSONObject.toJSONString(marketWebSocket));
                        error++;
                    }
                }
                log.info("订阅的交易不是成交的交易对的有："+count+"条");
                log.info("交易中心成交深度一共推送了"+num+"次给前端，session为null有"+error+"次没有推。一起有"+sockets.size()+"个人订阅了这个主题");
            }else{
                log.info("没有用户订阅交易中心主题>>>>>>>>>>>>>>>>>>>");
            }
        } catch (Exception e) {
            log.info("交易中心成交深度推送异常，请查看 ："+e);
        }
    }
}

