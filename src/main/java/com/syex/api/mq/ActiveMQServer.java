package com.syex.api.mq;

import com.alibaba.fastjson.JSONArray;
import com.syex.api.result.MarketResultJson;
import com.syex.api.tools.MarketParams;
import com.syex.api.tools.MarketURL;
import com.syex.api.tools.ZipUtil;
import com.syex.api.ws.MarketWebSocket;
import com.yobtc.domain.TradeDetailResult;
import com.yobtc.domain.vo.MarketDepthVo;
import com.yobtc.domain.vo.MarketDetailVo;
import com.yobtc.domain.vo.TradeDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
public class ActiveMQServer {

    /**
     * 最新成交记录WebSocket推送
     * <br/>
     *
     * @param msgResult TradeDetailResult<TradeDetailVo>
     * @return null
     * @author Mr.Lming
     * @date 2019/4/23 19:46
     **/
    @JmsListener(destination = "market.trade.detail", containerFactory = "jmsListenerContainerTopic")
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
                        json.setCh("market." + msgResult.getSymbol() + ".detail");
                        json.setTs(System.currentTimeMillis());
                        json.setTick(msgResult);
                        ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(),json);
                        log.info("最新成交记录WebSocket推送：" + JSONArray.toJSONString(json));
                    }
                }
            }
        }
    }

    @JmsListener(destination = "marketdetail", containerFactory = "jmsListenerContainerTopic")
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
                        json.setCh("market." + marketDetailVo.getSymbol() + ".detail");
                        json.setTick(marketDetailVo);
                        ZipUtil.gzAndSendWesocket(marketWebSocket.getSession(),json);
                    }
                }
            }
        }
    }

    @JmsListener(destination = "marketdepth", containerFactory = "jmsListenerContainerTopic")
    public void receive(MarketDepthVo marketDepthVo) throws IOException, EncodeException {
        if (marketDepthVo == null || StringUtils.isBlank(marketDepthVo.getSymbol())) {
            return;
        }
        CopyOnWriteArraySet<MarketWebSocket> sockets = MarketWebSocket.webSocketMap.get(MarketURL.MARKET_SYMBOL_DEPTH_TYPE.getValue());
        if (sockets.size() != 0) {
            for (MarketWebSocket marketDepthWebSocket : sockets) {
                if (marketDepthWebSocket.getSession() != null) {
                    if (marketDepthVo.getSymbol().equalsIgnoreCase(String.valueOf(marketDepthWebSocket.getParamsMap().get(MarketParams.SYMBOL)))) {
                        MarketResultJson json = new MarketResultJson();
                        json.setCh("market." + marketDepthVo.getSymbol() + ".depth." + marketDepthWebSocket.getParamsMap().get(MarketParams.TYPE));
                        json.setTick(marketDepthVo);
                        ZipUtil.gzAndSendWesocket(marketDepthWebSocket.getSession(),json);
                    }
                }
            }
        }
    }
}
