package com.yobtc.domain;

import com.hksy.framework.util.TimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("挂单信息模型")
@Data
public class TradeAre implements Serializable {
    private static final long serialVersionUID = -921008687184331221L;
    @ApiModelProperty("自增ID")
    private String id;
    @ApiModelProperty("币种ID")
    private Integer coinid;
    @ApiModelProperty("挂单类型")
    private Byte type;
    @ApiModelProperty("挂单数量")
    private BigDecimal amount;
    @ApiModelProperty("挂单价格")
    private BigDecimal price;
    @ApiModelProperty("状态")
    private Byte status;
    @ApiModelProperty("创建时间")
    private Long ctime;
    @ApiModelProperty("更新时间")
    private Long etime;
    @ApiModelProperty("用户给ID")
    private Integer userid;
    @ApiModelProperty("委托单ID")
    private String entrustid;
    @ApiModelProperty("支付币种ID")
    private Integer paycoinid;
    @ApiModelProperty("交易对【币种-支付币种】")
    private String symbol;
    @ApiModelProperty("预留时间分表字段")
    private Integer cutTime;

    public TradeAre(){}

    public TradeAre(String id, Integer coinid, Byte type, BigDecimal amount, BigDecimal price, Byte status, Integer userid, String entrustid, Integer paycoinid, String symbol) {
        this.id = id;
        this.coinid = coinid;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.status = status;
        this.ctime = TimeUtil.getUnixTime();
        this.userid = userid;
        this.entrustid = entrustid;
        this.paycoinid = paycoinid;
        this.symbol = symbol;
        this.cutTime = TimeUtil.formatDateTimeYearAndMonth(new Date());
    }
}