package com.syex.api.ws;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syex.api.mq.ServerEncoder;
import com.syex.api.result.MarketResultJson;
import com.syex.api.tools.MarketURL;
import com.syex.api.tools.RegexUtils;
import com.syex.api.tools.TextJsonMessage;
import com.syex.api.tools.ZipUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
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
     * 连接关闭调用的方法
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
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        MarketResultJson json = new MarketResultJson();
        TextJsonMessage tjMsg = null;
        try {
            tjMsg = JSONObject.parseObject(message, TextJsonMessage.class);
        } catch (Exception e) {
            json.setStatus("ERROR");
            json.setErr_msg("收到的异常消息！");
            ZipUtil.gzAndSendWesocket(session,json);
            return;
        }

        String sub = tjMsg.getSub();
        if (StringUtils.isBlank(sub)) {
            sub = tjMsg.getReq();
        }

        json.setId(tjMsg.getId());
        json.setCh(message);
        json.setTs(System.currentTimeMillis());

        for (MarketURL url : MarketURL.values()) {
            if (RegexUtils.isMatches(url.getRegex(), sub)) {
                String[] params = sub.split("\\.");
                for (Map.Entry index : url.getParamIndexMap().entrySet()) {
                    this.paramsMap.put(String.valueOf(index.getKey()), params[(int) index.getValue()]);
                }

                this.session = session;
                //加入set中
                webSocketMap.get(url.getValue()).add(this);
                json.setStatus("OK");
                ZipUtil.gzAndSendWesocket(session,json);
                log.info("+++++++++++++++" + url.getValue() + "有新连接加入！当前在线人数为：" + webSocketMap.get(url.getValue()).size() + "+++++++++++++++");
                return;
            }
        }
        // 响应消息
        json.setStatus("ERROR");
        json.setErr_msg("收到的异常消息！");
        ZipUtil.gzAndSendWesocket(session,json);
//        throw new RuntimeException();
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public HashMap<String, Object> getParamsMap() {
        return paramsMap;
    }
}
