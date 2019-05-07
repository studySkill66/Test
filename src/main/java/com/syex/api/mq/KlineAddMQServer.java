package com.syex.api.mq;

import com.syex.api.result.MarketResultJson;
import com.syex.api.tools.MarketParams;
import com.syex.api.tools.MarketURL;
import com.syex.api.tools.ZipUtil;
import com.syex.api.ws.MarketWebSocket;
import com.yobtc.domain.KLineDetailResult;
import com.yobtc.domain.vo.KLineDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 发送K线的增量Websocket服务
 */
@Component
@Slf4j
public class KlineAddMQServer {
    @Deprecated
    @JmsListener(destination = "market.klineadd.btc.usdt.60min", containerFactory = "jmsListenerContainerTopic")
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
    @JmsListener(destination = "marketklineadd", containerFactory = "jmsListenerContainerTopic")
    public void sendKLineAddWebSocket(String msg) throws IOException, EncodeException {
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

    /**
     * 最新KLineWebSocket推送
     **/
    @JmsListener(destination = "market.klineadd", containerFactory = "jmsListenerContainerTopic")
    public void sendMarketKLineAddWebSocket(KLineDetailResult<KLineDetailVo> msgResult) throws IOException, EncodeException {
        if (msgResult == null || StringUtils.isBlank(msgResult.getSymbol())) {
            return;
        }

        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_KLINE_PERIOD.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketWebSocket : sockets) {
                if (marketWebSocket.getSession() != null) {
                    // 接受MQ的交易对 与 当前WebSocket回话连接的交易对一致
                    if (msgResult.getSymbol().equalsIgnoreCase(String.valueOf(marketWebSocket.getParamsMap().get(MarketParams.SYMBOL)))
                            && msgResult.getPeriod().equalsIgnoreCase(String.valueOf(marketWebSocket.getParamsMap().get(MarketParams.PERIOD)))) {
                        MarketResultJson json = new MarketResultJson<KLineDetailVo>();
                        json.setRep("market." + msgResult.getSymbol().toLowerCase() + ".kline." + msgResult.getPeriod().toLowerCase());
                        json.setTs(System.currentTimeMillis());
                        json.setTick(msgResult.getTick());
                        // json.setId();
                        ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(),json);
                        log.info("最新成交KLine推送WebSocket服务：" + json.getCh());
                    }
                }
            }
        }
    }
}
