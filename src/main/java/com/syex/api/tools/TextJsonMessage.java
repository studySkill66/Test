package com.syex.api.tools;

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
}
