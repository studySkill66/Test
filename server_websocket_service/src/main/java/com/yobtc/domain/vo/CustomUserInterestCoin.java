package com.yobtc.domain.vo;

public class CustomUserInterestCoin {
    private Long id;

    private  Integer userId;

    private Integer coinId;

    private Integer payCoinId;

    private Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
