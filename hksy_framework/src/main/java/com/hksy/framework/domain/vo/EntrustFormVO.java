package com.hksy.framework.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 委托单websocket推送监听信息实体类
 */
@Data
public class EntrustFormVO implements Serializable{

    private static final long serialVersionUID = -921228687184331666L;

    //监听委托单与币种信和用户信息queue主题
    public static final String LISTENET_ENTRUSTFORM_QUEUE="listenet.entrustform.queue";

    //解决集群多台服务器问题,监听委托单与币种信和用户信息topic主题
    public static final String LISTENET_ENTRUSTFORM_TOPIC="listenet.entrustform.topic";

    //交易对
    private String symbol;

    //用户id
    private Set<Integer> userIdList;

    /**
     * false 刷新委托单和币种用户信息,true仅刷新用户信息
     */
    private boolean userEntrustFlag;

    public EntrustFormVO(String symbol, Set<Integer> userIdList) {
        this.symbol = symbol;
        this.userIdList = userIdList;
    }

    public EntrustFormVO(String symbol,Integer userId) {
        this.symbol = symbol;
        this.userIdList = new HashSet<Integer>(){{
            add(userId);
        }};
    }
}
