package com.yobtc.domain;

import com.hksy.framework.util.TimeUtil;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeClinch implements Serializable {
    private static final long serialVersionUID = -921008687184331331L;
    private Long id;

    private Integer userid;

    private Integer coinid;

    private Byte type;

    private Byte status;

    private Long createtime;

    private BigDecimal price;

    private BigDecimal amount;

    private String entrustid;

    private Integer alternatefield;

    private String clinchsource;

    private String symbol;

    private Integer cutTime;

    public TradeClinch(){}
    public TradeClinch(Integer userid, Integer coinid, Byte type, Byte status, BigDecimal price, BigDecimal amount, String entrustid, Integer alternatefield, String clinchsource, String symbol) {
        this.userid = userid;
        this.coinid = coinid;
        this.type = type;
        this.status = status;
        this.createtime = TimeUtil.getUnixTime();
        this.price = price;
        this.amount = amount;
        this.entrustid = entrustid;
        this.alternatefield = alternatefield;
        this.clinchsource = clinchsource;
        this.symbol = symbol;
        this.cutTime = TimeUtil.formatDateTimeYearAndMonth(new Date());
    }
}