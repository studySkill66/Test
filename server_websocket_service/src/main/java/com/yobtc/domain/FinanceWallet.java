package com.yobtc.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Wallet(钱包信息模型)")
public class FinanceWallet {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("用户ID")
    private Integer userid;
    @ApiModelProperty("币种ID")
    private Integer coinid;
    @ApiModelProperty("数量")
    private BigDecimal amount;
    @ApiModelProperty("冻结数量")
    private BigDecimal amountfrozen;
    @ApiModelProperty("冲币地址")
    private String inaddr;
    @ApiModelProperty("创建时间")
    private Long createtime;
    @ApiModelProperty("修改时间")
    private Long updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountfrozen() {
        return amountfrozen;
    }

    public void setAmountfrozen(BigDecimal amountfrozen) {
        this.amountfrozen = amountfrozen;
    }

    public String getInaddr() {
        return inaddr;
    }

    public void setInaddr(String inaddr) {
        this.inaddr = inaddr == null ? null : inaddr.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }
}