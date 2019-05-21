package com.yobtc.api.mq;

import com.hksy.framework.common.MarketResultJson;
import com.yobtc.api.tools.MarketParams;
import com.yobtc.api.tools.MarketURL;
import com.yobtc.api.tools.ZipUtil;
import com.yobtc.api.ws.MarketWebSocket;
import com.yobtc.domain.KLineDetailResult;
import com.yobtc.domain.vo.KLineDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 发送K线的增量Websocket服务
 */
@Component
@Slf4j
public class KlineAddMQServer {

    public final static Map<String,Integer> stepMap = new HashMap<String, Integer>();
    static {
        stepMap.put("1min",60);//1分钟
        stepMap.put("3min",180);//3分钟
        stepMap.put("5min",300);//5分钟
        stepMap.put("15min",900);//15分钟
        stepMap.put("30min",1800);//30分钟
        stepMap.put("1hour",3600);//1小时
        stepMap.put("2hour",7200);//2小时
        stepMap.put("4hour",14400);//4小时
        stepMap.put("6hour",21600);//6小时
        stepMap.put("12hour",43200);//12小时
        stepMap.put("1day",86400);//1天
        stepMap.put("3day",259200);//3天
        stepMap.put("1mon",604800);//1周
    }

    public final static Map<Integer,String> stepMapStr = new HashMap<Integer, String>();
    static {
        stepMapStr.put(60,"1min");//1分钟
        stepMapStr.put(180,"3min");//3分钟
        stepMapStr.put(300,"5min");//5分钟
        stepMapStr.put(900,"15min");//15分钟
        stepMapStr.put(1800,"30min");//30分钟
        stepMapStr.put(3600,"1hour");//1小时
        stepMapStr.put(7200,"2hour");//2小时
        stepMapStr.put(14400,"4hour");//4小时
        stepMapStr.put(21600,"6hour");//6小时
        stepMapStr.put(43200,"12hour");//12小时
        stepMapStr.put(86400,"1day");//1天
        stepMapStr.put(259200,"3day");//3天
        stepMapStr.put(604800,"1mon");//1周
    }
    @Deprecated
    @JmsListener(destination = "market.klineadd.btc.usdt.60min")
    public void sendTradeDetailWebSocket(String msg) throws IOException, EncodeException {
        if (msg == null || StringUtils.isBlank(msg)) {
            return;
        }

        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_KLINE_PERIOD.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketWebSocket : sockets) {
                if (marketWebSocket.getSession() != null) {
                    ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(),msg);
                    log.info("最新成交KLine WebSocket推送：" + msg);
                }
            }
        }
    }

    @Deprecated
    @JmsListener(destination = "marketklineadd")
    public void sendKLineAddWebSocket(KLineDetailVo kLineDetailVo) throws IOException, EncodeException {
        if (kLineDetailVo == null) {
            return;
        }

        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_KLINE_PERIOD.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketWebSocket : sockets) {
                if (marketWebSocket.getSession() != null) {
                        ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(), kLineDetailVo);
                        log.info("最新成交KLine WebSocket推送：" + kLineDetailVo);

                }
            }
        }
    }

    /**
     * 最新KLineWebSocket推送
     **/
    @JmsListener(destination = "market.klineadd")
    public void sendMarketKLineAddWebSocket(KLineDetailVo kLineDetailVo) throws IOException, EncodeException {
        if (kLineDetailVo == null || StringUtils.isBlank(kLineDetailVo.getSymbol())) {
            return;
        }
        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_KLINE_PERIOD.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketWebSocket : sockets) {
                if (marketWebSocket.getSession() != null) {
                    Integer num =stepMap.get(marketWebSocket.getParamsMap().get(MarketParams.PERIOD));
                    // 接受MQ的交易对 与 当前WebSocket回话连接的交易对一致
                    if (kLineDetailVo.getSymbol().equalsIgnoreCase(String.valueOf(marketWebSocket.getParamsMap().get(MarketParams.SYMBOL)))
                            && num.equals(kLineDetailVo.getStep())) {//判断session交易对以及时间是不是跟推送过来的数据相等，相等就推送
                        MarketResultJson json = new MarketResultJson<KLineDetailVo>();
                        json.setCh( String.format(MarketURL.MARKET_SYMBOL_KLINE_PERIOD.getValue(), kLineDetailVo.getSymbol(),stepMapStr.get(kLineDetailVo.getStep())));
                        json.setTs(System.currentTimeMillis());
                        json.setTick(kLineDetailVo);
                        // json.setId();
                        ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(),json);
                        log.info("最新成交KLine推送WebSocket服务：" + kLineDetailVo.getSymbol());
                    }
                }
            }
        }
    }
}
