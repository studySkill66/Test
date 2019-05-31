package com.yobtc.domain.vo;


import java.io.Serializable;

public class MarketDetailVo implements Serializable {

    private static final long serialVersionUID = -621228687184331557L;

    private String symbol;

    private Long id;

    private Integer count;

    private String open;

    private String close;

    private String low;

    private String high;

    private String amount;

    private String vol;

    private long ts;

    private String  range24;

    private String defaultenname;

    private String defaultcnname;

    private String defaultpayenname;

    private Byte type;

    private String logo;

    private Byte isInterest;

    private Integer userId;

    private Integer payCoinId;

    private Integer coinId;


    public String getDefaultpayenname() {
        return defaultpayenname;
    }

    public void setDefaultpayenname(String defaultpayenname) {
        this.defaultpayenname = defaultpayenname;
    }

    public Integer getCoinId() {
        return coinId;
    }

    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
    }

    public Integer getPayCoinId() {
        return payCoinId;
    }

    public void setPayCoinId(Integer payCoinId) {
        this.payCoinId = payCoinId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getIsInterest() {
        return isInterest;
    }

    public void setIsInterest(Byte isInterest) {
        this.isInterest = isInterest;
    }

    public String getDefaultenname() {
        return defaultenname;
    }

    public void setDefaultenname(String defaultenname) {
        this.defaultenname = defaultenname;
    }

    public String getDefaultcnname() {
        return defaultcnname;
    }

    public void setDefaultcnname(String defaultcnname) {
        this.defaultcnname = defaultcnname;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRange24() {
        return range24;
    }

    public void setRange24(String range24) {
        this.range24 = range24;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}
