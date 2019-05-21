package com.yobtc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class TradeClinch   implements Serializable {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCoinid() {
        return coinid;
    }

    public void setCoinid(Integer coinid) {
        this.coinid = coinid;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEntrustid() {
        return entrustid;
    }

    public void setEntrustid(String entrustid) {
        this.entrustid = entrustid == null ? null : entrustid.trim();
    }

    public Integer getAlternatefield() {
        return alternatefield;
    }

    public void setAlternatefield(Integer alternatefield) {
        this.alternatefield = alternatefield == null ? null : alternatefield;
    }

    public String getClinchsource() {
        return clinchsource;
    }

    public void setClinchsource(String clinchsource) {
        this.clinchsource = clinchsource == null ? null : clinchsource.trim();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getCutTime() {
        return cutTime;
    }

    public void setCutTime(Integer cutTime) {
        this.cutTime = cutTime;
    }
}