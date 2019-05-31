package com.hksy.framework.common.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详情
 */
@Data
public class OrdersDetailVO {

    //账户 ID
    private Integer account_id;
    //订单数量
    private BigDecimal amount;
    //订单撤销时间
    private Long canceled_at;
    //订单创建时间
    private Long created_at;
    //已成交数量
    private BigDecimal field_amount;
    //已成交总金额
    private BigDecimal field_cash_amount;
    //已成交手续费
    private BigDecimal field_fees;
    //订单变为终结态的时间，不是成交时间，包含“已撤单”状态
    private Long finished_at;
    //订单ID
    private String id;
    //订单价格
    private BigDecimal price;
    //订单来源(api)
    private String source;
    //订单状态(0、提交中1、进行中-未成交2、进行中-部分成交3、关闭-完全成交4、关闭-未成交
    // 5、关闭-部分成交6、关闭-提交中7、异常-委托单8、关闭-异常委托单)
    private Byte state;
    //交易对
    private String symbol;
    //订单类型(1、买2、卖)
    private Byte type;

}
