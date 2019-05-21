package com.yobtc.api.tools;

import lombok.Data;

/**
 * 客户端消息实体
 *
 * @program: syex-api-parent
 * @description: 客户端消息实体
 * @author: Mr.Lming
 * @create: 2019-04-25 10:51
 **/

@Data
public class TextJsonMessage {
    private String id;
    private String sub;
    private String req;

    /**
     * 心跳请求消息
     */
    private String ping;

    /**
     * 心跳响应消息
     */
    private String pong;

    /**
     * 取消订阅的主题名称
     */
    private String unsub;
}
