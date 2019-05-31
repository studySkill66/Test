package com.yobtc.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("FinanceRechargeRmb(人民币冲提记录信息)")
public class FinanceRechargeRmb {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("用户IP地址")
    private String userip;
    @ApiModelProperty("用户ID")
    private Integer userid;
    @ApiModelProperty("数量")
    private BigDecimal amount;
    @ApiModelProperty("用户真是姓名")
    private String name;
    @ApiModelProperty("提现地址")
    private String address;
    @ApiModelProperty("开户行")
    private String bankopening;
    @ApiModelProperty("银行名称")
    private String bank;
    @ApiModelProperty("银行卡号")
    private String card;
    @ApiModelProperty("管理员ID")
    private Integer adminid;
    @ApiModelProperty("创建时间")
    private Long createtime;
    @ApiModelProperty("修改时间")
    private Long updatetime;
    @ApiModelProperty("状态 0:正在处理，1：支付成功，2：支付失败")
    private Integer status;
    @ApiModelProperty("类型 1表示人民币充值，2表示人民币提现")
    private Integer type;
    @ApiModelProperty("充值类型 0智付通自动，1支付宝自动，3支付宝人工，4银行卡汇款，5充值码充值")
    private Integer fintype;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("数据来源")
    private String addsource;
    @ApiModelProperty("创建时间")
    private String createtimestr;
    @ApiModelProperty("修改时间")
    private String updatetimestr;
    @ApiModelProperty("原状态")
    private Integer statusTo;
    @ApiModelProperty("实际到账")
    private BigDecimal actualArrival;
    @ApiModelProperty("是否快速到账")
    private Boolean fastArrival;
    @ApiModelProperty("充值类型 0智付通自动，1支付宝自动，3支付宝人工，4银行卡汇款，5充值码充值")
    private String fintypeStr;

    public String getFintypeStr() {
        return fintypeStr;
    }

    public void setFintypeStr(String fintypeStr) {
        this.fintypeStr = fintypeStr;
    }

    public Boolean getFastArrival() {
        return fastArrival;
    }

    public void setFastArrival(Boolean fastArrival) {
        this.fastArrival = fastArrival;
    }

    public BigDecimal getActualArrival() {
        return actualArrival;
    }

    public void setActualArrival(BigDecimal actualArrival) {
        this.actualArrival = actualArrival;
    }

    public Integer getStatusTo() {
        return statusTo;
    }

    public void setStatusTo(Integer statusTo) {
        this.statusTo = statusTo;
    }

    public String getCreatetimestr() {
        return createtimestr;
    }

    public void setCreatetimestr(String createtimestr) {
        this.createtimestr = createtimestr;
    }

    public String getUpdatetimestr() {
        return updatetimestr;
    }

    public void setUpdatetimestr(String updatetimestr) {
        this.updatetimestr = updatetimestr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFintype() {
        return fintype;
    }

    public void setFintype(Integer fintype) {
        this.fintype = fintype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip == null ? null : userip.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBankopening() {
        return bankopening;
    }

    public void setBankopening(String bankopening) {
        this.bankopening = bankopening == null ? null : bankopening.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAddsource() {
        return addsource;
    }

    public void setAddsource(String addsource) {
        this.addsource = addsource == null ? null : addsource.trim();
    }
}