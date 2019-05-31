package com.yobtc.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("FinanceCoinBaseInfo(币种基本信息)")
public class FinanceCoinBaseInfo {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("币种分类ID")
    private Integer tradetypeid;
    @ApiModelProperty("币种LOGO")
    private String logo;
    @ApiModelProperty("币种英文名")
    private String defaultenname;
    @ApiModelProperty("币种中文名")
    private String defaultcnname;
    @ApiModelProperty("第一次开盘时间")
    private Long opentradetime;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("状态")
    private Boolean status;
    @ApiModelProperty("向推荐者发放买交易利润比率（不含push）")
    private BigDecimal buytransactionproportion;
    @ApiModelProperty("向推荐者发放卖交易利润比率（不含push）")
    private BigDecimal selltransactionproportion;
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

    public Integer getTradetypeid() {
        return tradetypeid;
    }

    public void setTradetypeid(Integer tradetypeid) {
        this.tradetypeid = tradetypeid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getDefaultenname() {
        return defaultenname;
    }

    public void setDefaultenname(String defaultenname) {
        this.defaultenname = defaultenname == null ? null : defaultenname.trim();
    }

    public String getDefaultcnname() {
        return defaultcnname;
    }

    public void setDefaultcnname(String defaultcnname) {
        this.defaultcnname = defaultcnname == null ? null : defaultcnname.trim();
    }

    public Long getOpentradetime() {
        return opentradetime;
    }

    public void setOpentradetime(Long opentradetime) {
        this.opentradetime = opentradetime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigDecimal getBuytransactionproportion() {
        return buytransactionproportion;
    }

    public void setBuytransactionproportion(BigDecimal buytransactionproportion) {
        this.buytransactionproportion = buytransactionproportion;
    }

    public BigDecimal getSelltransactionproportion() {
        return selltransactionproportion;
    }

    public void setSelltransactionproportion(BigDecimal selltransactionproportion) {
        this.selltransactionproportion = selltransactionproportion;
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