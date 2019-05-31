package com.hksy.framework.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class TradeEntrustWebSocketVO {

    //用户id
    private Integer userId;
    //用户对应的委托单
    private List<TradeEntrustVO> tradeEntrustVOList;
    //币种ID
    private Integer coinId;
    //币种英文名
    private String defaultEnName;
    //支付币种ID
    private Integer payCoinId;
    //支付币种币种英文名
    private String paydefaultEnName;

}
