package com.yobtc.domain.vo;

import java.io.Serializable;

public class MarketDepthVo implements Serializable {

    private static final long serialVersionUID = -821228687184331557L;

    private String symbol;
    private Object asks;
    private Object bids;
    private String ch;
    private Long id;
    private Long mrid;
    private Long ts;
    private Long version;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Object getAsks() {
        return asks;
    }

    public void setAsks(Object asks) {
        this.asks = asks;
    }

    public Object getBids() {
        return bids;
    }

    public void setBids(Object bids) {
        this.bids = bids;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMrid() {
        return mrid;
    }

    public void setMrid(Long mrid) {
        this.mrid = mrid;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
