package com.syex.api.mq;

import com.alibaba.fastjson.JSONArray;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * WebSocket Object对象传输编码类
 * <br/>
 *
 * @author Mr.Lming
 * @date 2019/4/24 11:38
 **/
public class ServerEncoder implements Encoder.Text<Object> {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(EndpointConfig arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public String encode(Object messageObj) {
        return JSONArray.toJSONString(messageObj);
    }

}