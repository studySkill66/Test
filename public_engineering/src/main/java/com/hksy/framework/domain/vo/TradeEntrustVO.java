package com.hksy.framework.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeEntrustVO {
    private String id;

    private Integer userid;

    private Integer coinid;

    private Byte type;

    private BigDecimal entrustprice;

    private BigDecimal dealsumprice;

    private BigDecimal amount;

    private Byte status;

    private Long createtime;

    private Long endtime;

    private String remark;

    private Integer alternatefield;

    private BigDecimal surplusamount;

    private String entrustsource;

    private Integer dealstatus;

    private BigDecimal entrustfeediscount;

    private Integer randomnum;

    private String coinName;

    private String payCoinName;

    private String symbol;

    private Integer cuttime;
}