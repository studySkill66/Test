package com.yobtc.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
@ApiModel("C2cCoinInfo(c2c交易币种信息)")
public class C2cCoinInfo {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("币种ID")
    private Integer coinId;
    @ApiModelProperty("最小买入数量")
    private BigDecimal minBuyAmount;
    @ApiModelProperty("最大买入数量")
    private BigDecimal maxBuyAmount;
    @ApiModelProperty("最小卖出数量")
    private BigDecimal minSellAmount;
    @ApiModelProperty("最大卖出数量")
    private BigDecimal maxSellAmount;
    @ApiModelProperty("买单价")
    private BigDecimal buyPrice;
    @ApiModelProperty("卖单价")
    private BigDecimal sellPrice;
    @ApiModelProperty("买手续费")
    private BigDecimal buyFee;
    @ApiModelProperty("卖手续费")
    private BigDecimal sellFee;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("是否开启买,0:关闭，1：开启")
    private Boolean buyStatus;
    @ApiModelProperty("是否开启卖,0:关闭，1：开启")
    private Boolean sellStatus;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("修改时间")
    private Long updateTime;
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoinId() {
        return coinId;
    }

    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
    }

    public BigDecimal getMinBuyAmount() {
        return minBuyAmount;
    }

    public void setMinBuyAmount(BigDecimal minBuyAmount) {
        this.minBuyAmount = minBuyAmount;
    }

    public BigDecimal getMaxBuyAmount() {
        return maxBuyAmount;
    }

    public void setMaxBuyAmount(BigDecimal maxBuyAmount) {
        this.maxBuyAmount = maxBuyAmount;
    }

    public BigDecimal getMinSellAmount() {
        return minSellAmount;
    }

    public void setMinSellAmount(BigDecimal minSellAmount) {
        this.minSellAmount = minSellAmount;
    }

    public BigDecimal getMaxSellAmount() {
        return maxSellAmount;
    }

    public void setMaxSellAmount(BigDecimal maxSellAmount) {
        this.maxSellAmount = maxSellAmount;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getBuyFee() {
        return buyFee;
    }

    public void setBuyFee(BigDecimal buyFee) {
        this.buyFee = buyFee;
    }

    public BigDecimal getSellFee() {
        return sellFee;
    }

    public void setSellFee(BigDecimal sellFee) {
        this.sellFee = sellFee;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(Boolean buyStatus) {
        this.buyStatus = buyStatus;
    }

    public Boolean getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Boolean sellStatus) {
        this.sellStatus = sellStatus;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
