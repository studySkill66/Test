package com.hksy.framework.common.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 未成交订单
 */
@Data
public class OrdersNoDealVO {

    //订单id
    private String id;
    //交易对
    private String symbol;
    //下单价格
    private BigDecimal price;
    //下单时间（毫秒）
    private Long created_at;
    //订单类型(1、买2、卖)
    private Byte type;
    //已成交单量
    private BigDecimal filled_amount;
    //已成交部分的订单价格(=已成交单量x下单价格)
    private  BigDecimal filled_cash_amount;
    //已成交部分所收取手续费
    private BigDecimal filled_fees;
    //订单来源
    private String source;
    //订单状态(0、提交中1、进行中-未成交2、进行中-部分成交3、关闭-完全成交4、关闭-未成交
    // 5、关闭-部分成交6、关闭-提交中7、异常-委托单8、关闭-异常委托单)
    private Byte state;

}
