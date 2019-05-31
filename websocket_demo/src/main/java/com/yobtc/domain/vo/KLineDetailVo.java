package com.yobtc.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * KLine 数据
 */
@Data
public class KLineDetailVo implements Serializable {
    private static final long serialVersionUID = -921228687184331557L;

    /**
     * Id
     */
    private Long id;
    /**
     * 销售量
     */
    private BigDecimal amount;
    /**
     * 销售数
     */
    private Integer count;
    /**
     * 开盘价
     */
    private BigDecimal open;
    /**
     * 收盘价
     */
    private BigDecimal close;
    /**
     * 最低价
     */
    private BigDecimal low;
    /**
     *  最高价
     */
    private BigDecimal high;
    /**
     * 销售额
     */
    private BigDecimal vol;

    private Long min;

    private Integer step;

    private String symbol;
    private Byte isInterest;

    private Integer payCoinId;

    public Byte getIsInterest() {
        return isInterest;
    }

    public void setIsInterest(Byte isInterest) {
        this.isInterest = isInterest;
    }

    public Integer getPayCoinId() {
        return payCoinId;
    }

    public void setPayCoinId(Integer payCoinId) {
        this.payCoinId = payCoinId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }
}
