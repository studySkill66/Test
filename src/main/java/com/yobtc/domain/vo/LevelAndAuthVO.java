package com.yobtc.domain.vo;

import java.math.BigDecimal;

public class LevelAndAuthVO {
    private Integer id;
    private String levelname;
    private BigDecimal minaccumulate;
    private BigDecimal maxaccumulate;
    private String levelremark;
    private Integer levelType;
    private Integer sort;
    private String suffix;
    //买币手续费折扣
    private BigDecimal payCoin;
    //卖币手续费折扣
    private BigDecimal sellCoin;
    //提币手续费折扣
    private BigDecimal getCoin;
    //提现手续费折扣
    private BigDecimal getRmb;
    //PUSH买手续费折扣
    private BigDecimal pushPay;
    //PUSH卖手续费折扣
    private BigDecimal pushSell;
    //提出资产额度
    private BigDecimal getAsset;
    //交易中心可见档位深度
    private Integer tradeCenterDepth;
    //是否提供批量撤单功能
    private Boolean isBatchUndoOrder;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelname() {
        return this.levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public BigDecimal getMinaccumulate() {
        return this.minaccumulate;
    }

    public void setMinaccumulate(BigDecimal minaccumulate) {
        this.minaccumulate = minaccumulate;
    }

    public BigDecimal getMaxaccumulate() {
        return this.maxaccumulate;
    }

    public void setMaxaccumulate(BigDecimal maxaccumulate) {
        this.maxaccumulate = maxaccumulate;
    }

    public String getLevelremark() {
        return this.levelremark;
    }

    public void setLevelremark(String levelremark) {
        this.levelremark = levelremark;
    }

    public Integer getLevelType() {
        return this.levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public BigDecimal getPayCoin() {
        return this.payCoin;
    }

    public void setPayCoin(BigDecimal payCoin) {
        this.payCoin = payCoin;
    }

    public BigDecimal getSellCoin() {
        return this.sellCoin;
    }

    public void setSellCoin(BigDecimal sellCoin) {
        this.sellCoin = sellCoin;
    }

    public BigDecimal getGetCoin() {
        return this.getCoin;
    }

    public void setGetCoin(BigDecimal getCoin) {
        this.getCoin = getCoin;
    }

    public BigDecimal getGetRmb() {
        return this.getRmb;
    }

    public void setGetRmb(BigDecimal getRmb) {
        this.getRmb = getRmb;
    }

    public BigDecimal getPushPay() {
        return this.pushPay;
    }

    public void setPushPay(BigDecimal pushPay) {
        this.pushPay = pushPay;
    }

    public BigDecimal getPushSell() {
        return this.pushSell;
    }

    public void setPushSell(BigDecimal pushSell) {
        this.pushSell = pushSell;
    }

    public BigDecimal getGetAsset() {
        return this.getAsset;
    }

    public void setGetAsset(BigDecimal getAsset) {
        this.getAsset = getAsset;
    }

    public Integer getTradeCenterDepth() {
        return this.tradeCenterDepth;
    }

    public void setTradeCenterDepth(Integer tradeCenterDepth) {
        this.tradeCenterDepth = tradeCenterDepth;
    }

    public Boolean getBatchUndoOrder() {
        return this.isBatchUndoOrder;
    }

    public void setBatchUndoOrder(Boolean batchUndoOrder) {
        this.isBatchUndoOrder = batchUndoOrder;
    }
}


/* Location:              /Users/Edanel/Downloads/20180302-0448/yobtc-user-service_1.1.0.jar!/BOOT-INF/classes/com/yobtc/domain/vo/LevelAndAuthVO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */