package com.yobtc.domain.vo;

import com.hksy.framework.util.TimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@ApiModel("FinanceCoinTradeInfo(币种交易相关信息)")
public class FinanceCoinTradeInfo {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("币种ID")
    private Integer coinid;
    @ApiModelProperty("每日涨幅，0表示无限制")
    private BigDecimal tradeincreaselimit;
    @ApiModelProperty("开盘价，0表示无限制")
    private BigDecimal tradeopeningprice;
    @ApiModelProperty("最大交易价格,0表示无限制")
    private BigDecimal tradepricemax;
    @ApiModelProperty("最大交易数量,0表示无限制")
    private BigDecimal tradeamountmax;
    @ApiModelProperty("最小交易价格,0表示无限制")
    private BigDecimal tradepricemin;
    @ApiModelProperty("最小交易数量,0表示无限制")
    private BigDecimal tradeamountmin;
    @ApiModelProperty("交易的虚拟币价格的最小单位小数的点精度,交易价格精度")
    private Integer tradepricenum;
    @ApiModelProperty("交易的虚拟币数量的最小单位小数的点精度,交易量精度")
    private Integer tradeamountnum;
    @ApiModelProperty("卖手续费")
    private BigDecimal tradesellfee;
    @ApiModelProperty("买手续费")
    private BigDecimal tradebuyfee;
    @ApiModelProperty("单日卖出数量限制,0表示无限制")
    private BigDecimal tradesellamountlimit;
    @ApiModelProperty("单日买入数量限制,0表示无限制")
    private BigDecimal tradebuyamountlimit;
    @ApiModelProperty("交易时间限制,0关闭；1打开")
    private Boolean tradetimeenable;
    @ApiModelProperty("单日挂卖单比例")
    private BigDecimal tradedaysellrate;
    @ApiModelProperty("端到端交易买手续费")
    private BigDecimal tradeportbuyfee;
    @ApiModelProperty("端到端交易卖手续费")
    private BigDecimal tradeportsellfee;
    @ApiModelProperty("交易日期（例：1,2,3 为周1.2.3可以交易）")
    private String tradedate;
    @ApiModelProperty("每日开盘时间 时间格式为：00:00:00")
    private String tradeopentime;
    @ApiModelProperty("每日收盘时间 时间格式为：00:00:00")
    private String tradeclosetime;
    @ApiModelProperty("周末休市，0关闭；1打开")
    private Boolean weekendcloseenable;
    @ApiModelProperty("创建时间")
    private Long createtime;
    @ApiModelProperty("修改时间")
    private Long updatetime;
    @ApiModelProperty("货币交易支付币种")
    private Integer payCoinId;
    @ApiModelProperty("是否开始交易")
    private Boolean tradeStatus;
    @ApiModelProperty("交易撮合时间")
    private String tradetime;

    public String getTradetime() {
        String result = "";
        String tradeOpenTime = this.getTradeopentime();	//开盘时间
        String tradeCloseTime = this.getTradeclosetime();	//收盘时间
        String trade_date = this.getTradedate();	//日期
        if(!StringUtils.isBlank(tradeOpenTime)&&!StringUtils.isBlank(tradeCloseTime)
                &&!StringUtils.isBlank(trade_date)){
            if(trade_date.equals("1,2,3,4,5,6,7")&&tradeOpenTime.equals("00:00:00")&&tradeCloseTime.equals("23:59:59")){
                result = "7*24H";
            }else{
                try {
                    int datWeek = TimeUtil.dayForWeek(TimeUtil.getNowDate());
                    if(trade_date.indexOf(String.valueOf(datWeek)) > -1){
                        result = tradeOpenTime + "－" + tradeCloseTime;
                    }else{
                        result = "休市";
                    }
                } catch (Exception e) {
                }
            }
        }
        if(StringUtils.isBlank(trade_date)){
            result = "休市";
        }
        return result;
    }

    public void setTradetime(String tradetime) {
        this.tradetime = tradetime;
    }

    public Integer getPayCoinId() {
        return payCoinId;
    }

    public void setPayCoinId(Integer payCoinId) {
        this.payCoinId = payCoinId;
    }

    public Boolean getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Boolean tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoinid() {
        return coinid;
    }

    public void setCoinid(Integer coinid) {
        this.coinid = coinid;
    }

    public BigDecimal getTradeincreaselimit() {
        return tradeincreaselimit;
    }

    public void setTradeincreaselimit(BigDecimal tradeincreaselimit) {
        this.tradeincreaselimit = tradeincreaselimit;
    }

    public BigDecimal getTradeopeningprice() {
        return tradeopeningprice;
    }

    public void setTradeopeningprice(BigDecimal tradeopeningprice) {
        this.tradeopeningprice = tradeopeningprice;
    }

    public BigDecimal getTradepricemax() {
        return tradepricemax;
    }

    public void setTradepricemax(BigDecimal tradepricemax) {
        this.tradepricemax = tradepricemax;
    }

    public BigDecimal getTradeamountmax() {
        return tradeamountmax;
    }

    public void setTradeamountmax(BigDecimal tradeamountmax) {
        this.tradeamountmax = tradeamountmax;
    }

    public BigDecimal getTradepricemin() {
        return tradepricemin;
    }

    public void setTradepricemin(BigDecimal tradepricemin) {
        this.tradepricemin = tradepricemin;
    }

    public BigDecimal getTradeamountmin() {
        return tradeamountmin;
    }

    public void setTradeamountmin(BigDecimal tradeamountmin) {
        this.tradeamountmin = tradeamountmin;
    }

    public Integer getTradepricenum() {
        return tradepricenum;
    }

    public void setTradepricenum(Integer tradepricenum) {
        this.tradepricenum = tradepricenum;
    }

    public Integer getTradeamountnum() {
        return tradeamountnum;
    }

    public void setTradeamountnum(Integer tradeamountnum) {
        this.tradeamountnum = tradeamountnum;
    }

    public BigDecimal getTradesellfee() {
        return tradesellfee;
    }

    public void setTradesellfee(BigDecimal tradesellfee) {
        this.tradesellfee = tradesellfee;
    }

    public BigDecimal getTradebuyfee() {
        return tradebuyfee;
    }

    public void setTradebuyfee(BigDecimal tradebuyfee) {
        this.tradebuyfee = tradebuyfee;
    }

    public BigDecimal getTradesellamountlimit() {
        return tradesellamountlimit;
    }

    public void setTradesellamountlimit(BigDecimal tradesellamountlimit) {
        this.tradesellamountlimit = tradesellamountlimit;
    }

    public BigDecimal getTradebuyamountlimit() {
        return tradebuyamountlimit;
    }

    public void setTradebuyamountlimit(BigDecimal tradebuyamountlimit) {
        this.tradebuyamountlimit = tradebuyamountlimit;
    }

    public Boolean getTradetimeenable() {
        return tradetimeenable;
    }

    public void setTradetimeenable(Boolean tradetimeenable) {
        this.tradetimeenable = tradetimeenable;
    }

    public BigDecimal getTradedaysellrate() {
        return tradedaysellrate;
    }

    public void setTradedaysellrate(BigDecimal tradedaysellrate) {
        this.tradedaysellrate = tradedaysellrate;
    }

    public BigDecimal getTradeportbuyfee() {
        return tradeportbuyfee;
    }

    public void setTradeportbuyfee(BigDecimal tradeportbuyfee) {
        this.tradeportbuyfee = tradeportbuyfee;
    }

    public BigDecimal getTradeportsellfee() {
        return tradeportsellfee;
    }

    public void setTradeportsellfee(BigDecimal tradeportsellfee) {
        this.tradeportsellfee = tradeportsellfee;
    }

    public String getTradedate() {
        return tradedate;
    }

    public void setTradedate(String tradedate) {
        this.tradedate = tradedate == null ? null : tradedate.trim();
    }

    public String getTradeopentime() {
        return tradeopentime;
    }

    public void setTradeopentime(String tradeopentime) {
        this.tradeopentime = tradeopentime == null ? null : tradeopentime.trim();
    }

    public String getTradeclosetime() {
        return tradeclosetime;
    }

    public void setTradeclosetime(String tradeclosetime) {
        this.tradeclosetime = tradeclosetime == null ? null : tradeclosetime.trim();
    }

    public Boolean getWeekendcloseenable() {
        return weekendcloseenable;
    }

    public void setWeekendcloseenable(Boolean weekendcloseenable) {
        this.weekendcloseenable = weekendcloseenable;
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