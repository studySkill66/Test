package com.yobtc.api.ws;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hksy.framework.common.MarketResultJson;
import com.yobtc.api.mq.ServerEncoder;
import com.yobtc.api.tools.MarketURL;
import com.yobtc.api.tools.RegexUtils;
import com.yobtc.api.tools.TextJsonMessage;
import com.yobtc.api.tools.ZipUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
 * <br/>
 *
 * @author Mr.Lming
 * @date 2019/4/25 18:42
 **/
@ServerEndpoint(value = "/market", encoders = {ServerEncoder.class})
@Component
public class MarketWebSocket {
    private static Logger log = LogManager.getLogger(MarketWebSocket.class);

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
     */
//    public static CopyOnWriteArraySet<MarketWebSocket> webSocketSet = new CopyOnWriteArraySet<MarketWebSocket>();


    /**
     * 根据连接格式分组存储的连接Map
     */
    public static HashMap<String, CopyOnWriteArraySet<MarketWebSocket>> webSocketMap = new HashMap<>();

    static {
        for (MarketURL url : MarketURL.values()) {
            webSocketMap.put(url.getValue(), new CopyOnWriteArraySet<>());
        }
    }

    /**
     * 根据连接分组存储的参数Map
     */
    private HashMap<String, Object> paramsMap = new HashMap<>();

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {
    }

    /**
     * 连接关闭时触发
     */
    @OnClose
    public void onClose() {
        //从set中删除
        for (MarketURL url : MarketURL.values()) {
            if (webSocketMap.get(url.getValue()).remove(this)) {
                log.info("+++++++++++++++" + url.getValue() + "有一连接关闭！当前在线人数为：" + webSocketMap.get(url.getValue()).size() + "+++++++++++++++");
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 来自客户端的消息
     * @param session 回话对象
     * @throws Exception
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        MarketResultJson json = new MarketResultJson();
        TextJsonMessage tjMsg = null;
        try {
            tjMsg = JSONObject.parseObject(message, TextJsonMessage.class);
            json.setId(tjMsg.getId());
            json.setTs(System.currentTimeMillis());
        } catch (Exception e) {
            json.setStatus("ERROR");
            json.setErr_msg("收到的异常消息！");
            log.error("Json解析消息异常：" + message);
            ZipUtil.gzAndSendWesocket(session, json);
            return;
        }

        // 心跳消息，Eg：{"ping":"$时间戳"}
        if (!StringUtils.isBlank(tjMsg.getPing())) {
            json = new MarketResultJson();
            json.setPong(tjMsg.getPing());
            ZipUtil.gzAndSendWesocket(session, json);
            return;
        }
        // 取消订阅
        if (!StringUtils.isBlank(tjMsg.getUnsub())) {
            this.unsub(tjMsg);
            return;
        }

        // 尝试获取消息中的订阅主题
        String sub = tjMsg.getSub();
        if (StringUtils.isBlank(sub)) {
            sub = tjMsg.getReq();
        }

        // 订阅主题
        if (!StringUtils.isBlank(sub)) {
            this.session = session;
            this.sub(tjMsg, sub);
            return;
        }
    }

    /**
     * 订阅主题
     * <br/>
     *
     * @param tjMsg TextJsonMessage
     * @param sub   订阅主题
     * @return
     * @author Mr.Lming
     * @date 2019/5/15 14:29
     **/
    private void sub(TextJsonMessage tjMsg, String sub) throws Exception {
        MarketResultJson json = new MarketResultJson();
        json.setId(tjMsg.getId());
        json.setTs(System.currentTimeMillis());
        for (MarketURL url : MarketURL.values()) {
            if (RegexUtils.isMatches(url.getRegex(), sub)) {
                String[] params = sub.split("\\.");
                for (Map.Entry index : url.getParamIndexMap().entrySet()) {
                    this.paramsMap.put(String.valueOf(index.getKey()), params[(int) index.getValue()]);
                }

                //加入set中
                webSocketMap.get(url.getValue()).add(this);
                json.setStatus("OK");
                json.setSubbed(String.format(url.getValue(), new ArrayList(paramsMap.values()).toArray()));
                ZipUtil.gzAndSendWesocket(session, json);
                log.info("+++++++++++++++" + url.getValue() + "有新连接加入！当前在线人数为：" + webSocketMap.get(url.getValue()).size() + "+++++++++++++++");
                return;
            }
        }
        // 消息无法识别
        json.setStatus("error");
        json.setErr_msg("bad-request");
        json.setErr_msg("invalid topic " + sub);
        log.error("消息无法识别：" + JSONArray.toJSONString(tjMsg));
        ZipUtil.gzAndSendWesocket(session, json);
//        throw new RuntimeException();
    }

    /**
     * 取消订阅
     * <br/>
     * Eg：{"unsub":"market.neo_usdt.trade.detail","id":"123456"}
     *
     * @param tjMsg TextJsonMessage
     * @return
     * @author Mr.Lming
     * @date 2019/5/15 14:23
     **/
    private void unsub(TextJsonMessage tjMsg) throws Exception {

        // 校验主题格式是否正确，并获取主题value
        String topic = null;
        for (MarketURL url : MarketURL.values()) {
            if (RegexUtils.isMatches(url.getRegex(), tjMsg.getUnsub())) {
                topic = url.getValue();
                break;
            }
        }
        if (StringUtils.isBlank(topic)) {
            log.error("【" + tjMsg.getUnsub() + "】取消订阅失败：" + JSONArray.toJSONString(tjMsg));
            MarketResultJson json = new MarketResultJson();
            json.setId(tjMsg.getId());
            json.setErr_code("bad-request");
            json.setErr_msg("unsub with not subbed topic " + tjMsg.getUnsub());
            json.setStatus("error");
            json.setTs(System.currentTimeMillis());
            ZipUtil.gzAndSendWesocket(this.session, json);
            return;
        }

        boolean remove = webSocketMap.get(topic).remove(this);
        if (remove) {
            log.info("+++++++++++++++" + tjMsg.getUnsub() + "取消订阅成功！当前在线人数为：" + webSocketMap.get(topic).size() + "+++++++++++++++");
            MarketResultJson json = new MarketResultJson();
            json.setId(tjMsg.getId());
            json.setUnsubbed(tjMsg.getUnsub());
            json.setStatus("OK");
            ZipUtil.gzAndSendWesocket(this.session, json);
            return;
        }
    }

    /**
     * 发生异常时触发
     *
     * @param session 回话对象，可选
     * @param error   Throwable 异常
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误", error);
    }

    public Session getSession() {
        return session;
    }

    public HashMap<String, Object> getParamsMap() {
        return paramsMap;
    }
}
