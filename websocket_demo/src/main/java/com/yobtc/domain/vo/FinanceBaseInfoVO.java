package com.yobtc.domain.vo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/29 0029.
 */
public class FinanceBaseInfoVO {

    private Integer id;

    private String logo;

    private String defaultEnName;

    private String defaultCnName;

    private BigDecimal amount;

    private BigDecimal amountFrozen;

    private BigDecimal sumAmount;

    private Boolean walletEnable;

    private Boolean walletEnableWithdraw;

    private int walletRechargeWay;

    private BigDecimal toHkdPrice;

    public BigDecimal getToHkdPrice() {
        return toHkdPrice;
    }

    public void setToHkdPrice(BigDecimal toHkdPrice) {
        this.toHkdPrice = toHkdPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWalletRechargeWay() {
        return walletRechargeWay;
    }

    public void setWalletRechargeWay(int walletRechargeWay) {
        this.walletRechargeWay = walletRechargeWay;
    }

    public Boolean getWalletEnable() {
        return walletEnable;
    }

    public void setWalletEnable(Boolean walletEnable) {
        this.walletEnable = walletEnable;
    }

    public Boolean getWalletEnableWithdraw() {
        return walletEnableWithdraw;
    }

    public void setWalletEnableWithdraw(Boolean walletEnableWithdraw) {
        this.walletEnableWithdraw = walletEnableWithdraw;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDefaultEnName() {
        return defaultEnName;
    }

    public void setDefaultEnName(String defaultEnName) {
        this.defaultEnName = defaultEnName;
    }

    public String getDefaultCnName() {
        return defaultCnName;
    }

    public void setDefaultCnName(String defaultCnName) {
        this.defaultCnName = defaultCnName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountFrozen() {
        return amountFrozen;
    }

    public void setAmountFrozen(BigDecimal amountFrozen) {
        this.amountFrozen = amountFrozen;
    }

    public BigDecimal getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(BigDecimal sumAmount) {
        this.sumAmount = sumAmount;
    }
}
