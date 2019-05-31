package com.yobtc.domain.vo;

import java.io.Serializable;
import java.util.List;

public class MarketDetailListVo implements Serializable {

    private static final long serialVersionUID = -2905588615545599457L;

    private List<MarketDetailVo> listVo;

    private List<MarketDetailVo> userInterestList;

    private MarketDetailVo marketDetailVo;

    private Integer userId;

    private Integer payCoinId;

    public MarketDetailVo getMarketDetailVo() {
        return marketDetailVo;
    }

    public void setMarketDetailVo(MarketDetailVo marketDetailVo) {
        this.marketDetailVo = marketDetailVo;
    }

    public List<MarketDetailVo> getUserInterestList() {
        return userInterestList;
    }

    public void setUserInterestList(List<MarketDetailVo> userInterestList) {
        this.userInterestList = userInterestList;
    }

    public Integer getPayCoinId() {
        return payCoinId;
    }

    public void setPayCoinId(Integer payCoinId) {
        this.payCoinId = payCoinId;
    }

    public List<MarketDetailVo> getListVo() {
        return listVo;
    }

    public void setListVo(List<MarketDetailVo> listVo) {
        this.listVo = listVo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
